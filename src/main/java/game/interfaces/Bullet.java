package game.interfaces;

import game.controller.EventSender;

public interface Bullet extends Spawnable{


    public long getPlayerId();

    public void setPlayerId(long playerId);

    public long getDamage();
    
    public void setDamage(long damage);

    public void increaseAge();
    
    public long getAge();

    public void setAge(long age);

    /** Periodically updated bullet effect. Use this to create heat-seeking missiles, etc.. */
    public void effect();
    
    public void hitDetected();
    
    public String getPhysicalRepresentation();
    
    public boolean hits(Spawnable item);

    void hitDetected(Spawnable item);

    void hitDetected(Spawnable item, EventSender eventSender);
    
    boolean isAgeCounterExpired();
}
