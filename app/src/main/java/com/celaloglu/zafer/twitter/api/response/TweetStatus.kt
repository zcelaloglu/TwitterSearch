package com.celaloglu.zafer.twitter.api.response

import android.os.Parcelable
import android.support.annotation.VisibleForTesting
import android.text.TextUtils
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue
import java.util.*

@Parcelize
data class TweetStatus(val statuses: @RawValue List<Status>,
                       @SerializedName("search_metadata")
                       val searchMetadata: @RawValue SearchMetadata) : Parcelable

class Status private constructor(@SerializedName("created_at")
                                 val createdAt: Date,
                                 val text: String,
                                 val id_str: String,
                                 private val user: User,
                                 private val entities: Entities?) {

    fun getUserName(): String {
        return user.name
    }

    private class User private constructor(val name: String,
                                           @field:SerializedName("screen_name")
                                           val screenName: String)

    private class Entities(val media: List<Media>) {

        class Media(@SerializedName("media_url_https")
                    val url: String,
                    private val type: String) {

            val isPhoto: Boolean
                get() = "photo".equals(type, ignoreCase = true) && !TextUtils.isEmpty(url)
        }
    }
}

@Parcelize
data class SearchMetadata(
        @SerializedName("next_results")
        var nextResults: String,
        @SerializedName("since_id_str")
        var sinceIdStr: String,
        @SerializedName("max_id_str")
        var maxIdStr: String,
        @SerializedName("query")
        var query: String,
        @SerializedName("count")
        var count: Int = 0,
        @SerializedName("max_id")
        var maxId: Long = 0,
        @SerializedName("since_id")
        var sinceId: Long = 0,
        @SerializedName("refresh_url")
        var refreshUrl: String,
        @SerializedName("completed_in")
        var completedIn: Double = 0.toDouble()
) : Parcelable {

    @VisibleForTesting
    constructor() : this("1", "1", "1", "turkey", 0,
            0, 0, "q", 0.0)
}