package com.missouristate.koch.virtualcasino

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.ImageView
import java.util.Random

class Blackjack : AppCompatActivity() {

    private lateinit var backButton: Button
    private lateinit var playButton: Button
    private lateinit var clearButton2: Button
    private lateinit var add10Button2: Button
    private lateinit var add50Button2: Button
    private lateinit var add100Button2: Button
    private lateinit var add500Button2: Button
    private lateinit var add1000Button2: Button
    private lateinit var halfButton2: Button
    private lateinit var doubleButton2: Button
    private lateinit var maxButton2: Button
    private lateinit var doubleDownButton: Button
    private lateinit var hitButton: Button
    private lateinit var standButton: Button
    private lateinit var betAmountTV2: TextView
    private lateinit var balanceTV2: TextView
    private lateinit var result2: TextView
    private lateinit var playerCardCountTV: TextView
    private lateinit var dealerCardCountTV: TextView
    private lateinit var playerCard1: ImageView
    private lateinit var playerCard2: ImageView
    private lateinit var playerCard3: ImageView
    private lateinit var playerCard4: ImageView
    private lateinit var playerCard5: ImageView
    private lateinit var playerCard6: ImageView
    private lateinit var dealerCard1: ImageView
    private lateinit var dealerCard2: ImageView
    private lateinit var dealerCard3: ImageView
    private lateinit var dealerCard4: ImageView
    private lateinit var dealerCard5: ImageView
    private lateinit var dealerCard6: ImageView

    private var doubleDown = false
    private var hit = false
    private var stand = false

    private var balance = 0

    private var dealerCount = 0
    private var playerCount = 0

    private var cards = arrayOf(Cards(R.drawable.card_ac, 1), Cards(R.drawable.card_ad, 1), Cards(R.drawable.card_ah, 1), Cards(R.drawable.card_as, 1),
            Cards(R.drawable.card_2c, 2), Cards(R.drawable.card_2d, 2), Cards(R.drawable.card_2h, 2), Cards(R.drawable.card_2s, 2),
            Cards(R.drawable.card_3c, 3), Cards(R.drawable.card_3d, 3), Cards(R.drawable.card_3h, 3), Cards(R.drawable.card_3s, 3),
            Cards(R.drawable.card_4c, 4), Cards(R.drawable.card_4d, 4), Cards(R.drawable.card_4h, 4), Cards(R.drawable.card_4s, 4),
            Cards(R.drawable.card_5c, 5), Cards(R.drawable.card_5d, 5), Cards(R.drawable.card_5h, 5), Cards(R.drawable.card_5s, 5),
            Cards(R.drawable.card_6c, 6), Cards(R.drawable.card_6d, 6), Cards(R.drawable.card_6h, 6), Cards(R.drawable.card_6s, 6),
            Cards(R.drawable.card_7c, 7), Cards(R.drawable.card_7d, 7), Cards(R.drawable.card_7h, 7), Cards(R.drawable.card_7s, 7),
            Cards(R.drawable.card_8c, 8), Cards(R.drawable.card_8d, 8), Cards(R.drawable.card_8h, 8), Cards(R.drawable.card_8s, 8),
            Cards(R.drawable.card_9c, 9), Cards(R.drawable.card_9d, 9), Cards(R.drawable.card_9h, 9), Cards(R.drawable.card_9s, 9),
            Cards(R.drawable.card_10c, 10), Cards(R.drawable.card_10d, 10), Cards(R.drawable.card_10h, 10), Cards(R.drawable.card_10s, 10),
            Cards(R.drawable.card_jc, 10), Cards(R.drawable.card_jd, 10), Cards(R.drawable.card_jh, 10), Cards(R.drawable.card_js, 10),
            Cards(R.drawable.card_qc, 10), Cards(R.drawable.card_qd, 10), Cards(R.drawable.card_qh, 10), Cards(R.drawable.card_qs, 10),
            Cards(R.drawable.card_kc, 10), Cards(R.drawable.card_kd, 10), Cards(R.drawable.card_kh, 10), Cards(R.drawable.card_ks, 10))


