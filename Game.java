import java.util.Scanner;
import java.util.StringTokenizer;

public class Game {

    public static void main(String[] args) {
        int choice = 0;
        boolean status = true;


        while (status) {
            Scanner myScanner = new Scanner(System.in);
            System.out.println("Choose a game mode! Beginner, Intermediate, or Expert:");
            String pick = myScanner.nextLine();

            if (pick.equals("B")) {
                choice = 3;
            }
            if (pick.equals("I")) {
                choice = 6;
            }
            if (pick.equals("E")) {
                choice = 9;
            }

            Scanner debugMode = new Scanner(System.in);
            System.out.println("Enter debug mode? Y/N");
            String debug = debugMode.nextLine();


            Board test = new Board(choice);
            test.placeBoats();

            if (debug.equals("Y")) {
                test.print();
            } else {
                test.display();
            }

            int turn = 0; //turn counter
            System.out.println("Number of boats: " + test.getNumBoats());
            System.out.println("Your move: x,y");

//            test.checkBoat();
            while (myScanner.hasNext()) {
//                System.out.println("Your move: x,y");
                String atk = myScanner.nextLine();
                System.out.println("getsunk " + test.getSunkCount() + " getNumBoats "+ test.getNumBoats());//user move

                String[] split = atk.split(","); //get coord
                int x = Integer.parseInt(split[0]);
                int y = Integer.parseInt(split[1]);

                if (x > (choice - 1) || x < 0 || y < 0 || y > (choice - 1)) {
                    //out of bounds
                    System.out.println("Penalty");
                }
                else {
                    int move = test.fire(x, y);
                    test.sunk();

                    if (test.getSunkCount() == test.getNumBoats()) {
                        System.out.println("Game over");

                        status = false;
                        break;
                    }

                    if (move == 1) { //already guessed
                        System.out.println("Penalty");
                    }
                }

                if (debug.equals("Y")) {
                    test.print();
                } else {
                    test.display();
                }

                turn++;

                System.out.println("turn " + turn);
                System.out.println("\n");
                System.out.println("Your move: x,y");
            }
        }

    }
}