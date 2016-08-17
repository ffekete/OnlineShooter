package game.datatypes.weapons;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import game.datatypes.PlayerData;
import game.datatypes.Items.ItemParent;
import game.datatypes.bullet.BulletData;
import game.interfaces.Bullet;
import game.interfaces.Weapon;

public abstract class WeaponParent extends ItemParent implements Weapon {
    private long rateOfFireCooldown;
    private long rateOfFire;
    private long ammo;
    private long damage;

    @Override
    public void applyEffect(PlayerData player) {
        player.setWeapon(this);
    }

    @Override
    public void increaseDamage(long amount) {
        this.setDamage(this.getDamage() + amount);
    }

    @Override
    public void increaseRateOfFire(long amount) {
        long rof = this.getRateOfFire();

        this.setRateOfFire(rof - amount);
    }

    public boolean canShoot() {
        return this.hasAmmo() && this.rateOfFireCooldown < 1L;
    }

    @Override
    public long getDamage() {
        return this.damage;
    }

    @Override
    public void decreaseAmmo(long value) {
        if (this.ammo > 0L) {
            this.ammo -= value;
            if (this.ammo < 0L)
                this.ammo = 0L;
        }
    }

    @Override
    public boolean hasAmmo() {
        return this.ammo > 0L;
    }

    public long getAmmo() {
        return ammo;
    }

    public void setAmmo(long ammo) {
        this.ammo = ammo;
    }

    public void setDamage(long damage) {
        this.damage = damage;
    }

    public long getRateOfFire() {
        return rateOfFire;
    }

    public void setRateOfFire(long rateOfFire) {
        this.rateOfFire = rateOfFire;
        if (rateOfFire < 1L)
            rateOfFire = 1L;
    }

    public long getRateOfFireCooldown() {
        return rateOfFireCooldown;
    }

    public void setRateOfFireCooldown(long rateOfFireCooldown) {
        this.rateOfFireCooldown = rateOfFireCooldown;
    }

    public void startShootingRateCooldownEffect() {
        rateOfFireCooldown = this.getRateOfFire();
    }

    public void decreaseRateOfFireCooldownValue(long value) {
        if (rateOfFireCooldown > 0L) {
            rateOfFireCooldown -= value;
            if (rateOfFireCooldown < 0L)
                rateOfFireCooldown = 0L;
        }
    }

    public List<Bullet> createBullet(PlayerData player) {
        ArrayList<Bullet> bulletsToCreate = new ArrayList<>();

        bulletsToCreate.add(new BulletData(new Point2D.Double(player.getX(), player.getY()), player.getShipAngle(),
                player.getId(), player.getWeapon().getDamage()));

        return bulletsToCreate;
    }

}
