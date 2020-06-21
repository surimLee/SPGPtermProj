package kr.ac.kpu.ce2016152030.spgptermproj;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

public class OnePlayerActivity extends AppCompatActivity {

    private EditText plyr1;

    public CharSequence player1 = "1";
    public CharSequence player2 = "2";

    private int length;
    public boolean selectedsingleplayer = true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_player);

        plyr1 = (EditText) findViewById(R.id.playername);
        plyr1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                player1 = s.toString();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                length = plyr1.getText().length();
            }
        }, 0, 2);

        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (length > 1) {
                    Button BtnContinue = findViewById(R.id.btn_continue3);
                    BtnContinue.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i = new Intent(OnePlayerActivity.this, SelectActivity.class);
                            CharSequence[] players = {player1, player2};
                            i.putExtra("playersnames", players);
                            i.putExtra("selectedsingleplyer", selectedsingleplayer);
                            startActivity(i);
                        }
                    });

                }
            }
        }, 0, 20);

    }
    @Override
    public void onBackPressed() {
//        stopService(new Intent(getApplicationContext(), MusicService.class));
        Intent intent = new Intent(OnePlayerActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
