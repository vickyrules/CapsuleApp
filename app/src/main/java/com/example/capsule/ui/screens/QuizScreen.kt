package com.example.capsule.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material.icons.outlined.BookmarkBorder
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.capsule.R
import com.example.capsule.data.MCQ
import com.example.capsule.data.MCQQuiz
import com.example.capsule.ui.theme.CapsuleTheme
import com.example.capsule.ui.theme.Theme_Option_Container
import com.example.capsule.ui.theme.Theme_Option_Container_Outline
import com.example.capsule.ui.theme.Theme_Option_Container_Outline_disabled
import com.example.capsule.ui.theme.extraLargePadding
import com.example.capsule.ui.theme.extraSmallPadding
import com.example.capsule.ui.theme.largePadding
import com.example.capsule.ui.theme.mediumPadding
import com.example.capsule.ui.theme.smallPadding
import com.example.capsule.ui.theme.standardShadow
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun QuizScreen(quizList: List<MCQ> = MCQQuiz) {
    val pagerState = rememberPagerState(
        initialPage = 0,
        initialPageOffsetFraction = 0f
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(mediumPadding),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(mediumPadding, Alignment.Top)
    ) {
        ProgressBookMarkIndicatorRail(
            currentProgress = pagerState.currentPage + 1f,
            target = quizList.size.toFloat()
        )
        QuizContainer(pagerState = pagerState, quizList = quizList, Modifier.weight(0.8f))
        CheckAnswerBar(pagerState = pagerState, totalSize = quizList.size)
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun QuizContainer(pagerState: PagerState, quizList: List<MCQ>, modifier: Modifier) {
    HorizontalPager(
        modifier = modifier
            .fillMaxWidth(),
        state = pagerState,
        pageCount = quizList.size
    ) { page ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(mediumPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(largePadding, Alignment.Top)
        ) {
            QuestionCard(question = quizList[page].question, ind = page + 1)
            MCQVerticalColumn(quizList[page].options)
        }
    }
}


@Composable
fun QuestionCard(question: String, ind: Int) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                border = BorderStroke(
                    width = 2.dp,
                    color = MaterialTheme.colorScheme.onBackground,
                ),
                shape = MaterialTheme.shapes.small
            )
            .padding(mediumPadding),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Q$ind. $question",
            modifier = Modifier.fillMaxWidth(),
            overflow = TextOverflow.Clip,
            style = MaterialTheme.typography.titleMedium
        )
    }
}

@Composable
fun MCQVerticalColumn(optionsList: List<String> = listOf()) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(smallPadding, Alignment.CenterVertically)
    ) {
        val (selected, onSelected) = remember {
            mutableStateOf(-1)
        }
        optionsList.forEachIndexed { ind, option ->
            val isSelected = (ind == selected)
            OptionCard(
                ind,
                option = option,
                selected = isSelected,
                onSelected = onSelected
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OptionCard(ind: Int, option: String, selected: Boolean, onSelected: (Int) -> Unit) {

    Card(
        shape = RoundedCornerShape(10.dp),
        border = BorderStroke(
            if (selected) 2.dp else 0.dp,
            SolidColor(Theme_Option_Container_Outline)
        ),
        onClick = { onSelected(ind) },
        colors = CardDefaults.cardColors(
            containerColor = if (selected) Theme_Option_Container else Theme_Option_Container_Outline_disabled,
            contentColor = Color.Black,
        ),
        modifier = Modifier
            .standardShadow(8.dp, Theme_Option_Container_Outline)
            .fillMaxWidth()
            .height(60.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = mediumPadding)
    ) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(
                text = option,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.labelMedium
            )
        }
    }
}

@Composable
fun ProgressBookMarkIndicatorRail(currentProgress: Float, target: Float) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(smallPadding, Alignment.CenterHorizontally),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(Modifier.weight(.7f), contentAlignment = Alignment.Center) {
            LinearProgressIndicator(
                modifier = Modifier
                    .height(extraLargePadding)
                    .clip(MaterialTheme.shapes.small),
                progress = currentProgress / target,
                trackColor = MaterialTheme.colorScheme.secondary
            )
            Text(
                text = stringResource(
                    R.string.question_numering,
                    currentProgress.toInt(),
                    target.toInt()
                )
            )
        }
        Row(
            modifier = Modifier
                .wrapContentWidth()
                .height(extraLargePadding)
                .clip(MaterialTheme.shapes.small)
                .background(MaterialTheme.colorScheme.primary)
                .padding(extraSmallPadding),
            horizontalArrangement = Arrangement.spacedBy(
                smallPadding,
                Alignment.CenterHorizontally
            ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(imageVector = Icons.Outlined.BookmarkBorder, contentDescription = "Bookmark")
            Text(text = "|")
            Icon(imageVector = Icons.Outlined.Info, contentDescription = "info")
        }
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CheckAnswerBar(pagerState: PagerState, totalSize: Int) {
    val scope = rememberCoroutineScope()
    Row(
        horizontalArrangement = Arrangement.spacedBy(
            extraSmallPadding,
            Alignment.CenterHorizontally
        ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Button(
            shape = RoundedCornerShape(10.dp),
            border = BorderStroke(2.dp, SolidColor(Color.Gray)),
            onClick = {

            },
            colors = ButtonDefaults.buttonColors(containerColor = Color.LightGray),
            modifier = Modifier
                .weight(.8f)
        ) {
            Text(
                text = stringResource(R.string.check_answer),
                style = MaterialTheme.typography.labelLarge,
                color = Color.White
            )
        }
        if (pagerState.currentPage < totalSize - 1) {
            FilledIconButton(
                onClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(pagerState.currentPage + 1)
                    }

                },
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier.padding(smallPadding)
            ) {
                Icon(imageVector = Icons.Filled.ArrowForwardIos, contentDescription = "")
            }
        }
    }

}

@Preview
@Composable
fun PreviewOptionCard() {
    OptionCard(0, "This is a sample option", true, {})
}

@Preview
@Composable
fun PreviewQuestionCard() {
    CapsuleTheme {
        QuestionCard(question = "", ind = 0)
    }
}

@Preview
@Composable
fun PreviewMCQVerticalColumn() {
    MCQVerticalColumn()
}

@Preview
@Composable
fun PreviewQuizScreen() {
    CapsuleTheme {
        QuizScreen()
    }
}