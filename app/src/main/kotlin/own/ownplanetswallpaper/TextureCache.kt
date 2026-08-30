package own.ownplanetswallpaper

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log

/**
 * Loads and caches [Bitmap]s from Android drawable resources.
 * Each image is decoded once and returned from the in-memory cache on subsequent calls.
 */
class TextureCache(
    private val resources: Resources,
    private val packageName: String
) {

    private val cache = mutableMapOf<String, Bitmap>()
    private val drawableIds = R.drawable::class.java.fields.associate { it.name to it.getInt(null) }

    /** Returns the [Bitmap] for [filename], decoding from drawable resources on first access. */
    fun get(filename: String): Bitmap = cache.getOrPut(filename) {
        val resourceName = drawableNameFor(filename)
        val drawableId = drawableIds[resourceName] ?: resources.getIdentifier(resourceName, "drawable", packageName)
        try {
            if (drawableId != 0) {
                BitmapFactory.decodeResource(resources, drawableId) ?: throw IllegalArgumentException("decodeResource returned null")
            } else {
                throw IllegalArgumentException("Drawable resource not found")
            }
        } catch (e: Exception) {
            Log.e("TextureCache", "Failed to load drawable/$filename", e)
            Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888)
        }
    }

    /** Recycles every cached bitmap and clears the cache. Call on engine destroy. */
    fun recycle() {
        cache.values.forEach { it.recycle() }
        cache.clear()
    }

    companion object {
        internal fun drawableNameFor(filename: String): String =
            filename.substringBeforeLast('.').replace('-', '_').lowercase()
    }
}
