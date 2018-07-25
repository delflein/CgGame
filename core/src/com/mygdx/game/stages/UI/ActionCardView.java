package com.mygdx.game.stages.UI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;
import com.mygdx.game.controller.GameController;
import com.mygdx.game.stages.ActionCards.ActionCard;
import com.mygdx.game.stages.ActionCards.ChanceCard;
import com.mygdx.game.stages.ActionCards.CommunityChestCard;
import com.mygdx.game.utils.GameAssets;
import com.mygdx.game.utils.ModelFactory;

import java.util.List;

public class ActionCardView extends Dialog implements GameUiElement {

    static List<CommunityChestCard> chestCardList = CommunityChestCard.getCards();
    static List<ChanceCard> chanceCardList = ChanceCard.getCards();
    private static boolean visible = false;
    private boolean chance;
    private TextButton buyBtn;
    private ActionCard currentCard;
    private boolean showButtons;

    public ActionCardView(boolean showButtons, boolean chance) {
        super("", ModelFactory.loadSkin(GameAssets.DEFAULT_UI_SKIN.filepath));
        this.showButtons = showButtons;
        this.chance = chance;
    }

    @Override
    public ActionCardView create() {
        Pixmap backgroundColor = new Pixmap((int) (Gdx.graphics.getWidth()*0.2), (int) (Gdx.graphics.getHeight()*0.2), Pixmap.Format.RGB888);
        backgroundColor.setColor(Color.WHITE);
        backgroundColor.fill();
        this.setBackground(new Image(new Texture(backgroundColor)).getDrawable());
        this.setTouchable(Touchable.enabled);
        this.addListener(new DragListener() {
            public void drag(InputEvent event, float x, float y, int pointer) {
                moveBy(x - getWidth() / 2, y - getHeight() / 2);
            }
        });

        this.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                if (x < getOriginX() || x > (getOriginX() + getWidth()) || y < getOriginY() || y > (getOriginY() + getHeight())) {
                    remove();
                }
            }
        });

        if (showButtons) {
            buyBtn = new TextButton("Accept!", getSkin());
            buyBtn.setColor(Color.GRAY);
            buyBtn.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    super.clicked(event, x, y);
                    currentCard.effect(GameController.getCurrentPlayerComponent());
                    remove();
                }
            });
            this.getButtonTable().add(buyBtn);
        }
        displayNextCard(chance);
        this.getContentTable().add(currentCard);
        this.pack();
        this.setPosition(Gdx.graphics.getWidth() / 2 - this.getWidth()/2, Gdx.graphics.getHeight() / 2 - this.getHeight() / 2);
        this.setVisible(true);

        return this;
    }

    public void displayNextCard(boolean chance) {
        if (chance) {
            currentCard = chanceCardList.remove(chanceCardList.size() - 1);
            if (chanceCardList.isEmpty()) {
                chanceCardList = ChanceCard.getCards();
            }
        } else {
            currentCard = chestCardList.remove(chestCardList.size() - 1);
            if (chestCardList.isEmpty()) {
                chestCardList = CommunityChestCard.getCards();
            }
        }


    }
}
