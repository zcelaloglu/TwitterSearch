package com.celaloglu.zafer.twitter.util

import android.util.Log
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonParseException
import java.lang.reflect.Type
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class DateDeserializer : JsonDeserializer<Date> {

    private val TAG = "DateDeserializer"

    @Throws(JsonParseException::class)
    override fun deserialize(element: JsonElement, arg1: Type, arg2: JsonDeserializationContext): Date? {
        val date = element.asString

        val twitterFormat = "EEE MMM dd HH:mm:ss ZZZZZ yyyy"
        val formatter = SimpleDateFormat(twitterFormat, Locale.ENGLISH)
        formatter.isLenient = true
        formatter.timeZone = TimeZone.getTimeZone("UTC")

        return try {
            formatter.parse(date)
        } catch (e: ParseException) {
            Log.e(TAG, "Failed to parse Date due to:", e)
            null
        }

    }
}