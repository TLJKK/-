package model;

import view.GameOVer;
import view.RankingListCanvas;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

//写在前面：
//TODO 该类必须且只能调用writeName | toFace
// JDBC(Java DataBase Connectivity,java数据库连接)是一种用于执行SQL语句的Java API，
// 可以为多种关系数据库提供统一访问，它由一组用Java语言编写的类和接口组成。JDBC提供了一种基准，
// 据此可以构建更高级的工具和接口，使数据库开发人员能够编写数据库应用程序，同时，JDBC也是个商标名。

public class GameIsOverAndWriteToMySqlAndReadData
{
    private Connection con = null;
    //驱动程序名
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    //URL指向要访问的数据库名《俄罗斯方块分数排行榜》
    private static final String URL = "jdbc:mysql://localhost:3306/俄罗斯方块分数排行榜";
    //MySQL配置时的用户名
    private static final String USER = "root";
    //MySQL配置时的密码
    private static final String PASSWORD = "toor";

    private GameOVer gameOVer;

    private int score;

    public GameIsOverAndWriteToMySqlAndReadData()
    {
        this(null, -1);
    }

    public GameIsOverAndWriteToMySqlAndReadData(GameOVer gameOVer, int score)
    {
        this.score = score;
        this.gameOVer = gameOVer;
        try
        {
            //加载驱动程序
            Class.forName(DRIVER);
            //1.getConnection()方法，连接MySQL数据库！！
            con = DriverManager.getConnection(URL, USER, PASSWORD);
            if (!con.isClosed())
                System.out.println("驱动已加载成功！！!");
            //2.创建statement类对象，用来执行SQL语句！！
        } catch (ClassNotFoundException e) {
            //数据库驱动类异常处理
            System.out.println("Sorry,can`t find the Driver!");
            e.printStackTrace();
        } catch (Exception e) {
            //数据库连接失败异常处理
            e.printStackTrace();
        }// TODO: handle exception
        finally {
            System.out.println("尝试连接至[jdbc:mysql://localhost:3306/俄罗斯方块分数排行榜]，请稍候！！");
        }
    }

