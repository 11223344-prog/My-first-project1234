package com.example.myproject

import android.R
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.myproject.model.InquiryModel
import java.util.UUID

class InquiryScreen : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            InquiryScreenUI()
        }
    }
}

@Composable
fun InquiryScreenUI() {

    var productId by remember { mutableStateOf("") }
    var userId by remember { mutableStateOf("") }
    var message by remember { mutableStateOf("") }

    var inquiryList by remember { mutableStateOf<List<InquiryModel>>(emptyList()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(Color.Yellow )
    ) {

        Text("Add Inquiry", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = productId,
            onValueChange = { productId = it },
            label = { Text("Product ID") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = userId,
            onValueChange = { userId = it },
            label = { Text("User ID") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = message,
            onValueChange = { message = it },
            label = { Text("Message") },
            modifier = Modifier.fillMaxWidth(),
            minLines = 2
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                val newInquiry = InquiryModel(
                    inquiryId = UUID.randomUUID().toString(),
                    productId = productId,
                    userId = userId,
                    message = message
                )
                inquiryList = inquiryList + newInquiry

                productId = ""
                userId = ""
                message = ""
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Add Inquiry")
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text("All Inquiries", style = MaterialTheme.typography.titleLarge)

        Spacer(modifier = Modifier.height(8.dp))

        LazyColumn {
            items(inquiryList) { inquiry ->
                InquiryItem(inquiry) {
                    inquiryList = inquiryList.filter { it.inquiryId != inquiry.inquiryId }
                }
            }
        }
    }
}

@Composable
fun InquiryItem(
    inquiry: InquiryModel,
    onDelete: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Product ID: ${inquiry.productId}")
            Text("User ID: ${inquiry.userId}")
            Text("Message: ${inquiry.message}")

            Spacer(modifier = Modifier.height(8.dp))

            Button(onClick = { onDelete() }) {
                Text("Delete")
            }
        }
    }
}

