package own.ownplanetswallpaper

import android.service.wallpaper.WallpaperService
import android.util.Log
import android.view.SurfaceHolder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

/**
 * Live wallpaper service — Canvas-based Kotlin rewrite of the AndEngine version.
 *
 * A coroutine draw loop on [Dispatchers.Main] targets ~60 fps while visible.
 * No OpenGL or third-party engine required.
 */
class PlanetWallpaperService : WallpaperService() {

    override fun onCreateEngine(): Engine = PlanetEngine()

    inner class PlanetEngine : Engine() {

        private val textures by lazy { TextureCache(resources, packageName) }
        private val renderer by lazy { WallpaperRenderer(textures) }

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
            if (visible) startDrawing() else stopDrawing()
        }

        override fun onSurfaceDestroyed(holder: SurfaceHolder) {
            super.onSurfaceDestroyed(holder)
            stopDrawing()
        }

        override fun onDestroy() {
            super.onDestroy()
            stopDrawing()
            scope.cancel()
            textures.recycle()
        }

        private fun startDrawing() {
            drawJob?.cancel()
            drawJob = scope.launch {
                while (isActive && isVisible) {
                    drawFrame()
                    delay(16L)
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
