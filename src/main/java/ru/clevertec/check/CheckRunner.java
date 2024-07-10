package main.java.ru.clevertec.check;

import main.java.ru.clevertec.check.controller.MainController;

public class CheckRunner {
    public static void main(String[] args) {
        MainController.getMainControllerInstance().run(args);
    }
}