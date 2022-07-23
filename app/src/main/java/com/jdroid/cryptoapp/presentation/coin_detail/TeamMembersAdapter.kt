package com.jdroid.cryptoapp.presentation.coin_detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jdroid.cryptoapp.data.dto.Team
import com.jdroid.cryptoapp.databinding.ListItemTeamMemberBinding

class TeamMembersAdapter(var team: List<Team> = ArrayList()) : RecyclerView.Adapter<TeamMembersAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ListItemTeamMemberBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(team[position])
    }

    override fun getItemCount(): Int {
        return team.size
    }

    inner class ViewHolder(private val fBinding: ListItemTeamMemberBinding) : RecyclerView.ViewHolder(fBinding.root) {

        fun bind(team: Team) {
            fBinding.apply {
                tvTeamMember.text = team.name
                tvTeamMemberRole.text = team.position
            }
        }

    }
}