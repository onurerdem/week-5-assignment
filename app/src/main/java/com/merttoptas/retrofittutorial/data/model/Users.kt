package com.merttoptas.retrofittutorial.data.model


import com.google.gson.annotations.SerializedName

data class Users(
    @SerializedName("address")
    val address: Address?,
    @SerializedName("company")
    val company: Company?,
    @SerializedName("email")
    val email: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("phone")
    val phone: String?,
    @SerializedName("username")
    val username: String?,
    @SerializedName("website")
    val website: String?
)

data class UsersDTO(
    val address: Address?,
    val company: Company?,
    val email: String?,
    val id: Int?,
    val name: String?,
    val phone: String?,
    val username: String?,
    val website: String?
)