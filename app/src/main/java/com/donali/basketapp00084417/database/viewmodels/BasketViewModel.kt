package com.donali.basketapp00084417.database.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.donali.basketapp00084417.database.RoomDB
import com.donali.basketapp00084417.database.entities.Team
import com.donali.basketapp00084417.database.repositories.TeamRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BasketViewModel(val app:Application):AndroidViewModel(app) {
    private val teamRepository:TeamRepository
    init {
        val teamDao = RoomDB.getInstance(app,viewModelScope).teamDao()

        teamRepository = TeamRepository(teamDao)
    }

    fun insert(team:Team) = viewModelScope.launch(Dispatchers.IO){
        teamRepository.insert(team)
    }

    fun getAllTeams() = teamRepository.getAll()


}