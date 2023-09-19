/*
how to get individual cells of a boat
how to get length of boat
sunk help
how to format so that each cell is even
*/
public class Board {
    private int numBoats;
    private Battleboat[] boats;
    private Cell[][] board;
    private int[] boatSizes;
    private String strBoard = "";
    private int sunkCount = 0;

    public Board(int mode) {
        // class constructor
        /*
        creates a board with size, determine how many boats, boat length
         */
        board = new Cell[mode][mode];
        if (mode == 3) {
            numBoats = 1;
            boatSizes = new int[]{2};
        }
        if (mode == 6) {
            numBoats = 3;
            boatSizes = new int[]{2,3,4};
        }
        if (mode == 9) {
            numBoats = 5;
            boatSizes = new int[]{2, 3, 3, 4, 5};
        }

        boats = new Battleboat[numBoats];
        for (int i = 0; i < mode; i++){
            for (int j = 0; j < mode; j++) {
                board[i][j] = new Cell(i, j, '-');
            }
        }
    }


    public void sunk() {
        int check = 0;
        for (Battleboat boat : boats) {
            for (Cell chunk: boat.getSpaces()) {
                if(chunk.getStatus() == 'H') {
                    check++;
                }
            }
            System.out.println("check " + check);
            if (check == boat.getSize()) {
                if (boat.getSunk() == false) {
                    boat.setSunk(true);
                    System.out.println("Sunk");
                    sunkCount += 1;
                }

            }
        }
    }

    public int getNumBoats() {
        return this.numBoats;
    }
    public void placeBoats() {
        // creates a boat object
        // adds boat object to boat[]
        // places boats randomly on the board

        int i;
        int rand_x, rand_y;
        //Battleboat[] boats = new Battleboat[][numBoats];
        for (i = 0; i < numBoats; i++) {
            boolean switcher = true; //monitor the while loop
            while (switcher) { //iterates through while y is true until smth is found
                Battleboat btl = new Battleboat(boatSizes[i]);
                boats[i] = btl;

                int pass_count = 0;

                if (btl.getOrientation()) { //vertical is true, checking to see if there is space
                    rand_x = (int) Math.floor(Math.random() * (board.length));
                    rand_y = (int) Math.floor((board.length - boatSizes[i] +1) * Math.random());

                    for (int j = rand_y; j < rand_y + boatSizes[i]; j++) {
                        if (board[j][rand_x].getStatus() == '-') {
                            pass_count++;
                        }
                    }
                } else { //horizontal is true, checking to see if there is space
                    rand_y = (int) Math.floor(Math.random() * (board.length));
                    rand_x = (int) (int) Math.floor((board.length - boatSizes[i] +1) * Math.random());
                    for (int k = rand_x; k < rand_x + boatSizes[i]; k++) {
                        if (board[rand_y][k].getStatus() == '-') {
                            pass_count++;
                        }
                    }
                }
                Cell[] cells = new Cell[boatSizes[i]];
                if (boatSizes[i] == pass_count) {
                    if (btl.getOrientation()) { //vertical is true
                        for (int j = rand_y; j < rand_y + boatSizes[i]; j++) {
                            Cell cell = new Cell(j, rand_x, 'B');
                            board[j][rand_x].setStatus('B');
                            cells[j-rand_y] = cell;
                            switcher = false;
                        }
                    } else if (!btl.getOrientation()) { //horizontal is true
                        for (int j = rand_x; j < rand_x + boatSizes[i]; j++) {
                            Cell cell = new Cell(rand_y, j, 'B');
                            board[rand_y][j].setStatus('B');
                            cells[j-rand_x] = cell;
                            switcher = false;
                        }
                    }
                    btl.setSpaces(cells);
                }
            }
        }
    }

    public int fire(int x, int y) {
        // handles attacking a coordinate
        //if user attacks a coordinate and there is a b there, set to hit
        int penalty = 0;
        if (board[y][x].getStatus() == 'B') {
            board[y][x].setStatus('H');
            for (Battleboat boat : boats) {
                for (Cell chunk : boat.getSpaces()) {
                    if (chunk.getRow() == y && chunk.getCol() == x) {
                        chunk.setStatus('H');
                        System.out.println("Hit");
                    }
                }
                //need a way to tell when boat is sunk
            }
        }
        else if (board[y][x].getStatus() == '-') {
            board[y][x].setStatus('M');
            System.out.println("Miss");
        }
        else if (board[y][x].getStatus() != '-') {
            penalty = 1;
        }



        return penalty;




    }

    public void display() {
        strBoard = "";
        // prints out the final board state every turn
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (getCell(i,j) == 'B') {
                    strBoard += "-";
                    strBoard += " ";
                }
                else {
                    strBoard += getCell(i, j);
                    strBoard += " ";
                }
            }
            strBoard += "\n";
        }

        System.out.println(strBoard);
    }

    public void print() {

        // prints out the full revealed board
        strBoard = "";
        // prints out the full revealed board
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                strBoard += getCell(i, j);
                strBoard += " ";
            }
            strBoard += "\n";
        }


            System.out.println(strBoard);

            for (Battleboat boat : boats) {
                for (Cell chunk : boat.getSpaces()) {
                    System.out.println(chunk.getCol() + ", " + chunk.getRow() + " " + chunk.getStatus());
                }
            }


    }
    public char getCell(int x, int y) {
        return board[x][y].getStatus();
    }


    public int getSunkCount() {return this.sunkCount;}
}
