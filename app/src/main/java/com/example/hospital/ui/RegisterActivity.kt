package com.example.hospital.ui

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.hospital.R
import com.example.hospital.databinding.ActivityRegisterBinding
import com.example.hospital.model.register.RegisterModel
import com.example.hospital.model.viewModel.UserViewModel
import java.util.Calendar

class RegisterActivity : AppCompatActivity(R.layout.activity_register) {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var viewModel: UserViewModel

    private val calendar: Calendar = Calendar.getInstance()
    private val year_: Int = calendar.get(Calendar.YEAR)
    private val month_: Int = calendar.get(Calendar.MONTH)
    private val day_: Int = calendar.get(Calendar.DAY_OF_MONTH)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        binding.register.setOnClickListener {


            if (binding.regName.text.toString().trim().isEmpty()) {
                binding.regName.requestFocus()
            } else if (binding.regPhone.text.toString().trim().isEmpty()) {

                binding.regPhone.requestFocus()
            } else if (binding.regEmail.text.toString().trim().isEmpty()) {

                binding.regEmail.requestFocus()
            } else if (binding.regTitle.text.toString().trim().isEmpty()) {

                binding.regTitle.requestFocus()
            } else if (binding.regDate.text.toString().trim().isEmpty()) {

                binding.regDate.requestFocus()
            }else {
                submit()
            }
        }

    }


    private fun activity() {
        Toast.makeText(this, "تم تسجيل الحساب بنجاح", Toast.LENGTH_SHORT).show()
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }


    fun regData(view: View) {
        val datePickerDialog = DatePickerDialog(
            this,
            { view, year, month, day ->
                binding.regDate.setText("$year-" + 1.plus(month).toString() + "-$day")
            }, year_, month_, day_
        )
        datePickerDialog.setTitle("تاريخ الميلاد")
        datePickerDialog.show()
    }


    private fun submit() {

        val name = binding.regName.text.toString().trim()
        val phone = binding.regPhone.text.toString().trim()
        val email = binding.regEmail.text.toString().trim()
        val title = binding.regTitle.text.toString().trim()
        val date = binding.regDate.text.toString().trim()
        viewModel.patientData.observe(this, Observer { response ->
            Log.e("response : ", response.toString())
            activity()
        })
        viewModel.register(RegisterModel(name, title, phone, email, date))

    }


}