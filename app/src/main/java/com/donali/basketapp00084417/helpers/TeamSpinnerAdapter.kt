package com.donali.basketapp00084417.helpers

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.donali.basketapp00084417.database.entities.Team

class TeamSpinnerAdapter(val receivedContext: Context, val tvResourceId: Int, val teams: ArrayList<Team>) : ArrayAdapter<Team>(
        receivedContext,
        tvResourceId,
        teams
) {
    override fun getCount(): Int = teams.size
    override fun getItem(position: Int): Team?  = teams[position]
    override fun getItemId(position: Int): Long  = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val label:TextView = super.getView(position, convertView, parent) as TextView
        label.textSize = 30f
        label.text = teams[position].name
        return label
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        val textView = super.getDropDownView(position, convertView, parent) as TextView
        textView.text = teams[position].name
        textView.textSize = 30f
        return textView
    }

}