package controller;

import model.*;
import view.Playing;
import view.SuspendCanvas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class AssumeGame extends JButton implements MouseListener
{
    private Image nowIcon = null;
    private SuspendCanvas suspendCanvas = null;

    public AssumeGame(SuspendCanvas suspendCanvas)
    {
        this.suspendCanvas = suspendCanvas;
        //this.thisBlock = null;
        this.setVisible(true);
        this.setBorderPainted(false);
        this.setContentAreaFilled(false);
        //this.setOpaque(false);//透明
        //this.setSize(Playing.getBlockSize(),Playing.getBlockSize());

        nowIcon = Explorer.getSuspend().getImage();

        this.addMouseListener(this);
        //this.setContentAreaFilled(false);
        this.setFocusable(false);//把焦点给键盘
    }

    //TODO 为什么鼠标监听和键盘监听不能同时存在
    public void paintComponent(Graphics g)
    {
        //super.paintComponent(g);
        //if为按钮特效，目前不需要
        if(this.getModel().isPressed()) g.drawImage(nowIcon, 1, 1,this.getWidth(), this.getHeight(), this);
        else g.drawImage(nowIcon, 0, 0, this.getWidth(), this.getHeight(), this);
    }

    //@Override
    //public boolean contains(int x,int y) { super(x, y); }




    @Override
    public Dimension getPreferredSize()
    {
        return super.getPreferredSize();
    }

    @Override
    public void mouseClicked(MouseEvent e)//mouseReleased
    {

        this.repaint();
        Playing.getInstance().assumeTheGame();
        //public SuspenCanvas(int width, int height, int transparency, int blockSize, float lineSize, int mX, int mY)
        Playing.getInstance().getSuspend().setVisible(true);//恢复程序
        SuspendCanvas.getInstance().closeThisDialog();
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
