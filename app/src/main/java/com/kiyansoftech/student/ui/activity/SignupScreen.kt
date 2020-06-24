package com.kiyansoftech.student.ui.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.Bohni.Network.RetrofitClientInstance
import com.contestee.extention.*
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.iid.FirebaseInstanceId
import com.kiyansoftech.student.Global.BaseActivity
import com.kiyansoftech.student.R
import com.kiyansoftech.student.model.Registration.CustomerRegister
import com.kiyansoftech.student.utils.ApiInterface
import com.oeye.extentions.goToActivity
import com.oeye.extentions.goToActivityAndClearTask
import com.oeye.network.Networking
import kotlinx.android.synthetic.main.activity_signup.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SignupScreen : BaseActivity() {


    override fun getLayout(): Int {
        return R.layout.activity_signup
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        var name = edtfName.text;
        var email = edtEmail.text;
        var password = edtPassword.text;

        btnSignup.setOnClickListener {

            validation();
           // startActivity(Intent(this, Dashboard::class.java))

           /* val retrofit = RetrofitClientInstance.retrofitInstance;
            val apiInterface = retrofit?.create(ApiInterface::class.java)

            val call = apiInterface?.makeStudentRegistration(name.toString(),email.toString(),password.toString())
            val result = call?.execute()?.body();



            Toast.makeText(this,name,Toast.LENGTH_LONG).show();*/
        }

        txtLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }


    private fun validation() {
        if (edtfName.isEmpty()) {
            root.showSnackBar(getString(R.string.enter_name))
        } else if (edtEmail.isEmpty()) {
            root.showSnackBar(getString(R.string.enter_email))
        } else if (!isValidEmail(edtEmail.getValue())) {
            root.showSnackBar(getString(R.string.enter_valid_email))
        } else if (edtPassword.isEmpty()) {
            root.showSnackBar(getString(R.string.enter_password))
        } else if (edtPassword.getValue() != edtPassword.getValue()) {
            root.showSnackBar(getString(R.string.confirm_password))
        } else {

            Networking.with().getServices().makeStudentRegistration(edtfName.getValue(),edtEmail.getValue(),edtPassword.getValue())
                .enqueue(object : Callback<CustomerRegister>{
                    override fun onFailure(call: Call<CustomerRegister>, t: Throwable) {
                        Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()

                    }

                    override fun onResponse(
                        call: Call<CustomerRegister>,
                        response: Response<CustomerRegister>
                    ) {
                        Toast.makeText(applicationContext, response.body()?.message, Toast.LENGTH_LONG).show()
                        goToActivityAndClearTask<LoginActivity>()

                    }

                })

//            register(token.toString())

/*
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
                })
*/

        }

    }
    fun register(){

    }

}
