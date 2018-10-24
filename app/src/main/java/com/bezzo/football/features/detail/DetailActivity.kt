package com.bezzo.football.features.detail

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bezzo.core.base.BaseActivity
import com.bezzo.core.util.GlideApp
import com.bezzo.core.util.constanta.AppConstans
import com.bezzo.football.R
import org.jetbrains.anko.find
import org.jetbrains.anko.setContentView

class DetailActivity : BaseActivity(), DetailContracts.View {

    override fun onInitializedView(savedInstanceState: Bundle?) {
        DetailUI().setContentView(this)

        val clubImage = find<ImageView>(R.id.club_image)
        val clubName = find<TextView>(R.id.club_name)
        val clubDesc = find<TextView>(R.id.club_desc)

        GlideApp.with(this).load(dataReceived?.getInt(AppConstans.CLUB_IMAGE)).into(clubImage)
        clubName.text = dataReceived?.getString(AppConstans.CLUB_NAME)
        clubDesc.text = dataReceived?.getString(AppConstans.CLUB_DESC)
    }
}
