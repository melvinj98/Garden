import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Garden {
    static Scanner keyboard = new Scanner(System.in);
    static int row = 5, column = 5;
    static String plant = "", selectedFlower = "";

    public static void main(String[] args) {
        String[][] garden = new String[row][column];
        String fileName = "";

        System.out.println("Would you like to create a new garden or continue a garden?");
        System.out.print("Enter 'New' to create a new garden or enter file name to continue garden: ");
        fileName = "" + keyboard.nextLine() + ".txt";
        System.out.println("Using garden file: " + fileName);

        //creates garden file
        try {
            File gardenFile = new File(fileName);
            if (gardenFile.createNewFile()) {
                System.out.println("Created " + fileName);
            } else {
                System.out.println("Garden already exists");
            }
        } catch (IOException e) {
            System.out.println("Error occurred");
        }

        plantInGarden(garden);

        for (int row = 0; row < garden.length; row++) {
            System.out.println(Arrays.toString(garden[row]));
        }

        //adds plant to file
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));

            for (int i = 0; i < garden.length; i++) {
                for (int j = 0; j < garden[i].length; j++) {
                    bw.write(garden[i][j] + ((j == garden[i].length - 1) ? "" : ","));
                }
                bw.newLine();
            }
            bw.flush();
        } catch (IOException e) {
        }
    }

    public static boolean createGarden(int row, int column) {
        return true;
    }

    public static String[][] plantInGarden(String[][] garden) {
        plantList();
        /*
        //test to fill garden array
        try {
            for (int x = 0; x < garden.length; x++) {
                for (int y = 0; y < garden[0].length; y++) {
                    garden[x][y] = "[" + plant + "]";
                }
            }
            return true;
        } catch (Exception e) {
            return false;
        }
         */

        System.out.println("Where would you like to plant the flower?");
        int x = keyboard.nextInt();
        int y = keyboard.nextInt();

        garden[x][y] = "[" + plant + "]";

        return garden;
    }

    public static String plantList() {
        System.out.println("Plant List");
        System.out.println("African Lily: A");
        System.out.println("Azalea: B");
        System.out.println("Bluebell: C");
        System.out.println("Chrysanthemum: D");
        System.out.println("Daffodil: E");
        System.out.println("Hyacinths: F");
        System.out.println("Tulip: G");
        System.out.println("Hydrangea: H");
        System.out.println("Orchids: I");
        System.out.println("Lavender: J");
        System.out.print("Select Plant: ");
        String input = keyboard.nextLine();

        switch (input.toLowerCase()) {
            case "a" -> plant = "A";
            case "b" -> plant = "B";
            case "c" -> plant = "C";
        }

        return plant;
    }
}
