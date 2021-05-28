package main.java.fr.enseeiht.lbs.view.content;

import main.java.fr.enseeiht.lbs.model.gameObject.Entity;
import main.java.fr.enseeiht.lbs.model.gameObject.unit.Unit;
import main.java.fr.enseeiht.lbs.model.gameObject.unit.soldier.Knight;
import main.java.fr.enseeiht.lbs.model.gameObject.unit.soldier.Peasant;

import javax.swing.*;
import java.awt.*;
import main.java.fr.enseeiht.lbs.model.gameObject.Entity;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

@SuppressWarnings("serial")
public class BattleView extends JPanel implements PropertyChangeListener {

    private static final int WORLD_TO_PIXEL = 11;

    private List<GraphicalEntity> graphicalEntities;

    private final static HashMap<Class<? extends Entity>, String> entitySprites = new HashMap<>();
    private final static List<Color> teamColors = new ArrayList<>();

    static {
        entitySprites.put(Knight.class, "Knight.png");
        entitySprites.put(Peasant.class, "Peasant.png");

        teamColors.add(Color.BLUE);
        teamColors.add(Color.RED);
        teamColors.add(Color.GREEN);
        teamColors.add(Color.YELLOW);
    }

    public BattleView() {
        this.setVisible(true);
        this.graphicalEntities = new LinkedList<>();
    }

    @Override
    public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
        if (propertyChangeEvent.getPropertyName().equals("gameObjects"))
            modifiedGameObjectTreatement(propertyChangeEvent);

        this.repaint();
        Toolkit.getDefaultToolkit().sync();
    }

    protected void modifiedGameObjectTreatement(PropertyChangeEvent propertyChangeEvent) {
        graphicalEntities.clear();
        for (Object gameObject : (List<?>) propertyChangeEvent.getNewValue()) {
            if (gameObject instanceof Entity) {
                Entity entity = (Entity) gameObject;
                String sprite = getCorrespondingSprite(entity);
                Color color = null;
                if (entity instanceof Unit) {
                    Unit unit = (Unit) entity;
                    if (unit.getTeam() != null && unit.getTeam().getColorIndex() < teamColors.size()) {
                        color = teamColors.get(unit.getTeam().getColorIndex());
                    }
                }
                if (sprite != null) {
                    this.graphicalEntities.add(new SpriteGraphicalEntity(entity.getPosition().scale(WORLD_TO_PIXEL), sprite, color));
                } else {
                    this.graphicalEntities.add(new GraphicalEntity(entity.getPosition().scale(WORLD_TO_PIXEL), color));
                }

            }
        }
    }

    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);

        for (GraphicalEntity entityGraphic : this.graphicalEntities) {
            entityGraphic.paint(graphics);
        }
    }

    public static String getCorrespondingSprite(Entity entity) {
        return entitySprites.get(entity.getClass());
    }
}
