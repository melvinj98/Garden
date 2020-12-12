import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class Garden {
    static Scanner keyboard = new Scanner(System.in);
    static int row, column;
    static String plant = "", fileName;
    static String[][] garden = new String[row][column];
    static String[][] emptyLocations = new String[row][column];
    static String[][] plantLocations = new String[row][column];

    public static void main(String[] args) {
        String option;
        boolean quit = false;

        System.out.println("Would you like to create a new garden?[Y/N]");
        option = keyboard.nextLine();
        if (option.equalsIgnoreCase("y")) {
            System.out.println("Enter new garden name");
            fileName = keyboard.nextLine();
            boolean correctSize = false;

            do {
                System.out.println("How many rows and columns will " + fileName + " have?");
                row = keyboard.nextInt();
                column = keyboard.nextInt();
                if (row >= 5 && column >= 5) {
                    garden = new String[row][column];
                    correctSize = true;
                } else
                    System.out.println("Garden must be at least 5x5");
            } while (!correctSize);

            //fills garden array with * (* = empty spots)
            try {
                for (int x = 0; x < garden.length; x++) {
                    for (int y = 0; y < garden[0].length; y++) {
                        garden[x][y] = "*";
                    }
                }
            } catch (Exception e) {
                System.out.println("Error");
            }

        } else if (option.equalsIgnoreCase("n")) {
            try {
                System.out.println("Which garden would you like to continue?");
                fileName = keyboard.nextLine();
                System.out.println("Using garden file: " + fileName);
                garden = readFromFile(fileName);
            } catch (Exception e) {
                System.out.println("File not found");
            }
        }

        do {
            //add, remove, show garden, show plant list, move plant, *print locations of plants, quit
            System.out.println("-------------");
            System.out.println("[1] Add plant");
            System.out.println("[2] Remove plant");
            System.out.println("[3] See " + fileName);
            System.out.println("[4] Plant list");
            System.out.println("[5] Move plant");
            System.out.println("[6] See locations of plant");
            System.out.println("[7] Let it rain");
            System.out.println("[8] Drought");
            System.out.println("[9] View empty spots only");
            System.out.println("[10] View specific plant only");
            System.out.println("[11] Barchart");
            System.out.println("[12] Save garden");
            System.out.println("[13] Quit");

            option = keyboard.next();

            switch (option) {
                case "1" -> addToGarden(garden);
                case "2" -> removeFromGarden(garden);
                case "3" -> viewGarden(garden);
                case "4" -> plantList();
                case "5" -> movePlant(garden);
                case "6" -> plantLocations(garden);
                case "7" -> letItRain(garden);
                case "8" -> drought(garden);
                case "9" -> viewEmpty();
                case "10" -> viewSpecificPlantOnly();
                //case "11" -> barchart(garden);
                case "12" -> addToFile(garden, fileName);
                case "13" -> {
                    System.out.println("Quitting Program");
                    quit = true;
                }
                default -> System.out.println("Not a valid option, try again");
            }
        } while (!quit);

        addToFile(garden, fileName);
    }

    private static void drought(String[][] garden) {
        int droughtRow, rainColumn;
        int min = 0;
        int rowRange = (row - 1) - min + 1;
        int columnRange = (column - 1) - min + 1;
        int availableSpots = 0;

        for (int x = 0; x < garden.length; x++) {
            for (int y = 0; y < garden[0].length; y++) {
                String spot = garden[x][y];
                if (!spot.equals("*"))
                    availableSpots++;
            }
        }

        if (availableSpots > 0) {
            for (int i = 0; i < 5; i++) {
                droughtRow = (int) (Math.random() * rowRange) + min;
                rainColumn = (int) (Math.random() * columnRange) + min;
                String randomSpot = garden[droughtRow][rainColumn];

                if (!randomSpot.equals("*")) {
                    garden[droughtRow][rainColumn] = "*";
                    availableSpots--;
                }
                viewGarden(garden);
            }
        } else {
            System.out.println("Garden is empty");
        }

    }

    private static void letItRain(String[][] garden) {
        int rainRow, rainColumn;
        int min = 0;
        int rowRange = (row - 1) - min + 1;
        int columnRange = (column - 1) - min + 1;
        int availableSpots = 0;

        for (int x = 0; x < garden.length; x++) {
            for (int y = 0; y < garden[0].length; y++) {
                String spot = garden[x][y];
                if (spot.equals("*"))
                    availableSpots++;
            }
        }

        if (availableSpots > 0) {
            for (int i = 0; i < 5; i++) {
                rainRow = (int) (Math.random() * rowRange) + min;
                rainColumn = (int) (Math.random() * columnRange) + min;
                String randomSpot = garden[rainRow][rainColumn];

                if (!randomSpot.equals("*") && availableSpots > 0) {
                    //search for empty spot and fill in with randomSpot
                    rainRow = (int) (Math.random() * rowRange) + min;
                    rainColumn = (int) (Math.random() * columnRange) + min;
                    if (garden[rainRow][rainColumn].equals("*")) {
                        garden[rainRow][rainColumn] = randomSpot;
                    }

                }
                viewGarden(garden);
            }
        } else {
            System.out.println("Garden is full");
        }
    }


    //reads from selected file and adds to garden array
    public static String[][] readFromFile(String fileName) {
        try {
            Scanner sc = new Scanner(new BufferedReader(new FileReader(fileName)));

            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String fileRead = br.readLine();

            String[] tokenize = fileRead.split("x");
            row = Integer.parseInt(tokenize[0]);
            column = Integer.parseInt(tokenize[1]);
            garden = new String[row][column];

            garden = new String[row][column];
            int c = 0;
            for (int k = 0; k < garden.length + 1; k++) {
                String[] line = sc.nextLine().trim().split(",");
                for (int j = 0; j < line.length; j++) {
                    if (k != 0) {
                        garden[c - 1][j] = line[j];
                    }
                }
                c++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return garden;
    }

    //adds plant to garden array
    public static void addToGarden(String[][] garden) {
        for (String[] strings : garden) {
            System.out.println(Arrays.toString(strings));
        }
        System.out.println();
        System.out.println("-------------------------------");
        System.out.print("");

        viewGarden(garden);
        plantList();

        System.out.println("Where would you like to plant the flower?");
        int x = keyboard.nextInt();
        int y = keyboard.nextInt();

        if (garden[x][y].equals("*")) {
            garden[x][y] = plant;
        } else {
            System.out.println("There is already a flower here, would you like to replace it?[Y/N]");
            String option = keyboard.next();
            if (option.equalsIgnoreCase("y")) {
                garden[x][y] = plant;
                System.out.println("Planted at row " + x + " column " + y);
            } else {
                System.out.println("Selected flower will not be planted");
            }
        }

    }

    //removes plant from garden array
    public static void removeFromGarden(String[][] garden) {
        for (String[] strings : garden) {
            System.out.println(Arrays.toString(strings));
        }

        System.out.println("Which spot would you like to clear up?: ");
        int row = keyboard.nextInt();
        int column = keyboard.nextInt();
        garden[row][column] = "*";
    }

    //moves plant in garden array
    public static void movePlant(String[][] garden) {
        viewGarden(garden);
        System.out.println("Which plant would you like to move?");
        int row = keyboard.nextInt();
        int column = keyboard.nextInt();

        String plantToMove = garden[row][column];
        garden[row][column] = "*";

        viewGarden(garden);
        System.out.println("Where would you like to move the plant?");
        row = keyboard.nextInt();
        column = keyboard.nextInt();

        garden[row][column] = plantToMove;
    }

    //prints out garden
    public static void viewGarden(String[][] garden) {
        System.out.println("");
        System.out.println(fileName);
        for (String[] strings : garden) {
            System.out.println(Arrays.toString(strings));
        }
        System.out.println("");
    }

    //adds garden array to file
    public static void addToFile(String[][] garden, String fileName) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));
            bw.write(row + "x" + column);
            bw.newLine();
            for (String[] strings : garden) {
                for (int j = 0; j < strings.length; j++) {
                    bw.write(strings[j] + ((j == strings.length - 1) ? "" : ","));
                }
                bw.newLine();
            }
            bw.flush();
        } catch (IOException e) {
            System.out.println("Error occurred when attempting to save garden");
        }
    }

    //lets user pick a plant
    public static void plantList() {
        boolean done;
        do {
            done = true;
            System.out.println("Plant List");
            System.out.println("Autumn Brilliance Serviceberry: A");
            System.out.println("Flowering Dogwood: F");
            System.out.println("Purple MilkWeed: P");
            System.out.println("Moonbeam Whorled Tickseed: M");
            System.out.println("Butterfly Weed: B");
            System.out.println("Yellowroot: Y");
            System.out.println("Dusty Zenobia: D");
            System.out.println("Twisted Trillium: T");
            System.out.println("Southern Red Trillium: S");
            System.out.println("Large Flowered Bellwort: L");
            System.out.print("Select Plant: ");
            String input = keyboard.next();

            switch (input.toLowerCase()) {
                case "a" -> plant = "A";
                case "f" -> plant = "F";
                case "p" -> plant = "P";
                case "m" -> plant = "M";
                case "b" -> plant = "B";
                case "y" -> plant = "Y";
                case "d" -> plant = "D";
                case "t" -> plant = "T";
                case "s" -> plant = "S";
                case "l" -> plant = "L";
                default -> {
                    System.out.println("Option does not match an available plant, please enter again");
                    done = false;
                }
            }
        } while (!done);
    }

    //prints out plants and their location in array
    public static void plantLocations(String[][] garden) {
        System.out.println("");
        for (int x = 0; x < garden.length; x++) {
            for (int y = 0; y < garden[0].length; y++) {
                String plant = garden[x][y];
                if (!plant.equals("*"))
                    System.out.println("Plant: " + plant + " -> Row: " + x + " Column: " + y);
                System.out.println("");
            }
        }
    }

    //print only empty spots
    public static void viewEmpty() {
        emptyLocations = new String[row][column];

        for (int x = 0; x < garden.length; x++) {
            for (int y = 0; y < garden[0].length; y++) {
                String spot = garden[x][y];
                if (spot.equals("*"))
                    emptyLocations[x][y] = spot;
                else
                    emptyLocations[x][y] = " ";
            }
        }
        System.out.println("");
        System.out.println(fileName);
        for (String[] strings : emptyLocations) {
            System.out.println(Arrays.toString(strings));
        }
        System.out.println("");
    }

    private static void viewSpecificPlantOnly() {
        plantLocations = new String[row][column];
        System.out.println("Which plant should be shown");
        String plant = keyboard.next();

        for (int x = 0; x < garden.length; x++) {
            for (int y = 0; y < garden[0].length; y++) {
                String spot = garden[x][y];
                if (spot.equals(plant))
                    plantLocations[x][y] = spot;
                else
                    plantLocations[x][y] = " ";
            }
        }

        System.out.println("");
        System.out.println(fileName);
        for (String[] strings : plantLocations) {
            System.out.println(Arrays.toString(strings));
        }
        System.out.println("");
    }

}
