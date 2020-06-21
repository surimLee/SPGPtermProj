package kr.ac.kpu.ce2016152030.spgptermproj;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class TwoPlayerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two_player);
    }

    @Override
    public void onBackPressed() {
//        stopService(new Intent(getApplicationContext(), MusicService.class));
        Intent intent = new Intent(TwoPlayerActivity.this, MainActivity.class);
        startActivity(intent);
    }

}
