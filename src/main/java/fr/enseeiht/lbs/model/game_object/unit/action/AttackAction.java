package main.java.fr.enseeiht.lbs.model.game_object.unit.action;

import main.java.fr.enseeiht.lbs.model.game_object.Statistic;
import main.java.fr.enseeiht.lbs.model.game_object.unit.Unit;
import main.java.fr.enseeiht.lbs.model.game_object.unit.soldier.Archer;

public class AttackAction implements IAttackAction {

    Unit attaquant;
    Unit target;

    public AttackAction(Unit attaquant) {
        this.attaquant = attaquant;
        this.target = null;
    }

    @Override
    public void execute(long deltaTime) {
        if (target == null||attaquant==null) {
            return;
        }

        if (attaquant.attackSuccess()) {
            target.receiveDamage(attaquant.getStats().getStatisticValue(Statistic.DAMAGE));
        }
    }

    @Override
    public void setTarget(Unit unit) {
        target = unit;
    }
}
