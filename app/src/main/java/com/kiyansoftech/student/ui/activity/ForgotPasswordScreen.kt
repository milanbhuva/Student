package com.kiyansoftech.student.ui.activity

import android.os.Bundle
import android.widget.Toast
import com.contestee.extention.*
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.iid.FirebaseInstanceId
import com.kiyansoftech.student.Global.BaseActivity
import com.kiyansoftech.student.R
import com.kiyansoftech.student.model.ForgotPwd.ForgotPassword
import com.oeye.extentions.goToActivityAndClearTask
import com.oeye.network.Networking
import kotlinx.android.synthetic.main.activity_forgot_password.*
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_signup.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ForgotPasswordScreen : BaseActivity() {
    override fun getLayout(): Int {
        return R.layout.activity_forgot_password
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        imgBack.setOnClickListener {
            finish()
        }

        btnSubmit.setOnClickListener {
            submitcall()
        }
    }

    private fun submitcall() {
        if (!isValidEmail(edtEmailforgot.getValue())) {
            root.showSnackBar(getString(R.string.enter_valid_email))
        } else {

            Networking.with().getServices().forgotPassword(edtEmailforgot.getValue())
                .enqueue(object : Callback<ForgotPassword> {
                    override fun onFailure(call: Call<ForgotPassword>, t: Throwable) {
                        Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
                    }

                    override fun onResponse(
                        call: Call<ForgotPassword>,
                        response: Response<ForgotPassword>
                    ) {
                        if (response.body()?.status == 0) {
                            Toast.makeText(
                                applicationContext,
                                response.body()?.message,
                                Toast.LENGTH_LONG
                            ).show()
                        } else {
                            Toast.makeText(
                                applicationContext,
                                response.body()?.message,
                                Toast.LENGTH_LONG
                            ).show()
                            goToActivityAndClearTask<LoginActivity>()
                        }

                    }

                })
        }

    }


}
