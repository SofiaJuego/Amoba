package com.pt.amoba.data.repositories

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.pt.amoba.data.api.Patients
import com.pt.amoba.data.api.Response
import com.pt.amoba.data.api.ResponseLogin
import com.pt.amoba.data.api.User

class RepositoryFirebase(private var repositoryRoom: RepositoryRoom) {
    private var database: DatabaseReference = Firebase.database.reference
    private val keyPatients = "PATIENTS"

    fun singIn(user: User, response: ResponseLogin) {
        response.onLoading()
        FirebaseAuth.getInstance().signInWithEmailAndPassword(user.email, user.password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    saveTokenUser(user)
                    response.onSucessfull()
                } else if (it.isCanceled) {
                    response.onError()
                } else {
                    response.onError()
                }
            }
    }

    fun logout() {
        FirebaseAuth.getInstance().signOut()
        repositoryRoom.removeUserLogged()
    }

    fun getAllPatients(response: Response) {
        response.onLoading()
        database.child(keyPatients).get().addOnSuccessListener { data ->

            val listAllPatients = ArrayList<Patients>()

            data.children.forEach { patientsData ->
                val patients = patientsData.getValue(Patients::class.java)
                if (patients != null) {
                    listAllPatients.add(patients)
                }
                response.onSuccessful(listAllPatients)
            }
        }
    }

    private fun saveTokenUser(user: User) {
        val firebaseUser = FirebaseAuth.getInstance().currentUser
        firebaseUser?.getIdToken(true)?.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val idToken: String? = task.result.token
                if (idToken != null) {
                    user.token = idToken
                    repositoryRoom.insertUser(user)
                }
            }
        }
    }

    suspend fun verifyToken(): Boolean {
        val sessions = repositoryRoom.getUserLocal()
        if (sessions.isNotEmpty()) {
            val userLocal = sessions[0]
            return userLocal.token.isNotBlank()
        }
        return false
    }
}