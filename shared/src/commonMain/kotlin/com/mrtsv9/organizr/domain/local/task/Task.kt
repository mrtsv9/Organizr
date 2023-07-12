package com.mrtsv9.organizr.domain.local.task

import kotlinx.datetime.LocalDateTime

data class Task(
    val id: Long,
    val title: String,
    val timeLength: Long,
    val createdAt: LocalDateTime
)