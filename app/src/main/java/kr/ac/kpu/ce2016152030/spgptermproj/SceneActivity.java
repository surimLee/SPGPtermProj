package kr.ac.kpu.ce2016152030.spgptermproj;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;

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

    CharSequence player1 = "Player1";
    CharSequence player2 = "Player2";

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

        //setting
        final ImageButton imageButton = findViewById(R.id.imageButton2);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                clickSound.start();
                Intent intent3 = new Intent(SceneActivity.this, SettingActivity.class);
                startActivity(intent3);
            }
        });

        //get Name
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
        if (win == 0 && buttonpressed[0][0] == 0) {
            if (flag % 2 == 0)
                tracker[0][0] = ax;
            else
                tracker[0][0] = zero;
            printBoard();
            winchecker();
            cpuplay();
            flag++;
            buttonpressed[0][0]++;
        }
    }

    public void c1x2(View view) {
        if (win == 0 && buttonpressed[0][1] == 0) {
            if (flag % 2 == 0) tracker[0][1] = ax;
            else tracker[0][1] = zero;
            printBoard();
            winchecker();
            cpuplay();
            buttonpressed[0][1]++;
            flag++;
        }
    }

    public void c1x3(View view) {
        if (win == 0 && buttonpressed[0][2] == 0) {
            if (flag % 2 == 0) tracker[0][2] = ax;
            else tracker[0][2] = zero;
            printBoard();
            winchecker();
            cpuplay();
            buttonpressed[0][2]++;
            flag++;
        }
    }

    public void c2x1(View view) {
        if (win == 0 && buttonpressed[1][0] == 0) {
            if (flag % 2 == 0) tracker[1][0] = ax;
            else tracker[1][0] = zero;
            printBoard();
            winchecker();
            cpuplay();
            ++buttonpressed[1][0];
            flag++;
        }
    }

    public void c2x2(View view) {
        if (win == 0 && buttonpressed[1][1] == 0) {
            if (flag % 2 == 0) tracker[1][1] = ax;
            else tracker[1][1] = zero;
            printBoard();
            winchecker();
            cpuplay();
            ++buttonpressed[1][1];
            flag++;
        }
    }

    public void c2x3(View view) {
        if (win == 0 && buttonpressed[1][2] == 0) {
            if (flag % 2 == 0) tracker[1][2] = ax;
            else tracker[1][2] = zero;
            printBoard();
            winchecker();
            cpuplay();
            ++buttonpressed[1][2];
            flag++;
        }
    }

    public void c3x1(View view) {
        if (win == 0 && buttonpressed[2][0] == 0) {
            if (flag % 2 == 0) tracker[2][0] = ax;
            else tracker[2][0] = zero;
            printBoard();
            winchecker();
            cpuplay();
            ++buttonpressed[2][0];
            flag++;
        }
    }

    public void c3x2(View view) {
        if (win == 0 && buttonpressed[2][1] == 0) {
            if (flag % 2 == 0) tracker[2][1] = ax;
            else tracker[2][1] = zero;
            printBoard();
            winchecker();
            cpuplay();
            ++buttonpressed[2][1];
            flag++;
        }
    }

    public void c3x3(View view) {
        if (win == 0 && buttonpressed[2][2] == 0) {
            if (flag % 2 == 0) tracker[2][2] = ax;
            else tracker[2][2] = zero;
            printBoard();
            winchecker();
            cpuplay();
            ++buttonpressed[2][2];
            flag++;
        }
    }

////////////////////////////////////

