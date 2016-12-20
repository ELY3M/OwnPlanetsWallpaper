package own.ownplanetswallpaper;

import android.content.Context;
import android.os.Bundle;
import android.service.wallpaper.WallpaperService.Engine;
import java.util.Random;
import org.andengine.engine.camera.Camera;
import org.andengine.engine.handler.physics.PhysicsHandler;
import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.FillResolutionPolicy;
import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.andengine.entity.modifier.LoopEntityModifier;
import org.andengine.entity.modifier.RotationModifier;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.SpriteBackground;
import org.andengine.entity.sprite.Sprite;
import org.andengine.extension.opengl.GLWallpaperService.GLEngine;
import org.andengine.extension.ui.livewallpaper.BaseLiveWallpaperService;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.util.GLState;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.opengl.view.ConfigChooser;
import org.andengine.opengl.view.EngineRenderer;
import org.andengine.opengl.view.IRendererListener;
import org.andengine.ui.IGameInterface.OnCreateResourcesCallback;
import org.andengine.ui.IGameInterface.OnCreateSceneCallback;
import org.andengine.ui.IGameInterface.OnPopulateSceneCallback;
import org.andengine.util.debug.Debug;
public class wallpaper extends BaseLiveWallpaperService {
    private static int CAMERA_HEIGHT = 800;
    private static int CAMERA_WIDTH = 600;
    private static final float DEMO_VELOCITY = 5.0f;
    private static final int PLANETS_COUNT = 30;
    private static final long RANDOM_SEED = 1234567890;
    private static final int SATURNS_COUNT = 8;
    private static final String TAG = "planets wallpaper";
    ITextureRegion alien3;
    BitmapTextureAtlas alien3Texture;
    ITextureRegion alien4;
    BitmapTextureAtlas alien4Texture;
    ITextureRegion asteroid1;
    BitmapTextureAtlas asteroid1Texture;
    ITextureRegion asteroid2;
    BitmapTextureAtlas asteroid2Texture;
    ITextureRegion asteroid3;
    BitmapTextureAtlas asteroid3Texture;
    ITextureRegion asteroid4;
    BitmapTextureAtlas asteroid4Texture;
    ITextureRegion asteroid5;
    BitmapTextureAtlas asteroid5Texture;
    ITextureRegion background;
    BitmapTextureAtlas backgroundTexture;
    ITextureRegion bluemoon;
    BitmapTextureAtlas bluemoonTexture;
    ITextureRegion blueplanet;
    BitmapTextureAtlas blueplanetTexture;
    ITextureRegion bluepurpleplanet;
    BitmapTextureAtlas bluepurpleplanetTexture;
    ITextureRegion cyanplanet;
    BitmapTextureAtlas cyanplanetTexture;
    ITextureRegion firefly;
    BitmapTextureAtlas fireflyTexture;
    ITextureRegion fly;
    BitmapTextureAtlas flyTexture;
    ITextureRegion grayplanet;
    BitmapTextureAtlas grayplanetTexture;
    ITextureRegion greenplanet;
    BitmapTextureAtlas greenplanetTexture;
    ITextureRegion light;
    BitmapTextureAtlas lightTexture;
    ITextureRegion lightoff;
    BitmapTextureAtlas lightoffTexture;
    ITextureRegion lighton;
    BitmapTextureAtlas lightonTexture;
    ITextureRegion ltblueplanet;
    BitmapTextureAtlas ltblueplanetTexture;
    ITextureRegion ltgreenplanet;
    BitmapTextureAtlas ltgreenplanetTexture;
    private Camera mCamera;
    protected final Random mRandom;
    private Scene mScene;
    ITextureRegion moon;
    BitmapTextureAtlas moonTexture;
    ITextureRegion mouse;
    BitmapTextureAtlas mouseTexture;
    ITextureRegion neongreenplanet;
    BitmapTextureAtlas neongreenplanetTexture;
    ITextureRegion orangeplanet;
    BitmapTextureAtlas orangeplanetTexture;
    Random random;
    ITextureRegion redplanet;
    BitmapTextureAtlas redplanetTexture;
    private Ball rndball1;
    private Ball rndball2;
    private Ball rndball3;
    private Ball rndball4;
    private Ball rndball5;
    private Ball rndball6;
    private Ball rndball7;
    private Ball rndball8;
    private Ball rndball9;
    private Ball rndball10;
    private Ball rndball11;
    private Ball rndball12;
    private Ball rndball13;
    ITextureRegion saturn;
    BitmapTextureAtlas saturnTexture;
    ITextureRegion star;
    BitmapTextureAtlas starTexture;
    ITextureRegion ufo;
    BitmapTextureAtlas ufoTexture;
    ITextureRegion whiteplanet;
    BitmapTextureAtlas whiteplanetTexture;
    ITextureRegion yellowplanet;
    BitmapTextureAtlas yellowplanetTexture;

    /* renamed from: own.planetswallpaperandengine.wallpaper.1 */
    class C02981 implements ITimerCallback {

        /* renamed from: own.planetswallpaperandengine.wallpaper.1.1 */
        class C01671 implements Runnable {
            C01671() {
            }

