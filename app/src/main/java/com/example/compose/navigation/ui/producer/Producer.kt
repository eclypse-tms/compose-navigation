package com.example.compose.navigation.ui.producer

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable
import java.util.UUID

@Parcelize
@Serializable
data class Producer(val firstName: String,
                    val lastName: String,
                    val id: String = UUID.randomUUID().toString(),
                    val isExecutive: Boolean = false): Parcelable {
    fun displayName(): String {
        return "$firstName $lastName"
    }
}