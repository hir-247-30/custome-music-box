// created by Hiroshi Hagiwara

package com.example.hiroshi.tohobbamedley;

import android.app.Activity;
import android.os.Bundle;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends Activity implements OnCompletionListener{

    MediaPlayer mp;
    ImageView imageview;

    // i番目の曲を再生する
    int i = 0;

    // BGMの配列
    int[] sound = {R.raw.remilia, R.raw.fran, R.raw.yuyuko, R.raw.ransama, R.raw.yukarin,
            R.raw.eirin, R.raw.kaguya, R.raw.mokotan, R.raw.kanako, R.raw.suwako,
            R.raw.tenko, R.raw.okuu, R.raw.koishi, R.raw.hijiri, R.raw.nue};

    // 画像の配列
    int[] picture = {R.drawable.remilia, R.drawable.frandle, R.drawable.yuyuko, R.drawable.ran, R.drawable.yukari,
           R.drawable.eirin, R.drawable.kaguya, R.drawable.mokotan, R.drawable.kanako, R.drawable.suwako,
            R.drawable.tenko, R.drawable.okuu, R.drawable.koishi, R.drawable.hijiri, R.drawable.nue};

    // 背景画像の配列
    int[] color = {R.color.remilia, R.color.frandle, R.color.yuyuko, R.color.ran, R.color.yukari,
            R.color.eirin, R.color.kaguya, R.color.mokotan, R.color.kanako, R.color.suwako,
            R.color.tenko, R.color.okuu, R.color.koishi, R.color.hijiri, R.color.nue};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        // 背景画像をセット
        ConstraintLayout background = findViewById(R.id.back);
        background.setBackgroundColor(getResources().getColor(color[0]));

        // 画像をセット
        imageview = findViewById(R.id.imageView);
        imageview.setImageResource(R.drawable.remilia);

        // BGMをセット
        mp = MediaPlayer.create(getApplicationContext(), sound[i]);
        mp.start();
        // 曲が終わった時に呼び出されるリスナー
        mp.setOnCompletionListener(this);

    }

    // backボタン
    public void back(View v){

        if(mp.isPlaying()){
            mp.pause();
        }
        // 最初の曲なら最後の曲へ戻る
        if(i == 0){
            i = 13;
        } else {
            i = i - 2;
        }
        mp.reset();
        onCompletion(mp);
    }

    // controlボタン
    public void control(View v){

        if(mp.isPlaying()){
            mp.pause();
        } else {
            mp.start();
        }
    }

    // skipボタン
    public void skip(View v){

        if(mp.isPlaying()){
            mp.pause();
        }
        mp.reset();
        onCompletion(mp);
    }

    // 曲が終わったら呼び出されるメソッド
    public void onCompletion(MediaPlayer mediaPlayer) {

        mediaPlayer.release();
        i++;
        // 終わりまで流れたら、次は最初の曲を流す
        if(i == 15){
            i = 0;
        }

        mp = MediaPlayer.create(getApplicationContext(), sound[i]);
        mp.start();

        imageview = findViewById(R.id.imageView);
        imageview.setImageResource(picture[i]);

        ConstraintLayout background = findViewById(R.id.back);
        background.setBackgroundColor(getResources().getColor(color[i]));

        // リスナーのセット
        mp.setOnCompletionListener(this);

    }

    // 画面が表示されるたびに実行
    @Override
    protected void onResume() {
        super.onResume();
        mp.start(); // 再生
    }

    // アプリ終了時に実行
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mp.stop();
        mp.release(); // メモリの解放
        mp = null; // 音楽プレーヤーを破棄
    }
}