    public void readDataToTheRankingList(RankingListCanvas rankingListCanvas)
    {
        int yRay = 40;
        try
        {
            Statement statement = con.createStatement();

            String sql = "SELECT * FROM `data` ORDER BY `分数` DESC";
            //3.ResultSet类，用来存放获取的结果集！！
            ResultSet rs = statement.executeQuery(sql);

            int idx = 1, num = 0, preScore = -1, nowScore = -1;
            boolean hasDate = false;
            while(rs.next())
            {
                hasDate = true;
                num++;
                nowScore = rs.getInt("分数");
                if(1 == num)preScore = nowScore;
                if(preScore != nowScore) { idx++; preScore = nowScore; }
                JLabel tmp = new JLabel("No." + String.format("%02d：" + "id(" +
                        (("".equals(rs.getString("用户")))
                                ? "null" :rs.getString("用户")  )+ ")", idx));

                tmp.setBounds(20,yRay, rankingListCanvas.getWidth(),28);
                tmp.setFont(new Font("微软雅黑", Font.BOLD,20));
                tmp.setForeground(Color.BLACK);

                JLabel tmp1 = new JLabel("分数(" + nowScore + ")");
                tmp1.setBounds(280,yRay, rankingListCanvas.getWidth(),28);
                tmp1.setFont(new Font("微软雅黑", Font.BOLD,20));
                tmp1.setForeground(Color.BLACK);

                rankingListCanvas.getPanel().add(tmp1);
                rankingListCanvas.getPanel().add(tmp);
                yRay += 41;
                if(10 == num)break;
            }

            if(hasDate)idx++;
            for(; num < 10; num++)
            {
                JLabel tmp = new JLabel("No." + String.format("%02d：", idx++) + "id(null)");
                tmp.setBounds(20,yRay, rankingListCanvas.getWidth(),28);
                tmp.setFont(new Font("微软雅黑", Font.BOLD,20));
                tmp.setForeground(Color.BLACK);
                rankingListCanvas.getPanel().add(tmp);

                JLabel tmp1 = new JLabel("分数(" + "null" + ")");
                tmp1.setBounds(280,yRay, rankingListCanvas.getWidth(),28);
                tmp1.setFont(new Font("微软雅黑", Font.BOLD,20));
                tmp1.setForeground(Color.BLACK);
                rankingListCanvas.getPanel().add(tmp1);
                yRay += 41;
            }


            rs.close();
        } catch (Exception e) {
            //数据库连接失败异常处理
            e.printStackTrace();
        }
        finally {
            try {
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            System.out.println("读取数据至排行榜已完成！！！");
        }

    }


    public void writeName()//游戏结束后调用的函数，写入MySql，不管有没有进入前十名，并且执行删除十名以后的数据
    {
        PreparedStatement psql = null;
        ResultSet res = null;

        try
        {
            int nowMAxScore = -1;
            if(-1 == (nowMAxScore = searchData(gameOVer.getUserNme())))//若库中已经有了该ID，则赋值为最高分
            {
                psql = con.prepareStatement("INSERT INTO `俄罗斯方块分数排行榜`.`data` (`用户`, `分数`)VALUES(?,?)");
                psql.setString(1, gameOVer.getUserNme());              //设置参数1，创建id为3212的数据
                psql.setInt(2, score);
                psql.executeUpdate();           //执行更新
            }
            else if(nowMAxScore < score) updateMySql(gameOVer.getUserNme(), score);
            else System.out.println("用户ID已存在，只取历史最高！！！");
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        finally {
            try {
                System.out.println("写入数据库成功！！!");
                deleteTheDataWhereScoreIsNotINTen();//每次写入都会删除前十名之外的数据
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

    }

    public void updateMySql(String name, int score)
    {
        PreparedStatement psql;
        //预处理更新（修改）数据
        try {
            //UPDATE `data` SET `分数` = '289' WHERE `用户` = 'tlj'
            psql = con.prepareStatement("UPDATE `data` SET `分数` = ? WHERE `用户` = ?");
            psql.setInt(1, score);
            psql.setString(2,name);
            psql.executeUpdate();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }finally {
            System.out.println("历史新高！！");
        }

    }

    public int searchData(String name)
    {
        int flag = -1;
        try
        {
            Statement statement = con.createStatement();
            //要执行的SQL语句
            String sql = "SELECT * FROM `data` WHERE `用户` = '" + name+ "'" + "ORDER BY `分数` DESC;";
            //3.ResultSet类，用来存放获取的结果集！！
            ResultSet rs = statement.executeQuery(sql);
            if(rs.next())flag = rs.getInt("分数");
            rs.close();
        } catch (Exception e) {
            //数据库连接失败异常处理
            e.printStackTrace();
        }// TODO: handle exception
        finally {
            System.out.println("查询目标数据已完成！！");
        }
        return flag;
    }

    public void deleteTheDataWhereScoreIsNotINTen()
    {
        try
        {
            Statement statement = con.createStatement();
            //要执行的SQL语句
            //SELECT * FROM `data`
            //ORDER BY `分数` DESC;SELECT * FROM `data` ORDER BY `分数` DESC;
            //SELECT * FROM `data` ORDER BY `分数` DESC LIMIT 9,1
            String sql = "SELECT * FROM `data` ORDER BY `分数` DESC LIMIT 9,1";
            //ResultSet类，用来存放获取的结果集！！
            ResultSet rs = statement.executeQuery(sql);


            if(rs.next())
            {
                PreparedStatement psql;
                int score = -1;
                score = rs.getInt("分数");
                psql = con.prepareStatement("DELETE FROM `data` WHERE `data`.`分数`< ?");
                psql.setInt(1, score);
                psql.executeUpdate();
            }


            rs.close();
        } catch (Exception e) {
            //数据库连接失败异常处理
            e.printStackTrace();
        }
        finally {
            System.out.println("数据删除成功， 仅保留前十名！！！");
        }
    }

}
