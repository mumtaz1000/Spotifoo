package Spotifoo.src;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
    protected static void userSearchSongInput(){
        Songs songObj = new Songs();
        List<String> displayName = songObj.songsNames;
        Scanner searchSongInput = new Scanner(System.in);
        List<String> requiredList = new ArrayList<>();
        System.out.println("Enter the song to search ");
        String userInput = searchSongInput.nextLine();
        try{
            if(userInput.equals("0")){
                mainMenuSection();
            } else {
                System.out.println("You entered "+userInput);
                for (String name: songObj.songsNames) {
                    System.out.println("Song searching "+name);

                    List<Integer> foundIndexes = performKMPSearch(name, userInput);

                    if (foundIndexes.isEmpty()) {
                        System.out.println("Pattern not found in the given text String");
                    } else {
                        System.out.println("Pattern found in the given text String at positions: ");
                    }


                    if(name.contains(userInput)){
                        requiredList.add(name);
                        System.out.println("Song name "+name);
                    }
                }

            }
        }
        catch(Exception e){
            System.out.println(displayWarningMsg("Please enter the valid input"));
        }
    }
    public static int[] compilePatternArray(String pattern) {
        int patternLength = pattern.length();
        int len = 0;
        int i = 1;
        int[] compliedPatternArray = new int[patternLength];
        compliedPatternArray[0] = 0;

        while (i < patternLength) {
            if (pattern.charAt(i) == pattern.charAt(len)) {
                len++;
                compliedPatternArray[i] = len;
                i++;
            } else {
                if (len != 0) {
                    len = compliedPatternArray[len - 1];
                } else {
                    compliedPatternArray[i] = len;
                    i++;
                }
            }
        }
        System.out.println("Compiled Pattern Array " + Arrays.toString(compliedPatternArray));
        return compliedPatternArray;
    }
    public static List<Integer> performKMPSearch(String text, String pattern) {
        int[] compliedPatternArray = compilePatternArray(pattern);

        int textIndex = 0;
        int patternIndex = 0;

        List<Integer> foundIndexes = new ArrayList<>();

        while (textIndex < text.length()) {
            if (pattern.charAt(patternIndex) == text.charAt(textIndex)) {
                patternIndex++;
                textIndex++;
            }
            if (patternIndex == pattern.length()) {
                foundIndexes.add(textIndex - patternIndex);
                patternIndex = compliedPatternArray[patternIndex - 1];
            }

            else if (textIndex < text.length() && pattern.charAt(patternIndex) != text.charAt(textIndex)) {
                if (patternIndex != 0)
                    patternIndex = compliedPatternArray[patternIndex - 1];
                else
                    textIndex = textIndex + 1;
            }
        }
        return foundIndexes;
    }
}
