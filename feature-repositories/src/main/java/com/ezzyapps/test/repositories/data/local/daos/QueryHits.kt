package com.ezzyapps.test.repositories.data.local.daos

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.ezzyapps.test.repositories.data.local.models.HitDbo
import com.ezzyapps.test.repositories.data.local.models.QueryDbo
import com.ezzyapps.test.repositories.data.local.models.QueryHitRelDbo

data class QueryHits(
    @Embedded
    val query: QueryDbo,
    @Relation(
        parentColumn = "queryId",
        entityColumn = "hitId",
        associateBy = Junction(QueryHitRelDbo::class)
    )
    val hits: List<HitDbo>
)