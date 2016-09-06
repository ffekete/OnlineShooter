package game.datatype.weapon;

import game.config.constant.AmmoType;
import game.config.constant.ItemType;
import game.config.constant.WeaponConfig;
import game.service.Spawner;

public class MissileLauncher extends WeaponParent {

    public MissileLauncher() {
        Spawner.spawn(this);
        super.setName(ItemType.MISSILE_LAUNCHER.getVisibleName());
        super.setType(ItemType.MISSILE_LAUNCHER);
        super.setAmmoType(AmmoType.MISSILE);
        super.setShotCount(WeaponConfig.MISSILE_LAUNCHER.getInitShotCount());
        super.setShotAngle(WeaponConfig.MISSILE_LAUNCHER.getInitShotAngle());
        super.setRateOfFire(WeaponConfig.MISSILE_LAUNCHER.getInitRateOfFire());
        super.setDamage(AmmoType.MISSILE.getDamage(0));
    }

    @Override
    public long getInitAmmoCount() {
        return WeaponConfig.MISSILE_LAUNCHER.getInitAmmoCount();
    }

    @Override
    public void increaseRateOfFire(long bonus) {
        this.setRateOfFire(WeaponConfig.MISSILE_LAUNCHER.getInitRateOfFire()
                + WeaponConfig.MISSILE_LAUNCHER.getRateOfFireBonus() * bonus);
    }

    @Override
    public void increaseDamage(long bonus) {
        super.setDamage(AmmoType.MISSILE.getDamage(bonus));
    }

    @Override
    public void startCooldownEffect() {
        this.setCooldown(WeaponConfig.MISSILE_LAUNCHER.getRateOfFireTimeesCooldown() / this.getRateOfFire());
    }
}