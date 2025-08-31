package com.example.firsttask;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Locale;

public class MicActivity extends AppCompatActivity
{
    TextView tvText;
    ImageView ivMic;
    TextToSpeech textToSpeech;
    private static final int REQUEST_CODE_SPEECH_INPUT = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_mic);
        tvText = findViewById(R.id.tvMicText);
        ivMic = findViewById(R.id.ivHomeMic);

        ivMic.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);

                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
                intent.putExtra(RecognizerIntent.EXTRA_PROMPT,"Speak Now");
                try
                {
                 startActivityForResult(intent,REQUEST_CODE_SPEECH_INPUT);
                }
                catch (Exception e)
                {
                    Toast.makeText(MicActivity.this, ""+e, Toast.LENGTH_SHORT).show();
                }
            }

        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_SPEECH_INPUT)
        {
            if (resultCode==RESULT_OK&&data!=null)
            {
                ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                tvText.setText(result.get(0));
                textToSpeech=new TextToSpeech(MicActivity.this, new TextToSpeech.OnInitListener()
                {
                    @Override
                    public void onInit(int i)
                    {
                        textToSpeech.setLanguage(Locale.US);
                        textToSpeech.speak(result.get(0),TextToSpeech.QUEUE_FLUSH,null,null);
                        textToSpeech.setSpeechRate(15);
                        textToSpeech.setPitch(5);
                    }
                });
            }
        }
    }
}