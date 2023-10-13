package com.gzg.tankGame3;


import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

/**
 * 坦克大战绘图区域
 */
public class MyPanel extends JPanel implements KeyListener, Runnable {
    //定义我的坦克
    Hero hero = null;
    //定义敌方坦克
    Vector<Enemy> enemys = new Vector<>();


    public MyPanel() {
        hero = new Hero(100, 100, 0);
        int enemyTankSize = 3;
        for (int i = 0; i < enemyTankSize; i++) {
            enemys.add(new Enemy(i * 80 + 20, 0, 2));
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.fillRect(0, 0, 1000, 750); //填充矩形，默认为黑色
        
        //画出自己的坦克
        drawTank(hero.getX(), hero.getY(), g, hero.getDirection(), 1);

        //画出敌方坦克
        for(Enemy ele: enemys){
            drawTank(ele.getX(), ele.getY(), g, ele.getDirection(), 0);
        }

        //画出hero射击的子弹
        if(hero.getShot() != null && hero.getShot().isLive() == true) {
            System.out.println("子弹被绘制...");
            g.draw3DRect(hero.getShot().getX(), hero.getShot().getY(), 1, 1, false);
        }
    }

    //编写方法画出坦克

    /**
     *
     * @param x 坦克左上角x坐标
     * @param y 坦克左上角y坐标
     * @param g 画笔
     * @param direction 坦克方向（上下左右）
     * @param type  坦克类型
     */
    public void drawTank(int x, int y, Graphics g, int direction, int type){
        //不同类型的坦克设置不同的颜色
        switch(type){
            case 0: //敌人的坦克
                g.setColor(Color.cyan);
                break;
            case 1: //我的坦克
                g.setColor(Color.yellow);
                break;
        }
        //根据坦克的方向绘制对应形状坦克
        //direction 表示方向（0：向上 1：向右 2：向下 3：向左）
        switch(direction){
            case 0: //表示向上
                g.fill3DRect(x, y, 10, 60, false);//画出坦克左边轮子
                g.fill3DRect(x + 30, y, 10, 60, false);//画出坦克右边轮子
                g.fill3DRect(x + 10, y + 10, 20, 40, false);//画出坦克盖子
                g.fillOval(x + 10, y + 20, 20, 20);//画出圆形盖子
                g.drawLine(x + 20, y + 30, x + 20, y);//画出炮筒
                break;
            case 1: //表示向右
                g.fill3DRect(x, y, 60, 10, false);//画出坦克上边轮子
                g.fill3DRect(x, y + 30, 60, 10, false);//画出坦克下边轮子
                g.fill3DRect(x + 10, y + 10, 40, 20, false);//画出坦克盖子
                g.fillOval(x + 20, y + 10, 20, 20);//画出圆形盖子
                g.drawLine(x + 30, y + 20, x + 60, y + 20);//画出炮筒
                break;
            case 2: //表示向下
                g.fill3DRect(x, y, 10, 60, false);//画出坦克左边轮子
                g.fill3DRect(x + 30, y, 10, 60, false);//画出坦克右边轮子
                g.fill3DRect(x + 10, y + 10, 20, 40, false);//画出坦克盖子
                g.fillOval(x + 10, y + 20, 20, 20);//画出圆形盖子
                g.drawLine(x + 20, y + 30, x + 20, y + 60);//画出炮筒
                break;
            case 3: //表示向左
                g.fill3DRect(x, y, 60, 10, false);//画出坦克上边轮子
                g.fill3DRect(x, y + 30, 60, 10, false);//画出坦克下边轮子
                g.fill3DRect(x + 10, y + 10, 40, 20, false);//画出坦克盖子
                g.fillOval(x + 20, y + 10, 20, 20);//画出圆形盖子
                g.drawLine(x + 30, y + 20, x, y + 20);//画出炮筒
                break;
            default:
                System.out.println("暂时不处理");
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W){
            hero.setDirection(0);
            hero.moveUp();
            this.repaint();
        }else if(e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D){
            hero.setDirection(1);
            hero.moveRight();
            this.repaint();
        }else if(e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S){
            hero.setDirection(2);
            hero.moveDown();
            this.repaint();
        }else if(e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A){
            hero.setDirection(3);
            hero.moveLeft();
            this.repaint();
        }

        //如果用户按下J， 则发射子弹
        if(e.getKeyCode() == KeyEvent.VK_J){
            System.out.println("用户发射了一个子弹");
            hero.shotEnemyTank();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void run() { //每隔 100毫秒，重绘区域, 刷新绘图区域, 子弹就移动

        while (true) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.repaint();
        }

    }
}
