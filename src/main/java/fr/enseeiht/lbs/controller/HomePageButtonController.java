package main.java.fr.enseeiht.lbs.controller;

import javax.swing.*;

import static main.java.fr.enseeiht.lbs.LiveBattleSimulator.mainFrame;

/**
 * Contrôleur qui reset la bataille et ramène à la page d'accueil.
 */
public class HomePageButtonController extends JButton {

    public HomePageButtonController() {
        super("Accueil");
        this.addActionListener(actionEvent -> {
            mainFrame().showHomePage();
        });
    }
}
