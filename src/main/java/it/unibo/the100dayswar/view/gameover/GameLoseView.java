package it.unibo.the100dayswar.view.gameover;

/**
 * The view that shows when the player loses the game.
 */
public class GameLoseView extends AbstractGameOverView {
    /** 
     * The constructor of the game lose view.
     */
    public GameLoseView() {
        setupUI("Sorry, you lost!", "/gameover/lose.png");
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
