package com.abdulkadirekrem.userapp.service

import com.abdulkadirekrem.userapp.model.User
import retrofit2.http.GET

interface UserAPI {
    //https://jsonplaceholder.typicode.com/users
    @GET("/users")
    suspend fun getData():List<User>
}