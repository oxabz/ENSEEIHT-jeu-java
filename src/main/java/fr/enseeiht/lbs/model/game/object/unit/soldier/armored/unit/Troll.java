package main.java.fr.enseeiht.lbs.model.game.object.unit.soldier.armored.unit;

import main.java.fr.enseeiht.lbs.model.battle.simulator.Battle;
import main.java.fr.enseeiht.lbs.model.game.object.unit.action.GroundHeavyMovementAction;
import main.java.fr.enseeiht.lbs.model.game.object.unit.ai.ChargeAndHitAI;
import main.java.fr.enseeiht.lbs.model.game.object.unit.visitor.dot.visitor.BasicTicVisitor;
import main.java.fr.enseeiht.lbs.model.game.object.unit.visitor.dot.visitor.TrollTicVisitor;
import main.java.fr.enseeiht.lbs.model.game.object.unit.visitor.stat.modifier.visitor.BasicStatModifierBuffVisitor;
import main.java.fr.enseeiht.lbs.model.game.object.unit.visitor.stat.modifier.visitor.TrollStatModifierVisitor;
import main.java.fr.enseeiht.lbs.model.game.object.Stats;
import main.java.fr.enseeiht.lbs.model.game.object.unit.action.TrollAttack;
import main.java.fr.enseeiht.lbs.utils.Vector2;

/**
 * L'unité Troll
 */
public class Troll extends ArmoredUnit {

    public Troll(String name, Stats stats, Vector2 position) {
        super(name, stats, position);
        ai = new ChargeAndHitAI(new TrollAttack(this), new GroundHeavyMovementAction(this));
    }

    /**
     * Le Troll régait de manière unique aux débuffs qui modifient les statistiques
     * (voir TrollStatModifierVisitor)
     */
    @Override
    protected BasicStatModifierBuffVisitor getStatModifierVisitor() {
        return new TrollStatModifierVisitor(stats, this);
    }

    /**
     * Le Troll régait de manière unique aux tics de dégâts
     * (voir TrollTicVisitor)
     */
    @Override
    protected BasicTicVisitor getTicVisitor(long deltaTime) {
        return new TrollTicVisitor(deltaTime, this);
    }

    @Override
    public void start(Battle context) {

    }

    @Override
    public void end(Battle context) {

    }
}
