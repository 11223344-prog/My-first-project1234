package com.example.myproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge

import androidx.compose.runtime.Composable

import androidx.compose.ui.tooling.preview.Preview


class DashboardActivity2 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DashboardActivity()
        }
    }
}


@Composable
fun DashboardActivity(){

}

@Preview
@Composable
fun DashboardActivityPreview(){
    DashboardActivity()
}
