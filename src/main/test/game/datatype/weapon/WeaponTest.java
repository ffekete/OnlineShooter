package game.datatype.weapon;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import game.datatype.weapon.DoubleGatlingGun;

public class WeaponTest {

    @DataProvider(name="canShootInputData")
    public Object[][] canShootInputData(){
        return new Object[][]{
            {0L, true},
            {1L, false}
        };
    }
    
    @Test(dataProvider="canShootInputData")
    public void testCanShootAbility(long cooldown, boolean expectedResult){
        DoubleGatlingGun doubleGatlingGun = new DoubleGatlingGun();
        
        doubleGatlingGun.setCooldown(cooldown);
        
        boolean result = doubleGatlingGun.isReadyToShoot();
        
        Assert.assertEquals(result, expectedResult);
    }
}
