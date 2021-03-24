package controller;

import view.Playing;
import java.awt.*;

//TODO 3000
// 将Explorer声明为抽象
public class Director
{
    public static void main(String[] args)
    {
        //开启线程，载入窗口
        //new 出一个JFrame即可， 接下来的全局监听写在JFrame中
        EventQueue.invokeLater(Playing::getInstance);
        //EventQueue.invokeLater(Playing::getInstance);
    }
}
