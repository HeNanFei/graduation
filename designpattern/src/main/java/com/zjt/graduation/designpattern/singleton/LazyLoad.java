package com.zjt.graduation.designpattern.singleton;

public class LazyLoad {
    public static volatile LazyLoad lazyLoad;

    public static  Object object = new Object();

    private LazyLoad(){

    }

    public static  LazyLoad lazyLoad() throws InterruptedException {
        synchronized (object) {
            if (lazyLoad == null) {
                Thread.sleep(1000L);
                lazyLoad = new LazyLoad();
            }
            return lazyLoad;
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                try {
                    System.out.println(LazyLoad.lazyLoad());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
