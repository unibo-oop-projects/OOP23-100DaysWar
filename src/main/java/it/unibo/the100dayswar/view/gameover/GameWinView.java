package it.unibo.the100dayswar.view.gameover;

/**
 * The view that shows when the player wins the game.
 */
public class GameWinView extends AbstractGameOverView {
    /** 
     * The constructor of the game win view.
     */
    public GameWinView() {
        setupUI("Congrats, you won!", "/gameover/win.png");
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
