import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int value1, value2;

        char[][] arr = {
                { ' ',' ',' '},
                { ' ',' ',' '},
                { ' ',' ',' '}
        };

        printBoard(arr);

        System.out.print("Enter the coordinates: ");
        String cord = in.nextLine();

        String[] splitStr = cord.split("\\s+");

        value1 = Integer.parseInt(splitStr[0]);
        value2 = Integer.parseInt(splitStr[1]);

        boolean test = false;

        int turn = 1;
        while(test == false){


            if (!isNumeric(splitStr[0])||!isNumeric(splitStr[1])) {
                System.out.println("You should enter numbers!");
                cord = in.nextLine();
            } else if ( value1 < 1 || value1 > 3 || value2 < 1 || value2 > 3 ){
                System.out.println("Coordinates should be from 1 to 3!");
                cord = in.nextLine();
            } else if ( arr[value1 - 1][value2 - 1] == 'X' || arr[value1 - 1][value2 - 1] == 'O' ){
                System.out.println("This cell is occupied! Choose another one!");
                cord = in.nextLine();
            } else {
                if(turn%2 == 0){
                    arr[value1 - 1][value2 - 1] = 'O';
                } else {
                    arr[value1 - 1][value2 - 1] = 'X';
                }
                printBoard(arr);
                turn++;
                checkSolved(arr);

                System.out.print("Enter the coordinates: ");
                cord = in.nextLine();
            }

            splitStr = cord.split("\\s+");

            value1 = Integer.parseInt(splitStr[0]);
            value2 = Integer.parseInt(splitStr[1]);



        }
    }


    public static void printBoard(char[][] arr){
        System.out.println("---------");

        System.out.println("|" + " " + arr[0][0] + " " + arr[0][1] + " " + arr[0][2] + " " + "|");
        System.out.println("|" + " " + arr[1][0] + " " + arr[1][1] + " " + arr[1][2] + " " + "|");
        System.out.println("|" + " " + arr[2][0] + " " + arr[2][1] + " " + arr[2][2] + " " + "|");

        System.out.println("---------");
    }

    public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }

    public static void checkSolved (char[][] arr) {

        int sum = 0;
        int res = 0 ;
        int numX = 0;
        int numO = 0;
        char win = '0';

        int[][] intArr = new int[3][3];

        //test for num of Xs and Os
        for(int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (arr[i][j] == 'O'){
                    numO++;
                    intArr[i][j] = 79;
                } else if (arr[i][j] == 'X'){
                    numX++;
                    intArr[i][j] = 88;
                } else intArr[i][j] = 0;
            }
        }

        //rows
        for(int i = 0; i < 3; i++) {
            sum = 0;
            for (int j = 0; j < 3; j++) {
                sum = sum + intArr[i][j];
            }
            if (sum == 264 || sum == 237){
                res++;
                if(sum ==264) win = 'X';
                if (sum == 237) win = 'O';
                break;
            }
        }

        //columns
        for(int i = 0; i < 3; i++) {
            sum = 0;
            for (int j = 0; j < 3; j++) {
                sum = sum + intArr[j][i];
            }
            if (sum == 264 || sum == 237){
                if(sum ==264) win = 'X';
                if (sum == 237) win = 'O';
                res++;
            }
        }

        //test diagonal
        if(intArr[0][0] == intArr[1][1] && intArr[1][1] == intArr[2][2] && intArr[0][0] != ' ')
        {
            win = (char) intArr[0][0];
            res++;
        }

        //Check diagonal top right to bottom left
        if(intArr[0][2] == intArr[1][1] && intArr[1][1] == intArr[2][0] && intArr[0][2] != ' ') {
            win = (char) intArr[0][2];
            res++;
        }


        // final tests
        if(Math.abs(numX-numO)>1) {
            System.out.println("Impossible");
            System.exit(0);
        } else if (win == 'X')  {
            System.out.println("X wins");
            System.exit(0);
        } else if (win == 'O') {
            System.out.println("O wins");
            System.exit(0);
        } else if (numX < 5 && numO < 5 ) {
            System.out.println("Game not finished");
        } else if(Math.abs(numX-numO) == 1) {
            System.out.println("Draw");
            System.exit(0);
        }


    }


}

