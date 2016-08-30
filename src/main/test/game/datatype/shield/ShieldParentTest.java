package game.datatype.shield;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import game.config.constant.ShieldConfig;
import game.config.constant.ShipConfig;
import game.datatype.AIDao;
import game.datatype.PlayerData;

public class ShieldParentTest {

    public AIDao aiDao;

    public PlayerData player;
    public NormalShield ns;
    public PlasmaShield ps;
    public AtomShield as;

    @BeforeMethod
    public void init() {
        aiDao = new AIDao(false, false);

        player = new PlayerData(1l, "P02", ShipConfig.INTERCEPTOR, aiDao);

        ns = new NormalShield();
        ps = new PlasmaShield();
        as = new AtomShield();
    }

    @Test
    public void testShouldDecreaseShieldProtection_AS() {
        as.applyEffect(player);

        player.getShield().decreaseProtection(10.0);

        Assert.assertEquals((long)player.getShield().getProtection(), (long)ShieldConfig.ATOM_SHIELD_PROTECTION - 10L);
    }

    @Test
    public void testShouldDecreaseShieldProtection_PS() {
        ps.applyEffect(player);

        player.getShield().decreaseProtection(10.0);

        Assert.assertEquals((long)player.getShield().getProtection(), (long)ShieldConfig.PLASMA_SHIELD_PROTECTION - 10L);
    }

    @Test
    public void testShouldDecreaseShieldProtection_NS() {
        ns.applyEffect(player);

        player.getShield().decreaseProtection(10.0);

        Assert.assertEquals((long)player.getShield().getProtection(), (long)ShieldConfig.NORMAL_SHIELD_PROTECTION - 10L);
    }

    @Test
    public void testShouldDecreaseShieldProtection_AS_0() {
        as.applyEffect(player);

        player.getShield().decreaseProtection(ShieldConfig.ATOM_SHIELD_PROTECTION + 1L);

        Assert.assertEquals((long)player.getShield().getProtection(), 0L);
    }

    @Test
    public void testShouldDecreaseShieldProtection_PS_0() {
        ps.applyEffect(player);

        player.getShield().decreaseProtection(ShieldConfig.PLASMA_SHIELD_PROTECTION + 1L);

        Assert.assertEquals((long)player.getShield().getProtection(), 0L);
    }

    @Test
    public void testShouldDecreaseShieldProtection_NS_0() {
        ns.applyEffect(player);

        player.getShield().decreaseProtection(ShieldConfig.NORMAL_SHIELD_PROTECTION + 1L);

        Assert.assertEquals((long)player.getShield().getProtection(), 0L);
    }

    public void testShouldIncreaseShieldProtection_AS() {
        as.applyEffect(player);

        player.getShield().setProtection(0L);

        player.getShield().increaseShieldPower();

        Assert.assertEquals((long)player.getShield().getProtection(), 1L);
    }

    @Test
    public void testShouldIncreaseShieldProtection_PS() {
        ps.applyEffect(player);
        player.getShield().setProtection(0L);
        player.getShield().increaseShieldPower();

        Assert.assertEquals((long)player.getShield().getProtection(), 1L);
    }

    @Test
    public void testShouldIncreaseShieldProtection_NS() {
        ns.applyEffect(player);
        player.getShield().setProtection(0L);

        player.getShield().increaseShieldPower();

        Assert.assertEquals((long)player.getShield().getProtection(), 1L);
    }

    public void testShouldIncreaseShieldProtection_AS_MAX() {
        as.applyEffect(player);

        player.getShield().setProtection(ShieldConfig.ATOM_SHIELD_PROTECTION);

        player.getShield().increaseShieldPower();

        Assert.assertEquals((long)player.getShield().getProtection(), (long)ShieldConfig.ATOM_SHIELD_PROTECTION);
    }

    @Test
    public void testShouldIncreaseShieldProtection_PS_MAX() {
        ps.applyEffect(player);
        player.getShield().setProtection(ShieldConfig.PLASMA_SHIELD_PROTECTION);
        player.getShield().increaseShieldPower();

        Assert.assertEquals((long)player.getShield().getProtection(), (long)ShieldConfig.PLASMA_SHIELD_PROTECTION);
    }

    @Test
    public void testShouldIncreaseShieldProtection_NS_MAX() {
        ns.applyEffect(player);
        player.getShield().setProtection(ShieldConfig.NORMAL_SHIELD_PROTECTION);

        player.getShield().increaseShieldPower();

        Assert.assertEquals((long)player.getShield().getProtection(), (long)ShieldConfig.NORMAL_SHIELD_PROTECTION);
    }
}
