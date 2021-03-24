package view;

import controller.UpButton;
import model.Explorer;
import model.GameIsOverAndWriteToMySqlAndReadData;
import model.GameSize;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RankingListCanvas extends JDialog
{
    private JPanel panel = null;

    public RankingListCanvas(Frame owner, Component parentComponent)
    {
        super(owner, true);
        this.setSize(440, 528);
        // 设置对话框大小不可改变
        this.setResizable(false);
        // 设置对话框相对显示的位置
        this.setLocationRelativeTo(parentComponent);
        //设置背景
        this.setUndecorated(true);

        // 创建对话框的内容面板, 在面板内可以根据自己的需要添加任何组件并做任意是布局
        panel = new JPanel();
        panel.setLayout(null);

        UpButton okBtn = new UpButton(this);
        okBtn.setBounds(300,465,100,40);
        panel.add(okBtn);

        JLabel tmp = new JLabel("排行榜");
        tmp.setFont(new Font("微软雅黑", Font.BOLD,22));
        tmp.setForeground(Color.BLACK);
        tmp.setBounds(340, -2,114,35);
        panel.add(tmp);
        //884 592
        JLabel bk = new JLabel(Explorer.getRankingList());

        initUI(panel);

        panel.add(bk);
        bk.setBounds(-41, -45, 526, 644);


        // 设置对话框的内容面板
        this.setContentPane(panel);
        // 显示对话框
        this.setVisible(true);

    }

    private void initUI(JPanel panel)
    {
        new GameIsOverAndWriteToMySqlAndReadData().readDataToTheRankingList(this);
    }

    public JPanel getPanel() { return panel; }
}
