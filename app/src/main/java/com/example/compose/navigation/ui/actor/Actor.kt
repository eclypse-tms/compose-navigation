package com.example.compose.navigation.ui.actor

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable
import java.util.UUID

@Parcelize
@Serializable
data class Actor(val firstName: String,
                 val lastName: String,
                 val id: String = UUID.randomUUID().toString(),
                 val dob: Int? = null): Parcelable {

    fun displayName(): String {
        return if (dob != null) {
            "$firstName $lastName - $dob"
        } else {
            "$firstName $lastName"
        }
    }

    fun toViewState(): ActorDetailViewState {
        return ActorDetailViewState(
            id = id,
            firstName = firstName,
            lastName = lastName,
            dob = dob?.toString() ?: ""
        )
    }
}
