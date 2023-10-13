package com.gzg.tankGame3;

/**
 * @brief 射击子弹
 */
public class Shot implements Runnable{
    private int x, y; //子弹坐标
    int direction; //子弹方向
    int speed = 5;  //子弹速度
    private boolean isLive = true;  //子弹存活情况
    

    public Shot(int x, int y, int direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }
    


    


    public int getX() {
        return x;
    }






    public int getY() {
        return y;
    }






    public boolean isLive() {
        return isLive;
    }






    public void setSpeed(int speed) {
        this.speed = speed;
    }

    @Override
    public void run() {
        while(true){
            try {
                Thread.sleep(50);
            } catch (Exception e) {
                e.printStackTrace();
            }
            //根据方向来改变x,y坐标
            switch (direction) {
                case 0://上
                    y -= speed;
                    break;
                case 1://右
                    x += speed;
                    break;
                case 2://下
                    y += speed;
                    break;
                case 3://左
                    x -= speed;
                    break;
            }
            System.out.println("子弹 x = " + x + ", y = " + y);
            if (!(x >= 0 && x <= 1000 && y >= 0 && y <= 750)) {
                System.out.println("子弹线程退出");
                isLive = false;
                break;
            }
        }
    }
}
