package com.ezzyapps.test.repositories.data.local.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "hits")
data class HitDbo(
    @PrimaryKey
    val hitId: Long,
    @ColumnInfo(name = "user_name")
    val userName: String,
    @ColumnInfo(name = "full_url")
    val urlFull: String,
    @ColumnInfo(name = "full_width")
    val widthFull: Int,
    @ColumnInfo(name = "full_height")
    val heightFull: Int,
    @ColumnInfo(name = "thumb_url")
    val urlThumb: String,
    @ColumnInfo(name = "thumb_width")
    val widthThumb: Int,
    @ColumnInfo(name = "thumb_height")
    val heightThumb: Int,
    @ColumnInfo(name = "tags_string")
    val tags: String,
    @ColumnInfo(name = "likes_count")
    val likesCount: Int,
    @ColumnInfo(name = "downloads_count")
    val downloadsCount: Int,
    @ColumnInfo(name = "comments_count")
    val commentsCount: Int
)