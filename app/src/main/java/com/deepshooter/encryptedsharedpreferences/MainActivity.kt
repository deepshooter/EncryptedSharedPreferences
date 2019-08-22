package com.deepshooter.encryptedsharedpreferences

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var mSaveButton: Button
    lateinit var mRetrieveButton: Button
    lateinit var mUsernameEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
        setValues()
    }

    private fun init() {
        mSaveButton = findViewById(R.id.save_bt)
        mRetrieveButton = findViewById(R.id.retrieve_bt)
        mUsernameEditText = findViewById(R.id.username_et)
    }

    private fun setValues() {
        mSaveButton.setOnClickListener(this)
        mRetrieveButton.setOnClickListener(this)
    }

    private fun save() {
        SharedPreferenceUtils.setUsername(mUsernameEditText.text.toString(), this)
        Toast.makeText(applicationContext, getString(R.string.saved), Toast.LENGTH_SHORT).show()
        mUsernameEditText.setText("")
    }


    private fun retrieve() {
        val userName = SharedPreferenceUtils.getUserName(this)
        Toast.makeText(applicationContext, getString(R.string.retrieved_value) + userName, Toast.LENGTH_SHORT).show()
    }

    override fun onClick(v: View?) {
        if (v != null) {
            when (v.id) {
                R.id.save_bt -> save()
                R.id.retrieve_bt -> retrieve()
            }
        }
    }
}
