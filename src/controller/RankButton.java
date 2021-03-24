package controller;

import model.*;
import view.Playing;
import view.RankingListCanvas;
import view.SuspendCanvas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class RankButton extends JButton implements MouseListener
{
    private Image nowIcon = null;
    public RankButton()
    {

        this.setBounds(365,420, 50, 50);
        //this.thisBlock = null;

        this.setBorderPainted(false);
        this.setContentAreaFilled(false);
        this.setOpaque(false);//透明
        //this.setSize(Playing.getBlockSize(),Playing.getBlockSize());

        nowIcon = Explorer.getRankLogo().getImage();


        this.addMouseListener(this);
        //this.setContentAreaFilled(false);
        this.setFocusable(false);//把焦点给键盘
    }

    //TODO 为什么鼠标监听和键盘监听不能同时存在
    public void paintComponent(Graphics g)
    {
        if(this.getModel().isPressed()) g.drawImage(nowIcon, 1, 1,this.getWidth(), this.getHeight(), this);
        else g.drawImage(nowIcon, 0, 0, this.getWidth(), this.getHeight(), this);
    }


    @Override
    public Dimension getPreferredSize()
    {
        return super.getPreferredSize();
    }

    @Override
    public void mouseClicked(MouseEvent e)//mouseReleased
    {

        this.repaint();
        Playing.getInstance().suspendTheGame();
        this.setVisible(false);
        new RankingListCanvas(Playing.getInstance(),Playing.getInstance());
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
