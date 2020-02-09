package layout

import android.app.ProgressDialog
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.cs442.dsuraj.quantumc.DatabaseHelper
import com.cs442.dsuraj.quantumc.R
import com.cs442.dsuraj.quantumc.db.AppDatabase
import com.cs442.dsuraj.quantumc.db.dao.UserInfoDao
import com.cs442.dsuraj.quantumc.db.table.UserInfo
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.auth.api.signin.GoogleSignInResult
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener
import com.google.android.gms.common.api.ResultCallback
import com.google.android.gms.common.api.Status
import java.net.URL

class GPlusFragment : Fragment(), OnConnectionFailedListener {
    var sql: SQLiteDatabase? = null
    var db: DatabaseHelper? = null
    var mGoogleApiClient: GoogleApiClient? = null
    private val RC_SIGN_IN = 0
    private var mProgressDialog: ProgressDialog? = null
    private var mStatusTextView: TextView? = null
    var signInButton: SignInButton? = null
    var signOutButton: Button? = null
    private var imgProfilePic: ImageView? = null
    lateinit var appDatabase: AppDatabase
    lateinit var userInfoDao: UserInfoDao
    private val mListener: OnFragmentInteractionListener? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        db = DatabaseHelper(activity)
        appDatabase= AppDatabase.getDatabase(activity!!)
        userInfoDao=appDatabase.userInfo()
        sql = db!!.writableDatabase
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail().requestProfile()
                .build()
        mGoogleApiClient = GoogleApiClient.Builder(activity!!)
                .enableAutoManage(activity!! /* FragmentActivity */, this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build()
    }

    override fun onStart() {
        super.onStart()
        val opr = Auth.GoogleSignInApi.silentSignIn(mGoogleApiClient)
        if (opr.isDone) { // If the user's cached credentials are valid, the OptionalPendingResult will be "done"
// and the GoogleSignInResult will be available instantly.
            Log.d(TAG, "Got cached sign-in")
            val result = opr.get()
            handleSignInResult(result)
        } else { // If the user has not previously signed in on this device or the sign-in has expired,
// this asynchronous branch will attempt to sign in the user silently.  Cross-device
// single sign-on will occur in this branch.
            showProgressDialog()
            opr.setResultCallback { googleSignInResult ->
                hideProgressDialog()
                handleSignInResult(googleSignInResult)
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, parent: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_gplus, parent, false)
        signInButton = v.findViewById<View>(R.id.sign_in_button) as SignInButton
        signOutButton = v.findViewById<View>(R.id.sign_out_button) as Button
        imgProfilePic = v.findViewById<View>(R.id.img_profile_pic) as ImageView
        mStatusTextView = v.findViewById<View>(R.id.status) as TextView
        val icon = BitmapFactory.decodeResource(context!!.resources, R.drawable.user_default)
        imgProfilePic!!.setImageBitmap(ImageHelper.getRoundedCornerBitmap(activity!!, icon, 200, 200, 200, false, false, false, false))
        signInButton!!.setOnClickListener {
            val signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient)
            startActivityForResult(signInIntent, RC_SIGN_IN)
        }
        signOutButton!!.setOnClickListener {
            Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                    object : ResultCallback<Status?> {
                        override fun onResult(status: Status) {
                            updateUI(false)
                        }
                    })
        }
        return v
    }

    private fun updateUI(signedIn: Boolean) {
        if (signedIn) {
            signInButton!!.visibility = View.GONE
            signOutButton!!.visibility = View.VISIBLE
        } else {
            mStatusTextView!!.setText(R.string.signed_out)
            val icon = BitmapFactory.decodeResource(context!!.resources, R.drawable.user_default)
            imgProfilePic!!.setImageBitmap(ImageHelper.getRoundedCornerBitmap(activity!!, icon, 200, 200, 200, false, false, false, false))
            signInButton!!.visibility = View.VISIBLE
            signOutButton!!.visibility = View.GONE
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        super.onActivityResult(requestCode, resultCode, data)
        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            val result = Auth.GoogleSignInApi.getSignInResultFromIntent(data)
            handleSignInResult(result)
        }
    }

    private fun handleSignInResult(result: GoogleSignInResult) {
        Log.d(TAG, "handleSignInResult:" + result.isSuccess)
        if (result.isSuccess) { // Signed in successfully, show authenticated UI.
            val acct = result.signInAccount
            mStatusTextView!!.text = getString(R.string.signed_in_fmt, acct!!.displayName)
            //Similarly you can get the email and photourl using acct.getEmail() and  acct.getPhotoUrl(
            if (acct.photoUrl != null) LoadProfileImage(imgProfilePic).execute(acct.photoUrl.toString())
            val list=ArrayList<UserInfo>()
            list.add(UserInfo(acct.id, acct.displayName, acct.email))
            userInfoDao.insert(list)
            db!!.insertuser(acct.id, acct.displayName, acct.email)
            println(acct.displayName + " " + acct.email)
            updateUI(true)
        } else { // Signed out, show unauthenticated UI.
            updateUI(false)
        }
    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri?) {
        mListener?.onFragmentInteraction(uri)
    }

    private fun showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = ProgressDialog(activity)
            mProgressDialog!!.setMessage(getString(R.string.loading))
            mProgressDialog!!.isIndeterminate = true
        }
        mProgressDialog!!.show()
    }

    private fun hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog!!.isShowing) {
            mProgressDialog!!.hide()
        }
    }

    private inner class LoadProfileImage(var bmImage: ImageView?) : AsyncTask<String?, Void?, Bitmap?>() {
        protected override fun doInBackground(vararg uri: String?): Bitmap? {
            val url = uri[0]
            var mIcon11: Bitmap? = null
            try {
                val `in` = URL(url).openStream()
                mIcon11 = BitmapFactory.decodeStream(`in`)
            } catch (e: Exception) {
                Log.e("Error", e.message)
                e.printStackTrace()
            }
            return mIcon11
        }

        override fun onPostExecute(result: Bitmap?) {
            if (result != null) {
                val resized = Bitmap.createScaledBitmap(result, 200, 200, true)
                bmImage!!.setImageBitmap(ImageHelper.getRoundedCornerBitmap(activity!!, resized, 250, 200, 200, false, false, false, false))
            }
        }

    }

    override fun onConnectionFailed(connectionResult: ConnectionResult) {}
    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments](http://developer.android.com/training/basics/fragments/communicating.html) for more information.
     */
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri?)
    }

    companion object {
        private const val TAG = "GPlusFragent"
    }
}