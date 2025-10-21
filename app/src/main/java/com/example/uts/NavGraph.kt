package com.example.uts

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument


@Composable
fun NavApp() {
    val navController = rememberNavController()
    val viewModel: ContactViewModel = viewModel()

    NavHost(navController = navController, startDestination = "list") {
        composable("list") {
            ContactListScreen(
                contacts = viewModel.contacts,
                onAddClick = { navController.navigate("add") },
                onEditClick = { index -> navController.navigate("edit/$index") }
            )
        }

        composable("add") {
            AddEditContactScreen(
                onSave = { contact ->
                    if (contact.address.split(" ").size >= 5) {
                        viewModel.addContact(contact)
                        navController.popBackStack()
                    }
                },
                onCancel = { navController.popBackStack() }
            )
        }

        composable(
            route = "edit/{index}",
            arguments = listOf(navArgument("index") { type = NavType.IntType })
        ) { backStackEntry ->
            val index = backStackEntry.arguments?.getInt("index") ?: 0
            val existingContact = viewModel.getContact(index)

            AddEditContactScreen(
                existingContact = existingContact,
                onSave = { updated ->
                    if (updated.address.split(" ").size >= 5) {
                        viewModel.updateContact(index, updated)
                        navController.popBackStack()
                    }
                },
                onCancel = { navController.popBackStack() }
            )
        }
    }
}
