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

        viewModel.getAllTeams().observe(this, Observer {
            it.forEach {team->
                Log.d("CUSTOM",team.name)
            }
        })
    }
}
