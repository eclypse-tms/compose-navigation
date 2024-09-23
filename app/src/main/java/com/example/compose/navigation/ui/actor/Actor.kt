package com.example.compose.navigation.ui.actor

import com.example.compose.navigation.ui.detail.AddNewActorViewState

data class Actor(val firstName: String,
                 val lastName: String,
                 val dob: Int? = null) {

    fun displayName(): String {
        return if (dob != null) {
            "$firstName $lastName - $dob"
        } else {
            "$firstName $lastName"
        }
    }

    fun toAddNewActorViewState(): AddNewActorViewState {
        return AddNewActorViewState(firstName = firstName,
            lastName = lastName,
            dob = dob?.toString() ?: ""
        )
    }
}
