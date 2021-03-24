package model;

import controller.ClickToDelete;
import view.Playing;

public class BlockDates
{
    //TODO 删除方块的想法：记入每一行的方块个数，当每一个方块的据加入二维数组时，若该行满了右10个块，
    // 则告诉此对象该消行了, for循环19次， 若改行满10， 则上面所有行下移行数加一

    private int line = 0, column = 4, virtualLine = 0, virtualColumn = 4;
    private BlockTexture blockTexture, nxtBlockTexture;
    private final Playing playing;
    private OtherBlocks otherBlocks, nxtOtherBlocks;
    private final Move MOVE;
    private final Rotation ROTATION;
    //private final BlockTexture[][] blockColors = new BlockTexture[23][10];
    private ClickToDelete[][] blockColors = new ClickToDelete[23][10];
    private int[] nxt;//俄罗斯方块堆每一行的下一个位置，每次init后重新new出来
    // 下一个俄罗斯方块
    private BlockStyles nxtBlockStyles = null;
    private final int[] QUERY;//对于游戏的二维数组的个数记录，若满十，则消除该行

    //顺序为从左到右， 从上到下
    private final static int[] leftZPx = {-1, 0, 0, 1}, rightZPx = {-1, 0, 0, 1}, leftLPx = {-1, 0, 1, 1};
    private final static int[] rightLPx = {-1, -1, 0, 1}, wordTX = {-1, 0, 0, 1}, squareX = {0, 0, 1, 1}, rectangleX = {-1, 0, 1, 2};
    private final static int[] leftZPy = {0, 0, 1, 1}, rightZPy = {1, 1, 0, 0}, leftLPy = {0, 0, 0, 1};
    private final static int[] rightLPy = {1, 0, 0, 0}, wordTY = {0, -1, 0, 0}, squareY = {0, 1, 0, 1}, rectangleY = {0, 0, 0, 0};
    //private Object ClickToDelete;

    public BlockDates(Playing playing)
    {
        this.playing = playing;
        this.QUERY = new int[23];
        otherBlocks = new OtherBlocks(squareX, squareY);
        nxtOtherBlocks = new OtherBlocks(squareX, squareY);
        this.nxtBlockTexture = Enums.random(BlockTexture.class);
        this.MOVE = new Move(this, playing);
        this.ROTATION = new Rotation(this, playing, MOVE);
        for(int i = 0; i < 23; i++)
            for(int j = 0; j < 10; j++)
            {
                //ClickToDelete(BlockDates blockDates, int myLine, int myColumn)
                blockColors[i][j] = new ClickToDelete(this, i, j, null);
                //this.add(clickToDelete[i][j],  BorderLayout.CENTER);
            }
        /*为了不让多个对象访问Playing单例产生多个窗口*/
        this.nxtBlockStyles = Enums.random(BlockStyles.class);
        BlockStyles now = nxtBlockStyles;
        nxtBlockStyles = Enums.random(BlockStyles.class);//BlockStyles.SQUARE;
        this.blockTexture = this.nxtBlockTexture;
        this.nxtBlockTexture = Enums.random(BlockTexture.class);
        resetNxt();
        this.setLine(0);
        this.setColumn(4);
        if(null != this.otherBlocks)this.otherBlocks.setPoints(now.getAli());
        if(null != this.nxtOtherBlocks)this.nxtOtherBlocks.setPoints(nxtBlockStyles.getAli());
        MOVE.setOtherBlocks(this.otherBlocks.getExcursion());
        //this.addKeyListener(new MoveBlocks(this));
    }

    //public ClickToDelete[][] getBlockColorss() { return blockColorss; }

    public void init(boolean isFull)
    {
        if(!isFull)new IsDownMusic().start();
        BlockStyles now = nxtBlockStyles;
        nxtBlockStyles = Enums.random(BlockStyles.class);//BlockStyles.SQUARE;
        this.blockTexture = this.nxtBlockTexture;
        this.nxtBlockTexture = Enums.random(BlockTexture.class);
        if(isFull)resetNxt();
        this.setLine(0);
        this.setColumn(4);
        if(null != this.otherBlocks)this.otherBlocks.setPoints(now.getAli());
        if(null != this.nxtOtherBlocks)this.nxtOtherBlocks.setPoints(nxtBlockStyles.getAli());
        MOVE.setOtherBlocks(this.otherBlocks.getExcursion());

        if(null != Playing.getInstance() && null != Playing.getInstance().getNextBlocks())Playing.getInstance().getNextBlocks().repaint();
    }

