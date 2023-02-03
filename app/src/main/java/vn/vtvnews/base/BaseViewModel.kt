package vn.vtvnews.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.delay
import java.util.*

open class BaseViewModel : ViewModel() {
    suspend fun delayLoadingAtLeast(timeStart: Long, timeEnd: Long, atLeastSec: Int) {
        val atLeast = atLeastSec * 1000 - (timeEnd - timeStart)
        if (atLeast > 0)
            delay(atLeast)
    }

    fun getCurrentTime() = Calendar.getInstance().time.time
}