package main.java.fr.enseeiht.lbs.model.game_object;

public abstract class Entity extends GameObject {
    protected double health;
    protected Stats stats;
    protected Vector2 position;

    public Entity(double health, Vector2 position) {
        this.health = health;
        this.position = position;
    }

    public Stats getStats() {
        return stats;
    }

    public double getHealth() {
        return health;
    }

    public Vector2 getPosition() {
        return position;
    }

    public boolean isDead() {
        return getHealth() <= 0;
    }
}