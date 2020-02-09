package com.cs442.dsuraj.quantumc

class TableDetails(private var moviename: String, private var movieimage: String, private var moviedata: String) {
    fun getmoviedata(): String {
        return moviedata
    }

    fun setmoviedata(moviedata: String) {
        this.moviedata = moviedata
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

    override fun toString(): String {
        return moviename + ". " + movieimage
    }

}