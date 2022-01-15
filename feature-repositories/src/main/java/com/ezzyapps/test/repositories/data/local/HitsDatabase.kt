package com.ezzyapps.test.repositories.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ezzyapps.test.repositories.data.local.daos.HitsDao
import com.ezzyapps.test.repositories.data.local.models.HitDbo
import com.ezzyapps.test.repositories.data.local.models.QueryDbo
import com.ezzyapps.test.repositories.data.local.models.QueryHitRelDbo

@Database(
    version = 1,
    entities = [
        HitDbo::class,
        QueryDbo::class,
        QueryHitRelDbo::class
    ]
)
abstract class HitsDatabase : RoomDatabase() {

    abstract fun hitsDao(): HitsDao

}