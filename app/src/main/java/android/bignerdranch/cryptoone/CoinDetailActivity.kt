package android.bignerdranch.cryptoone

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.squareup.picasso.Picasso

class CoinDetailActivity : AppCompatActivity() {

    private lateinit var viewModel: CoinViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coin_detail)
        if(!intent.hasExtra(EXTRA_FROM_SYMBOL)) {
            finish()
            return
        }
        val fromSymbol = intent.getStringExtra(EXTRA_FROM_SYMBOL)
        if(fromSymbol == null) {
            return
        }
        viewModel = ViewModelProvider(this)[CoinViewModel::class.java]
        viewModel.getDatailInfo(fromSymbol).observe(this, Observer {
            (findViewById(R.id.tvPrice) as TextView).text = it.price
            (findViewById(R.id.tvMinPrice) as TextView).text = it.lowDay
            (findViewById(R.id.tvMaxPrice) as TextView).text = it.highDay
            (findViewById(R.id.tvLastMarket) as TextView).text = it.lastMarket
            (findViewById(R.id.tvLastUpdate) as TextView).text = it.getFormattedTime()
            (findViewById(R.id.tvFromSymbol) as TextView).text = it.fromSymbol
            (findViewById(R.id.tvToSymbol) as TextView).text = it.toSymbol
            Picasso.get().load(it.getFullImageUrl()).into(findViewById(R.id.ivLogoCoin) as ImageView)
        } )
    }

    companion object {
        private const val EXTRA_FROM_SYMBOL = "fSym"

        fun newIntent(context: Context, fromSymbol: String): Intent {
            val intent = Intent(context, CoinDetailActivity::class.java)
            intent.putExtra(EXTRA_FROM_SYMBOL, fromSymbol)
            return intent
        }
    }

}