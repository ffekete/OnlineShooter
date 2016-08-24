package factory;

import org.testng.Assert;
import org.testng.annotations.Test;

import game.config.ShieldId;
import game.datatype.shield.*;
import game.interfaces.Shield;

public class ShieldFactoryTest {
    
    @Test
    public void testShouldCreateNormalShield() {
        Shield shield = ShieldFactory.createShield(ShieldId.NORMAL_SHIELD);
        Assert.assertTrue(shield instanceof NormalShield);
    }
    @Test
    public void testShouldCreatePlasmaShield() {
        Shield shield = ShieldFactory.createShield(ShieldId.PLASMA_SHIELD);
        Assert.assertTrue(shield instanceof PlasmaShield);
    }
    @Test
    public void testShouldCreateAtomShield() {
        Shield shield = ShieldFactory.createShield(ShieldId.ATOM_SHIELD);
        Assert.assertTrue(shield instanceof AtomShield);
    }
}
