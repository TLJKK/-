package model;

import view.Playing;

import java.awt.*;
import java.util.Random;

public class FormParticle implements LocationOperator
{
    private BlockTexture texture;
    private int width, height;
    private int life;
    private int myX, myY;
    private double vX, vY, acceleration, time, speed;
    private int dirY, dirX;
    private final double LOSS =  0.48;
    private double quality;
    //this(seedUniquifier() ^ System.nanoTime());
    private static final Random RANDOM = new Random();
    private static double divSon = 100.0;
    //private int blockSize, bottom, rightMargin;
    //private LocationOperator posIterator;

    private FormParticle()
    {
        //Random random = new Random();
        int randNumber = RANDOM.nextInt(1029); // randNumber 将被赋值为一个 MIN 和 MAX 范围内的随机数
        //System.out.println(random.nextBoolean());
        double angle = (double) randNumber / divSon;///(random.nextBoolean() ? 2.8555555555555 : random.nextBoolean() ? 100.0 : 50.0);
        //System.out.println(randNumber);
        this.speed = randNumber / 10.0;
        this.vY = speed * Math.sin(angle);
        this.vX = speed * Math.cos(angle);

        if(this.vY > 0)this.dirY = 1;
        else this.dirY = -1;
        if(this.vX > 0)this.dirX = 1;
        else this.dirX = -1;

        this.vY = Math.abs(this.vY);
        this.vX = Math.abs(this.vX);
        this.acceleration = 9.8;
        this.quality = randNumber / 30.0;
    }


    public FormParticle(BlockTexture texture, int width, int height, int life, int myX, int myY)
    {
        this();
        this.texture = texture;
        this.width = width;
        this.height = height;
        this.life = life;
        this.myX = myX;
        this.myY = myY;
    }

    public BlockTexture getTexture() { return texture; }

    public void setTexture(BlockTexture texture) { this.texture = texture; }

    public int getWidth() { return width; }

    public void setWidth(int width) { this.width = width; }

    public int getHeight() { return height; }

    public void setHeight(int height) { this.height = height; }

    public int getLife() { return life; }

    public void setLife(int life) { this.life = life; }

    public int getMyX() { return myX; }

    public void setMyX(int myX) { this.myX = myX; }

    public int getMyY() { return myY; }

    public void setMyY(int myY) { this.myY = myY; }

    public double getSpeed() { return speed; }

    public void setSpeed(double speed) { this.speed = speed; }

    public double getAcceleration() { return acceleration; }

    public void setAcceleration(double acceleration) { this.acceleration = acceleration; }

    @Override
    public void forNextPos()//位置算子
    {


        this.myX += vX * 0.1 * dirX;//都是经过100ms后的位置
        this.myY += vY * 0.1 * dirY + 0.5 * this.acceleration * 0.01;

        vY += acceleration * 0.1 * dirY;
        //if(this.myY >= Playing.getPHeight() - 60 || this.myY < 0) { dirY = -dirY; energyConversion(); }

        if(this.myX < 0) { dirX = 1; energyConversion(); }
        else if(this.myX >= Playing.getPWidth()) { dirX = -1; energyConversion(); }

        new Point(this.myX, this.myY);
    }

    private void energyConversion()
    {
        //vX = Math.sqrt(vX * vX * (loss / 10000));
        //vY = Math.sqrt(vY * vY * loss);
    }

    public static void setDivSon(double divSon) { FormParticle.divSon = divSon; }

    //public LocationOperator getPosIterator() { return posIterator; }

    //public void setPosIterator(LocationOperator posIterator) { this.posIterator = posIterator; }

    public static void setRandomForm()
    {
        double now = FormParticle.getRandom().nextInt(300) / 3.0;
        while(now < 30)now = 20 + FormParticle.getRandom().nextInt(300) / 3.0;;
        //System.out.println(now);
        FormParticle.setDivSon(100);
    }


    public static Random getRandom() { return RANDOM; }
}
