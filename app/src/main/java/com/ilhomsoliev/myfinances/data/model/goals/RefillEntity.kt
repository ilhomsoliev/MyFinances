package com.ilhomsoliev.myfinances.data.model.goals

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "refill")
data class RefillEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val goalId: Int,
    val deadline: Long,
    val currencyId: Int,
    val amount: Double,
    val type: String,
)