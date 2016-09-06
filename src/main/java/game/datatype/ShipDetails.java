package game.datatype;

public class ShipDetails {
    private String weapon;
    private int speed;
    private Long hp;
    private int maneuverability;
    private int cargoCapacity;

    public ShipDetails(String weapon, double speed, Long hp, double maneuverability, int cargoCapacity) {
        this.weapon = weapon;
        this.speed = (int) speed;
        this.hp = hp;
        this.maneuverability = (int) maneuverability;
        this.cargoCapacity = cargoCapacity;
    }

    public String getWeapon() {
        return weapon;
    }

    public int getSpeed() {
        return speed;
    }

    public Long getHp() {
        return hp;
    }

    public int getManeuverability() {
        return maneuverability;
    }

    public int getCargoCapacity() {
        return cargoCapacity;
    }
}