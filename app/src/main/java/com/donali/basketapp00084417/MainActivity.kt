package com.donali.basketapp00084417

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.donali.basketapp00084417.database.entities.Team
import com.donali.basketapp00084417.database.viewmodels.BasketViewModel
import com.donali.basketapp00084417.helpers.TeamSpinnerAdapter

class MainActivity : AppCompatActivity() {

    lateinit var btnStartMatch: Button
    lateinit var etTeam1: EditText
    lateinit var etTeam2: EditText
    lateinit var spTeams: Spinner
    lateinit var spTeams2: Spinner
    lateinit var spinnerAdapter: TeamSpinnerAdapter
    lateinit var spinnerAdapter2: TeamSpinnerAdapter
    lateinit var viewModel: BasketViewModel

    var idTeamSelected1: Long = 0
    var idTeamSelected2: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnStartMatch = findViewById(R.id.btn_start_match)
/*        etTeam1 = findViewById(R.id.et_team_1)
        etTeam2 = findViewById(R.id.et_team_2)*/
        spTeams = findViewById(R.id.sp_teams)
        spTeams2 = findViewById(R.id.sp_teams_2)

        viewModel = ViewModelProviders.of(this).get(BasketViewModel::class.java)


        viewModel.getAllTeams().observe(this, Observer {
            val spinnerArray = arrayListOf<Team>()

            it.forEach { team ->
                spinnerArray.add(team)
            }
            spinnerAdapter = TeamSpinnerAdapter(this, R.layout.spinner_layout, spinnerArray)

            spTeams.adapter = spinnerAdapter

        })

        spTeams.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                idTeamSelected1 = spinnerAdapter.getItem(position)!!.id


                Log.d("SPINNER", "first item: $idTeamSelected1")


                viewModel.getAllTeamsExcept(spinnerAdapter.getItem(position)!!.id).observe(this@MainActivity, Observer {

                    val secondSpinnerArray = arrayListOf<Team>()
                    it.forEach { team ->
                        secondSpinnerArray.add(team)
                    }
                    spinnerAdapter2 = TeamSpinnerAdapter(this@MainActivity, R.layout.spinner_layout, secondSpinnerArray)
                    spTeams2.adapter = spinnerAdapter2


                })
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }
        spTeams2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                idTeamSelected2 = spinnerAdapter2.getItem(position)!!.id
                Log.d("SPINNER", "second item: $idTeamSelected2")

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }

        btnStartMatch.setOnClickListener {
            val teamOne = viewModel.insertTeam(Team(etTeam1.text.toString()))
            val teamTwo = viewModel.insertTeam(Team(etTeam2.text.toString()))

            Log.d("CUSTOM", teamOne.toString())
            Log.d("CUSTOM", teamTwo.toString())
        }
    }


}
