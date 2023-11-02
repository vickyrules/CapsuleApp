package com.example.capsule

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.capsule.data.FAKE_NOTES
import com.example.capsule.data.MCQQuiz
import com.example.capsule.ui.components.BottomBar
import com.example.capsule.ui.components.TopBar
import com.example.capsule.ui.screens.LinesScreen
import com.example.capsule.ui.screens.MemesScreen
import com.example.capsule.ui.screens.NotesScreen
import com.example.capsule.ui.screens.QuizScreen
import com.example.capsule.ui.screens.VideoScreen
import com.example.capsule.ui.theme.CapsuleTheme
import com.example.capsule.ui.theme.extraSmallPadding
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun CapsuleApp() {
    val coroutineScope = rememberCoroutineScope()
    val pagerState = rememberPagerState(
        initialPage = 0,
        initialPageOffsetFraction = 0f
    )

    val remainingTime = remember { mutableStateOf(600000L) }

    LaunchedEffect(key1 = remainingTime) {
        coroutineScope.launch {
            while (remainingTime.value > 0) {
                remainingTime.value -= 1000
                kotlinx.coroutines.delay(1000)
            }
        }
    }

    Scaffold(
        topBar = {
            TopBar(
                title = "Blood",
                NavUp = true,
                onNavUp = { /*TODO*/ },
                showTime = true,
                duration = remainingTime.value
            )
        },
        bottomBar = {
            if (pagerState.currentPage < topTabs.size - 2) {
                BottomBar(
                    label = topTabs[pagerState.currentPage + 1].first,
                    sublabel = topTabs[pagerState.currentPage + 1].second,
                    onNext = { coroutineScope.launch { pagerState.animateScrollToPage(pagerState.currentPage + 1) } }
                )
            }
        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(paddingValues = innerPadding)) {
            //TopNavBAr
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                topTabs.forEachIndexed { ind, item ->
                    NavItem(label = item.first, isEnabled = pagerState.currentPage >= ind)
                }
            }

            HorizontalPager(
                modifier = Modifier
                    .fillMaxSize(),
                state = pagerState,
                pageCount = topTabs.size
            ) { page ->
                when (page) {
                    0 -> VideoScreen()
                    1 -> NotesScreen(notesList = FAKE_NOTES)
                    2 -> QuizScreen(quizList = MCQQuiz)
                    3 -> LinesScreen()
                    else -> MemesScreen()
                }
            }
        }
    }
}


@Composable
fun NavItem(label: String, isEnabled: Boolean) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = label)
        Divider(
            color = if (isEnabled) MaterialTheme.colorScheme.primary else Color.Gray.copy(alpha = .5f),
            modifier = Modifier
                .width((label.length * 12).dp)
                .clip(MaterialTheme.shapes.small),
            thickness = extraSmallPadding
        )
    }
}

@Preview
@Composable
fun PreviewNavItem() {
    CapsuleTheme {
        NavItem(label = "Video", isEnabled = true)
    }
}

@Preview
@Composable
fun PreviewCapsuleApp() {
    CapsuleTheme(darkTheme = true) {
        CapsuleApp()
    }
}

private val topTabs = listOf(
    Pair("Video", ""),
    Pair("Notes", "saved notes : ${FAKE_NOTES.size}"),
    Pair("Quiz", "Questions: ${MCQQuiz.size}"),
    Pair("Lines", "Read from NCERT Book"),
    Pair("Memes", "Refresh your mind"),
)