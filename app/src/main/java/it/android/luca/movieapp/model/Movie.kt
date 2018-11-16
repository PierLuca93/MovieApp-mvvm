package it.android.luca.movieapp.repository

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Movie(
    @SerializedName("vote_count")
    @Expose
    val vote_count: Int = 0,
    @SerializedName("id")
    @Expose
    val id: Int = -1,
    @SerializedName("video")
    @Expose
    val video: Boolean = false,
    @SerializedName("vote_average")
    @Expose
    val vote_average: Float = 0f,
    @SerializedName("title")
    @Expose
    val title: String = "",
    @SerializedName("popularity")
    @Expose
    val popularity: Float = 0f,
    @SerializedName("poster_path")
    @Expose
    val poster_path: String = "",
    @SerializedName("original_language")
    @Expose
    val original_language: String = "",
    @SerializedName("original_title")
    @Expose
    val original_title: String = "",
    @SerializedName("genre_ids")
    @Expose
    val genre_ids: Array<Int> = arrayOf(),
    @SerializedName("backdrop_path")
    @Expose
    val backdrop_path: String = "",
    @SerializedName("adult")
    @Expose
    val adult: Boolean = false,
    @SerializedName("overview")
    @Expose
    val overview: String = "",
    @SerializedName("release_date")
    @Expose
    val release_date: String = ""
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Movie

        if (id != other.id) return false

        return true
    }
}