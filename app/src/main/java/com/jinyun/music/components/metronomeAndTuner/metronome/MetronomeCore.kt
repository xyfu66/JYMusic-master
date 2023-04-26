package com.jinyun.music.components.metronomeAndTuner.metronome

import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject
import java.util.concurrent.TimeUnit

class MetronomeCore {
    var scheduler: Scheduler? = null
    private var delayIsChange = false
    private var delay: Long = 0

    companion object {
        private var mBpm = 100
        private var mX = 4
        private var mY = 4
        private val numBeats = mX
        private var beat = 0
        private val firstDelay = 0

        private val delayObservable: BehaviorSubject<Long> = BehaviorSubject.create()
        private val beatObservable: BehaviorSubject<Long> = BehaviorSubject.create()
        private val playStateObservable: BehaviorSubject<Boolean> = BehaviorSubject.create()
        private val stopTrigger: PublishSubject<Any> = PublishSubject.create()
    }


    constructor() {
        setConfig(mBpm, mX, mY);
    }

    fun togglePlay() {
        if (isPlaying()) {
            stop()
        } else {
            play()
        }
    }

    private fun setDelay(delay: Long) {
        this.delay = delay
        delayObservable.onNext(delay)
        restartIfPlaying()
    }

    private fun isPlaying(): Boolean {
        return java.lang.Boolean.TRUE == playStateObservable.value
    }

    fun setConfig(x: Int, y: Int) {
        setConfig(mBpm, x, y)
    }

    fun setConfig(pdm: Int) {
        setConfig(pdm, mX, mY)
    }

    fun setConfig(pdm: Int, x: Int, y: Int) {
        if (mBpm == 0 || x == 0 || y == 0) {
            return
        }
        mBpm = pdm
        mX = x
        mY = y
        //        int  newDelay=(int)(((1000*60.0)/ mBpm)*(1.0*mX/mY));
//        if(newDelay==delay){
//            return;
//        }
        delay = (1000 * 60.0 / mBpm * (1.0 * mX / mY)).toLong()
        delayIsChange = true
//        setDelay(delay);
    }

    private fun play() {
        playStateObservable.onNext(true)
        Observable
            .interval(delay, TimeUnit.MILLISECONDS, scheduler)
            .takeUntil(stopTrigger)
            .subscribe()
//            .subscribe(object : Action1<Long?>() {
//                fun call(o: Long?) {
////                        Log.e("LDQ","111111111111     beat="+beat);
////                        courentTime= SystemClock.currentThreadTimeMillis();
//                    beat++
//                    beatObservable.onNext(beat)
//                    if (beat == numBeats) {
//                        beat = 0
//                    }
//                    if (delayIsChange) {
//                        delayIsChange = false
//                        setDelay(delay)
//                    }
//                }
//            })
    }

    private fun stop() {
        //stopTrigger.onNext(null)
        stopTrigger.onNext(0)
        beat = 0
        playStateObservable.onNext(false)
    }

    fun stopPlay() {
        if (isPlaying()) {
            stop()
        }
    }

    private fun restartIfPlaying() {
        if (isPlaying()) {
            stopTrigger.onNext(0)
            //stopTrigger.onNext(null)
            play()
        }
    }

}