package com.bezzo.football.features.main

import android.os.Bundle
import android.widget.Toast
import com.bezzo.core.base.BaseActivity
import com.bezzo.core.data.model.FootballClub
import com.bezzo.core.util.constanta.AppConstans
import com.bezzo.football.R
import com.bezzo.football.adapter.recyclerView.FootballClubRVAdapter
import com.bezzo.football.features.detail.DetailActivity
import com.bezzo.football.features.main.ui.MainUI
import org.jetbrains.anko.setContentView
import javax.inject.Inject

class MainActivity : BaseActivity(), MainContracts.View {

    @Inject
    lateinit var presenter : MainPresenter<MainContracts.View>

    private var items : MutableList<FootballClub> = mutableListOf()

    override fun onInitializedView(savedInstanceState: Bundle?) {
        presenter.onAttach(this)

        var adapter = FootballClubRVAdapter(this, items){
            var data = Bundle()
            data.putString(AppConstans.CLUB_NAME, it.name)
            data.putString(AppConstans.CLUB_DESC, it.desc)
            data.putInt(AppConstans.CLUB_IMAGE, it.image ?: 0)

            goToActivity(DetailActivity::class.java, data, false)
        }

        MainUI(adapter).setContentView(this)
        initData()
        adapter.notifyDataSetChanged()
    }

    override fun onDestroy() {
        presenter.onDetach()
        super.onDestroy()
    }

    private fun initData() {
        val name = resources.getStringArray(R.array.club_name)
        val image = resources.obtainTypedArray(R.array.club_image)
        val desc = resources.getStringArray(R.array.club_desc)

        items.clear()


        // indices to get index of element
        for (counter in name.indices){
            items.add(FootballClub(name[counter],
                    image.getResourceId(counter, 0), desc[counter]))
        }

        //Recycle the typed array
        image.recycle()
    }
}
