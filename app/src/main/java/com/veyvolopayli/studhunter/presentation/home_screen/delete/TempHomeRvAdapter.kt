package com.veyvolopayli.studhunter.presentation.home_screen.delete

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.veyvolopayli.studhunter.databinding.UserBinding

class TempHomeRvAdapter : RecyclerView.Adapter<TempHomeRvAdapter.ViewHolder>(), View.OnClickListener {

    private var users: List<User> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
        val binding: UserBinding = UserBinding.inflate(view, parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = users[position]

        with(holder.binding){
            name.text = currentItem.name
            age.text = currentItem.age.toString()
        }

    }

    fun setData(users: List<User>) {
        this.users = users
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return users.size
    }

    class ViewHolder(val binding: UserBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onClick(v: View) {
        val publication = v.tag as UserBinding
    }
}
