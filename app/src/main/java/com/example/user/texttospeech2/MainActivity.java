package com.example.user.texttospeech2;

import android.content.pm.FeatureGroupInfo;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {


    TextToSpeech ttsobj;
    int result;
    EditText et;
    String text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et=(EditText)findViewById(R.id.edtext);
        ttsobj= new TextToSpeech(MainActivity.this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status==TextToSpeech.SUCCESS){

                    result=ttsobj.setLanguage(Locale.UK);

                }

                else{
                    Toast.makeText(getApplicationContext(), "Feature not supported in your device",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public void doSome(View v){

        switch (v.getId())
        {
            case R.id.btnspeak:
                if(result==TextToSpeech.LANG_NOT_SUPPORTED||result==TextToSpeech.LANG_MISSING_DATA){

                    Toast.makeText(getApplicationContext(),
                            "Feature not supported in your device",
                            Toast.LENGTH_SHORT).show();

                }
                else
                {   text= et.getText().toString();
                    ttsobj.speak(text,TextToSpeech.QUEUE_FLUSH,null);
                }



                break;
            case R.id.btnstop:
                if(ttsobj!=null)
                {
                    ttsobj.stop();
                }



                break;
        }


    }

    protected void onDestroy(){
        super.onDestroy();
        if(ttsobj!=null){
            ttsobj.stop();
            ttsobj.shutdown();
        }
    }




}




