package com.pt.amoba.data.api

import java.io.Serializable

class Patients : Serializable {
    lateinit var name: String
    lateinit var surname: String
    lateinit var ci: String
    var age: Int = 0
    var month: Int = 0
    lateinit var sex: String
    lateinit var email: String
    lateinit var address: String
    var phone: Int = 0
    var mobile: Int = 0
}
