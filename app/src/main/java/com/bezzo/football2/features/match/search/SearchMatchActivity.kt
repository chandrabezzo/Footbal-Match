package com.bezzo.football2.features.match.search

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.bezzo.core.base.BaseActivity
import com.bezzo.core.data.model.Event
import com.bezzo.core.data.network.ApiRepository
import com.bezzo.core.listener.OnItemClickListener
import com.bezzo.core.util.constanta.ApiConstans
import com.bezzo.football2.R
import com.bezzo.football2.adapter.recyclerView.EventRVAdapter
import com.bezzo.football2.features.match.detail.DetailActivity
import com.bezzo.football2.utils.hide
import com.bezzo.football2.utils.show
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_search_match.*


class SearchMatchActivity : BaseActivity(), SearchMatchView {


    private lateinit var presenter: SearchMatchPresenter
    private lateinit var adapter: EventRVAdapter
    private var list = ArrayList<Event>()

    val gson = Gson()
    val request = ApiRepository()

    override fun onInitializedView(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_search_match)
        presenter = SearchMatchPresenter(this, request, gson)

        adapter = EventRVAdapter(list, false)
        rv_search_match.layoutManager = LinearLayoutManager(this)
        rv_search_match.adapter = adapter

        showLoadMore()

        adapter.setOnItemClick(object : OnItemClickListener{
            override fun onItemClick(itemView: View, position: Int) {
                var data = Bundle()
                data.putString(ApiConstans.DATA, gson.toJson(list[position]))

                goToActivity(DetailActivity::class.java, data, false)
            }

            override fun onItemLongClick(itemView: View, position: Int): Boolean {
                return true
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search_navigation, menu)
        val searchItem = menu?.findItem(R.id.nav_search)

        val searchView: SearchView? = searchItem?.actionView as SearchView

        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                if (!query.isEmpty()) {
                    showLoadMore()
                    presenter.searchMatch(query)
                }
                return false
            }

            override fun onQueryTextChange(query: String): Boolean {
                if (!query.isEmpty()) {
                    showLoadMore()
                    presenter.searchMatch(query)
                }
                return false
            }
        })
        searchView?.requestFocus()
        return super.onCreateOptionsMenu(menu)

    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun showMatch(searchedMatch: List<Event>) {
        hideLoadMore()

        list.clear()
        list.addAll(searchedMatch)
        adapter.notifyDataSetChanged()
    }

    override fun showLoadMore() {
        rv_search_match.hide()
        pb_search_match.show()
    }

    override fun hideLoadMore() {
        rv_search_match.show()
        pb_search_match.hide()
    }
}
