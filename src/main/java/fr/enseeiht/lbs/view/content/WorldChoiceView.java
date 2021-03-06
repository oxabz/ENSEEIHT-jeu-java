package main.java.fr.enseeiht.lbs.view.content;

import main.java.fr.enseeiht.lbs.controller.ChoiceMapButtonsController;
import main.java.fr.enseeiht.lbs.controller.ChoiceMapPresetController;
import main.java.fr.enseeiht.lbs.view.gui.GuiComponent;

import javax.swing.*;
import java.awt.*;

/**
 * Vue de la personalisation du terrain avant la simulation de la bataille.
 */
public class WorldChoiceView extends JPanel implements GuiComponent {

    private static WorldView worldView;

    private static WorldChoiceView instance;

    public static WorldChoiceView getInstance(){
        if (instance == null){
            instance = new WorldChoiceView();
        }
        return instance;
    }

    private WorldChoiceView() {
        this.setBorder(BorderFactory.createEmptyBorder(50, 10, 10, 10));

        //Panneaux de choix à gauche
        ChoiceMapPresetController choixMapPresetController = ChoiceMapPresetController.getInstance();
        choixMapPresetController.init();
        choixMapPresetController.setBorder(BorderFactory.createEmptyBorder(0, 20, 20, 20));

        //Boutons en bas de page
        //Le bouton reload est cliqué afin d'initialiser un nouveau World !
        ChoiceMapButtonsController navigation = new ChoiceMapButtonsController();
        choixMapPresetController.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));
        //navigation.setPreferredSize(new Dimension(1200, 50));

        //Panneau central (la carte)
        //Doit être créé après l'initialisation du world
        worldView = new WorldView();
        worldView.setPreferredSize(new Dimension(900, 600));
        worldView.setBorder(BorderFactory.createEmptyBorder(0, 40, 20, 40));

        this.setLayout(new BorderLayout());
        this.add(worldView, BorderLayout.CENTER);
        this.add(choixMapPresetController, BorderLayout.WEST);
        this.add(navigation, BorderLayout.SOUTH);

        this.reset();
        this.setVisible(true);
    }

    @Override
    public void reset(){
        worldView.stopObserving();
    }

    @Override
    public void init() {
        worldView.startObserving();
        ChoiceMapPresetController.getInstance().init();
        ChoiceMapButtonsController.init();
    }
}