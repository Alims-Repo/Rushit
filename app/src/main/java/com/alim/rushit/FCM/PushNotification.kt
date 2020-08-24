package com.alim.rushit.FCM

data class PushNotification (
    val data: NotificationData,
    val to: String
)