package com.missouristate.koch.virtualcasino

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import java.util.*
import kotlin.system.measureTimeMillis

class Roulette : AppCompatActivity() {

    private lateinit var backButton: Button
    private lateinit var spinButton: Button
    private lateinit var stopButton: Button
    private lateinit var clearButton: Button
    private lateinit var add10Button: Button
    private lateinit var add50Button: Button
    private lateinit var add100Button: Button
    private lateinit var add500Button: Button
    private lateinit var add1000Button: Button
    private lateinit var halfButton: Button
    private lateinit var doubleButton: Button
    private lateinit var maxButton: Button
    private lateinit var oneToSevenButton: Button
    private lateinit var zeroButton: Button
    private lateinit var eightToFourteenButton: Button
    private lateinit var betAmountTV: TextView
    private lateinit var balanceTV: TextView
    private lateinit var winningNumber: TextView
    private lateinit var result: TextView

    private lateinit var rouletteNum_1: ImageView
    private lateinit var rouletteNum_2: ImageView
    private lateinit var rouletteNum_3: ImageView
    private lateinit var rouletteNum_4: ImageView
    private lateinit var rouletteNum_5: ImageView
    private lateinit var rouletteNum_6: ImageView
    private lateinit var rouletteNum_7: ImageView
    private lateinit var rouletteNum_8: ImageView
    private lateinit var rouletteNum_9: ImageView

    private var rouletteNumbers = arrayOf(RouletteImages(R.drawable.roulette0, 0), RouletteImages(R.drawable.roulette1, 1),
            RouletteImages(R.drawable.roulette2, 2), RouletteImages(R.drawable.roulette3, 3),
            RouletteImages(R.drawable.roulette4, 4), RouletteImages(R.drawable.roulette5, 5),
            RouletteImages(R.drawable.roulette6, 6), RouletteImages(R.drawable.roulette7, 7),
            RouletteImages(R.drawable.roulette8, 8), RouletteImages(R.drawable.roulette9, 9),
            RouletteImages(R.drawable.roulette10, 10), RouletteImages(R.drawable.roulette11, 11),
            RouletteImages(R.drawable.roulette12, 12), RouletteImages(R.drawable.roulette13, 13),
            RouletteImages(R.drawable.roulette14, 14))

    private var img1 = rouletteNumbers[0]
    private var img2 = rouletteNumbers[1]
    private var img3 = rouletteNumbers[8]
    private var img4 = rouletteNumbers[2]
    private var img5 = rouletteNumbers[9]
    private var img6 = rouletteNumbers[3]
    private var img7 = rouletteNumbers[10]
    private var img8 = rouletteNumbers[4]
    private var img9 = rouletteNumbers[11]

