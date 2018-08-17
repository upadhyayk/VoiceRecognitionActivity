package com.example.krati.voicerecognitionactivity;

import android.content.Intent;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    ImageButton imageButton;
    ArrayList<String> results;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageButton = (ImageButton) findViewById(R.id.speechButton);
        textView = (TextView) findViewById(R.id.setSpeechText);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                //specify free form input
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Please start speaking");
                intent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 1);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.ENGLISH);

                startActivityForResult(intent, 2);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode == 2){
            results = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            String text = results.get(0);

            switch (text){
                case "start":
                    Toast.makeText(MainActivity.this, "Starting Board", Toast.LENGTH_SHORT).show();
                    break;
                case "stop":
                    Toast.makeText(MainActivity.this, "Stopping Board", Toast.LENGTH_SHORT).show();
                    break;
                case "speed up":
                    Toast.makeText(MainActivity.this, "Speeding Up", Toast.LENGTH_SHORT).show();
                    break;
                case "slow down":
                    Toast.makeText(MainActivity.this, "Slowing Down", Toast.LENGTH_SHORT).show();
                    break;
                case "turn right":
                    Toast.makeText(MainActivity.this, "Turning Right", Toast.LENGTH_SHORT).show();
                    break;
                case "turn left":
                    Toast.makeText(MainActivity.this, "Turning Left", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    Toast.makeText(MainActivity.this, "Did not work", Toast.LENGTH_SHORT).show();
                    break;
            }

            textView.setText(text);
        }
    }
}
