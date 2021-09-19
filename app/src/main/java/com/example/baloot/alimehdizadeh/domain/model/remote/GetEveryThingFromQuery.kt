package com.example.baloot.alimehdizadeh.domain.model.remote

import android.os.Parcelable
import com.bluelinelabs.logansquare.annotation.JsonObject

import com.bluelinelabs.logansquare.annotation.JsonField
import kotlinx.parcelize.Parcelize
@Parcelize
@JsonObject
data class GetEveryThingFromQuery(
    @JsonField(name = ["articles"])
    var articles: List<Article>?= null,
    @JsonField(name = ["status"])
    var status: String?= null,
    @JsonField(name = ["totalResults"])
    var totalResults: Int?= null
) : Parcelable

@Parcelize
@JsonObject
data class Article(
    @JsonField(name = ["author"])
    var author: String?= null,
    @JsonField(name = ["content"])
    var content: String?= null,
    @JsonField(name = ["description"])
    var description: String?= null,
    @JsonField(name = ["publishedAt"])
    var publishedAt: String?= null,
    @JsonField(name = ["source"])
    var source: Source?= null,
    @JsonField(name = ["title"])
    var title: String?= null,
    @JsonField(name = ["url"])
    var url: String?= null,
    @JsonField(name = ["urlToImage"])
    var urlToImage: String?= null
) : Parcelable

@Parcelize
@JsonObject
data class Source(
    @JsonField(name = ["id"])
    var id: String?= null,
    @JsonField(name = ["name"])
    var name: String?= null
) : Parcelable