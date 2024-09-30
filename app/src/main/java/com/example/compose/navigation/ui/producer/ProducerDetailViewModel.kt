package com.example.compose.navigation.ui.producer

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.compose.navigation.di.ViewModelCoroutineContext
import com.example.compose.navigation.ui.detail.ProducerDetailViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@HiltViewModel
class ProducerDetailViewModel @Inject constructor(
    @ViewModelCoroutineContext private val couroutineContext: CoroutineContext,
    private val savedStateHandle: SavedStateHandle,
): ViewModel() {
    //region View State
    private val _producerDetailViewState: MutableStateFlow<ProducerDetailViewState> = MutableStateFlow(
        ProducerDetailViewState()
    )
    val producerDetailViewState: StateFlow<ProducerDetailViewState>
        get() = _producerDetailViewState
    //endregion

    init {
        val passedProducer = savedStateHandle.get<Producer?>(key = "producer")
        onReceive(Intent.InitialState(passedProducer))
    }

    fun onReceive(intent: Intent) = viewModelScope.launch(couroutineContext) {
        when (intent) {
            is Intent.InitialState -> {
                if (intent.producer != null) {
                    val initialState = ProducerDetailViewState(
                        firstName = intent.producer.firstName,
                        lastName = intent.producer.lastName,
                        isExecutive = intent.producer.isExecutive,
                    )
                    _producerDetailViewState.value = initialState
                }
            }
            is Intent.ChangeFirstName -> {
                val currentState = _producerDetailViewState.value
                val newState = currentState.copy(firstName = intent.firstName)
                _producerDetailViewState.value = newState
            }

            is Intent.ChangeLastName -> {
                val currentState = _producerDetailViewState.value
                val newState = currentState.copy(lastName = intent.lastName)
                _producerDetailViewState.value = newState
            }

            is Intent.ChangeExecutive -> {
                val currentState = _producerDetailViewState.value
                val newState = currentState.copy(isExecutive = intent.isExecutive)
                _producerDetailViewState.value = newState
            }

            is Intent.SaveProducer -> {
                // not implemented
            }
        }
    }
}