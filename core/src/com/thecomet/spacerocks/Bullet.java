package com.thecomet.spacerocks;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.scenes.scene2d.Action;

public class Bullet extends PhysicsEntity {
    private static final float VELOCITY = 800;
    private float life = 0.8f;

    public Bullet(Context context) {
        super(context, LineSoup.load("lines/bullet.json").cookSoup(5));

        addAction(new Action() {
            @Override
            public boolean act(float delta) {
                life -= delta;
                if (life <= 0) {
                    Particle.createExplosion(getContext(), new Vector2(getX(), getY()), 400, 50);
                    remove();
                }
                return false;
            }
        });
    }

    public void setDirection(Vector2 direction) {
        setLinearVelocity(direction.cpy().scl(VELOCITY));
    }

    @Override
    protected void configureBody(BodyDef bodyDef) {
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.bullet = true;
    }

    @Override
    protected void configureFixture(FixtureDef fixtureDef) {
        fixtureDef.density = 1.0f;
        fixtureDef.friction = 0.4f;
        fixtureDef.restitution = 1;
        fixtureDef.filter.categoryBits = PhysicsEntity.MASK_BULLET;
    }

}
