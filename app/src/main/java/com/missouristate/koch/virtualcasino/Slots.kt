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

class Slots : AppCompatActivity() {

    private lateinit var backButton: Button
    private lateinit var spinButton: Button
    private lateinit var stopButton: Button
    private lateinit var slotImage_1: ImageView
    private lateinit var slotImage_2: ImageView
    private lateinit var slotImage_3: ImageView
    private lateinit var slotImage_4: ImageView
    private lateinit var slotImage_5: ImageView
    private lateinit var slotImage_6: ImageView
    private lateinit var slotImage_7: ImageView
    private lateinit var slotImage_8: ImageView
    private lateinit var slotImage_9: ImageView
    private lateinit var balanceTV3: TextView

    private var reelSymbols = arrayOf(ReelImages(R.drawable.cherries, 1), ReelImages(R.drawable.oranges, 2),
                                      ReelImages(R.drawable.bananas, 3), ReelImages(R.drawable.plums, 4),
                                      ReelImages(R.drawable.diamond, 5), ReelImages(R.drawable.seven, 6))

    private var img1 = reelSymbols[Random().nextInt(reelSymbols.size)]
    private var img2 = reelSymbols[Random().nextInt(reelSymbols.size)]
    private var img3 = reelSymbols[Random().nextInt(reelSymbols.size)]
    private var img4 = reelSymbols[Random().nextInt(reelSymbols.size)]
    private var img5 = reelSymbols[Random().nextInt(reelSymbols.size)]
    private var img6 = reelSymbols[Random().nextInt(reelSymbols.size)]
    private var img7 = reelSymbols[Random().nextInt(reelSymbols.size)]
    private var img8 = reelSymbols[Random().nextInt(reelSymbols.size)]
    private var img9 = reelSymbols[Random().nextInt(reelSymbols.size)]

