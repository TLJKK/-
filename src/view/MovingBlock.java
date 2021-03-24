package view;

import model.BlockDates;

import javax.swing.*;
import java.awt.*;

//移动的俄罗斯方块的画布；
public class MovingBlock extends JPanel
{
    private int mX, mY, blockSize;

    public MovingBlock(int mX, int mY, int width, int height, int blockSize)
    {
        this.mX = mX;
        this.mY = mY;
        this.blockSize = blockSize;
        this.setBounds(mX, mY, width, height);
        this.setOpaque(false);//Opaque -- 不透明
        this.setVisible(true);
        this.setLayout(null);
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        paintRect(g);
    }

    private void paintRect(Graphics g)
    {
        Point[] now = Playing.getInstance().getBlockDates().getOtherBlocks().getExcursion();
        BlockDates blockDates = Playing.getInstance().getBlockDates();
        AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.30f);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setComposite(ac);
        //g2d.drawImage(bgimage, x, y, width, height, this);

        //TODO 探究为什么是21，而不是22：
        //由于它四个方块中有一个是往下偏移，所以主方块是21，而不是22
        //虚影
        for(int i = 0; i < 4; i++)
            g2d.drawImage(blockDates.getBlockTexture().getIMAGEICON().getImage(), (blockDates.getVirtualColumn() + now[i].x) * this.getBlockSize(),
                    (blockDates.getVirtualLine() + now[i].y) * this.getBlockSize(), getBlockSize(), getBlockSize(),null);

        ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f);
        //实体
        g2d.setComposite(ac);
        for(int i = 0; i < 4; i++)
            g2d.drawImage(blockDates.getBlockTexture().getIMAGEICON().getImage(), (blockDates.getColumn() + now[i].x) * this.getBlockSize(),
                    (blockDates.getLine() + now[i].y) * this.getBlockSize(), getBlockSize(), getBlockSize(),null);
        //g2d.drawImage(blockDatas.getBlockTexture().getImageIcon().getImage(), (blockDatas.getColumn() + now[0].x) * this.getBlockSize(),
                //(21 + now[0].y) * this.getBlockSize(), getBlockSize(), getBlockSize(),null);

    }

    public int getMX() { return mX; }
    public int getMY() { return mY; }
    public int getBlockSize() { return blockSize; }
}
