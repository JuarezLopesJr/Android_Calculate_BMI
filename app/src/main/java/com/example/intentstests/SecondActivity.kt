package com.example.intentstests

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        /*  Kotlin provides reference to the Intent to receive as intent
         val receivedIntentData = intent.getStringExtra("main_activity_data")
         txtSecond.text = receivedIntentData*/

        // using getBundleExtra because i'm sending a Bundle()
        val bundle = intent.getBundleExtra("main_activity_data")
        // getting the key-name to extract data from the Bundle()
        val height = bundle!!.getFloat("height")
        val weight = bundle.getFloat("weight")

        txtSecond.text = "Height: $height | Weight: $weight"

        btnSecond.setOnClickListener {
            // creating new Intent() to be able to send data back
            val intent = Intent()
            val bmi = 703 * (weight / (height * height))
            // loading the result to be passed back to the first Intent() in MainActivity.kt
            intent.putExtra("second_activity_data", bmi)
            // 2 parameters, the activity code and the intent object
            setResult(Activity.RESULT_OK, intent)
            // calling finish() to return the result to the first Intent() in MainActivity.kt
            finish()
        }
    }


}
