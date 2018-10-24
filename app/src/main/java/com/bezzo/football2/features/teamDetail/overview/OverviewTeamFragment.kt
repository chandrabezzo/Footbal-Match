package com.bezzo.football2.features.teamDetail.overview

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View
import com.bezzo.core.base.BaseFragment
import com.bezzo.core.util.constanta.ApiConstans
import com.bezzo.core.util.constanta.AppConstans
import com.bezzo.football2.R
import kotlinx.android.synthetic.main.fragment_overview_team.*

class OverviewTeamFragment : BaseFragment() {

    override fun onViewInitialized(savedInstanceState: Bundle?) {

    }

    companion object {
        fun newInstance(desc : String?) : Fragment {
            var args = Bundle()
            args.putString(ApiConstans.DATA, desc)

            var fragment = OverviewTeamFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tv_overview.text = dataReceived?.getString(ApiConstans.DATA)
    }

    override fun setLayoutId(): Int {
        return R.layout.fragment_overview_team
    }
}
