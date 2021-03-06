package com.ezzyapps.test.images.data.local.daos

import androidx.room.*
import com.ezzyapps.test.images.data.local.models.HitDbo
import com.ezzyapps.test.images.data.local.models.QueryDbo
import com.ezzyapps.test.images.data.local.models.QueryHitRelDbo
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Observable

@Dao
interface HitsDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertQuery(query: QueryDbo): Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun QueryDbo.insert(): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertHits(hits: List<HitDbo>): List<Long>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertQueryHitRel(rels: List<QueryHitRelDbo>)

    @Transaction
    @Query("SELECT * FROM queries WHERE `query`=:query")
    fun getHitsForQuery(query: String): Observable<List<QueryHits>>

    @Query("SELECT * FROM hits WHERE hitId=:id")
    fun getHitForId(id: Long): Maybe<HitDbo>

}