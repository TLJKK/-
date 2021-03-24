package model;


import java.awt.*;

public enum BlockStyles
{
    LEFTWORDZ(BlockDates.getLeftZPx(), BlockDates.getLeftZPy()),
    RIGHTWORDZ(BlockDates.getRightZPx(), BlockDates.getRightZPy()),
    LEFTWORDL(BlockDates.getLeftLPx(), BlockDates.getLeftLPy()),
    RIGHTWORDL(BlockDates.getRightLPx(), BlockDates.getRightLPy()),
    WORDT(BlockDates.getWordTX(), BlockDates.getWordTY()),
    SQUARE(BlockDates.getSquareX(), BlockDates.getSquareY()),
    RECTANGLE(BlockDates.getRectangleX(), BlockDates.getRectangleY())
    ;


    private int[] pX, pY;
    private Point[] ali;
    BlockStyles(int[] pX, int[] pY)
    {
        this.pX = new int[4];
        this.pY = new int[4];
        this.ali = new Point[4];
        for(int i = 0; i < 4; i++)
        {
            this.pX[i] = pX[i];
            this.pY[i] = pY[i];
            this.ali[i] = new Point(pX[i], pY[i]);
        }
    }

    public int[] getpX() { return pX; }

    public Point[] getAli() { return ali; }

    public int[] getpY() { return pY; }
}