    private var balance = 0
    private var payout = 0
    private var spinning = false
    private var bet1To7 = false
    private var bet0 = false
    private var bet8To14 = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.roulette)

        val intent = intent
        balance = intent.getIntExtra("balance", balance)

        backButton = findViewById(R.id.back_button)
        spinButton = findViewById(R.id.btn_spin)
        stopButton = findViewById(R.id.btn_stop)
        clearButton = findViewById(R.id.btn_clear)
        add10Button = findViewById(R.id.btn_add10)
        add50Button = findViewById(R.id.btn_add50)
        add100Button = findViewById(R.id.btn_add100)
        add500Button = findViewById(R.id.btn_add500)
        add1000Button = findViewById(R.id.btn_add1000)
        halfButton = findViewById(R.id.btn_half)
        doubleButton = findViewById(R.id.btn_double)
        maxButton = findViewById(R.id.btn_max)
        oneToSevenButton = findViewById(R.id.btn_1to7)
        zeroButton = findViewById(R.id.btn_0)
        eightToFourteenButton = findViewById(R.id.btn_8to14)
        betAmountTV = findViewById(R.id.betAmount_tv)
        balanceTV = findViewById(R.id.temp_balance)
        winningNumber = findViewById(R.id.random_num)
        result = findViewById(R.id.roulette_output)

        rouletteNum_1 = findViewById(R.id.rouletteNum_1)
        rouletteNum_2 = findViewById(R.id.rouletteNum_2)
        rouletteNum_3 = findViewById(R.id.rouletteNum_3)
        rouletteNum_4 = findViewById(R.id.rouletteNum_4)
        rouletteNum_5 = findViewById(R.id.rouletteNum_5)
        rouletteNum_6 = findViewById(R.id.rouletteNum_6)
        rouletteNum_7 = findViewById(R.id.rouletteNum_7)
        rouletteNum_8 = findViewById(R.id.rouletteNum_8)
        rouletteNum_9 = findViewById(R.id.rouletteNum_9)

        turnOnBetButtons()
        turnOffNumberButtons()
        turnOffSpinButtons()

        updateBalance(balanceTV)
    }

    fun switchToMain(view: View) {
        val getMainScreenIntent = Intent(this@Roulette, SelectGame::class.java);
        getMainScreenIntent.putExtra("balance", balance)
        startActivity(getMainScreenIntent)
    }

    fun updateBalance(view: View) {
        balanceTV.text = ("Balance: $balance").toString()
    }

    fun spin(view: View) {
        spinning = true
        spinButton.isClickable = false
        turnOffNumberButtons()
        turnOffBetButtons()
        spinWheel()
    }

    fun spinWheel() {
        Handler(Looper.getMainLooper()).postDelayed(Runnable {
            img1 = img2
            img2 = img3
            img3 = img4
            img4 = img5
            img5 = img6
            img6 = img7
            img7 = img8
            img8 = img9
            nextNumber()

            rouletteNum_1.setImageResource(img1.image)
            rouletteNum_2.setImageResource(img2.image)
            rouletteNum_3.setImageResource(img3.image)
            rouletteNum_4.setImageResource(img4.image)
            rouletteNum_5.setImageResource(img5.image)
            rouletteNum_6.setImageResource(img6.image)
            rouletteNum_7.setImageResource(img7.image)
            rouletteNum_8.setImageResource(img8.image)
            rouletteNum_9.setImageResource(img9.image)

            if (spinning) {
                spin(spinButton)
            } else {
                payout()
                turnOnBetButtons()
            }
        }, 150)
    }

    fun stopSpinning(view: View) {
        Handler(Looper.getMainLooper()).postDelayed(Runnable {
            spinning = false
            turnOffSpinButtons()
        }, 1000 + Random().nextInt(4000).toLong())
    }

    fun nextNumber() {
        if(img9.id == 0) {
            img9 = rouletteNumbers[1]
        } else if(img9.id == 1) {
            img9 = rouletteNumbers[8]
        } else if(img9.id == 8) {
            img9 = rouletteNumbers[2]
        } else if(img9.id == 2) {
            img9 = rouletteNumbers[9]
        } else if(img9.id == 9) {
            img9 = rouletteNumbers[3]
        } else if(img9.id == 3) {
            img9 = rouletteNumbers[10]
        } else if(img9.id == 10) {
            img9 = rouletteNumbers[4]
        } else if(img9.id == 4) {
            img9 = rouletteNumbers[11]
        } else if(img9.id == 11) {
            img9 = rouletteNumbers[5]
        } else if(img9.id == 5) {
            img9 = rouletteNumbers[12]
        } else if(img9.id == 12) {
            img9 = rouletteNumbers[6]
        } else if(img9.id == 6) {
            img9 = rouletteNumbers[13]
        } else if(img9.id == 13) {
            img9 = rouletteNumbers[7]
        } else if(img9.id == 7) {
            img9 = rouletteNumbers[14]
        } else if(img9.id == 14) {
            img9 = rouletteNumbers[0]
        }
    }

    fun payout() {
        winningNumber.text = ("Winning Number: " + img5.id)
        if(bet1To7 && img5.id in (1..7)) {
            payout = betAmountTV.text.toString().toInt() * 2
            result.text = ("Result: Won $payout! ")
            balance += payout
            betAmountTV.text = "0"
        } else if (bet0 && img5.id == 0) {
            payout = betAmountTV.text.toString().toInt() * 15
            result.text = ("Result: Won $payout! ")
            balance += payout
            betAmountTV.text = "0"
        } else if(bet8To14 && img5.id in (8..14)) {
            payout = betAmountTV.text.toString().toInt() * 2
            result.text = ("Result: Won $payout! ")
            balance += payout
            betAmountTV.text = "0"
        } else {
            result.text = ("Result: Lost ${betAmountTV.text}! ")
            betAmountTV.text = "0"
        }
        updateBalance(balanceTV)
    }

    fun clearBets(view: View) {
        balance += betAmountTV.text.toString().toInt()
        updateBalance(balanceTV)
        betAmountTV.text = "0"
        bet1To7 = false
        bet0 = false
        bet8To14 = false
        turnOffNumberButtons()
        turnOffSpinButtons()
    }
    fun add10(view: View) {
        if(balance >= 10) {
            betAmountTV.text = (betAmountTV.text.toString().toInt() + 10).toString()
            balance -= 10
            updateBalance(balanceTV)
            turnOnNumberButtons()
        }
    }
    fun add50(view: View) {
        if(balance >= 50) {
            betAmountTV.text = (betAmountTV.text.toString().toInt() + 50).toString()
            balance -= 50
            updateBalance(balanceTV)
            turnOnNumberButtons()
        }
    }
    fun add100(view: View) {
        if(balance >= 100) {
            betAmountTV.text = (betAmountTV.text.toString().toInt() + 100).toString()
            balance -= 100
            updateBalance(balanceTV)
            turnOnNumberButtons()
        }
    }
    fun add500(view: View) {
        if(balance >= 500) {
            betAmountTV.text = (betAmountTV.text.toString().toInt() + 500).toString()
            balance -= 500
            updateBalance(balanceTV)
            turnOnNumberButtons()
        }
    }
    fun add1000(view: View) {
        if(balance >= 1000) {
            betAmountTV.text = (betAmountTV.text.toString().toInt() + 1000).toString()
            balance -= 1000
            updateBalance(balanceTV)
            turnOnNumberButtons()
        }
    }
    fun halfBets(view: View) {
        balance += betAmountTV.text.toString().toInt() / 2
        betAmountTV.text = (betAmountTV.text.toString().toInt() / 2).toString()
        updateBalance(balanceTV)
    }
    fun doubleBets(view: View) {
        if (balance >= betAmountTV.text.toString().toInt()) {
            balance -= betAmountTV.text.toString().toInt()
            betAmountTV.text = (betAmountTV.text.toString().toInt() * 2).toString()
            updateBalance(balanceTV)
        }
    }
    fun maxBets(view: View) {
        betAmountTV.text = (betAmountTV.text.toString().toInt() + balance).toString()
        balance -= balance
        updateBalance(balanceTV)
        turnOnNumberButtons()
    }
    fun bet1to7(view: View) {
        bet1To7 = true
        bet0 = false
        bet8To14 = false
        turnOnSpinButtons()
    }
    fun bet0(view: View) {
        bet0 = true
        bet1To7 = false
        bet8To14 = false
        turnOnSpinButtons()
    }
    fun bet8to14(view: View) {
        bet8To14 = true
        bet1To7 = false
        bet0 = false
        turnOnSpinButtons()
    }

    fun turnOnSpinButtons() {
        spinButton.isClickable = true
        stopButton.isClickable = true
    }

    fun turnOnNumberButtons() {
        oneToSevenButton.isClickable = true
        zeroButton.isClickable = true
        eightToFourteenButton.isClickable = true
    }

    fun turnOnBetButtons() {
        clearButton.isClickable = true
        add10Button.isClickable = true
        add50Button.isClickable = true
        add500Button.isClickable = true
        add100Button.isClickable = true
        add1000Button.isClickable = true
        halfButton.isClickable = true
        doubleButton.isClickable = true
        maxButton.isClickable = true
    }

    fun turnOffSpinButtons() {
        spinButton.isClickable = false
        stopButton.isClickable = false
    }

    fun turnOffNumberButtons() {
        oneToSevenButton.isClickable = false
        zeroButton.isClickable = false
        eightToFourteenButton.isClickable = false
    }

    fun turnOffBetButtons() {
        clearButton.isClickable = false
        add10Button.isClickable = false
        add50Button.isClickable = false
        add500Button.isClickable = false
        add100Button.isClickable = false
        add1000Button.isClickable = false
        halfButton.isClickable = false
        doubleButton.isClickable = false
        maxButton.isClickable = false
    }
}