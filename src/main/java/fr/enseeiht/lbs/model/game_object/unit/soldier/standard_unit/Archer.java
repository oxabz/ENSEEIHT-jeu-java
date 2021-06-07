package main.java.fr.enseeiht.lbs.model.game_object.unit.soldier.standard_unit;

import main.java.fr.enseeiht.lbs.model.battle_simulator.Battle;
import main.java.fr.enseeiht.lbs.model.game_object.Stats;
import main.java.fr.enseeiht.lbs.model.game_object.unit.Unit;
import main.java.fr.enseeiht.lbs.model.game_object.unit.action.ArcherAttack;
import main.java.fr.enseeiht.lbs.model.game_object.unit.action.FlightMovementAction;
import main.java.fr.enseeiht.lbs.model.game_object.unit.ai.ChargeAndHitAI;
import main.java.fr.enseeiht.lbs.utils.Vector2;

public class Archer extends Unit {

    private int nbArrowsShot;

    public Archer(String name, Stats stats, Vector2 position) {
        super(name, stats, position);
        nbArrowsShot = 1;
        ai = new ChargeAndHitAI(new ArcherAttack(this), new FlightMovementAction(this));
    }

    public void updateNbArrowsShot(){
        nbArrowsShot = nbArrowsShot == 3 ? 1 : nbArrowsShot + 1;
    }

    public int getNbArrowsShot(){
        return nbArrowsShot;
    }

    @Override
    public void start(Battle context) {

    }

    @Override
    public void end(Battle context) {

    }
}