            public void run() {
                if (wallpaper.this.rndball1 == null) {
                    Debug.i(wallpaper.TAG, "rndball1 is null");
                } else {
                    Debug.i(wallpaper.TAG, "removing rndball1");
                    wallpaper.this.mScene.detachChild(wallpaper.this.rndball1);
                    wallpaper.this.rndball1.detachSelf();
                    wallpaper.this.rndball1.dispose();
                    wallpaper.this.rndball1 = null;
                }
                Random rand1 = new Random();
                Random rand2 = new Random();
                int[] ROTNUMBERS = new int[]{0, 360};
                int randrot1 = ROTNUMBERS[rand1.nextInt(ROTNUMBERS.length)];
                int randrot2 = ROTNUMBERS[rand2.nextInt(ROTNUMBERS.length)];
                if (randrot1 == 0 && randrot2 == 0) {
                    randrot1 = 360;
                }
                if (randrot1 == 360 && randrot2 == 360) {
                    randrot1 = 0;
                }
                int SpriteNumber = new Random().nextInt(wallpaper.PLANETS_COUNT) + 1;
                Debug.i(wallpaper.TAG, "RandSpriteNumber: " + SpriteNumber);
                if (SpriteNumber == 1) {
                    wallpaper.this.rndball1 = new Ball(wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_WIDTH), wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_HEIGHT), wallpaper.this.neongreenplanet, wallpaper.this.getVertexBufferObjectManager());
                } else if (SpriteNumber == 2) {
                    wallpaper.this.rndball1 = new Ball(wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_WIDTH), wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_HEIGHT), wallpaper.this.blueplanet, wallpaper.this.getVertexBufferObjectManager());
                } else if (SpriteNumber == 3) {
                    wallpaper.this.rndball1 = new Ball(wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_WIDTH), wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_HEIGHT), wallpaper.this.cyanplanet, wallpaper.this.getVertexBufferObjectManager());
                } else if (SpriteNumber == 4) {
                    wallpaper.this.rndball1 = new Ball(wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_WIDTH), wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_HEIGHT), wallpaper.this.ltblueplanet, wallpaper.this.getVertexBufferObjectManager());
                } else if (SpriteNumber == 5) {
                    wallpaper.this.rndball1 = new Ball(wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_WIDTH), wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_HEIGHT), wallpaper.this.bluepurpleplanet, wallpaper.this.getVertexBufferObjectManager());
                } else if (SpriteNumber == 6) {
                    wallpaper.this.rndball1 = new Ball(wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_WIDTH), wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_HEIGHT), wallpaper.this.redplanet, wallpaper.this.getVertexBufferObjectManager());
                } else if (SpriteNumber == 7) {
                    wallpaper.this.rndball1 = new Ball(wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_WIDTH), wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_HEIGHT), wallpaper.this.yellowplanet, wallpaper.this.getVertexBufferObjectManager());
                } else if (SpriteNumber == wallpaper.SATURNS_COUNT) {
                    wallpaper.this.rndball1 = new Ball(wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_WIDTH), wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_HEIGHT), wallpaper.this.orangeplanet, wallpaper.this.getVertexBufferObjectManager());
                } else if (SpriteNumber == 9) {
                    wallpaper.this.rndball1 = new Ball(wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_WIDTH), wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_HEIGHT), wallpaper.this.whiteplanet, wallpaper.this.getVertexBufferObjectManager());
                } else if (SpriteNumber == 10) {
                    wallpaper.this.rndball1 = new Ball(wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_WIDTH), wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_HEIGHT), wallpaper.this.grayplanet, wallpaper.this.getVertexBufferObjectManager());
                } else if (SpriteNumber == 11) {
                    wallpaper.this.rndball1 = new Ball(wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_WIDTH), wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_HEIGHT), wallpaper.this.greenplanet, wallpaper.this.getVertexBufferObjectManager());
                } else if (SpriteNumber == 12) {
                    wallpaper.this.rndball1 = new Ball(wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_WIDTH), wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_HEIGHT), wallpaper.this.ltgreenplanet, wallpaper.this.getVertexBufferObjectManager());
                } else if (SpriteNumber == 13) {
                    wallpaper.this.rndball1 = new Ball(wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_WIDTH), wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_HEIGHT), wallpaper.this.bluemoon, wallpaper.this.getVertexBufferObjectManager());
                } else if (SpriteNumber == 14) {
                    wallpaper.this.rndball1 = new Ball(wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_WIDTH), wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_HEIGHT), wallpaper.this.saturn, wallpaper.this.getVertexBufferObjectManager());
                } else if (SpriteNumber == 15) {
                    wallpaper.this.rndball1 = new Ball(wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_WIDTH), wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_HEIGHT), wallpaper.this.firefly, wallpaper.this.getVertexBufferObjectManager());
                } else if (SpriteNumber == 16) {
                    wallpaper.this.rndball1 = new Ball(wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_WIDTH), wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_HEIGHT), wallpaper.this.fly, wallpaper.this.getVertexBufferObjectManager());
                } else if (SpriteNumber == 17) {
                    wallpaper.this.rndball1 = new Ball(wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_WIDTH), wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_HEIGHT), wallpaper.this.moon, wallpaper.this.getVertexBufferObjectManager());
                } else if (SpriteNumber == 18) {
                    wallpaper.this.rndball1 = new Ball(wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_WIDTH), wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_HEIGHT), wallpaper.this.star, wallpaper.this.getVertexBufferObjectManager());
                } else if (SpriteNumber == 19) {
                    wallpaper.this.rndball1 = new Ball(wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_WIDTH), wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_HEIGHT), wallpaper.this.alien3, wallpaper.this.getVertexBufferObjectManager());
                } else if (SpriteNumber == 20) {
                    wallpaper.this.rndball1 = new Ball(wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_WIDTH), wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_HEIGHT), wallpaper.this.alien4, wallpaper.this.getVertexBufferObjectManager());
                } else if (SpriteNumber == 21) {
                    wallpaper.this.rndball1 = new Ball(wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_WIDTH), wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_HEIGHT), wallpaper.this.ufo, wallpaper.this.getVertexBufferObjectManager());
                } else if (SpriteNumber == 22) {
                    wallpaper.this.rndball1 = new Ball(wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_WIDTH), wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_HEIGHT), wallpaper.this.light, wallpaper.this.getVertexBufferObjectManager());
                } else if (SpriteNumber == 23) {
                    wallpaper.this.rndball1 = new Ball(wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_WIDTH), wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_HEIGHT), wallpaper.this.lighton, wallpaper.this.getVertexBufferObjectManager());
                } else if (SpriteNumber == 24) {
                    wallpaper.this.rndball1 = new Ball(wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_WIDTH), wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_HEIGHT), wallpaper.this.lightoff, wallpaper.this.getVertexBufferObjectManager());
                } else if (SpriteNumber == 25) {
                    wallpaper.this.rndball1 = new Ball(wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_WIDTH), wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_HEIGHT), wallpaper.this.mouse, wallpaper.this.getVertexBufferObjectManager());
                } else if (SpriteNumber == 26) {
                    wallpaper.this.rndball1 = new Ball(wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_WIDTH), wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_HEIGHT), wallpaper.this.asteroid1, wallpaper.this.getVertexBufferObjectManager());
                } else if (SpriteNumber == 27) {
                    wallpaper.this.rndball1 = new Ball(wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_WIDTH), wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_HEIGHT), wallpaper.this.asteroid2, wallpaper.this.getVertexBufferObjectManager());
                } else if (SpriteNumber == 28) {
                    wallpaper.this.rndball1 = new Ball(wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_WIDTH), wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_HEIGHT), wallpaper.this.asteroid3, wallpaper.this.getVertexBufferObjectManager());
                } else if (SpriteNumber == 29) {
                    wallpaper.this.rndball1 = new Ball(wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_WIDTH), wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_HEIGHT), wallpaper.this.asteroid4, wallpaper.this.getVertexBufferObjectManager());
                } else if (SpriteNumber == wallpaper.PLANETS_COUNT) {
                    wallpaper.this.rndball1 = new Ball(wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_WIDTH), wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_HEIGHT), wallpaper.this.asteroid5, wallpaper.this.getVertexBufferObjectManager());
                }
                PhysicsHandler physicsHandler = new PhysicsHandler(wallpaper.this.rndball1);
                wallpaper.this.rndball1.registerUpdateHandler(physicsHandler);
                wallpaper.this.rndball1.registerEntityModifier(new LoopEntityModifier(new RotationModifier(30.0f, (float) randrot1, (float) randrot2)));
                physicsHandler.setVelocity(10.0f * (wallpaper.this.random.nextFloat() - 0.5f), 10.0f * (wallpaper.this.random.nextFloat() - 0.5f));
                wallpaper.this.mScene.attachChild(wallpaper.this.rndball1);
            }
        }

        /* renamed from: own.planetswallpaperandengine.wallpaper.1.2 */
        class C01682 implements Runnable {
            C01682() {
            }

            public void run() {
                if (wallpaper.this.rndball2 == null) {
                    Debug.i(wallpaper.TAG, "rndball2 is null");
                } else {
                    Debug.i(wallpaper.TAG, "removing rndball2");
                    wallpaper.this.mScene.detachChild(wallpaper.this.rndball2);
                    wallpaper.this.rndball2.detachSelf();
                    wallpaper.this.rndball2.dispose();
                    wallpaper.this.rndball2 = null;
                }
                Random rand1 = new Random();
                Random rand2 = new Random();
                int[] ROTNUMBERS = new int[]{0, 360};
                int randrot1 = ROTNUMBERS[rand1.nextInt(ROTNUMBERS.length)];
                int randrot2 = ROTNUMBERS[rand2.nextInt(ROTNUMBERS.length)];
                if (randrot1 == 0 && randrot2 == 0) {
                    randrot1 = 360;
                }
                if (randrot1 == 360 && randrot2 == 360) {
                    randrot1 = 0;
                }
                int SpriteNumber = new Random().nextInt(wallpaper.PLANETS_COUNT) + 1;
                Debug.i(wallpaper.TAG, "RandSpriteNumber: " + SpriteNumber);
                if (SpriteNumber == 1) {
                    wallpaper.this.rndball2 = new Ball(wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_WIDTH), wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_HEIGHT), wallpaper.this.neongreenplanet, wallpaper.this.getVertexBufferObjectManager());
                } else if (SpriteNumber == 2) {
                    wallpaper.this.rndball2 = new Ball(wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_WIDTH), wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_HEIGHT), wallpaper.this.blueplanet, wallpaper.this.getVertexBufferObjectManager());
                } else if (SpriteNumber == 3) {
                    wallpaper.this.rndball2 = new Ball(wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_WIDTH), wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_HEIGHT), wallpaper.this.cyanplanet, wallpaper.this.getVertexBufferObjectManager());
                } else if (SpriteNumber == 4) {
                    wallpaper.this.rndball2 = new Ball(wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_WIDTH), wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_HEIGHT), wallpaper.this.ltblueplanet, wallpaper.this.getVertexBufferObjectManager());
                } else if (SpriteNumber == 5) {
                    wallpaper.this.rndball2 = new Ball(wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_WIDTH), wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_HEIGHT), wallpaper.this.bluepurpleplanet, wallpaper.this.getVertexBufferObjectManager());
                } else if (SpriteNumber == 6) {
                    wallpaper.this.rndball2 = new Ball(wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_WIDTH), wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_HEIGHT), wallpaper.this.redplanet, wallpaper.this.getVertexBufferObjectManager());
                } else if (SpriteNumber == 7) {
                    wallpaper.this.rndball2 = new Ball(wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_WIDTH), wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_HEIGHT), wallpaper.this.yellowplanet, wallpaper.this.getVertexBufferObjectManager());
                } else if (SpriteNumber == wallpaper.SATURNS_COUNT) {
                    wallpaper.this.rndball2 = new Ball(wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_WIDTH), wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_HEIGHT), wallpaper.this.orangeplanet, wallpaper.this.getVertexBufferObjectManager());
                } else if (SpriteNumber == 9) {
                    wallpaper.this.rndball2 = new Ball(wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_WIDTH), wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_HEIGHT), wallpaper.this.whiteplanet, wallpaper.this.getVertexBufferObjectManager());
                } else if (SpriteNumber == 10) {
                    wallpaper.this.rndball2 = new Ball(wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_WIDTH), wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_HEIGHT), wallpaper.this.grayplanet, wallpaper.this.getVertexBufferObjectManager());
                } else if (SpriteNumber == 11) {
                    wallpaper.this.rndball2 = new Ball(wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_WIDTH), wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_HEIGHT), wallpaper.this.greenplanet, wallpaper.this.getVertexBufferObjectManager());
                } else if (SpriteNumber == 12) {
                    wallpaper.this.rndball2 = new Ball(wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_WIDTH), wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_HEIGHT), wallpaper.this.ltgreenplanet, wallpaper.this.getVertexBufferObjectManager());
                } else if (SpriteNumber == 13) {
                    wallpaper.this.rndball2 = new Ball(wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_WIDTH), wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_HEIGHT), wallpaper.this.bluemoon, wallpaper.this.getVertexBufferObjectManager());
                } else if (SpriteNumber == 14) {
                    wallpaper.this.rndball2 = new Ball(wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_WIDTH), wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_HEIGHT), wallpaper.this.saturn, wallpaper.this.getVertexBufferObjectManager());
                } else if (SpriteNumber == 15) {
                    wallpaper.this.rndball2 = new Ball(wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_WIDTH), wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_HEIGHT), wallpaper.this.firefly, wallpaper.this.getVertexBufferObjectManager());
                } else if (SpriteNumber == 16) {
                    wallpaper.this.rndball2 = new Ball(wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_WIDTH), wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_HEIGHT), wallpaper.this.fly, wallpaper.this.getVertexBufferObjectManager());
                } else if (SpriteNumber == 17) {
                    wallpaper.this.rndball2 = new Ball(wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_WIDTH), wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_HEIGHT), wallpaper.this.moon, wallpaper.this.getVertexBufferObjectManager());
                } else if (SpriteNumber == 18) {
                    wallpaper.this.rndball2 = new Ball(wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_WIDTH), wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_HEIGHT), wallpaper.this.star, wallpaper.this.getVertexBufferObjectManager());
                } else if (SpriteNumber == 19) {
                    wallpaper.this.rndball2 = new Ball(wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_WIDTH), wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_HEIGHT), wallpaper.this.alien3, wallpaper.this.getVertexBufferObjectManager());
                } else if (SpriteNumber == 20) {
                    wallpaper.this.rndball2 = new Ball(wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_WIDTH), wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_HEIGHT), wallpaper.this.alien4, wallpaper.this.getVertexBufferObjectManager());
                } else if (SpriteNumber == 21) {
                    wallpaper.this.rndball2 = new Ball(wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_WIDTH), wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_HEIGHT), wallpaper.this.ufo, wallpaper.this.getVertexBufferObjectManager());
                } else if (SpriteNumber == 22) {
                    wallpaper.this.rndball2 = new Ball(wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_WIDTH), wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_HEIGHT), wallpaper.this.light, wallpaper.this.getVertexBufferObjectManager());
                } else if (SpriteNumber == 23) {
                    wallpaper.this.rndball2 = new Ball(wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_WIDTH), wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_HEIGHT), wallpaper.this.lighton, wallpaper.this.getVertexBufferObjectManager());
                } else if (SpriteNumber == 24) {
                    wallpaper.this.rndball2 = new Ball(wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_WIDTH), wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_HEIGHT), wallpaper.this.lightoff, wallpaper.this.getVertexBufferObjectManager());
                } else if (SpriteNumber == 25) {
                    wallpaper.this.rndball2 = new Ball(wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_WIDTH), wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_HEIGHT), wallpaper.this.mouse, wallpaper.this.getVertexBufferObjectManager());
                } else if (SpriteNumber == 26) {
                    wallpaper.this.rndball2 = new Ball(wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_WIDTH), wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_HEIGHT), wallpaper.this.asteroid1, wallpaper.this.getVertexBufferObjectManager());
                } else if (SpriteNumber == 27) {
                    wallpaper.this.rndball2 = new Ball(wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_WIDTH), wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_HEIGHT), wallpaper.this.asteroid2, wallpaper.this.getVertexBufferObjectManager());
                } else if (SpriteNumber == 28) {
                    wallpaper.this.rndball2 = new Ball(wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_WIDTH), wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_HEIGHT), wallpaper.this.asteroid3, wallpaper.this.getVertexBufferObjectManager());
                } else if (SpriteNumber == 29) {
                    wallpaper.this.rndball2 = new Ball(wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_WIDTH), wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_HEIGHT), wallpaper.this.asteroid4, wallpaper.this.getVertexBufferObjectManager());
                } else if (SpriteNumber == wallpaper.PLANETS_COUNT) {
                    wallpaper.this.rndball2 = new Ball(wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_WIDTH), wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_HEIGHT), wallpaper.this.asteroid5, wallpaper.this.getVertexBufferObjectManager());
                }
                PhysicsHandler physicsHandler = new PhysicsHandler(wallpaper.this.rndball2);
                wallpaper.this.rndball2.registerUpdateHandler(physicsHandler);
                wallpaper.this.rndball2.registerEntityModifier(new LoopEntityModifier(new RotationModifier(30.0f, (float) randrot1, (float) randrot2)));
                physicsHandler.setVelocity(10.0f * (wallpaper.this.random.nextFloat() - 0.5f), 10.0f * (wallpaper.this.random.nextFloat() - 0.5f));
                wallpaper.this.mScene.attachChild(wallpaper.this.rndball2);
            }
        }

        /* renamed from: own.planetswallpaperandengine.wallpaper.1.3 */
        class C01693 implements Runnable {
            C01693() {
            }

            public void run() {
                if (wallpaper.this.rndball3 == null) {
                    Debug.i(wallpaper.TAG, "rndball3 is null");
                } else {
                    Debug.i(wallpaper.TAG, "removing rndball3");
                    wallpaper.this.mScene.detachChild(wallpaper.this.rndball3);
                    wallpaper.this.rndball3.detachSelf();
                    wallpaper.this.rndball3.dispose();
                    wallpaper.this.rndball3 = null;
                }
                Random rand1 = new Random();
                Random rand2 = new Random();
                int[] ROTNUMBERS = new int[]{0, 360};
                int randrot1 = ROTNUMBERS[rand1.nextInt(ROTNUMBERS.length)];
                int randrot2 = ROTNUMBERS[rand2.nextInt(ROTNUMBERS.length)];
                if (randrot1 == 0 && randrot2 == 0) {
                    randrot1 = 360;
                }
                if (randrot1 == 360 && randrot2 == 360) {
                    randrot1 = 0;
                }
                int SpriteNumber = new Random().nextInt(wallpaper.PLANETS_COUNT) + 1;
                Debug.i(wallpaper.TAG, "RandSpriteNumber: " + SpriteNumber);
                if (SpriteNumber == 1) {
                    wallpaper.this.rndball3 = new Ball(wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_WIDTH), wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_HEIGHT), wallpaper.this.neongreenplanet, wallpaper.this.getVertexBufferObjectManager());
                } else if (SpriteNumber == 2) {
                    wallpaper.this.rndball3 = new Ball(wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_WIDTH), wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_HEIGHT), wallpaper.this.blueplanet, wallpaper.this.getVertexBufferObjectManager());
                } else if (SpriteNumber == 3) {
                    wallpaper.this.rndball3 = new Ball(wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_WIDTH), wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_HEIGHT), wallpaper.this.cyanplanet, wallpaper.this.getVertexBufferObjectManager());
                } else if (SpriteNumber == 4) {
                    wallpaper.this.rndball3 = new Ball(wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_WIDTH), wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_HEIGHT), wallpaper.this.ltblueplanet, wallpaper.this.getVertexBufferObjectManager());
                } else if (SpriteNumber == 5) {
                    wallpaper.this.rndball3 = new Ball(wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_WIDTH), wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_HEIGHT), wallpaper.this.bluepurpleplanet, wallpaper.this.getVertexBufferObjectManager());
                } else if (SpriteNumber == 6) {
                    wallpaper.this.rndball3 = new Ball(wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_WIDTH), wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_HEIGHT), wallpaper.this.redplanet, wallpaper.this.getVertexBufferObjectManager());
                } else if (SpriteNumber == 7) {
                    wallpaper.this.rndball3 = new Ball(wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_WIDTH), wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_HEIGHT), wallpaper.this.yellowplanet, wallpaper.this.getVertexBufferObjectManager());
                } else if (SpriteNumber == wallpaper.SATURNS_COUNT) {
                    wallpaper.this.rndball3 = new Ball(wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_WIDTH), wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_HEIGHT), wallpaper.this.orangeplanet, wallpaper.this.getVertexBufferObjectManager());
                } else if (SpriteNumber == 9) {
                    wallpaper.this.rndball3 = new Ball(wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_WIDTH), wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_HEIGHT), wallpaper.this.whiteplanet, wallpaper.this.getVertexBufferObjectManager());
                } else if (SpriteNumber == 10) {
                    wallpaper.this.rndball3 = new Ball(wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_WIDTH), wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_HEIGHT), wallpaper.this.grayplanet, wallpaper.this.getVertexBufferObjectManager());
                } else if (SpriteNumber == 11) {
                    wallpaper.this.rndball3 = new Ball(wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_WIDTH), wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_HEIGHT), wallpaper.this.greenplanet, wallpaper.this.getVertexBufferObjectManager());
                } else if (SpriteNumber == 12) {
                    wallpaper.this.rndball3 = new Ball(wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_WIDTH), wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_HEIGHT), wallpaper.this.ltgreenplanet, wallpaper.this.getVertexBufferObjectManager());
                } else if (SpriteNumber == 13) {
                    wallpaper.this.rndball3 = new Ball(wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_WIDTH), wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_HEIGHT), wallpaper.this.bluemoon, wallpaper.this.getVertexBufferObjectManager());
                } else if (SpriteNumber == 14) {
                    wallpaper.this.rndball3 = new Ball(wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_WIDTH), wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_HEIGHT), wallpaper.this.saturn, wallpaper.this.getVertexBufferObjectManager());
                } else if (SpriteNumber == 15) {
                    wallpaper.this.rndball3 = new Ball(wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_WIDTH), wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_HEIGHT), wallpaper.this.firefly, wallpaper.this.getVertexBufferObjectManager());
                } else if (SpriteNumber == 16) {
                    wallpaper.this.rndball3 = new Ball(wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_WIDTH), wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_HEIGHT), wallpaper.this.fly, wallpaper.this.getVertexBufferObjectManager());
                } else if (SpriteNumber == 17) {
                    wallpaper.this.rndball3 = new Ball(wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_WIDTH), wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_HEIGHT), wallpaper.this.moon, wallpaper.this.getVertexBufferObjectManager());
                } else if (SpriteNumber == 18) {
                    wallpaper.this.rndball3 = new Ball(wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_WIDTH), wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_HEIGHT), wallpaper.this.star, wallpaper.this.getVertexBufferObjectManager());
                } else if (SpriteNumber == 19) {
                    wallpaper.this.rndball3 = new Ball(wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_WIDTH), wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_HEIGHT), wallpaper.this.alien3, wallpaper.this.getVertexBufferObjectManager());
                } else if (SpriteNumber == 20) {
                    wallpaper.this.rndball3 = new Ball(wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_WIDTH), wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_HEIGHT), wallpaper.this.alien4, wallpaper.this.getVertexBufferObjectManager());
                } else if (SpriteNumber == 21) {
                    wallpaper.this.rndball3 = new Ball(wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_WIDTH), wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_HEIGHT), wallpaper.this.ufo, wallpaper.this.getVertexBufferObjectManager());
                } else if (SpriteNumber == 22) {
                    wallpaper.this.rndball3 = new Ball(wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_WIDTH), wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_HEIGHT), wallpaper.this.light, wallpaper.this.getVertexBufferObjectManager());
                } else if (SpriteNumber == 23) {
                    wallpaper.this.rndball3 = new Ball(wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_WIDTH), wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_HEIGHT), wallpaper.this.lighton, wallpaper.this.getVertexBufferObjectManager());
                } else if (SpriteNumber == 24) {
                    wallpaper.this.rndball3 = new Ball(wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_WIDTH), wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_HEIGHT), wallpaper.this.lightoff, wallpaper.this.getVertexBufferObjectManager());
                } else if (SpriteNumber == 25) {
                    wallpaper.this.rndball3 = new Ball(wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_WIDTH), wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_HEIGHT), wallpaper.this.mouse, wallpaper.this.getVertexBufferObjectManager());
                } else if (SpriteNumber == 26) {
                    wallpaper.this.rndball3 = new Ball(wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_WIDTH), wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_HEIGHT), wallpaper.this.asteroid1, wallpaper.this.getVertexBufferObjectManager());
                } else if (SpriteNumber == 27) {
                    wallpaper.this.rndball3 = new Ball(wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_WIDTH), wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_HEIGHT), wallpaper.this.asteroid2, wallpaper.this.getVertexBufferObjectManager());
                } else if (SpriteNumber == 28) {
                    wallpaper.this.rndball3 = new Ball(wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_WIDTH), wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_HEIGHT), wallpaper.this.asteroid3, wallpaper.this.getVertexBufferObjectManager());
                } else if (SpriteNumber == 29) {
                    wallpaper.this.rndball3 = new Ball(wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_WIDTH), wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_HEIGHT), wallpaper.this.asteroid4, wallpaper.this.getVertexBufferObjectManager());
                } else if (SpriteNumber == wallpaper.PLANETS_COUNT) {
                    wallpaper.this.rndball3 = new Ball(wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_WIDTH), wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_HEIGHT), wallpaper.this.asteroid5, wallpaper.this.getVertexBufferObjectManager());
                }
                PhysicsHandler physicsHandler = new PhysicsHandler(wallpaper.this.rndball3);
                wallpaper.this.rndball3.registerUpdateHandler(physicsHandler);
                wallpaper.this.rndball3.registerEntityModifier(new LoopEntityModifier(new RotationModifier(30.0f, (float) randrot1, (float) randrot2)));
                physicsHandler.setVelocity(10.0f * (wallpaper.this.random.nextFloat() - 0.5f), 10.0f * (wallpaper.this.random.nextFloat() - 0.5f));
                wallpaper.this.mScene.attachChild(wallpaper.this.rndball3);
            }
        }

        /* renamed from: own.planetswallpaperandengine.wallpaper.1.4 */
        class C01704 implements Runnable {
            C01704() {
            }

            public void run() {
                if (wallpaper.this.rndball4 == null) {
                    Debug.i(wallpaper.TAG, "rndball4 is null");
                } else {
                    Debug.i(wallpaper.TAG, "removing rndball4");
                    wallpaper.this.mScene.detachChild(wallpaper.this.rndball4);
                    wallpaper.this.rndball4.detachSelf();
                    wallpaper.this.rndball4.dispose();
                    wallpaper.this.rndball4 = null;
                }
                Random rand1 = new Random();
                Random rand2 = new Random();
                int[] ROTNUMBERS = new int[]{0, 360};
                int randrot1 = ROTNUMBERS[rand1.nextInt(ROTNUMBERS.length)];
                int randrot2 = ROTNUMBERS[rand2.nextInt(ROTNUMBERS.length)];
                if (randrot1 == 0 && randrot2 == 0) {
                    randrot1 = 360;
                }
                if (randrot1 == 360 && randrot2 == 360) {
                    randrot1 = 0;
                }
                int SpriteNumber = new Random().nextInt(wallpaper.PLANETS_COUNT) + 1;
                Debug.i(wallpaper.TAG, "RandSpriteNumber: " + SpriteNumber);
                if (SpriteNumber == 1) {
                    wallpaper.this.rndball4 = new Ball(wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_WIDTH), wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_HEIGHT), wallpaper.this.neongreenplanet, wallpaper.this.getVertexBufferObjectManager());
                } else if (SpriteNumber == 2) {
                    wallpaper.this.rndball4 = new Ball(wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_WIDTH), wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_HEIGHT), wallpaper.this.blueplanet, wallpaper.this.getVertexBufferObjectManager());
                } else if (SpriteNumber == 3) {
                    wallpaper.this.rndball4 = new Ball(wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_WIDTH), wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_HEIGHT), wallpaper.this.cyanplanet, wallpaper.this.getVertexBufferObjectManager());
                } else if (SpriteNumber == 4) {
                    wallpaper.this.rndball4 = new Ball(wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_WIDTH), wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_HEIGHT), wallpaper.this.ltblueplanet, wallpaper.this.getVertexBufferObjectManager());
                } else if (SpriteNumber == 5) {
                    wallpaper.this.rndball4 = new Ball(wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_WIDTH), wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_HEIGHT), wallpaper.this.bluepurpleplanet, wallpaper.this.getVertexBufferObjectManager());
                } else if (SpriteNumber == 6) {
                    wallpaper.this.rndball4 = new Ball(wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_WIDTH), wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_HEIGHT), wallpaper.this.redplanet, wallpaper.this.getVertexBufferObjectManager());
                } else if (SpriteNumber == 7) {
                    wallpaper.this.rndball4 = new Ball(wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_WIDTH), wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_HEIGHT), wallpaper.this.yellowplanet, wallpaper.this.getVertexBufferObjectManager());
                } else if (SpriteNumber == wallpaper.SATURNS_COUNT) {
                    wallpaper.this.rndball4 = new Ball(wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_WIDTH), wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_HEIGHT), wallpaper.this.orangeplanet, wallpaper.this.getVertexBufferObjectManager());
                } else if (SpriteNumber == 9) {
                    wallpaper.this.rndball4 = new Ball(wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_WIDTH), wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_HEIGHT), wallpaper.this.whiteplanet, wallpaper.this.getVertexBufferObjectManager());
                } else if (SpriteNumber == 10) {
                    wallpaper.this.rndball4 = new Ball(wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_WIDTH), wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_HEIGHT), wallpaper.this.grayplanet, wallpaper.this.getVertexBufferObjectManager());
                } else if (SpriteNumber == 11) {
                    wallpaper.this.rndball4 = new Ball(wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_WIDTH), wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_HEIGHT), wallpaper.this.greenplanet, wallpaper.this.getVertexBufferObjectManager());
                } else if (SpriteNumber == 12) {
                    wallpaper.this.rndball4 = new Ball(wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_WIDTH), wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_HEIGHT), wallpaper.this.ltgreenplanet, wallpaper.this.getVertexBufferObjectManager());
                } else if (SpriteNumber == 13) {
                    wallpaper.this.rndball4 = new Ball(wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_WIDTH), wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_HEIGHT), wallpaper.this.bluemoon, wallpaper.this.getVertexBufferObjectManager());
                } else if (SpriteNumber == 14) {
                    wallpaper.this.rndball4 = new Ball(wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_WIDTH), wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_HEIGHT), wallpaper.this.saturn, wallpaper.this.getVertexBufferObjectManager());
                } else if (SpriteNumber == 15) {
                    wallpaper.this.rndball4 = new Ball(wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_WIDTH), wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_HEIGHT), wallpaper.this.firefly, wallpaper.this.getVertexBufferObjectManager());
                } else if (SpriteNumber == 16) {
                    wallpaper.this.rndball4 = new Ball(wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_WIDTH), wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_HEIGHT), wallpaper.this.fly, wallpaper.this.getVertexBufferObjectManager());
                } else if (SpriteNumber == 17) {
                    wallpaper.this.rndball4 = new Ball(wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_WIDTH), wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_HEIGHT), wallpaper.this.moon, wallpaper.this.getVertexBufferObjectManager());
                } else if (SpriteNumber == 18) {
                    wallpaper.this.rndball4 = new Ball(wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_WIDTH), wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_HEIGHT), wallpaper.this.star, wallpaper.this.getVertexBufferObjectManager());
                } else if (SpriteNumber == 19) {
                    wallpaper.this.rndball4 = new Ball(wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_WIDTH), wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_HEIGHT), wallpaper.this.alien3, wallpaper.this.getVertexBufferObjectManager());
                } else if (SpriteNumber == 20) {
                    wallpaper.this.rndball4 = new Ball(wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_WIDTH), wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_HEIGHT), wallpaper.this.alien4, wallpaper.this.getVertexBufferObjectManager());
                } else if (SpriteNumber == 21) {
                    wallpaper.this.rndball4 = new Ball(wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_WIDTH), wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_HEIGHT), wallpaper.this.ufo, wallpaper.this.getVertexBufferObjectManager());
                } else if (SpriteNumber == 22) {
                    wallpaper.this.rndball4 = new Ball(wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_WIDTH), wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_HEIGHT), wallpaper.this.light, wallpaper.this.getVertexBufferObjectManager());
                } else if (SpriteNumber == 23) {
                    wallpaper.this.rndball4 = new Ball(wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_WIDTH), wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_HEIGHT), wallpaper.this.lighton, wallpaper.this.getVertexBufferObjectManager());
                } else if (SpriteNumber == 24) {
                    wallpaper.this.rndball4 = new Ball(wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_WIDTH), wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_HEIGHT), wallpaper.this.lightoff, wallpaper.this.getVertexBufferObjectManager());
                } else if (SpriteNumber == 25) {
                    wallpaper.this.rndball4 = new Ball(wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_WIDTH), wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_HEIGHT), wallpaper.this.mouse, wallpaper.this.getVertexBufferObjectManager());
                } else if (SpriteNumber == 26) {
                    wallpaper.this.rndball4 = new Ball(wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_WIDTH), wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_HEIGHT), wallpaper.this.asteroid1, wallpaper.this.getVertexBufferObjectManager());
                } else if (SpriteNumber == 27) {
                    wallpaper.this.rndball4 = new Ball(wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_WIDTH), wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_HEIGHT), wallpaper.this.asteroid2, wallpaper.this.getVertexBufferObjectManager());
                } else if (SpriteNumber == 28) {
                    wallpaper.this.rndball4 = new Ball(wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_WIDTH), wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_HEIGHT), wallpaper.this.asteroid3, wallpaper.this.getVertexBufferObjectManager());
                } else if (SpriteNumber == 29) {
                    wallpaper.this.rndball4 = new Ball(wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_WIDTH), wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_HEIGHT), wallpaper.this.asteroid4, wallpaper.this.getVertexBufferObjectManager());
                } else if (SpriteNumber == wallpaper.PLANETS_COUNT) {
                    wallpaper.this.rndball4 = new Ball(wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_WIDTH), wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_HEIGHT), wallpaper.this.asteroid5, wallpaper.this.getVertexBufferObjectManager());
                }
                PhysicsHandler physicsHandler = new PhysicsHandler(wallpaper.this.rndball4);
                wallpaper.this.rndball4.registerUpdateHandler(physicsHandler);
                wallpaper.this.rndball4.registerEntityModifier(new LoopEntityModifier(new RotationModifier(30.0f, (float) randrot1, (float) randrot2)));
                physicsHandler.setVelocity(10.0f * (wallpaper.this.random.nextFloat() - 0.5f), 10.0f * (wallpaper.this.random.nextFloat() - 0.5f));
                wallpaper.this.mScene.attachChild(wallpaper.this.rndball4);
            }
        }

        /* renamed from: own.planetswallpaperandengine.wallpaper.1.5 */
        class C01715 implements Runnable {
            C01715() {
            }

            public void run() {
                if (wallpaper.this.rndball5 == null) {
                    Debug.i(wallpaper.TAG, "rndball5 is null");
                } else {
                    Debug.i(wallpaper.TAG, "removing rndball5");
                    wallpaper.this.mScene.detachChild(wallpaper.this.rndball5);
                    wallpaper.this.rndball5.detachSelf();
                    wallpaper.this.rndball5.dispose();
                    wallpaper.this.rndball5 = null;
                }
                Random rand1 = new Random();
                Random rand2 = new Random();
                int[] ROTNUMBERS = new int[]{0, 360};
                int randrot1 = ROTNUMBERS[rand1.nextInt(ROTNUMBERS.length)];
                int randrot2 = ROTNUMBERS[rand2.nextInt(ROTNUMBERS.length)];
                if (randrot1 == 0 && randrot2 == 0) {
                    randrot1 = 360;
                }
                if (randrot1 == 360 && randrot2 == 360) {
                    randrot1 = 0;
                }
                int SpriteNumber = new Random().nextInt(wallpaper.PLANETS_COUNT) + 1;
                Debug.i(wallpaper.TAG, "RandSpriteNumber: " + SpriteNumber);
                if (SpriteNumber == 1) {
                    wallpaper.this.rndball5 = new Ball(wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_WIDTH), wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_HEIGHT), wallpaper.this.neongreenplanet, wallpaper.this.getVertexBufferObjectManager());
                } else if (SpriteNumber == 2) {
                    wallpaper.this.rndball5 = new Ball(wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_WIDTH), wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_HEIGHT), wallpaper.this.blueplanet, wallpaper.this.getVertexBufferObjectManager());
                } else if (SpriteNumber == 3) {
                    wallpaper.this.rndball5 = new Ball(wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_WIDTH), wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_HEIGHT), wallpaper.this.cyanplanet, wallpaper.this.getVertexBufferObjectManager());
                } else if (SpriteNumber == 4) {
                    wallpaper.this.rndball5 = new Ball(wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_WIDTH), wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_HEIGHT), wallpaper.this.ltblueplanet, wallpaper.this.getVertexBufferObjectManager());
                } else if (SpriteNumber == 5) {
                    wallpaper.this.rndball5 = new Ball(wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_WIDTH), wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_HEIGHT), wallpaper.this.bluepurpleplanet, wallpaper.this.getVertexBufferObjectManager());
                } else if (SpriteNumber == 6) {
                    wallpaper.this.rndball5 = new Ball(wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_WIDTH), wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_HEIGHT), wallpaper.this.redplanet, wallpaper.this.getVertexBufferObjectManager());
                } else if (SpriteNumber == 7) {
                    wallpaper.this.rndball5 = new Ball(wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_WIDTH), wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_HEIGHT), wallpaper.this.yellowplanet, wallpaper.this.getVertexBufferObjectManager());
                } else if (SpriteNumber == wallpaper.SATURNS_COUNT) {
                    wallpaper.this.rndball5 = new Ball(wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_WIDTH), wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_HEIGHT), wallpaper.this.orangeplanet, wallpaper.this.getVertexBufferObjectManager());
                } else if (SpriteNumber == 9) {
                    wallpaper.this.rndball5 = new Ball(wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_WIDTH), wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_HEIGHT), wallpaper.this.whiteplanet, wallpaper.this.getVertexBufferObjectManager());
                } else if (SpriteNumber == 10) {
                    wallpaper.this.rndball5 = new Ball(wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_WIDTH), wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_HEIGHT), wallpaper.this.grayplanet, wallpaper.this.getVertexBufferObjectManager());
                } else if (SpriteNumber == 11) {
                    wallpaper.this.rndball5 = new Ball(wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_WIDTH), wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_HEIGHT), wallpaper.this.greenplanet, wallpaper.this.getVertexBufferObjectManager());
                } else if (SpriteNumber == 12) {
                    wallpaper.this.rndball5 = new Ball(wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_WIDTH), wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_HEIGHT), wallpaper.this.ltgreenplanet, wallpaper.this.getVertexBufferObjectManager());
                } else if (SpriteNumber == 13) {
                    wallpaper.this.rndball5 = new Ball(wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_WIDTH), wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_HEIGHT), wallpaper.this.bluemoon, wallpaper.this.getVertexBufferObjectManager());
                } else if (SpriteNumber == 14) {
                    wallpaper.this.rndball5 = new Ball(wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_WIDTH), wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_HEIGHT), wallpaper.this.saturn, wallpaper.this.getVertexBufferObjectManager());
                } else if (SpriteNumber == 15) {
                    wallpaper.this.rndball5 = new Ball(wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_WIDTH), wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_HEIGHT), wallpaper.this.firefly, wallpaper.this.getVertexBufferObjectManager());
                } else if (SpriteNumber == 16) {
                    wallpaper.this.rndball5 = new Ball(wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_WIDTH), wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_HEIGHT), wallpaper.this.fly, wallpaper.this.getVertexBufferObjectManager());
                } else if (SpriteNumber == 17) {
                    wallpaper.this.rndball5 = new Ball(wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_WIDTH), wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_HEIGHT), wallpaper.this.moon, wallpaper.this.getVertexBufferObjectManager());
                } else if (SpriteNumber == 18) {
                    wallpaper.this.rndball5 = new Ball(wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_WIDTH), wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_HEIGHT), wallpaper.this.star, wallpaper.this.getVertexBufferObjectManager());
                } else if (SpriteNumber == 19) {
                    wallpaper.this.rndball5 = new Ball(wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_WIDTH), wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_HEIGHT), wallpaper.this.alien3, wallpaper.this.getVertexBufferObjectManager());
                } else if (SpriteNumber == 20) {
                    wallpaper.this.rndball5 = new Ball(wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_WIDTH), wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_HEIGHT), wallpaper.this.alien4, wallpaper.this.getVertexBufferObjectManager());
                } else if (SpriteNumber == 21) {
                    wallpaper.this.rndball5 = new Ball(wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_WIDTH), wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_HEIGHT), wallpaper.this.ufo, wallpaper.this.getVertexBufferObjectManager());
                } else if (SpriteNumber == 22) {
                    wallpaper.this.rndball5 = new Ball(wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_WIDTH), wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_HEIGHT), wallpaper.this.light, wallpaper.this.getVertexBufferObjectManager());
                } else if (SpriteNumber == 23) {
                    wallpaper.this.rndball5 = new Ball(wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_WIDTH), wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_HEIGHT), wallpaper.this.lighton, wallpaper.this.getVertexBufferObjectManager());
                } else if (SpriteNumber == 24) {
                    wallpaper.this.rndball5 = new Ball(wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_WIDTH), wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_HEIGHT), wallpaper.this.lightoff, wallpaper.this.getVertexBufferObjectManager());
                } else if (SpriteNumber == 25) {
                    wallpaper.this.rndball5 = new Ball(wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_WIDTH), wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_HEIGHT), wallpaper.this.mouse, wallpaper.this.getVertexBufferObjectManager());
                } else if (SpriteNumber == 26) {
                    wallpaper.this.rndball5 = new Ball(wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_WIDTH), wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_HEIGHT), wallpaper.this.asteroid1, wallpaper.this.getVertexBufferObjectManager());
                } else if (SpriteNumber == 27) {
                    wallpaper.this.rndball5 = new Ball(wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_WIDTH), wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_HEIGHT), wallpaper.this.asteroid2, wallpaper.this.getVertexBufferObjectManager());
                } else if (SpriteNumber == 28) {
                    wallpaper.this.rndball5 = new Ball(wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_WIDTH), wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_HEIGHT), wallpaper.this.asteroid3, wallpaper.this.getVertexBufferObjectManager());
                } else if (SpriteNumber == 29) {
                    wallpaper.this.rndball5 = new Ball(wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_WIDTH), wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_HEIGHT), wallpaper.this.asteroid4, wallpaper.this.getVertexBufferObjectManager());
                } else if (SpriteNumber == wallpaper.PLANETS_COUNT) {
                    wallpaper.this.rndball5 = new Ball(wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_WIDTH), wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_HEIGHT), wallpaper.this.asteroid5, wallpaper.this.getVertexBufferObjectManager());
                }
                PhysicsHandler physicsHandler = new PhysicsHandler(wallpaper.this.rndball5);
                wallpaper.this.rndball5.registerUpdateHandler(physicsHandler);
                wallpaper.this.rndball5.registerEntityModifier(new LoopEntityModifier(new RotationModifier(30.0f, (float) randrot1, (float) randrot2)));
                physicsHandler.setVelocity(10.0f * (wallpaper.this.random.nextFloat() - 0.5f), 10.0f * (wallpaper.this.random.nextFloat() - 0.5f));
                wallpaper.this.mScene.attachChild(wallpaper.this.rndball5);
            }
        }

        /* renamed from: own.planetswallpaperandengine.wallpaper.1.6 */
        class C01726 implements Runnable {
            C01726() {
            }

            public void run() {
                if (wallpaper.this.rndball6 == null) {
                    Debug.i(wallpaper.TAG, "rndball6 is null");
                } else {
                    Debug.i(wallpaper.TAG, "removing rndball6");
                    wallpaper.this.mScene.detachChild(wallpaper.this.rndball6);
                    wallpaper.this.rndball6.detachSelf();
                    wallpaper.this.rndball6.dispose();
                    wallpaper.this.rndball6 = null;
                }
                Random rand1 = new Random();
                Random rand2 = new Random();
                int[] ROTNUMBERS = new int[]{0, 360};
                int randrot1 = ROTNUMBERS[rand1.nextInt(ROTNUMBERS.length)];
                int randrot2 = ROTNUMBERS[rand2.nextInt(ROTNUMBERS.length)];
                if (randrot1 == 0 && randrot2 == 0) {
                    randrot1 = 360;
                }
                if (randrot1 == 360 && randrot2 == 360) {
                    randrot1 = 0;
                }
                int SpriteNumber = new Random().nextInt(wallpaper.PLANETS_COUNT) + 1;
                Debug.i(wallpaper.TAG, "RandSpriteNumber: " + SpriteNumber);
                if (SpriteNumber == 1) {
                    wallpaper.this.rndball6 = new Ball(wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_WIDTH), wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_HEIGHT), wallpaper.this.neongreenplanet, wallpaper.this.getVertexBufferObjectManager());
                } else if (SpriteNumber == 2) {
                    wallpaper.this.rndball6 = new Ball(wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_WIDTH), wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_HEIGHT), wallpaper.this.blueplanet, wallpaper.this.getVertexBufferObjectManager());
                } else if (SpriteNumber == 3) {
                    wallpaper.this.rndball6 = new Ball(wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_WIDTH), wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_HEIGHT), wallpaper.this.cyanplanet, wallpaper.this.getVertexBufferObjectManager());
                } else if (SpriteNumber == 4) {
                    wallpaper.this.rndball6 = new Ball(wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_WIDTH), wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_HEIGHT), wallpaper.this.ltblueplanet, wallpaper.this.getVertexBufferObjectManager());
                } else if (SpriteNumber == 5) {
                    wallpaper.this.rndball6 = new Ball(wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_WIDTH), wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_HEIGHT), wallpaper.this.bluepurpleplanet, wallpaper.this.getVertexBufferObjectManager());
                } else if (SpriteNumber == 6) {
                    wallpaper.this.rndball6 = new Ball(wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_WIDTH), wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_HEIGHT), wallpaper.this.redplanet, wallpaper.this.getVertexBufferObjectManager());
                } else if (SpriteNumber == 7) {
                    wallpaper.this.rndball6 = new Ball(wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_WIDTH), wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_HEIGHT), wallpaper.this.yellowplanet, wallpaper.this.getVertexBufferObjectManager());
                } else if (SpriteNumber == wallpaper.SATURNS_COUNT) {
                    wallpaper.this.rndball6 = new Ball(wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_WIDTH), wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_HEIGHT), wallpaper.this.orangeplanet, wallpaper.this.getVertexBufferObjectManager());
                } else if (SpriteNumber == 9) {
                    wallpaper.this.rndball6 = new Ball(wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_WIDTH), wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_HEIGHT), wallpaper.this.whiteplanet, wallpaper.this.getVertexBufferObjectManager());
                } else if (SpriteNumber == 10) {
                    wallpaper.this.rndball6 = new Ball(wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_WIDTH), wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_HEIGHT), wallpaper.this.grayplanet, wallpaper.this.getVertexBufferObjectManager());
                } else if (SpriteNumber == 11) {
                    wallpaper.this.rndball6 = new Ball(wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_WIDTH), wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_HEIGHT), wallpaper.this.greenplanet, wallpaper.this.getVertexBufferObjectManager());
                } else if (SpriteNumber == 12) {
                    wallpaper.this.rndball6 = new Ball(wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_WIDTH), wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_HEIGHT), wallpaper.this.ltgreenplanet, wallpaper.this.getVertexBufferObjectManager());
                } else if (SpriteNumber == 13) {
                    wallpaper.this.rndball6 = new Ball(wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_WIDTH), wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_HEIGHT), wallpaper.this.bluemoon, wallpaper.this.getVertexBufferObjectManager());
                } else if (SpriteNumber == 14) {
                    wallpaper.this.rndball6 = new Ball(wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_WIDTH), wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_HEIGHT), wallpaper.this.saturn, wallpaper.this.getVertexBufferObjectManager());
                } else if (SpriteNumber == 15) {
                    wallpaper.this.rndball6 = new Ball(wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_WIDTH), wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_HEIGHT), wallpaper.this.firefly, wallpaper.this.getVertexBufferObjectManager());
                } else if (SpriteNumber == 16) {
                    wallpaper.this.rndball6 = new Ball(wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_WIDTH), wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_HEIGHT), wallpaper.this.fly, wallpaper.this.getVertexBufferObjectManager());
                } else if (SpriteNumber == 17) {
                    wallpaper.this.rndball6 = new Ball(wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_WIDTH), wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_HEIGHT), wallpaper.this.moon, wallpaper.this.getVertexBufferObjectManager());
                } else if (SpriteNumber == 18) {
                    wallpaper.this.rndball6 = new Ball(wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_WIDTH), wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_HEIGHT), wallpaper.this.star, wallpaper.this.getVertexBufferObjectManager());
                } else if (SpriteNumber == 19) {
                    wallpaper.this.rndball6 = new Ball(wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_WIDTH), wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_HEIGHT), wallpaper.this.alien3, wallpaper.this.getVertexBufferObjectManager());
                } else if (SpriteNumber == 20) {
                    wallpaper.this.rndball6 = new Ball(wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_WIDTH), wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_HEIGHT), wallpaper.this.alien4, wallpaper.this.getVertexBufferObjectManager());
                } else if (SpriteNumber == 21) {
                    wallpaper.this.rndball6 = new Ball(wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_WIDTH), wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_HEIGHT), wallpaper.this.ufo, wallpaper.this.getVertexBufferObjectManager());
                } else if (SpriteNumber == 22) {
                    wallpaper.this.rndball6 = new Ball(wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_WIDTH), wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_HEIGHT), wallpaper.this.light, wallpaper.this.getVertexBufferObjectManager());
                } else if (SpriteNumber == 23) {
                    wallpaper.this.rndball6 = new Ball(wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_WIDTH), wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_HEIGHT), wallpaper.this.lighton, wallpaper.this.getVertexBufferObjectManager());
                } else if (SpriteNumber == 24) {
                    wallpaper.this.rndball6 = new Ball(wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_WIDTH), wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_HEIGHT), wallpaper.this.lightoff, wallpaper.this.getVertexBufferObjectManager());
                } else if (SpriteNumber == 25) {
                    wallpaper.this.rndball6 = new Ball(wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_WIDTH), wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_HEIGHT), wallpaper.this.mouse, wallpaper.this.getVertexBufferObjectManager());
                } else if (SpriteNumber == 26) {
                    wallpaper.this.rndball6 = new Ball(wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_WIDTH), wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_HEIGHT), wallpaper.this.asteroid1, wallpaper.this.getVertexBufferObjectManager());
                } else if (SpriteNumber == 27) {
                    wallpaper.this.rndball6 = new Ball(wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_WIDTH), wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_HEIGHT), wallpaper.this.asteroid2, wallpaper.this.getVertexBufferObjectManager());
                } else if (SpriteNumber == 28) {
                    wallpaper.this.rndball6 = new Ball(wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_WIDTH), wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_HEIGHT), wallpaper.this.asteroid3, wallpaper.this.getVertexBufferObjectManager());
                } else if (SpriteNumber == 29) {
                    wallpaper.this.rndball6 = new Ball(wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_WIDTH), wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_HEIGHT), wallpaper.this.asteroid4, wallpaper.this.getVertexBufferObjectManager());
                } else if (SpriteNumber == wallpaper.PLANETS_COUNT) {
                    wallpaper.this.rndball6 = new Ball(wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_WIDTH), wallpaper.this.random.nextFloat() * ((float) wallpaper.CAMERA_HEIGHT), wallpaper.this.asteroid5, wallpaper.this.getVertexBufferObjectManager());
                }
                PhysicsHandler physicsHandler = new PhysicsHandler(wallpaper.this.rndball6);
                wallpaper.this.rndball6.registerUpdateHandler(physicsHandler);
                wallpaper.this.rndball6.registerEntityModifier(new LoopEntityModifier(new RotationModifier(30.0f, (float) randrot1, (float) randrot2)));
                physicsHandler.setVelocity(10.0f * (wallpaper.this.random.nextFloat() - 0.5f), 10.0f * (wallpaper.this.random.nextFloat() - 0.5f));
                wallpaper.this.mScene.attachChild(wallpaper.this.rndball6);
            }
        }

        C02981() {
        }

        public void onTimePassed(TimerHandler pTimerHandler) {
            wallpaper.this.mEngine.runOnUpdateThread(new C01671());
            wallpaper.this.mEngine.runOnUpdateThread(new C01682());
            wallpaper.this.mEngine.runOnUpdateThread(new C01693());
            wallpaper.this.mEngine.runOnUpdateThread(new C01704());
            wallpaper.this.mEngine.runOnUpdateThread(new C01715());
            wallpaper.this.mEngine.runOnUpdateThread(new C01726());
        }
    }



    protected class MyBaseWallpaperGLEngine extends GLEngine {
        private ConfigChooser mConfigChooser;
        private EngineRenderer mEngineRenderer;

        public MyBaseWallpaperGLEngine(IRendererListener pRendererListener) {
            super();

            if (this.mConfigChooser == null) {
                wallpaper.this.mEngine.getEngineOptions().getRenderOptions().setMultiSampling(false);
                this.mConfigChooser = new ConfigChooser(wallpaper.this.mEngine.getEngineOptions().getRenderOptions().isMultiSampling());
            }


            setEGLConfigChooser(this.mConfigChooser);
            this.mEngineRenderer = new EngineRenderer(wallpaper.this.mEngine, this.mConfigChooser, pRendererListener);
            setRenderer(this.mEngineRenderer);
            setRenderMode(1);

        }

        public Bundle onCommand(String pAction, int pX, int pY, int pZ, Bundle pExtras, boolean pResultRequested) {
            if (!pAction.equals("android.wallpaper.tap") && pAction.equals("android.home.drop")) {
            }
            return super.onCommand(pAction, pX, pY, pZ, pExtras, pResultRequested);
        }

        public void onResume() {
            super.onResume();
            Debug.i(wallpaper.TAG, "onResume() (GLEngine)");
            wallpaper.this.onResume();
        }

        public void onPause() {
            super.onPause();
            Debug.i(wallpaper.TAG, "onPause()");
            wallpaper.this.onPause();
        }

        public void onDestroy() {
            super.onDestroy();
            Debug.i(wallpaper.TAG, "onDestory()");
            this.mEngineRenderer = null;
        }
    }

    private static class Ball extends Sprite {
        private final PhysicsHandler mPhysicsHandler;
        Random random;

        public Ball(float pX, float pY, ITextureRegion pTextureRegion, VertexBufferObjectManager pVertexBufferObjectManager) {
            super(pX, pY, pTextureRegion, pVertexBufferObjectManager);
            this.random = new Random(System.nanoTime());
            this.mPhysicsHandler = new PhysicsHandler(this);
            registerUpdateHandler(this.mPhysicsHandler);
            this.mPhysicsHandler.setVelocity(wallpaper.DEMO_VELOCITY, wallpaper.DEMO_VELOCITY);
        }

        protected void onManagedUpdate(float pSecondsElapsed) {
            if (this.mX < -100.0f) {
                this.mPhysicsHandler.setVelocityX((this.random.nextFloat() + 1.1f) * wallpaper.DEMO_VELOCITY);
            } else if (this.mX + getWidth() >= ((float) (wallpaper.CAMERA_WIDTH + 100))) {
                this.mPhysicsHandler.setVelocityX((this.random.nextFloat() + 1.1f) * -5.0f);
            }
            if (this.mY < -100.0f) {
                this.mPhysicsHandler.setVelocityY((this.random.nextFloat() + 1.1f) * wallpaper.DEMO_VELOCITY);
            } else if (this.mY + getHeight() >= ((float) (wallpaper.CAMERA_HEIGHT + 100))) {
                this.mPhysicsHandler.setVelocityY((this.random.nextFloat() + 1.1f) * -5.0f);
            }
            super.onManagedUpdate(pSecondsElapsed);
        }
    }

    private static class RandBall extends Sprite {
        private final PhysicsHandler mPhysicsHandler;
        Random random;

        public RandBall(float pX, float pY, ITextureRegion pTextureRegion, VertexBufferObjectManager pVertexBufferObjectManager) {
            super(pX, pY, pTextureRegion, pVertexBufferObjectManager);
            this.random = new Random(System.nanoTime());
            this.mPhysicsHandler = new PhysicsHandler(this);
            registerUpdateHandler(this.mPhysicsHandler);
        }

        protected void onManagedUpdate(float pSecondsElapsed) {
            if (this.mX < -200.0f) {
                this.mPhysicsHandler.setVelocityX((this.random.nextFloat() + 1.1f) * wallpaper.DEMO_VELOCITY);
            } else if (this.mX + getWidth() >= ((float) (wallpaper.CAMERA_WIDTH + 200))) {
                this.mPhysicsHandler.setVelocityX((this.random.nextFloat() + 1.1f) * -5.0f);
            }
            if (this.mY < -200.0f) {
                this.mPhysicsHandler.setVelocityY((this.random.nextFloat() + 1.1f) * wallpaper.DEMO_VELOCITY);
            } else if (this.mY + getHeight() >= ((float) (wallpaper.CAMERA_HEIGHT + 200))) {
                this.mPhysicsHandler.setVelocityY((this.random.nextFloat() + 1.1f) * -5.0f);
            }
            super.onManagedUpdate(pSecondsElapsed);
        }
    }

    public wallpaper() {
        this.mRandom = new Random(RANDOM_SEED);
        this.random = new Random(System.nanoTime());
        this.rndball1 = null;
        this.rndball2 = null;
        this.rndball3 = null;
        this.rndball4 = null;
        this.rndball5 = null;
        this.rndball6 = null;
        this.rndball7 = null;
        this.rndball8 = null;
        this.rndball9 = null;
        this.rndball10 = null;
        this.rndball11 = null;
        this.rndball12 = null;
        this.rndball13 = null;
    }


    public EngineOptions onCreateEngineOptions() {
        this.mCamera = new Camera(0.0f, 0.0f, (float) CAMERA_WIDTH, (float) CAMERA_HEIGHT);
        return new EngineOptions(true, ScreenOrientation.PORTRAIT_FIXED, new FillResolutionPolicy(), this.mCamera);
        ///return new EngineOptions(true, ScreenOrientation.PORTRAIT_FIXED, new RatioResolutionPolicy((float) CAMERA_WIDTH, (float) CAMERA_HEIGHT), this.mCamera);
    }

    public void onCreateResources(OnCreateResourcesCallback pOnCreateResourcesCallback) {
        BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
        this.backgroundTexture = new BitmapTextureAtlas(getTextureManager(), 1080, 1920);
        this.neongreenplanetTexture = new BitmapTextureAtlas(getTextureManager(), 200, 200);
        this.blueplanetTexture = new BitmapTextureAtlas(getTextureManager(), 200, 200);
        this.cyanplanetTexture = new BitmapTextureAtlas(getTextureManager(), 200, 200);
        this.ltblueplanetTexture = new BitmapTextureAtlas(getTextureManager(), 200, 200);
        this.bluepurpleplanetTexture = new BitmapTextureAtlas(getTextureManager(), 200, 200);
        this.redplanetTexture = new BitmapTextureAtlas(getTextureManager(), 200, 200);
        this.yellowplanetTexture = new BitmapTextureAtlas(getTextureManager(), 200, 200);
        this.orangeplanetTexture = new BitmapTextureAtlas(getTextureManager(), 200, 200);
        this.whiteplanetTexture = new BitmapTextureAtlas(getTextureManager(), 200, 200);
        this.grayplanetTexture = new BitmapTextureAtlas(getTextureManager(), 200, 200);
        this.greenplanetTexture = new BitmapTextureAtlas(getTextureManager(), 200, 200);
        this.ltgreenplanetTexture = new BitmapTextureAtlas(getTextureManager(), 200, 200);
        this.bluemoonTexture = new BitmapTextureAtlas(getTextureManager(), 200, 200);
        this.saturnTexture = new BitmapTextureAtlas(getTextureManager(), 200, 200);
        this.fireflyTexture = new BitmapTextureAtlas(getTextureManager(), 200, 200);
        this.flyTexture = new BitmapTextureAtlas(getTextureManager(), 200, 200);
        this.moonTexture = new BitmapTextureAtlas(getTextureManager(), 200, 200);
        this.starTexture = new BitmapTextureAtlas(getTextureManager(), 200, 200);
        this.alien3Texture = new BitmapTextureAtlas(getTextureManager(), 200, 200);
        this.alien4Texture = new BitmapTextureAtlas(getTextureManager(), 200, 200);
        this.ufoTexture = new BitmapTextureAtlas(getTextureManager(), 200, 200);
        this.lightTexture = new BitmapTextureAtlas(getTextureManager(), 200, 200);
        this.lightonTexture = new BitmapTextureAtlas(getTextureManager(), 200, 200);
        this.lightoffTexture = new BitmapTextureAtlas(getTextureManager(), 200, 200);
        this.mouseTexture = new BitmapTextureAtlas(getTextureManager(), 200, 200);
        this.asteroid1Texture = new BitmapTextureAtlas(getTextureManager(), 200, 200);
        this.asteroid2Texture = new BitmapTextureAtlas(getTextureManager(), 200, 200);
        this.asteroid3Texture = new BitmapTextureAtlas(getTextureManager(), 200, 200);
        this.asteroid4Texture = new BitmapTextureAtlas(getTextureManager(), 200, 200);
        this.asteroid5Texture = new BitmapTextureAtlas(getTextureManager(), 200, 200);
        this.background = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.backgroundTexture, (Context) this, "background.png", 0, 0);
        this.neongreenplanet = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.neongreenplanetTexture, (Context) this, "element1.png", 0, 0);
        this.blueplanet = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.blueplanetTexture, (Context) this, "element13.png", 0, 0);
        this.cyanplanet = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.cyanplanetTexture, (Context) this, "element7.png", 0, 0);
        this.ltblueplanet = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.ltblueplanetTexture, (Context) this, "element3.png", 0, 0);
        this.bluepurpleplanet = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.bluepurpleplanetTexture, (Context) this, "element9.png", 0, 0);
        this.redplanet = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.redplanetTexture, (Context) this, "element4.png", 0, 0);
        this.yellowplanet = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.yellowplanetTexture, (Context) this, "element11.png", 0, 0);
        this.orangeplanet = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.orangeplanetTexture, (Context) this, "element12.png", 0, 0);
        this.whiteplanet = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.whiteplanetTexture, (Context) this, "element14.png", 0, 0);
        this.grayplanet = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.grayplanetTexture, (Context) this, "element8.png", 0, 0);
        this.greenplanet = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.greenplanetTexture, (Context) this, "element15.png", 0, 0);
        this.ltgreenplanet = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.ltgreenplanetTexture, (Context) this, "element10.png", 0, 0);
        this.bluemoon = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.bluemoonTexture, (Context) this, "element5.png", 0, 0);
        this.saturn = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.saturnTexture, (Context) this, "element2.png", 0, 0);
        this.firefly = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.fireflyTexture, (Context) this, "firefly.png", 0, 0);
        this.fly = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.flyTexture, (Context) this, "pixelfly.png", 0, 0);
        this.moon = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.moonTexture, (Context) this, "moon.png", 0, 0);
        this.star = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.starTexture, (Context) this, "star32.png", 0, 0);
        this.alien3 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.alien3Texture, (Context) this, "alien3.png", 0, 0);
        this.alien4 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.alien4Texture, (Context) this, "alien4.png", 0, 0);
        this.ufo = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.ufoTexture, (Context) this, "tinyufo.png", 0, 0);
        this.light = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.lightTexture, (Context) this, "light.png", 0, 0);
        this.lighton = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.lightonTexture, (Context) this, "lighton.png", 0, 0);
        this.lightoff = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.lightonTexture, (Context) this, "lightoff.png", 0, 0);
        this.mouse = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.mouseTexture, (Context) this, "bluemouse.png", 0, 0);
        this.asteroid1 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.asteroid1Texture, (Context) this, "asteroid1.png", 0, 0);
        this.asteroid2 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.asteroid2Texture, (Context) this, "asteroid2.png", 0, 0);
        this.asteroid3 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.asteroid3Texture, (Context) this, "asteroid3.png", 0, 0);
        this.asteroid4 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.asteroid4Texture, (Context) this, "asteroid4.png", 0, 0);
        this.asteroid5 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.asteroid5Texture, (Context) this, "asteroid5.png", 0, 0);
        this.backgroundTexture.load();
        this.neongreenplanetTexture.load();
        this.blueplanetTexture.load();
        this.cyanplanetTexture.load();
        this.ltblueplanetTexture.load();
        this.bluepurpleplanetTexture.load();
        this.redplanetTexture.load();
        this.yellowplanetTexture.load();
        this.orangeplanetTexture.load();
        this.whiteplanetTexture.load();
        this.grayplanetTexture.load();
        this.greenplanetTexture.load();
        this.ltgreenplanetTexture.load();
        this.bluemoonTexture.load();
        this.saturnTexture.load();
        this.fireflyTexture.load();
        this.flyTexture.load();
        this.moonTexture.load();
        this.starTexture.load();
        this.alien3Texture.load();
        this.alien4Texture.load();
        this.ufoTexture.load();
        this.lightTexture.load();
        this.lightonTexture.load();
        this.lightoffTexture.load();
        this.mouseTexture.load();
        this.asteroid1Texture.load();
        this.asteroid2Texture.load();
        this.asteroid3Texture.load();
        this.asteroid4Texture.load();
        this.asteroid5Texture.load();
        pOnCreateResourcesCallback.onCreateResourcesFinished();
    }

    public void onCreateScene(OnCreateSceneCallback pOnCreateSceneCallback) {
        int i;
        this.mScene = new Scene();
        this.mScene.setBackground(new SpriteBackground(new Sprite(0.0f, -400.0f, this.background, getVertexBufferObjectManager())));

        for (i = 0; i < PLANETS_COUNT; i++) {
            Random rand1 = new Random();
            Random rand2 = new Random();
            int[] ROTNUMBERS = new int[]{0, 360};
            int randrot1 = ROTNUMBERS[rand1.nextInt(ROTNUMBERS.length)];
            int randrot2 = ROTNUMBERS[rand2.nextInt(ROTNUMBERS.length)];
            if (randrot1 == 0 && randrot2 == 0) {
                randrot1 = 360;
            }
            if (randrot1 == 360 && randrot2 == 360) {
                randrot1 = 0;
            }
            Ball ball = new Ball(this.random.nextFloat() * ((float) CAMERA_WIDTH), this.random.nextFloat() * ((float) CAMERA_HEIGHT), this.neongreenplanet, getVertexBufferObjectManager());
            PhysicsHandler physicsHandler = new PhysicsHandler(ball);
            ball.registerUpdateHandler(physicsHandler);
            ball.registerEntityModifier(new LoopEntityModifier(new RotationModifier(30.0f, (float) randrot1, (float) randrot2)));
            physicsHandler.setVelocity(10.0f * (this.random.nextFloat() - 0.5f), 10.0f * (this.random.nextFloat() - 0.5f));
            this.mScene.attachChild(ball);
        }
        for (i = 0; i < SATURNS_COUNT; i++) {
            Random rand1 = new Random();
            Random rand2 = new Random();
            int[] ROTNUMBERS = new int[]{0, 360};
            int randrot1 = ROTNUMBERS[rand1.nextInt(ROTNUMBERS.length)];
            int randrot2 = ROTNUMBERS[rand2.nextInt(ROTNUMBERS.length)];
            if (randrot1 == 0 && randrot2 == 0) {
                randrot1 = 360;
            }
            if (randrot1 == 360 && randrot2 == 360) {
                randrot1 = 0;
            }
            Ball saturnball = new Ball(this.random.nextFloat() * ((float) CAMERA_WIDTH), this.random.nextFloat() * ((float) CAMERA_HEIGHT), this.saturn, getVertexBufferObjectManager());
            PhysicsHandler physicsHandler = new PhysicsHandler(saturnball);
            saturnball.registerUpdateHandler(physicsHandler);
            saturnball.registerEntityModifier(new LoopEntityModifier(new RotationModifier(30.0f, (float) randrot1, (float) randrot2)));
            physicsHandler.setVelocity(10.0f * (this.random.nextFloat() - 0.5f), 10.0f * (this.random.nextFloat() - 0.5f));
            this.mScene.attachChild(saturnball);
        }
        this.mScene.registerUpdateHandler(new TimerHandler(103.0f, true, new C02981()));
        pOnCreateSceneCallback.onCreateSceneFinished(this.mScene);
    }

    public Engine onCreateEngine() {
        return new MyBaseWallpaperGLEngine(this);
    }

    public void onPopulateScene(Scene pScene, OnPopulateSceneCallback pOnPopulateSceneCallback) {
        pOnPopulateSceneCallback.onPopulateSceneFinished();
    }

    public void onSurfaceChanged(GLState pGLState, int pWidth, int pHeight) {
        super.onSurfaceChanged(pGLState, pWidth, pHeight);
        if ((this.mEngine.getEngineOptions().getScreenOrientation() != ScreenOrientation.PORTRAIT_FIXED || pWidth <= pHeight) && (this.mEngine.getEngineOptions().getScreenOrientation() != ScreenOrientation.LANDSCAPE_FIXED || pHeight <= pWidth)) {
            this.mEngine.getScene().setRotation(0.0f);
        } else {
            this.mEngine.getScene().setRotation(90.0f);
        }
    }
}
