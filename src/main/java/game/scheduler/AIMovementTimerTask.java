package game.scheduler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.Timer;

import game.config.constant.AIConfig;
import game.datatype.PlayerData;

public class AIMovementTimerTask implements ActionListener {
    private PlayerData player;

    private Timer timer;

    public AIMovementTimerTask(PlayerData player) {
        this.player = player;
        this.createNewTimer(this.getRandomDelay());

    }

    private int getRandomDelay() {
        return AIConfig.MOVEMENT_CHANGE_DELAY_BASE + new Random().nextInt(AIConfig.MOVEMENT_CHANGE_DELAY_MAX);
    }

    private void createNewTimer(int delay) {
        timer = new Timer(delay, this);
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        player.setNewMousePointForAI();
        timer.stop();
        this.createNewTimer(this.getRandomDelay());
    }
}