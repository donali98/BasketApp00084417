package com.donali.basketapp00084417

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


    lateinit var basketMatch: BasketMatch
    lateinit var bViewModel: BasketViewModel
    lateinit var myMatch: BasketMatch

    var idMatch:Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match)

        val cal = Calendar.getInstance()
        cal.add(Calendar.DATE, 1)
        val dateFormat = SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy")
        bViewModel = ViewModelProviders.of(this).get(BasketViewModel::class.java)
        val teamId1 = intent.extras.getLong("teamId1")
        val teamId2 = intent.extras.getLong("teamId2")
        bViewModel.insertMatch(BasketMatch(teamId1, teamId2, dateFormat.parse(dateFormat.format(cal.time))))

        bViewModel.getLastMatchLive().observe(this, Observer {
            idMatch = it.id
            val matchFragment1 = MatchFragment.newInstance(teamId1,teamId2,idMatch)
            supportFragmentManager.beginTransaction().add(R.id.fl_match,matchFragment1).commit()
        })

    }

    override fun getMatchId(): Long = idMatch

    override fun getBasketViewModel(): BasketViewModel = bViewModel

}
