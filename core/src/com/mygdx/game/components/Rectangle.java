package com.mygdx.game.components;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Rectangle extends Actor {

    private Texture texture;
    private Street street;

    private Color color;

    public Rectangle(float x, float y, float width, float height, Color color, Street street) {
        this.color = color;
        this.street = street;

        createTexture((int)width, (int)height, color);

        setX(x);
        setY(y);
        setWidth(width);
        setHeight(height);
    }

    public Street getStreet() {
        return street;
    }

    private void createTexture(int width, int height, Color color) {
        Pixmap pixmap = new Pixmap(width, height, Pixmap.Format.RGBA8888);
        pixmap.setColor(color);
        pixmap.fillRectangle(0, 0, width, height);
        texture = new Texture(pixmap);
        pixmap.dispose();
    }

    public void changeColor(Color color){
        this.color = color;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        texture.dispose();
        createTexture((int)getWidth(), (int)getHeight(), color);
        batch.draw(texture, getX(), getY(), getWidth(), getHeight());
    }
}
