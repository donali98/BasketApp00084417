package com.donali.basketapp00084417.database.viewmodels

import android.app.Application
import android.os.Parcel
import android.os.Parcelable
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.donali.basketapp00084417.database.RoomDB
import com.donali.basketapp00084417.database.entities.BasketMatch
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
    fun getLastMatchLive() = basketMatchRepository.getLastMatchLive()
    fun getAllPoints() = pointRepository.getAll()
    fun getPointById(id:Long) = pointRepository.getById(id)
    fun getByIdNoLiveData(id:Long) = pointRepository.getByIdNoLiveData(id)
    fun getAllTeamsExcept(id: Long) = teamRepository.getAllExcept(id)
    fun getLastPointMatchLive() = pointRepository.getLastMatchLive()
    fun insertTeam(team: Team): Long {
        var teamId: Long = 0
        viewModelScope.launch(Dispatchers.IO) {
            teamId = teamRepository.insert(team)
        }

        return teamId

    }

    fun insertPoint(point: Point) = viewModelScope.launch(Dispatchers.IO) {
        pointRepository.insert(point)
    }


    fun updatePoints(amount: Int, idPoint: Long) = viewModelScope.launch(Dispatchers.IO) {
        pointRepository.updatePoints(amount, idPoint)
    }

    fun insertMatch(match: BasketMatch):Long {
        var insertedId:Long = 0
        viewModelScope.launch(Dispatchers.IO) {
            insertedId = basketMatchRepository.insert(match)
        }
        return insertedId
    }


}