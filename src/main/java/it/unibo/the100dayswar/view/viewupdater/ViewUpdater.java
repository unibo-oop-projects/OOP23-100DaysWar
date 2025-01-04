package it.unibo.the100dayswar.view.viewupdater;

import it.unibo.the100dayswar.view.gameview.GameView;
import it.unibo.the100dayswar.view.map.MapView;
import it.unibo.the100dayswar.view.statistics.StatisticsView;

/**
 * A class that periodically reloads the map background image on a separate thread.
 */
public class ViewUpdater implements Runnable {
    private final GameView mainframe;
    private final MapView mapView;
    private final StatisticsView statisticsView;

    public ViewUpdater(final GameView mainframe) {
        this.mainframe = mainframe;
        this.mapView = mainframe.getMapView();
        this.statisticsView = mainframe.getStatisticView(); 
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'run'");
    }
}
