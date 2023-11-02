package com.example.capsule.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.capsule.R
import com.example.capsule.ui.theme.extraLargePadding
import com.example.capsule.ui.theme.largePadding
import com.example.capsule.ui.theme.mediumPadding
import com.example.capsule.ui.theme.smallPadding
import com.example.capsule.ui.theme.standardBorder


@Composable
fun NotesScreen(notesList: List<String>) {
    LazyColumn(
        Modifier
            .fillMaxSize()
            .offset(y = mediumPadding),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(smallPadding, Alignment.Top)
    ) {
        items(notesList) { note ->
            OutlinedCard(
                modifier = Modifier
                    .fillMaxWidth(),
                shape = MaterialTheme.shapes.medium
            ) {
                Text(
                    modifier = Modifier.padding(largePadding),
                    text = note,
                    style = MaterialTheme.typography.labelMedium,
                )
            }
        }
    }
}