package game.datatypes.ship;

import game.datatypes.Coordinate;
import game.interfaces.Shield;
import game.interfaces.Ship;
import game.interfaces.Weapon;

public abstract class ShipParent implements Ship {
    private Coordinate coordinates;
    private String color;
    private String shipType;
    private Shield shield;
    private long hp;
    private Double angle;
    private Weapon weapon;
    private double speed;
    private double maxSpeed;
    private double maneuverability;
    
    @Override
    public void increaseManeuverablility(double value){
        this.maneuverability -= value;
        if(this.maneuverability < 1.0d) this.maneuverability = 1.0d;
    }
    
    @Override
    public void setHp(long hp){
        this.hp = hp;
    }
    
    @Override
    public void increaseSpeed(double value){
        this.speed += value;
    }
    
    @Override
    public long decreaseHp(long value){
        long shieldProtection = getShield().getProtection();
            
        if(shieldProtection > 0L){
            getShield().decreaseProtection(value);
        }
        else
        {
            hp -= value;
        }
        return hp;
    }
    
    @Override
    public void increaseShieldPower(){
        getShield().increaseShieldPower();
    }
    
    @Override
    public abstract void initWeapon();
    
    @Override
    public double getX() {
        return coordinates.getX();
    }
    
    @Override
    public void setX(double x) {
        this.coordinates.setX(x);
    }
    
    @Override
    public double getY() {
        return coordinates.getY();
    }
    
    public abstract void resetHp();
    
    @Override
    public void setY(double y) {
        this.coordinates.setY(y);
    }
    
    @Override
    public String getColor() {
        return color;
    }
    
    @Override
    public void setColor(String color) {
        this.color = color;
    }
    
    @Override
    public String getShipType() {
        return shipType;
    }
    
    @Override
    public void setShipType(String shipType) {
        this.shipType = shipType;
    }
    
    @Override
    public Shield getShield() {
        return shield;
    }
    
    @Override
    public void setShield(Shield shield) {
        this.shield = shield;
    }
    
    @Override
    public long getHp() {
        return hp;
    }
    
    @Override
    public double getAngle() {
        return angle;
    }
    
    @Override
    public void setAngle(double angle) {
        this.angle = angle;
    }
    
    @Override
    public Weapon getWeapon() {
        return weapon;
    }
    
    @Override
    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }
    
    @Override
    public double getSpeed() {
        return speed;
    }
    
    @Override
    public void setSpeed(double speed) {
        this.speed = speed;
    }
    
    @Override
    public double getMaxSpeed() {
        return maxSpeed;
    }
    
    @Override
    public void setMaxSpeed(double maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public Coordinate getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(double x, double y) {
        if(this.coordinates == null){
            this.coordinates = new Coordinate();
        }
        
        this.coordinates.setX(x);
        this.coordinates.setY(y);
    }
    
    public void setCoordinates(Coordinate coordinates) {
        this.coordinates = coordinates;
    }

    public double getManeuverability() {
        return maneuverability;
    }

    public void setManeuverability(double maneuverability) {
        this.maneuverability = maneuverability;
    }
}
