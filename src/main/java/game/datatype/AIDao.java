package game.datatype;

public class AIDao {
    private boolean isAi;
    private boolean isAsteroid;
    
    public AIDao (boolean isAi, boolean isAsteroid){
        this.isAi = isAi;
        this.isAsteroid = isAsteroid;
    }
    
    public boolean getIsAi() {
        return isAi;
    }

    public boolean getIsAsteroid() {
        return isAsteroid;
    }
}