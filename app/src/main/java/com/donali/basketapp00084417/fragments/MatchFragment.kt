package com.donali.basketapp00084417.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.donali.basketapp00084417.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ID_TEAM_1 = "idTeam1"
private const val ID_TEAM_2 = "idTeam2"

class MatchFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.match_team, container, false)
    }

    companion object {

        fun newInstance(param1: Long,param2:Long) =
            MatchFragment().apply {
                arguments = Bundle().apply {
                    putLong(ID_TEAM_1, param1)
                    putLong(ID_TEAM_2, param2)
                }
            }
    }

}
