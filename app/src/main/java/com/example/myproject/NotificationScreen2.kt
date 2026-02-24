package com.example.myproject

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.myproject.ui.theme.PurpleGrey40

@Composable
fun NotificationScreen2(){
    Column(
        modifier = Modifier.fillMaxSize().background(PurpleGrey40)
    ) {
        Text("Notification screen")
    }
}