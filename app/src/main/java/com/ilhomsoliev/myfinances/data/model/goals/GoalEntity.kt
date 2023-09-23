package com.ilhomsoliev.myfinances.data.model.goals

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "goal")
data class GoalEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val name: String,
    val targetAmount: Double,
    val comment: String,
    val imageUrl: String?,
    val dateCreated: Long,
    val isReminderActive: Boolean,
    val currencyId: Int,
    val deadline: Long,
    val state: String,
)