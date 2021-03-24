package model;

import java.util.*;

public class Enums
{
    private static int random = (int)(Math.random()*10);// 生成种子
    private static Random rand = new Random(random);

    public static <T extends Enum<T>> T random(Class<T> any)
    {
        return random(any.getEnumConstants());
    }

    public static <T> T random(T[] values)
    {
        return values[rand.nextInt(values.length)];
    }
}
