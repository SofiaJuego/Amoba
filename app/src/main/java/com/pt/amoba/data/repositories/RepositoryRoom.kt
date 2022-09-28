package com.pt.amoba.data.repositories

import com.pt.amoba.data.api.User
import com.pt.amoba.data.database.UserDao

class RepositoryRoom(private val userDao: UserDao) {

    fun insertUser(user: User) {
        userDao.registerUser(user)
    }

    fun removeUserLogged() {
        userDao.deleteUserLogged()
    }

    suspend fun getUserLocal(): List<User> {
        return userDao.getLastUser(1)
    }
}