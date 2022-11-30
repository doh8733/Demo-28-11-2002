package com.example.demo_28_11_2002

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.Dialog
import android.app.TimePickerDialog
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.CalendarContract
import android.view.LayoutInflater
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_picker.*
import kotlinx.android.synthetic.main.dialog_view.*
import java.text.SimpleDateFormat
import java.util.*

class PickerActivity : AppCompatActivity() {
    private lateinit var cal : Calendar
    private lateinit var alertDialog: AlertDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_picker)
        cal = Calendar.getInstance()
        timePicker()
        datePicker()
        spinner()
        tvAlertDialog.setOnClickListener {
            alertDialog()
        }
        tvDialog.setOnClickListener {
            openDialog()
        }
        tvNextActivity.setOnClickListener {
            startActivity(Intent(this,ListviewActivity::class.java))
        }
    }
    private fun timePicker(){
        val timeSetListener =
            TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
                cal.set(Calendar.HOUR_OF_DAY,hourOfDay)
                cal.set(Calendar.MINUTE,minute)
                tvTime.text = SimpleDateFormat("HH:mm").format(cal.time)
                btnTime.text = SimpleDateFormat("HH:mm").format(cal.time)
            }
        btnTime.setOnClickListener {
            TimePickerDialog(this,timeSetListener,cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), true).show()
        }
    }
    private fun datePicker(){
        val year = cal[Calendar.YEAR]
        val month = cal[Calendar.MONTH]
        val day = cal[Calendar.DAY_OF_MONTH]
        val date = DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
            month+1
            val date = makeDateStringFormat(dayOfMonth, month, year)
            tvDate.text = date
            btnDate.text = date
        }
        btnDate.setOnClickListener {
            DatePickerDialog(this,date,year,month,day).show()
        }
    }
    private val format: SimpleDateFormat = SimpleDateFormat("dd-MM-yyyy")
    private fun makeDateStringFormat(dayOfMonth: Int, month: Int, year: Int): String {
        val calendar = Calendar.getInstance()
        calendar.set(year, month, dayOfMonth)
        return format.format(calendar.time)
    }

    private fun spinner(){
        spnPicker.adapter = SelectSpinnerAdapter(this,R.layout.item_category,getAllName()) {
            tvSpinner.text = it.name
        }
    }

    private fun getAllName(): MutableList<NoMore> {
        val list = mutableListOf<NoMore>()
        list.add(NoMore("PHP"))
        list.add(NoMore("C++"))
        list.add(NoMore("C#"))
        list.add(NoMore("JAVA"))
        list.add(NoMore("KOTLIN"))
        return list
    }

    //dialog 30/11/2022
    private fun alertDialog(){
        val build = AlertDialog.Builder(this)
        build.setTitle("Xin chào ngày mới")
        build.setMessage("Chúc bạn một ngày mới vui vẻ")
        build.setNegativeButton("No"){_,_->
            alertDialog.dismiss()
        }.setPositiveButton("OK"){_,_->
            Toast.makeText(this@PickerActivity, "Ok", Toast.LENGTH_SHORT).show()
            alertDialog.dismiss()
        }
        alertDialog = build.create()
        alertDialog.show()
    }
    private fun openDialog(){
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.dialog_view)
        dialog.setCancelable(false)
        dialog.window?.decorView?.setBackgroundColor(Color.TRANSPARENT)
        dialog.tvAgree.setOnClickListener {
            Toast.makeText(this, "Agree", Toast.LENGTH_SHORT).show()
            dialog.dismiss()
        }
        dialog.tvClose.setOnClickListener {
            Toast.makeText(this, "Close", Toast.LENGTH_SHORT).show()
            dialog.dismiss()
        }
        dialog.create()
        dialog.show()
    }
}