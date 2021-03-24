package model;

import javax.swing.*;

public enum BlockTexture
{
    PINK(Explorer.getPinkBlock()),
    BLUE(Explorer.getBlueBlock()),
    YELLOW(Explorer.getYellowBlock()),
    GREEN(Explorer.getGreenBlock()),
    PURPLE(Explorer.getPurpleBlock())
    ;

    BlockTexture(ImageIcon IMAGEICON)
    {
        this.IMAGEICON = IMAGEICON;
    }

    private final ImageIcon IMAGEICON;
    public ImageIcon getIMAGEICON() { return IMAGEICON; }
}
