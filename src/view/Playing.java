package view;

import controller.*;
import model.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


//该类是游戏的主要运行界面，掌控JFrame
public class Playing extends JFrame
{
    private final int WIDTH, HEIGHT;//主屏幕的长和宽
    private BlockDates blockDates;//正在移动的俄罗斯方块信息对象实例
    private MovingBlock movingBlock;//主要用于传递给BlockDatas用于repaint；
    private BlocksHeap blocksHeap;//方块堆的JPanel
    private static final int blockSize = 25;//方块大小
    private static Playing instance = null;//单例模式的实例
    private final ScoreCanvas SCORECANVAS;//分数画布
    private final ParticleCanvas PARTICLECANVAS;//粒子特效画布
    private NextBlocks nextBlocks = null;//下一个方块的画布
    private static int pWidth, pHeight;//偏移的X， Y
    private static boolean isRunning;//游戏暂停的判断参数
    private SuspendGame suspendGame = null;//暂停游戏按钮
    private PlayTheBackgroundMusic playTheBackgroundMusic = null;//背景音乐播放类
    private AutoDown autoDown = null;//方块自动下降的线程
    private RankButton rankButton = null;//排行榜触发按钮

    private Playing(int WIDTH, int HEIGHT)
    {
        //PlayMusic.getBk().loop();
        isRunning = true;
        pWidth = this.WIDTH = WIDTH; pHeight = this.HEIGHT = HEIGHT;
        this.blockDates = new BlockDates(this);
        //设置主窗口的距离屏幕的偏移以及大小
        this.setBounds(500,100, this.getWidth(), this.getHeight());
        //设置窗口可见
        this.setVisible(true);
        //为了使俄罗斯方块美观，所以不能拖拉屏幕改变其大小
        this.setResizable(false);
        //设置一个标题
        this.setTitle("Tetris. Ocean");
        //布局采用自定义布局即可
        this.setLayout(null);
        //关闭了窗口，程序自然也要退出进程
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //设置游戏的主背景
        setBack();
        //设置前景，在背景之上
        //这个类是俄罗斯方块显示处的玻璃罩，以及白色矩形框区域
        //构造方法有遮罩透明度设置，俄罗斯方块的大小，以及白线的宽度，距离主JFrame的偏移
        getLayeredPane().add(SCORECANVAS = new ScoreCanvas(200, 60, 80, 25,0.0f, 290, 18));

        //下一个方块的画布
        getLayeredPane().add(nextBlocks = new NextBlocks(140, 140, 80, 25,0.0f, 290 + 30, 90));

        getLayeredPane().add(new MainCanvas( this.getWidth(), this.getHeight(), 30, 25,4.0f, 20, 20));//mX, mY是给边框的，bound偏移是0，0
        //这一个类是显示正在移动的俄罗斯方块的类
        //250 由于方块的像素大小是 25 * 25 像素， 横十竖二十
        //上移三个格子
        getLayeredPane().add(movingBlock = new MovingBlock(20, -55, 250,575, 25));
        getLayeredPane().add(blocksHeap = new BlocksHeap(20, -55, 250,575, 25, blockDates));
        //public ParticleCanvas(int width, int height, int blockSize, int mX, int mY)
        getLayeredPane().add(PARTICLECANVAS = new ParticleCanvas(WIDTH, HEIGHT, 25, 0, 0));
        //setScore();
        //autoDown.stop();

        //添加俄罗斯方块堆的JPanel的组件
        //addFakeButton();
        addFakerButton();
        this.addKeyListener(new PressKeyBoardToMoveBlocks(blockDates));//把全局键盘监听加入JFrame
        this.setFocusable(true);//设置键盘焦点,使主窗口又键盘监听的权力
        //加入暂停的玻璃遮罩
        getLayeredPane().add(SuspendCanvas.getInstance(),1);
        //加入排行榜

        autoDown = new AutoDown(blockDates);
        autoDown.start();
        //TODO 解决多线程的未知bug
        new AutoCalculatePos(this).start();
        addParticleTimer();

        playTheBackgroundMusic = new PlayTheBackgroundMusic();
        playTheBackgroundMusic.start();

        suspendGame = new SuspendGame();
        getLayeredPane().add(suspendGame);

        rankButton = new RankButton();
        getLayeredPane().add(rankButton);

        BackgroundMusicController backgroundMusicController = new BackgroundMusicController();
        getLayeredPane().add(backgroundMusicController);

        //getLayeredPane().add(rankingListCanvas = RankingListCanvas.getInstance(this,this), 1);

    }

    public RankButton getRankButton() { return rankButton; }

    private void addParticleTimer()
    {
        Timer timer = new Timer(8, new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                PARTICLECANVAS.repaint();
            }
        });
        timer.start();
    }

    private void setScorePicture()
    {
        ImageIcon newIcon = new ImageIcon(Explorer.getScore().getImage().getScaledInstance(153 / 2, 181 / 2, Image.SCALE_SMOOTH));
        JLabel score = new JLabel(newIcon);
        //score.setIcon(Explorer.getScore().getImage().getScaledInstance(153 / 2, 181 / 2, Image.SCALE_SMOOTH));
        score.setBounds(300,50,153 / 2,181 / 2);
        this.getLayeredPane().add(score);
    }

    private void addFakerButton()
    {
        ClickToDelete[][] clickToDelete = blockDates.getBlockColors();
        for(int i = 0; i < 23; i++)
            for(int j = 0; j < 10; j++)
            {
                blocksHeap.add(clickToDelete[i][j]);
            }
    }

    public static Playing getInstance()
    {
        synchronized (Playing.class)
        {
            if (null == instance)
            {
                EventQueue.invokeLater(() ->
                {
                    instance = new Playing(520, 650);
                });
            }
        }
        return instance;
    }

    @Override
    public void paintComponents(Graphics g) {
        super.paintComponents(g);
    }

    private void setBack()
    {
        JLabel background = new JLabel(Explorer.getBackground());
        background.setBounds(-100,-650,4096 / 2,2836 / 2);
        this.getContentPane().add(background);
    }

    public MovingBlock getMovingBlock() { return movingBlock; }

    @Override
    public int getWidth() { return WIDTH; }

    @Override
    public int getHeight() { return HEIGHT; }

    public static int getBlockSize() { return blockSize; }

    public BlocksHeap getBlocksHeap() { return blocksHeap; }

    public ParticleCanvas getPARTICLECANVAS() { return PARTICLECANVAS; }

    public static int getPHeight() { return pHeight; }

    public static int getPWidth() { return pWidth; }

    public ScoreCanvas getSCORECANVAS() { return SCORECANVAS; }

    public BlockDates getBlockDates() { return blockDates; }

    public NextBlocks getNextBlocks() { return nextBlocks; }

    public boolean getIsRunning(){ return isRunning; }

    public void suspendTheGame(){ isRunning = false; }

    public void assumeTheGame(){ isRunning = true; }

    public SuspendGame getSuspend() { return suspendGame; }

    public PlayTheBackgroundMusic getPlayTheBackgroundMusic() { return playTheBackgroundMusic; }

    //public void reserveTheState(){ isRunning = !isRunning; }

    public AutoDown getAutoDown() { return autoDown; }
}
