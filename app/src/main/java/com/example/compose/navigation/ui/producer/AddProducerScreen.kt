package com.example.compose.navigation.ui.producer

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.SavedStateHandle
import com.example.compose.navigation.ui.theme.WayfinderTheme
import kotlinx.coroutines.Dispatchers

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddProducerScreen(viewModel: ProducerDetailViewModel,
                      onSaveProducer: (Producer) -> Unit,
                      onDismissScreen: () -> Unit) {

    val currentProducerDetail by viewModel.producerDetailViewState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(text = "Producer Information")
            }, navigationIcon = {
                    IconButton(onClick = onDismissScreen) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(modifier = Modifier
            .padding(innerPadding)
            .padding(start = 16.dp, end = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally) {
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                placeholder = {
                    Text(text = "First Name")
                },
                value = currentProducerDetail.firstName,
                onValueChange = {
                    viewModel.onReceive(Intent.ChangeFirstName(it))
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text
                )
            )

            TextField(modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
                placeholder = {
                    Text(text = "Last Name")
                },
                value = currentProducerDetail.lastName,
                onValueChange = {
                    viewModel.onReceive(Intent.ChangeLastName(it))
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text
                )
            )

            Row(modifier = Modifier.padding(bottom = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    "Executive Producer"
                )
                Checkbox(
                    checked = currentProducerDetail.isExecutive,
                    onCheckedChange = {
                        viewModel.onReceive(Intent.ChangeExecutive(it))
                    }
                )
            }

            Button(modifier = Modifier.widthIn(min = 150.dp),
                onClick = {
                    onSaveProducer(currentProducerDetail.toProducer())
            }) {
                Text(text = "Save")
            }

        }
    }
}

@Preview
@Composable
fun PreviewAddActorScreen() {
    val previewViewModel = ProducerDetailViewModel(
        couroutineContext = Dispatchers.Main,
        savedStateHandle = SavedStateHandle()
    )
    WayfinderTheme {
        AddProducerScreen(viewModel = previewViewModel,
            onDismissScreen = {},
            onSaveProducer = {})
    }
}