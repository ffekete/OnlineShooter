package factory;

import javax.validation.constraints.AssertTrue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;
import game.config.*;
import game.datatypes.shield.*;
import game.entrypoint.Application;
import game.interfaces.Shield;

@ContextConfiguration(classes = Application.class)
public class ShieldFactoryTest extends AbstractTestNGSpringContextTests {
    
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
    //@Test(expectedExceptions=RuntimeException.class)
    //public void testShouldThowException() {
    //    Shield shield = ShieldFactory.createShield();
    //}
}
