package com.mrtsv9.features.presentation.main_screen.model

import androidx.compose.runtime.Immutable
import kotlinx.datetime.LocalDateTime

@Immutable
data class TaskItem(
    val id: Long,
    val title: String,
    val timeLength: Long,
    val createdAt: LocalDateTime
)