package com.example.demo_28_11_2002.demo_1_12_2022.task2_firebase.firestorage

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.LinkAddress
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.demo_28_11_2002.R
import com.google.android.gms.auth.api.signin.internal.Storage
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.android.synthetic.main.activity_fire_storage.*
import kotlinx.android.synthetic.main.row_image.*
import java.io.ByteArrayOutputStream
import java.util.*
import kotlin.math.log

class FireStorageActivity : AppCompatActivity() {
    val storage = FirebaseStorage.getInstance()
    private val CODE = 1
    private lateinit var recyclerImageAdapter :RecyclerImageAdapter
    private lateinit var listImg : MutableList<Image>
    private lateinit var uri : Uri
    private lateinit var  storageReference : StorageReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fire_storage)
        btnChoseImage.setOnClickListener {
            selectImage()
        }
        btnPushImage.setOnClickListener {
            uploadImage()
        }
    }

    private fun uploadImage() {
        val cal = Calendar.getInstance()
        storageReference = FirebaseStorage.getInstance().reference
        storageReference = FirebaseStorage.getInstance().getReference("image/${cal.time}.png")
        storageReference.putFile(uri).addOnSuccessListener {
            imageStorage.setImageURI(null)
            Toast.makeText(this, "Ok", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener{
            Toast.makeText(this, "$it", Toast.LENGTH_SHORT).show()
            Log.e("TAG", "uploadImage: $it", )


        }
    }

    private fun selectImage(){
        val i = Intent()
        i.type = "image/"
        i.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(i,1000)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1000 && data?.data != null){
            uri = data.data!!
            imageStorage.setImageURI(uri)
        }
    }
//        imageViewCamera.setOnClickListener {
//            val i = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
//            startActivityForResult(i,CODE)
//        }
//        btnSaveFireStorage.setOnClickListener {
//            // Get the data from an ImageView as bytes
//            val cal = Calendar.getInstance()
//            val mountainsRef = storageRef.child("image${cal.time}.jpg")
//            imageViewCamera.isDrawingCacheEnabled = true
//            imageViewCamera.buildDrawingCache()
//            val bitmap = (imageViewCamera.drawable as BitmapDrawable).bitmap
//            val baos = ByteArrayOutputStream()
//            bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos)
//            val data = baos.toByteArray()
//
//            var uploadTask = mountainsRef.putBytes(data)
//            uploadTask.addOnFailureListener {
//                // Handle unsuccessful uploads
//                Toast.makeText(this, "Loi", Toast.LENGTH_SHORT).show()
//                Log.e("TAG", "onCreate: $it", )
//            }.addOnSuccessListener { taskSnapshot ->
//                // taskSnapshot.metadata contains file metadata such as size, content-type, etc.
//                // ...
//                val downUrl = taskSnapshot.storage.downloadUrl
//                Toast.makeText(this, "Thanh cong", Toast.LENGTH_SHORT).show()
//                Log.e("TAG", "onCreate: $downUrl", )
//            }
//        }
//    }
//
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//       if (requestCode == CODE && resultCode == RESULT_OK && data != null){
//           val bitMap = data.extras!!["data"] as Bitmap
//           imageViewCamera.setImageBitmap(bitMap)
//       }
//        super.onActivityResult(requestCode, resultCode, data)
//    }
}