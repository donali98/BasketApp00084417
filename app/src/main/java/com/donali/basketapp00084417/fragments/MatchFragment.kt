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
import com.donali.basketapp00084417.database.entities.BasketMatch
import com.donali.basketapp00084417.database.viewmodels.BasketViewModel
import com.donali.basketapp00084417.helpers.ActivityHelper
import java.text.SimpleDateFormat
import java.util.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ID_TEAM_1 = "idTeam1"
private const val ID_TEAM_2 = "idTeam2"
private const val ID_MATCH = "idMatch"

class MatchFragment : Fragment() {


    lateinit var activityHelper: ActivityHelper
    lateinit var tvTeamName2: TextView
    lateinit var tvCounter2: TextView
    lateinit var btnAddOne2: Button
    lateinit var btnAddTwo2: Button
    lateinit var btnAddThree2: Button
    lateinit var tvTeamName1: TextView
    lateinit var tvCounter1: TextView
    lateinit var btnAddOne1: Button
    lateinit var btnAddTwo1: Button
    lateinit var btnAddThree1: Button
    lateinit var basketViewModel: BasketViewModel

    var pointId1: Long = 0
    var pointId2: Long = 0

    var insertOp = false


    override fun onAttach(context: Context) {
        super.onAttach(context)
        activityHelper = context as ActivityHelper
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        basketViewModel = activityHelper.getBasketViewModel()
        val cal = Calendar.getInstance()
        cal.add(Calendar.DATE, 1)
        val dateFormat = SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy")

        basketViewModel.insertMatch(
            BasketMatch(
                arguments!!.getLong(ID_TEAM_1),
                arguments!!.getLong(ID_TEAM_1),
                dateFormat.parse(dateFormat.format(cal.time))
            )
        )


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("CUSTOM", "ejecutado")
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.match_team, container, false)

/*        Log.d("IDS","team 1: ${arguments!!.getLong(ID_TEAM_1)}")
        Log.d("IDS","team 2: ${arguments!!.getLong(ID_TEAM_2)}")*/
        tvTeamName1 = view.findViewById(R.id.tv_team_name_1)
        tvCounter1 = view.findViewById(R.id.tv_team_counter_1)
        btnAddOne1 = view.findViewById(R.id.btn_add_one_1)
        btnAddTwo1 = view.findViewById(R.id.btn_add_two_1)
        btnAddThree1 = view.findViewById(R.id.btn_add_three_1)

        tvTeamName2 = view.findViewById(R.id.tv_team_name_2)
        tvCounter2 = view.findViewById(R.id.tv_team_counter_2)
        btnAddOne2 = view.findViewById(R.id.btn_add_one_2)
        btnAddTwo2 = view.findViewById(R.id.btn_add_two_2)
        btnAddThree2 = view.findViewById(R.id.btn_add_three_2)

        /* btnAddOne1.setOnClickListener {
             basketViewModel.setMatchLogic(
                 arguments!!.getLong(ID_TEAM_1),
                 arguments!!.getLong(ID_TEAM_2),
                 this
             )
         }*/
        return view
    }

    companion object {

        fun newInstance(param1: Long, param2: Long, param3: Long) =
            MatchFragment().apply {
                arguments = Bundle().apply {
                    putLong(ID_TEAM_1, param1)
                    putLong(ID_TEAM_2, param2)
                    putLong(ID_MATCH, param3)
                }
            }
    }

}
