package com.jinyun.music.components.metronomeAndTuner.metronome

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.jakewharton.rxbinding3.view.clicks
import com.jakewharton.rxbinding3.view.layoutChangeEvents
import com.jinyun.music.R
import io.reactivex.Scheduler
import org.reactivestreams.Subscription
import java.util.concurrent.TimeUnit

/**
 * 节拍器
 */
class IndexFragment : Fragment() {


    companion object {
        fun newInstance() = IndexFragment()
    }

    private lateinit var viewModel: IndexViewModel
    private lateinit var rotateControlView: RotateControlView
    private lateinit var beatStartIb: ImageButton
    private lateinit var playStateSubscription: Subscription
    private var mainThreadScheduler: Scheduler? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_mat_metronome_index, container, false)
        rotateControlView = view.findViewById(R.id.rotate)
        beatStartIb = view.findViewById(R.id.beat_start_ib)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(IndexViewModel::class.java)

        beatStartIb.clicks()
            .throttleFirst(1, TimeUnit.SECONDS)
            .subscribe {

            }


    }
}