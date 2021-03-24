package controller;

import model.BlockDates;
import view.Playing;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PressKeyBoardToMoveBlocks implements KeyListener
{
    private BlockDates blockDates;

    public PressKeyBoardToMoveBlocks(BlockDates blockDates)
    {
        this.blockDates = blockDates;
        //this.setFocusable(true);
    }

    @Override
    public void keyTyped(KeyEvent e) { }

    @Override
    public void keyPressed(KeyEvent e)
    {
        if(27 == e.getKeyChar())Playing.getInstance().suspendTheGame();//esc暂停
        if(!Playing.getInstance().getIsRunning())return;
        switch (e.getKeyChar())
        {
            case 'A' :
            case 'a' : blockDates.getMOVE().moveLeft();  return;//左
            case 'D' :
            case 'd' : blockDates.getMOVE().moveRight(); return;//右
            case 'S' :
            case 's' : blockDates.getMOVE().moveDown();  return;//下
            case 'l' :
            case 'L' :
            case 'j' :
            case 'J' : blockDates.getROTATION().contrariety(); return;//逆时针
            case 'K' :
            case 'k' : blockDates.getROTATION().clockwiseRotation(); return;//顺时针
            case '\n':
            case ' ' :blockDates.getMOVE().toTheEnd();//直接到达底部
            //case 'l' :
            //case ' ' : blockDates.init();
            default  : ;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) { }
}
