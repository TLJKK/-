package model;

import controller.ClickToDelete;
import view.GameOVer;
import view.Playing;
import view.SuspendCanvas;

import java.awt.*;

public class Move
{
    private BlockDates blockDates;
    private Point[] otherBlocks;
    private Playing playing;

    public Move(BlockDates blockDates, Playing playing)
    {
        this.blockDates = blockDates;
        this.otherBlocks = blockDates.getOtherBlocks().getExcursion();
        this.playing = playing;
    }

    //boolean 的作用是避免旋转死循环
    public boolean moveLeft()
    {
        ClickToDelete[][] now = blockDates.getBlockColors();
        for(int i = 0; i < 4; i++)
        {
            if (this.blockDates.getColumn() + this.otherBlocks[i].x <= 0) return false;
            if(this.blockDates.getLine() + this.otherBlocks[i].y >= 0 && this.blockDates.getLine() + this.otherBlocks[i].y < 23)
                if(this.blockDates.getColumn() + this.otherBlocks[i].x - 1 >= 0 && this.blockDates.getColumn() + this.otherBlocks[i].x - 1 < 10)
                    if(null != now[this.blockDates.getLine() + this.otherBlocks[i].y][this.blockDates.getColumn() + this.otherBlocks[i].x - 1].getThisBlock())
                        return false;
            //if()
        }
        this.blockDates.setColumn(this.blockDates.getColumn() - 1);
        testMoveDown();
        this.playing.getMovingBlock().repaint();
        return true;
    }

    public boolean moveRight()
    {
        ClickToDelete[][] now = blockDates.getBlockColors();
        for(int i = 0; i < 4; i++)
        {
            if (this.blockDates.getColumn() + this.otherBlocks[i].x >= 9) return false;
            if(this.blockDates.getLine() + this.otherBlocks[i].y >= 0 && this.blockDates.getLine() + this.otherBlocks[i].y < 23)
                if(this.blockDates.getColumn() + this.otherBlocks[i].x + 1 >= 0 && this.blockDates.getColumn() + this.otherBlocks[i].x + 1 < 10)
                    if(null != now[this.blockDates.getLine() + this.otherBlocks[i].y][this.blockDates.getColumn() + this.otherBlocks[i].x + 1].getThisBlock())
                        return false;
        }
        this.blockDates.setColumn(this.blockDates.getColumn() + 1);
        testMoveDown();
        this.playing.getMovingBlock().repaint();
        return true;
    }

    public void toTheEnd()//MoveDown调用直接到达底部
    {
        int lineNow = blockDates.getLine(), columnNow = blockDates.getColumn();//现在的行与列
        ClickToDelete[][] now = blockDates.getBlockColors();//现在矩阵的情况

        while(true)
        {
            for (int i = 0; i < 4; i++)
                if ((lineNow + this.otherBlocks[i].y >= 22 || // 最底边
                        (lineNow + this.otherBlocks[i].y + 1 >= 0 &&
                                lineNow + this.otherBlocks[i].y + 1 <= 22 &&
                                    columnNow + this.otherBlocks[i].x >= 0 &&
                                        columnNow + this.otherBlocks[i].x <= 9 &&
                                            null != now[lineNow + this.otherBlocks[i].y + 1][columnNow + this.otherBlocks[i].x].getThisBlock())))
                {
                    blockDates.setLine(lineNow);
                    blockDates.setColumn(columnNow);
                    blockDates.getMOVE().moveDown();
                    return;
                }
            lineNow++;
        }
        //this.blockDatas.setLine(this.blockDatas.getLine() + 1);
        //playing.getMovingBlock().repaint();
    }

