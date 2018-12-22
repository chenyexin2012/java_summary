package com.holmes.composite;

import org.junit.Test;

public class CompositeTest {

    @Test
    public void compositeTest() {

        FileComposite file1 = new File("file1");
        FileComposite file2 = new File("file2");
        FileComposite file3 = new File("file3");
        FileComposite file4 = new File("file4");
        FileComposite file5 = new File("file5");
        FileComposite file6 = new File("file6");

        FileComposite folder1 = new Folder("folder1");
        folder1.addChild(file1);
        folder1.addChild(file2);
        folder1.addChild(file3);

        FileComposite folder2 = new Folder("folder2");
        folder2.addChild(folder1);
        folder1.addChild(file4);
        folder2.addChild(file5);

        FileComposite folder3 = new Folder("folder3");
        folder3.addChild(folder2);
        folder3.addChild(file6);

        folder3.display();
    }
}
