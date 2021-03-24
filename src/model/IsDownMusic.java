package model;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import javax.sound.sampled.*;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

public class IsDownMusic extends Thread
{

    @Override
    public void run()
    {

        try
        {
            //声明一个File对象
            File mp3 = new File("resources/isDown.mp3");

            //创建一个输入流
            FileInputStream fileInputStream = new FileInputStream(mp3);

            //创建一个缓冲流
            BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);

            //创建播放器对象，把文件的缓冲流传入进去
            Player getScore = new Player(bufferedInputStream);

            getScore.play();

        } catch (Exception e) {
            System.out.println("初始化俄罗斯方块音乐加载异常！！！");
        }
        //System.out.println("musicEnd!!");
    }

}
