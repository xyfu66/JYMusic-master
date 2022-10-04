package com.jinyun.music.navs.practice.index

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.jinyun.music.R
import com.jinyun.music.databinding.FragmentPracticeIndexBinding

class IndexFragment : Fragment() {

    companion object {
        fun newInstance() = IndexFragment()
    }

    private lateinit var viewModel: IndexViewModel
    private lateinit var binging: FragmentPracticeIndexBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binging =
            DataBindingUtil.inflate(inflater, R.layout.fragment_practice_index, container, false)
        return binging.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(IndexViewModel::class.java)

        binging.data = viewModel
        binging.lifecycleOwner = this

        binging.accessTunerBtn.setOnClickListener {
            startActivity(
                Intent(
                    activity,
                    com.jinyun.music.components.metronomeAndTuner.index.IndexActivity::class.java
                )
            )
        }
    }

}