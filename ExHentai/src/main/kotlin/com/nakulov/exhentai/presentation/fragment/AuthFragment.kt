package com.nakulov.exhentai.presentation.fragment

import android.os.Bundle
import android.view.View
import com.nakulov.exhentai.R

class AuthFragment : BaseFragment() {

    override val viewId: Int = R.layout.fragment_auth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fragmentComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

    }

    companion object {
        fun newInstance() : AuthFragment {
            return AuthFragment()
        }
    }

}