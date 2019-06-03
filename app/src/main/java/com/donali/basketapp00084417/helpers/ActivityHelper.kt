package com.donali.basketapp00084417.helpers

import com.donali.basketapp00084417.database.entities.BasketMatch
import com.donali.basketapp00084417.database.viewmodels.BasketViewModel

interface ActivityHelper {
    fun getMatchId():Long
    fun getBasketViewModel():BasketViewModel

}