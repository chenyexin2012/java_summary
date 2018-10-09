package com.holmes.learn.nio;

import java.nio.file.Path;
import java.nio.file.Paths;

public class PathDemo {

    public static void main(String[] args) {

        Path path = Paths.get("G://dongqiudi/..");

        System.out.println(path.toAbsolutePath());

        path = path.normalize();

        System.out.println(path.toAbsolutePath());
    }
}
