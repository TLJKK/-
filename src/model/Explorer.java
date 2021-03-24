package model;

import javax.swing.*;

//资源管理器
public abstract class Explorer
{
    private static ImageIcon background = new ImageIcon("resources/background.jpg");
    private static ImageIcon blueBlock = new ImageIcon("resources/blue.png");
    private static ImageIcon greenBlock = new ImageIcon("resources/green.png");
    private static ImageIcon pinkBlock = new ImageIcon("resources/pink.png");
    private static ImageIcon purpleBlock = new ImageIcon("resources/purple.png");
    private static ImageIcon yellowBlock  = new ImageIcon("resources/yellow.png");
    private static ImageIcon score  = new ImageIcon("resources/score.png");
    private static ImageIcon suspend  = new ImageIcon("resources/suspend.png");
    private static ImageIcon running  = new ImageIcon("resources/running.png");
    private static ImageIcon refresh  = new ImageIcon("resources/refresh.png");
    private static ImageIcon gameOver  = new ImageIcon("resources/gameOver.png");
    private static ImageIcon rankLogo  = new ImageIcon("resources/rankLogo.png");
    private static ImageIcon up  = new ImageIcon("resources/up.png");
    private static ImageIcon musicPlaying  = new ImageIcon("resources/musicPlaying.png");
    private static ImageIcon rankingList  = new ImageIcon("resources/rankingList.png");
    private static ImageIcon musicSuspend  = new ImageIcon("resources/musicSuspend.png");
    private static String    scoreTxt = "分数：";

    public static ImageIcon getBackground() { return background; }
    public static ImageIcon getBlueBlock() { return blueBlock; }
    public static ImageIcon getGreenBlock() { return greenBlock; }
    public static ImageIcon getPinkBlock() { return pinkBlock; }
    public static ImageIcon getPurpleBlock() { return purpleBlock; }
    public static ImageIcon getYellowBlock() { return yellowBlock; }
    public static ImageIcon getScore() { return score; }
    public static ImageIcon getSuspend() { return suspend; }
    public static ImageIcon getRunning() { return running; }
    public static ImageIcon getRefresh() { return refresh; }
    public static ImageIcon getRankingList() { return rankingList; }
    public static ImageIcon getMusicPlaying() { return musicPlaying; }
    public static ImageIcon getMusicSuspend() { return musicSuspend; }
    public static ImageIcon getGameOver() { return gameOver; }
    public static ImageIcon getUp() { return up; }
    public static ImageIcon getRankLogo() { return rankLogo; }
    public static String    getScoreTxt() { return scoreTxt; }
}
