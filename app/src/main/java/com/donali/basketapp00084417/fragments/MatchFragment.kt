package com.donali.basketapp00084417.fragments


import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.donali.basketapp00084417.R
import com.donali.basketapp00084417.database.entities.Point
import com.donali.basketapp00084417.database.viewmodels.BasketViewModel
import com.donali.basketapp00084417.helpers.ActivityHelper

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ID_TEAM = "idTeam"
private const val ID_MATCH = "idMatch"

class MatchFragment : Fragment() {

    lateinit var activityHelper: ActivityHelper
    lateinit var tvTeamName:TextView
    lateinit var tvCounter:TextView
    lateinit var btnAddOne:Button
    lateinit var btnAddTwo:Button
    lateinit var btnAddThree:Button

    lateinit var basketViewModel:BasketViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        activityHelper = context as ActivityHelper
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.match_team, container, false)
        basketViewModel = ViewModelProviders.of(this).get(BasketViewModel::class.java)
        tvTeamName = view.findViewById(R.id.tv_team_name)
        tvCounter = view.findViewById(R.id.tv_team_counter)
        btnAddOne = view.findViewById(R.id.btn_add_one)
        btnAddTwo = view.findViewById(R.id.btn_add_two)
        btnAddThree = view.findViewById(R.id.btn_add_three)

        basketViewModel.getPointsOf(arguments!!.get(ID_TEAM) as Long,arguments!!.get(ID_MATCH) as Long).observe(this, Observer {
            Log.d("MATCH",arguments!!.get(ID_MATCH).toString())
            if(it!=null)
                tvCounter.text = it.amount.toString()
        })

        btnAddOne.setOnClickListener {
            val matchId = activityHelper.getMatchId()
            val teamId = arguments!!.get(ID_TEAM) as Long

//            Log.d("KEYERROR","match: $matchId, $teamId")
//            val point = basketViewModel.insertPoint(Point(matchId,teamId, 1))
        }


        return view
    }

    companion object {

        fun newInstance(param1: Long,param2:Long) =
            MatchFragment().apply {
                arguments = Bundle().apply {
                    putLong(ID_TEAM, param1)
                    putLong(ID_MATCH, param2)
                }
            }
    }

}
