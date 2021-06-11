package main.java.fr.enseeiht.lbs.model.game.object.unit.soldier.standard.unit;

import main.java.fr.enseeiht.lbs.model.battle.simulator.Battle;
import main.java.fr.enseeiht.lbs.model.game.object.unit.Unit;
import main.java.fr.enseeiht.lbs.model.game.object.unit.action.GroundMovementAction;
import main.java.fr.enseeiht.lbs.model.game.object.unit.ai.ChargeAndHitAI;
import main.java.fr.enseeiht.lbs.model.game.object.Stats;
import main.java.fr.enseeiht.lbs.model.game.object.unit.action.SamuraiAttack;
import main.java.fr.enseeiht.lbs.utils.Vector2;

/**
 * L'unité Samurai
 */
public class Samurai extends Unit {

    public Samurai(String name, Stats stats, Vector2 position) {
        super(name, stats, position);
        ai = new ChargeAndHitAI(new SamuraiAttack(this), new GroundMovementAction(this));
    }

    @Override
    public void start(Battle context) {

    }

    @Override
    public void end(Battle context) {

    }
}
