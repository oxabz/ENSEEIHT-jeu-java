package main.java.fr.enseeiht.lbs.model.game_object.unit.buff;

import main.java.fr.enseeiht.lbs.model.game_object.unit.visitor.BuffVisitor;

public class FireDebuff implements Buff {

    private double ticDamage;

    public FireDebuff(double ticDamage) {
        this.ticDamage = ticDamage;
    }

    @Override
    public void accept(BuffVisitor visitor) {
        visitor.visit(this);
    }

    public double getTicDamage() {
        return ticDamage;
    }
}
