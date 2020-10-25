package com.example.dicer

import android.content.Context
import android.media.MediaPlayer
import android.os.*
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val rollButton: Button = findViewById(R.id.button) //getting button instance
        val mp=MediaPlayer.create(this,R.raw.roll) //created a media instance to roll.mp3

        rollButton.setOnClickListener {
            vibrate()
            mp.start() //starting media
            //  val toast = Toast.makeText(this, "Dice Rolled!!", Toast.LENGTH_SHORT).show() //displaying toast on button click
            rollDice()  //calling rollDevice
        }

        rollDice() // roll dice at start
    }

    private fun rollDice() {
        /*roll device class*/
        val dice = Dice(6)   //creating dice object
        //val resultTextView: TextView = findViewById(R.id.textView)  //getting textview instance
        val diceView:ImageView=findViewById(R.id.imageView)  //getting app's image view instance

        val value=dice.roll()   //random dice value
        when (value) {
            //1,6 are lucky numbers so displaying a toast greeting
            1,6 -> Toast.makeText(this, "KUDOS! You got Lucky no: $value", Toast.LENGTH_SHORT).show()
        }
        //resultTextView.text =value.toString() //getting roll result and converting them to string

        val diceId=when(value){        //fetching image resource id to respective value
            1->R.drawable.dice_1
            2->R.drawable.dice_2
            3->R.drawable.dice_3
            4->R.drawable.dice_4
            5->R.drawable.dice_5
            else->R.drawable.dice_6
        }
        diceView.setImageResource(diceId)     //setting that image resource to image view
        diceView.contentDescription=value.toString()  //setting content description to the value of dice
    }

    private fun vibrate(){ //copied from stackoverflow
        val vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        if (vibrator.hasVibrator()) { // Vibrator availability checking
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                vibrator.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE)) // New vibrate method for API Level 26 or higher
            } else {
                vibrator.vibrate(500) // Vibrate method for below API Level 26
            }
        }
    }
}

class Dice(val sides: Int) {
    /*Dice Class*/
    fun roll(): Int {
        return (1..sides).random()   //return random values from IntRange
    }
}

