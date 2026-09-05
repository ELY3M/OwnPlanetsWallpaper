package own.ownplanetswallpaper

import android.content.Context
import android.os.Build
import android.service.wallpaper.WallpaperService
import android.util.DisplayMetrics
import android.util.Log
import android.view.SurfaceHolder
import android.view.WindowManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.milliseconds

/**
 * Live wallpaper service — Canvas-based Kotlin rewrite of the AndEngine version.
 *
 * A coroutine draw loop on [Dispatchers.Main] targets ~60 fps while visible.
 * No OpenGL or third-party engine required.
 */
class PlanetWallpaperService : WallpaperService() {

    var screenWith = 1600
    var screenHeight = 1800

    fun getScreenSize(context: Context): Pair<Int, Int> {
        val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager

        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            // Modern approach for Android 11 (API 30) and above
            val metrics = windowManager.currentWindowMetrics
            val bounds = metrics.bounds
            Pair(bounds.width(), bounds.height())
        } else {
            // Legacy approach for older versions
            val displayMetrics = DisplayMetrics()
            @Suppress("DEPRECATION")
            windowManager.defaultDisplay.getMetrics(displayMetrics)
            Pair(displayMetrics.widthPixels, displayMetrics.heightPixels)
        }
    }

    override fun onCreate() {
        super.onCreate()
        val (width, height) = getScreenSize(applicationContext)
        screenWith = width
        screenHeight = height
        Log.i("PlanetWallpaperService", "screenWidth: "+screenWith+ " screenHeight: "+screenHeight)
    }

    override fun onCreateEngine(): Engine = PlanetEngine()

    inner class PlanetEngine : Engine() {


        private val textures by lazy { TextureCache(resources, packageName) }
        private val renderer by lazy { WallpaperRenderer(textures, screenWith, screenHeight) }

        private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Main)
        private var drawJob: Job? = null
        private var isVisible = false

        override fun onSurfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {
            super.onSurfaceChanged(holder, format, width, height)
            renderer.onSurfaceChanged(width, height)
        }


        override fun onVisibilityChanged(visible: Boolean) {
            super.onVisibilityChanged(visible)
            isVisible = visible
            if (visible) {
                Log.i("PlanetWallpaperService","onVisibilityChanged() - visible")
                startDrawing()
            } else {
                Log.i("PlanetWallpaperService","onVisibilityChanged() - not visible")
                //stopDrawing() //this causes blank wallpaper too.
            }
        }


        override fun onSurfaceDestroyed(holder: SurfaceHolder) {
            super.onSurfaceDestroyed(holder)
            Log.i("PlanetWallpaperService","onSurfaceDestroyed()")
            stopDrawing()
        }

        override fun onDestroy() {
            super.onDestroy()
            Log.i("PlanetWallpaperService","onDestory()")
            stopDrawing()
            scope.cancel()
            textures.recycle()
        }

        private fun startDrawing() {
            drawJob?.cancel()
            drawJob = scope.launch {
                //while (isActive && isVisible) { //IsVisible causes blank wallpaper after waking up phone....
                while(isActive) {
                    drawFrame()
                    delay(1.milliseconds)
                    ///delay(16L.milliseconds)
                }
            }
        }

        private fun stopDrawing() {
            drawJob?.cancel()
            drawJob = null
        }

        private fun drawFrame() {
            val canvas = surfaceHolder.lockCanvas() ?: run {
                Log.w("PlanetWallpaper", "lockCanvas returned null — skipping frame")
                return
            }
            try {
                renderer.draw(canvas)
            } finally {
                surfaceHolder.unlockCanvasAndPost(canvas)
            }
        }
    }
}
