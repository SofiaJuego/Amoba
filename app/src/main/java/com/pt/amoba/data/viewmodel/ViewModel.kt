package com.pt.amoba.data.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.pt.amoba.data.api.Response
import com.pt.amoba.data.api.ResponseLogin
import com.pt.amoba.data.api.User
import com.pt.amoba.data.database.AppDatabase
import com.pt.amoba.data.repositories.RepositoryFirebase
import com.pt.amoba.data.repositories.RepositoryRoom
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class ViewModel(application: Application) : AndroidViewModel(application) {
    private val dao = AppDatabase.getDatabase(application).patientsDao()
    private var repositoryRoom: RepositoryRoom = RepositoryRoom(dao)
    private var repositoryFirebase: RepositoryFirebase = RepositoryFirebase(repositoryRoom)
    var mainState: MutableLiveData<Boolean> = MutableLiveData()

    @OptIn(DelicateCoroutinesApi::class)
    fun liveDataSession() {
        GlobalScope.launch {
            mainState.postValue(repositoryFirebase.verifyToken())
        }
    }

    fun loginUser(email: String, pass: String, responseRepository: ResponseLogin) {
        val user = User(email, pass, "")
        repositoryFirebase.singIn(user, responseRepository)
    }

    fun logout() {
        repositoryFirebase.logout()
    }

    fun getAllPatients(responseRepository: Response) {
        repositoryFirebase.getAllPatients(responseRepository)
    }

}