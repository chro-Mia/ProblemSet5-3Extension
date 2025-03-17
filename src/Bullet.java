import processing.core.PApplet;

public class Bullet extends Floater{

    public Bullet(PApplet applet, SpaceShip s) {
        super(applet);

        this.setPos(s.getX(), s.getY());
        this.rotate(s.getDirection());
        this.setV(s.getVX() * 3, s.getVY() * 3);
    }

    public void show(){
        int color = applet.color(255,255,0);
        applet.fill(color);
        applet.stroke(color);
        applet.ellipse((float)getX(),(float)getY(),10,10);
    }

    public void move(){
        setPos(getX() + getVX(), getY() + getVY());
        accelerateForward(5);
    }
}
