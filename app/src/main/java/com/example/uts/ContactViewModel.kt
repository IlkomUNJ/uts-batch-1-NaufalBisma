package com.example.uts

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

class ContactViewModel : ViewModel() {

    val contacts = mutableStateListOf<Contact>()

    fun addContact(contact: Contact) {
        contacts.add(contact)
    }

    fun updateContact(index: Int, updated: Contact) {
        if (index in contacts.indices) {
            contacts[index] = updated
        }
    }

    fun getContact(index: Int): Contact? {
        return contacts.getOrNull(index)
    }
}
