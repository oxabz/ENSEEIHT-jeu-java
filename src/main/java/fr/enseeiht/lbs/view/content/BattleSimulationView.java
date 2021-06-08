package main.java.fr.enseeiht.lbs.view.content;

import main.java.fr.enseeiht.lbs.controller.HomePageButtonController;
import main.java.fr.enseeiht.lbs.controller.SpeedController;
import main.java.fr.enseeiht.lbs.model.battle_simulator.Army;
import main.java.fr.enseeiht.lbs.model.battle_simulator.Battle;
import main.java.fr.enseeiht.lbs.view.gui.GuiComponent;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * Vue qui affiche la bataille et le controleur de vitesse de déroulement de bataille.
 */
public class BattleSimulationView extends JPanel implements PropertyChangeListener, GuiComponent {

    private final BattleWorldView battleWorldView;
    private final SpeedController speedController;

    private static BattleSimulationView instance;

    public static BattleSimulationView getInstance(){
        if (instance == null){
            instance = new BattleSimulationView();
        }
        return instance;
    }

    private BattleSimulationView() {
        this.battleWorldView = new BattleWorldView();
        this.speedController = new SpeedController();

        setLayout(new BorderLayout());
        add(new HomePageButtonController(), BorderLayout.NORTH);
        add(battleWorldView, BorderLayout.CENTER);
        add(speedController, BorderLayout.SOUTH);

        this.reset();
    }

    @Override
    public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
        if (propertyChangeEvent.getPropertyName().equals(Battle.PROPERTY_RESULTS)) {
            endGameTreatment(propertyChangeEvent);
        }
    }

    protected void startObserving(){
        Battle.addObserver(this, Battle.PROPERTY_RESULTS);
    }

    protected void stopObserving(){
        Battle.removeObserver(this, Battle.PROPERTY_RESULTS);
    }

    protected void endGameTreatment(PropertyChangeEvent propertyChangeEvent) {
        Army winner = (Army) propertyChangeEvent.getNewValue();
        Color color = BattleView.TEAM_COLORS.get(winner.getArmyIndex());
        String colorName = BattleView.COLORS_NAME.get(color);
        String result = Battle.getInstance().getName() +
                "\nL'armée " + colorName + " a gagné";
        JOptionPane.showMessageDialog(null, result);
    }

    @Override
    public void reset() {
        this.stopObserving();
        battleWorldView.stopObserving();
    }

    @Override
    public void init() {
        this.startObserving();
        battleWorldView.startObserving();
        this.speedController.init();
    }

}
