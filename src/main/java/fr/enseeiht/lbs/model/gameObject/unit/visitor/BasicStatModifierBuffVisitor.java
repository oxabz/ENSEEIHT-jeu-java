package main.java.fr.enseeiht.lbs.model.gameObject.unit.visitor;

import main.java.fr.enseeiht.lbs.model.gameObject.Statistic;
import main.java.fr.enseeiht.lbs.model.gameObject.Stats;
import main.java.fr.enseeiht.lbs.model.gameObject.unit.buff.FireDebuff;
import main.java.fr.enseeiht.lbs.model.gameObject.unit.buff.FreezeDebuff;
import main.java.fr.enseeiht.lbs.model.gameObject.unit.buff.SlowDebuff;

public class BasicStatModifierBuffVisitor implements BuffVisitor {
    Stats stats;

    public BasicStatModifierBuffVisitor(Stats stats){
        this.stats = new Stats(stats);
    }

    @Override
    public void visit(FireDebuff buff) {
        stats.addStat(Statistic.ARMOR, 0);
    }

    @Override
    public void visit(FreezeDebuff buff) {
        stats.addStat(Statistic.SPEED, 0);
    }

    @Override
    public void visit(SlowDebuff buff) {
        stats.addStat(Statistic.SPEED, buff.getSlow() * getStats().getStatisticValue(Statistic.SPEED));
    }

    public Stats getStats() {
        return stats;
    }
}