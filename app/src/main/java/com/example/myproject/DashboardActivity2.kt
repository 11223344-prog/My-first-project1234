package com.example.myproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier

class DashboardActivity2 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            DashboardMainUI()
        }
    }
}

@Composable
fun DashboardMainUI() {

    var selectedIndex by remember { mutableStateOf(0) }

    Scaffold(
        bottomBar = {
            NavigationBar {

                NavigationBarItem(
                    selected = selectedIndex == 0,
                    onClick = { selectedIndex = 0 },
                    icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
                    label = { Text("Home") }
                )

                NavigationBarItem(
                    selected = selectedIndex == 1,
                    onClick = { selectedIndex = 1 },
                    icon = { Icon(Icons.Default.Search, contentDescription = "Search") },
                    label = { Text("Search") }
                )

                NavigationBarItem(
                    selected = selectedIndex == 2,
                    onClick = { selectedIndex = 2 },
                    icon = {
                        Icon(
                            Icons.Default.Notifications,
                            contentDescription = "Notification"
                        )
                    },
                    label = { Text("Notify") }
                )

                NavigationBarItem(
                    selected = selectedIndex == 3,
                    onClick = { selectedIndex = 3 },
                    icon = { Icon(Icons.Default.Person, contentDescription = "Profile") },
                    label = { Text("Profile") }
                )

                NavigationBarItem(
                    selected = selectedIndex==4,
                    onClick = {selectedIndex=4},
                    icon = {Icon(imageVector = Icons.Default.Add,contentDescription = "Inquiry")}
                )
            }
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {

            when (selectedIndex) {
                0 -> HomeScreen2()
                1 -> SearchScreen()
                2 -> NotificationScreen2()
                3 -> ProfileScreen()
                4 -> InquiryScreen()
                else -> HomeScreen2()
            }
        }
    }
}