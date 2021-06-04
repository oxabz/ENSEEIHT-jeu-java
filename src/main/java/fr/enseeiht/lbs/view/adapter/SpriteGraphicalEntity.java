package main.java.fr.enseeiht.lbs.view.adapter;

import main.java.fr.enseeiht.lbs.model.game_object.Vector2;

import java.awt.*;
import java.io.IOException;

public class SpriteGraphicalEntity extends GraphicalEntity {

    private final String spritePath;

    public SpriteGraphicalEntity(Vector2 position, String spritePath, Color color) {
        super(position, color);
        this.spritePath = spritePath;
    }

    @Override
    public void paint(Graphics graphics) {
        try {
            Image image = SpriteBuffer.getSprite(this.spritePath);
            graphics.drawImage(image, (int) position.x - (SUPER_PIXEL_SIZE/2), (int) position.y - (SUPER_PIXEL_SIZE/2), null);
            paintLabel(graphics);
        } catch (IOException e) {
            e.printStackTrace();
            super.paint(graphics);
        }
    }
}
