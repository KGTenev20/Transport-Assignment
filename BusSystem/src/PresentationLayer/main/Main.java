package PresentationLayer.main;

import PresentationLayer.menus.MainMenu;
import PresentationLayer.menus.WelcomeMenu;

public class Main {
    public static void main(String[] args) {
        WelcomeMenu welcomeMenu = new WelcomeMenu();
        MainMenu mainMenu = new MainMenu();
        welcomeMenu.start();

        try {
            welcomeMenu.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        mainMenu.start();
    }
}