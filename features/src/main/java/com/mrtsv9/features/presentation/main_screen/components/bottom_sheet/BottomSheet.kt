package com.mrtsv9.features.presentation.main_screen.components.bottom_sheet

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.HorizontalAlignmentLine
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.mrtsv9.features.R
import com.mrtsv9.features.presentation.main_screen.model.MainScreenAction
import com.mrtsv9.features.presentation.main_screen.model.MainScreenIntent
import com.mrtsv9.features.presentation.main_screen.model.TaskItem
import com.mrtsv9.organizr.domain.util.DateTimeUtil
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import pro.respawn.flowmvi.android.compose.ConsumerScope

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun ConsumerScope<MainScreenIntent, MainScreenAction>.EditBottomSheet(
    modifier: Modifier,
    sheetState: ModalBottomSheetState,
    scope: CoroutineScope,
) {
    var text by rememberSaveable {
        mutableStateOf("")
    }
    var isDialogShown by rememberSaveable {
        mutableStateOf(false)
    }
    var timePickerState by remember {
        mutableStateOf(TimePickerState(0, 0, true))
    }
    var selectedDuration: String? by rememberSaveable {
        mutableStateOf(null)
    }

    fun closeWithReset() {
        isDialogShown = false
        timePickerState = TimePickerState(0, 0, true)
        selectedDuration = null
        text = ""
    }

    LaunchedEffect(sheetState) {
        snapshotFlow { sheetState.isVisible }.collect { isVisible ->
            if(!isVisible) {
                closeWithReset()
            }
        }
    }
    if (isDialogShown) {
        TimePickerDialog(
            onCancel = { closeWithReset() },
            onConfirm = {
                selectedDuration = "${timePickerState.hour}" + ":${timePickerState.minute}"
                isDialogShown = false
            },
            title = stringResource(id = R.string.duration),
        ) {
            TimePicker(state = timePickerState)
        }
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
                            text = stringResource(id = R.string.enter_valid_title),
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
                    Button(
                        onClick = {
                            isDialogShown = true
                        }, modifier = Modifier
                            .width(150.dp)
                            .height(40.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Start
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_add),
                                contentDescription = "Select time",
                                modifier = Modifier.size(18.dp)
                            )
                            Spacer(modifier = Modifier.padding(end = 8.dp))
                            Text(
                                text = selectedDuration
                                    ?: stringResource(id = R.string.select_time),
                                style = MaterialTheme.typography.body1,
                            )
                        }
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
                    onClick = {
                        send(
                            MainScreenIntent.SaveTaskClicked(
                                TaskItem(
                                    0,
                                    text,
                                    (timePickerState.hour * 60 + timePickerState.minute).toLong(),
                                    DateTimeUtil.now()
                                )
                            )
                        )
                        closeWithReset()
                        scope.launch {
                            sheetState.hide()
                        }
                    },
                    modifier = Modifier
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
        },
        content = { })
}