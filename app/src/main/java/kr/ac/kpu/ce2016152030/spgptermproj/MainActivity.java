package kr.ac.kpu.ce2016152030.spgptermproj;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final MediaPlayer clickSound = MediaPlayer.create(this, R.raw.waterclick);

        startService(new Intent(getApplicationContext(), MusicService.class));

        Button withFriend = findViewById(R.id.bt1);
        withFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickSound.start();
                Intent intent = new Intent(MainActivity.this, TwoPlayerActivity.class);
                startActivity(intent);
//                startService(new Intent(getApplicationContext(), MusicService.class));
            }
        });

        Button withAi = findViewById(R.id.bt2);
        withAi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickSound.start();
                Intent intent2 = new Intent(MainActivity.this, OnePlayerActivity.class);
                startActivity(intent2);
//                startService(new Intent(getApplicationContext(), MusicService.class));
            }
        });

        ImageButton imageButton = findViewById(R.id.imageButton);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickSound.start();
                Intent intent3 = new Intent(MainActivity.this, SettingActivity.class);
                startActivity(intent3);
            }
        });


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
    }
}