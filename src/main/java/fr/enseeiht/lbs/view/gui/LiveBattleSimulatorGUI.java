package main.java.fr.enseeiht.lbs.view.gui;

import main.java.fr.enseeiht.lbs.controller.BattleArmiesChoiceController;
import main.java.fr.enseeiht.lbs.controller.HomePageController;
import main.java.fr.enseeiht.lbs.controller.UnitPlacementController;
import main.java.fr.enseeiht.lbs.controller.UnitPlacementController;
import main.java.fr.enseeiht.lbs.model.world.World;
import main.java.fr.enseeiht.lbs.view.content.BattleSimulationView;
import main.java.fr.enseeiht.lbs.view.content.WorldChoiceView;

import javax.swing.*;
import java.awt.*;

/**
 * Fenêtre principale de l'application
 * Elle change le contenu mais ne se ferme jamais.
 */
public class LiveBattleSimulatorGUI extends JFrame {

    public static final String UNIT_EDITOR_CARD = "UNIT_EDITOR_CARD";
    static LiveBattleSimulatorGUI instance;

    static JPanel cards;
    private final UnitEditorView unitEditorView;

    private BattleSimulationView battleSimulationView;
    private UnitPlacementController unitPlacementController;


    /**
     * Identifiants des cards
     */
    static final String HOME_PAGE_CARD = "HOME_PAGE_CARD";
    static final String ARMIES_NB_CHOICES_CARD = "ARMIES_NB_CHOICES_CARD";
    static final String BATTLE_SIMULATION_CARD = "BATTLE_SIMULATION_CARD";
    static final String WORLD_CHOICE_CARD = "WORLD_CHOICE_CARD";
    static final String UNIT_PLACEMENT_CARD = "UNIT_PLACEMENT_CARD";

    private GuiComponent acutalComponent;

    /**
     * Singleton pour n'avoir qu'une seule instance de la fenêtre.
     *
     * @return l'instance de l'objet
     */
    public static LiveBattleSimulatorGUI getInstance() {
        if (instance == null) {
            instance = new LiveBattleSimulatorGUI();
        }
        return instance;
    }

    /**
     * Crée le cardlayout de la fenêtre et affiche la page d'accueil.
     */
    private LiveBattleSimulatorGUI() {
        cards = new JPanel(new CardLayout());
        getContentPane().add(cards);

        HomePageController homePageController = HomePageController.getInstance();
        cards.add(homePageController, HOME_PAGE_CARD);
        cards.add(BattleArmiesChoiceController.getInstance(), ARMIES_NB_CHOICES_CARD);
        cards.add(WorldChoiceView.getInstance(), WORLD_CHOICE_CARD);
        cards.add(UnitPlacementController.getInstance(), UNIT_PLACEMENT_CARD);
        cards.add(BattleSimulationView.getInstance(), BATTLE_SIMULATION_CARD);

        World world = World.getInstance();
        world.generateWorld(10, 20, 5, 25, 40);
        cards.add(new WorldChoiceView(), WORLD_CHOICE_CARD);
        unitPlacementController = new UnitPlacementController();
        unitEditorView = new UnitEditorView();
        cards.add(unitEditorView, UNIT_EDITOR_CARD);

        showHomePage();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    /**
     * Affiche la page d'accueil.
     */
    public void showHomePage() {
        acutalComponent.reset();
        acutalComponent = HomePageController.getInstance();
        setChangesReady(HOME_PAGE_CARD);
    }

    /**
     * Affiche le menu de selection
     * du nom de bataille
     * du nombre d'armées
     */
    public void showBattleArmiesChoice() {
        acutalComponent.reset();
        acutalComponent = BattleArmiesChoiceController.getInstance();
        setChangesReady(ARMIES_NB_CHOICES_CARD);
    }

    /**
     * Afficher le menu de selection du terrain
     */
    public void showWorldSelection() {
        acutalComponent.reset();
        acutalComponent = WorldChoiceView.getInstance();
        setChangesReady(WORLD_CHOICE_CARD);
    }

    /**
     * Afficher le menu de selection du terrain
     */
    public void showUnitPlacement() {
        //for concurent thread modification
        BattleSimulationView.getInstance().init();

        acutalComponent.reset();
        acutalComponent = UnitPlacementController.getInstance();
        setChangesReady(UNIT_PLACEMENT_CARD);
    }

    /**
     * Affiche la vue de la bataille.
     */
    public void showBattleSimulation() {
        acutalComponent.reset();
        acutalComponent = BattleSimulationView.getInstance();
        setChangesReady(BATTLE_SIMULATION_CARD);
    }

    /**
     * Rend les panels prêts à l'affichage.
     */
    private void setChangesReady(final String CARD_STRING) {
        acutalComponent.init();
        CardLayout cl = (CardLayout) (cards.getLayout());
        cl.show(cards, CARD_STRING);

        this.pack();
        this.setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
        this.setVisible(true);
        setChangesReady();
    }

    /**
     * Affiche la vue d'edition.
     */
    public void showUnitEditor() {
        CardLayout cl = (CardLayout) (cards.getLayout());
        cl.show(cards, UNIT_EDITOR_CARD);

        setChangesReady();
    }


    /**
     * Rend les panels prêts à l'affichage.
     */
    private void setChangesReady() {
        pack();
        setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
        setVisible(true);
    }
}
