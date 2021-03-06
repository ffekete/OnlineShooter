package game.datahandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import game.config.constant.AIConfig;
import game.datatype.PlayerData;
import game.entrypoint.Application;

@ContextConfiguration(classes = { Application.class })
public class AIHandlerTest extends AbstractTestNGSpringContextTests {

    @Autowired
    PlayerPool playerPool;

    @Autowired
    AIHandler aiHandler;

    @BeforeMethod
    public void init() {
        for (long i : playerPool.getAll()) {
            playerPool.removePlayer(playerPool.get(i));
        }
    }
    
    //TODO: rendberakni a tesztet
    @Test(enabled = false)
    public void testShouldGiveBackOneAi() {
        aiHandler.updateAIData();
        int aiCount = 0;
        for (long i : playerPool.getAll()) {
            PlayerData player = playerPool.get(i);
            if (player.getIsAI() && !player.getIsAsteroid()) {
                aiCount++;
            }
        }
        Assert.assertEquals(aiCount, 1);
    }
}
