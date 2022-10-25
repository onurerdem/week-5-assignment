package com.merttoptas.retrofittutorial.data.repository

import com.merttoptas.retrofittutorial.data.local.database.PostsDatabase
import com.merttoptas.retrofittutorial.data.local.database.entity.PostEntity
import com.merttoptas.retrofittutorial.data.remote.api.PostApiService
import com.merttoptas.retrofittutorial.data.model.Post
import retrofit2.Call

/**
 * Created by merttoptas on 16.10.2022.
 */

class PostRepositoryImpl constructor(
    private val postApiService: PostApiService,
    private val postsDatabase: PostsDatabase
) : PostRepository {
    override fun getPosts(): Call<List<Post>> {
        return postApiService.getPosts()
    }

    override fun getPostById(id: Int): PostEntity? {
        return postsDatabase.postDao().getPostById(id.toString())
    }

    override fun insertFavoritePost(post: PostEntity) {
        return postsDatabase.postDao().insert(post)
    }

    override fun deleteFavoritePost(id: String) {
        return postsDatabase.postDao().deleteFavoriteById(id)
    }

    override fun getFavorites(): List<PostEntity> {
        return postsDatabase.postDao().getAllPosts()
    }
}