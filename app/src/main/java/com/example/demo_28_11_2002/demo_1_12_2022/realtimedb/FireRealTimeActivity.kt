package com.example.demo_28_11_2002.demo_1_12_2022.realtimedb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.demo_28_11_2002.R
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_fire_real_time.*

class FireRealTimeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fire_real_time)
        // Write a message to the database
        btnPushData.setOnClickListener {
            writeData()
        }
        btnReadData.setOnClickListener {
            readDataBase()
        }

    }

    private fun writeData() {
        val database :FirebaseDatabase = FirebaseDatabase.getInstance()
        val myRef = database.getReference("user")
        val user = User(1,"Do Quoc Huy")
        myRef.setValue(user,object : DatabaseReference.CompletionListener{
            override fun onComplete(error: DatabaseError?, ref: DatabaseReference) {
                Toast.makeText(this@FireRealTimeActivity, "Thanh cong", Toast.LENGTH_SHORT).show()
            }

        })
    }
    private fun readDataBase(){
        // Read from the database
        val database = Firebase.database
        val myRef = database.getReference("message")
        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                val value = dataSnapshot.getValue(User::class.java)
                tvResultData.text = "${value?.name}  ${value?.id}"
                Log.d("TAG", "Value is: $value")
            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.w("TAG", "Failed to read value.", error.toException())
            }
        })
    }
}