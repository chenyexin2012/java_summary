package com.holmes.memento;

import org.junit.Test;

public class MementoTest {

    @Test
    public void mementoTest() {

        AdminCaretaker adminCaretaker = new AdminCaretaker();

        GamerOriginator gamer = new GamerOriginator("gamer", 1, 1000);
        // 保存当前记录
        adminCaretaker.setMemento(gamer.saveMemento());

        gamer.setLp(0);
        gamer.setLevel(10);

        System.out.println(gamer);
        // 恢复至备份状态
        gamer.recovery(adminCaretaker.getMemento());
        System.out.println(gamer);
    }
}