    public void testMoveDown()
    {
        int lineNow = blockDates.getLine(), columnNow = blockDates.getColumn();//现在的行与列
        ClickToDelete[][] now = blockDates.getBlockColors();//现在矩阵的情况

        while(true)
        {
            for (int i = 0; i < 4; i++)
                if ((lineNow + this.otherBlocks[i].y >= 22 || // 最底边
                        (lineNow + this.otherBlocks[i].y + 1 >= 0 &&
                           lineNow + this.otherBlocks[i].y + 1 <= 22 &&
                             columnNow + this.otherBlocks[i].x >= 0 &&
                                columnNow + this.otherBlocks[i].x <= 9 &&
                                     null != now[lineNow + this.otherBlocks[i].y + 1][columnNow + this.otherBlocks[i].x].getThisBlock())))
                                     {
                                          blockDates.setVirtualLine(lineNow);
                                          blockDates.setVirtualColumn(columnNow);
                                          return;
                                     }
            lineNow++;
        }
        //this.blockDatas.setLine(this.blockDatas.getLine() + 1);
        //playing.getMovingBlock().repaint();
    }

    //返回true则下移成功， false则下移失败
    public void moveDown()
    {
        ClickToDelete[][] now = blockDates.getBlockColors();
        for(int i = 0; i < 4; i++)
            if((this.blockDates.getLine() + this.otherBlocks[i].y >= 22|| // 最底边
                    (blockDates.getLine() + this.otherBlocks[i].y + 1 >= 0 &&
                        blockDates.getLine() + this.otherBlocks[i].y + 1 <= 22 &&
                         blockDates.getColumn() + this.otherBlocks[i].x >= 0 &&
                            blockDates.getColumn() + this.otherBlocks[i].x <= 9 &&
                              null != now[blockDates.getLine() + this.otherBlocks[i].y + 1]
                                 [blockDates.getColumn() + this.otherBlocks[i].x].getThisBlock()))) {

                boolean isFulled = false;
                for(int j = 0; j < 4; j++)
                {
                    if(this.blockDates.getLine() + this.otherBlocks[j].y >= 22|| // 最底边
                            (blockDates.getLine() + this.otherBlocks[j].y >= 0 &&
                               blockDates.getLine() + this.otherBlocks[j].y <= 22 &&
                                 blockDates.getColumn() + this.otherBlocks[j].x >= 0 &&
                                    blockDates.getColumn() + this.otherBlocks[j].x <= 9)) {
                                        now[blockDates.getLine() + this.otherBlocks[j].y]
                                            [blockDates.getColumn() + this.otherBlocks[j].x].setThisBlock(blockDates.getBlockTexture());
                                                if(blockDates.addThisLine(blockDates.getLine() + this.otherBlocks[j].y))isFulled = true;//粒子特效添加在addThisLine
                    }
                }

                //若游戏未结束，则更新一下数据
                if(isFulled)
                {
                    blockDates.getDeleteData();
                    blockDates.forNxtScene();
                }

                //游戏结束
                if(blockDates.gameOver())
                {
                    //TODO gameOverScene
                    //System.out.println("game over!!!");
                    SuspendCanvas.getInstance().openThisDialog(true);
                    Playing.getInstance().suspendTheGame();
                    showCustomDialog(Playing.getInstance(), Playing.getInstance(), Playing.getInstance().getSCORECANVAS().getScore());
                    //public SuspenCanvas(int width, int height, int transparency, int blockSize, float lineSize, int mX, int mY)
//                    while(true){ if(Playing.getInstance().getIsRunning())break; }//直到点击开始
                    Playing.getInstance().getBlockDates().restartGame();
                    blockDates.init(true);
                    return;
                }

                blockDates.init(isFulled);
                blockDates.setVirtualColumn(blockDates.getColumn());
                blockDates.setVirtualLine(blockDates.getLine());
                testMoveDown();

                playing.getMovingBlock().repaint();
                return;
            }

        this.blockDates.setLine(this.blockDates.getLine() + 1);
        testMoveDown();
        playing.getMovingBlock().repaint();
    }

    private static void showCustomDialog(Frame owner, Component parentComponent, int score)
    {
        // 创建一个模态对话框
        final GameOVer dialog = new GameOVer(owner, parentComponent, String.format("%d", score));
    }

    public void setOtherBlocks(Point[] otherBlocks) { this.otherBlocks = otherBlocks; }
}
