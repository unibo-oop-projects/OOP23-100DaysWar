package it.unibo.the100dayswar.view.viewupdater;

import it.unibo.the100dayswar.application.The100DaysWar;
import it.unibo.the100dayswar.view.gameview.GameView;
import it.unibo.the100dayswar.view.map.MapView;
import it.unibo.the100dayswar.view.statistics.StatisticsView;

/**
 * A class that periodically reloads the map background image on a separate thread.
 */
public class ViewUpdater implements Runnable {
    private static final long REFRESH_INTERVAL_MS = 50;
    private final MapView mapView;
    private final StatisticsView statisticsView;
    private boolean running;

    public ViewUpdater(final GameView mainframe) {
        this.mapView = mainframe.getMapView();
        this.statisticsView = mainframe.getStatisticView(); 
        this.running = false;
    }

    public void start() {
        if(!running) {
            running = true;
            new Thread(this, "MapImageReloader-Thread").start();
        }
    }

    public void stop() {
        if(running) {
            running = false;
        }
    }

    @Override
    public void run() {
        while (running) {
            try {
                // Reload the image

                /*
                 * TODO aggiungere l'aggiornamento delle posizioni
                 * The100DaysWar.CONTROLLER.
                 */

                // mapView.paintSoldiers();
                // mapView.paintTowers();
                
                // statisticsView.paintStatistics();
                // mapView.paintArea();
                //statisticsView.refreshStatistics();
                // Sleep until next update
                Thread.sleep(REFRESH_INTERVAL_MS);

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}
