package com.merttoptas.retrofittutorial.data.repository

import com.merttoptas.retrofittutorial.data.local.database.entity.PostEntity
import com.merttoptas.retrofittutorial.data.model.Post
import retrofit2.Call

/**
 * Created by merttoptas on 16.10.2022.
 */

interface PostRepository {
    fun getPosts(): Call<List<Post>>
    fun getPostById(id: Int): PostEntity?
    fun insertFavoritePost(post: PostEntity)
    fun deleteFavoritePost(id: String)
    fun getFavorites(): List<PostEntity>
}