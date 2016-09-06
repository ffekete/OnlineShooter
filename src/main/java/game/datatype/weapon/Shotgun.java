package game.datatype.weapon;

import game.config.constant.AmmoType;
import game.config.constant.ItemType;
import game.config.constant.WeaponConfig;
import game.service.Spawner;

public class Shotgun extends WeaponParent {

    public Shotgun() {
        Spawner.spawn(this);
        super.setName(ItemType.SHOTGUN.getVisibleName());
        super.setType(ItemType.SHOTGUN);
        super.setAmmoType(AmmoType.CANISTER);
        super.setShotCount(WeaponConfig.SHOTGUN.getInitShotCount());
        super.setShotAngle(WeaponConfig.SHOTGUN.getInitShotAngle());
        super.setRateOfFire(WeaponConfig.SHOTGUN.getInitRateOfFire());
        super.setDamage(AmmoType.CANISTER.getDamage(0));
    }

    @Override
    public void increaseRateOfFire(long bonus) {
        this.setRateOfFire(
                WeaponConfig.SHOTGUN.getInitRateOfFire() + WeaponConfig.SHOTGUN.getRateOfFireBonus() * bonus);
    }

    @Override
    public void increaseDamage(long bonus) {
        super.setDamage(AmmoType.CANISTER.getDamage(bonus));
    }

    @Override
    public long getInitAmmoCount() {
        return WeaponConfig.SHOTGUN.getInitAmmoCount();
    }

    @Override
    public void startCooldownEffect() {
        this.setCooldown(WeaponConfig.SHOTGUN.getRateOfFireTimeesCooldown() / this.getRateOfFire());
    }
}
