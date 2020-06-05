package com.kiyansoftech.student.ui.fragment.SideFragments

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.kiyansoftech.student.R
import okhttp3.MultipartBody
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*


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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
        lblFileChoose.setOnClickListener {

            val intent = Intent()
            intent.action = Intent.ACTION_GET_CONTENT
            intent.type = "application/pdf"
            startActivityForResult(intent, PDF_REQUEST)

        }
        return view
    }

    private fun submitassignment() {
        val pdfPerfil: MultipartBody.Part? = null


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == PDF_REQUEST) {
            var selectedImageUri: Uri? = data?.data

            if (selectedImageUri != null) {
              /*  imgString = selectedImageUri.path.toString()
                output = File(imgString)*/

                var result: String? = null
                val proj = arrayOf(MediaStore.Images.Media.DATA)
                val cursor = requireContext().contentResolver.query(selectedImageUri, proj, null, null, null)
                if (cursor != null) {
                    if (cursor.moveToFirst()) {
                        val column_index = cursor.getColumnIndexOrThrow(proj[0])
                        result = cursor.getString(column_index)
                    }
                    cursor.close()
                }
                if (result == null) {
                    result = "Not found"
                }else{
                    imgString=result;
                    output=File(imgString)
                }

                Toast.makeText(activity,""+imgString,Toast.LENGTH_LONG).show()
            }
        }
    }

    @Throws(IOException::class)
    fun createpdfFile(context: Context): File? {
        // Create an image file name
        val timeStamp =
            SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val imageFileName = "PDF_" + timeStamp + "_"
        val storageDir =
            context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        // Save a file: path for use with ACTION_VIEW intents
        //  mCurrentPhotoPath = image.getAbsolutePath();
        return File.createTempFile(
            imageFileName,  /* prefix */
            ".pdf",  /* suffix */
            storageDir /* directory */
        )
    }

    private fun getPathDeprecated(ctx: Context, uri: Uri?): String? {
        if (uri == null) {
            return null
        }
        val projection = arrayOf(MediaStore.Images.Media.DATA)
        val cursor = ctx.contentResolver.query(uri, projection, null, null, null)
        if (cursor != null) {
            val column_index = cursor
                .getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
            cursor.moveToFirst()
            return cursor.getString(column_index)
        }
        return uri.path
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
