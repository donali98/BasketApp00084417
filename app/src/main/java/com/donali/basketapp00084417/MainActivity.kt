package com.donali.basketapp00084417

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.donali.basketapp00084417.database.viewmodels.BasketViewModel
import com.donali.basketapp00084417.fragments.TeamFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initFragments()
    }

    fun initFragments(){
        val team1 = TeamFragment()
        val team2 = TeamFragment()
        supportFragmentManager.beginTransaction().add(R.id.fl_team_1,team1).commit()
        supportFragmentManager.beginTransaction().add(R.id.fl_team_2,team2).commit()
    }

}
