package main.java.fr.enseeiht.lbs.gameObject.unit.buff;

import main.java.fr.enseeiht.lbs.gameObject.unit.visitors.IBuffVisitor;

public class FireDebuff implements Buff {

    private static final double TIC_DAMAGE = 20;

    @Override
    public void accept(IBuffVisitor visitor) {
        visitor.visit(this);
    }

}
