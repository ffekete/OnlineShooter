package game.datatype.weapon;

import java.util.ArrayList;
import java.util.List;

import factory.AmmoBuilder;
import game.config.constant.AmmoType;
import game.config.constant.Bonuses;
import game.config.constant.ItemType;
import game.datatype.PlayerData;
import game.datatype.item.ItemParent;
import game.interfaces.Ammo;
import game.interfaces.Weapon;

public abstract class WeaponParent extends ItemParent implements Weapon {
    private ItemType type;
    private AmmoType ammoType;
    private long shotCount;
    private double shotAngle;
    private double rateOfFire;
    private double cooldown;
    private double damage;

    @Override
    public ItemType getType() {
        return this.type;
    }

    @Override
    public void setType(ItemType type) {
        this.type = type;
    }

    @Override
    public AmmoType getAmmoType() {
        return ammoType;
    }

    @Override
    public void setAmmoType(AmmoType ammoType) {
        this.ammoType = ammoType;
    }

    @Override
    public void applyEffect(PlayerData player) {
        player.addWeapon(this);
    }

    @Override
    public boolean isReadyToShoot() {
        return this.cooldown < 1L;
    }

    @Override
    public long getShotCount() {
        return shotCount;
    }

    @Override
    public void setShotCount(long shotCount) {
        this.shotCount = shotCount;
    }

    @Override
    public double getShotAngle() {
        return shotAngle;
    }

    @Override
    public void setShotAngle(double shotAngle) {
        this.shotAngle = shotAngle;
    }

    @Override
    public double getRateOfFire() {
        return rateOfFire;
    }

    @Override
    public void setRateOfFire(double rateOfFire) {
        this.rateOfFire = rateOfFire;
        if (rateOfFire < 1L)
            rateOfFire = 1L;
    }

    @Override
    public double getCooldown() {
        return this.cooldown;
    }

    @Override
    public void setCooldown(double cooldown) {
        this.cooldown = cooldown;
    }

    @Override
    public void decreaseCooldownValue(double value) {
        if (cooldown > 0L) {
            cooldown -= value;
            if (cooldown < 0L)
                cooldown = 0L;
        }
    }

    @Override
    public double getDamage() {
        return damage;
    }

    @Override
    public void setDamage(double damage) {
        this.damage = damage;
    }

    @Override
    public void applyBonuses(PlayerData playerData) {
        increaseRateOfFire(playerData.getRateOfFireBonus());
        increaseDamage(playerData.getDamageBonus());
    }

    @Override
    public List<Ammo> createAmmo(PlayerData player) {
        ArrayList<Ammo> bulletsToCreate = new ArrayList<>();

        if (this.shotCount % 2 == 1) {
            for (long i = -this.shotCount / 2; i <= this.shotCount / 2; i++) {
                bulletsToCreate.add(new AmmoBuilder().setCoordinate(player.getCoordinate())
                        .setAngle(player.getShipAngle() + i * this.shotAngle).setPlayerId(player.getId())
                        .setDamageBonus(player.getBonuses().get(Bonuses.DAMAGE)).build(this.ammoType));
            }
        } else {
            for (long i = -this.shotCount + 1; i <= this.shotCount - 1; i += 2) {
                bulletsToCreate.add(new AmmoBuilder().setCoordinate(player.getCoordinate())
                        .setAngle(player.getShipAngle() + i * this.shotAngle / 2).setPlayerId(player.getId())
                        .setDamageBonus(player.getBonuses().get(Bonuses.DAMAGE)).build(this.ammoType));
            }
        }

        return bulletsToCreate;
    }

    @Override
    public double getPower() {
        return this.getDamage() * this.getShotCount() * this.getRateOfFire();
    }
}
