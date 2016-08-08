package game.model;

public class HighScore {

    private long score;
    
    private String name;

    public long getScore() {
        return score;
    }

    public void setScore(long score) {
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HighScore(long score, String name) {
        super();
        this.score = score;
        this.name = name;
    }
    
    public String toString(){
        return "" + name + " " + score; 
    }
    
}
