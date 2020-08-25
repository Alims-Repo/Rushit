package com.alim.rushit

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Button
import android.widget.EditText
import com.alim.rushit.Config.Config
import com.alim.rushit.FCM.NotificationData
import com.alim.rushit.FCM.PushNotification
import com.alim.rushit.FCM.RetrofitInstance
import com.alim.rushit.Services.FirebaseService
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.android.synthetic.main.activity_post.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PostActivity : AppCompatActivity() {

    val TOPIC = "/topics/myTopic2"
    val TAG = "POST ACTIVITY"
    lateinit var postButton: Button
    lateinit var postText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post)
        close.setOnClickListener { finish() }
        FirebaseService.sharedPref = getSharedPreferences("sharedPref", Context.MODE_PRIVATE)
        FirebaseInstanceId.getInstance().instanceId.addOnSuccessListener {
            FirebaseService.token = it.token
        }

        //FirebaseMessaging.getInstance().subscribeToTopic(TOPIC)
        //FirebaseMessaging.getInstance().unsubscribeFromTopic(TOPIC)

        postText = findViewById(R.id.post_text)
        postButton = findViewById(R.id.post)

        postText.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                postButton.isEnabled = p0.toString().isNotEmpty() }
            override fun afterTextChanged(p0: Editable?) {}
        })

        postButton.setOnClickListener {
            PushNotification(
                NotificationData("New post from : Alim Sourav",
                    postText.text.toString(),
                    "","PostActivity"),
                TOPIC
            ).also {
                sendNotification(it)
            }
        }
    }

    private fun sendNotification(notification: PushNotification)
            = CoroutineScope(Dispatchers.IO).launch {
        try {
            val response = RetrofitInstance.api.postNotification(notification)
            if(response.isSuccessful) {
                finish()
                Log.e(TAG, "Response: Successful")
                //Log.e(TAG, "Response: ${Gson().toJson(response)}")
            } else {
                Log.e(TAG, "Response: Failed")
                Log.e(TAG, response.errorBody().toString())
            }
        } catch(e: Exception) {
            Log.e(TAG, e.toString())
        }
    }
}