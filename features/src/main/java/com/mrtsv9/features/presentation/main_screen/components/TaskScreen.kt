package com.mrtsv9.features.presentation.main_screen.components

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.mrtsv9.features.R
import com.mrtsv9.features.presentation.main_screen.MainViewModel
import com.mrtsv9.features.presentation.main_screen.model.MainScreenAction
import com.mrtsv9.features.presentation.main_screen.model.MainScreenIntent
import com.mrtsv9.features.presentation.main_screen.model.MainScreenState
import com.mrtsv9.features.presentation.main_screen.model.TaskItem
import pro.respawn.flowmvi.android.compose.ConsumerScope


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ConsumerScope<MainScreenIntent, MainScreenAction>.TaskScreen(
    state: MainScreenState,
    modifier: Modifier = Modifier
) {
    when (state) {
        is MainScreenState.Loading -> {
            Log.d("TAG", "AppScreen: Loading")
        }

        is MainScreenState.DisplayingTasks -> {
            LazyColumn(
                modifier = modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colors.primary),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                stickyHeader {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 18.dp),
                        textAlign = TextAlign.Start,
                        text = stringResource(id = R.string.tasks_for_the_day),
                        style = MaterialTheme.typography.h1,
                        color = MaterialTheme.colors.onBackground,
                    )
                }
                items(state.tasks) {
                    TaskItemScreen(modifier = Modifier, item = it)
                }
            }
        }
    }
}

@Composable
fun TaskItemScreen(
    modifier: Modifier,
    item: TaskItem
) {
    val boxColor = if (item.timeLength < 60) {
        MaterialTheme.colors.background
    } else if (item.timeLength in 60..320) {
        MaterialTheme.colors.onPrimary
    } else {
        MaterialTheme.colors.error
    }
    val boxHeight = (item.timeLength / 480f * 180).coerceAtLeast(48f).coerceAtMost(180f)

    Box(
        modifier = modifier
            .padding(vertical = 6.dp)
            .fillMaxWidth(0.95f)
            .height(boxHeight.dp)
            .background(color = boxColor, shape = MaterialTheme.shapes.large)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = item.title,
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.h2,
                fontWeight = FontWeight(500),
            )
            Text(
                text = "${item.timeLength / 60}:${item.timeLength.rem(60)}",
                textAlign = TextAlign.End,
                style = MaterialTheme.typography.body2,
                maxLines = 1
            )
        }
    }
}
