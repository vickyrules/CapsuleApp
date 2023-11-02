package com.example.capsule.ui.components

import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.capsule.R
import com.example.capsule.ui.theme.CapsuleTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    title: String,
    NavUp: Boolean,
    onNavUp: () -> Unit,
    showTime: Boolean,
    duration: Long = 300L
) {
    TopAppBar(title = { Text(text = title) },
        navigationIcon = {
            IconButton(onClick = { onNavUp() }) {
                Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "back arrow")
            }
        },
        actions = {
            TimerBar(duration = duration)
        })

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimerBar(duration: Long) {
    AssistChip(
        onClick = { /*TODO*/ },
        label = {
            Text(modifier = Modifier.width(90.dp),
                text = stringResource(
                    id = R.string.timer_label,
                    duration / 60000,
                    duration % 60000 / 1000
                ),
                style = MaterialTheme.typography.labelMedium
            )
        },
        leadingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.ic_timer),
                contentDescription = "",
            )
        },
        colors = AssistChipDefaults.assistChipColors(
            labelColor = MaterialTheme.colorScheme.primary
        ),

        )
}

@Preview
@Composable
fun PreviewTimeBar() {
    CapsuleTheme {
        TimerBar(duration = 300)
    }
}

@Preview
@Composable
fun PreviewTopBar() {
    CapsuleTheme {
        TopBar(title = "Blood", NavUp = true, onNavUp = { /*TODO*/ }, showTime = true)
    }
}