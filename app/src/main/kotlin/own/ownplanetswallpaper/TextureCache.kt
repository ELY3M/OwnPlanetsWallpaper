package own.ownplanetswallpaper

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import java.util.Locale

/**
 * Loads and caches [Bitmap]s from Android drawable resources.
 * Each image is decoded once and returned from the in-memory cache on subsequent calls.
 */
class TextureCache(
    private val resources: Resources,
    private val packageName: String
) {

    private val cache = mutableMapOf<String, Bitmap>()

    /** Returns the [Bitmap] for [filename], decoding from drawable resources on first access. */
    fun get(filename: String): Bitmap {
        val resourceName = drawableNameFor(filename)
        return cache.getOrPut(resourceName) {
            val drawableId = resources.getIdentifier(resourceName, "drawable", packageName)
            if (drawableId == 0) {
                Log.e("TextureCache", "Drawable resource not found: $resourceName (from $filename)")
                Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888)
            } else {
                try {
                    BitmapFactory.decodeResource(resources, drawableId)
                        ?: Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888).also {
                            Log.e("TextureCache", "decodeResource returned null for $resourceName (from $filename)")
                        }
                } catch (e: Exception) {
                    Log.e("TextureCache", "Failed to load drawable/$resourceName (from $filename)", e)
                    Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888)
                }
            }
        }
    }

    /** Recycles every cached bitmap and clears the cache. Call on engine destroy. */
    fun recycle() {
        cache.values.forEach { it.recycle() }
        cache.clear()
    }

    companion object {
        internal fun drawableNameFor(filename: String): String =
            filename.substringBeforeLast('.').replace('-', '_').lowercase(Locale.ROOT)
    }
}
