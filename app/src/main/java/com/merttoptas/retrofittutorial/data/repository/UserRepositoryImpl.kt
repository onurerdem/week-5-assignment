package com.merttoptas.retrofittutorial.data.repository

import com.merttoptas.retrofittutorial.data.local.database.UsersDatabase
import com.merttoptas.retrofittutorial.data.model.Users
import com.merttoptas.retrofittutorial.data.remote.api.UserApiService
import retrofit2.Call

class UserRepositoryImpl constructor(
    private val userApiService: UserApiService,
    private val usersDatabase: UsersDatabase
) : UserRepository {
    override fun getUsers(): Call<List<Users>> {
        return userApiService.getUsers()
    }

    /*override fun getUserById(id: Int): UserEntity? {
        return usersDatabase.userDao().getUserById(id.toString())
    }*/
}