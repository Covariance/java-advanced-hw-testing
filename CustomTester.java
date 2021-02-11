package info.kgeorgiy.ja.фамилия.walk;

import info.kgeorgiy.java.advanced.walk.RecursiveWalkTest;
import info.kgeorgiy.java.advanced.walk.Tester;
import info.kgeorgiy.java.advanced.walk.WalkTest;

public class CustomTester extends Tester {

  public static void main(String[] args) {
    new CustomTester()
        .add("Custom", CustomTest.class)
        .add("Walk", WalkTest.class)
        .add("Recursive", RecursiveWalkTest.class)
        .run(args);
  }
}
