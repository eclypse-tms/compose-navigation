package com.example.compose.navigation.ui.producer

data class ProducerDetailViewState(
    val id: String = "",
    val firstName: String = "",
    val lastName: String = "",
    val isExecutive: Boolean = false
) {
    fun toProducer(): Producer {
        return Producer(
            id = id,
            firstName = firstName,
            lastName = lastName,
            isExecutive = isExecutive
        )
    }
}