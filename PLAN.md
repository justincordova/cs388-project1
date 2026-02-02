# Wordle Clone Implementation Plan

> **For Claude:** REQUIRED SUB-SKILL: Use superpowers:executing-plans to implement this plan task-by-task.

**Goal:** Build a simple Android Wordle clone with 3 chances to guess a 4-letter word using Kotlin and ConstraintLayout.

**Architecture:** Single Activity Android app with XML layout, using helper classes for word list and guess validation, simple state management with arrays for UI elements.

**Tech Stack:** Kotlin, Android SDK, ConstraintLayout, EditText, TextView, Button

---

### Task 1: Create FourLetterWordList Helper Class

**Files:**

- Create: `app/src/main/java/com/example/cs388_project1/FourLetterWordList.kt`

**Step 1: Create the file with helper code**

```kotlin
// author: calren
object FourLetterWordList {
    val fourLetterWords =
        "Area,Army,Baby,Back,Ball,Band,Bank,Base,Bill,Body,Book,Call,Card,Care,Case,Cash,City,Club,Cost,Date,Deal,Door,Duty,East,Edge,Face,Fact,Farm,Fear,File,Film,Fire,Firm,Fish,Food,Foot,Form,Fund,Game,Girl,Goal,Gold,Hair,Half,Hall,Hand,Head,Help,Hill,Home,Hope,Hour,Idea,Jack,John,Kind,King,Lack,Lady,Land,Life,Line,List,Look,Lord,Loss,Love,Mark,Mary,Mind,Miss,Move,Name,Need,News,Note,Page,Pain,Pair,Park,Part,Past,Path,Paul,Plan,Play,Post,Race,Rain,Rate,Rest,Rise,Risk,Road,Rock,Role,Room,Rule,Sale,Seat,Shop,Show,Side,Sign,Site,Size,Skin,Sort,Star,Step,Task,Team,Term,Test,Text,Time,Tour,Town,Tree,Turn,Type,Unit,User,View,Wall,Week,West,Wife,Will,Wind,Wine,Wood,Word,Work,Year,Bear,Beat,Blow,Burn,Call,Care,Cast,Come,Cook,Cope,Cost,Dare,Deal,Deny,Draw,Drop,Earn,Face,Fail,Fall,Fear,Feel,Fill,Find,Form,Gain,Give,Grow,Hang,Hate,Have,Head,Hear,Help,Hide,Hold,Hope,Hurt,Join,Jump,Keep,Kill,Know,Land,Last,Lead,Lend,Lift,Like,Link,Live,Look,Lose,Love,Make,Mark,Meet,Mind,Miss,Move,Must,Name,Need,Note,Open,Pass,Pick,Plan,Play,Pray,Pull,Push,Read,Rely,Rest,Ride,Ring,Rise,Risk,Roll,Rule,Save,Seek,Seem,Sell,Send,Shed,Show,Shut,Sign,Sing,Slip,Sort,Stay,Step,Stop,Suit,Take,Talk,Tell,Tend,Test,Turn,Vary,View,Vote,Wait,Wake,Walk,Want,Warn,Wash,Wear,Will,Wish,Work,Able,Back,Bare,Bass,Blue,Bold,Busy,Calm,Cold,Cool,Damp,Dark,Dead,Deaf,Dear,Deep,Dual,Dull,Dumb,Easy,Evil,Fair,Fast,Fine,Firm,Flat,Fond,Foul,Free,Full,Glad,Good,Grey,Grim,Half,Hard,Head,High,Holy,Huge,Just,Keen,Kind,Last,Late,Lazy,Like,Live,Lone,Long,Loud,Main,Male,Mass,Mean,Mere,Mild,Nazi,Near,Neat,Next,Nice,Okay,Only,Open,Oral,Pale,Past,Pink,Poor,Pure,Rare,Real,Rear,Rich,Rude,Safe,Same,Sick,Slim,Slow,Soft,Sole,Sore,Sure,Tall,Then,Thin,Tidy,Tiny,Tory,Ugly,Vain,Vast,Very,Vice,Warm,Wary,Weak,Wide,Wild,Wise,Zero,Ably,Afar,Anew,Away,Back,Dead,Deep,Down,Duly,Easy,Else,Even,Ever,Fair,Fast,Flat,Full,Good,Half,Hard,Here,High,Home,Idly,Just,Late,Like,Live,Long,Loud,Much,Near,Nice,Okay,Once,Only,Over,Part,Past,Real,Slow,Solo,Soon,Sure,That,Then,This,Thus,Very,When,Wide"

    fun getAllFourLetterWords(): List<String> {
        return fourLetterWords.split(",")
    }

    fun getRandomFourLetterWord(): String {
        val allWords = getAllFourLetterWords()
        val randomNumber = (0..allWords.size).shuffled().last()
        return allWords[randomNumber].uppercase()
    }
}
```

