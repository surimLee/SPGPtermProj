package kr.ac.kpu.ce2016152030.spgptermproj;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SettingActivity extends AppCompatActivity {

    public static final String EXTRA_CIRCULAR_REVEAL_X = "EXTRA_CIRCULAR_REVEAL_X";
    public static final String EXTRA_CIRCULAR_REVEAL_Y = "EXTRA_CIRCULAR_REVEAL_Y";

    private boolean isChecked;

    private boolean Vibration ;

    private static final String PREFS_NAME = "vibration";
    private static final String PREF_VIBRATION = "TicVib";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences preferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        Vibration = preferences.getBoolean(PREF_VIBRATION, true);

        setContentView(R.layout.activity_setting);

        Switch swit = findViewById(R.id.switch1);
        swit.setChecked(Vibration);
        swit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Vibration){
                    isChecked=false;
                    SharedPreferences.Editor editor=getSharedPreferences(PREFS_NAME, MODE_PRIVATE).edit();
                    editor.putBoolean(PREF_VIBRATION, isChecked);
                    editor.apply();
                }else {
                    isChecked=true;

                    SharedPreferences.Editor editor=getSharedPreferences(PREFS_NAME, MODE_PRIVATE).edit();
                    editor.putBoolean(PREF_VIBRATION, isChecked);
                    editor.apply();
                }
            }
        });

    }
}
