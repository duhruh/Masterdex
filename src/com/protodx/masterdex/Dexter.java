package com.protodx.masterdex;

import java.util.Locale;

import android.app.Activity;
import android.content.Intent;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class Dexter implements OnClickListener, OnInitListener {

	private Activity activity;
	public TextToSpeech myTTS = null;
	private int MY_DATA_CHECK_CODE = 1234;
	private Intent checkTTSIntent;
	public String speech;
	
	public Dexter(Activity activity, String speech, TextToSpeech myTTs){
		this.activity = activity;
		checkTTSIntent = new Intent();
        checkTTSIntent.setAction(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);
        activity.startActivityForResult(checkTTSIntent, MY_DATA_CHECK_CODE);
        this.speech = speech;
        this.myTTS = myTTs;
	}
	
	private void speakWords() {
        myTTS.speak(speech, TextToSpeech.QUEUE_FLUSH, null);
	}
	public void onInit(int initStatus) {
		if (initStatus == TextToSpeech.SUCCESS) {
            if(myTTS.isLanguageAvailable(Locale.US)==TextToSpeech.LANG_AVAILABLE)
                myTTS.setLanguage(Locale.US);
        }
        else if (initStatus == TextToSpeech.ERROR) {
            Toast.makeText(activity, "Sorry! Text To Speech failed...", Toast.LENGTH_LONG).show();
        }
	}

	public void onClick(View v) {
		speakWords();
	}
	public void shutdownTTS(){
		if (myTTS != null)
        {
			myTTS.stop();
			myTTS.shutdown();
			Log.d("DexterTTS", "TTS Destroyed");
        }
	}
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		 
        if (requestCode == MY_DATA_CHECK_CODE) {
            if (resultCode == TextToSpeech.Engine.CHECK_VOICE_DATA_PASS) {
            	//myTTS = new TextToSpeech(activity, this);
            	//Do nothing they have a valid TTS engine
            }
            else {
                Intent installTTSIntent = new Intent();
                installTTSIntent.setAction(TextToSpeech.Engine.ACTION_INSTALL_TTS_DATA);
                activity.startActivity(installTTSIntent);
            }
        }
    }
}
