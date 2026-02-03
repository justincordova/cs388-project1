package com.example.cs388_project1

import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.graphics.Color

class MainActivity : AppCompatActivity() {
    private lateinit var guessTexts: Array<TextView>
    private lateinit var guessInput: EditText
    private lateinit var submitBtn: Button
    private lateinit var resetBtn: Button
    private lateinit var answerText: TextView
    private lateinit var streakText: TextView

    private var wordToGuess: String = ""
    private var streak: Int = 0
    private var guessCount: Int = 0
    private var isGameOver: Boolean = false
    private var wordListMode: Int = 0
    private lateinit var wordListToggleBtn: Button
    private val wordListModes = listOf("Normal", "Easy", "Hard")

    companion object {
        private const val COLOR_CORRECT = "#4CAF50"      // Green - right letter, right place
        private const val COLOR_PRESENT = "#FF9800"      // Orange - right letter, wrong place
        private const val COLOR_ABSENT = "#757575"       // Gray - letter not in word
        private const val COLOR_DEFAULT = "#000000"      // Black - default text color
    }

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

        guessInput = findViewById(R.id.guessInput)
        submitBtn = findViewById(R.id.submitBtn)
        resetBtn = findViewById(R.id.resetBtn)
        wordListToggleBtn = findViewById(R.id.wordListToggleBtn)
        answerText = findViewById(R.id.answerText)
        streakText = findViewById(R.id.streakText)
    }

    private fun setupButtons() {
        submitBtn.setOnClickListener {
            handleSubmitGuess()
        }
        resetBtn.setOnClickListener {
            resetGame()
        }
        wordListToggleBtn.setOnClickListener {
            wordListMode = (wordListMode + 1) % 3
            wordListToggleBtn.text = "Word List: ${wordListModes[wordListMode]}"
            wordToGuess = FourLetterWordList.getRandomFourLetterWord(wordListMode)
            resetGame()
        }
    }

    private fun handleSubmitGuess() {
        if (isGameOver) {
            Toast.makeText(this, "Game is over! Press New Game to play again.", Toast.LENGTH_SHORT).show()
            return
        }

        val userInput = guessInput.text.toString().trim()

        if (userInput.isEmpty()) {
            Toast.makeText(this, "Please enter a word", Toast.LENGTH_SHORT).show()
            return
        }

        if (userInput.length != 4) {
            Toast.makeText(this, "Please enter exactly 4 letters", Toast.LENGTH_SHORT).show()
            return
        }

        if (!userInput.all { it.isLetter() }) {
            Toast.makeText(this, "Only letters are allowed", Toast.LENGTH_SHORT).show()
            return
        }

        val guess = userInput.uppercase()

        guessTexts[guessCount].text = checkGuess(guess, wordToGuess)

        guessInput.text.clear()
        guessInput.hideKeyboard()

        if (guess == wordToGuess) {
            endGame(won = true)
            return
        }

        guessCount++

        if (guessCount >= 3) {
            endGame(won = false)
        }
    }

    private fun resetGame() {
        wordToGuess = FourLetterWordList.getRandomFourLetterWord(wordListMode)
        guessCount = 0
        isGameOver = false

        guessInput.text.clear()
        submitBtn.isEnabled = true
        guessInput.isEnabled = true
        answerText.visibility = TextView.GONE
        resetBtn.visibility = TextView.GONE

        for (i in 0..2) {
            guessTexts[i].text = "_ _ _ _"
        }
        guessInput.requestFocus()
    }

    private fun endGame(won: Boolean = false) {
        isGameOver = true
        answerText.text = "The word was: $wordToGuess"
        answerText.visibility = TextView.VISIBLE
        submitBtn.isEnabled = false
        resetBtn.visibility = TextView.VISIBLE
        guessInput.isEnabled = false

        if (won) {
            streak++
            streakText.text = "Streak: $streak"
            answerText.setTextColor(Color.parseColor("#4CAF50"))
        } else {
            streak = 0
            streakText.text = "Streak: $streak"
        }
    }
    
    private fun checkGuess(guess: String, targetWord: String): SpannableString {
        val spannable = SpannableString(guess)

        for (i in guess.indices) {
            val guessLetter = guess[i]
            val targetLetter = targetWord[i]
            val color = when {
                guessLetter == targetLetter -> {
                    COLOR_CORRECT
                }
                guessLetter in targetWord -> {
                    COLOR_PRESENT
                }
                else -> {
                    COLOR_ABSENT
                }
            }

            spannable.setSpan(
                ForegroundColorSpan(Color.parseColor(color)),
                i,
                i + 1,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )
        }

        return spannable
    }

    private fun EditText.hideKeyboard() {
        val imm = context.getSystemService(android.content.Context.INPUT_METHOD_SERVICE) as android.view.inputmethod.InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }
}
