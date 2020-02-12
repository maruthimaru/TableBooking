package com.cs442.dsuraj.quantumc

import android.Manifest
import android.app.Activity
import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.*
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.AsyncTask
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.speech.tts.TextToSpeech.OnInitListener
import android.telephony.SmsManager
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import com.cs442.dsuraj.quantumc.db.AppDatabase
import com.cs442.dsuraj.quantumc.db.JSONArrayCursor
import com.cs442.dsuraj.quantumc.db.dao.MovieBookedDao
import com.cs442.dsuraj.quantumc.db.table.DataResponse
import com.cs442.dsuraj.quantumc.retrofit.CustomDialog
import com.cs442.dsuraj.quantumc.retrofit.Utils
import com.google.gson.Gson
import com.scoto.visitormanagent.retrofit.ApiService
import com.scoto.visitormanagent.retrofit.RetrofitClientInstance
import org.json.JSONArray
import retrofit2.Call
import retrofit2.Callback
import java.util.*
import javax.mail.AuthenticationFailedException
import javax.mail.MessagingException

/**
 * Created by sushma on 10/23/2016.
 */
class TableConfirmation : AppCompatActivity(), OnInitListener {
    private lateinit var amount: String
    private lateinit var home: Button
    private lateinit var imageview: ImageView
    private lateinit var datetime: TextView
    private lateinit var totalamount: TextView
    private lateinit var seats: TextView
    private lateinit var bookingid: TextView
    private lateinit var theatre: TextView
    private val CHECK_CODE = 0x1
    private val LONG_DURATION = 5000
    private val SHORT_DURATION = 1200
    var MovieName: TextView? = null
    var notificationManager: NotificationManager? = null
    var speech: String? = null
    var bm: Bitmap? = null
    var Builder: NotificationCompat.Builder? = null
    var notification: Notification? = null
    private val bundle: Bundle? = null
    private val MY_DATA_CHECK_CODE = 0
    lateinit var appDatabase: AppDatabase
    lateinit var movieBookedDao: MovieBookedDao
    private val myTTS: TextToSpeech? = null
    lateinit var jSONArrayCursor: Cursor
    val TAG=TableConfirmation::class.java.simpleName
    lateinit var customDialog:CustomDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.movieconfirmation)
        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        Builder = NotificationCompat.Builder(this@TableConfirmation)
        val checkTTSIntent = Intent()
        checkTTSIntent.action = TextToSpeech.Engine.ACTION_CHECK_TTS_DATA
        startActivityForResult(checkTTSIntent, MY_DATA_CHECK_CODE)
        Log.i("inside screen", "ok")
        MovieName = findViewById<View>(R.id.MovieName) as TextView
        theatre = findViewById<View>(R.id.theatrescreen) as TextView
        bookingid = findViewById<View>(R.id.bookingid) as TextView
         seats = findViewById<View>(R.id.seats) as TextView
         totalamount = findViewById<View>(R.id.totalamount) as TextView
         datetime = findViewById<View>(R.id.datetime) as TextView
         imageview = findViewById<View>(R.id.imageView3) as ImageView
        Log.i("done select", "ok")
         home = findViewById<View>(R.id.home) as Button
        customDialog=CustomDialog(this)
        appDatabase= AppDatabase.getDatabase(this)
        jSONArrayCursor=JSONArrayCursor()
        movieBookedDao=appDatabase.movieBooking()
        home.setOnClickListener {
            val home = Intent(this@TableConfirmation, MainActivity::class.java)
            startActivity(home)
        }
        val BookingId = intent.getIntExtra("booking_id", 0)
        val intent = intent
        amount = intent.getStringExtra("amount")
        val d = DatabaseHelper(applicationContext)
        //boolean val= d.insertdatamovie("Flywire","adad", "6", "adad",true,5 );
// System.out.print(val);
//  val= d.insertdatatimimgs(1,"Imax","10.30",50);
//System.out.print(val);
//val=d.insertmoviebooked(1,"1","imax","10.20", 50,"cs442project@gmail.com", "2247033339");
//System.out.print(val);
        Log.i("done inserting", "ok")

        val cursor = movieBookedDao.getData(BookingId.toString())
        getData(BookingId.toString())
        val db = d.readableDatabase
