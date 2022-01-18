package com.ezzyapps.test.images.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ezzyapps.test.images.data.local.daos.HitsDao
import com.ezzyapps.test.images.data.local.models.HitDbo
import com.ezzyapps.test.images.data.local.models.QueryDbo
import com.ezzyapps.test.images.data.local.models.QueryHitRelDbo

@Database(
    version = 2,
    entities = [
        HitDbo::class,
        QueryDbo::class,
        QueryHitRelDbo::class
    ]
)
abstract class HitsDatabase : RoomDatabase() {

    abstract fun hitsDao(): HitsDao

}