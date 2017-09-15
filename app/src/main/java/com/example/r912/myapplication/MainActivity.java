package com.example.r912.myapplication;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import kr.ac.sogang.speech.sgspeechapi.SgRecognizerClient;
import kr.ac.sogang.speech.sgspeechapi.SgRecognizerListener;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView TextBox;
    private SgRecognizerClient sr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button speakButton = (Button)findViewById(R.id.button2);
        TextBox = (TextView)findViewById(R.id.textView2);
        speakButton.setOnClickListener(this);

        sr = new SgRecognizerClient(this, listener);
        checkPermission();

    }

    private SgRecognizerListener listener = new SgRecognizerListener() {
        @Override
        public void onStart() {

        }

        @Override
        public void onAudioLevel(double v) {

        }

        @Override
        public void onStartOfSpeech() {

        }

        @Override
        public void onEndOfSpeech() {

        }

        @Override
        public void onResults(List<String> list) {
            TextBox.setText("results:" + String.valueOf(list));
        }

        @Override
        public void onError(int i) {
            TextBox.setText("Error: "+ String.valueOf(i));
        }
    };

    public void onClick(View v){
        if(v.getId()==R.id.button2){
            sr.start();
        }
    }

    private void checkPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.RECORD_AUDIO)
                    != PackageManager.PERMISSION_GRANTED
                    || checkSelfPermission(Manifest.permission.INTERNET)
                    != PackageManager.PERMISSION_GRANTED
                    || checkSelfPermission(Manifest.permission.ACCESS_NETWORK_STATE)
                    != PackageManager.PERMISSION_GRANTED
                    ) {

                // Should we show an explanation?
                if (shouldShowRequestPermissionRationale(Manifest.permission.RECORD_AUDIO)) {
                    // Explain to the user why we need to write the permission.
                    Toast.makeText(this, "마이크 권한 획득", Toast.LENGTH_SHORT).show();
                }

                requestPermissions(new String[]{Manifest.permission.RECORD_AUDIO, Manifest.permission.INTERNET, Manifest.permission.ACCESS_NETWORK_STATE},
                        0);

                // MY_PERMISSION_REQUEST_STORAGE is an
                // app-defined int constant

            } else {
                // 다음 부분은 항상 허용일 경우에 해당이 됩니다.
                //
            }
        }
    }

}
