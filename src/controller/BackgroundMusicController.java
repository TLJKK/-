package controller;

import model.*;
import view.Playing;
import view.SuspendCanvas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class BackgroundMusicController extends JButton implements MouseListener
{
    private Image nowIcon = null;

    public BackgroundMusicController()
    {
        this.setBounds(365,260, 50, 50);
        //this.thisBlock = null;
        this.setVisible(true);
        this.setBorderPainted(false);
        this.setContentAreaFilled(false);

        nowIcon = Explorer.getMusicPlaying().getImage();

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

    @Override
    public Dimension getPreferredSize()
    {
        return super.getPreferredSize();
    }

    @Override
    public void mouseClicked(MouseEvent e)//mouseReleased
    {
        if(Playing.getInstance().getPlayTheBackgroundMusic().isPlayingMusic())
        {
            nowIcon = Explorer.getMusicSuspend().getImage();//.getScaledInstance(50,50,1);
            Playing.getInstance().getPlayTheBackgroundMusic().suspendTheMusic();
        }
        else
        {
            nowIcon = Explorer.getMusicPlaying().getImage();//.getScaledInstance(50,50,1);
            Playing.getInstance().getPlayTheBackgroundMusic().assumeTheMusic();
        }

        this.repaint();

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
