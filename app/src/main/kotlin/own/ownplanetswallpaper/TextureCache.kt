package own.ownplanetswallpaper

import android.content.res.AssetManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log

/**
 * Loads and caches [Bitmap]s from the `assets/gfx/` directory.
 * Each image is decoded once and returned from the in-memory cache on subsequent calls.
 */
class TextureCache(private val assets: AssetManager) {

    private val cache = mutableMapOf<String, Bitmap>()

    /** Returns the [Bitmap] for [filename] (relative to `assets/gfx/`), decoding on first access. */
    fun get(filename: String): Bitmap = cache.getOrPut(filename) {
        try {
            assets.open("gfx/$filename").use { BitmapFactory.decodeStream(it)!! }
        } catch (e: Exception) {
            Log.e("TextureCache", "Failed to load gfx/$filename", e)
            Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888)
        }
    }

    /** Recycles every cached bitmap and clears the cache. Call on engine destroy. */
    fun recycle() {
        cache.values.forEach { it.recycle() }
        cache.clear()
    }
}
