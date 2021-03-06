package main.java.fr.enseeiht.lbs.model.battle.simulator;

/**
 * Exception levée dans le cas ou on essayye de lancer la bataille quand elle n'a pas été initialisée correctement.
 */
public class InvalidBattleStateException extends Exception {
    public InvalidBattleStateException(String message) {
        super(message);
    }
}
