package com.kiyansoftech.student.model.MyClassmates

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




class Data {
    @SerializedName("id")
    @Expose
    private var id: String? = null

    @SerializedName("class_room_id")
    @Expose
    private var classRoomId: String? = null

    @SerializedName("user_id")
    @Expose
    private var userId: String? = null

    @SerializedName("secreat_key")
    @Expose
    private var secreatKey: String? = null

    @SerializedName("isActive")
    @Expose
    private var isActive: String? = null

    @SerializedName("name")
    @Expose
    private var name: String? = null

    @SerializedName("phone")
    @Expose
    private var phone: String? = null

    @SerializedName("email")
    @Expose
    private var email: String? = null

    @SerializedName("password")
    @Expose
    private var password: String? = null

    @SerializedName("Usertype")
    @Expose
    private var usertype: String? = null

    @SerializedName("ip_address")
    @Expose
    private var ipAddress: String? = null

    @SerializedName("secret_key")
    @Expose
    private var secretKey: String? = null

    @SerializedName("fcm_token")
    @Expose
    private var fcmToken: String? = null

    @SerializedName("create_on")
    @Expose
    private var createOn: String? = null

    @SerializedName("update_on")
    @Expose
    private var updateOn: String? = null

    @SerializedName("isDelete")
    @Expose
    private var isDelete: String? = null

    fun getId(): String? {
        return id
    }

    fun setId(id: String?) {
        this.id = id
    }

    fun getClassRoomId(): String? {
        return classRoomId
    }

    fun setClassRoomId(classRoomId: String?) {
        this.classRoomId = classRoomId
    }

    fun getUserId(): String? {
        return userId
    }

    fun setUserId(userId: String?) {
        this.userId = userId
    }

    fun getSecreatKey(): String? {
        return secreatKey
    }

    fun setSecreatKey(secreatKey: String?) {
        this.secreatKey = secreatKey
    }

    fun getIsActive(): String? {
        return isActive
    }

    fun setIsActive(isActive: String?) {
        this.isActive = isActive
    }

    fun getName(): String? {
        return name
    }

    fun setName(name: String?) {
        this.name = name
    }

    fun getPhone(): String? {
        return phone
    }

    fun setPhone(phone: String?) {
        this.phone = phone
    }

    fun getEmail(): String? {
        return email
    }

    fun setEmail(email: String?) {
        this.email = email
    }

    fun getPassword(): String? {
        return password
    }

    fun setPassword(password: String?) {
        this.password = password
    }

    fun getUsertype(): String? {
        return usertype
    }

    fun setUsertype(usertype: String?) {
        this.usertype = usertype
    }

    fun getIpAddress(): String? {
        return ipAddress
    }

    fun setIpAddress(ipAddress: String?) {
        this.ipAddress = ipAddress
    }

    fun getSecretKey(): String? {
        return secretKey
    }

    fun setSecretKey(secretKey: String?) {
        this.secretKey = secretKey
    }

    fun getFcmToken(): String? {
        return fcmToken
    }

    fun setFcmToken(fcmToken: String?) {
        this.fcmToken = fcmToken
    }

    fun getCreateOn(): String? {
        return createOn
    }

    fun setCreateOn(createOn: String?) {
        this.createOn = createOn
    }

    fun getUpdateOn(): String? {
        return updateOn
    }

    fun setUpdateOn(updateOn: String?) {
        this.updateOn = updateOn
    }

    fun getIsDelete(): String? {
        return isDelete
    }

    fun setIsDelete(isDelete: String?) {
        this.isDelete = isDelete
    }

}