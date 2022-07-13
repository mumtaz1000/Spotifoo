package Spotifoo.src;

import java.util.Scanner;

import static Spotifoo.src.Main.displayWarningMsg;
import static Spotifoo.src.Main.mainMenuSection;

public class User {
    protected static int validateUserInput(int minUserInput,int maxUserInput){
        Scanner menuOption = new Scanner(System.in);
        boolean correctInput = false;
        int input = 0;
        while (!correctInput){
            input = menuOption.nextInt();
            if(input == 0){
                mainMenuSection();
            } else {
                if (input > minUserInput && input <= maxUserInput) {
                    System.out.println(input);
                    correctInput = true;
                } else {
                    System.out.println(displayWarningMsg("Please enter the number between " + minUserInput + " to " + maxUserInput));
                }
            }
        }
        return input;
    }
}
