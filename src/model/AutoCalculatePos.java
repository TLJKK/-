package model;

import view.Playing;

public class AutoCalculatePos extends Thread
{
    private int delayTime;
    private Playing playing;
    //private BlockDates blockDates;

    public AutoCalculatePos(Playing playing)
    {
        this.playing = playing;
        this.delayTime = 14;
        //this.blockDates = blockDates;
    }

    @Override
    public void run()
    {
        //super.run();
        try { sleep(1000); } catch (InterruptedException e) { e.printStackTrace(); }//instance实例化没好会有多个窗口
        while(true)
        {
            if(Playing.getInstance().getIsRunning())ParticleGather.updateParticle();
            //playing.getParticleCanvas().repaint();
            try { sleep(delayTime); } catch (InterruptedException e) { e.printStackTrace(); }
        }
    }

    public void setDelayTime(int delayTime) { this.delayTime = delayTime; }
}
