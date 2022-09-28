package com.pt.amoba.data.database


import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.pt.amoba.data.api.User


@Dao
interface UserDao {

    @Query("SELECT * FROM user")
    fun getUser(): LiveData<List<User>>

    @Insert(onConflict = REPLACE)
    fun registerUser(user: User)

    @Query("SELECT * FROM user LIMIT :limit")
    suspend fun getLastUser(limit: Int): List<User>

    @Query("DELETE FROM user")
    fun deleteUserLogged()
}