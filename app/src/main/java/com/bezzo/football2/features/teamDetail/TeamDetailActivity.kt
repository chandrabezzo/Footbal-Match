package com.bezzo.football2.features.teamDetail

import android.database.sqlite.SQLiteConstraintException
import android.os.Build
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.view.Menu
import android.view.MenuItem
import android.view.WindowManager
import android.widget.Toast
import com.bezzo.core.base.BaseActivity
import com.bezzo.core.data.model.Event
import com.bezzo.core.data.model.Team
import com.bezzo.core.data.network.ApiRepository
import com.bezzo.core.util.GlideApp
import com.bezzo.core.util.constanta.ApiConstans
import com.bezzo.football2.R
import com.bezzo.football2.adapter.viewPager.TeamVPAdapter
import com.bezzo.football2.utils.database
import com.google.gson.Gson
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.activity_team_detail.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import javax.inject.Inject

class TeamDetailActivity : BaseActivity(), TeamDetailView, HasSupportFragmentInjector {

    lateinit var presenter : TeamDetailPresenter
    lateinit var menu : Menu

    val request = ApiRepository()
    val gson = Gson()
    lateinit var team : Team
    lateinit var adapter : TeamVPAdapter
    var isFavorite : Boolean = false

    @Inject
    lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return fragmentInjector
    }

    override fun onInitializedView(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_team_detail)
        presenter = TeamDetailPresenter(this, request, gson)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setTitle("")

        val window = window
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = ContextCompat.getColor(this, R.color.colorPrimaryDark)
        }


        team = gson.fromJson(dataReceived?.getString(ApiConstans.DATA), Team::class.java)

        GlideApp.with(this).load(team.imageTeam).into(iv_team)
        tv_team_name.text = team.strTeam
        tv_team_years.text = team.intFormedYear
        tv_team_stadion.text = team.strStadium

        setActionBarTitle("")
        displayHome()

        adapter = TeamVPAdapter(this, supportFragmentManager, team.strDescriptionEN, team.idTeam)
        vp_team_detail.adapter = adapter
        tl_team_detail.setupWithViewPager(vp_team_detail)
        vp_team_detail.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tl_team_detail))

        tl_team_detail.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.let {
                    vp_team_detail.currentItem = it.position
                }
            }
        })

        team.idTeam?.let { checkFavoriteState(it) }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.favorite, menu)
        menu?.let {
            this.menu = it
        }
        setFavorite()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            R.id.is_favorite -> {
                if (isFavorite) {
                    team.idTeam?.let { deleteFromFavorite(it) }
                }
                else {
                    addToFavorite(team)
                }

                isFavorite = !isFavorite
                setFavorite()
            }
        }

        return super.onOptionsItemSelected(item)
    }

    override fun addToFavorite(team : Team) {
        try {
            database.use {
                insert(Team.TABLE_TEAM,
                         Team.ID_TEAM to team.idTeam,
                        Team.TEAM_NAME to team.strTeam,
                        Team.FORMED_YEAR to team.intFormedYear,
                        Team.LEAGUE_ID to team.idLeague,
                        Team.STADIUM to team.strStadium,
                        Team.DESCRIPTION to team.strDescriptionEN,
                        Team.IMAGE_TEAM to team.imageTeam)
            }

            addedAsFavorite(true)
        } catch (e : SQLiteConstraintException){
            showToast(e.localizedMessage, Toast.LENGTH_SHORT)
        }
    }

    override fun addedAsFavorite(isFavorite : Boolean) {
        if (isFavorite){
            showToast(getString(R.string.added_as_fav), Toast.LENGTH_SHORT)
        }
        else {
            showToast(getString(R.string.deleted_as_favorite), Toast.LENGTH_SHORT)
        }
    }

    override fun isFavorite(state: Boolean) {
        isFavorite = state
    }

    override fun setFavorite() {
        if (isFavorite){
            menu.getItem(0).icon = ContextCompat.getDrawable(this,
                    R.drawable.ic_star_white)
        }
        else {
            menu.getItem(0).icon = ContextCompat.getDrawable(this,
                    R.drawable.ic_star_border_white)
        }
    }

    override fun deleteFromFavorite(teamId: String) {
        try {
            database.use {
                delete(Team.TABLE_TEAM, "(${Team.ID_TEAM} = {id})",
                        "id" to teamId)
            }

            addedAsFavorite(false)
        } catch (e : SQLiteConstraintException){
            showToast(e.localizedMessage, Toast.LENGTH_SHORT)
        }
    }

    override fun checkFavoriteState(teamId: String) {
        database.use {
            val result = select(Team.TABLE_TEAM)
                    .whereArgs("(${Team.ID_TEAM} = {id})",
                            "id" to teamId)
            val favorite = result.parseList(classParser<Team>())
            if (!favorite.isEmpty()) {
                isFavorite(true)
            }
            else {
                isFavorite(false)
            }
        }
    }
}
