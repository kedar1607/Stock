package kedar.com.testapp.services

import android.arch.lifecycle.LiveData

interface ConnectionEstablisher {
    fun connectionStarted(data: LiveData<String>)
}