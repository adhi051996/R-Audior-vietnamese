package com.rentokil.pci.rauditor_sg

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class kotlinActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin)

        fun main(args: Array<String>) {
            val a:Int = 5
            val b:Int = 2
            var max: Int

            if (a > b) {
                max = a
            } else {
                max = b
            }
            print("Maximum of a or b is " +max)

            Log.e("HHGGAFAFS","max = "+max);

            // As expression
            // val max = if (a > b) a else b
        }
    }
}
