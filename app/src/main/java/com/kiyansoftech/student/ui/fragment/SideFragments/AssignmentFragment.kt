package com.kiyansoftech.student.ui.fragment.SideFragments

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.contestee.extention.hide
import com.contestee.extention.invisible
import com.contestee.extention.visible
import com.kiyansoftech.student.R
import com.kiyansoftech.student.adapter.MyTutoradapter
import com.kiyansoftech.student.model.GetMyTutors.Data
import com.kiyansoftech.student.model.GetMyTutors.GetMyTutor
import com.kiyansoftech.student.model.MyAssignments.MyAssignment
import com.kiyansoftech.student.utils.Utility
import com.oeye.network.Networking
import kotlinx.android.synthetic.main.fragment_assignment.*
import kotlinx.android.synthetic.main.fragment_my_tutor.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AssignmentFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AssignmentFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private val PDF_REQUEST = 212
    private var output: File? = null
    private var imgString = ""
    var userid: String = ""
    var progress: ProgressBar? = null
    var txtfilename: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        userid = this.getArguments()?.getString("userid").toString()

        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_assignment, container, false)
        val lblFileChoose = view.findViewById<TextView>(R.id.lblFileChoose)
        val submit = view.findViewById<TextView>(R.id.lblUpload)
         progress=view.findViewById<ProgressBar>(R.id.progressupload);
        txtfilename=view.findViewById(R.id.filename)

        lblFileChoose.setOnClickListener {

            Utility.checkPermission(context)
            val intent = Intent()
            intent.action = Intent.ACTION_GET_CONTENT
            intent.type = "application/pdf"
            startActivityForResult(intent, PDF_REQUEST)
        }
        submit.setOnClickListener {
            submitassignment()

        }
        return view
    }


    private fun submitassignment() {
        progress?.visible()
        var pdfPerfil: MultipartBody.Part? = null
        if (output != null && output!!.exists()) {
            val requestFile =
                RequestBody.create(MediaType.parse("multipart/form-data"), output)
            // MultipartBody.Part is used to send also the actual file name
            pdfPerfil = MultipartBody.Part.createFormData("assigment", output!!.name, requestFile)

            val requeststudentid: RequestBody =
                RequestBody.create(MediaType.parse("multipart/form-data"), userid)
            val requestassignmentid: RequestBody =
                RequestBody.create(MediaType.parse("multipart/form-data"), userid)

            Networking.with().getServices()
                .submitAssignments(requeststudentid, requestassignmentid, pdfPerfil)
                .enqueue(object : Callback<MyAssignment> {
                    override fun onFailure(call: Call<MyAssignment>, t: Throwable) {
                        Toast.makeText(context, t.message, Toast.LENGTH_LONG).show()
                    }

                    override fun onResponse(
                        call: Call<MyAssignment>,
                        response: Response<MyAssignment>
                    ) {
                        if (response.body()?.status == 0) {
                            progress?.invisible()
                            Toast.makeText(context, response.body()?.message, Toast.LENGTH_LONG)
                                .show()
                        } else {
                            progress?.invisible()

                            Toast.makeText(context, "Assignment uploaded", Toast.LENGTH_LONG)
                                .show()
                        }
                    }
                })

        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == PDF_REQUEST) {
            var selectedImageUri: Uri? = data?.data

            if (selectedImageUri != null) {

//getpath from uri in result
                var result: String? = null
                val proj = arrayOf(MediaStore.Images.Media.DATA)
                val cursor =
                    requireContext().contentResolver.query(selectedImageUri, proj, null, null, null)
                if (cursor != null) {
                    if (cursor.moveToFirst()) {
                        val column_index = cursor.getColumnIndexOrThrow(proj[0])
                        result = cursor.getString(column_index)
                    }
                    cursor.close()
                }
                if (result == null) {
                    result = "Not found"
                } else {
                    imgString = result;
                    output = File(imgString)
                    val upToNCharacters: String =
                        output!!.name.substring(0, Math.min(output!!.name.length, 16))

                    txtfilename?.setText(upToNCharacters)
                }

                Toast.makeText(activity, "" + imgString, Toast.LENGTH_LONG).show()
            }
        }
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment AssignmentFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() =
            AssignmentFragment().apply {

            }
    }
}
