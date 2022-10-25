package com.merttoptas.retrofittutorial.ui.users.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.merttoptas.retrofittutorial.data.model.DataState
import com.merttoptas.retrofittutorial.data.model.Users
import com.merttoptas.retrofittutorial.data.model.UsersDTO
import com.merttoptas.retrofittutorial.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor(private val userRepository: UserRepository) : ViewModel() {
    private var _userLiveData = MutableLiveData<DataState<List<UsersDTO>?>>()
    val userLiveData: LiveData<DataState<List<UsersDTO>?>>
        get() = _userLiveData

    private val _eventStateLiveData = MutableLiveData<UserViewEvent>()
    val eventStateLiveData: LiveData<UserViewEvent>
        get() = _eventStateLiveData

    init {
        getUsers()
    }

    private fun getUsers() {
        _userLiveData.postValue(DataState.Loading())
        userRepository.getUsers().enqueue(object : Callback<List<Users>> {
            override fun onResponse(call: Call<List<Users>>, response: Response<List<Users>>) {
                if (response.isSuccessful) {
                    response.body()?.let {

                        _userLiveData.postValue(DataState.Success(it.map { safePost ->
                            UsersDTO(
                                address = safePost.address,
                                company = safePost.company,
                                email = safePost.email,
                                id = safePost.id,
                                name = safePost.name,
                                phone = safePost.phone,
                                username = safePost.username,
                                website = safePost.website
                            )
                        }))

                    } ?: kotlin.run {
                        _userLiveData.postValue(DataState.Error("Data Empty"))
                    }
                } else {
                    _userLiveData.postValue(DataState.Error(response.message()))
                }
            }

            override fun onFailure(call: Call<List<Users>>, t: Throwable) {
                _userLiveData.postValue(DataState.Error(t.message.toString()))
                _eventStateLiveData.postValue(UserViewEvent.ShowMessage(t.message.toString()))
            }
        })
    }

    /*fun onFavoritePost(post: PostDTO) {
        postRepository.getPostById(post.id ?: 0)?.let {
            postRepository.deleteFavoritePost(
                PostEntity(
                    postId = post.id.toString(),
                    postTitle = post.title,
                    postBody = post.body
                )
            )
        } ?: kotlin.run {
            postRepository.insertFavoritePost(
                PostEntity(
                    postId = post.id.toString(),
                    postTitle = post.title,
                    postBody = post.body
                )
            )
        }
    }*/

    /*private fun isExists(userId: Int?): Boolean {
        userId?.let {
            userRepository.getUserById(it)?.let {
                return true
            }
        }
        return false
    }*/
}

sealed class UserViewEvent {
    object NavigateToDetail : UserViewEvent()
    class ShowMessage(val message: String?) : UserViewEvent()
}