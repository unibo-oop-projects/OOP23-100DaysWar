package it.unibo.the100dayswar.model.savedata.impl;

import it.unibo.the100dayswar.model.savedata.api.GameData;
import it.unibo.the100dayswar.model.savedata.api.PlayerData;


public class GameDataImpl implements GameData {
    private final PlayerData player1;
    private PlayerData player2;
    
    public GameDataImpl(PlayerData player1, PlayerData player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    public PlayerData getPlayer1() {
        return player1;
    }

    public PlayerData getPlayer2() {
        return player2;
    }

    public void setPlayer2(PlayerData player2) {
        this.player2 = player2;
    }
    
}
