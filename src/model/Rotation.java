package model;

import controller.ClickToDelete;
import view.Playing;

import java.awt.*;

//TODO 旋转公式：x = -y , y = x
//  xNow = x cos(b) – y sin(b)    (1.3)
//  yNow = x sin(b) + y cos(b)    (1.4)

public class Rotation
{
    private BlockDates blockDates;
    private Playing playing;
    private Move move;

    Rotation(BlockDates blockDates, Playing playing, Move move)
    {
        this.move = move;
        this.blockDates = blockDates;
        this.playing = playing;
    }

    public void clockwiseRotation()
    {
        // x = -y , y = x
        Point[] now = blockDates.getOtherBlocks().getExcursion();//Excursion 偏移
        //Point[] ret = new Point[4];
        for (int i = 0; i < 4; i++)
        {
            int testX = -now[i].y + blockDates.getColumn();
            int testY = now[i].x + blockDates.getLine();

            while(testX < 0)//不合法则右移 x轴在列上
            {
                if(!this.move.moveRight())return;
                testX =  -now[i].y + blockDates.getColumn();
            }
            while(testX > 9)//不合法则左移
            {
                if(!this.move.moveLeft())return;
                testX =  -now[i].y + blockDates.getColumn();
            }
            if(testY > 22)return;
            ClickToDelete[][] nowData = blockDates.getBlockColors();
            if(testY >= 0 && null != nowData[testY][testX].getThisBlock())return;
        }

        for(int i = 0; i < 4; i++)
        {
            int tmp = now[i].x;
            now[i].x = -now[i].y;
            now[i].y = tmp;
        }
        blockDates.getMOVE().testMoveDown();
        playing.getMovingBlock().repaint();
        //blockDatas.setOtherBlocks(new OtherBlocks(ret));
    }

    public void contrariety()//到最后一层不能旋转的原因是因为JPanel给提了上去， 画得就上去了
    {
        Point[] now = blockDates.getOtherBlocks().getExcursion();
        //x = y y = - x

        for (int i = 0; i < 4; i++)
        {
            int testX = now[i].y + blockDates.getColumn();
            int testY = -now[i].x + blockDates.getLine();
            while(testX < 0)
            {
                if(!this.move.moveRight())return;
                testX =  now[i].y + blockDates.getColumn();
            }
            while(testX > 9)
            {
                if(!this.move.moveLeft())return;
                testX =  now[i].y + blockDates.getColumn();
            }
            if(testY > 22)return;
            ClickToDelete[][] nowData = blockDates.getBlockColors();
            if(testY >= 0 && null != nowData[testY][testX].getThisBlock())return;
        }

        for(int i = 0; i < 4; i++)
        {
            int tmp = now[i].x;
            now[i].x = now[i].y;
            now[i].y = -tmp;
        }
        blockDates.getMOVE().testMoveDown();
        playing.getMovingBlock().repaint();
    }



}
