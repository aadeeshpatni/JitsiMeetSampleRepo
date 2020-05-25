package com.example.dummy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import org.jitsi.meet.sdk.JitsiMeet
import org.jitsi.meet.sdk.JitsiMeetActivity
import org.jitsi.meet.sdk.JitsiMeetConferenceOptions
import java.lang.RuntimeException
import java.net.MalformedURLException
import java.net.URL

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val joinButton = findViewById<Button>(R.id.buttonJoinMeeting)
        val meetingName = findViewById<EditText>(R.id.editTextMeetingName)


        val url: URL
        url = try {
            URL("https://meet.jit.si")
        }catch (e: MalformedURLException){
            e.printStackTrace()
            throw RuntimeException("Invalid URL")
        }

        val defaultOptions = JitsiMeetConferenceOptions.Builder()
            .setServerURL(url)
            .setWelcomePageEnabled(false)
            .build()
        JitsiMeet.setDefaultConferenceOptions(defaultOptions)

        joinButton.setOnClickListener {
            val text = meetingName.text.toString()
            if(text.isNotEmpty()){
                val options = JitsiMeetConferenceOptions.Builder()
                    .setRoom(text)
                    .build()
                JitsiMeetActivity.launch(this, options)
            }
        }
    }
}
