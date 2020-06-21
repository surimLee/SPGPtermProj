package kr.ac.kpu.ce2016152030.spgptermproj;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

import androidx.annotation.Nullable;

public class MusicService2 extends Service {

    MediaPlayer mediaPlayer2;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public void onCreate() {
        super.onCreate();

        mediaPlayer2 = MediaPlayer.create(this, R.raw.ingamebgm);
        mediaPlayer2.setLooping(true);
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mediaPlayer2.start();
        return super.onStartCommand(intent, flags, startId);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        mediaPlayer2.stop();
    }
}
