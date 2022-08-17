package com.demo.zzy.design.observe;

import java.util.Observable;
import java.util.Observer;

/**
 * @author zhangzhongyuan@szanfu.cn
 * @description
 * @since 2022/8/17 14:53
 */
public class People implements Observer {

    private String name;

    public People(String name) {
        this.name = name;
    }
    /**
     * This method is called whenever the observed object is changed. An
     * application calls an <tt>Observable</tt> object's
     * <code>notifyObservers</code> method to have all the object's
     * observers notified of the change.
     *
     * @param o   the observable object.
     * @param arg an argument passed to the <code>notifyObservers</code>
     */
    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof Float) {
            System.out.println(this.name + "观察到价格更改为" + arg);
        }
    }
}
