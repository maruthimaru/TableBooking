package com.cs442.dsuraj.quantumc

/**
 * Created by sushma on 11/24/2016.
 */
class TableReview(private var moviename: String, private var movieimage: String, private var rating: Int, private var user: String) {
    fun getuser(): String {
        return user
    }

    fun setuser(user: String) {
        this.user = user
    }

    fun getmoviename(): String {
        return moviename
    }

    fun setmoviename(moviename: String) {
        this.moviename = moviename
    }

    fun getmovieimage(): String {
        return movieimage
    }

    fun setmovieimage(menuimage: String) {
        movieimage = menuimage
    }

    fun getmovierating(): Int {
        return rating
    }

    fun setmovierating(rating: Int) {
        this.rating = rating
    }

    override fun toString(): String {
        return moviename + ". " + movieimage
    }

}