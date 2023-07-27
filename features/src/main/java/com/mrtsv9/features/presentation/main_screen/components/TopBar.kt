package com.mrtsv9.features.presentation.main_screen.components

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.mrtsv9.features.R
import com.mrtsv9.features.presentation.main_screen.model.MainScreenAction
import com.mrtsv9.features.presentation.main_screen.model.MainScreenIntent
import pro.respawn.flowmvi.android.compose.ConsumerScope

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
            send(MainScreenIntent.AddOrEditTaskClicked(null))
        }) {
            Icon(
                imageVector = Icons.Filled.Add,
                contentDescription = stringResource(id = R.string.ic_add),
                tint = MaterialTheme.colors.secondary
            )
        }
    })
}