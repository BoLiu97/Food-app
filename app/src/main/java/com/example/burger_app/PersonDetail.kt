package com.example.burger_app

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import kotlin.math.roundToInt
import kotlinx.android.synthetic.main.activity_person_details.*

class PersonDetail : AppCompatActivity() {
    class Processor {
        fun transform(a: Double, b: Double, operator_lambda: (Int, Int) -> Int): Int {
            val result = operator_lambda(a.roundToInt(), b.roundToInt())
            return result
        }
    }
    fun someFunction():Int{

        val process = Processor()
        val multiply_lambda = { a: Int, b: Int ->
            a * b
        }
        val minus_lambda = { a: Int, b: Int ->
            a - b
        }
        val add_lambda = { a: Int, b: Int ->
            a + b
        }

        val height1 = process.transform(30.84, feetIP.text.toString().toDouble(), multiply_lambda)
        val height2 = process.transform(2.54, inchIP.text.toString().toDouble(), multiply_lambda)
        val height = height1 + height2
        val heightBMR =  process.transform(6.25, height.toDouble(), multiply_lambda)
        val weightBMR =  process.transform(10.0, weightIP.text.toString().toDouble(), multiply_lambda)
        val ageBMR = process.transform(5.0, ageIP.text.toString().toDouble(), multiply_lambda)
        val firstTwo =  process.transform(weightBMR.toDouble(), heightBMR.toDouble(), add_lambda)

        val id = radioGroup.checkedRadioButtonId

        var difMF = 0
        when(id) {
            R.id.maleCB ->  difMF = 5
            R.id.femaleCB -> difMF = -161
        }

        val sum = process.transform(firstTwo.toDouble(), ageBMR.toDouble(), minus_lambda)  +difMF
        return sum
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_person_details)



        updateBK.setOnClickListener{
            /*
            if(feetIP==null||inchIP==null|| weightIP==null||ageIP==null){
                val text = "Invaild input!!!!!!! some number unentered in this page"
                val duration = Toast.LENGTH_SHORT

                val toast = Toast.makeText(applicationContext, text, duration)
                toast.show()
            }else


             */
                if(feetIP.text.toString().toDouble()>8.0||feetIP.text.toString().toDouble()<1.0||
                inchIP.text.toString().toDouble()>12.0||
                weightIP.text.toString().toDouble()>300.0||weightIP.text.toString().toDouble()<5.0||
                ageIP.text.toString().toDouble()>120.0||ageIP.text.toString().toDouble()<1){
                intent.putExtra("wrongMsg", "Invaild input!!!!!!!")
                val text = "Invaild input!!!!!!! in this page"
                val duration = Toast.LENGTH_SHORT

                val toast = Toast.makeText(applicationContext, text, duration)
                toast.show()
            }else{
            val result = someFunction().toString()
            val intent = Intent()
            intent.putExtra("result", result)
            val id = radioGroup.checkedRadioButtonId
            var difMF = ""
            when(id) {
                R.id.maleCB ->  difMF = "Male"
                R.id.femaleCB -> difMF = "Female"
            }
            intent.putExtra("personal_information", "Gender: "+difMF
            + " Age: "+ ageIP.text.toString() + " Weight: "+ weightIP.text.toString()
                    + " Height: "+ feetIP.text.toString() +"' "+ inchIP.text.toString() +"''")
            /*
            if(feetIP.text.toString().toDouble()>8.0||feetIP.text.toString().toDouble()<1.0|| feetIP.text.toString()==null||
                inchIP.text.toString().toDouble()>12.0|| inchIP.text.toString() ==null||
                weightIP.text.toString().toDouble()>300.0||weightIP.text.toString().toDouble()<5.0|| weightIP.text.toString()==null||
                ageIP.text.toString().toDouble()>120.0||ageIP.text.toString().toDouble()<1){
                intent.putExtra("wrongMsg", "Invaild input!!!!!!!")
                val text = "Invaild input!!!!!!! in this page"
                val duration = Toast.LENGTH_SHORT

                val toast = Toast.makeText(applicationContext, text, duration)
                toast.show()
            }

             */
            setResult(Activity.RESULT_OK, intent)
            finish()}
        }


    }

}
