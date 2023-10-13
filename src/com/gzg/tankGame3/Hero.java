package com.gzg.tankGame3;

public class Hero extends Tank {
    //定义一个Shot对象，表示一个射击行为
    private Shot shot = null;
    //射击
    public void shotEnemyTank(){
        //创建 Shot 对象, 根据当前Hero对象的位置和方向来创建Shot
        switch (getDirection()) {//得到Hero对象方向
            case 0: //向上
                shot = new Shot(getX() + 20, getY(), 0);
                break;
            case 1: //向右
                shot = new Shot(getX() + 60, getY() + 20, 1);
                break;
            case 2: //向下
                shot = new Shot(getX() + 20, getY() + 60, 2);
                break;
            case 3: //向左
                shot = new Shot(getX(), getY() + 20, 3);
                break;
        }
        //启动我们的Shot线程
        new Thread(shot).start();

    }

    public Hero(int x, int y, int direction) {
        super(x, y, direction);
    }

    public Shot getShot() {
        return shot;
    }

    
}
