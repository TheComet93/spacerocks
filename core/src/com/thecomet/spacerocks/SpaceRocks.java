package com.thecomet.spacerocks;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class SpaceRocks extends ApplicationAdapter {
    private Stage stage;

    @Override
    public void create() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        Ship player = new Ship();
        stage.addActor(player);
        stage.setKeyboardFocus(player);
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height);
    }

    @Override
    public void render() {
        float timeStep = 1.0f / 60; // TODO Calculate this using previous frame
        stage.act(timeStep);

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.draw();
    }
    
    @Override
    public void dispose() {
        stage.dispose();
    }
}
