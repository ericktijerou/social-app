package erick.tijerou.socialapp.presentation.ui.userlist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.util.Pair
import androidx.core.view.ViewCompat
import androidx.databinding.DataBindingUtil
import erick.tijerou.socialapp.R
import erick.tijerou.socialapp.presentation.ui.extension.observe
import erick.tijerou.socialapp.presentation.ui.extension.startNewActivity
import erick.tijerou.socialapp.presentation.ui.userdetail.UserDetailActivity
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber
import erick.tijerou.socialapp.databinding.ActivityUserListBinding as Binding

class UserListActivity : AppCompatActivity() {

    private val binding by lazy {
        DataBindingUtil.setContentView<Binding>(this, R.layout.activity_user_list)
    }

    private val viewModel by viewModel<UserListViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this.let { activity ->
            binding.apply {
                lifecycleOwner = activity
                viewModel = activity.viewModel
                recyclerView.adapter = UserListAdapter(::goToUserDetail)
            }
        }

        observeUsers()

        observeError()
    }

    private fun observeUsers() {
        viewModel.users.observe(this) { users ->
            Timber.d("users = $users")
            binding.recyclerView.adapter?.let {
                (it as UserListAdapter).updateUsers(users)
            }
        }
    }

    private fun observeError() {
        viewModel.error.observe(this) {
            Timber.e(it)
            Toast.makeText(
                this@UserListActivity,
                R.string.load_users_error,
                Toast.LENGTH_LONG
            ).show()
        }
    }

    private fun goToUserDetail(view: View, userId: Long) {
        Timber.d("userId = $userId")
        val cardView = view.findViewById<View>(R.id.cardview)
        val imageView = view.findViewById<View>(R.id.image_thumbnail)
        val nameView = view.findViewById<View>(R.id.text_name)
        val sharedViews = arrayOf(
            Pair(cardView, ViewCompat.getTransitionName(cardView)),
            Pair(imageView, ViewCompat.getTransitionName(imageView)),
            Pair(nameView, ViewCompat.getTransitionName(nameView)))
        startNewActivity(UserDetailActivity::class, sharedViews) {
            putExtra(USER_ID, userId)
        }
    }

    companion object {
        const val USER_ID = "user-id"
    }

}
