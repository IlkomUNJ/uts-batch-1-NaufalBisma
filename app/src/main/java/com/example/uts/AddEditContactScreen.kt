package com.example.uts

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditContactScreen(
    existingContact: Contact? = null,
    onSave: (Contact) -> Unit,
    onCancel: () -> Unit,
    viewModel: AddEditContactViewModel = viewModel()
) {
    LaunchedEffect(existingContact) {
        viewModel.loadContact(existingContact)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(
                        if (existingContact == null) "Add Contact"
                            else "Edit Contact",
                        fontWeight = FontWeight.Bold
                )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF09C810),
                    titleContentColor = Color.White
                )
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp, end = 16.dp)
                .padding(padding),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            OutlinedTextField(
                value = viewModel.name.value,
                onValueChange = { viewModel.name.value = it },
                label = { Text("Name") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = viewModel.address.value,
                onValueChange = { viewModel.address.value = it },
                label = { Text("Address") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = viewModel.phone.value,
                onValueChange = { viewModel.phone.value = it },
                label = { Text("Phone") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = viewModel.email.value,
                onValueChange = { viewModel.email.value = it },
                label = { Text("Email") },
                modifier = Modifier.fillMaxWidth()
            )

            viewModel.errorMessage.value?.let { msg ->
                Text(msg, color = MaterialTheme.colorScheme.error)
            }

            Button(
                onClick = {
                    if (viewModel.validate()) {
                        onSave(viewModel.getContact())
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Save")
            }

            TextButton(onClick = onCancel, modifier = Modifier.fillMaxWidth()) {
                Text("Back")
            }
        }
    }
}

