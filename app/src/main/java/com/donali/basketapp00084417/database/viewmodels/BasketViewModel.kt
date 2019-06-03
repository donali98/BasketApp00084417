package com.donali.basketapp00084417.database.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.donali.basketapp00084417.database.RoomDB
import com.donali.basketapp00084417.database.entities.Point
import com.donali.basketapp00084417.database.entities.Team
import com.donali.basketapp00084417.database.repositories.BasketMatchRepository
import com.donali.basketapp00084417.database.repositories.PointRepository
import com.donali.basketapp00084417.database.repositories.TeamRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BasketViewModel(val app: Application) : AndroidViewModel(app) {
    private val teamRepository: TeamRepository
    private val basketMatchRepository: BasketMatchRepository
    private val pointRepository: PointRepository

    init {
        val teamDao = RoomDB.getInstance(app, viewModelScope).teamDao()
        val basketMatchDao = RoomDB.getInstance(app, viewModelScope).basketMatchDao()
        val pointDao = RoomDB.getInstance(app, viewModelScope).pointDao()

        teamRepository = TeamRepository(teamDao)
        basketMatchRepository = BasketMatchRepository(basketMatchDao)
        pointRepository = PointRepository(pointDao)
    }

    fun getAllTeams() = teamRepository.getAll()
    fun getAllMatches() = basketMatchRepository.getAll()
    fun getAllPoints() = pointRepository.getAll()


    fun getAllTeamsExcept(id:Long) = teamRepository.getAllExcept(id)

    fun insertTeam(team: Team):Long {
        var teamId:Long = 0
        viewModelScope.launch(Dispatchers.IO) {
           teamId = teamRepository.insert(team)
        }

        return teamId

    }

    fun insertPoint(point: Point) = viewModelScope.launch(Dispatchers.IO) {
        pointRepository.insert(point)
    }

}