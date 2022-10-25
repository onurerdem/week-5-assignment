package com.merttoptas.retrofittutorial.data.repository

import com.merttoptas.retrofittutorial.data.local.database.entity.UserEntity
import com.merttoptas.retrofittutorial.data.model.Users
import retrofit2.Call

interface UserRepository {
    fun getUsers(): Call<List<Users>>
    //fun getUserById(id: Int): UserEntity?
}