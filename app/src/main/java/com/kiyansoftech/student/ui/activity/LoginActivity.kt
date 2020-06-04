package com.kiyansoftech.student.ui.activity

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import com.Bohni.Globals.Global
import com.contestee.extention.*
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.iid.FirebaseInstanceId
import com.kiyansoftech.student.Global.BaseActivity
import com.kiyansoftech.student.R
import com.kiyansoftech.student.model.Login.StudentLogin
import com.oeye.network.Networking
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_signup.*
import kotlinx.android.synthetic.main.dialog_referral.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginActivity : BaseActivity() {
    private var sharedPref: SharedPreferences? = null

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
            val  mAlertDialog = mBuilder.show()
            //login button click of custom layout
            mDialogView.btnsubmit.setOnClickListener {
                //dismiss dialog
                Logincall()
//                mAlertDialog.dismiss()

            }

            // Logincall()
           // startActivity(Intent(this, WelcomeLoginActivity::class.java))
        }

        txtForgot.setOnClickListener {
            startActivity(Intent(this, ForgotPasswordScreen::class.java))

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
        Networking.with().getServices().makeStudentLogin(txtEmail.getValue(),txtPassword.getValue(),token.toString())
            .enqueue(object : Callback<StudentLogin> {
                override fun onFailure(call: Call<StudentLogin>, t: Throwable) {
                    Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
                }

                override fun onResponse(
                    call: Call<StudentLogin>,
                    response: Response<StudentLogin>
                ) {
                    if (response.body()?.status ==0){

                        Toast.makeText(applicationContext, response.body()?.message, Toast.LENGTH_LONG).show()
                            progreslogin.hide();
                    }else{
                        val userid=response.body()?.data?.userId;

//                        session.isLoggedIn=true
                        session.user = response.body()?.data
                        session.storeDataByKey("userid",userid.toString())

                        val intent = Intent(this@LoginActivity,Dashboard::class.java)
                        intent.putExtra("userid",userid)
                        startActivity(intent)
                        Toast.makeText(applicationContext, response.body()?.message, Toast.LENGTH_LONG).show()
/*
                        goToActivityAndClearTask<Dashboard>()
*/
                        progreslogin.hide();
                    }


                }

            })

    }


}