**Step 2: Verify file created successfully**

Run: `ls -la app/src/main/java/com/example/cs388_project1/`
Expected: See FourLetterWordList.kt in the listing

**Step 3: Build to verify no errors**

Run: `./gradlew build`
Expected: Build succeeds with no compilation errors

**Step 4: Commit**

```bash
git add app/src/main/java/com/example/cs388_project1/FourLetterWordList.kt
git commit -m "feat: add FourLetterWordList helper class"
```

---

### Task 2: Lock Screen Orientation

**Files:**

- Modify: `app/src/main/AndroidManifest.xml`

**Step 1: Add portrait orientation to activity**

```xml
<activity
    android:name=".MainActivity"
    android:screenOrientation="portrait"
    android:exported="true">
```

**Step 2: Build to verify manifest changes**

Run: `./gradlew build`
Expected: Build succeeds with no manifest errors

**Step 3: Commit**

```bash
git add app/src/main/AndroidManifest.xml
git commit -m "feat: lock screen orientation to portrait"
```

---

### Task 3: Create Basic XML Layout

**Files:**

- Modify: `app/src/main/res/layout/activity_main.xml`

**Step 1: Replace existing layout with Wordle layout**

```xml
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/main"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:padding="16dp"
    tools:context=".MainActivity">

    <TextView
        android:id="@+id/titleText"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Wordle"
        android:textSize="32sp"
        android:textStyle="bold"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent" />

    <TextView
        android:id="@+id/answerText"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginTop="24dp"
        android:text=""
        android:textSize="20sp"
        android:textStyle="bold"
        android:visibility="gone"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@id/titleText" />

    <TextView
        android:id="@+id/guess1Text"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginTop="32dp"
        android:text="_ _ _ _"
        android:textSize="18sp"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@id/answerText" />

    <TextView
        android:id="@+id/result1Text"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginTop="8dp"
        android:text="- - - -"
        android:textSize="16sp"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@id/guess1Text" />

    <TextView
        android:id="@+id/guess2Text"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginTop="16dp"
        android:text="_ _ _ _"
        android:textSize="18sp"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@id/result1Text" />

    <TextView
        android:id="@+id/result2Text"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginTop="8dp"
        android:text="- - - -"
        android:textSize="16sp"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@id/guess2Text" />

    <TextView
        android:id="@+id/guess3Text"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginTop="16dp"
        android:text="_ _ _ _"
        android:textSize="18sp"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@id/result2Text" />

    <TextView
        android:id="@+id/result3Text"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginTop="8dp"
        android:text="- - - -"
        android:textSize="16sp"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@id/guess3Text" />

    <EditText
        android:id="@+id/guessInput"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:layout_marginTop="32dp"
        android:hint="Enter 4-letter word"
        android:inputType="textCapCharacters"
        android:maxLength="4"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@id/result3Text" />

    <Button
        android:id="@+id/submitBtn"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginTop="16dp"
        android:text="Submit"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@id/guessInput" />

    <Button
        android:id="@+id/resetBtn"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginTop="8dp"
        android:text="New Game"
        android:visibility="gone"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@id/submitBtn" />

    <TextView
        android:id="@+id/instructionsText"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginTop="16dp"
        android:text="Type a 4-letter word and press Submit"
        android:textSize="14sp"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@id/resetBtn" />

</androidx.constraintlayout.widget.ConstraintLayout>
```

**Step 2: Build project to verify layout**