    private var spinning = false
    private var balance = 0
    private var winner = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.slots)

        val intent = intent
        balance = intent.getIntExtra("balance", balance)

        backButton = findViewById(R.id.back_button)
        spinButton = findViewById(R.id.spin_button)
        stopButton = findViewById(R.id.stop_button)
        slotImage_1 = findViewById(R.id.slot_image1)
        slotImage_2 = findViewById(R.id.slot_image2)
        slotImage_3 = findViewById(R.id.slot_image3)
        slotImage_4 = findViewById(R.id.slot_image4)
        slotImage_5 = findViewById(R.id.slot_image5)
        slotImage_6 = findViewById(R.id.slot_image6)
        slotImage_7 = findViewById(R.id.slot_image7)
        slotImage_8 = findViewById(R.id.slot_image8)
        slotImage_9 = findViewById(R.id.slot_image9)
        balanceTV3 = findViewById(R.id.temp_balance_3)

        slotImage_1.setImageResource(img1.image)
        slotImage_2.setImageResource(img2.image)
        slotImage_3.setImageResource(img3.image)
        slotImage_4.setImageResource(img4.image)
        slotImage_5.setImageResource(img5.image)
        slotImage_6.setImageResource(img6.image)
        slotImage_7.setImageResource(img7.image)
        slotImage_8.setImageResource(img8.image)
        slotImage_9.setImageResource(img9.image)

        updateBalance(balanceTV3)
    }

    fun switchToMain(view: View) {
        val getMainScreenIntent = Intent(this@Slots, SelectGame::class.java);
        getMainScreenIntent.putExtra("balance", balance)
        startActivity(getMainScreenIntent)
    }

    fun updateBalance(view: View) {
        balanceTV3.text = ("Balance: $balance").toString()
    }

    fun spinReelOne(view: View) {
        Handler(Looper.getMainLooper()).postDelayed(Runnable {
            img3 = img2
            img2 = img1
            img1 = reelSymbols[Random().nextInt(reelSymbols.size)]

            slotImage_3.setImageResource(img3.image)
            slotImage_2.setImageResource(img2.image)
            slotImage_1.setImageResource(img1.image)

            if (spinning) {
                spinReelOne(spinButton)
            }
        }, 100)
    }

    fun spinReelTwo(view: View) {
        Handler(Looper.getMainLooper()).postDelayed(Runnable {
            img6 = img5
            img5 = img4
            img4 = reelSymbols[Random().nextInt(reelSymbols.size)]

            slotImage_6.setImageResource(img6.image)
            slotImage_5.setImageResource(img5.image)
            slotImage_4.setImageResource(img4.image)

            if (spinning) {
                spinReelTwo(spinButton)
            }
        }, 100)
    }

    fun spinReelThree(view: View) {
        Handler(Looper.getMainLooper()).postDelayed(Runnable {
            img9 = img8
            img8 = img7
            img7 = reelSymbols[Random().nextInt(reelSymbols.size)]

            slotImage_9.setImageResource(img9.image)
            slotImage_8.setImageResource(img8.image)
            slotImage_7.setImageResource(img7.image)

            if (spinning) {
                spinReelThree(spinButton)
            }
        }, 100)
    }

    fun spinReels(view: View) {
        spinning = true
        spinReelOne(spinButton)
        spinReelTwo(spinButton)
        spinReelThree(spinButton)
        spinButton.isClickable = false
        stopButton.isClickable = true
    }

    fun stopReels(view: View) {
        spinning = false
        threeOfAKind()
    }

    private fun threeOfAKind() {
        Handler(Looper.getMainLooper()).postDelayed(Runnable{
            if (img1.id == img4.id && img4.id == img7.id) {  //Top Row Three of a Kind
                winner = 1
                determinePayout(winner)
            }
            if (img2.id == img5.id && img5.id == img8.id) {  //Middle Row Three of a Kind
                winner = 2
                determinePayout(winner)

            }
            if (img3.id == img6.id && img6.id == img9.id) {  //Bottom Row Three of a Kind
                winner = 3
                determinePayout(winner)
            }
            if (img1.id == img5.id && img5.id == img9.id) {  //Diagonal Top Left to Bottom Right Three of a Kind
                winner = 4
                determinePayout(winner)
            }
            if (img3.id == img5.id && img5.id == img7.id) {  //Diagonal Bottom Left to Top Right Three of a Kind
                winner = 5
                determinePayout(winner)
            }
        },500)
        stopButton.isClickable = false
        spinButton.isClickable = true
    }

    private fun determinePayout(winner: Int) {
        if(winner == 1) {
            if(img1.id == 1) {
                balance += 100
            } else if(img1.id == 2) {
                balance += 150
            } else if(img1.id == 3) {
                balance += 200
            } else if(img1.id == 4) {
                balance += 300
            } else if(img1.id == 5) {
                balance += 600
            } else if(img1.id == 6) {
                balance += 1200
            }
        }

        if(winner == 2) {
            if(img2.id == 1) {
                balance += 100
            } else if(img2.id == 2) {
                balance += 150
            } else if(img2.id == 3) {
                balance += 200
            } else if(img2.id == 4) {
                balance += 300
            } else if(img2.id == 5) {
                balance += 600
            } else if(img2.id == 6) {
                balance += 1200
            }
        }

        if(winner == 3) {
            if(img3.id == 1) {
                balance += 100
            } else if(img3.id == 2) {
                balance += 150
            } else if(img3.id == 3) {
                balance += 200
            } else if(img3.id == 4) {
                balance += 300
            } else if(img3.id == 5) {
                balance += 600
            } else if(img3.id == 6) {
                balance += 1200
            }
        }

        if(winner == 4) {
            if(img1.id == 1) {
                balance += 100
            } else if(img1.id == 2) {
                balance += 150
            } else if(img1.id == 3) {
                balance += 200
            } else if(img1.id == 4) {
                balance += 300
            } else if(img1.id == 5) {
                balance += 600
            } else if(img1.id == 6) {
                balance += 1200
            }
        }

        if(winner == 5) {
            if(img3.id == 1) {
                balance += 100
            } else if(img3.id == 2) {
                balance += 150
            } else if(img3.id == 3) {
                balance += 200
            } else if(img3.id == 4) {
                balance += 300
            } else if(img3.id == 5) {
                balance += 600
            } else if(img3.id == 6) {
                balance += 1200
            }
        }
        updateBalance(balanceTV3)
    }
}
