package com.ezzyapps.test.images.data.local.daos

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.ezzyapps.test.images.data.local.models.HitDbo
import com.ezzyapps.test.images.data.local.models.QueryDbo
import com.ezzyapps.test.images.data.local.models.QueryHitRelDbo

data class QueryHits(
    @Embedded
    val query: QueryDbo,
    @Relation(
        parentColumn = "query",
        entityColumn = "hitId",
        associateBy = Junction(QueryHitRelDbo::class)
    )
    val hits: List<HitDbo>
)