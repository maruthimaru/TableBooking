package com.scoto.visitormanagent.retrofit

import com.cs442.dsuraj.quantumc.db.table.*
import com.cs442.dsuraj.quantumc.retrofit.Utils
import retrofit2.Call
import retrofit2.http.*


interface ApiService {

//    @Streaming
//    @GET
//    fun downloadFile(@Url fileUrl: String): Call<ResponseBody>
//
//    @GET(Utils.sendPersonalMessage)
//    fun sendPersonalMessage(@Query(value = APIKey.KEY_PARENT_ID_SMALL)parentid:String,
//                            @Query(value =APIKey.KEY_MESSAGE)message:String,
//                            @Query(value =APIKey.KEY_USER)user:String,
//                            @Query(value =APIKey.KEY_STUDENT_ID_SMALL)studentid:String,
//                            @Query(value =APIKey. KEY_TEACHER_ID_SMALL)teacherID:String): Call<Any>
//
//    @GET(Utils.updatePayment)
//    fun updatePayment(
//        @Query(value = APIKey.KEY_STUDENT_ID_CAPS) studentID:  String,
//        @Query(value = APIKey.KEY_AMOUNT) amount: String,
//        @Query(value = APIKey.KEY_TRANSACTION_ID) transaction: String
//    ): Call<Object>
//
//
    @POST(Utils.inserTableUrl)
    fun inserTablet(@Body movieList:ArrayList<Movies>): Call<ArrayList<Movies>>

    @POST(Utils.inserBookedTableListUrl)
    fun insertBookedTable(@Body movieList:ArrayList<MoviesBooked>):Call<ArrayList<MoviesBooked>>

    @FormUrlEncoded
    @POST(Utils.getdatatUrl)
    fun getdata(@Field("BOOKING_ID") bookingId: String):Call<DataResponse>

    @POST(Utils.getSeat)
    fun getSeat(@Body seatRequest:SeatRequest):Call<SeatDetailResponse>

    @GET(Utils.getMax)
    fun getmax():Call<MaxResponse>
}
