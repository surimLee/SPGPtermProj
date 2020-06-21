package kr.ac.kpu.ce2016152030.spgptermproj;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class TwoPlayerActivity extends AppCompatActivity {


    public EditText plyr1;
    private EditText plyr2;
    public CharSequence player1 = "Player 1";
    public CharSequence player2 = "Player 2";
    public boolean selectedsingleplayer = false;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two_player);
        final MediaPlayer clickSound = MediaPlayer.create(this, R.raw.waterclick);

        plyr1 = (EditText)findViewById(R.id.player1);
        plyr1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int count, int after) {
                player1 = s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        plyr2 = (EditText) findViewById(R.id.player2);
        plyr2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int count, int after) {
                player2 = s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        Button gotoGame = findViewById(R.id.btn_continue);
        gotoGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(TwoPlayerActivity.this, SelectActivity.class);
                i.putExtra("selectedsingleplayer", selectedsingleplayer);
                CharSequence[] players = {player1, player2};
                i.putExtra("playersnames", players);

                startActivity(i);
                clickSound.start();
            }
        });

    }

    @Override
    public void onBackPressed() {
//        stopService(new Intent(getApplicationContext(), MusicService.class));
        Intent intent = new Intent(TwoPlayerActivity.this, MainActivity.class);
        startActivity(intent);
    }

}
