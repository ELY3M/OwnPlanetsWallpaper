package own.ownplanetswallpaper

import android.graphics.Bitmap
import kotlin.random.Random

/**
 * Represents a single moving, rotating sprite on the wallpaper.
 *
 * @param bitmap        Decoded bitmap image for this sprite.
 * @param x             Initial x position in logical scene coordinates.
 * @param y             Initial y position in logical scene coordinates.
 * @param velX          Horizontal velocity (logical units per second).
 * @param velY          Vertical velocity (logical units per second).
 * @param rotation      Current rotation angle in degrees.
 * @param rotationSpeed Degrees per second; sign determines direction.
 */
data class SpriteEntity(
    val bitmap: Bitmap,
    var x: Float,
    var y: Float,
    var velX: Float,
    var velY: Float,
    var rotation: Float = 0f,
    val rotationSpeed: Float = if (Random.nextBoolean()) 12f else -12f
) {
    /**
     * Advance physics by [deltaSeconds] and bounce off the scene edges.
     * A margin of 150 units beyond the scene boundary is allowed before reversing.
     */
    fun update(deltaSeconds: Float, sceneWidth: Int, sceneHeight: Int) {
        x += velX * deltaSeconds
        y += velY * deltaSeconds
        rotation = (rotation + rotationSpeed * deltaSeconds) % 360f

        val margin = 150f
        if (x < -margin) {
            velX = (Random.nextFloat() + 1.1f) * 10f
        } else if (x + bitmap.width >= sceneWidth + margin) {
            velX = -(Random.nextFloat() + 1.1f) * 10f
        }
        if (y < -margin) {
            velY = (Random.nextFloat() + 1.1f) * 10f
        } else if (y + bitmap.height >= sceneHeight + margin) {
            velY = -(Random.nextFloat() + 1.1f) * 10f
        }
    }
}
