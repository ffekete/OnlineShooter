package game.datatype.shield;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import game.config.constant.ShieldConfig;
import game.datatype.AIDao;
import game.datatype.PlayerData;

public class ShieldParentTest {

    public AIDao aiDao;

    @BeforeMethod
    public void init() {
        aiDao = new AIDao();
        aiDao.setIsAi(false);
        aiDao.setIsAsteroid(false);
    }

    @Test
    public void testShouldDecreaseShieldProtection_AS() {
        PlayerData player = new PlayerData(1l, "P02", "Interceptor", aiDao);
        AtomShield as = new AtomShield();

        as.applyEffect(player);

        player.getShield().decreaseProtection(10L);

        Assert.assertEquals(player.getShield().getProtection(), ShieldConfig.ATOM_SHIELD_PROTECTION - 10L);
    }

    @Test
    public void testShouldDecreaseShieldProtection_PS() {
        PlayerData player = new PlayerData(1l, "P02", "Interceptor", aiDao);

        PlasmaShield ps = new PlasmaShield();

        ps.applyEffect(player);

        player.getShield().decreaseProtection(10L);

        Assert.assertEquals(player.getShield().getProtection(), ShieldConfig.PLASMA_SHIELD_PROTECTION - 10L);
    }

    @Test
    public void testShouldDecreaseShieldProtection_NS() {
        PlayerData player = new PlayerData(1l, "P02", "Interceptor", aiDao);

        NormalShield ns = new NormalShield();

        ns.applyEffect(player);

        player.getShield().decreaseProtection(10L);

        Assert.assertEquals(player.getShield().getProtection(), ShieldConfig.NORMAL_SHIELD_PROTECTION - 10L);
    }

    @Test
    public void testShouldDecreaseShieldProtection_AS_0() {
        PlayerData player = new PlayerData(1l, "P02", "Interceptor", aiDao);
        AtomShield as = new AtomShield();

        as.applyEffect(player);

        player.getShield().decreaseProtection(ShieldConfig.ATOM_SHIELD_PROTECTION + 1L);

        Assert.assertEquals(player.getShield().getProtection(), 0L);
    }

    @Test
    public void testShouldDecreaseShieldProtection_PS_0() {
        PlayerData player = new PlayerData(1l, "P02", "Interceptor", aiDao);

        PlasmaShield ps = new PlasmaShield();

        ps.applyEffect(player);

        player.getShield().decreaseProtection(ShieldConfig.PLASMA_SHIELD_PROTECTION + 1L);

        Assert.assertEquals(player.getShield().getProtection(), 0L);
    }

    @Test
    public void testShouldDecreaseShieldProtection_NS_0() {
        PlayerData player = new PlayerData(1l, "P02", "Interceptor", aiDao);

        NormalShield ns = new NormalShield();

        ns.applyEffect(player);

        player.getShield().decreaseProtection(ShieldConfig.NORMAL_SHIELD_PROTECTION + 1L);

        Assert.assertEquals(player.getShield().getProtection(), 0L);
    }

    public void testShouldIncreaseShieldProtection_AS() {
        PlayerData player = new PlayerData(1l, "P02", "Interceptor", aiDao);
        AtomShield as = new AtomShield();

        as.applyEffect(player);

        player.getShield().setProtection(0L);

        player.getShield().increaseShieldPower();

        Assert.assertEquals(player.getShield().getProtection(), 1L);
    }

    @Test
    public void testShouldIncreaseShieldProtection_PS() {
        PlayerData player = new PlayerData(1l, "P02", "Interceptor", aiDao);

        PlasmaShield ps = new PlasmaShield();

        ps.applyEffect(player);
        player.getShield().setProtection(0L);
        player.getShield().increaseShieldPower();

        Assert.assertEquals(player.getShield().getProtection(), 1L);
    }

    @Test
    public void testShouldIncreaseShieldProtection_NS() {
        PlayerData player = new PlayerData(1l, "P02", "Interceptor", aiDao);

        NormalShield ns = new NormalShield();

        ns.applyEffect(player);
        player.getShield().setProtection(0L);

        player.getShield().increaseShieldPower();

        Assert.assertEquals(player.getShield().getProtection(), 1L);
    }

    public void testShouldIncreaseShieldProtection_AS_MAX() {
        PlayerData player = new PlayerData(1l, "P02", "Interceptor", aiDao);
        AtomShield as = new AtomShield();

        as.applyEffect(player);

        player.getShield().setProtection(ShieldConfig.ATOM_SHIELD_PROTECTION);

        player.getShield().increaseShieldPower();

        Assert.assertEquals(player.getShield().getProtection(), ShieldConfig.ATOM_SHIELD_PROTECTION);
    }

    @Test
    public void testShouldIncreaseShieldProtection_PS_MAX() {
        PlayerData player = new PlayerData(1l, "P02", "Interceptor", aiDao);

        PlasmaShield ps = new PlasmaShield();

        ps.applyEffect(player);
        player.getShield().setProtection(ShieldConfig.PLASMA_SHIELD_PROTECTION);
        player.getShield().increaseShieldPower();

        Assert.assertEquals(player.getShield().getProtection(), ShieldConfig.PLASMA_SHIELD_PROTECTION);
    }

    @Test
    public void testShouldIncreaseShieldProtection_NS_MAX() {
        PlayerData player = new PlayerData(1l, "P02", "Interceptor", aiDao);

        NormalShield ns = new NormalShield();

        ns.applyEffect(player);
        player.getShield().setProtection(ShieldConfig.NORMAL_SHIELD_PROTECTION);

        player.getShield().increaseShieldPower();

        Assert.assertEquals(player.getShield().getProtection(), ShieldConfig.NORMAL_SHIELD_PROTECTION);
    }
}
