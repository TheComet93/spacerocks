package com.thecomet.spacerocks;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;

class Asteroid extends PhysicsEntity {
    public Asteroid(Context context, LineSoup lineSoup) {
        super(context, lineSoup.cookSoup(80));
        setPosition(100, 100);
        setLinearVelocity(Util.getRandomVelocity(5, 20));
        setAngularVelocity(MathUtils.random(-100, 100));
    }

    @Override
    protected void configureBody(BodyDef bodyDef) {
        bodyDef.type = BodyDef.BodyType.DynamicBody;
    }

    @Override
    protected void configureFixture(FixtureDef fixtureDef) {
        fixtureDef.density = 0.3f;
        fixtureDef.friction = 0.4f;
        fixtureDef.restitution = 0.6f;
        fixtureDef.filter.categoryBits = PhysicsEntity.MASK_ENTITY;
    }
}
