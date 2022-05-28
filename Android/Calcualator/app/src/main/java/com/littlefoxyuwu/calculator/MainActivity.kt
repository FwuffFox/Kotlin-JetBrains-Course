package com.littlefoxyuwu.calculator

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private var lastNumber: String = ""
    private var lastOp: String = "@"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setEventListeners()
    }

    private fun setEventListeners() {
        findViewById<Button>(R.id.button0).setOnClickListener { numButtonPress(0) }
        findViewById<Button>(R.id.button1).setOnClickListener { numButtonPress(1) }
        findViewById<Button>(R.id.button2).setOnClickListener { numButtonPress(2) }
        findViewById<Button>(R.id.button3).setOnClickListener { numButtonPress(3) }
        findViewById<Button>(R.id.button4).setOnClickListener { numButtonPress(4) }
        findViewById<Button>(R.id.button5).setOnClickListener { numButtonPress(5) }
        findViewById<Button>(R.id.button6).setOnClickListener { numButtonPress(6) }
        findViewById<Button>(R.id.button7).setOnClickListener { numButtonPress(7) }
        findViewById<Button>(R.id.button8).setOnClickListener { numButtonPress(8) }
        findViewById<Button>(R.id.button9).setOnClickListener { numButtonPress(9) }

        findViewById<Button>(R.id.clearButton).setOnClickListener { clearButtonPress() }
        findViewById<Button>(R.id.dotButton).setOnClickListener { dotButtonClick() }

        findViewById<Button>(R.id.addButton).setOnClickListener { addButtonClick() }
        findViewById<Button>(R.id.subtractButton).setOnClickListener { subtractButtonPress() }
        findViewById<Button>(R.id.multiplyButton).setOnClickListener { multiplyButtonClick() }
        findViewById<Button>(R.id.divideButton).setOnClickListener { divideButtonClick() }

        findViewById<Button>(R.id.equalButton).setOnClickListener { equalButtonClick() }
    }

    private fun numButtonPress(x: Int) {
        val editText = findViewById<EditText>(R.id.editText)

        val oldNumber = editText.text.toString()
        if ((oldNumber == "0" || oldNumber == "-") && x == 0) return
        if (oldNumber == "0") {
            editText.setText("$x")
            return
        }
        editText.setText("$oldNumber$x")
    }

    private fun clearButtonPress() {
        val editText = findViewById<EditText>(R.id.editText)
        editText.setText("0")
        editText.hint = "0"
    }

    private fun dotButtonClick() {
        val editText = findViewById<EditText>(R.id.editText)
        val oldNumber = editText.text.toString()
        if (oldNumber.last() == '.') return
        if (oldNumber == "-") {
            editText.setText("${oldNumber}0.")
            return
        }
        editText.setText("$oldNumber.")
    }

    private fun subtractButtonPress() {
        val editText = findViewById<EditText>(R.id.editText)
        val number = editText.text.toString()
        if (number == "0") {
            editText.setText("-")
        }
        else {
            lastNumber = number
            lastOp = "-"
            editText.hint = number
            editText.setText("")
        }
    }

    private fun addButtonClick() {
        val editText = findViewById<EditText>(R.id.editText)
        val number = editText.text.toString()
        lastNumber = number
        editText.hint = number
        lastOp = "+"
        editText.setText("")
    }

    private fun multiplyButtonClick() {
        val editText = findViewById<EditText>(R.id.editText)
        val number = editText.text.toString()
        lastNumber = number
        editText.hint = number
        lastOp = "*"
        editText.setText("")
    }

    private fun divideButtonClick() {
        val editText = findViewById<EditText>(R.id.editText)
        val number = editText.text.toString()
        lastNumber = number
        editText.hint = number
        lastOp = "/"
        editText.setText("")
    }

    private fun equalButtonClick() {
        val editText = findViewById<EditText>(R.id.editText)
        val firstNumber = lastNumber.toDouble()
        val secondNumber = editText.text.toString().toDouble()
        val res: Double = when (lastOp) {
            "+" -> firstNumber + secondNumber
            "-" -> firstNumber - secondNumber
            "*" -> firstNumber * secondNumber
            "/" -> firstNumber / secondNumber
            else -> secondNumber
        }
        val resString = if (res.toInt().toDouble() == res) "${res.toInt()}"
        else "$res"
        editText.setText(resString)
        lastNumber = "$res"
        editText.hint = resString
    }
}