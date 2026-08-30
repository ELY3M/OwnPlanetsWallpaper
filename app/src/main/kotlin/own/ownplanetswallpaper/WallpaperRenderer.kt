package own.ownplanetswallpaper

import android.graphics.Canvas
import android.graphics.Matrix
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.RectF
import kotlin.random.Random

/**
 * All drawing logic for the planets live wallpaper.
 *
 * Maintains:
 * - A scaled background image.
 * - 30 "planet" sprites + 8 "saturn" sprites that move for the wallpaper lifetime.
 * - 13 randomly-replaced sprite slots that are swapped every ~103 seconds.
 *
 * Rendering uses Android [Canvas]; no OpenGL or third-party engine required.
 */
class WallpaperRenderer(
    private val textures: TextureCache,
    private val sceneWidth: Int = 600,
    private val sceneHeight: Int = 800
) {
    private val spriteAssets = listOf(
        "element1.png", "element2.png", "element3.png", "element4.png", "element5.png",
        "element6.png", "element7.png", "element8.png", "element9.png", "element10.png",
        "element11.png", "element12.png", "element13.png", "element14.png", "element15.png",
        "element16.png", "element17.png", "element18.png", "element19.png", "element20.png",
        "element21.png", "element22.png", "element23.png", "element24.png", "element25.png",
        "element26.png", "element27.png", "element28.png", "element29.png", "element30.png",
        "element31.png",
        "alien3.png", "alien4.png",
        "asteroid1.png", "asteroid2.png", "asteroid3.png", "asteroid4.png", "asteroid5.png",
        "firefly.png", "pixelfly.png",
        "moon.png", "largemoon.png",
        "star32.png", "tinyufo.png",
        "light.png", "lighton.png", "lightoff.png",
        "bluemouse.png",
        "planet1.png", "planet2.png", "planet3.png", "planet4.png", "planet5.png",
        "planet6.png", "planet7.png", "planet8.png", "planet9.png", "planet10.png",
        "planet11.png", "planet12.png", "planet13.png"
    )

    private val planetAssets = listOf(
        "element1.png", "element13.png", "element7.png", "element3.png", "element9.png",
        "element4.png", "element11.png", "element12.png", "element14.png", "element8.png",
        "element15.png", "element10.png", "element5.png"
    )

    private val permanentSprites = mutableListOf<SpriteEntity>()
    private val randomSlots = arrayOfNulls<SpriteEntity>(13)

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG or Paint.FILTER_BITMAP_FLAG)
    private val matrix = Matrix()
    private val srcRect = Rect()
    private val dstRectF = RectF()

    private var lastFrameNs = 0L
    private var lastSwapNs = 0L
    private val swapIntervalNs = 103_000_000_000L

    private var scaleX = 1f
    private var scaleY = 1f
    private var initialized = false

    fun onSurfaceChanged(width: Int, height: Int) {
        scaleX = width.toFloat() / sceneWidth
        scaleY = height.toFloat() / sceneHeight
        if (!initialized) {
            createPermanentSprites()
            fillRandomSlots()
            initialized = true
        }
        val now = System.nanoTime()
        lastFrameNs = now
        lastSwapNs = now
    }

    fun draw(canvas: Canvas) {
        val now = System.nanoTime()
        val delta = if (lastFrameNs == 0L) 0f else (now - lastFrameNs) / 1_000_000_000f
        lastFrameNs = now

        if (now - lastSwapNs >= swapIntervalNs) {
            fillRandomSlots()
            lastSwapNs = now
        }

        canvas.save()
        canvas.scale(scaleX, scaleY)

        val bg = textures.get("background.png")
        srcRect.set(0, 0, bg.width, bg.height)
        dstRectF.set(0f, -400f, sceneWidth.toFloat(), sceneHeight.toFloat())
        canvas.drawBitmap(bg, srcRect, dstRectF, paint)

        permanentSprites.forEach { it.update(delta, sceneWidth, sceneHeight); drawSprite(canvas, it) }
        randomSlots.forEach { it?.let { s -> s.update(delta, sceneWidth, sceneHeight); drawSprite(canvas, s) } }

        canvas.restore()
    }

    private fun drawSprite(canvas: Canvas, sprite: SpriteEntity) {
        val bmp = sprite.bitmap
        val cx = sprite.x + bmp.width / 2f
        val cy = sprite.y + bmp.height / 2f
        matrix.reset()
        matrix.postTranslate(-bmp.width / 2f, -bmp.height / 2f)
        matrix.postRotate(sprite.rotation)
        matrix.postTranslate(cx, cy)
        canvas.drawBitmap(bmp, matrix, paint)
    }

    private fun createPermanentSprites() {
        repeat(30) { i -> permanentSprites.add(makeSprite(textures.get(planetAssets[i % planetAssets.size]))) }
        repeat(8) { permanentSprites.add(makeSprite(textures.get("element2.png"))) }
    }

    private fun fillRandomSlots() {
        for (i in randomSlots.indices) randomSlots[i] = makeSprite(textures.get(spriteAssets.random()))
    }

    private fun makeSprite(bitmap: android.graphics.Bitmap) = SpriteEntity(
        bitmap,
        x = Random.nextFloat() * sceneWidth,
        y = Random.nextFloat() * sceneHeight,
        velX = 10f * (Random.nextFloat() - 0.5f),
        velY = 10f * (Random.nextFloat() - 0.5f)
    )
}
