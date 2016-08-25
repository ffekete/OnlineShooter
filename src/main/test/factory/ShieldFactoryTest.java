package factory;

import org.testng.Assert;
import org.testng.annotations.Test;

import game.config.constant.SpawnableItemType;
import game.datatype.shield.AtomShield;
import game.datatype.shield.NormalShield;
import game.datatype.shield.PlasmaShield;
import game.interfaces.Shield;

public class ShieldFactoryTest {

    @Test
    public void testShouldCreateNormalShield() {
        Shield shield = ShieldFactory.createShield(SpawnableItemType.NORMAL_SHIELD);
        Assert.assertTrue(shield instanceof NormalShield);
    }

    @Test
    public void testShouldCreatePlasmaShield() {
        Shield shield = ShieldFactory.createShield(SpawnableItemType.PLASMA_SHIELD);
        Assert.assertTrue(shield instanceof PlasmaShield);
    }

    @Test
    public void testShouldCreateAtomShield() {
        Shield shield = ShieldFactory.createShield(SpawnableItemType.ATOM_SHIELD);
        Assert.assertTrue(shield instanceof AtomShield);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void testShouldThrowRuntimeException() {
        ShieldFactory.createShield(SpawnableItemType.DOUBLE_GATLING_GUN);
    }
}
