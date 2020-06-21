package kr.ac.kpu.ce2016152030.spgptermproj;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class OnePlayerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_player);
    }

    @Override
    public void onBackPressed() {
//        stopService(new Intent(getApplicationContext(), MusicService.class));
        Intent intent = new Intent(OnePlayerActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
