import processing.core.PApplet;
import java.util.ArrayList;
public class AsteroidsGame extends PApplet
{
    // This is just an example object
    private SpaceShip s;
    private int backcolor;
    private ArrayList<Floater> objects;

    public void settings()
    {
        size(600,600);
    }

    public void setup()
    {
        objects = new ArrayList<Floater>();
        s = new SpaceShip(this);

        backcolor = color(0,0,0);
        objects.add(s);

        for(int i = 0; i < 7; i++){
            Asteroid asteroid = new Asteroid(this,
                    (int)(Math.random() * width),
                    (int)(Math.random() * height),
                    Math.random() * -2 + 1,
                    Math.random() * -2 + 1,
                    Math.random() * 30 + 20,
                    Math.random() * -2 + 1);
            objects.add(asteroid);
        }
    }

    public void draw()
    {
        if(keyCode==UP && keyPressed)
        {
            s.accelerateForward(0.1);

        }
        if(keyCode== LEFT && keyPressed)
        {
            s.rotate(-3);

        }
        if(keyCode== RIGHT && keyPressed)
        {
            s.rotate(3);

        }
        if(keyCode==DOWN && keyPressed)
        {
            s.accelerateForward(-0.1);

        }

        //
        if(key == ' ' && keyPressed && objects.get(0) instanceof SpaceShip)
        {
            objects.add(new Bullet(this, s));
        }
        background(backcolor);

        for(Floater f : objects)
        {
            f.show();
            f.move();
        }

        //loop backwards because the size of the arraylist changes
        for(int i = objects.size() - 1; i >= 0; i--)
        {
            Floater obj = objects.get(i);
            if(obj instanceof Asteroid)
            {
                Asteroid ast = (Asteroid) obj;
                if(ast.isColliding(s))
                {
                    objects.remove(obj);
                    objects.remove(s);

                }
            }
        }



        // NOTE: THIS IS AN EXPLANATION OF THE NEW CODE, YOU CAN DECIDE TO KEEP OR DELETE THE EXPLANATION BUT DELETE
        // THIS MESSAGE

        // We create two for-each loops to check if we are comparing Asteroids and Bullets

        // Then, we use the isColliding() method to check if the Bullet and Asteroid we are comparing are colliding or
        //not

        // If they are, we will flag them as garbage to be removed from the object list later

        // We then check if the Asteroid we hit was larger than 10 units, if it was, we will split it later

        // Now, we check if any splittable Asteroids were hit on this frame, if so, we create two smaller Asteroids and
        //add them to the list

        // Finally, we remove any Floaters marked as garbage from the list and check if all the asteroids are gone to
        //see if we've won the game

        Asteroid splitPoint = null;

        for(Floater f1 : objects) {
            if(f1 instanceof Bullet) {
                Bullet b = (Bullet)f1;
                for(Floater f2 : objects) {
                    if(f2 instanceof Asteroid){
                        Asteroid a = (Asteroid)f2;
                        if(a.isColliding(b)){
                            a.isGarbage = true;
                            b.isGarbage = true;
                            if(a.getRad() > 20){
                                splitPoint = a;
                            }
                        }
                    }
                }
            }
        }

        if(splitPoint != null){
            Asteroid a1 = new Asteroid(this,
                    (int)(splitPoint.getX() + Math.random() * -15 + 7.5),
                    (int)(splitPoint.getY() + Math.random() * -15 + 7.5),
                    Math.random() * -5 + 2.5,
                    Math.random() * -5 + 2.5,
                    Math.random() * splitPoint.getRad()/2 + 5,
                    Math.random() * -2 + 1);
            Asteroid a2 = new Asteroid(this,
                    (int)(splitPoint.getX() + Math.random() * -15 + 7.5),
                    (int)(splitPoint.getY() + Math.random() * -15 + 7.5),
                    Math.random() * -5 + 2.5,
                    Math.random() * -5 + 2.5,
                    Math.random() * splitPoint.getRad()/2 + 5,
                    Math.random() * -2 + 1);
            objects.add(1, a1);
            objects.add(1, a2);
        }

        for(int i = objects.size() - 1; i >= 0; i--){
            if(objects.get(i).isGarbage){
                objects.remove(i);
            }
        }

        boolean win = true;
        for(Floater f : objects){
            if(f instanceof Asteroid){
                win = false;
            }
        }

        if(win && objects.get(0) instanceof SpaceShip){
            text("YOU WIN!", 250, 250, 0);
        }
    }
}