public void cpuplay() {
    if ((selectedsingleplayer) && (win == 0)) {
        final Handler handler = new Handler();
        Timer t = new Timer();
        t.schedule(new TimerTask() {
            public void run() {
                handler.post(new Runnable() {
                    public void run() {
                        if (ifcpuwin()) ;
                        else if (ifopowin()) ;
                        else if (emptycentre()) ;
                        else if (emptycorner()) ;
                        else emptyany();

                        printBoard();
                        winchecker();

                        flag++;
                    }
                });
            }
        }, 110);
        return;
    }
}

    public boolean ifcpuwin() {
        for (i = 0; i < 8; i++) {
            if (sum[i] == 2 * zero) {
                if (i == 0) {
                    for (int x = 0; x < 3; x++)
                        if (tracker[0][x] == 0)
                            tracker[0][x] = zero;
                }
                if (i == 1) {
                    for (int x = 0; x < 3; x++)
                        if (tracker[1][x] == 0)
                            tracker[1][x] = zero;
                }
                if (i == 2) {
                    for (int x = 0; x < 3; x++)
                        if (tracker[2][x] == 0)
                            tracker[2][x] = zero;
                }
                if (i == 3) {
                    for (int x = 0; x < 3; x++)
                        if (tracker[x][0] == 0)
                            tracker[x][0] = zero;
                }
                if (i == 4) {

                    for (int x = 0; x < 3; x++)
                        if (tracker[x][1] == 0)
                            tracker[x][1] = zero;
                }
                if (i == 5) {

                    for (int x = 0; x < 3; x++)
                        if (tracker[x][2] == 0)
                            tracker[x][2] = zero;
                }
                if (i == 6) {

                    for (int y = 0; y < 3; y++)
                        for (int x = 0; x < 3; x++)
                            if (x == y)
                                if (tracker[x][y] == 0)
                                    tracker[x][y] = zero;
                }
                if (i == 7) {
                    if (tracker[0][2] == 0)
                        tracker[0][2] = zero;
                    else if (tracker[1][1] == 0)
                        tracker[1][1] = zero;
                    else tracker[2][0] = zero;
                }
                return true;
            }
        }
        return false;
    }

    public boolean ifopowin() {
        for (i = 0; i < 8; i++) {
            if (sum[i] == 2 * ax) {
                if (i == 0) {
                    for (int x = 0; x < 3; x++)
                        if (tracker[0][x] == 0) {
                            tracker[0][x] = zero;
                            buttonpressed[0][x]++;
                        }
                }

                if (i == 1) {
                    for (int x = 0; x < 3; x++)
                        if (tracker[1][x] == 0) {
                            tracker[1][x] = zero;
                            buttonpressed[1][x]++;
                        }
                }
                if (i == 2) {
                    for (int x = 0; x < 3; x++)
                        if (tracker[2][x] == 0) {
                            tracker[2][x] = zero;
                            buttonpressed[2][x]++;
                        }
                }

                if (i == 3) {
                    for (int x = 0; x < 3; x++)
                        if (tracker[x][0] == 0) {
                            tracker[x][0] = zero;
                            buttonpressed[x][0]++;
                        }
                }

                if (i == 4) {

                    for (int x = 0; x < 3; x++)
                        if (tracker[x][1] == 0) {
                            tracker[x][1] = zero;
                            buttonpressed[x][1]++;
                        }
                }

                if (i == 5) {

                    for (int x = 0; x < 3; x++)
                        if (tracker[x][2] == 0) {
                            tracker[x][2] = zero;
                            buttonpressed[x][2]++;
                        }
                }
                if (i == 6) {

                    for (int y = 0; y < 3; y++)
                        for (int x = 0; x < 3; x++)
                            if (x == y)
                                if (tracker[x][y] == 0) {
                                    tracker[x][y] = zero;
                                    buttonpressed[x][y]++;
                                }
                }
                if (i == 7) {
                    if (tracker[0][2] == 0) {
                        tracker[0][2] = zero;
                        buttonpressed[0][2]++;
                    } else if (tracker[1][1] == 0) {
                        tracker[1][1] = zero;
                        buttonpressed[1][1]++;
                    } else {
                        tracker[2][0] = zero;
                        buttonpressed[2][0]++;
                    }
                }
                return true;
            }
        }

        return false;
    }

    public boolean emptycentre() {
        if (tracker[1][1] == 0) {
            tracker[1][1] = zero;
            buttonpressed[1][1]++;
            return true;
        }
        return false;
    }

    public boolean emptycorner() {
        for (int i = 0; i < 3; i++) {
            if (tracker[0][i] == ax) {
                if (tracker[0][0] == 0) {
                    tracker[0][0] = zero;
                    buttonpressed[0][0]++;
                    return true;
                }
                if (tracker[0][2] == 0) {
                    tracker[0][2] = zero;
                    buttonpressed[0][2]++;
                    return true;
                }
            }
        }
        for (int i = 0; i < 3; i++) {
            if (tracker[2][i] == ax) {
                if (tracker[2][0] == 0) {
                    tracker[2][0] = zero;
                    buttonpressed[2][0]++;
                    return true;
                }
                if (tracker[2][2] == 0) {
                    tracker[2][2] = zero;
                    buttonpressed[2][2]++;
                    return true;
                }
            }
        }
        for (int i = 0; i < 3; i++) {
            if (tracker[i][0] == ax) {
                if (tracker[0][0] == 0) {
                    tracker[0][0] = zero;
                    buttonpressed[0][0]++;
                    return true;
                }
                if (tracker[2][0] == 0) {
                    tracker[2][0] = zero;
                    buttonpressed[2][0]++;
                    return true;
                }
            }
        }
        for (int i = 0; i < 3; i++) {
            if (tracker[i][2] == ax) {
                if (tracker[0][2] == 0) {
                    tracker[0][2] = zero;
                    buttonpressed[0][2]++;
                    return true;
                }
                if (tracker[2][2] == 0) {
                    tracker[2][2] = zero;
                    buttonpressed[2][2]++;
                    return true;
                }
            }
        }
        return false;
    }

    public void emptyany() {
        if (ctrflag == 0)
            while (true) {
                int x = rand();
                int y = rand();

                if (tracker[x][y] == 0) {
                    tracker[x][y] = zero;
                    buttonpressed[x][y]++;
                    return;
                }
            }
        for (int x = 0; x < 3; x++)
            for (int y = 0; y < 3; y++)
                if (tracker[x][y] == 0) {
                    tracker[x][y] = zero;
                    buttonpressed[x][y]++;
                    return;
                }
    }

    public int rand() {
        return r.nextInt(3);
    }

    public void printBoard() {
        ImageView q1, q2, q3, q4, q5, q6, q7, q8, q9;

        q1 = (ImageView) findViewById(R.id.c11);
        q2 = (ImageView) findViewById(R.id.c12);
        q3 = (ImageView) findViewById(R.id.c13);
        q4 = (ImageView) findViewById(R.id.c21);
        q5 = (ImageView) findViewById(R.id.c22);
        q6 = (ImageView) findViewById(R.id.c23);
        q7 = (ImageView) findViewById(R.id.c31);
        q8 = (ImageView) findViewById(R.id.c32);
        q9 = (ImageView) findViewById(R.id.c33);

        final MediaPlayer ingameclick = MediaPlayer.create(this, R.raw.ingameclick);
        ingameclick.start();

        if (tracker[0][0] == 1) q1.setImageResource(R.drawable.xs);
        if (tracker[0][0] == 10) q1.setImageResource(R.drawable.os);

        if (tracker[0][1] == 1) q2.setImageResource(R.drawable.xs);
        if (tracker[0][1] == 10) q2.setImageResource(R.drawable.os);

        if (tracker[0][2] == 1) q3.setImageResource(R.drawable.xs);
        if (tracker[0][2] == 10) q3.setImageResource(R.drawable.os);

        if (tracker[1][0] == 1) q4.setImageResource(R.drawable.xs);
        if (tracker[1][0] == 10) q4.setImageResource(R.drawable.os);

        if (tracker[1][1] == 1) q5.setImageResource(R.drawable.xs);
        if (tracker[1][1] == 10) q5.setImageResource(R.drawable.os);

        if (tracker[1][2] == 1) q6.setImageResource(R.drawable.xs);
        if (tracker[1][2] == 10) q6.setImageResource(R.drawable.os);

        if (tracker[2][0] == 1) q7.setImageResource(R.drawable.xs);
        if (tracker[2][0] == 10) q7.setImageResource(R.drawable.os);

        if (tracker[2][1] == 1) q8.setImageResource(R.drawable.xs);
        if (tracker[2][1] == 10) q8.setImageResource(R.drawable.os);

        if (tracker[2][2] == 1) q9.setImageResource(R.drawable.xs);
        if (tracker[2][2] == 10) q9.setImageResource(R.drawable.os);

        resetchecker++;
    }

    public void winchecker() {
        ctrflag++;
        sum[0] = tracker[0][0] + tracker[0][1] + tracker[0][2];
        sum[1] = tracker[1][0] + tracker[1][1] + tracker[1][2];
        sum[2] = tracker[2][0] + tracker[2][1] + tracker[2][2];
        sum[3] = tracker[0][0] + tracker[1][0] + tracker[2][0];
        sum[4] = tracker[0][1] + tracker[1][1] + tracker[2][1];
        sum[5] = tracker[0][2] + tracker[1][2] + tracker[2][2];
        sum[6] = tracker[0][0] + tracker[1][1] + tracker[2][2];
        sum[7] = tracker[0][2] + tracker[1][1] + tracker[2][0];
        final boolean[] wins = new boolean[1];
        final boolean[] returns = new boolean[1];

        currentgamedonechecker++;
        resetchecker++;

        for (int i = 0; i < 8; i++)
            if (sum[i] == 3 || sum[i] == 30) {


                final MediaPlayer ScoreSound = MediaPlayer.create(this, R.raw.scoresound);
                ScoreSound.start();

                if(Vibration) {
                    Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    v.vibrate(400);
                }
                win++;
                if ((sum[i] == 3) && (ax == 1)) {
                    score1++;
                    final TextView q1 = (TextView) findViewById(R.id.p1score);
                    wins[0] = true;
                    returns[0] = false;
                    if (wins[0]){
                        q1.setTextColor(getResources().getColor(R.color.cotextred));
                        wins[0] = false;
                        returns[0] = true;
                    }

                    final boolean finalReturns = returns[0];
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if (finalReturns){
                                q1.setTextColor(getResources().getColor(R.color.cotext));
                                wins[0] = false;
                                returns[0] = true;
                            }
                        }
                    }, 300);

                    q1.setText("" + score1);
                    new Handler().postDelayed(new Runnable() {
                        public void run() {
                            playmore();
                        }
                    }, 500);

                }
                if ((sum[i] == 3) && (zero == 1)) {
                    score2++;
                    final TextView q1 = (TextView) findViewById(R.id.p2score);
                    wins[0] = true;
                    returns[0] = false;
                    if (wins[0]){
                        q1.setTextColor(getResources().getColor(R.color.cotextred));
                        wins[0] = false;
                        returns[0] = true;
                    }

                    final boolean finalReturns = returns[0];
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if (finalReturns){
                                q1.setTextColor(getResources().getColor(R.color.cotext));
                                wins[0] = false;
                                returns[0] = true;
                            }
                        }
                    }, 500);



                    q1.setText("" + score2);
                    // showDialog("" + player2 + " won!", "" + score2, "" + player1, "" + score1);
                    //  Toast.makeText(getApplicationContext(),"AI won!", Toast.LENGTH_SHORT).show();

                    new Handler().postDelayed(new Runnable() {
                        public void run() {
                            playmore();
                        }
                    }, 300);

                }
                if ((sum[i] == 30) && (ax == 10)) {


                    score1++;
                    final   TextView q1 = (TextView) findViewById(R.id.p1score);
                    wins[0] = true;
                    returns[0] = false;
                    if (wins[0]){
                        q1.setTextColor(getResources().getColor(R.color.cotextred));
                        wins[0] = false;
                        returns[0] = true;
                    }

                    final boolean finalReturns = returns[0];
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if (finalReturns){
                                q1.setTextColor(getResources().getColor(R.color.cotext));
                                wins[0] = false;
                                returns[0] = true;
                            }
                        }
                    }, 300);


                    q1.setText("" + score1);
                    //    showDialog("" + player1 + " won!", "" + score1, "" + player2, "" + score2);
                    //  Toast.makeText(getApplicationContext(),player1 + " won!", Toast.LENGTH_SHORT).show();

                    new Handler().postDelayed(new Runnable() {
                        public void run() {
                            playmore();
                        }
                    }, 500);

                }
                if ((sum[i] == 30) && (zero == 10)) {


                    score2++;
                    final TextView q1 = (TextView) findViewById(R.id.p2score);
                    wins[0] = true;
                    returns[0] = false;
                    if (wins[0]){
                        q1.setTextColor(getResources().getColor(R.color.cotextred));
                        wins[0] = false;
                        returns[0] = true;
                    }

                    final boolean finalReturns = returns[0];
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if (finalReturns){
                                q1.setTextColor(getResources().getColor(R.color.cotext));
                                wins[0] = false;
                                returns[0] = true;
                            }
                        }
                    }, 300);


                    q1.setText("" + score2);
                    // to asi netreba
                    //   Toast.makeText(getApplicationContext(), " AI won!", Toast.LENGTH_SHORT).show();
                    new Handler().postDelayed(new Runnable() {
                        public void run() {
                            playmore();
                        }
                    }, 500);


                    //  showDialog("" + player2 + " won!", "" + score2, "" + player1, "" + score1);

                }

            }

        if ((ctrflag == 9) && (win == 0)) {
            //  showDialog("This is a draw !", "" + score1, "" + "AI", "" + score2);


            Toast.makeText(getApplicationContext(),"DRAW!",Toast.LENGTH_SHORT).show();

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    playmore();
                }
            }, 900);
            drawchecker++;
        }


    }

    private void playmore() {


        int max = 4;
        int min = 1;

        if (savedValue == 0 || savedValue == 5) {
            int random = new Random().nextInt(max - min + 1) + min;

            if (random == 1){
                easy = true;
                medium = false;
                hard = false;
                impossible = false;

            }
            if (random == 2){
                easy = false;
                medium = true;
                hard = false;
                impossible = false;

            }
            if (random == 3){
                easy = false;
                medium = false;
                hard = true;
                impossible = false;

            }
            if (random == 4){
                easy = false;
                medium = false;
                hard = false;
                impossible = true;
            }
        }
        if (savedValue == 1){

            easy = true;
            medium = false;
            hard = false;
            impossible = false;        }
        if (savedValue == 2){

            easy = false;
            medium = true;
            hard = false;
            impossible = false;        }
        if (savedValue == 3){

            easy = false;
            medium = false;
            hard = true;
            impossible = false;        }
        if (savedValue == 4){

            easy = false;
            medium = false;
            hard = false;
            impossible = true;        }

        if ((drawchecker > 0) || (win > 0)) {
            game++;

            for (int i = 0; i < 8; i++)
                sum[i] = 0;

            drawchecker = 0;

            ImageView q1, q2, q3, q4, q5, q6, q7, q8, q9;
            q1 = (ImageView) findViewById(R.id.c11);
            q2 = (ImageView) findViewById(R.id.c12);
            q3 = (ImageView) findViewById(R.id.c13);
            q4 = (ImageView) findViewById(R.id.c21);
            q5 = (ImageView) findViewById(R.id.c22);
            q6 = (ImageView) findViewById(R.id.c23);
            q7 = (ImageView) findViewById(R.id.c31);
            q8 = (ImageView) findViewById(R.id.c32);
            q9 = (ImageView) findViewById(R.id.c33);
            q1.setImageDrawable(null);
            q2.setImageDrawable(null);
            q3.setImageDrawable(null);
            q4.setImageDrawable(null);
            q5.setImageDrawable(null);
            q6.setImageDrawable(null);
            q7.setImageDrawable(null);
            q8.setImageDrawable(null);
            q9.setImageDrawable(null);

            for (int i = 0; i < 3; i++)
                for (int j = 0; j < 3; j++)
                    buttonpressed[i][j] = 0;

            for (int i = 0; i < 3; i++)
                for (int j = 0; j < 3; j++)
                    tracker[i][j] = 0;

            if (!selectedsingleplayer) {
                if ((game + 1) % 2 == 0) {
                    Toast.makeText(this, "" + player1 + "\'s turn", Toast.LENGTH_SHORT).show();
                } else Toast.makeText(this, "" + player2 + "\'s turn", Toast.LENGTH_SHORT).show();
            }

            win = 0;
            summ = 0;
            ctrflag = 0;
            flag = (game + 1) % 2;
            currentgamedonechecker = 0;

        }
    }


    public void doreset() {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                tracker[i][j] = 0;
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                buttonpressed[i][j] = 0;

        ImageView q1, q2, q3, q4, q5, q6, q7, q8, q9;

        q1 = (ImageView) findViewById(R.id.c11);
        q2 = (ImageView) findViewById(R.id.c12);
        q3 = (ImageView) findViewById(R.id.c13);
        q4 = (ImageView) findViewById(R.id.c21);
        q5 = (ImageView) findViewById(R.id.c22);
        q6 = (ImageView) findViewById(R.id.c23);
        q7 = (ImageView) findViewById(R.id.c31);
        q8 = (ImageView) findViewById(R.id.c32);
        q9 = (ImageView) findViewById(R.id.c33);

        q1.setImageDrawable(null);
        q2.setImageDrawable(null);
        q3.setImageDrawable(null);
        q4.setImageDrawable(null);
        q5.setImageDrawable(null);
        q6.setImageDrawable(null);
        q7.setImageDrawable(null);
        q8.setImageDrawable(null);
        q9.setImageDrawable(null);

        win = 0;
        drawchecker = 0;
        summ = 0;
        resetchecker = 0;
        ctrflag = 0;
        score1 = 0;
        score2 = 0;
        game = 1;
        flag = 0;
        currentgamedonechecker = 0;
        TextView qqq = (TextView) findViewById(R.id.p1score);
        qqq.setText("" + score1);
        TextView qqqq = (TextView) findViewById(R.id.p2score);
        qqqq.setText("" + score2);

        Toast.makeText(this, "" + player1 + "\'s turn", Toast.LENGTH_SHORT).show();

    }

    private void showExitDialog() {
        final Dialog dialog = new Dialog(SceneActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_layout_exit);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.setCancelable(false);

        dialog.show();

        Button exit = dialog.findViewById(R.id.btn_yes);
        final Button dismiss = dialog.findViewById(R.id.btn_no);

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doreset();
                finish();
                Intent intent = new Intent(SceneActivity.this, MainActivity.class);
                startActivity(intent);
                stopService(new Intent(getApplicationContext(), MusicService2.class));
            }
        });

        dismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }

    public void presentActivity(View view) {
        ActivityOptionsCompat options = ActivityOptionsCompat.
                makeSceneTransitionAnimation(this, view, "transition");
        int revealX = (int) (view.getX() + view.getWidth() / 2);
        int revealY = (int) (view.getY() + view.getHeight() / 2);
        Intent intent =
                new Intent(this, SettingActivity.class);
        intent.putExtra(SettingActivity.
                EXTRA_CIRCULAR_REVEAL_X, revealX);
        intent.putExtra(SettingActivity.
                EXTRA_CIRCULAR_REVEAL_Y, revealY);
        ActivityCompat.
                startActivity(this, intent, options.toBundle());
    }

    @Override
    public void onBackPressed() {
        showExitDialog();
    }

}
