package erick.tijerou.socialapp.presentation.ui.userdetail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.Observable
import androidx.view.doOnPreDraw
import erick.tijerou.socialapp.R
import erick.tijerou.socialapp.presentation.ui.extension.observe
import erick.tijerou.socialapp.presentation.ui.userlist.UserListActivity.Companion.USER_ID
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber
import erick.tijerou.socialapp.databinding.ActivityUserDetailBinding as Binding

class UserDetailActivity : AppCompatActivity() {

    private val binding by lazy {
        DataBindingUtil.setContentView<Binding>(this, R.layout.activity_user_detail)
    }

    private val viewModel by viewModel<UserDetailViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportPostponeEnterTransition()

        intent.extras?.let {
            if(intent.hasExtra(USER_ID)) {
                viewModel.getUser(it.getLong(USER_ID))
            }
        }

        this.let { activity ->
            binding.apply {
                lifecycleOwner = activity
                viewModel = activity.viewModel
            }
        }

        setupToolbar()
        observeError()
        viewModel.user.observe(this) {
            (window.decorView as ViewGroup).doOnPreDraw {
                supportStartPostponedEnterTransition()
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) finish()
        return super.onOptionsItemSelected(item)
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            title = null
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }
    }

    private fun observeError() {
        viewModel.error.observe(this) {
            Timber.e(it)
        }
    }

}
