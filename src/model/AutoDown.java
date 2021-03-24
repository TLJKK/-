package model;

import view.Playing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AutoDown extends Thread
{
    private int delayTime;
    private final BlockDates BLOCKDATES;

    public AutoDown(BlockDates BLOCKDATES)
    {
        this.delayTime = 1000;
        this.BLOCKDATES = BLOCKDATES;

        //TODO 使俄罗斯方块下落的速度随时间增加而增加
        new javax.swing.Timer(30000, new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if(Playing.getInstance().getIsRunning() && delayTime >= 200)delayTime -= 50;
            }
        }).start();
    }

    @Override
    public void run()
    {
        //super.run();
        try { sleep(1000); } catch (InterruptedException e) { e.printStackTrace(); }//instance没好的话会有多个窗口
        while(true)
        {
            if(Playing.getInstance().getIsRunning()) BLOCKDATES.getMOVE().moveDown();
            //blockDatas.getMove().testMoveDown();
            try { sleep(delayTime); } catch (InterruptedException e) { e.printStackTrace(); }
        }
    }

    public void setDelayTime(int delayTime) { this.delayTime = delayTime; }

    public void resetDelayTime(){ this.delayTime = 1000; }
}
