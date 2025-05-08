package com.abdulkadirekrem.userapp.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abdulkadirekrem.userapp.model.User
import com.abdulkadirekrem.userapp.service.UserAPI
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class UserViewModel:ViewModel() {
    private val base_Url="https://jsonplaceholder.typicode.com"

    val retrofit= Retrofit.Builder()

        .baseUrl(base_Url)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(UserAPI::class.java)

    val userLists= mutableStateOf<List<User>>(listOf())
    fun getUsers() {
        viewModelScope.launch {
            try {
                val data = retrofit.getData()
                Log.d("UserVM", "API yanıtı: ${data.size} kullanıcı")
                userLists.value = data
            } catch (e: Exception) {
                Log.e("UserVM", "API hatası", e)
            }
        }
    }



}