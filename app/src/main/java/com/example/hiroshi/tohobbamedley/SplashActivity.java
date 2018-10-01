package com.example.hiroshi.tohobbamedley;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;

public class SplashActivity extends Activity {
    @Override
    // スプラッシュ画面の設定
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // タイトルを非表示にする
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // スプラッシュ画面を設定
        setContentView(R.layout.splash_layout);
        Handler handler = new Handler();
        // 1500msスプラッシュ画面を表示させる
        handler.postDelayed(new splashHandler(), 1500);
    }

    class splashHandler implements Runnable {
        public void run() {
            // スプラッシュ画面の表示後、MainActivityクラスに推移
            Intent intent = new Intent(getApplication(), MainActivity.class);
            startActivity(intent);
            // SplashActivityの終了
            SplashActivity.this.finish();
        }
    }
}