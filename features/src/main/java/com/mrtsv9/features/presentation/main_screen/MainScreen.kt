@file:OptIn(ExperimentalMaterialApi::class)

package com.mrtsv9.features.presentation.main_screen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.mrtsv9.features.R
import com.mrtsv9.features.presentation.main_screen.components.TaskScreen
import com.mrtsv9.features.presentation.main_screen.components.TopBar
import com.mrtsv9.features.presentation.main_screen.components.bottom_sheet.EditBottomSheet
import com.mrtsv9.features.presentation.main_screen.model.MainScreenAction
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel
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









