package it.android.luca.movieapp.util

import android.content.Context
import android.util.DisplayMetrics

class Utils {

    companion object {
        fun convertDpToPixel(dp: Int, context: Context): Int {
            val resources = context.resources
            val metrics = resources.displayMetrics
            return (dp * (metrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)).toInt()
        }
    }
}