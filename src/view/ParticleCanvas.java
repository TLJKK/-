package view;

import model.FormParticle;
import model.ParticleGather;

import javax.swing.*;
import java.awt.*;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;

//粒子特效的画布
public class ParticleCanvas extends JPanel
{
    //相对JFrame的偏移
    private int mX, mY;
    private int blockSize;
    private int width, height;

    public ParticleCanvas(int width, int height, int blockSize, int mX, int mY)
    {
        this.width = width;
        this.height = height;
        this.blockSize = blockSize;
        this.mX = mX;
        this.mY = mY;

        this.setBounds(0,0, width, height);
        //设置背景透明
        this.setOpaque(false);
        //添加俄罗斯方块
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);//不透明因为这个,父类会为JPanel画上一层背景颜色
        Graphics2D g2 = (Graphics2D)g;

        List<FormParticle> now = ParticleGather.getAllParticle();

        Iterator<FormParticle> iterator = now.iterator();

        try
        {
            while (iterator.hasNext())
            {
                FormParticle formParticle = null;
                formParticle = iterator.next();
                if (null == formParticle) return;
                g2.drawImage(formParticle.getTexture().getIMAGEICON().getImage(),
                        formParticle.getMyX(), formParticle.getMyY(), formParticle.getWidth(), formParticle.getHeight(), null);
            }
        }catch (ConcurrentModificationException ignored)
        {
            //System.out.println("未知错误！！！");
        }
        catch (NullPointerException ignored)
        {

        }

    }
}
