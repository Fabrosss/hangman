package com.example.hangman

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar
import java.lang.StringBuilder

class MainActivity : AppCompatActivity() {
    private var mistakes: Int = 0
    lateinit var stringArray: Array<String>
    private var word: String = " "
    private var wordguess : String = " "
    private var morons : Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        stringArray = resources.getStringArray(R.array.words)
    }
    private fun getRandomword():String{
        val number = (0..stringArray.size).random()
        return stringArray[number]
    }

    fun newWord(view: View) {
        morons = 1
        word = getRandomword()
        wordguess = word
        mistakes = 0
        updateimageview()
        val guess = findViewById<TextView>(R.id.guessTekst)
        guess.text = ""
        val haslo = findViewById<TextView>(R.id.password)2
        var hidden : String = ""
        for (i in 1.. word.length){
            hidden = hidden + "_"
        }
        haslo.text = hidden
    }

    fun checkWord(view: View) {
        if(morons != 0  ){
            val guessword = findViewById<TextView>(R.id.guessTekst)
            if(!(guessword.text.toString().isEmpty())){
                val test = findViewById<Button>(R.id.buttonNewWord)

                if (guessword.text.toString() == wordguess){

                    Snackbar.make(view, "BRAWO", Snackbar.LENGTH_SHORT).show()

                }else{
                    mistakeMade()
                }
            }


        }

    }

    fun checkLetter(view: View) {
        if (morons != 0){
            val guessLetter = findViewById<TextView>(R.id.guessTekst)
            var hidden = findViewById<TextView>(R.id.password)
            if(!(guessLetter.text.toString().isEmpty()))
            {
                if(word.contains(guessLetter.text.toString())){
                    while(word.contains(guessLetter.text.toString())){
                        val index = word.indexOf(guessLetter.text.toString(),0)
                        val stringa = StringBuilder (word)
                        stringa.setCharAt(index,'*')
                        word = stringa.toString()
                        val stringB = StringBuilder(hidden.text)
                        stringB.setCharAt(index, guessLetter.text.toString()[0]) //zamiana ukrytego tekstu na tekst z literami
                        hidden.text = stringB
                    }

                }else{
                    if(mistakes > 11) {
                        Snackbar.make(view, "PRZEGRANA", Snackbar.LENGTH_SHORT).show()
                    }
                    mistakeMade()
                }
        }




        }
    }

    private fun mistakeMade() {
        mistakes++
        if(mistakes < 11 ){
            updateimageview()

        }
    }

    private fun updateimageview() {
        val handmanimage = findViewById<ImageView>(R.id.imagehand)
        handmanimage.setImageResource(updateimage(mistakes))
    }

    private fun updateimage(mistakes : Int): Int{
        return when (mistakes){
            0->R.drawable.hangman0
            1->R.drawable.hangman1
            2->R.drawable.hangman2
            3->R.drawable.hangman3
            4->R.drawable.hangman4
            5->R.drawable.hangman5
            6->R.drawable.hangman6
            7->R.drawable.hangman7
            8->R.drawable.hangman8
            9->R.drawable.hangman9
            10->R.drawable.hangman10
            else -> R.drawable.hangman0

        }
    }
}