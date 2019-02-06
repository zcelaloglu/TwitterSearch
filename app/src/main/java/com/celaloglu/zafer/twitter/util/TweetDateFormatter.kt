package com.celaloglu.zafer.twitter.util

import android.content.Context
import com.celaloglu.zafer.twitter.R
import java.util.*

class TweetDateFormatter {

    companion object {

        @JvmStatic
        fun format(context: Context, date: Date): String {

            val SECONDS_PER_DAY = (24 * 60 * 60).toLong()

            val diff = (Date().time - date.time) / SECONDS_PER_DAY

            if (diff < 60) {
                return context.getString(R.string.seconds, diff)
            }

            if (diff < 60 * 60) {
                return context.getString(R.string.minutes, diff / 60)
            }

            return if (diff < 24 * 60 * 60) {
                context.getString(R.string.hours, diff / (60 * 60))
            } else context.getString(R.string.days, diff / SECONDS_PER_DAY)
        }
    }
}