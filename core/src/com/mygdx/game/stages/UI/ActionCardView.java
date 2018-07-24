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

    private static List<CommunityChestCard> chestCardList = CommunityChestCard.getCards();
    private static List<ChanceCard> chanceCardList = ChanceCard.getCards();
    private static boolean visible = false;
    private boolean chance;
    private TextButton buyBtn;
    ;
    private ActionCard currentCard;
    private boolean showButtons;

    public ActionCardView(boolean showButtons, boolean chance) {
        super("", ModelFactory.loadSkin(GameAssets.DEFAULT_UI_SKIN.filepath));
        this.currentCard = null;
        this.showButtons = showButtons;
        this.chance = chance;
    }

    @Override
    public ActionCardView create() {
        Pixmap backgroundColor = new Pixmap(400, 300, Pixmap.Format.RGB888);
        backgroundColor.setColor(Color.WHITE);
        backgroundColor.fill();
        this.setBackground(new Image(new Texture(backgroundColor)).getDrawable());
        this.setFillParent(false);
        this.setPosition(Gdx.graphics.getWidth() / 2 - this.getWidth() / 2, Gdx.graphics.getHeight() / 2 - this.getHeight() / 2);
        this.setSize(400, 300);
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
        this.getContentTable().clearChildren();
        displayNextCard(chance);
        this.getContentTable().add(currentCard);
        this.pack();
        this.setVisible(true);

        return this;
    }

    public void disableBuyOption() {
        buyBtn.setTouchable(Touchable.disabled);
        buyBtn.setText("Insufficient Funds !");
    }

    public void displayNextCard(boolean chance) {
        if (chance) {
            currentCard = chanceCardList.remove(chanceCardList.size() - 1);
            if (chanceCardList.isEmpty()) {
                chanceCardList = ChanceCard.getCards();
            }
        } else {
            currentCard = chestCardList.remove(chanceCardList.size() - 1);
            if (chestCardList.isEmpty()) {
                chestCardList = CommunityChestCard.getCards();
            }
        }


    }
}
