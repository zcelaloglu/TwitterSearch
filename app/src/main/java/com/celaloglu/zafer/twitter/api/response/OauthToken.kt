package com.celaloglu.zafer.twitter.api.response

import android.os.Parcelable
import com.celaloglu.zafer.twitter.util.Constants
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class OauthToken(@SerializedName(Constants.ACCESS_TOKEN_KEY)
                      val accessToken: String?) : Parcelable