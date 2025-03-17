import processing.core.PApplet;

public class Asteroid extends Floater
{
    private int color;
    private double rotationspeed;
    private double rad;

    public Asteroid(PApplet applet, int x, int y, double velx, double vely, double radius, double rotation)
    {
        super(applet);
        setV(velx,vely);

        color = applet.color(255, 0, 255);
        setColor(color);
        rotationspeed = rotation;
        rad=radius;


        int[] xCorners = new int[12];
        int[] yCorners = new int[12];
        super.setPos(x, y);
        for (int i = 0; i < 12; i++)
        {
            double angle = (2 * Math.PI * i) / 12;
            double rand = radius * (0.7 + Math.random() * 0.6);
            xCorners[i] = (int) (rand * Math.cos(angle));
            yCorners[i] = (int) (rand * Math.sin(angle));

        }
        setCorners(xCorners, yCorners);

    }

    public void move()
    {
        super.move();
        rotate(rotationspeed);
    }

    public Boolean isColliding(Floater thing)
    {
        double x2 = Math.pow(this.getX() - thing.getX(), 2);
        double y2 = Math.pow(this.getY() - thing.getY(), 2);
        double distance = Math.sqrt(x2 + y2);
        if(distance <=rad)
        {
            return true;
        }
        return false;
    }

    public double getRad(){
        return rad;
    }
}