package com.example.uts

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class AddEditContactViewModel : ViewModel() {

    var name = mutableStateOf("")
    var address = mutableStateOf("")
    var phone = mutableStateOf("")
    var email = mutableStateOf("")
    var errorMessage = mutableStateOf<String?>(null)

    fun loadContact(contact: Contact?) {
        contact?.let {
            name.value = it.name
            address.value = it.address
            phone.value = it.phone
            email.value = it.email
        }
    }

    fun validate(): Boolean {
        return if (address.value.split(" ").size < 5) {
            errorMessage.value = "Address must contain at least 5 words"
            false
        } else {
            errorMessage.value = null
            true
        }
    }

    fun getContact(): Contact {
        return Contact(name.value, address.value, phone.value, email.value)
    }
}
