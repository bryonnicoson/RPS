import java.io.*;
import java.util.*;

public class RPS {

    // game data multidimensional array
    public static int[][] gamesArray = new int[5][5];

    // data file
    public static String dataFile = new String("RPSData.data");

    // variables for storing game actions, outcomes
    public static String userThrow = "";
    public static String compThrow = "";
    public static String outcome = "";

    // variables for changing text color
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RESET = "\u001B[0m";

    // here we go
    public static void main(String[] args) {

        // multi-color splash screens!
        System.out.print("\033\143");
        System.out.print(ANSI_RED);
        showTextFile("Rock.asc");
        pause(1);
        System.out.print("\033\143");
        System.out.print(ANSI_PURPLE);
        showTextFile("Paper.asc");
        pause(1);
        System.out.print("\033\143");
        System.out.print(ANSI_BLUE);
        showTextFile("Scissors.asc");
        pause(1);
        System.out.print("\033\143");
        System.out.print(ANSI_GREEN);
        showTextFile("Lizard.asc");
        pause(1);
        System.out.print("\033\143");
        System.out.print(ANSI_YELLOW);
        showTextFile("Spock.asc");
        pause(1);
        System.out.print(ANSI_RESET);

        readData();
        menu();
    }

    // method to read and display contents of text file (.asc, .txt)
    public static void showTextFile(String fileName) {

        String line = null;  // variable to reference one line at a time

        try {
            FileReader fileReader = new FileReader("splash/" + fileName);
            // always wrap FileReader in BufferedReader
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }

            // close file
            bufferedReader.close();
        }
        catch(FileNotFoundException ex) {
            System.out.println("Unable to open file '" + fileName + "'");
        }
        catch(IOException ex) {
            System.out.println("Error reading file '" + fileName + "'");
        }
    }

    // method to pause execution for specified number of seconds
    public static void pause(int seconds) {
        try {
            Thread.sleep(seconds*1000);
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }

    // title - call this first whenever refreshing screen
    public static void title() {
        System.out.println("\033\143");
        System.out.println(ANSI_RED+"╦═╗┌─┐┌─┐┬┌─"+ANSI_PURPLE+"  ╔═╗┌─┐┌─┐┌─┐┬─┐"+ANSI_BLUE+"  ╔═╗┌─┐┬┌─┐┌─┐┌─┐┬─┐┌─┐"+ANSI_GREEN+"  ╦  ┬┌─┐┌─┐┬─┐┌┬┐"+ANSI_YELLOW+"  ┌─┐┌─┐┌─┐┌─┐╦╔═");
        System.out.println(ANSI_RED+"╠╦╝│ ││  ├┴┐"+ANSI_PURPLE+"  ╠═╝├─┤├─┘├┤ ├┬┘"+ANSI_BLUE+"  ╚═╗│  │└─┐└─┐│ │├┬┘└─┐"+ANSI_GREEN+"  ║  │┌─┘├─┤├┬┘ ││"+ANSI_YELLOW+"  └─┐├─┘│ ││  ╠╩╗");
        System.out.println(ANSI_RED+"╩╚═└─┘└─┘┴ ┴"+ANSI_PURPLE+"  ╩  ┴ ┴┴  └─┘┴└─"+ANSI_BLUE+"  ╚═╝└─┘┴└─┘└─┘└─┘┴└─└─┘"+ANSI_GREEN+"  ╩═╝┴└─┘┴ ┴┴└──┴┘"+ANSI_YELLOW+"  └─┘┴  └─┘└─┘╩ ╩");
        System.out.println(ANSI_RESET);
    }

    // menu
    public static void menu() {
        title();

        // column totals - initialize variables to store sums
        int col0Sum = gamesArray[0][0]+gamesArray[1][0]+gamesArray[2][0]+gamesArray[3][0]+gamesArray[4][0];
        int col1Sum = gamesArray[0][1]+gamesArray[1][1]+gamesArray[2][1]+gamesArray[3][1]+gamesArray[4][1];
        int col2Sum = gamesArray[0][2]+gamesArray[1][2]+gamesArray[2][2]+gamesArray[3][2]+gamesArray[4][2];
        int col3Sum = gamesArray[0][3]+gamesArray[1][3]+gamesArray[2][3]+gamesArray[3][3]+gamesArray[4][3];
        int col4Sum = gamesArray[0][4]+gamesArray[1][4]+gamesArray[2][4]+gamesArray[3][4]+gamesArray[4][4];
        // need a total of the columns for use to calc percentages
        int colsSum = col0Sum + col1Sum + col2Sum + col3Sum + col4Sum;

        // initialize these - replace with file read routine - TO BE DELETED
        int col0Pct = 0; int col1Pct = 0; int col2Pct = 0; int col3Pct = 0; int col4Pct = 0;
        int row0Pct = 0; int row1Pct = 0; int row2Pct = 0; int row3Pct = 0; int row4Pct = 0;

        // column percentages - to avoid div-by-zero error, call only if there is a non-zero ColsSum
        if (colsSum != 0) {
            col0Pct = col0Sum * 100 / colsSum;
            col1Pct = col1Sum * 100 / colsSum;
            col2Pct = col2Sum * 100 / colsSum;
            col3Pct = col3Sum * 100 / colsSum;
            col4Pct = col4Sum * 100 / colsSum;
        }
        // row totals
        int row0Sum = gamesArray[0][0]+gamesArray[0][1]+gamesArray[0][2]+gamesArray[0][3]+gamesArray[0][4];
        int row1Sum = gamesArray[1][0]+gamesArray[1][1]+gamesArray[1][2]+gamesArray[1][3]+gamesArray[1][4];
        int row2Sum = gamesArray[2][0]+gamesArray[2][1]+gamesArray[2][2]+gamesArray[2][3]+gamesArray[2][4];
        int row3Sum = gamesArray[3][0]+gamesArray[3][1]+gamesArray[3][2]+gamesArray[3][3]+gamesArray[3][4];
        int row4Sum = gamesArray[4][0]+gamesArray[4][1]+gamesArray[4][2]+gamesArray[4][3]+gamesArray[4][4];

        int rowsSum = row0Sum + row1Sum + row2Sum + row3Sum + row4Sum;

        // row percentages
        if (rowsSum != 0) {
            row0Pct = row0Sum * 100 / rowsSum;
            row1Pct = row1Sum * 100 / rowsSum;
            row2Pct = row2Sum * 100 / rowsSum;
            row3Pct = row3Sum * 100 / rowsSum;
            row4Pct = row4Sum * 100 / rowsSum;
        }

        System.out.println("       ME ▶                                  [c]lear data     [i]nstructions     e[x]it ");
        System.out.println("  YOU ┌────────────┬────────────┬────────────┬────────────┬────────────╥───────────────┐");
        System.out.println("   ▼  │   [R]ock   │   [P]aper  │ [S]cissors │  [L]izard  │   Spoc[K]  ║ Record (W-L-T)│");   //  ROCK              PAPER             SCISSORS          LIZARD            SPOCK                WINS                               LOSSES                          TIES
        System.out.println("      ├────────────┼────────────┼────────────┼────────────┼────────────╫───────────────┤");
        System.out.printf ("  [R] │     %4d   │     %4d   │     %4d   │     %4d   │     %4d   ║ %3d -%3d -%3d │%5d %3d%%\n", gamesArray[0][0], gamesArray[0][1], gamesArray[0][2], gamesArray[0][3], gamesArray[0][4], gamesArray[0][2]+gamesArray[0][3], gamesArray[0][1]+gamesArray[0][4], gamesArray[0][0], row0Sum, row0Pct);
        System.out.println("      ├────────────┼────────────┼────────────┼────────────┼────────────╫───────────────┤");
        System.out.printf ("  [P] │     %4d   │     %4d   │     %4d   │     %4d   │     %4d   ║ %3d -%3d -%3d │%5d %3d%%\n", gamesArray[1][0], gamesArray[1][1], gamesArray[1][2], gamesArray[1][3], gamesArray[1][4], gamesArray[1][0]+gamesArray[1][4], gamesArray[1][2]+gamesArray[1][3], gamesArray[1][1], row1Sum, row1Pct);
        System.out.println("      ├────────────┼────────────┼────────────┼────────────┼────────────╫───────────────┤");
        System.out.printf ("  [S] │     %4d   │     %4d   │     %4d   │     %4d   │     %4d   ║ %3d -%3d -%3d │%5d %3d%%\n", gamesArray[2][0], gamesArray[2][1], gamesArray[2][2], gamesArray[2][3], gamesArray[2][4], gamesArray[2][1]+gamesArray[2][3], gamesArray[2][0]+gamesArray[2][4], gamesArray[2][2], row2Sum, row2Pct);
        System.out.println("      ├────────────┼────────────┼────────────┼────────────┼────────────╫───────────────┤");
        System.out.printf ("  [L] │     %4d   │     %4d   │     %4d   │     %4d   │     %4d   ║ %3d -%3d -%3d │%5d %3d%%\n", gamesArray[3][0], gamesArray[3][1], gamesArray[3][2], gamesArray[3][3], gamesArray[3][4], gamesArray[3][1]+gamesArray[3][4], gamesArray[3][0]+gamesArray[3][2], gamesArray[3][3], row3Sum, row3Pct);
        System.out.println("      ├────────────┼────────────┼────────────┼────────────┼────────────╫───────────────┤");
        System.out.printf ("  [K] │     %4d   │     %4d   │     %4d   │     %4d   │     %4d   ║ %3d -%3d -%3d │%5d %3d%%\n", gamesArray[4][0], gamesArray[4][1], gamesArray[4][2], gamesArray[4][3], gamesArray[4][4], gamesArray[4][0]+gamesArray[4][2], gamesArray[4][1]+gamesArray[4][3], gamesArray[4][4], row4Sum, row4Pct);
        System.out.println("      └────────────┴────────────┴────────────┴────────────┴────────────╨───────────────┘");
        System.out.printf ("           %5d        %5d        %5d        %5d        %5d     %3d -%3d -%3d     ", col0Sum, col1Sum, col2Sum, col3Sum, col4Sum, gamesArray[0][2]+gamesArray[0][3]+gamesArray[1][0]+gamesArray[1][4]+gamesArray[2][1]+gamesArray[2][3]+gamesArray[3][1]+gamesArray[3][4]+gamesArray[4][0]+gamesArray[4][2],
                                                                                                                       gamesArray[0][1]+gamesArray[0][4]+gamesArray[1][2]+gamesArray[1][3]+gamesArray[2][0]+gamesArray[2][4]+gamesArray[3][0]+gamesArray[3][2]+gamesArray[4][1]+gamesArray[4][3], gamesArray[0][0]+gamesArray[1][1]+gamesArray[2][2]+gamesArray[3][3]+gamesArray[4][4]);

        System.out.printf ("\n             %3d%%         %3d%%         %3d%%         %3d%%         %3d%%", col0Pct, col1Pct, col2Pct, col3Pct, col4Pct);

        System.out.print("\n\nThrow! ==> ");

        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine().toUpperCase();

        switch (userInput) {
            case "X":
                System.out.print("Really?  You're leaving me? :(  [y/n] ");
                Scanner really = new Scanner(System.in);
                String seriously = really.nextLine().toUpperCase();

                switch (seriously) {
                    case "Y":
                        System.out.println("\nBye!\n\n");
                        saveData();
                        System.exit(0);
                        break;
                    default:
                        menu();
                        break;
                }
                break;
            case "C":
                System.out.print("Really?  You want to wipe all this awesome data? :(  [y/n] ");
                Scanner dontWipe = new Scanner(System.in);
                String nah = dontWipe.nextLine().toUpperCase();

                switch (nah) {
                    default:
                        System.out.println("\nI think I'll keep the data.");
                        pause(2);
                        menu();
                        break;
                }
            case "I":
                System.out.print("\033\143");
                showTextFile("Instructions.txt");
                promptEnterKey();
                menu();
                break;
            case "R":
                userThrow = "ROCK";
                break;
            case "P":
                userThrow = "PAPER";
                break;
            case "S":
                userThrow = "SCISSORS";
                break;
            case "L":
                userThrow = "LIZARD";
                break;
            case "K":
                userThrow = "SPOCK";
                break;
            default:
                userThrow = "INVALID";
                break;
        }
        if (userThrow == "INVALID") {
            System.out.println("Invalid choice.  Please try again.");
            pause(1);
            menu();
        }
        System.out.print("You threw " + userThrow + "!  ");
        pause(1);
        System.out.print("I threw " + opponentThrow() + "!  ");
        pause(1);
        System.out.print(determineOutcome(userThrow, compThrow) + "\n");

        // pause and return to menu
        pause(2);
        menu();

    }
    public static String opponentThrow() {
        Random randomGenerator = new Random();
        int randomInt = randomGenerator.nextInt(5);

        switch(randomInt) {
            case 0:
                compThrow = "ROCK";
                break;
            case 1:
                compThrow = "PAPER";
                break;
            case 2:
                compThrow = "SCISSORS";
                break;
            case 3:
                compThrow = "LIZARD";
                break;
            default:
                compThrow = "SPOCK";
                break;
        }
        return compThrow;
    }

    public static String determineOutcome(String userThrow, String compThrow) {

        // compare values of thrown strings, increment appropriate element of gamesArray, and return string outcome
        // ROCK = [0], PAPER = [1], SCISSORS = [2], LIZARD = [3], SPOCK = [4]
        // userThrow is first (rows), and compThrow is second (cols)

        if (userThrow == "ROCK") {

            switch (compThrow) {
                case "ROCK":
                    outcome = "Banging rocks together ain't accomplishing much.";
                    gamesArray[0][0] += 1;
                    break;
                case "PAPER":
                    outcome = "Paper covers Rock.  I win!";
                    gamesArray[0][1] += 1;
                    break;
                case "SCISSORS":
                    outcome = "Rock smashes Scissors.  You win.";
                    gamesArray[0][2] += 1;
                    break;
                case "LIZARD":
                    outcome = "Rock crushes Lizard.  Messy, but you win.";
                    gamesArray[0][3] += 1;
                    break;
                case "SPOCK":
                    outcome = "Spock vaporizes Rock and your hopes with it.  I win!";
                    gamesArray[0][4] += 1;
                    break;
            }
        } else if (userThrow == "PAPER") {

            switch (compThrow) {

                case "ROCK":
                    outcome = "Paper covers Rock.   You win this round.";
                    gamesArray[1][0] += 1;
                    break;
                case "PAPER":
                    outcome = "MORE Paperwork?  This reminds me too much of my last job.";
                    gamesArray[1][1] += 1;
                    break;
                case "SCISSORS":
                    outcome = "Scissors cut Paper.  Snip.  Snip.  I win!";
                    gamesArray[1][2] += 1;
                    break;
                case "LIZARD":
                    outcome = "Lizard eats Paper.  They're like goats.  I win!";
                    gamesArray[1][3] += 1;
                    break;
                case "SPOCK":
                    outcome = "Paper disproves Spock.  Leonard Nemoy will not be happy.  You win.";
                    gamesArray[1][4] += 1;
                    break;
            }
        } else if (userThrow == "SCISSORS") {

            switch (compThrow) {

                case "ROCK":
                    outcome = "My rock crushes your scissors.  I win!";
                    gamesArray[2][0] += 1;
                    break;
                case "PAPER":
                    outcome = "Scissors cut Paper.  You win.";
                    gamesArray[2][1] += 1;
                    break;
                case "SCISSORS":
                    outcome = "Scissors fight!  En garde!";
                    gamesArray[2][2] += 1;
                    break;
                case "LIZARD":
                    outcome = "Scissors decapitates Lizard.  You're cleaning that up.  You win.";
                    gamesArray[2][3] += 1;
                    break;
                case "SPOCK":
                    outcome = "Spock smashes Scissors. I win!";
                    gamesArray[2][4] += 1;
                    break;
            }
        } else if (userThrow == "LIZARD") {

            switch (compThrow) {
                case "ROCK":
                    outcome = "Rock crushes Lizard.  I win!";
                    gamesArray[3][0] += 1;
                    break;
                case "PAPER":
                    outcome = "Lizard eats paper.  Seems like a stretch, but you win.";
                    gamesArray[3][1] += 1;
                    break;
                case "SCISSORS":
                    outcome = "Scissors decapitates Lizard.  I win!";
                    gamesArray[3][2] += 1;
                    break;
                case "LIZARD":
                    outcome = "I once had two iguanas until the big one ate the little one.";
                    gamesArray[3][3] += 1;
                    break;
                case "SPOCK":
                    outcome = "Lizard poisons Spock.  Oh no!  You win.";
                    gamesArray[3][4] += 1;
                    break;
            }
        } else if (userThrow == "SPOCK") {

            switch (compThrow) {
                case "ROCK":
                    outcome = "Spock vaporizes Rock.  Well played.  You win.";
                    gamesArray[4][0] += 1;
                    break;
                case "PAPER":
                    outcome = "Paper disproves Spock.  I win!";
                    gamesArray[4][1] += 1;
                    break;
                case "SCISSORS":
                    outcome = "Spock crushes Scissors.  With a rock I suppose.  You win.";
                    gamesArray[4][2] += 1;
                    break;
                case "LIZARD":
                    outcome = "Lizard poisons Spock.  I am saddened, but I win!";
                    gamesArray[4][3] += 1;
                    break;
                case "SPOCK":
                    outcome = "Two Spocks?  Just like Star Trek: Into Darkness!";
                    gamesArray[4][4] += 1;
                    break;
            }
        }
        return(outcome);
    }

    public static void readData(){

        try {
            FileInputStream fin = new FileInputStream(dataFile);
            ObjectInputStream ois = new ObjectInputStream(fin);
            gamesArray = (int[][]) ois.readObject();

        } catch (Exception e) {
            // ya would wanna put some error handling here if needed
        }

    }

    public static void saveData(){

        try {
            FileOutputStream fout = new FileOutputStream(dataFile);
            ObjectOutputStream oos = new ObjectOutputStream(fout);
            oos.writeObject(gamesArray);
        } catch (Exception e) {
            // ya would wanna put some error handling here if needed
        }

    }
    public static void promptEnterKey(){
        System.out.println("Press \"ENTER\" to continue...");
        try {
            int read = System.in.read(new byte[2]);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}