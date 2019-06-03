package com.donali.basketapp00084417

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.donali.basketapp00084417.database.viewmodels.BasketViewModel

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewModel = ViewModelProviders.of(this).get(BasketViewModel::class.java)


        viewModel.getAllMatches().observe(this, Observer {
            it.forEach{match->
                Log.d("CUSTOM","start date: ${match.start_date}")
                Log.d("CUSTOM","id: ${match.id}")
                Log.d("CUSTOM","team id 1 ${match.id_team_1}")
                Log.d("CUSTOM","team id 2 ${match.id_team_2}")
            }
        })
    }
}
