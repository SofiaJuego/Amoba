package com.pt.amoba.data.api

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "User")
@Parcelize
data class User(
    @PrimaryKey(autoGenerate = false)
    var email: String,
    var password: String,
    var token: String
) : Parcelable