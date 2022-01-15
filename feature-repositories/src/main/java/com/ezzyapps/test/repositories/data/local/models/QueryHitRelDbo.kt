package com.ezzyapps.test.repositories.data.local.models

import androidx.room.Entity

@Entity(
    tableName = "hit_query_ref",
    primaryKeys = [
        "query", "hitId"
    ]
)
data class QueryHitRelDbo(
    val query: String,
    val hitId: Long
)