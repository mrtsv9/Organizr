@file:OptIn(ExperimentalMaterialApi::class)

package com.mrtsv9.features.presentation.main_screen

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.mrtsv9.features.R
import com.mrtsv9.features.presentation.main_screen.model.MainScreenAction
import com.mrtsv9.features.presentation.main_screen.model.MainScreenIntent
import com.mrtsv9.features.presentation.main_screen.model.MainScreenState
import com.mrtsv9.features.presentation.main_screen.model.TaskItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel
import pro.respawn.flowmvi.android.compose.ConsumerScope
import pro.respawn.flowmvi.android.compose.MVIComposable

@Composable
fun MainScreen() = MVIComposable(getViewModel<MainViewModel>()) { state ->

    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    val modalSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        confirmValueChange = { it != ModalBottomSheetValue.HalfExpanded },
        skipHalfExpanded = false
    )

    consume { action ->
        when (action) {
            is MainScreenAction.ShowSnackbar -> {
                Log.d("TAG", "AppScreen: ShowSnackbar")
            }

            is MainScreenAction.ShowEditScreen -> {
                scope.launch {
                    modalSheetState.show()
                }
            }
        }
    }

    Scaffold(modifier = Modifier.fillMaxSize(),
        scaffoldState = scaffoldState,
        topBar = { TopBar(modifier = Modifier.background(MaterialTheme.colors.primary)) },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { },
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
        EditBottomSheet(modifier = Modifier, sheetState = modalSheetState, scope = scope)
    }

    LaunchedEffect(key1 = state) {

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
        IconButton(onClick = {
            send(MainScreenIntent.EditTaskClicked)
        }) {
            Icon(
                imageVector = Icons.Filled.Add,
                contentDescription = stringResource(id = R.string.ic_add),
                tint = MaterialTheme.colors.secondary
            )
        }
    })
}

@Composable
fun ConsumerScope<MainScreenIntent, MainScreenAction>.EditBottomSheet(
    modifier: Modifier, sheetState: ModalBottomSheetState, scope: CoroutineScope
) {
    var text by rememberSaveable {
        mutableStateOf("")
    }

    ModalBottomSheetLayout(sheetState = sheetState,
        sheetShape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
        sheetBackgroundColor = MaterialTheme.colors.primary,
        sheetContent = {
            Column(
                modifier = modifier
                    .background(
                        shape = RoundedCornerShape(size = 18.dp),
                        color = MaterialTheme.colors.primary
                    )
                    .fillMaxWidth()
                    .height(420.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .width(48.dp)
                        .height(6.dp)
                        .background(
                            color = MaterialTheme.colors.secondary,
                            shape = RoundedCornerShape(18.dp)
                        )
                )
                OutlinedTextField(modifier = Modifier
                    .padding(top = 48.dp)
                    .fillMaxWidth(0.9f),
                    value = text,
                    onValueChange = { text = it },
                    label = {
                        Text(text = "Title", color = MaterialTheme.colors.background)
                    },
                    singleLine = true,
                    colors = TextFieldDefaults.colors(
                        focusedTextColor = MaterialTheme.colors.secondary,
                        unfocusedTextColor = MaterialTheme.colors.secondary,
                        focusedContainerColor = MaterialTheme.colors.primary,
                        unfocusedContainerColor = MaterialTheme.colors.primary,
                        unfocusedIndicatorColor = MaterialTheme.colors.background,
                    ),
                    supportingText = {
                        Text(
                            text = "Please enter a value up to 40 characters long",
                            style = MaterialTheme.typography.body2
                        )
                    })
                Row(
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .wrapContentHeight()
                        .padding(top = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Button(onClick = { /*TODO*/ }, modifier = Modifier.height(40.dp)) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_add),
                            contentDescription = "Select time",
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.padding(end = 8.dp))
                        Text(
                            text = "Select a time",
                            style = MaterialTheme.typography.body1,
                        )
                    }
                    IconButton(onClick = { }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_delete),
                            contentDescription = "Delete Icon",
                            tint = MaterialTheme.colors.background
                        )
                    }
                }
                IconButton(
                    onClick = { }, modifier = Modifier
                        .padding(top = 24.dp)
                        .width(56.dp)
                        .height(56.dp)
                        .background(
                            color = MaterialTheme.colors.onBackground,
                            shape = RoundedCornerShape(16.dp)
                        )
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_save),
                        contentDescription = "Save Button"
                    )
                }
            }
        }) { }
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

        else -> {}
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









