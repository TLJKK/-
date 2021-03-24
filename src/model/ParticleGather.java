package model;

import view.Playing;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class ParticleGather
{
    private static final List<FormParticle>allParticle = new LinkedList<>();

    public static List<FormParticle> getAllParticle() { return allParticle; }

    public static void addAParticle(BlockTexture texture, int width, int height, int life, int myX, int myY)
    {
        allParticle.add(new FormParticle(texture, width, height, life, myX, myY));
    }

    public static void updateParticle()
    {
        try
        {
            for (FormParticle formParticle : allParticle)
            {
                formParticle.forNextPos();
                //if(formParticle.getMyY() > 900) allParticle.remove(formParticle);
            }
        }catch (ConcurrentModificationException e)
        {
            System.out.println("未知错误！！！");
        }

        Iterator<FormParticle> iterator = allParticle.iterator();

        while(iterator.hasNext())
        {
            FormParticle formParticle = null;
            try {
                formParticle = iterator.next();
            }catch (ConcurrentModificationException e)
            {
                //System.out.println("未知错误！！!");
            }

            if(null == formParticle)return;
            if(formParticle.getMyY() > 900)
                iterator.remove();
            else formParticle.forNextPos();
        }


    }

}
