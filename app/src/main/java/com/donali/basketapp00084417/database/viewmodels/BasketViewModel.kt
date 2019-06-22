package com.donali.basketapp00084417.database.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.donali.basketapp00084417.database.RoomDB
import com.donali.basketapp00084417.database.entities.BasketMatch
import com.donali.basketapp00084417.database.entities.Point
import com.donali.basketapp00084417.database.entities.Team
import com.donali.basketapp00084417.database.repositories.BasketMatchRepository
import com.donali.basketapp00084417.database.repositories.PointRepository
import com.donali.basketapp00084417.database.repositories.TeamRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class BasketViewModel(val app: Application) : AndroidViewModel(app) {
    private val teamRepository: TeamRepository
    private val basketMatchRepository: BasketMatchRepository
    private val pointRepository: PointRepository

    val params = MutableLiveData<ArrayList<Long>>()

    init {
        val teamDao = RoomDB.getInstance(app, viewModelScope).teamDao()
        val basketMatchDao = RoomDB.getInstance(app, viewModelScope).basketMatchDao()
        val pointDao = RoomDB.getInstance(app, viewModelScope).pointDao()

        teamRepository = TeamRepository(teamDao)
        basketMatchRepository = BasketMatchRepository(basketMatchDao)
        pointRepository = PointRepository(pointDao)
    }

    fun getAllTeams() = teamRepository.getAll()
    fun getAllTeamsExcept(id: Long) = teamRepository.getAllExcept(id)


    fun insertMatch(match: BasketMatch) =GlobalScope.launch(Dispatchers.IO) {
        val insertedId = basketMatchRepository.insert(match)
        Log.d("CUSTOM", "ahuevo $insertedId")
    }


     fun setMatchLogic(idTeam1:Long,idTeam2:Long) = viewModelScope.launch(Dispatchers.IO){
        val cal = Calendar.getInstance()
        cal.add(Calendar.DATE,  1)
        val dateFormat = SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy")
        val matchId = basketMatchRepository.insert(BasketMatch(idTeam1,idTeam2,dateFormat.parse(dateFormat.format(cal.time))))
        val pTeam1 = pointRepository.insert(Point(matchId,idTeam1,0))
        val pTeam2= pointRepository.insert(Point(matchId,idTeam2,0))
        val paramsArray = ArrayList<Long>()

        paramsArray.add(pTeam1)
        paramsArray.add(pTeam2)
        paramsArray.add(matchId)

        params.postValue(paramsArray)

    }


}