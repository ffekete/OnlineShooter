package game.datatypes.Items;

import game.interfaces.SpawnableItem;

public abstract class ItemParent implements SpawnableItem {
    private String name;
    private double x;
    private double y;
    private double angle;
    
    @Override
    public void setX(double x) {
        this.x = x;
    }

    @Override
    public void setY(double y) {
        this.y = y;
    }

    @Override
    public double getX() {
        return this.x;
    }

    @Override
    public double getY() {
        return this.y;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public void setAngle(double angle){
    	this.angle = angle;
    }
    
    public double getAngle(){
    	return this.angle;
    }
}
