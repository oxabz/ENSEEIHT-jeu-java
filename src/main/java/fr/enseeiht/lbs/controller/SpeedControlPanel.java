package main.java.fr.enseeiht.lbs.controller;

import main.java.fr.enseeiht.lbs.battleSimulator.Battle;

import javax.swing.*;

public class SpeedControlPanel extends JPanel{
    Battle model;

    public SpeedControlPanel(Battle model) {
        this.model = model;
        var playButton = new JButton("⏸");
        this.add(playButton);
        playButton.addActionListener(actionEvent -> {
            if (model.getDeltaTimeMultiplier()==0.0f){
                model.setDeltaTimeMultiplier(1.0f);
                playButton.setText("⏸");
            }else {
                model.setDeltaTimeMultiplier(0.0f);
                playButton.setText("▶");
            }
        });
    }


}
