package com.holmes.memento;

/**
 * @Description: 备忘录的管理者
 * @Author: holmes
 * @Version: 1.0.0
 */
public class AdminCaretaker {

    private Memento memento;

    public Memento getMemento() {
        return memento;
    }

    public void setMemento(Memento memento) {
        this.memento = memento;
    }
}
