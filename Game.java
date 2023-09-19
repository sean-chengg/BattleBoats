//Written by Sean Cheng (chen7424) and Lily Li (li002398)
import java.util.Scanner;

public class Game {

    public static void main(String[] args) {
        int choice = 0; //initializes the choice
        boolean status = true; //sets the loop to run



        while (status) {
            Scanner myScanner = new Scanner(System.in); //starts scanner for input
            System.out.println("Choose a game mode! Input beginner, intermediate, or expert (not case-sensitive)");
            String pick = myScanner.nextLine(); //takes in difficulty mode

            if (pick.equalsIgnoreCase("Beginner")) {
                choice = 3;
            }
            if (pick.equalsIgnoreCase("Intermediate")) {
                choice = 6;
            }
            if (pick.equalsIgnoreCase("Expert")) {
                choice = 9;
            }

            Scanner debugMode = new Scanner(System.in);
            System.out.println("Enter debug mode? Y for yes, N for no");
            String debug = debugMode.nextLine(); //takes in next line of input for debug


            Board gameBoard = new Board(choice); //creates a board
            gameBoard.placeBoats(); //places boats on the board

            if (debug.equalsIgnoreCase("Y")) { //if debug is yes, print the debug board
                gameBoard.print();
            } else {
                gameBoard.display(); //if no print the user board
            }

            int turn = 0; //turn counter
            System.out.println("Turn: " + turn);
            System.out.println("Number of boats on board: " + gameBoard.getNumBoats());
            System.out.println("Your move, input in format: x,y");
            while (myScanner.hasNext()) { //as long as there is input

                String atk = myScanner.nextLine(); //user move input
                String[] split = atk.split(","); //get coordinates and split them into a string

                int x = Integer.parseInt(split[0]); //sets x to the user input of x
                int y = Integer.parseInt(split[1]); //sets y to the user input of y

                if (x > (choice - 1) || x < 0 || y < 0 || y > (choice - 1)) {
                    //input out of bounds, so penalty will be applied
                    System.out.println("Penalty: out of bounds! Skipping turn");
                    turn += 1;
                }
                else { //otherwise, fire at the Board
                    int move = gameBoard.fire(x, y);
                    gameBoard.sunk(); //sink

                    if (gameBoard.getSunkCount() == gameBoard.getNumBoats()) { //if sunk boats = inital boats, end game
                        System.out.println("Game over");
                        System.out.println("The game was won in " + turn + " turns!!!");

                        status = false; //end loops
                        break;
                    }

                    if (move == 1) { //already guessed a location, apply a penalty
                        System.out.println("Penalty: you shot at the same place twice! Skipping turn");
                        turn += 1;
                    }
                }

                if (debug.equalsIgnoreCase("Y")) { //print the debug board if debug is Y
                    gameBoard.print();
                } else {
                    gameBoard.display(); //print the normal userBoard
                }

                turn++;

                System.out.println("Turn:" + turn);
                System.out.println("Your move: x,y");
            }
        }

    }
}