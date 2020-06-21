package kr.ac.kpu.ce2016152030.spgptermproj;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class SceneActivity extends AppCompatActivity {

    boolean easy = true;
    boolean medium;
    boolean hard;
    boolean impossible;

    boolean wins;
    boolean returns;

    boolean player1ax;
    Random r = new Random();

    int flag = 0, ax = 10, zero = 1, sensorflag = 0, win = 0, i, game = 1, prevrow, prevcol;
    int summ = 0, ctrflag = 0, night = 0, resetchecker = 1, currentgamedonechecker = 0;
    int score1 = 0, score2 = 0, drawchecker = 0;
    static int[][] tracker = new int[3][3];
    int[] sum = new int[8];
    static int[][] buttonpressed = new int[3][3];
    boolean selectedsingleplayer;
    private int savedValue;

    CharSequence player1 = "Player 1";
    CharSequence player2 = "Player 2";

    private boolean Vibration;
    private static final String PREFS_NAME = "vibration";
    private static final String PREF_VIBRATION = "TicVib";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scene);

        final MediaPlayer clickSound = MediaPlayer.create(this, R.raw.waterclick);

        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {

                SharedPreferences preferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
                Vibration = preferences.getBoolean(PREF_VIBRATION, true);


                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                savedValue = sharedPreferences.getInt("key", 0);

            }
        }, 0, 2);//put here time 1000 milliseconds = 1 second

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow(); // in Activity's onCreate() for instance
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }

        final ImageButton imageButton = findViewById(R.id.imageButton2);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                clickSound.start();
                Intent intent3 = new Intent(SceneActivity.this, SettingActivity.class);
                startActivity(intent3);

            }
        });

        CharSequence[] players = getIntent().getCharSequenceArrayExtra("playersnames");

        player1 = players[0];
        player2 = players[1];

        player1ax = getIntent().getBooleanExtra("player1ax", true);
        selectedsingleplayer = getIntent().getBooleanExtra("selectedsingleplayer", true);
        if (player1ax) {
            ax = 1;
            zero = 10;
        }
        TextView textName = findViewById(R.id.player1name);
        TextView textName2 = findViewById(R.id.player2name);


        textName.setText(player1);

        if (!selectedsingleplayer){
            textName2.setText(player2);
            Toast.makeText(this, "" + player1 + "\'s turn", Toast.LENGTH_SHORT).show();}


    }

    public void c1x1(View view) {
    }

    public void c1x2(View view) {
    }

    public void c1x3(View view) {
    }

    public void c2x1(View view) {
    }

    public void c2x2(View view) {
    }

    public void c2x3(View view) {
    }

    public void c3x1(View view) {
    }

    public void c3x2(View view) {
    }

    public void c3x3(View view) {
    }

}
