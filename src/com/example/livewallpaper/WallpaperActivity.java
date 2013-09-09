package com.example.livewallpaper;

import android.app.Activity;
import android.app.WallpaperManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class WallpaperActivity extends Activity {

    private Button setWallpaperButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.act_wallpaper);

        setWallpaperButton = (Button) findViewById(R.id.btn_set_wallpaper);
        setWallpaperButton.setOnClickListener(setWallpaperListener);

    }

    private View.OnClickListener setWallpaperListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {

            Toast toast = Toast.makeText(WallpaperActivity.this, R.string.toast_select_wallpaper, Toast.LENGTH_LONG);
            toast.show();

            startActivity(new Intent(WallpaperManager.ACTION_LIVE_WALLPAPER_CHOOSER));

            finish();

        }
    };

}