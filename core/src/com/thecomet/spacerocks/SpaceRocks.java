package com.thecomet.spacerocks;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.esotericsoftware.kryonet.Client;

import java.io.IOException;

public class SpaceRocks extends AbstractSpaceRocks {
    private Box2DDebugRenderer debugRenderer;

    @Override
    protected Context createContext() {
        Context context = new Context();
        context.world = new World(new Vector2(0, 0), true);
        context.stage = new Stage();
        context.graphicsUtil = new DrawingGraphicsUtil();
        context.client = new Client();
        return context;
    }

    @Override
    protected void createViewport() {
        Camera camera = new OrthographicCamera();
        Viewport viewport = new ScreenViewport(camera);
        context.stage.setViewport(viewport);
    }

    @Override
    protected void createRenderers() {
        debugRenderer = new Box2DDebugRenderer();
    }

    @Override
    protected void setupNetworking() {
        context.client.start();
        try {
            context.client.connect(5000, "127.0.0.1", 8234, 11434);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void stepLogic(float delta) {
        context.stage.act(delta);
    }

    @Override
    protected void stepPhysics(float delta) {
        context.world.step(delta, 6, 2);
    }

    @Override
    protected void stepNetwork(float delta) {
        context.stage.getRoot().fire(new StepNetworkEvent());
    }

    @Override
    protected void preDraw() {
        context.stage.getRoot().fire(new PreDrawEvent());
    }

    @Override
    protected void draw() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        context.stage.draw();
    }

    @Override
    protected void drawDebug() {
        debugRenderer.render(context.world, context.stage.getCamera().combined.scl(PhysicsEntity.WORLD_SCALE));
    }

    @Override
    public void dispose() {
        super.dispose();
        debugRenderer.dispose();
    }
}
