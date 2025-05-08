@file:OptIn(androidx.compose.material3.ExperimentalMaterial3Api::class)
package com.abdulkadirekrem.userapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.abdulkadirekrem.userapp.screens.UserList
import com.abdulkadirekrem.userapp.ui.theme.UserAppTheme
import com.abdulkadirekrem.userapp.viewmodel.UserViewModel

class MainActivity : ComponentActivity() {
    private val viewModel by viewModels<UserViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UserAppTheme {
                LaunchedEffect(Unit) {
                    viewModel.getUsers()
                }

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        CenterAlignedTopAppBar(
                            title = { Text("API Users Spotlight", color = Color.Gray) }
                        )
                    }
                ) { innerPadding ->
                    val users = viewModel.userLists.value

                    Box(modifier = Modifier.padding(innerPadding)) {
                        if (users.isEmpty()) {
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {
                                CircularProgressIndicator()
                            }
                        } else {
                            UserList(userlist = users)
                        }
                    }
                }
            }
        }
    }
}