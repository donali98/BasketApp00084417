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
    lateinit var tvTeamName1:TextView
    lateinit var tvCounter1:TextView
    lateinit var btnAddOne1:Button
    lateinit var btnAddTwo1:Button
    lateinit var btnAddThree1:Button

    lateinit var basketViewModel:BasketViewModel

    var pointId:Long = 0

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
        tvTeamName1 = view.findViewById(R.id.tv_team_name_1)
        tvCounter1 = view.findViewById(R.id.tv_team_counter_1)
        btnAddOne1 = view.findViewById(R.id.btn_add_one_1)
        btnAddTwo1 = view.findViewById(R.id.btn_add_two_1)
        btnAddThree1 = view.findViewById(R.id.btn_add_three_1)

        btnAddOne1.setOnClickListener {
            if(pointId.toInt() == 0 ){
                basketViewModel.insertPoint(Point(arguments!!.getLong(ID_MATCH),arguments!!.getLong(ID_TEAM),1))
            }
            else Log.d("ALELUYA",pointId.toString())
        }

        basketViewModel.getLastPointMatchLive().observe(this, Observer {
           if(it!=null){
               pointId = it.id
               tvCounter1.text = it.amount.toString()
           }
        })

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
