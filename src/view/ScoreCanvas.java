package view;

import model.Explorer;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;

//分数画布；
public class ScoreCanvas extends JPanel
{
    //透明度
    private final int transparency;
    //方块大小
    private final int blockSize;
    //线条的粗细
    private final float lineSize;
    //相对JFrame的偏移
    private final int mX, mY;

    private JLabel scoreTxt = null;

    private final int width, height;

    private int score = 0;

    public ScoreCanvas(int width, int height, int transparency, int blockSize, float lineSize, int mX, int mY)
    {
        this.width = width;
        this.height = height;
        this.mX = mX;
        this.mY = mY;
        this.lineSize = lineSize;
        this.transparency = transparency;
        this.blockSize = blockSize;
        this.score = 0;
        this.setLayout(new BorderLayout());
        this.scoreTxt = new JLabel(Explorer.getScoreTxt() + String.format("%d", 0));
        scoreTxt.setBounds(50,0,100,100);
        scoreTxt.setFont(new Font("宋体", Font.BOLD, 20));
        scoreTxt.setForeground(Color.CYAN);

        this.add("Center", scoreTxt);

        this.setBounds(mX,mY, width, height);
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
    }

    private void airBubbleFilm(Graphics2D g2)
    {
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_DEFAULT);
        Stroke s = new BasicStroke(1, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER);
        g2.setStroke(s);
        g2.setColor(new Color(255,255,255, getTransparency() - 15));
        g2.fillRoundRect(0 , 0 , getWidth(), getHeight(), 45, 45);
        //g2.drawImage(Explorer.getBlueBlock().getImage(), 0,0,25,25,null);
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

    @Override
    public int getWidth() { return width; }

    @Override
    public int getHeight() { return height; }

    public void addScore(int addScore){ this.score += addScore; this.updateScore(); this.repaint(); }//System.out.println(this.score);

    public int getTransparency() { return transparency; }

    public int getBlockSize() { return blockSize; }

    public float getLineSize() { return lineSize; }

    public int getMX() { return mX; }

    public int getMY() { return mY; }

    public int getScore() { return score; }

    public void resetScore(){ this.score = 0; updateScore(); this.repaint();}

    private void updateScore(){ this.scoreTxt.setText(Explorer.getScoreTxt() + String.format("%d", this.score)); }
}
