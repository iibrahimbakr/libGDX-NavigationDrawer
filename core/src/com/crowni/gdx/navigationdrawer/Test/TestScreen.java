package com.crowni.gdx.navigationdrawer.Test;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.crowni.gdx.navigationdrawer.utils.*;
import com.crowni.gdx.navigationdrawer.NavigationDrawer;

/**
 * Created by Crowni on 9/14/2017.
 **/
public class TestScreen extends BaseScreen {
    private static final String TAG = TestScreen.class.getSimpleName();

    private static final float NAV_WIDTH = 200F;
    private static final float NAV_HEIGHT = 1920F;

    @Override
    public void show() {
        super.show();

        // load atlas file
        TextureAtlas atlas = new TextureAtlas("data/menu_ui.atlas");

        // initialize all menu items from atlas region.
        final Image logo_crowni = new Image(atlas.findRegion("logo_crowni"));
        final Image icon_rate = new Image(atlas.findRegion("icon_rate"));
        final Image icon_share = new Image(atlas.findRegion("icon_share"));
        final Image icon_music = new Image(atlas.findRegion("icon_music"));
        final Image icon_off_music = new Image(atlas.findRegion("icon_off_music"));
        final Image image_background = new Image(com.crowni.gdx.navigationdrawer.utils.Utils.getTintedDrawable(atlas.findRegion("image_background"), Color.BLACK));
        final Image button_menu = new Image(atlas.findRegion("button_menu"));

        // initialize NavigationDrawer
        final NavigationDrawer drawer = new NavigationDrawer(NAV_WIDTH, NAV_HEIGHT);

        // add items into drawer panel.
        drawer.add(logo_crowni).size(63, 85).pad(0, 52, 5, 52).expandX().row();
        drawer.add().height(950F).row(); // empty
        drawer.add(icon_rate).pad(35, 52, 35, 52).expandX().row();
        drawer.add(icon_share).pad(35, 52, 35, 52).expandX().row();

        icon_off_music.setVisible(false);
        drawer.stack(icon_music, icon_off_music).pad(52, 52, 300, 52).expandX().row();

        // setup attributes for menu navigation drawer.
        drawer.setBackground(image_background.getDrawable());
        drawer.bottom().left();
        drawer.setWidthStartDrag(40f);
        drawer.setWidthBackDrag(0F);
        drawer.setTouchable(Touchable.enabled);

        /* z-index = 1 */
        // add image_background as a separating actor into stage to make smooth shadow with dragging value.
        image_background.setFillParent(true);
        stage.addActor(image_background);
        drawer.setFadeBackground(image_background, 0.5f);

        /* z-index = 2 */
        stage.addActor(drawer);

        /* z-index = 3 */
        // add button_menu as a separating actor into stage to rotates with dragging value.
        button_menu.setOrigin(Align.center);
        stage.addActor(button_menu);
        drawer.setRotateMenuButton(button_menu, 90f);

        /** Optional **/
        Image image_shadow = new Image(atlas.findRegion("image_shadow"));
        image_shadow.setHeight(NAV_HEIGHT);
        image_shadow.setX(NAV_WIDTH);
        drawer.setAreaWidth(NAV_WIDTH + image_shadow.getWidth());
        drawer.addActor(image_shadow);

        // show the panel
        drawer.showManually(true);

        /************ add item listener ***********/
        icon_rate.setName("RATE");
        icon_share.setName("SHARE");
        icon_music.setName("MUSIC_ON");
        icon_off_music.setName("MUSIC_OFF");
        button_menu.setName("BUTTON_MENU");
        image_background.setName("IMAGE_BACKGROUND");

        ClickListener listener = new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                boolean closed = drawer.isCompletelyClosed();
                Actor actor = event.getTarget();

                if (actor.getName().equals("RATE")) {
                    Gdx.app.debug(TAG, "Rate button clicked.");

                } else if (actor.getName().equals("SHARE")) {
                    Gdx.app.debug(TAG, "Share button clicked.");

                } else if (actor.getName().equals("BUTTON_MENU") || actor.getName().equals("IMAGE_BACKGROUND")) {
                    Gdx.app.debug(TAG, "Menu button clicked.");

                    image_background.setTouchable(closed ? Touchable.enabled : Touchable.disabled);
                    drawer.showManually(closed);

                } else if (actor.getName().contains("MUSIC")) {
                    Gdx.app.debug(TAG, "Music button clicked.");

                    icon_music.setVisible(!icon_music.isVisible());
                    icon_off_music.setVisible(!icon_off_music.isVisible());
                }
            }
        };

        com.crowni.gdx.navigationdrawer.utils.Utils.addListeners(listener, icon_rate, icon_share, icon_music, icon_off_music, button_menu, image_background);
    }

}
