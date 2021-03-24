package view;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;

//主画布，整个游戏界面；
public class MainCanvas extends JPanel
{
    //透明度
    private final int transparency;
    //方块大小
    private final int blockSize;
    //线条的粗细
    private final float lineSize;
    //相对JFrame的偏移
    private final int mX, mY;

    private int width, height;

    public MainCanvas(int width, int height, int transparency, int blockSize, float lineSize, int mX, int mY)
    {
        this.width = width;
        this.height = height;
        this.mX = mX;
        this.mY = mY;
        this.lineSize = lineSize;
        this.transparency = transparency;
        this.blockSize = blockSize;

        this.setBounds(0,0, width, height);
        //设置背景透明
        this.setOpaque(false);
        //添加俄罗斯方块
        //this.add(new PaintGame(getMX(), getMY(), getBlockSize() * 10,getBlockSize() * 20, getBlockSize()));
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);//不透明因为这个,父类会为JPanel画上一层背景颜色
        Graphics2D g2 = (Graphics2D)g;
        //玻璃罩
        airBubbleFilm(g2);
        //白色矩形边框
        drawRect(g2);

    }

    private void airBubbleFilm(Graphics2D g2)
    {
        g2.setColor(new Color(255,255,255,this.getTransparency()));
        g2.fillRect(getMX() , getMY() ,getBlockSize() * 10,getBlockSize() * 20);
    }

    private void drawRect(Graphics2D g2)
    {
        g2.setPaint(Color.WHITE);
        Stroke stroke=new BasicStroke(getLineSize());//设置线宽为3.0
        g2.setStroke(stroke);
        //矩形下边
        Line2D now = new Line2D.Double(getMX() - 3, getMY() + 20 * getBlockSize() + 2, getMX() + getBlockSize() * 10, getMY() + 2 + 20 * getBlockSize());
        g2.draw(now);
        //矩形右边
        now = new Line2D.Double(getMX() + 3 + 10 * getBlockSize(), getMY() - 3, getMX() + 3 + 10  * getBlockSize(), getMY() + 2 + getBlockSize() * 20);
        g2.draw(now);
        //矩形上边, 当心线条宽度会阻挡俄罗斯方块的显示， 所以减去lineSize；
        now = new Line2D.Double(getMX(), getMY() - getLineSize() + 1, getMX() + getBlockSize() * 10 + 2, getMY() - getLineSize() + 1);
        g2.draw(now);
        //矩形左边
        now = new Line2D.Double(getMX() - getLineSize() + 1, getMY() - getLineSize() + 2 - 1, getMX() - getLineSize() + 1, getMY() + getBlockSize() * 20 + 2);
        g2.draw(now);
    }


    public int getTransparency() { return transparency; }

    public int getBlockSize() { return blockSize; }

    public float getLineSize() { return lineSize; }

    public int getMX() { return mX; }

    public int getMY() { return mY; }
}
