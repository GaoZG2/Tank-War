package com.gzg.tankGame3;

import javax.swing.*;

public class GzgTankGame03 extends JFrame {
    //定义MyPanel
    private MyPanel mp = null;
    public static void main(String[] args) throws InterruptedException {
        GzgTankGame03 gzgTankGame01 = new GzgTankGame03();
    }

    public GzgTankGame03(){
        mp = new MyPanel();
        //将mp 放入到Thread ,并启动
        Thread thread = new Thread(mp);
        thread.start();
        this.add(mp);
        this.addKeyListener(mp);//让JFrame监听键盘事件
        this.setSize(1000, 750);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
