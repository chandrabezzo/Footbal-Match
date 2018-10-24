package com.bezzo.football2.features.playerDetail

import android.os.Build
import android.os.Bundle
import com.bezzo.core.base.BaseActivity
import com.bezzo.core.data.model.PlayerResponse
import com.bezzo.core.data.network.ApiRepository
import com.bezzo.core.util.GlideApp
import com.bezzo.core.util.constanta.ApiConstans
import com.bezzo.football2.R
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_player_detail.*
import android.support.v4.content.ContextCompat
import android.view.WindowManager



class PlayerDetailActivity : BaseActivity(), PlayerDetailView {

    lateinit var presenter : PlayerDetailPresenter

    val request = ApiRepository()
    val gson = Gson()

    lateinit var player : PlayerResponse.Player

    override fun onInitializedView(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_player_detail)
        presenter = PlayerDetailPresenter(this, request, gson)

        player = gson.fromJson(dataReceived?.getString(ApiConstans.DATA),
                PlayerResponse.Player::class.java)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.title = player.strPlayer

        val window = window
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = ContextCompat.getColor(this, R.color.colorPrimaryDark)
        }

        GlideApp.with(this).load(player.strThumb).into(iv_player)
        tv_weight.text = player.strWeight?.split(" ")?.get(0) ?: " "
        tv_height.text = player.strHeight?.split(" ")?.get(0) ?: " "
        tv_position.text = player.strPosition
        tv_desc.text = player.strDescriptionEN
    }
}
