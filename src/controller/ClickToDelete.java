package controller;

import model.*;
import view.Playing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ClickToDelete extends JButton implements MouseListener
{
    private BlockTexture thisBlock = null;
    private BlockDates blockDates;
    private int myLine, myColumn;

    public ClickToDelete(BlockDates blockDates, int myLine, int myColumn, BlockTexture thisBlock)
    {
        //super();
        this.thisBlock = thisBlock;
        this.blockDates = blockDates;
        this.myLine = myLine;
        this.myColumn = myColumn;
        this.setBounds(myColumn * Playing.getBlockSize(),myLine * Playing.getBlockSize(), Playing.getBlockSize(), Playing.getBlockSize());
        //this.thisBlock = null;


        this.setBorderPainted(false);
        this.setContentAreaFilled(false);
        this.setOpaque(false);//透明
        this.setSize(Playing.getBlockSize(),Playing.getBlockSize());


        this.addMouseListener(this);
        //this.setContentAreaFilled(false);
        this.setFocusable(false);//把焦点给键盘
    }

    //TODO 为什么鼠标监听和键盘监听不能同时存在
    public void paintComponent(Graphics g)
    {
        //super.paintComponent(g);
        //if为按钮特效，目前不需要
        if(this.getModel().isPressed() && null != thisBlock) g.drawImage(thisBlock.getIMAGEICON().getImage(), 1, 1, Playing.getBlockSize(), Playing.getBlockSize(), this);
        else if(null != thisBlock) g.drawImage(thisBlock.getIMAGEICON().getImage(), 0, 0, Playing.getBlockSize(), Playing.getBlockSize(), this);
    }

    //@Override
    //public boolean contains(int x,int y) { super(x, y); }

    public void addParticulars25(BlockTexture thisBlock)
    {
        if(null == thisBlock)return;
        //(random.nextBoolean() ? 2.8555555555555 : random.nextBoolean() ? 100.0 : 50.0);
        for(int i = 0; i < 5; i++)
            for(int j = 0; j < 5; j++)
                ParticleGather.addAParticle(thisBlock, Playing.getBlockSize() / 5, Playing.getBlockSize() / 5, -1,
                        this.myColumn * Playing.getBlockSize() + j * (Playing.getBlockSize() / 5),
                        this.myLine * Playing.getBlockSize() + (i * Playing.getBlockSize() / 5));
    }

    public void setThisBlock(BlockTexture thisBlock) { this.thisBlock = thisBlock; }

    public BlockTexture getThisBlock() { return thisBlock; }

    @Override
    public Dimension getPreferredSize()
    {
        return super.getPreferredSize();
    }

    @Override
    public void mouseClicked(MouseEvent e)//mouseReleased
    {
        if(null == this.thisBlock)
        {
            this.setThisBlock(Enums.random(BlockTexture.class)); //空则添加方块，颜色随机
            if(blockDates.addThisLine(myLine))
            {
                blockDates.getDeleteData();
                blockDates.forNxtScene();
                blockDates.resetNxt();
            }
        }
        else { this.thisBlock = null;blockDates.subThisLine(this.myLine); } //有则消除方块
        //System.out.println("Clicking!!!");
        //Playing.getInstance().requestFocusInWindow();
    }

    @Override
    public void mousePressed(MouseEvent e) { }

    @Override
    public void mouseReleased(MouseEvent e) { }

    @Override
    public void mouseEntered(MouseEvent e) { }

    @Override
    public void mouseExited(MouseEvent e) { }
}
