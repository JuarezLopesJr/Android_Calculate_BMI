package com.example.intentstests

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val SECOND_ACTIVITY = 1000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*btnMain.setOnClickListener {
            getting the value typed, passing toString() method because the default return is Editable or CharSequence
            val dataIntent = txtMain.text.toString()
            val intent = Intent(this@MainActivity, SecondActivity::class.java)

            intent.putExtra("main_activity_data", dataIntent)
            startActivity(intent)
        }*/

        btnCalculate.setOnClickListener {
            val intent = Intent(this@MainActivity, SecondActivity::class.java)
            val bundle = Bundle()

            bundle.putFloat("height", inputHeight.text.toString().toFloat())
            bundle.putFloat("weight", inputWeight.text.toString().toFloat())

            intent.putExtra("main_activity_data", bundle)
            startActivityForResult(intent, SECOND_ACTIVITY)
        }
    }

    private fun clearInputs() {
        inputHeight.setText("")
        inputWeight.setText("")
    }

    override fun onResume() {
        super.onResume()
        clearInputs()
    }

    private fun getBMIDescription(bmi: Float): String {
        return when (bmi) {
            in 1.0..18.5 -> "Underweight"
            in 18.6..24.9 -> "Normal weight"
            in 25.0..29.9 -> "Overweight"
            else -> "Obese"
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if ((requestCode == SECOND_ACTIVITY) && (resultCode == Activity.RESULT_OK)) {
            val bmi = data!!.getFloatExtra("second_activity_data", 1.0F)
            val bmiString = "%.2f".format(bmi)

            inputHeight.setText("")
            inputWeight.setText("")

            txtMain.text = "BMI : $bmiString ${getBMIDescription(bmi)}"
        }
    }

}
