package com.mygdx.game.utils;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.FloatAttribute;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Logger;

public class ModelFactory {

    private static Logger log = new Logger("ModelFactory", Application.LOG_DEBUG);

    private static AssetManager assetManager = new AssetManager();
    private static ModelBuilder modelBuilder = new ModelBuilder();

    public static void init() {
        for(GameAssets asset : GameAssets.values()) {
            assetManager.load(asset.filepath, asset.type);
            System.out.println("Loaded " + asset.filepath);
        }
        assetManager.finishLoading();
    }

    public static void dispose() {
        assetManager.dispose();
    }

    public static Skin loadSkin(String fileName) {
        if(assetManager.isLoaded(fileName)) {
            return assetManager.get(fileName);
        }else {
            log.debug("Skin " + fileName + " isn't loaded yet!");
            return null;
        }
    }

    public static Texture loadTexture(String fileName) {
        if(assetManager.isLoaded(fileName)) {
            return assetManager.get(fileName);
        }else {
            log.debug("Texture " + fileName + " isn't loaded yet!");
            return null;
        }
    }

    public static Model loadModel(String fileName) {
        if (assetManager.isLoaded(fileName)) {
            return assetManager.get(fileName);
        } else {
            log.debug("Model " + fileName + " isn't loaded yet!");
            return null;
        }
    }

    public static Model createBox(float width, float height, float depth, Color color) {
        return modelBuilder.createBox(width, height, depth,
                new Material(ColorAttribute.createDiffuse(color), ColorAttribute.createSpecular(Color.WHITE), FloatAttribute.createShininess(16f)),
                VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal);
    }

    public static Model createSphere(float width, float height, float depth, int divisionsU, int divisionsV, Color color) {
        return modelBuilder.createSphere(width, height, depth, divisionsU, divisionsV, new Material(ColorAttribute.createDiffuse(color), ColorAttribute.createSpecular(Color.WHITE), FloatAttribute.createShininess(16f)),
                VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal);
    }
}
