package com.ezzyapps.test.images.data.local.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "queries")
data class QueryDbo(

    @PrimaryKey
    val query: String

)