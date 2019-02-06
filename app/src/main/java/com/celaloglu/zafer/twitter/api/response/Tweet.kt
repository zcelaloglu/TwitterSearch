package com.celaloglu.zafer.twitter.api.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class Tweet(val username: String,
                 val content: String,
                 val createdAt: Date): Parcelable
