package erick.tijerou.socialapp.presentation.ui.userlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import erick.tijerou.socialapp.R
import erick.tijerou.socialapp.databinding.ItemUserBinding
import erick.tijerou.socialapp.domain.entity.User

class UserListAdapter(
    private val onItemClick: (View, Long) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val userList = mutableListOf<User>()

    fun updateUsers(userList: List<User>) {
        with(this.userList) {
            clear()
            addAll(userList)
        }

        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return UserListViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_user, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val user = userList[position]
        val binding = (holder as UserListViewHolder).binding

        binding.user = user
        binding.constraintLayout.setOnClickListener { onItemClick(it, user.id) }
    }

    override fun getItemCount() = userList.size

    override fun onViewRecycled(holder: RecyclerView.ViewHolder) {
        super.onViewRecycled(holder)

        (holder as UserListViewHolder)
            .binding.constraintLayout.setOnClickListener(null)
    }

}

private class UserListViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val binding: ItemUserBinding = ItemUserBinding.bind(view)

}
