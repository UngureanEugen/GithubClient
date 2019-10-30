package com.yhn.githubclient.repo

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.yhn.githubclient.R
import com.yhn.githubclient.data.model.RepoItem

class RepoListAdapter(private val context: Context?) :
    RecyclerView.Adapter<RepoListAdapter.RepoViewHolder>() {

    private val entries = mutableListOf<RepoItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.repo_entry, parent, false)
        return RepoViewHolder(view)
    }

    override fun getItemCount(): Int = entries.size

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        holder.bind(item = entries[position])
        holder.itemView.setOnClickListener {
            context?.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(entries[position].htmlUrl)))
        }
    }

    fun populate(items: List<RepoItem>) {
        entries.addAll(items)
    }

    fun clear() {
        entries.clear()
    }

    class RepoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.name)
        val userName: TextView = itemView.findViewById(R.id.userName)
        val score: TextView = itemView.findViewById(R.id.score)

        fun bind(item: RepoItem) {
            name.text = item.name
            userName.text = item.owner?.login
            score.text = "${item.score}"
        }
    }
}