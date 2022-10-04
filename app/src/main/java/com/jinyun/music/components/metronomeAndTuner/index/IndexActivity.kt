package com.jinyun.music.components.metronomeAndTuner.index

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.jinyun.music.R
import com.jinyun.music.databinding.ActivityMatIndexBinding

class IndexActivity : AppCompatActivity() {
    companion object {
        fun newInstance() = IndexActivity()
        private val tunerF = com.jinyun.music.components.metronomeAndTuner.tuner.IndexFragment()
        private val metronomeF =
            com.jinyun.music.components.metronomeAndTuner.metronome.IndexFragment()
    }

    private lateinit var viewModel: IndexViewModel
    private lateinit var binding: ActivityMatIndexBinding

    // tabs
    private val fragments by lazy {
        listOf(tunerF, metronomeF)
    }
    private val tabTitles by lazy {
        listOf(getString(R.string.tuner), getString(R.string.metronome))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_mat_index)
        viewModel = ViewModelProvider(this).get(IndexViewModel::class.java)

        val fragmentStateAdapter: FragmentStateAdapter = object : FragmentStateAdapter(this) {
            override fun getItemCount(): Int {
                return fragments.size
            }

            override fun createFragment(position: Int): Fragment {
                return fragments[position]
            }
        }

        val viewPager = binding.mATViewPager
        val tabLayout = binding.mATTabLayout

        viewPager.adapter = fragmentStateAdapter

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = tabTitles[position]
        }.attach()
    }
}