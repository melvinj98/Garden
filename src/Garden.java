import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class Garden {
    static Scanner keyboard = new Scanner(System.in);
    static int row = 5, column = 5;
    static String plant = "", fileName;
    static String[][] garden = new String[row][column];
    boolean correctSize = true;

    public static void main(String[] args) {
        String option;
        boolean quit = false;
        boolean correctSize = false;

        /*
        //test to fill garden array
        try {
            for (int x = 0; x < garden.length; x++) {
                for (int y = 0; y < garden[0].length; y++) {
                    garden[x][y] = "[" + plant + "]";
                }
            }
        } catch (Exception e) {
        }
        */

        System.out.println("Would you like to create a new garden?[Y/N]");
        option = keyboard.nextLine();
        if (option.equalsIgnoreCase("y")) {
            System.out.println("Enter new garden name");
            fileName = keyboard.nextLine();


            do {
                System.out.println("Enter size of new garden");
                row = keyboard.nextInt();
                column = keyboard.nextInt();
                if (row >= 5 && column >= 5) {
                    garden = new String[row][column];
                    correctSize = true;
                } else
                    System.out.println("Garden must be at least 5x5");
            } while (!correctSize);

            // createGarden(fileName);

            //fills garden with x (x = empty spots)
            try {
                for (int x = 0; x < garden.length; x++) {
                    for (int y = 0; y < garden[0].length; y++) {
                        garden[x][y] = "x";
                    }
                }
            } catch (Exception e) {
                System.out.println("Error");
            }

        } else if (option.equalsIgnoreCase("n")) {
            System.out.println("Which garden would you like to continue?");
            fileName = keyboard.nextLine();
            System.out.println("Using garden file: " + fileName);

            readFromFile(garden, fileName);
        }
        do {
            System.out.println("Remove, Add, or Quit?");
            String option1 = keyboard.next();
            switch (option1.toLowerCase()) {
                case "remove" -> removeFromGarden(garden);
                case "add" -> addToGarden(garden);
                case "quit" -> {
                    System.out.println("Quitting Program");
                    quit = true;
                }
                default -> System.out.println("Not a valid option, try again");
            }


            for (String[] strings : garden) {
                System.out.println(Arrays.toString(strings));
            }
        } while (!quit);
        addToFile(garden, fileName, column);


    }

    //creates garden file
    public static boolean createGarden(String fileName) {
        try {
            File gardenFile = new File(fileName + ".txt");
            if (gardenFile.createNewFile()) {
                System.out.println("Created " + fileName);
            } else {
                System.out.println("Garden already exists");
            }
        } catch (IOException e) {
            System.out.println("Error occurred");
        }

        return true;
    }

    //reads from selected file and adds to garden array
    public static void readFromFile(String[][] garden, String fileName) {
        try {
            Scanner sc = new Scanner(new BufferedReader(new FileReader(fileName)));
            int columns = 0;

            while (sc.hasNextLine()) {
                for(int i = 0; i < 1; i++){
                    String[] line = sc.nextLine().trim().split(",");
                    columns = Integer.parseInt(line[i]);
                }

                for (int i = 0; i < columns - 1; i++) {
                    String[] line = sc.nextLine().trim().split(",");
                    for (int j = 0; j < line.length; j++) {

                            garden[i][j] = line[j];
                        }


                    }
                }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    //adds plant to garden array
    public static String[][] addToGarden(String[][] garden) {
        for (String[] strings : garden) {
            System.out.println(Arrays.toString(strings));
        }
        System.out.println();
        System.out.println("-------------------------------");
        System.out.print("");
        plantList();

        System.out.println("Where would you like to plant the flower?");
        int x = keyboard.nextInt();
        int y = keyboard.nextInt();

        if (garden[x][y].equals("x")) {
            garden[x][y] = "[" + plant + "]";
        } else {
            System.out.println("There is already a flower here, would you like to replace it?[Y/N]");
            String option = keyboard.next();
            if (option.equalsIgnoreCase("y")) {
                garden[x][y] = "[" + plant + "]";
            } else {
                System.out.println("Selected flower will not be planted");
            }
        }

        return garden;
    }

    //removes plant from garden array
    public static String[][] removeFromGarden(String[][] garden) {
        for (String[] strings : garden) {
            System.out.println(Arrays.toString(strings));
        }

        System.out.println("Which spot would you like to clear up?: ");
        int x = keyboard.nextInt();
        int y = keyboard.nextInt();
        garden[x][y] = "x";

        return garden;
    }

    //adds garden array to file
    public static void addToFile(String[][] garden, String fileName, int column) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));
            bw.write(column + ",");
            bw.newLine();
            for (String[] strings : garden) {
                for (int j = 0; j < strings.length; j++) {
                    bw.write(strings[j] + ((j == strings.length - 1) ? "" : ","));
                }
                bw.newLine();
            }
            bw.flush();
        } catch (IOException e) {
        }
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
        String input = keyboard.next();

        switch (input.toLowerCase()) {
            case "a" -> plant = "A";
            case "b" -> plant = "B";
            case "c" -> plant = "C";
        }

        return plant;
    }
}
