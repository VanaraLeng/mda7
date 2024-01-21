package com.vanaraleng.gardening.views
import android.os.Bundle
import androidx.fragment.app.Fragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

abstract class BaseFragment : Fragment(),CoroutineScope{
    // Instance in the Coroutine Context
    private lateinit var job: Job
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Create an Instance for the Job()
        job = Job()
    }

    override fun onDestroy() {
        super.onDestroy()
        // Cancel the Job
        job.cancel()
    }
}