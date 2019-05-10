package com.nakulov.exhentai.presentation.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.nakulov.exhentai.presentation.ExApplication.Companion.applicationComponent
import com.nakulov.exhentai.presentation.activity.NavigationDelegate
import com.nakulov.exhentai.presentation.di.HasComponent
import com.nakulov.exhentai.presentation.di.components.DaggerFragmentComponent
import com.nakulov.exhentai.presentation.di.components.FragmentComponent
import com.nakulov.exhentai.presentation.di.modules.FragmentModule
import timber.log.Timber

abstract class BaseFragment: Fragment(), HasComponent<FragmentComponent> {

    lateinit var fragmentComponent: FragmentComponent

    private var navigation: NavigationDelegate? = null

    override val component: FragmentComponent by lazy { fragmentComponent }

    abstract val viewId: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initInjector()
        fragmentComponent.inject(this)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        try {
            navigation = requireActivity() as NavigationDelegate
        }catch (e: ClassCastException) {
            Timber.e(e, "cannot get navigation delegate from activity: ${requireActivity().javaClass.simpleName}")
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(viewId, container, false)
    }

    protected fun restoreBundleState(savedInstanceState: Bundle?, restore: (Bundle) -> Unit) {
        (savedInstanceState ?: arguments)?.let(restore)
    }

    protected fun presentFragment(fragment: Fragment, removeLast: Boolean = false) {
        if (navigation != null) {
            navigation!!.presentFragment(fragment, removeLast)

            return
        }
        Timber.w("couldn't present new fragment, cause navigation is null, parent activity: ${requireActivity().javaClass.simpleName}")
    }

    protected fun closeCurrentFragment() {
        if (navigation != null) {
            navigation!!.closeCurrentFragment()

            return
        }
        Timber.w("couldn't close current fragment, cause navigation is null, parent activity: ${requireActivity().javaClass.simpleName}")
    }

    private fun initInjector() {
        fragmentComponent = DaggerFragmentComponent.builder()
            .applicationComponent(applicationComponent)
            .fragmentModule(FragmentModule(this))
            .build()
    }

}