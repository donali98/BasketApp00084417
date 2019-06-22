package com.donali.basketapp00084417

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.Observer
import com.donali.basketapp00084417.database.entities.BasketMatch
import com.donali.basketapp00084417.database.viewmodels.BasketViewModel
import com.donali.basketapp00084417.fragments.MatchFragment
import com.donali.basketapp00084417.helpers.ActivityHelper
import kotlinx.android.synthetic.main.activity_match.*
import java.security.acl.Owner
import java.text.SimpleDateFormat
import java.util.*

class MatchActivity : AppCompatActivity(), ActivityHelper {


    lateinit var bViewModel: BasketViewModel

    var pointIdTeam1: Long = 0
    var pointIdTeam2: Long = 0
    var idMatch: Long = 0

    var counter = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match)

        val teamId1 = intent.extras.getLong("teamId1")
        val teamId2 = intent.extras.getLong("teamId2")
        bViewModel = ViewModelProviders.of(this).get(BasketViewModel::class.java)
        bViewModel.setMatchLogic(teamId1, teamId2)

        bViewModel.params.observe(this, Observer {
            it.forEach { param ->
                when (counter) {
                    0 -> {
                        pointIdTeam1 = param
                    }
                    1 -> {
                        pointIdTeam2 = param
                    }
                    2 ->{
                        idMatch = param
                    }

                }
                counter++
            }

            Log.d("CUSTOM","point id team 1: $pointIdTeam1, point id team 2: $pointIdTeam2, idMatch: ${idMatch}")
        })
    }


    override fun getBasketViewModel(): BasketViewModel = bViewModel

}
