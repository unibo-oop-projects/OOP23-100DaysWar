package it.unibo.the100dayswar.model.savedata.impl;

import java.util.List;

import it.unibo.the100dayswar.model.player.api.Player;
import it.unibo.the100dayswar.model.savedata.api.PlayerData;
import it.unibo.the100dayswar.model.tower.api.Tower;

public class PlayerDataImpl implements PlayerData {
    private final Player player;
    private final List <Tower> towers;

    public PlayerDataImpl(Player player, List<Tower> towers) {
        this.player = player;
        this.towers = towers;
    }

    public Player getPlayer() {
        return player;
    }

    public List<Tower> getTowers() {
        return towers;
    }
}
