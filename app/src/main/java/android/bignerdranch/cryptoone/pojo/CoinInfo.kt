package android.bignerdranch.cryptoone.pojo

import android.media.Rating
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class CoinInfo (
    @SerializedName("Name")
    @Expose
    val name: String? = null,
)