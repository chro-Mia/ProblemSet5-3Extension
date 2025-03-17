import processing.core.PApplet;

public class SpaceShip extends Floater
{
    private int[] xCorners = {70, -30, -30};
    private int[] yCorners = {0, -20, 20};
    private int color;


    public SpaceShip(PApplet applet)
    {
        super(applet);
        color = applet.color(0, 255, 255);
        setColor(color);
        setCorners(xCorners,yCorners);
        rotate(-90);



        setStartPos();
    }

    private void setStartPos()
    {
        // Because the `applet` variable in Floater has "protected" access,
        // we can use it directly in subclasses.
        double x = applet.width/4;
        double y = applet.height*3/4;

        setPos(x,y);
    }
}