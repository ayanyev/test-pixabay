package com.ezzyapps.test.repositories.data

import com.ezzyapps.test.repositories.data.local.HitsDatabase
import com.ezzyapps.test.repositories.data.local.models.QueryDbo
import com.ezzyapps.test.repositories.data.local.models.QueryHitRelDbo
import com.ezzyapps.test.repositories.data.mappers.toFullImage
import com.ezzyapps.test.repositories.data.mappers.toLocal
import com.ezzyapps.test.repositories.data.mappers.toPreview
import com.ezzyapps.test.repositories.data.remote.HitsClient
import com.ezzyapps.test.repositories.domain.PhotoRepository
import com.ezzyapps.test.repositories.domain.models.FullImage
import com.ezzyapps.test.repositories.domain.models.PreviewImage
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class PhotoRepositoryImpl @Inject constructor(

    private val db: HitsDatabase,
    private val client: HitsClient

) : PhotoRepository {

    override fun getPreviews(query: String): Observable<List<PreviewImage>> {
        return db.hitsDao().getHitsForQuery(query)
            .subscribeOn(Schedulers.io())
            .doOnNext { list ->
                if (list.isEmpty()) fetchRemoteHits(query)
            }
            .filter { it.isNotEmpty() }
            .map { it.first().hits.toPreview() }
    }

    override fun getPhotoDetails(id: Int): Maybe<FullImage> {
        return db.hitsDao().getHitForId(id).map { it.toFullImage() }
    }

    private fun fetchRemoteHits(
        query: String,
        page: Int = 1,
        perPage: Int = 25,
        type: String = "photo"
    ) {
        val response = client.getPublicRepositories(query, page, perPage, type).execute()
        if (response.isSuccessful) {
            if (response.body() != null && response.body()!!.hits.isNotEmpty()) {
                val hits = response.body()!!.hits
                db.runInTransaction {
                    with(db.hitsDao()) {
                        val qId = insertQuery(QueryDbo(query = query))
                        val hIds = insertHits(hits.toLocal())
                        val rels = hIds.map { hId -> QueryHitRelDbo(qId, hId) }
                        insertQueryHitRel(rels)
                    }
                }
            } else throw Exception("Received no repositories")
        } else throw Exception("Error while fetching repositories (code: ${response.code()})")
    }

}