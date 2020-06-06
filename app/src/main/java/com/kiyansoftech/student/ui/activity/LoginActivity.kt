package com.kiyansoftech.student.ui.activity

import android.Manifest
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.Bohni.Globals.Global
import com.contestee.extention.*
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.iid.FirebaseInstanceId
import com.kiyansoftech.student.Global.BaseActivity
import com.kiyansoftech.student.R
import com.kiyansoftech.student.model.Login.StudentLogin
import com.kiyansoftech.student.utils.MyApplication.Companion.context
import com.kiyansoftech.student.utils.Utility
import com.oeye.network.Networking
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_signup.*
import kotlinx.android.synthetic.main.dialog_referral.*
import kotlinx.android.synthetic.main.dialog_referral.view.*
import kotlinx.android.synthetic.main.dialog_referral.view.txtcode
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginActivity : BaseActivity() {
    private var sharedPref: SharedPreferences? = null
    private val RECORD_REQUEST_CODE = 101

    var global: Global? = null
    override fun getLayout(): Int {
        return R.layout.activity_login
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        global = Global.getInstance(this)

        btnLogin.setOnClickListener {

            val mDialogView = LayoutInflater.from(this).inflate(R.layout.dialog_referral, null)
            //AlertDialogBuilder
            val mBuilder = AlertDialog.Builder(this)
                .setView(mDialogView)
            //show dialog
            val mAlertDialog = mBuilder.show()
            //login button click of custom layout
            mDialogView.btnsubmit.setOnClickListener {

                Utility.checkPermission(this)
                //dismiss dialog
               // Logincall()
                if (mDialogView.txtcode.isEmpty()){
                    Toast.makeText(applicationContext,"Enter Refferalcode",Toast.LENGTH_LONG).show()
                }else{
                    Logincall()

                }
//                mAlertDialog.dismiss()
            }

            // Logincall()
            // startActivity(Intent(this, WelcomeLoginActivity::class.java))
        }

        txtForgot.setOnClickListener {
            startActivity(Intent(this, ForgotPasswordScreen::class.java))

        }
    }

/*
    private fun setupPermissions() {
        val permission = ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )
        */
/* val permission2 = ContextCompat.checkSelfPermission(this,
             Manifest.permission.WRITE_EXTERNAL_STORAGE)*//*

        if (permission != PackageManager.PERMISSION_GRANTED) {
            makeRequest()
        }
    }
*/

/*
    private fun makeRequest() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
            RECORD_REQUEST_CODE
        )
    }
*/

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            RECORD_REQUEST_CODE -> {
                if (grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED) {

                    Toast.makeText(
                        applicationContext, "Permission has been denied by user", Toast.LENGTH_LONG
                    ).show()

                } else {
                    Toast.makeText(
                        applicationContext, "Permission has been granted by user", Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }


    private fun Logincall() {
        if (!isValidEmail(txtEmail.getValue())) {
            root.showSnackBar(getString(R.string.enter_valid_email))
        } else if (txtPassword.isEmpty()) {
            root.showSnackBar(getString(R.string.enter_password))
        } else {


            FirebaseInstanceId.getInstance().instanceId
                .addOnCompleteListener(OnCompleteListener { task ->
                    if (!task.isSuccessful) {
                        // Logger.e("getInstanceId failed", task.exception?.message.toString())
                        showAlert("Something went wrong please restart Application!")
//                        hideProgressbar()
                        return@OnCompleteListener
                    }
                    // Get new Instance ID token
                    val token = task.result?.token
                    login(token.toString())

                })

        }

    }

    fun login(token: String) {

        progreslogin.visible();
        Networking.with().getServices()
            .makeStudentLogin(txtEmail.getValue(), txtPassword.getValue(), token.toString())
            .enqueue(object : Callback<StudentLogin> {
                override fun onFailure(call: Call<StudentLogin>, t: Throwable) {
                    Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
                }

                override fun onResponse(
                    call: Call<StudentLogin>,
                    response: Response<StudentLogin>
                ) {
                    if (response.body()?.status == 0) {

                        Toast.makeText(
                            applicationContext,
                            response.body()?.message,
                            Toast.LENGTH_LONG
                        ).show()
                        progreslogin.hide();
                    } else {
                        val userid = response.body()?.data?.userId;

//                        session.isLoggedIn=true
                        session.user = response.body()?.data
                        session.storeDataByKey("userid", userid.toString())

                        val intent = Intent(this@LoginActivity, Dashboard::class.java)
                        intent.putExtra("userid", userid)
                        startActivity(intent)
                        Toast.makeText(
                            applicationContext,
                            response.body()?.message,
                            Toast.LENGTH_LONG
                        ).show()
/*
                        goToActivityAndClearTask<Dashboard>()
*/
                        progreslogin.hide();
                    }


                }

            })

    }


}
