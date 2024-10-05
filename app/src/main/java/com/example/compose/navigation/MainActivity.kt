package com.example.compose.navigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Surface
import androidx.navigation.compose.rememberNavController
import com.example.compose.navigation.navigation.AppDestination
import com.example.compose.navigation.navigation.AppNavHost
import com.example.compose.navigation.ui.theme.WayfinderTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WayfinderTheme {
                Surface {
                    AppNavHost(
                        navController = rememberNavController(),
                        startDestination = AppDestination.MovieList
                    )
                }
            }
        }
    }
}