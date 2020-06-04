package com.contestee.extention

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.net.ConnectivityManager
import android.util.Patterns
import android.util.TypedValue
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment

import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout

import java.util.*
import java.util.regex.Pattern


fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.invisible() {
    this.visibility = View.GONE
}

fun View.hide() {
    this.visibility = View.INVISIBLE
}

fun EditText.getValue(): String {
    return this.text.toString().trim()
}

fun TextView.getValue(): String {
    return this.text.toString().trim()
}

fun EditText.isEmpty(): Boolean {
    return this.text.trim().isEmpty()
}
 fun isValidEmail(email: String): Boolean {
    val pattern: Pattern = Patterns.EMAIL_ADDRESS
    return pattern.matcher(email).matches()
}
fun TextView.isEmpty(): Boolean {
    return this.text.isNullOrEmpty()
}

fun View.showSnackBar(msg: String, duration: Int = Snackbar.LENGTH_LONG) {
    val snack = Snackbar.make(this, msg, duration)
    val sbView = snack.view
    val textView = sbView.findViewById<TextView>(com.google.android.material.R.id.snackbar_text)
    textView.setTextColor(Color.WHITE)
    snack.show()
}

fun View.showSnackBar(
    msg: String,
    LENGTH: Int = Snackbar.LENGTH_INDEFINITE,
    action: String
) {
    val snackbar = Snackbar.make(this, msg, LENGTH)
    snackbar.setActionTextColor(Color.WHITE)

    val sbView = snackbar.view
    val textView = sbView.findViewById<TextView>(com.google.android.material.R.id.snackbar_text)
    textView.setTextColor(Color.WHITE)
    snackbar.show()
}

fun Context.showToast(msg: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, msg, duration).show()
}

fun Calendar.isSameDay(newDate: Calendar): Boolean =
    this.get(Calendar.DAY_OF_MONTH) == newDate.get(Calendar.DAY_OF_MONTH)

fun Calendar.isSameMonth(newDate: Calendar): Boolean =
    this.get(Calendar.MONTH) == newDate.get(Calendar.MONTH)


var dialog: AlertDialog? = null
fun AppCompatActivity.showAlert(msg: String, cancelable: Boolean = false) {
    dialog = AlertDialog.Builder(this)
        .setMessage(msg)
        .setCancelable(cancelable)
        .setPositiveButton(
            "OK"
        ) { dialog, which -> dialog.dismiss() }
        .create()
    dialog?.show()
}

fun Fragment.showAlert(msg: String, cancelable: Boolean = false) {
    dismissAlertDialog()
    dialog = AlertDialog.Builder(context)
        .setMessage(msg)
        .setCancelable(cancelable)
        .setPositiveButton(
           "OK"
        ) { dialog, which -> dialog.dismiss() }
        .create()
    dialog?.show()
}

fun dismissAlertDialog() {
    dialog?.dismiss()
}
fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
}
inline
val AppCompatActivity.connectivityManager: ConnectivityManager
    get() = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager