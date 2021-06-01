package main.java.fr.enseeiht.lbs.controller.content;

import main.java.fr.enseeiht.lbs.model.battleSimulator.Battle;

import javax.swing.*;

import java.awt.*;

import static main.java.fr.enseeiht.lbs.LiveBattleSimulator.mainFrame;

public class BattleArmiesChoiceController extends JPanel {

    JTextField battleName;
    JSpinner nbArmiesSpinner;

    public BattleArmiesChoiceController() {
        JLabel title = new JLabel("Préparez la bataille");
        title.setFont(new Font("Sans Serif", Font.PLAIN, 50));

        battleName = new JTextField("Nom de bataille");
        battleName.setFont(new Font("Sans Serif", Font.PLAIN, 30));
        battleName.setHorizontalAlignment(JTextField.CENTER);

        JLabel questionNbArmies = new JLabel("Combien d'armées vont d'affronter ?");
        questionNbArmies.setFont(new Font("Sans Serif", Font.PLAIN, 30));

        nbArmiesSpinner = new JSpinner(new SpinnerNumberModel(2, 2, 6, 1));
        nbArmiesSpinner.setFont(new Font("Sans Serif", Font.PLAIN, 30));
        nbArmiesSpinner.setEditor(new JSpinner.DefaultEditor(nbArmiesSpinner));

        JButton cancelButton = new JButton("Annuler");
        cancelButton.setFont(new Font("Sans Serif", Font.PLAIN, 30));

        cancelButton.addActionListener(actionEvent -> {
            Battle.reset();
            mainFrame().showHomePage();
            this.setVisible(false);
        });

        JButton okButton = new JButton("OK");
        okButton.setFont(new Font("Sans Serif", Font.PLAIN, 30));

        okButton.addActionListener(actionEvent -> {
            saveValues();
            mainFrame().showBattleSimulation();
            this.setVisible(false);

            new Thread(() -> {
                Battle.getInstance().run();
            }).start();
        });

        // set the layout
        setLayout(new GridBagLayout());
        GridBagConstraints layoutConstraint = new GridBagConstraints();

        int oldIpadx = layoutConstraint.ipadx;
        int oldIpadY = layoutConstraint.ipady;

        layoutConstraint.gridy = 1;
        layoutConstraint.anchor = GridBagConstraints.PAGE_START;
        layoutConstraint.insets = new Insets(10,0,0,0);
        add(title, layoutConstraint);

        layoutConstraint.gridy = 2;
        layoutConstraint.ipady = 40;   // element height size
        layoutConstraint.ipadx = 200;    // element width size
        layoutConstraint.insets = new Insets(100,0,0,0);
        add(battleName, layoutConstraint);

        layoutConstraint.gridy = 3;
        layoutConstraint.ipady = oldIpadY;
        layoutConstraint.ipadx = oldIpadx;
        layoutConstraint.insets = new Insets(50,0,0,0);
        add(questionNbArmies, layoutConstraint);

        layoutConstraint.gridy = 4;
        layoutConstraint.ipady = 40;
        layoutConstraint.ipadx = 32;
        layoutConstraint.insets = new Insets(50,0,0,0);
        add(nbArmiesSpinner, layoutConstraint);

        layoutConstraint.gridy = 6;
        layoutConstraint.ipady = oldIpadY;
        layoutConstraint.ipadx = oldIpadx;
        add(okButton, layoutConstraint);

        layoutConstraint.gridy = 7;
        add(cancelButton, layoutConstraint);
    }

    private void saveValues() {
        if (!battleName.equals("Nom de bataille")) {
            Battle.getInstance().setName(battleName.getText());
        } else {
            Battle.getInstance().setName("Bataille");
        }

        Battle.getInstance().setNbArmies((Integer) nbArmiesSpinner.getValue());
    }
}
