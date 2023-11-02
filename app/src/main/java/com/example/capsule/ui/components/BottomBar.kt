package com.example.capsule.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.capsule.ui.theme.CapsuleTheme
import com.example.capsule.ui.theme.mediumPadding
import com.example.capsule.ui.theme.standardBorder
import com.example.capsule.ui.theme.standardShadow


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomBar(
    modifier: Modifier = Modifier,
    label:String ="",
    sublabel:String="",
    onNext : () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(mediumPadding)
    ) {
        Text(text = "Up Next :", style = MaterialTheme.typography.labelMedium)

       Card(
            shape = RoundedCornerShape(10.dp),
            border = standardBorder,
            onClick = { onNext() },
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary),
            modifier = Modifier
                .standardShadow(8.dp)
                .fillMaxWidth()

        ) {
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(mediumPadding),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically) {
                Column {
                    Text(text = label, style = MaterialTheme.typography.labelMedium.copy(fontSize = 20.sp))
                    Text(text = sublabel, style = MaterialTheme.typography.titleSmall)
                }
                Icon(imageVector = Icons.Filled.ArrowForwardIos, contentDescription = "")

            }
        }
    }

}



@Preview
@Composable
fun PreviewBottomBar() {
    CapsuleTheme {
        BottomBar(
            onNext = {}
        )
    }
}

