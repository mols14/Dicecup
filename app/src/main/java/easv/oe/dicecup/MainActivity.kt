package easv.oe.dicecup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    private val TAG: String = "xyz"
    // mapping from 1..6 to drawables, the first index is unused
    private val diceId = intArrayOf(0, R.drawable.dice1,
                               R.drawable.dice2,
                               R.drawable.dice3,
                               R.drawable.dice4,
                               R.drawable.dice5,
                               R.drawable.dice6)

    private val mRandomGenerator = Random()

    private val mHistory = mutableListOf<Pair<Int, Int>>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnRoll.setOnClickListener { v -> onClickRoll() }

        imgDice1.setOnClickListener { v -> onClickRoll() }
        imgDice2.setOnClickListener { v -> onClickRoll() }
        btnClear.setOnClickListener { v -> onClickClear() }
        Log.d(TAG, "OnCreate")
    }

    private fun onClickRoll(){
        val e1 = mRandomGenerator.nextInt(6) + 1
        val e2 = mRandomGenerator.nextInt(6) + 1

        // update dices
        imgDice1.setImageResource( diceId[e1] )
        imgDice2.setImageResource( diceId[e2] )

        //update history
        mHistory.add(Pair(e1,e2))
        if (mHistory.size > 5) mHistory.removeAt(0)
        updateHistory()
        Log.d(TAG, "Roll")
    }

    private fun onClickClear() {
        Log.d(TAG, "Clear")
        mHistory.clear()
        updateHistory()
    }

    // ensures that the history text aligns the history object
    private fun updateHistory() {
        var s = ""
        mHistory.forEach { p ->  val e1 = p.first; val e2 = p.second; s += "$e1 - $e2 \n" }
        tvHistory.text = s
    }

}
