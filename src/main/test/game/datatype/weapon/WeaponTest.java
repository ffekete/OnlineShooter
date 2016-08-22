package game.datatype.weapon;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import game.datatype.weapon.DoubleGatlingGun;

public class WeaponTest {

    @DataProvider(name="canShootInputData")
    public Object[][] canShootInputData(){
        return new Object[][]{
            {1L, 0L, true},
            {100L, 0L, true},
            {0L, 0L, false},
            {0L, 1L, false},
            {10L, 1L, false},
        };
    }
    
    @Test(dataProvider="canShootInputData")
    public void testCanShootAbility(long ammo, long rofCooldown, boolean expectedResult){
        DoubleGatlingGun doubleGatlingGun = new DoubleGatlingGun();
        
        doubleGatlingGun.setRateOfFireCooldown(rofCooldown);
        doubleGatlingGun.setAmmo(ammo);
        
        boolean result = doubleGatlingGun.canShoot();
        
        Assert.assertEquals(result, expectedResult);
    }
}
