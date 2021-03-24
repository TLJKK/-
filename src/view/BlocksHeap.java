package view;

import controller.ClickToDelete;
import model.BlockDates;

import javax.swing.*;
import java.awt.*;

//该类是俄罗斯方块中已经确定位置的方块对，在底部等待消除，或者等待游戏结束；
public class BlocksHeap extends JPanel
{
    private int mX, mY, blockSize;
    private BlockDates blockDates;

    BlocksHeap(int mX, int mY, int width, int height, int blockSize, BlockDates blockDates)
    {
        this.blockDates = blockDates;
        this.mX = mX;
        this.mY = mY;
        this.blockSize = blockSize;
        this.setBounds(mX, mY, width, height);
        this.setOpaque(false);//Opaque -- 不透明
        this.setVisible(true);
        this.setLayout(null);
        //this.validate();

    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        paintRect(g);
    }

    private void paintRect(Graphics g)
    {
        ClickToDelete[][] now = blockDates.getBlockColors();

        for(int i = 2; i < 23; i++)
            for(int j = 0; j < 10; j++)
                if(null != now[i][j].getThisBlock())g.drawImage(now[i][j].getThisBlock().getIMAGEICON().getImage(),j * this.getBlockSize(),
                        i * this.getBlockSize(),getBlockSize(), getBlockSize(),null);
                //加一的原因是因为，JPanel往上了一格
    }

    public int getMX() { return mX; }
    public int getMY() { return mY; }
    public int getBlockSize() { return blockSize; }
}
