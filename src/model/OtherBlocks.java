package model;

import java.awt.*;

//这个类是方块的设计思想，只要获取一个方块，再加上其它方块距离该方块的偏移，即可获得其它方块的位置；
public class OtherBlocks
{
    private Point[] excursion;

    public OtherBlocks(int[] pX, int[] pY)
    {
        excursion = new Point[4];
        for(int i = 0; i < 4; i++)
           excursion[i] = new Point(pX[i], pY[i]);
    }

    public void setPoints(Point[] excursion)
    {
        this.excursion = new Point[4];
        for (int i = 0; i < 4; i++)
            this.excursion[i] = new Point(excursion[i].x, excursion[i].y);
    }

    public Point[] getExcursion() { return excursion; }

}
