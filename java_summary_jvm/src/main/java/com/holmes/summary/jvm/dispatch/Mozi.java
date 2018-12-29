package com.holmes.summary.jvm.dispatch;

public class Mozi {
    
    public void ride(Horse h){
        System.out.println("骑马");
    }
    
    public void ride(WhiteHorse wh){
        System.out.println("骑白马");
    }
    
    public void ride(BlackHorse bh){
        System.out.println("骑黑马");
    }
}