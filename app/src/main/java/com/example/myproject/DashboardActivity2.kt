package com.example.myproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddShoppingCart
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
                    icon = { Icon(Icons.Filled.ShoppingCart, contentDescription = "Products") },
                    label = { Text("Products") }
                )

                NavigationBarItem(
                    selected = selectedIndex == 1,
                    onClick = { selectedIndex = 1 },
                    icon = { Icon(Icons.Filled.AddShoppingCart, contentDescription = "Add Product") },
                    label = { Text("Add Product") }
                )

                NavigationBarItem(
                    selected = selectedIndex == 2,
                    onClick = { selectedIndex = 2 },
                    icon = { Icon(Icons.Filled.Search, contentDescription = "Search") },
                    label = { Text("Search") }
                )

                NavigationBarItem(
                    selected = selectedIndex == 3,
                    onClick = { selectedIndex = 3 },
                    icon = {
                        Icon(
                            Icons.Filled.Notifications,
                            contentDescription = "Notification"
                        )
                    },
                    label = { Text("Notify") }
                )

                NavigationBarItem(
                    selected = selectedIndex == 4,
                    onClick = { selectedIndex = 4 },
                    icon = { Icon(Icons.Filled.Person, contentDescription = "Profile") },
                    label = { Text("Profile") }
                )

                NavigationBarItem(
                    selected = selectedIndex == 5,
                    onClick = { selectedIndex = 5 },
                    icon = { Icon(imageVector = Icons.Filled.Add, contentDescription = "Inquiry") },
                    label = { Text("Inquiry") }
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
                0 -> HomeScreen()
                1 -> AddProductScreen()
                2 -> SearchScreen()
                3 -> NotificationScreen2()
                4 -> ProfileScreen()
                5 -> InquiryScreen()
                else -> HomeScreen()
            }
        }
    }
}