//        val cursor = d.getMaxTables(db, BookingId.toString())
        Log.i("done selecting", "ok")
        bookingid.text = BookingId.toString()
        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.SEND_SMS), 1)
        cursorProcess(cursor)
    }

    fun cursorProcess(cursor: Cursor){
        while (cursor.moveToNext()) {
            print("maxTables")
            val email = cursor.getString(5)
            println(email)
            println(cursor.getString(6))
            val phoneno = cursor.getString(6)
            println(phoneno)
            MovieName!!.text = cursor.getString(0)
            theatre.text = cursor.getString(1)
            totalamount.text = amount
            datetime.text = cursor.getString(3) + ' ' + cursor.getString(4)
            Log.e("TAG", "onCreate: seats selected : $amount")
            seats.text = cursor.getString(7)
            when (MovieName!!.text.toString()) {
                "Fly Paper" -> bm = BitmapFactory.decodeResource(resources, R.drawable.image1)
                "Mission: Impossible - Rogue Nation" -> bm = BitmapFactory.decodeResource(resources, R.drawable.front)
                "Avengers: Age of Ultron" -> bm = BitmapFactory.decodeResource(resources, R.drawable.premium)
                "The Amazing Spider-Man 2" -> bm = BitmapFactory.decodeResource(resources, R.drawable.spiddy)
            }
            imageview.setImageBitmap(bm)
            speech = "Movie Name:" + MovieName!!.text.toString() + "Theatre: " + theatre.text.toString() + "on " + cursor.getString(3) + "at " + cursor.getString(4)
            try { //SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
//Date dt=sdf.parse(datetime.getText().toString());
//System.out.println(dt);
                val cal = Calendar.getInstance()
                //cal.setTime(sdf.parse(datetime.getText().toString()));
                val EVENTS_URI = Uri.parse(getCalendarUriBase(this) + "events")
                var cr = contentResolver
                // event insert
                var values = ContentValues()
                values.put("calendar_id", 1)
                values.put("title", "Movie Booking")
                values.put("allDay", 0)
                values.put("dtstart", cal.timeInMillis + 11 * 60 * 1000) // event starts at 11 minutes from now
                values.put("dtend", cal.timeInMillis + 60 * 60 * 1000) // ends 60 minutes from now
                values.put("description", speech)
                values.put("eventTimezone", TimeZone.getDefault().id)
                values.put("hasAlarm", 1)
                val event = cr.insert(EVENTS_URI, values)
                // reminder insert
                val REMINDERS_URI = Uri.parse(getCalendarUriBase(this) + "reminders")
                cr = contentResolver
                values = ContentValues()
                values.put("event_id", event.lastPathSegment.toLong())
                values.put("method", 1)
                values.put("minutes", 5)
                cr.insert(REMINDERS_URI, values)
            } catch (e: Exception) { //  Toast.makeText(getApplicationContext(), "Reminder Could not be set",
//     Toast.LENGTH_SHORT).show();
            }
            /* try {
                new SendEmailAsyncTask().execute(email, bookingid.getText().toString(), MovieName.getText().toString(), theatre.getText().toString(), datetime.getText().toString(), seats.getText().toString(), totalamount.getText().toString());
                Toast.makeText(getApplicationContext(), "Email Sent Succesfully",
                        Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                Log.e("SendMail", e.getMessage(), e);
                Toast.makeText(getApplicationContext(), "Error in Sending Email",
                        Toast.LENGTH_SHORT).show();
            }*/
//            sendSMS(phoneno, "MovieName: " + MovieName.getText().toString() + " Theatre:" + theatre.getText().toString() +
//                    "  Seats:" + seats.getText().toString() + " Total Amount: $" + totalamount.getText().toString() + " Date/Time:" + datetime.getText().toString());
        }
    }

    private fun getData(bookingId:String){
//        alertDialog = customDialog.loading(activity!!)
        val showme=customDialog.processDialog()
        val service = RetrofitClientInstance.createServices(ApiService::class.java, Utils.baseUrl)
        val listCall = service.getdata(bookingId)
        Log.e(TAG, "listCall : " + listCall.request())
        listCall.enqueue(object : Callback<DataResponse> {
            override fun onFailure(call: Call<DataResponse>, t: Throwable) {
                Log.e(TAG, "onFailure : " + t.localizedMessage)
                showme.dismiss()
            }
            override fun onResponse(call: Call<DataResponse>, response: retrofit2.Response<DataResponse>) {
                Log.e(TAG, "onResponse : " + response)
                if (response.body() != null) {
                    if (response.body()!!.status!!) {
                        val jsArray = JSONArray(Gson().toJson(response.body()!!.data));
                        Log.e(TAG,"json object get " + Gson().toJson(response.body()!!.data))
                        Log.e(TAG,"json jsArray " + jsArray)
                    jSONArrayCursor=JSONArrayCursor(jsArray)
                    cursorProcess(jSONArrayCursor)
                }else{
                    Toast.makeText(applicationContext,response.body()!!.message!!,Toast.LENGTH_SHORT).show()
                }
                    showme.dismiss()
                }else{
                    showme.dismiss()
                }
            }

        })
    }

    private fun getCalendarUriBase(act: Activity): String? {
        var calendarUriBase: String? = null
        var calendars = Uri.parse("content://calendar/calendars")
        var managedCursor: Cursor? = null
        try {
            managedCursor = act.managedQuery(calendars, null, null, null, null)
        } catch (e: Exception) {
        }
        if (managedCursor != null) {
            calendarUriBase = "content://calendar/"
        } else {
            calendars = Uri.parse("content://com.android.calendar/calendars")
            try {
                managedCursor = act.managedQuery(calendars, null, null, null, null)
            } catch (e: Exception) {
            }
            if (managedCursor != null) {
                calendarUriBase = "content://com.android.calendar/"
            }
        }
        return calendarUriBase
    }

    private fun sendSMS(phoneNumber: String, message: String) {
        val SENT = "SMS_SENT"
        val DELIVERED = "SMS_DELIVERED"
        val sentPI = PendingIntent.getBroadcast(this, 0,
                Intent(SENT), 0)
        val deliveredPI = PendingIntent.getBroadcast(this, 0,
                Intent(DELIVERED), 0)
        //---when the SMS has been sent---
        registerReceiver(object : BroadcastReceiver() {
            override fun onReceive(arg0: Context, arg1: Intent) {
                when (resultCode) {
                    Activity.RESULT_OK -> Toast.makeText(baseContext, "SMS sent",
                            Toast.LENGTH_SHORT).show()
                    SmsManager.RESULT_ERROR_GENERIC_FAILURE -> Toast.makeText(baseContext, "Generic failure",
                            Toast.LENGTH_SHORT).show()
                    SmsManager.RESULT_ERROR_NO_SERVICE -> Toast.makeText(baseContext, "No service",
                            Toast.LENGTH_SHORT).show()
                    SmsManager.RESULT_ERROR_NULL_PDU -> Toast.makeText(baseContext, "Null PDU",
                            Toast.LENGTH_SHORT).show()
                    SmsManager.RESULT_ERROR_RADIO_OFF -> Toast.makeText(baseContext, "Radio off",
                            Toast.LENGTH_SHORT).show()
                }
            }
        }, IntentFilter(SENT))
        //---when the SMS has been delivered---
        registerReceiver(object : BroadcastReceiver() {
            override fun onReceive(arg0: Context, arg1: Intent) {
                when (resultCode) {
                    Activity.RESULT_OK -> Toast.makeText(baseContext, "SMS delivered",
                            Toast.LENGTH_SHORT).show()
                    Activity.RESULT_CANCELED -> Toast.makeText(baseContext, "SMS not delivered",
                            Toast.LENGTH_SHORT).show()
                }
            }
        }, IntentFilter(DELIVERED))
        val sms = SmsManager.getDefault()
        sms.sendTextMessage(phoneNumber, "null", message.trim { it <= ' ' }, sentPI, deliveredPI)
    }

    internal inner class SendEmailAsyncTask : AsyncTask<String?, Void?, Boolean>() {
        var m = GMailSender("cs442project@gmail.com", "cs442mobile")
        protected override fun doInBackground(vararg params: String?): Boolean {
            if (BuildConfig.DEBUG) Log.v(SendEmailAsyncTask::class.java.name, "doInBackground()")
            return try {
                m.sendMail("Movie Booking",
                        "Booking Id : " + params[1] + " MovieName: " + params[2] + " Theatre: " + params[3] + " Date/Time: " + params[4] + " No Of Seats: " + params[5] + " TotalAmount:" + params[6],
                        "cs442project@gmail.com",
                        params[0]!!)
                true
            } catch (e: AuthenticationFailedException) {
                Log.e(SendEmailAsyncTask::class.java.name, "Bad account details")
                e.printStackTrace()
                false
            } catch (e: MessagingException) {
                e.printStackTrace()
                false
            } catch (e: Exception) {
                e.printStackTrace()
                false
            }
        }
    }

    private fun speakWords(speech: String) { //speak straight away
        Log.d("Speak Words Called", " ")
        myTTS!!.setPitch(1.1f)
        myTTS.setSpeechRate(0.8f)
        myTTS.speak(speech, TextToSpeech.QUEUE_FLUSH, null, null)
    }

    fun notification(message: String?) //Notification tab to print the notification
    {
        Log.d("Notification started", " ")
        Log.e("TAG", "notification movie name : " + MovieName!!.text.toString())
        when (MovieName!!.text.toString()) {
            "Fly Paper" -> bm = BitmapFactory.decodeResource(resources, R.drawable.images)
            "Mission: Impossible - Rogue Nation" -> bm = BitmapFactory.decodeResource(resources, R.drawable.mi55)
            "Avengers: Age of Ultron" -> bm = BitmapFactory.decodeResource(resources, R.drawable.avengers3)
            "The Amazing Spider-Man 2" -> bm = BitmapFactory.decodeResource(resources, R.drawable.spiddy)
        }
        notification = Builder!!.setSmallIcon(R.mipmap.ic_launcher)
                .setTicker("Notification")
                .setWhen(System.currentTimeMillis())
                .setAutoCancel(true).setLargeIcon(bm)
                .setContentTitle("Movie Booked ").setContentText(message)
                .setDefaults(Notification.DEFAULT_SOUND or Notification.DEFAULT_VIBRATE).build()
        val inboxstyle = NotificationCompat.InboxStyle()
        inboxstyle.setBigContentTitle("Booking Confirmation")
        inboxstyle.addLine(message)
        Builder!!.setStyle(inboxstyle)
        notificationManager!!.notify(ID, notification)
        //        speakWords(speech);
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == MY_DATA_CHECK_CODE) {
            if (resultCode == TextToSpeech.Engine.CHECK_VOICE_DATA_PASS) { //the user has the necessary maxTables - create the TTS
//                myTTS = new TextToSpeech(TableConfirmation.this, this);
                Log.d("Initialised TTS", " ")
            } else { //no maxTables - install it now
                val installTTSIntent = Intent()
                installTTSIntent.action = TextToSpeech.Engine.ACTION_INSTALL_TTS_DATA
                startActivity(installTTSIntent)
            }
        }
    }

    override fun onInit(initStatus: Int) { //check for successful instantiation
        if (initStatus == TextToSpeech.SUCCESS) { //            if(myTTS.isLanguageAvailable(Locale.US)==TextToSpeech.LANG_AVAILABLE)
//                myTTS.setLanguage(Locale.US);
            Log.d("Success", " ")
            notification(speech)
        } else if (initStatus == TextToSpeech.ERROR) {
            Toast.makeText(this@TableConfirmation, "Sorry! Text To Speech failed...", Toast.LENGTH_LONG).show()
            Log.d("Failed", " ")
        }
    }

    override fun onBackPressed() { // super.onBackPressed();
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    companion object {
        const val ID = 45612
    }
}