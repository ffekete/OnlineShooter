package game.interfaces;

import game.config.constant.AmmoType;
import game.controller.EventSender;

public interface Ammo extends Spawnable {

    public long getPlayerId();

    public void setPlayerId(long playerId);
    
    public AmmoType getType();

	public void setType(AmmoType type);

    public double getDamage();

    public void setDamage(double damage);
    
    public void increaseDamage(double amount);

    public double getAge();

    public void setAge(double age);
    
    public void increaseAge();
    
    public double getSpeed();

	public void setSpeed(double speed);

    /*
     * Periodically updated bullet effect. Use this to create heat-seeking
     * missiles, etc..
     */
    public void effect();

    public void hitDetected();

    public String getPhysicalRepresentation();

    public boolean hits(Spawnable item);

    void hitDetected(Spawnable item);

    void hitDetected(Spawnable item, EventSender eventSender);

    boolean isAgeCounterExpired();
}