    private var pCard1 = Cards(0, 0)
    private var pCard2 = Cards(0, 0)
    private var pCard3 = Cards(0, 0)
    private var pCard4 = Cards(0, 0)
    private var pCard5 = Cards(0, 0)
    private var pCard6 = Cards(0, 0)
    private var dCard1 = Cards(0, 0)
    private var dCard2 = Cards(0, 0)
    private var dCard3 = Cards(0, 0)
    private var dCard4 = Cards(0, 0)
    private var dCard5 = Cards(0, 0)
    private var dCard6 = Cards(0, 0)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.blackjack)

        val intent = intent
        balance = intent.getIntExtra("balance", balance)

        backButton = findViewById(R.id.back_button)
        playButton = findViewById(R.id.play_button)
        clearButton2 = findViewById(R.id.btn_clear_2)
        add10Button2 = findViewById(R.id.btn_add10_2)
        add50Button2 = findViewById(R.id.btn_add50_2)
        add100Button2 = findViewById(R.id.btn_add100_2)
        add500Button2 = findViewById(R.id.btn_add500_2)
        add1000Button2 = findViewById(R.id.btn_add1000_2)
        halfButton2 = findViewById(R.id.btn_half_2)
        doubleButton2 = findViewById(R.id.btn_double_2)
        maxButton2 = findViewById(R.id.btn_max_2)
        doubleDownButton = findViewById(R.id.double_down_button)
        hitButton = findViewById(R.id.hit_button)
        standButton = findViewById(R.id.stand_button)
        betAmountTV2 = findViewById(R.id.betAmount_tv_2)
        balanceTV2 = findViewById(R.id.temp_balance_2)
        result2 = findViewById(R.id.blackjack_winnings)
        playerCardCountTV = findViewById(R.id.player_cardCount)
        dealerCardCountTV = findViewById(R.id.dealer_cardCount)
        playerCard1 = findViewById(R.id.player_card_1)
        playerCard2 = findViewById(R.id.player_card_2)
        playerCard3 = findViewById(R.id.player_card_3)
        playerCard4 = findViewById(R.id.player_card_4)
        playerCard5 = findViewById(R.id.player_card_5)
        playerCard6 = findViewById(R.id.player_card_6)
        dealerCard1 = findViewById(R.id.dealer_card_1)
        dealerCard2 = findViewById(R.id.dealer_card_2)
        dealerCard3 = findViewById(R.id.dealer_card_3)
        dealerCard4 = findViewById(R.id.dealer_card_4)
        dealerCard5 = findViewById(R.id.dealer_card_5)
        dealerCard6 = findViewById(R.id.dealer_card_6)

        playButton.isClickable = false
        hitButton.isClickable = false
        standButton.isClickable = false
        doubleDownButton.isClickable = false
        updateBalance(balanceTV2)
    }

    fun switchToMain(view: View) {
        val getMainScreenIntent = Intent(this@Blackjack, SelectGame::class.java);
        getMainScreenIntent.putExtra("balance", balance)
        startActivity(getMainScreenIntent)
    }

    private fun updateBalance(view: View) {
        balanceTV2.text = ("Balance: $balance").toString()
    }

    private fun checkBets(): Boolean {
        if(betAmountTV2.text.toString().toInt() > 0) {
            playButton.isClickable = true
            return true
        } else {
            return false
        }
    }

    private fun countPlayerCards() {
        playerCount = pCard1.id + pCard2.id + pCard3.id + pCard4.id + pCard5.id + pCard6.id

        if (pCard1.id == 1) {
            if (10 + playerCount <= 21) {
                playerCount += 10
            }
        }
        if (pCard2.id == 1) {
            if (10 + playerCount <= 21) {
                playerCount += 10
            }
        }
        if (pCard3.id == 1) {
            if (10 + playerCount <= 21) {
                playerCount += 10
            }
        }
        if (pCard4.id == 1) {
            if (10 + playerCount <= 21) {
                playerCount += 10
            }
        }
        if (pCard5.id == 1) {
            if (10 + playerCount <= 21) {
                playerCount += 10
            }
        }
        if (pCard6.id == 1) {
            if (10 + playerCount <= 21) {
                playerCount += 10
            }
        }

        playerCardCountTV.text = ("Player: $playerCount").toString()
        //todo allow ace to be 1 or 11
    }

    private fun countDealerCards() {
        dealerCount = dCard1.id + dCard2.id + dCard3.id + dCard4.id + dCard5.id + dCard6.id

        if (dCard1.id == 1) {
            if (10 + dealerCount <= 21) {
                dealerCount += 10
            }
        }
        if (dCard2.id == 1) {
            if (10 + dealerCount <= 21) {
                dealerCount += 10
            }
        }
        if (dCard3.id == 1) {
            if (10 + dealerCount <= 21) {
                dealerCount += 10
            }
        }
        if (dCard4.id == 1) {
            if (10 + dealerCount <= 21) {
                dealerCount += 10
            }
        }
        if (dCard5.id == 1) {
            if (10 + dealerCount <= 21) {
                dealerCount += 10
            }
        }
        if (dCard6.id == 1) {
            if (10 + dealerCount <= 21) {
                dealerCount += 10
            }
        }

        dealerCardCountTV.text = ("Dealer: $dealerCount").toString()
        //todo allow ace to be 1 or 11
    }

    fun dealCards(view: View) {
        playButton.isClickable = false
        hitButton.isClickable = true
        standButton.isClickable = true
        doubleDownButton.isClickable = true
        clearCards()
        pCard1 = cards[Random().nextInt(cards.size)]
        pCard2 = cards[Random().nextInt(cards.size)]
        dCard1 = cards[Random().nextInt(cards.size)]

        playerCard1.setImageResource(pCard1.image)
        playerCard2.setImageResource(pCard2.image)
        dealerCard1.setImageResource(dCard1.image)
        countPlayerCards()
        countDealerCards()
    }

    private fun clearCards() {
        pCard1 = Cards(0, 0)
        pCard2 = Cards(0, 0)
        pCard3 = Cards(0, 0)
        pCard4 = Cards(0, 0)
        pCard5 = Cards(0, 0)
        pCard6 = Cards(0, 0)
        dCard1 = Cards(0, 0)
        dCard2 = Cards(0, 0)
        dCard3 = Cards(0, 0)
        dCard4 = Cards(0, 0)
        dCard5 = Cards(0, 0)
        dCard6 = Cards(0, 0)
        playerCard1.setImageResource(0)
        playerCard2.setImageResource(0)
        playerCard3.setImageResource(0)
        playerCard4.setImageResource(0)
        playerCard5.setImageResource(0)
        playerCard6.setImageResource(0)
        dealerCard1.setImageResource(0)
        dealerCard2.setImageResource(0)
        dealerCard3.setImageResource(0)
        dealerCard4.setImageResource(0)
        dealerCard5.setImageResource(0)
        dealerCard6.setImageResource(0)
    }

    fun clearBets(view: View) {
        balance += betAmountTV2.text.toString().toInt()
        updateBalance(balanceTV2)
        betAmountTV2.text = "0"
        playButton.isClickable = false
        hitButton.isClickable = false
        standButton.isClickable = false
        doubleDownButton.isClickable = false
    }
    fun add10(view: View) {
        if(balance >= 10) {
            betAmountTV2.text = (betAmountTV2.text.toString().toInt() + 10).toString()
            balance -= 10
            updateBalance(balanceTV2)
            checkBets()
        }
    }
    fun add50(view: View) {
        if(balance >= 50) {
            betAmountTV2.text = (betAmountTV2.text.toString().toInt() + 50).toString()
            balance -= 50
            updateBalance(balanceTV2)
            checkBets()
        }
    }
    fun add100(view: View) {
        if(balance >= 100) {
            betAmountTV2.text = (betAmountTV2.text.toString().toInt() + 100).toString()
            balance -= 100
            updateBalance(balanceTV2)
            checkBets()
        }
    }
    fun add500(view: View) {
        if(balance >= 500) {
            betAmountTV2.text = (betAmountTV2.text.toString().toInt() + 500).toString()
            balance -= 500
            updateBalance(balanceTV2)
            checkBets()
        }
    }
    fun add1000(view: View) {
        if(balance >= 1000) {
            betAmountTV2.text = (betAmountTV2.text.toString().toInt() + 1000).toString()
            balance -= 1000
            updateBalance(balanceTV2)
            checkBets()
        }
    }
    fun halfBets(view: View) {
        balance += betAmountTV2.text.toString().toInt() / 2
        betAmountTV2.text = (betAmountTV2.text.toString().toInt() / 2).toString()
        updateBalance(balanceTV2)
    }

    fun doubleBets(view: View) : Boolean {
        if (balance >= betAmountTV2.text.toString().toInt()) {
            balance -= betAmountTV2.text.toString().toInt()
            betAmountTV2.text = (betAmountTV2.text.toString().toInt() * 2).toString()
            updateBalance(balanceTV2)
            return true
        } else {
            return false
        }
    }

    fun maxBets(view: View) {
        betAmountTV2.text = (betAmountTV2.text.toString().toInt() + balance).toString()
        balance -= balance
        updateBalance(balanceTV2)
        checkBets()
    }

    fun hitMove(view: View) {
        if(pCard3.id == 0) {
            pCard3 = cards[Random().nextInt(cards.size)]
            playerCard3.setImageResource(pCard3.image)
        } else if(pCard4.id == 0) {
            pCard4 = cards[Random().nextInt(cards.size)]
            playerCard4.setImageResource(pCard4.image)
        } else if(pCard5.id == 0) {
            pCard5 = cards[Random().nextInt(cards.size)]
            playerCard5.setImageResource(pCard5.image)
        } else {
            pCard6 = cards[Random().nextInt(cards.size)]
            playerCard6.setImageResource(pCard6.image)
        }
        countPlayerCards()
        countDealerCards()

        if(playerCount > 21) {
            gameResult()
        }
    }
    fun doubleDownMove(view: View) {
        if(doubleBets(balanceTV2)) {
            hitMove(hitButton)
            standMove(standButton)
        } else {
            countPlayerCards()
            countDealerCards()
        }
    }
    fun standMove(view: View) {
        while(dealerCount < 17) {
            if (dCard2.id == 0) {
                dCard2 = cards[Random().nextInt(cards.size)]
                dealerCard2.setImageResource(dCard2.image)
            } else if (dCard3.id == 0) {
                dCard3 = cards[Random().nextInt(cards.size)]
                dealerCard3.setImageResource(dCard3.image)
            } else if (dCard4.id == 0) {
                dCard4 = cards[Random().nextInt(cards.size)]
                dealerCard4.setImageResource(dCard4.image)
            } else if (dCard5.id == 0) {
                dCard5 = cards[Random().nextInt(cards.size)]
                dealerCard5.setImageResource(dCard5.image)
            } else {
                dCard6 = cards[Random().nextInt(cards.size)]
                dealerCard6.setImageResource(dCard6.image)
            }

            countPlayerCards()
            countDealerCards()
        }
        gameResult()
    }

    private fun gameResult() {
        var winnings = 0
        if(playerCount in (dealerCount + 1)..21 || dealerCount > 21) {
            winnings = (betAmountTV2.text.toString().toInt() * 2)
            balance += winnings
            updateBalance(balanceTV2)
            betAmountTV2.text = "0"
            result2.text = ("Result: Player Won $winnings!")
        } else if(playerCount == dealerCount) {
            balance += betAmountTV2.text.toString().toInt()
            updateBalance(balanceTV2)
            betAmountTV2.text = "0"
            result2.text = "Result: Tie"
        } else {
            result2.text = "Result: Player Lost"
            betAmountTV2.text = "0"

        }

        playButton.isClickable = false
        hitButton.isClickable = false
        standButton.isClickable = false
        doubleDownButton.isClickable = false
    }
}