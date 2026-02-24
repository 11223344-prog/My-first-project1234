package com.example.myproject

import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.example.myproject.model.InquiryModel
import com.example.myproject.viewmodel.InquiryViewModel
import com.example.myproject.viewmodel.ViewModelFactory
import java.util.UUID

@Composable
fun InquiryScreen(inquiryViewModel: InquiryViewModel = viewModel(factory = ViewModelFactory())) {

    val context = LocalContext.current

    val inquiries by inquiryViewModel.inquiries.observeAsState(emptyList())
    val message by inquiryViewModel.message.observeAsState()
    val isLoading by inquiryViewModel.loading.observeAsState(false)

    var productId by remember { mutableStateOf("") }
    var userId by remember { mutableStateOf("") }
    var inquiryMessage by remember { mutableStateOf("") }
    var imageUri by remember { mutableStateOf<Uri?>(null) }

    val imagePicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri ->
            imageUri = uri
        }
    )

    LaunchedEffect(Unit) {
        inquiryViewModel.getAllInquiries()
    }

    LaunchedEffect(message) {
        message?.let {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
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
                value = inquiryMessage,
                onValueChange = { inquiryMessage = it },
                label = { Text("Message") },
                modifier = Modifier.fillMaxWidth(),
                minLines = 2
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = { imagePicker.launch("image/*") }) {
                Text("Add Image")
            }

            imageUri?.let {
                Image(
                    painter = rememberAsyncImagePainter(it),
                    contentDescription = "Selected Image",
                    modifier = Modifier
                        .size(100.dp)
                        .padding(top = 8.dp)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    val inquiry = InquiryModel(
                        inquiryId = UUID.randomUUID().toString(),
                        productId = productId,
                        userId = userId,
                        message = inquiryMessage
                    )
                    inquiryViewModel.addInquiry(context, inquiry, imageUri)
                },
                modifier = Modifier.fillMaxWidth(),
                enabled = !isLoading
            ) {
                Text("Submit Inquiry")
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text("All Inquiries", style = MaterialTheme.typography.titleLarge)

            Spacer(modifier = Modifier.height(8.dp))

            LazyColumn {
                items(inquiries) { inquiry ->
                    InquiryItem(inquiry) {
                        inquiryViewModel.deleteInquiry(inquiry.inquiryId)
                    }
                }
            }
        }

        if (isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
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

            if (inquiry.inquiryImage.isNotEmpty()) {
                Image(
                    painter = rememberAsyncImagePainter(inquiry.inquiryImage),
                    contentDescription = "Inquiry Image",
                    modifier = Modifier
                        .size(100.dp)
                        .padding(top = 8.dp)
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Button(onClick = onDelete) {
                Text("Delete")
            }
        }
    }
}