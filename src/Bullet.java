import processing.core.PApplet;

public class Bullet extends Floater{

    public Bullet(PApplet applet, SpaceShip ship) {
        super(applet);
        this.setPos(ship.getX(), ship.getY());
        this.setV(ship.getVX() * 2, ship.getVY() * 2);
        this.rotate(ship.getDirection());
    }

    public void show(){
        applet.fill(applet.color(255,255,0));
        applet.stroke(applet.color(255,255,0));
        applet.ellipse((float)getX(),(float)getY(),10,10);
    }

    public void move(){
        setPos(getX() + getVX(), getY() + getVY());
        accelerateForward(1);
    }
}
