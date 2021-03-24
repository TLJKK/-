package controller;

import model.*;
import view.GameOVer;
import view.Playing;
import view.RankingListCanvas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class UpButton extends JButton implements MouseListener
{
    private GameOVer gameOVer = null;
    private Image nowIcon = null;
    private boolean justExit = false;
    private RankingListCanvas rankingListCanvas = null;

    public UpButton(RankingListCanvas rankingListCanvas)
    {
        this.rankingListCanvas = rankingListCanvas;
        justExit = true;
        this.setVisible(true);
        this.setBorderPainted(false);
        nowIcon = Explorer.getUp().getImage();
        this.addMouseListener(this);
    }

    public UpButton(GameOVer gameOVer)
    {
        this.gameOVer = gameOVer;
        justExit = false;
        this.setVisible(true);
        this.setBorderPainted(false);
        nowIcon = Explorer.getUp().getImage();

        this.addMouseListener(this);
        //this.setFocusable(false);
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
        // 创建一个模态对话框
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                if(!justExit)new GameIsOverAndWriteToMySqlAndReadData(gameOVer, Playing.getInstance().getSCORECANVAS().getScore()).writeName();
                if(justExit)
                {
                    Playing.getInstance().assumeTheGame();
                    Playing.getInstance().getRankButton().setVisible(true);
                    Playing.getInstance().repaint();
                    rankingListCanvas.dispose();
                }
                else gameOVer.dispose();
            }
        });

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
