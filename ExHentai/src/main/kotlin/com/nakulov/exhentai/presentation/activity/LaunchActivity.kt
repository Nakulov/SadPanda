package com.nakulov.exhentai.presentation.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.nakulov.exhentai.R
import com.nakulov.exhentai.presentation.ExApplication.Companion.applicationComponent
import com.nakulov.exhentai.presentation.calculateDensity
import com.nakulov.exhentai.presentation.di.HasComponent
import com.nakulov.exhentai.presentation.di.components.ActivityComponent
import com.nakulov.exhentai.presentation.di.components.DaggerActivityComponent
import com.nakulov.exhentai.presentation.di.modules.ActivityModule
import kotlinx.android.synthetic.main.activity_launch.*

class LaunchActivity : AppCompatActivity(), HasComponent<ActivityComponent>, NavigationDelegate {

    private lateinit var activityComponent: ActivityComponent

    override val component: ActivityComponent = activityComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initInjectors()
        activityComponent.inject(this)
        calculateDensity()
        setContentView(R.layout.activity_launch)
    }

    private fun initInjectors() {
        activityComponent = DaggerActivityComponent.builder()
            .applicationComponent(applicationComponent)
            .activityModule(ActivityModule(this))
            .build()
    }

    override fun presentFragment(fragment: Fragment, removeLast: Boolean) {
        val last = supportFragmentManager.backStackEntryCount

        if (last > 0) {
            val tag = supportFragmentManager.getBackStackEntryAt(last - 1).name

            val currentFragment = supportFragmentManager.findFragmentByTag(tag) ?: return

            if (fragment.javaClass.canonicalName == currentFragment.javaClass.canonicalName) return
        }

        if (removeLast) supportFragmentManager.popBackStack()

        val stackSize = supportFragmentManager.backStackEntryCount

        val fragmentTransaction = supportFragmentManager.beginTransaction()

        if (stackSize == 0) fragmentTransaction.add(container.id, fragment, fragment.javaClass.simpleName)
        else {
            fragmentTransaction.setCustomAnimations(
                R.anim.enter_from_right, R.anim.exit_to_left,
                R.anim.enter_from_left, R.anim.exit_to_right
            )
            fragmentTransaction.replace(container.id, fragment, fragment.javaClass.simpleName)
        }

        fragmentTransaction.addToBackStack(fragment.javaClass.simpleName)
        fragmentTransaction.commit()
    }

    override fun closeCurrentFragment(): Boolean {
        onBackPressed()

        return false
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 1) super.onBackPressed()
        else finish()
    }
}

interface NavigationDelegate {
    fun presentFragment(fragment: Fragment, removeLast: Boolean = false)
    fun closeCurrentFragment(): Boolean
}
