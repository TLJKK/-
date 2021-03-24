package view;

import controller.AssumeGame;
import controller.RefreshGame;
import model.GameSize;

import javax.swing.*;
import java.awt.*;

//分数画布；
public class SuspendCanvas extends JPanel
{
    private static SuspendCanvas instance = null;
    private JLabel title = null;
    private AssumeGame assumeGame = null;
    private RefreshGame refreshGame = null;

    private SuspendCanvas()
    {
        this.setVisible(false);
        this.setLayout(null);
        this.setSize(GameSize.getWidth(), GameSize.getHeight());
        title = new JLabel("游戏暂停");
        title.setFont(new Font("华云彩体", Font.BOLD,20));
        title.setForeground(Color.PINK);
        this.add(title);
        title.setBounds(this.getWidth() / 2 - 50, 100, this.getWidth(), 20);
        //设置背景透明
        addButton();
    }

    private void addButton()
    {
        this.setOpaque(false);
        assumeGame = new AssumeGame(this);
        assumeGame.setBounds(300,220, 50, 50);
        this.add(assumeGame);

        refreshGame = new RefreshGame(this);
        refreshGame.setBounds(160,220, 50, 50);
        this.add(refreshGame);
    }

    public void mySetVisible(boolean aFlag, boolean gameOver)//0 结束 1 展厅
    {
        super.setVisible(aFlag);
        if(gameOver)
        {
            title.setForeground(Color.GREEN);
            title.setText("游戏结束");
            assumeGame.setVisible(false);
            refreshGame.setLocation(225,200);
            this.repaint();
        }
        else
        {
            title.setForeground(Color.PINK);
            title.setText("游戏暂停");
            assumeGame.setVisible(true);
            refreshGame.setLocation(160,220);
            this.repaint();
        }
    }

    public static SuspendCanvas getInstance()
    {
        synchronized (SuspendCanvas.class)
        {
            if (null == instance)
            {
                instance = new SuspendCanvas();
            }
            return instance;
        }
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
        g2.setColor(new Color(0,0,0,120));
        g2.fillRect(0 , 0 , getWidth(), getHeight());
        //g2.drawImage(Explorer.getBlueBlock().getImage(), 0,0,25,25,null);
    }

    public void openThisDialog(boolean gameOver)
    {
        instance.mySetVisible(true, gameOver);
    }

    public void closeThisDialog()
    {
        instance.setVisible(false);
    }

}
