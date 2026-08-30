package own.ownplanetswallpaper

import org.junit.Assert.assertEquals
import org.junit.Test

class TextureCacheTest {

    @Test
    fun drawableNameFor_stripsExtension_andNormalizes() {
        assertEquals("background", TextureCache.drawableNameFor("background.png"))
        assertEquals("background_lg", TextureCache.drawableNameFor("background-lg.png"))
        assertEquals("planet1", TextureCache.drawableNameFor("planet1"))
    }
}
