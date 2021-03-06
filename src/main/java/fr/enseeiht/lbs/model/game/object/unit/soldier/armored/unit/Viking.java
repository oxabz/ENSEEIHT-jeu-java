package main.java.fr.enseeiht.lbs.model.game.object.unit.soldier.armored.unit;

import main.java.fr.enseeiht.lbs.LiveBattleSimulator;
import main.java.fr.enseeiht.lbs.model.battle.simulator.Battle;
import main.java.fr.enseeiht.lbs.model.game.object.unit.action.AttackAction;
import main.java.fr.enseeiht.lbs.model.game.object.unit.action.GroundHeavyMovementAction;
import main.java.fr.enseeiht.lbs.model.game.object.unit.ai.ChargeAndHitAI;
import main.java.fr.enseeiht.lbs.model.game.object.unit.visitor.dot.visitor.BasicTicVisitor;
import main.java.fr.enseeiht.lbs.model.game.object.unit.visitor.dot.visitor.VikingTicVisitor;
import main.java.fr.enseeiht.lbs.model.game.object.Stats;
import main.java.fr.enseeiht.lbs.utils.Vector2;

/**
 * L'unité Viking
 */
public class Viking extends ArmoredUnit {

    private double shield;

    public Viking(String name, Stats stats, Vector2 position) {
        super(name, stats, position);
        ai = new ChargeAndHitAI(new AttackAction(this), new GroundHeavyMovementAction(this));
        shield = 100;
    }

    /**
     * Le Viking reçoit des dégâts sur son bouclier avant d'en recevoir sur la vie
     */
    @Override
    public void receiveDamage(double damage) {
        if (hasShield()) {
            shield -= damage;
            if (LiveBattleSimulator.VERBOSE >= 2) {
                status();
            }
        } else {
            super.receiveDamage(damage);
        }
    }

    public boolean hasShield() {
        return shield > 0;
    }

    /**
     * Le Viking régait de manière unique aux tics de dégâts
     * (voir VikingTicVisitor)
     */
    @Override
    protected BasicTicVisitor getTicVisitor(long deltaTime) {
        return new VikingTicVisitor(deltaTime, this);
    }

    @Override
    public void status() {
        super.status();
        System.out.println("\tShield : " + shield);
        System.out.println("\n");
    }

    @Override
    public void start(Battle context) {

    }

    @Override
    public void end(Battle context) {

    }
}
