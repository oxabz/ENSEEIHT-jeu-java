package main.java.fr.enseeiht.lbs.model.game_object.unit.visitor.dotVisitor;

import main.java.fr.enseeiht.lbs.model.game_object.unit.buff.FireDebuff;
import main.java.fr.enseeiht.lbs.model.game_object.unit.buff.FreezeDebuff;
import main.java.fr.enseeiht.lbs.model.game_object.unit.buff.SlowDebuff;
import main.java.fr.enseeiht.lbs.model.game_object.unit.soldier.armored_unit.ArmoredUnit;
import main.java.fr.enseeiht.lbs.model.game_object.unit.soldier.armored_unit.Knight;
import main.java.fr.enseeiht.lbs.model.game_object.unit.visitor.BasicDotVisitor;

public class KnightDotVisitor extends BasicDotVisitor {

    public KnightDotVisitor(long deltaTime, Knight unit) {
        super(deltaTime, unit);
    }

    @Override
    public void visit(FireDebuff buff) {
        if (!((ArmoredUnit)unit).hasArmor()){
            super.visit(buff);
        }
    }

    @Override
    public void visit(FreezeDebuff buff) {
        if (!((ArmoredUnit)unit).hasArmor()){
            super.visit(buff);
        }
    }

    @Override
    public void visit(SlowDebuff buff) {
        if (!((ArmoredUnit)unit).hasArmor()){
            super.visit(buff);
        }
    }

}
