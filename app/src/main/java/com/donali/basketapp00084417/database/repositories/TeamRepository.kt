package com.donali.basketapp00084417.database.repositories

import androidx.annotation.WorkerThread
import com.donali.basketapp00084417.database.daos.TeamDao
import com.donali.basketapp00084417.database.entities.Team

class TeamRepository(val teamDao: TeamDao) {

    @WorkerThread
    suspend fun insert(team: Team) = teamDao.insert(team)

    fun getOne(id:Long) = teamDao.getOne(id)

    fun getAll() = teamDao.getAll()

    fun getAllExcept(id:Long) = teamDao.getAllExcept(id)
}