Run: `./gradlew build`
Expected: Build succeeds with no layout errors

**Step 3: Commit**

```bash
git add app/src/main/res/layout/activity_main.xml
git commit -m "feat: design Wordle game layout with reset button"
```

---

### Task 4: Add Reset Button to Layout

**Files:**

- Modify: `app/src/main/res/layout/activity_main.xml`

**Step 1: Add reset button between submit and instructions**

```xml
    <Button
        android:id="@+id/resetBtn"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginTop="8dp"
        android:text="New Game"
        android:visibility="gone"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@id/submitBtn" />
```

**Step 2: Update instructions constraint**

Change instructions constraint from `app:layout_constraintTop_toBottomOf="@id/submitBtn"` to `app:layout_constraintTop_toBottomOf="@id/resetBtn"`

**Step 3: Build to verify layout changes**

Run: `./gradlew build`
Expected: Build succeeds with no layout errors

**Step 4: Commit**

```bash
git add app/src/main/res/layout/activity_main.xml
git commit -m "feat: add reset button to layout"
```

---

### Task 5: Create MainActivity Structure

**Files:**

- Modify: `app/src/main/java/com/example/cs388_project1/MainActivity.kt`

**Step 1: Create basic MainActivity structure**

```kotlin
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
}
```

**Step 2: Build to verify structure**

Run: `./gradlew build`
Expected: Build succeeds with no compilation errors

**Step 3: Commit**

```bash
git add app/src/main/java/com/example/cs388_project1/MainActivity.kt
git commit -m "feat: create MainActivity basic structure"
```

---

### Task 6: Implement UI Initialization

**Files:**

- Modify: `app/src/main/java/com/example/cs388_project1/MainActivity.kt`

**Step 1: Add initializeUI() method**

```kotlin
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
```

**Step 2: Call initializeUI() in onCreate()**

Add `initializeUI()` call after `setContentView()` in onCreate()

**Step 3: Build to verify**

Run: `./gradlew build`
Expected: Build succeeds

**Step 4: Commit**

```bash
git add app/src/main/java/com/example/cs388_project1/MainActivity.kt
git commit -m "feat: implement UI initialization"
```

---

### Task 7: Implement Button Setup

**Files:**

- Modify: `app/src/main/java/com/example/cs388_project1/MainActivity.kt`

**Step 1: Add setupButtons() method**

```kotlin
    private fun setupButtons() {
        submitBtn.setOnClickListener {
            handleSubmitGuess()
        }
        resetBtn.setOnClickListener {
            resetGame()
        }
    }
```

**Step 2: Call setupButtons() in onCreate()**

Add `setupButtons()` call after `wordToGuess` initialization in onCreate()

**Step 3: Build to verify**

Run: `./gradlew build`
Expected: Build succeeds

**Step 4: Commit**

```bash
git add app/src/main/java/com/example/cs388_project1/MainActivity.kt
git commit -m "feat: implement button click handlers"
```

---

### Task 8: Implement checkGuess Function

**Files:**

- Modify: `app/src/main/java/com/example/cs388_project1/MainActivity.kt`

**Step 1: Add checkGuess() method**

```kotlin
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
```

**Step 2: Build to verify**

Run: `./gradlew build`
Expected: Build succeeds

**Step 3: Commit**

```bash
git add app/src/main/java/com/example/cs388_project1/MainActivity.kt
git commit -m "feat: implement checkGuess function"
```

---

### Task 9: Implement Basic Game Logic

**Files:**

- Modify: `app/src/main/java/com/example/cs388_project1/MainActivity.kt`

**Step 1: Add handleSubmitGuess() basic structure**

```kotlin
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
```

**Step 2: Add resetGame() basic structure**

```kotlin
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
```

**Step 3: Add endGame() basic structure**

```kotlin
    private fun endGame() {
        isGameOver = true
        answerText.text = "The word was: $wordToGuess"
        answerText.visibility = TextView.VISIBLE
        submitBtn.isEnabled = false
        resetBtn.visibility = TextView.VISIBLE
    }
```

**Step 4: Build to verify**

Run: `./gradlew build`
Expected: Build succeeds

**Step 5: Commit**

