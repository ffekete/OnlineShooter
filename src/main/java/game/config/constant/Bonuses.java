package game.config.constant;

public enum Bonuses {
    RATE_OF_FIRE("bonusRof"),
    DAMAGE("bonusDamage");
    
    private final String id;
    
    private Bonuses(String id){
        this.id = id;
    }
    
    public String getId(){
        return this.id;
    }
}
