package com.example.calculator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import net.objecthunter.exp4j.ExpressionBuilder
import java.lang.ArithmeticException

class MainActivity : AppCompatActivity()
{

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        /*Number Buttons*/

        tvOne.setOnClickListener {
            evaluateExpression("1", clear = true)
        }

        tvTwo.setOnClickListener {
            evaluateExpression("2", clear = true)
        }

        tvThree.setOnClickListener {
            evaluateExpression("3", clear = true)
        }
        tvFour.setOnClickListener {
            evaluateExpression("4", clear = true)
        }

        tvFive.setOnClickListener {
            evaluateExpression("5", clear = true)
        }

        tvSix.setOnClickListener {
            evaluateExpression("6", clear = true)
        }

        tvSeven.setOnClickListener {
            evaluateExpression("7", clear = true)
        }

        tvEight.setOnClickListener {
            evaluateExpression("8", clear = true)
        }

        tvNine.setOnClickListener {
            evaluateExpression("9", clear = true)
        }

        tvZero.setOnClickListener {
            evaluateExpression("0", clear = true)
        }

        /*Operators*/

        tvPlus.setOnClickListener {
            evaluateExpression("+", clear = true)
        }

        tvMinus.setOnClickListener {
            evaluateExpression("-", clear = true)
        }

        tvMul.setOnClickListener {
            evaluateExpression("*", clear = true)
        }

        tvDivide.setOnClickListener {
            evaluateExpression("/", clear = true)
        }
        tvMod.setOnClickListener {
            evaluateExpression("%", clear = true)
        }
        tvDot.setOnClickListener {
            evaluateExpression(".", clear = true)
        }

        tvClear.setOnClickListener {
            tvExpression.text = ""
            tvResult.text = ""
        }

        tvEquals.setOnClickListener {
            var text = tvExpression.text.toString()
            if(text.length==0) {
                text="0"
                tvExpression.text = text
                invalid()
            }
                var d = 0
                while (d == 0) {
                    if (text[0].equals('+') ||
                        text[0].equals('*') ||
                        text[0].equals('/') ||
                        text[0].equals('%')
                    ) {
                        text = text.substring(1, text.length)
                        tvExpression.text = text
                    } else {
                        d = 1
                    }
                }
                var c = 0
                while (c == 0) {
                    if (text[text.length - 1].equals('*') ||
                        text[text.length - 1].equals('+') ||
                        text[text.length - 1].equals('-') ||
                        text[text.length - 1].equals('/') ||
                        text[text.length - 1].equals('%')
                    ) {
                        text = text.substring(0, text.length - 1)
                        tvExpression.text = text
                    } else {
                        c = 1
                    }
                }
            val expression = ExpressionBuilder(text).build()
            try {
                val result = expression.evaluate()
                val longResult = result.toLong()
                if (result == longResult.toDouble()) {
                    tvResult.text = longResult.toString()
                } else {
                    tvResult.text = result.toString()
                }
            }catch (ex: ArithmeticException){invalid()}
        }

        tvBack.setOnClickListener {
            val text = tvExpression.text.toString()
            if(text.isNotEmpty()) {
                tvExpression.text = text.substring(0,text.length-1)
            }

            tvResult.text = ""
        }
    }

    /*Function to calculate the expressions using expression builder library*/

    fun evaluateExpression(string: String, clear: Boolean) {
        if(clear) {
            tvResult.text = ""
            tvExpression.append(string)
        } else {
            tvExpression.append(tvResult.text)
            tvExpression.append(string)
           tvResult.text = ""
        }
    }
    private fun invalid() {
        Toast.makeText(this, "Invalid input format",
            Toast.LENGTH_SHORT).show()
    }
}
