package com.merttoptas.retrofittutorial.data.remote.api

import com.merttoptas.retrofittutorial.data.model.Users
import retrofit2.Call
import retrofit2.http.GET

interface UserApiService {
    @GET("users")
    fun getUsers(): Call<List<Users>>
}