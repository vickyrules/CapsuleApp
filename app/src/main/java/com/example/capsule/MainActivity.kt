package com.example.capsule

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import com.example.capsule.ui.theme.CapsuleTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CapsuleTheme {

                WindowCompat.setDecorFitsSystemWindows(window,false)
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize().windowInsetsPadding(WindowInsets.navigationBars),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CapsuleApp()
                }
            }
        }
    }
}

