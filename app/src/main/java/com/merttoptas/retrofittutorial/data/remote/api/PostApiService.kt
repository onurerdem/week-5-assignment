package com.merttoptas.retrofittutorial.data.remote.api

import com.merttoptas.retrofittutorial.data.model.Post
import com.merttoptas.retrofittutorial.data.model.Users
import retrofit2.Call
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by merttoptas on 8.10.2022.
 */

interface PostApiService {
    @GET("posts")
    fun getPosts(): Call<List<Post>>

    /*@GET("users")
    fun getUsers(): Call<List<Users>>*/

    @DELETE("posts/{id}")
    fun deletePost(@Path("{id}") id: String): Call<Post>
}
