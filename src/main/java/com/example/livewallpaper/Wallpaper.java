package com.example.livewallpaper;

import android.service.wallpaper.WallpaperService;
import android.util.Log;
import android.view.SurfaceHolder;

public class Wallpaper extends WallpaperService {

    private static final String TAG = "Wallpaper";

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");

    }

    @Override
    public Engine onCreateEngine() {
        return new WallpaperEngine();
    }

    class WallpaperEngine extends Engine {

        private static final String TAG = "WallpaperEngine";

        private AnimationThread animationThread;
        private Scene scene;

        @Override
        public void onCreate(SurfaceHolder surfaceHolder) {
            super.onCreate(surfaceHolder);

            Log.d(TAG, "onCreate");

            // create the scene
            scene = new Scene();
            
            // start animation thread; thread starts paused
            // will run onVisibilityChanged
            animationThread = new AnimationThread(surfaceHolder, scene);
            animationThread.start();

        }

        @Override
        public void onDestroy() {
            Log.d(TAG, "onDestroy");

            animationThread.stopThread();
            joinThread(animationThread);
            animationThread = null;

            super.onDestroy();
        }

        @Override
        public void onVisibilityChanged(boolean visible) {
            Log.d(TAG, "onVisibilityChanged: " + (visible ? "visible" : "invisible"));
            if (visible) {
                animationThread.resumeThread();
            } else {
                animationThread.pauseThread();
            }
        }

        @Override
        public void onSurfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            super.onSurfaceChanged(holder, format, width, height);
            Log.d(TAG, "onSurfaceChanged: width: " + width + ", height: " + height);

            scene.updateSize(width, height);

        }

        private void joinThread(Thread thread) {
            boolean retry = true;
            while (retry) {
                try {
                    thread.join();
                    retry = false;
                } catch (InterruptedException e) {
                }
            }
        }

    }

}
