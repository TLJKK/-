package view;

import controller.UpButton;
import model.Explorer;
import model.GameSize;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameOVer extends JDialog
{
    private GameOVer dialog = null;
    private String out = "最终得分：";
    private JTextField in = null;

    public GameOVer(Frame owner, Component parentComponent, String score)
    {
        super(owner, true);
        this.setSize(350, 234);
        // 设置对话框大小不可改变
        this.setResizable(false);
        // 设置对话框相对显示的位置
        this.setLocationRelativeTo(parentComponent);
        //设置背景
        this.setUndecorated(true);

        // 创建对话框的内容面板, 在面板内可以根据自己的需要添加任何组件并做任意是布局
        JPanel panel = new JPanel();
        panel.setLayout(null);


        UpButton okBtn = new UpButton(this);
        okBtn.setBounds(125,176,114,35);
        panel.add(okBtn);

        //884 592
        JLabel bk = new JLabel(Explorer.getGameOver());
        panel.add(bk);
        bk.setBounds(-1, -1, 350, 234);


        out += score;
        dialog = this;
        initGUI(panel);

        // 设置对话框的内容面板
        this.setContentPane(panel);
        // 显示对话框
        this.setVisible(true);

    }

    private void initGUI(JPanel panel) {
        JLabel outTxt = new JLabel(out);
        outTxt.setFont(new Font("宋体", Font.BOLD,20));
        outTxt.setForeground(Color.BLACK);
        outTxt.setVisible(true);
        this.add(outTxt);
        outTxt.setBounds(50, 45, 850, 30);
        panel.add(outTxt, 0);

        JLabel name = new JLabel("记录创造者：");
        name.setFont(new Font("宋体", Font.BOLD,18));
        name.setForeground(Color.BLACK);
        name.setVisible(true);
        this.add(name);
        name.setBounds(50, 100, 150, 30);
        panel.add(name, 0);

        in = new JTextField();
        in.setFont(new Font("宋体", Font.BOLD,18));
        name.setForeground(Color.BLACK);
        in.setVisible(true);
        this.add(in);
        in.setBounds(160, 105, 150, 18);
        panel.add(in, 0);
    }

    public String getUserNme(){ return this.in.getText(); }
}
