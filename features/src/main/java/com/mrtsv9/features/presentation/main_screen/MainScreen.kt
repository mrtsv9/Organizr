package com.mrtsv9.features.presentation.main_screen

import android.graphics.drawable.shapes.Shape
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mrtsv9.features.R
import com.mrtsv9.features.presentation.main_screen.model.MainScreenAction
import com.mrtsv9.features.presentation.main_screen.model.MainScreenIntent
import com.mrtsv9.features.presentation.main_screen.model.MainScreenState
import com.mrtsv9.features.presentation.main_screen.model.TaskItem
import org.koin.androidx.compose.getViewModel
import pro.respawn.flowmvi.android.compose.ConsumerScope
import pro.respawn.flowmvi.android.compose.MVIComposable

@Composable
fun MainScreen() = MVIComposable(getViewModel<MainScreenViewModel>()) { state ->

    val scaffoldState = rememberScaffoldState()

    consume { action ->
        when (action) {
            is MainScreenAction.ShowSnackbar -> {
                Log.d("TAG", "AppScreen: ShowSnackbar")
            }
        }
    }

    Scaffold(modifier = Modifier.fillMaxSize(),
        scaffoldState = scaffoldState,
        topBar = { TopBar(modifier = Modifier.background(MaterialTheme.colors.primary)) },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { send(MainScreenIntent.FabClicked) },
                shape = MaterialTheme.shapes.large,
                backgroundColor = MaterialTheme.colors.onBackground,
                contentColor = MaterialTheme.colors.secondary
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_pen),
                    contentDescription = stringResource(id = R.string.ic_pen)
                )
            }
        }) {
        TaskScreen(state, Modifier.padding(it))
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConsumerScope<MainScreenIntent, MainScreenAction>.TopBar(
    modifier: Modifier
) {
    CenterAlignedTopAppBar(colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
        containerColor = MaterialTheme.colors.primary,
        actionIconContentColor = MaterialTheme.colors.secondary
    ), modifier = modifier, title = {
        Text(
            text = stringResource(id = R.string.app_name), style = MaterialTheme.typography.h1
        )
    }, actions = {
        IconButton(onClick = { send(MainScreenIntent.AddTaskClicked) }) {
            Icon(
                imageVector = Icons.Filled.Add,
                contentDescription = stringResource(id = R.string.ic_add),
                tint = MaterialTheme.colors.secondary
            )
        }
    })
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ConsumerScope<MainScreenIntent, MainScreenAction>.TaskScreen(
    state: MainScreenState, modifier: Modifier = Modifier
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
    modifier: Modifier, item: TaskItem
) {

    Box(
        modifier = modifier
            .padding(top = 12.dp)
            .fillMaxWidth(0.95f)
            .height(80.dp)
            .background(color = MaterialTheme.colors.error, shape = MaterialTheme.shapes.large)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = item.title, textAlign = TextAlign.Start, style = MaterialTheme.typography.h2,
            )
            Text(
                text = item.timeLength.toString(),
                textAlign = TextAlign.End,
                style = MaterialTheme.typography.body2,
                maxLines = 1
            )
        }
    }
}









