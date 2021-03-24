package Test;

import model.Explorer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TestDialog {

    public static void main(String[] args) throws Exception {
        final JFrame jf = new JFrame("测试窗口");
        jf.setSize(800, 800);
        jf.setLocationRelativeTo(null);
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JButton btn = new JButton("显示自定义对话框");
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showCustomDialog(jf, jf);
            }
        });

        JPanel panel = new JPanel();
        panel.add(btn);

        jf.setContentPane(panel);
        jf.setVisible(true);
    }


    static class myDiaLog extends JDialog
    {
        myDiaLog(Frame owner, Component parentComponent)
        {
            super(owner, true);
        }
    }

    private static void showCustomDialog(Frame owner, Component parentComponent) {
        // 创建一个模态对话框
        final myDiaLog dialog = new myDiaLog(owner, parentComponent);
        // 设置对话框的宽高
        dialog.setSize(250, 150);
        // 设置对话框大小不可改变
        dialog.setResizable(false);
        // 设置对话框相对显示的位置
        dialog.setLocationRelativeTo(parentComponent);

        dialog.setUndecorated(true);

        // 创建一个标签显示消息内容
        JLabel messageLabel = new JLabel(Explorer.getBackground());

        // 创建一个按钮用于关闭对话框
        JButton okBtn = new JButton("确定");
        okBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 关闭对话框
                dialog.dispose();
            }
        });

        // 创建对话框的内容面板, 在面板内可以根据自己的需要添加任何组件并做任意是布局
        JPanel panel = new JPanel();

        // 添加组件到面板
        panel.add(messageLabel);
        panel.add(okBtn);

        // 设置对话框的内容面板
        dialog.setContentPane(panel);
        // 显示对话框
        dialog.setVisible(true);
    }

}