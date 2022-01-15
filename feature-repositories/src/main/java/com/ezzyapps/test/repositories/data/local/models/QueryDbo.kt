package com.ezzyapps.test.repositories.data.local.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "queries")
data class QueryDbo(

    @PrimaryKey(autoGenerate = true)
    val queryId: Long = 0,
    val query: String

)