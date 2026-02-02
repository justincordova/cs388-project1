package com.example.cs388_project1

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var guessTexts: Array<TextView>
    private lateinit var resultTexts: Array<TextView>
    private lateinit var guessInput: EditText
    private lateinit var submitBtn: Button
    private lateinit var resetBtn: Button
    private lateinit var answerText: TextView

    private var wordToGuess: String = ""
    private var guessCount: Int = 0
    private var isGameOver: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initializeUI()
        wordToGuess = FourLetterWordList.getRandomFourLetterWord()
        setupButtons()
    }

    private fun initializeUI() {
        guessTexts = arrayOf(
            findViewById(R.id.guess1Text),
            findViewById(R.id.guess2Text),
            findViewById(R.id.guess3Text)
        )

        resultTexts = arrayOf(
            findViewById(R.id.result1Text),
            findViewById(R.id.result2Text),
            findViewById(R.id.result3Text)
        )

        guessInput = findViewById(R.id.guessInput)
        submitBtn = findViewById(R.id.submitBtn)
        resetBtn = findViewById(R.id.resetBtn)
        answerText = findViewById(R.id.answerText)
    }

    private fun setupButtons() {
        submitBtn.setOnClickListener {
            handleSubmitGuess()
        }
        resetBtn.setOnClickListener {
            resetGame()
        }
    }

    private fun handleSubmitGuess() {
        if (isGameOver) {
            return
        }

        val userInput = guessInput.text.toString().trim()
        val guess = userInput.uppercase()
        val result = checkGuess(guess)

        guessTexts[guessCount].text = guess
        resultTexts[guessCount].text = result
        guessInput.text.clear()

        if (result == "OOOO") {
            endGame()
            return
        }

        guessCount++

        if (guessCount >= 3) {
            endGame()
        }
    }

    private fun resetGame() {
        wordToGuess = FourLetterWordList.getRandomFourLetterWord()
        guessCount = 0
        isGameOver = false

        guessInput.text.clear()
        submitBtn.isEnabled = true
        answerText.visibility = TextView.GONE
        resetBtn.visibility = TextView.GONE

        for (i in 0..2) {
            guessTexts[i].text = "_ _ _ _"
            resultTexts[i].text = "- - - -"
        }
    }

    private fun endGame() {
        isGameOver = true
        answerText.text = "The word was: $wordToGuess"
        answerText.visibility = TextView.VISIBLE
        submitBtn.isEnabled = false
        resetBtn.visibility = TextView.VISIBLE
    }

    /**
     * Parameters:
     *   guess : String - what user entered as their guess
     *
     * Returns a String of 'O', '+', and 'X', where:
     *   'O' represents right letter in right place
     *   '+' represents right letter in wrong place
     *   'X' represents a letter not in the target word
     */
    private fun checkGuess(guess: String): String {
        var result = ""
        for (i in 0..3) {
            if (guess[i] == wordToGuess[i]) {
                result += "O"
            }
            else if (guess[i] in wordToGuess) {
                result += "+"
            }
            else {
                result += "X"
            }
        }
        return result
    }
}
