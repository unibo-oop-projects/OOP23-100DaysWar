package it.unibo.the100dayswar.model.turn.impl;

import java.util.List;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import it.unibo.the100dayswar.model.bot.api.BotPlayer;
import it.unibo.the100dayswar.model.player.api.Player;
import it.unibo.the100dayswar.model.turn.api.GameTurnManager;
import it.unibo.the100dayswar.model.turn.api.GameDay;

/**
 * Class that implement the interface GameTurnManager.
 */
public class GameTurnManagerImpl implements GameTurnManager {
    private static final long serialVersionUID = 1L;

    private static final int PERIOD = 4000;
    private static final int DELAY = 4000;
    private static final int MAX_TURN_WITH_NO_MOVE = 4;
    private int turn;
    private final List<Player> players;
    private int currentPlayerIndex;
    private int daysNoMove;
    private final GameDay gameDay;
    private transient Timer timer;

    /**
     * Constructor of GameTurnManagerImpl.
     * 
     * @param players List of player
     */
    public GameTurnManagerImpl(final List<Player> players) {
        this.turn = 1;
        this.players = new ArrayList<>(players);
        this.currentPlayerIndex = 0;
        this.daysNoMove = 0;
        this.gameDay = new GameDayImpl();
        gameDay.attach(players.get(0));
        gameDay.attach(players.get(1));
    }
    /**
     * get the current player.
     * 
     * @return the current player
     */
    @Override
    public Player getCurrentPlayer() {
        return this.players.get(getCurrentPlayerIndex());
    }
    /** 
     * {@inheritDoc}
     */
    @Override
    public int getDay() {
        return this.gameDay.getCurrentDay();
    }
    /**
     * get the current player.
     * 
     * @return the index of the current player in the list
     */
    @Override
    public int getCurrentPlayerIndex() {
        return this.currentPlayerIndex;
    }
    /**
     * get the current turn.
     * 
     * @return the current turn
     */
    @Override
    public int getCurrentTurn() {
        return this.turn;
    }
    /**
     * switch Turn to the other player.
     */
    @Override
    public synchronized void switchTurn() {
        this.currentPlayerIndex = (this.currentPlayerIndex == 0) ? 1 : 0;
        increaseTurn();
        playerStartTurn();
        if (this.getCurrentPlayer() instanceof BotPlayer) {
            ((BotPlayer) getCurrentPlayer()).makeMove();
            switchTurn();
        }
    }
    /**
     * increase the Turn counter.
     */
    @Override
    public synchronized void increaseTurn() {
        this.turn++;
    }
    /**
     * called when a player start his turn.
     */
    @Override
    public synchronized void playerStartTurn() {
        this.daysNoMove = 0;
    }
    /**
     * unsed to automatically change turn when day passes.
     */
    @Override
    public synchronized void onDayPassed() {
        this.gameDay.increaseDay();
        this.daysNoMove++;
        if (daysNoMove >= MAX_TURN_WITH_NO_MOVE) {
            switchTurn();
        }
        if (this.gameDay.getCurrentDay() >= this.gameDay.getMaxDay()) {
            stopTimer();
        }
    }
    /**
     * start the timer for the day.
     */
    @Override
    public void startTimer() {
        final TimerTask dayTimer;
        this.timer = new Timer();
        dayTimer = new TimerTask() {
            @Override
            public void run() {
                onDayPassed();
            }
        };
        timer.schedule(dayTimer, DELAY, PERIOD);
    }
    /**
     * stop the timer for the day.
     */
    @Override
    public void stopTimer() {
        this.timer.cancel();
    }

    /**
     * writeObject method for serialization.
     * 
     * @param input the input stream
     * @throws IOException if an I/O error occurs
     * @throws ClassNotFoundException if the class of a serialized object cannot be found
     */
    private void readObject(final java.io.ObjectInputStream input) throws IOException, ClassNotFoundException {
        input.defaultReadObject();
    }
}
