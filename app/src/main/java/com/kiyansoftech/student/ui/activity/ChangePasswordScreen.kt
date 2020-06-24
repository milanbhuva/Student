package com.kiyansoftech.student.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.contestee.extention.getValue
import com.contestee.extention.isEmpty
import com.contestee.extention.showSnackBar
import com.kiyansoftech.student.Global.BaseActivity
import com.kiyansoftech.student.R
import com.kiyansoftech.student.model.ChangePwd.ChangePassword
import com.kiyansoftech.student.model.Registration.CustomerRegister
import com.oeye.extentions.goToActivityAndClearTask
import com.oeye.network.Networking
import kotlinx.android.synthetic.main.activity_change_password.*
import kotlinx.android.synthetic.main.activity_signup.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ChangePasswordScreen : BaseActivity() {
    override fun getLayout(): Int {
        return  R.layout.activity_change_password
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_change_password)

        btnchangepass.setOnClickListener {

            changepassword()
        }
    }

    private fun changepassword() {
        if (edtoldpass.isEmpty()){
            root.showSnackBar(getString(R.string.enter_passwordold))

        }
        else if(edtnewpass.isEmpty()){
            root.showSnackBar(getString(R.string.enter_passwordnew))
        }else{

            Networking.with().getServices().changePassword(edtfName.getValue(),edtoldpass.getValue(),edtnewpass.getValue())
                .enqueue(object : Callback<ChangePassword> {
                    override fun onFailure(call: Call<ChangePassword>, t: Throwable) {
                        Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()

                    }

                    override fun onResponse(
                        call: Call<ChangePassword>,
                        response: Response<ChangePassword>
                    ) {
                        Toast.makeText(applicationContext, response.body()?.message, Toast.LENGTH_LONG).show()
                        goToActivityAndClearTask<LoginActivity>()

                    }

                })

        }

    }
}
