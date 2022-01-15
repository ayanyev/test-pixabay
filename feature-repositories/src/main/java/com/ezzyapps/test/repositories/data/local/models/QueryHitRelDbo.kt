package com.ezzyapps.test.repositories.data.local.models

import androidx.room.Entity

@Entity(
    tableName = "hit_query_ref",
    primaryKeys = [
        "queryId", "hitId"
    ]
)
data class QueryHitRelDbo(
    val hitId: Long,
    val queryId: Long
)