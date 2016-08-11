package game.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import game.config.GameConfig;
import game.config.TimerValues;
import game.datahandler.HighScoreTable;
import game.datahandler.PlayerPool;
import game.interfaces.BulletDataProcessorInterface;
import game.interfaces.ItemProcessorInterface;
import game.interfaces.PlayerDataProcessorInterface;

/** A basic scheduler for the main loop of the game. */
@Component
public class TaskScheduler {
    
    private long timer = 0;
    private long exitTime = 0;
    private long entryTime = 0;
    
    public long getTimer() {
        return timer;
    }

    @Autowired
    ItemProcessorInterface itemProcessor;
    
    @Autowired
    BulletDataProcessorInterface bulletDataProcessor;
    
    @Autowired
    PlayerPool playerPool;
    
    @Autowired
    PlayerDataProcessorInterface playerDataPrcessor;
    
    @Autowired
    HighScoreTable highScoreTable;
    
    /** Main game loop. */
    @Scheduled(fixedRate = TimerValues.GAME_MAIN_PERIOD_IN_MS)
    public void run() throws InterruptedException{
        
        timer = (timer + 1) % TimerValues.MAIN_SCHEDULER_LOOP;
        
        if(GameConfig.FREE_SYSTEM_TIME_MEASUREMENT_ENABLED && timer == 0){
            entryTime = System.currentTimeMillis();
            System.out.println("Free time during two tasks:" + (entryTime - exitTime));
        }
        
        itemProcessor.updateItemData();
        
        bulletDataProcessor.updateBulletData();
        
        /* handle player inactivity counters */
        playerPool.updatePlayerPoolData();
        
        /* Do the math */
        playerDataPrcessor.updatePlayerData();
        
        if(timer == 0)
            exitTime = System.currentTimeMillis();
            
    }
}