```bash
git add app/src/main/java/com/example/cs388_project1/MainActivity.kt
git commit -m "feat: implement basic game logic"
```

---

### Task 10: Add Input Validation

**Files:**

- Modify: `app/src/main/java/com/example/cs388_project1/MainActivity.kt`

**Step 1: Add validation to handleSubmitGuess()**

Replace beginning of handleSubmitGuess() with:

```kotlin
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
```

**Step 2: Build to verify**

Run: `./gradlew build`
Expected: Build succeeds

**Step 3: Commit**

```bash
git add app/src/main/java/com/example/cs388_project1/MainActivity.kt
git commit -m "feat: add input validation with edge case handling"
```

---

### Task 11: Add Keyboard Hiding

**Files:**

- Modify: `app/src/main/java/com/example/cs388_project1/MainActivity.kt`

**Step 1: Add hideKeyboard() extension function**

```kotlin
    private fun EditText.hideKeyboard() {
        val imm = context.getSystemService(android.content.Context.INPUT_METHOD_SERVICE) as android.view.inputmethod.InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }
```

**Step 2: Call hideKeyboard() after clearing input**

Add `guessInput.hideKeyboard()` after `guessInput.text.clear()` in handleSubmitGuess()

**Step 3: Build to verify**

Run: `./gradlew build`
Expected: Build succeeds

**Step 4: Commit**

```bash
git add app/src/main/java/com/example/cs388_project1/MainActivity.kt
git commit -m "feat: add keyboard hiding for better UX"
```

---

### Task 12: Add User Feedback Messages

**Files:**

- Modify: `app/src/main/java/com/example/cs388_project1/MainActivity.kt`

**Step 1: Add Toast messages to game logic**

Add to handleSubmitGuess():

- After checking "OOOO": `Toast.makeText(this, "Congratulations! You won!", Toast.LENGTH_LONG).show()`
- After checking guessCount >= 3: `Toast.makeText(this, "Game Over! No more guesses.", Toast.LENGTH_LONG).show()`

**Step 2: Build to verify**

Run: `./gradlew build`
Expected: Build succeeds

**Step 3: Commit**

```bash
git add app/src/main/java/com/example/cs388_project1/MainActivity.kt
git commit -m "feat: add user feedback Toast messages"
```

---

### Task 13: Add Focus Management

**Files:**

- Modify: `app/src/main/java/com/example/cs388_project1/MainActivity.kt`

**Step 1: Add focus request to resetGame()**

Add `guessInput.requestFocus()` at the end of resetGame() after clearing displays

**Step 2: Build to verify**

Run: `./gradlew build`
Expected: Build succeeds

**Step 3: Commit**

```bash
git add app/src/main/java/com/example/cs388_project1/MainActivity.kt
git commit -m "feat: add input focus management"
```

---

### Task 14: Disable Input on Game Over

**Files:**

- Modify: `app/src/main/java/com/example/cs388_project1/MainActivity.kt`

**Step 1: Add input disable to endGame()**

Add these lines to endGame():

```kotlin
        guessInput.isEnabled = false
```

**Step 2: Add input enable to resetGame()**

Add this line to resetGame():

```kotlin
        guessInput.isEnabled = true
```

**Step 3: Build to verify**

Run: `./gradlew build`
Expected: Build succeeds

**Step 4: Commit**

```bash
git add app/src/main/java/com/example/cs388_project1/MainActivity.kt
git commit -m "feat: disable input when game ends"
```

---

## Remember

- Test each step thoroughly before proceeding
- Use the provided helper code exactly as specified
- Follow Kotlin and Android best practices
- Keep the implementation simple and focused on requirements

## Final Verification

After completing all tasks, the app should:

1. Start with a random 4-letter word
2. Allow exactly 3 guesses
3. Show O for correct letter in correct position
4. Show + for correct letter in wrong position
5. Show X for incorrect letter
6. Display the answer after game ends
7. Prevent further submissions after game ends
8. Handle edge cases (empty input, non-alphabetic characters)
9. Prevent screen rotation issues
10. Allow new game reset functionality
11. Hide keyboard after submission
12. Provide clear user feedback via Toast
13. Manage input focus properly after reset