    public void getDeleteData()//boolean
    {
       //TODO 加入boolean返回值，为下一个场景的判断提速；//外面已经有判断的了
        int line = 0;
        for(int i = 22; i >= 2; i--)//二一定为空， 三不一定为空， 所以把二传递下去保险
        {
            if(10 == QUERY[i]){ nxt[i - 1] = nxt[i] + 1; ++line;}
            else nxt[i - 1] = nxt[i];
        }
        if(0 != line)Playing.getInstance().getSCORECANVAS().addScore(line * 100 + (line - 1) * line * 20);
    }

    public void forNxtScene()
    {
        new GetScoreMusic().start();
        for (int i = 22; i >= 2 ; i--)
            if (0 != nxt[i])
            {
                for (int j = 0; j <= 9; j++)
                    this.blockColors[i + nxt[i]][j].setThisBlock(this.blockColors[i][j].getThisBlock());
                this.QUERY[i + nxt[i]] = this.QUERY[i];
            }
    }

    public void resetNxt()
    {
        this.nxt = new int[23 + 7];
    }

    boolean gameOver()
    {
        for(int i = 0; i <= 9; i++)
            if(null != this.blockColors[2][i].getThisBlock())return true;
        return false;
    }

    public void restartGame()
    {
        //null != now[i][j].getThisBlock()
        //this.query = new int[23];
        for(int i = 0; i < 23; i++)this.QUERY[i] = 0;
        Playing.getInstance().getSCORECANVAS().resetScore();
        Playing.getInstance().getAutoDown().resetDelayTime();
    }

    public ClickToDelete[][] getBlockColors() { return blockColors; }

    public void setBlockTexture(BlockTexture blockTexture) { this.blockTexture = blockTexture; }

    public void setOtherBlocks(OtherBlocks otherBlocks) { this.otherBlocks = otherBlocks; }

    public int getColumn() { return column; }

    public void setColumn(int column) { this.column = column; }

    public int getLine() { return line; }

    public void setLine(int line) { this.line = line; }

    public OtherBlocks getOtherBlocks() { return otherBlocks; }

    public Move getMOVE() { return MOVE; }

    public Rotation getROTATION() { return ROTATION; }

    public static int[] getLeftZPx() { return leftZPx; }

    public static int[] getRightZPx() { return rightZPx; }

    public static int[] getLeftLPx() { return leftLPx; }

    public static int[] getRightLPx() { return rightLPx; }

    public static int[] getWordTX() { return wordTX; }

    public static int[] getSquareX() { return squareX; }

    public static int[] getRectangleX() { return rectangleX; }

    public static int[] getLeftZPy() { return leftZPy; }

    public static int[] getRightZPy() { return rightZPy; }

    public static int[] getLeftLPy() { return leftLPy; }

    public static int[] getRightLPy() { return rightLPy; }

    public static int[] getWordTY() { return wordTY; }

    public static int[] getSquareY() { return squareY; }

    public static int[] getRectangleY() { return rectangleY; }

    //public BlockTexture[][] getBlockColors() { return blockColors; }

    public BlockTexture getBlockTexture() { return blockTexture; }

    public int getVirtualColumn() { return virtualColumn; }

    public int getVirtualLine() { return virtualLine; }

    public void setVirtualLine(int virtualLine) { this.virtualLine = virtualLine; }

    public void setVirtualColumn(int virtualColumn) { this.virtualColumn = virtualColumn; }

    public boolean addThisLine(int line)
    {
        boolean isFulled = false;
        if(10 == ++ QUERY[line])isFulled = true;

        if(isFulled)
        {
            FormParticle.setRandomForm();
            for (int j = 0; j <= 9; j++)
                this.blockColors[line][j].addParticulars25(this.blockColors[line][j].getThisBlock());
        }

        return isFulled;
    }

    public void subThisLine(int line){ QUERY[line]--; }

    public Playing getPlaying() { return playing; }

    //获得下一个方块的数据
    public BlockStyles getNxtBlockStyles() { return nxtBlockStyles; }

    public OtherBlocks getNxtOtherBlocks() { return nxtOtherBlocks; }

    public BlockTexture getNxtBlockTexture() { return nxtBlockTexture; }
}