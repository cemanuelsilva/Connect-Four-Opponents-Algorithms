import java.util.*;

public class work{

    static class Game{

        char configInicial[][];
        int lastmove;

        Game(){

            configInicial = new char[6][7];

            for(int i = 1; i < 6; i++){
                for(int j = 0; j < 7; j++){
                    configInicial[i][j] = '-';
                }
            }

            this.lastmove = lastmove;

        }



        public void printBoard() {
      
        for (int x = 1; x < 6; x++) {
            for (int y = 0; y < 7; y++) {
                // just a print so it does not make new lines for every char
                System.out.print(configInicial[x][y]);
            }
            // new line once one column (board[x][0] - board[x][8]) is printed
            // note: you proably want to turn around the x and y above since
            // I guess you want to print rows instead of columns
            System.out.println();
        }

        for(int i = 1; i < 8; i++){
            System.out.print(i);
            }
            System.out.println();
        }
    


        void PossibleMoves(){
            
        }

        void MakeMove(int move){

            move--;

            if(move >= 6 && move <= 0){
                System.out.println("Not possible!");   
            }
            else{
                for(int i = 5; i > 0; i--){
                        if(configInicial[i][move] != '-'){
                            continue;
                        }
                        else{
                            configInicial[i][move] = changePlay();
                            lastmove = changePlay();
                            break;
                        }
                    }
                }
               
        }

        char changePlay(){
            if (lastmove == 'x'){
                return 'o';
            }
            else{
                return 'x';
            }
            
        }

        /*
        boolean winner(){
            for(int i = 5; i < 0; i++){
                for(int j = 7; j < 0; j++){
                    if(configInicial[i][j] == )
                }
            }
        }
        */

    }


    public static void main(String Argrs[]){

        System.out.println("----------");
        System.out.println("Welcome!");
        System.out.println("----------");
        System.out.println("1º - Two Players");
        System.out.println("----------");

        Scanner sc = new Scanner(System.in);
        int answer = sc.nextInt();
        
        System.out.println("Have fun!");
        System.out.println("----------");

        switch(answer){
            case 1:
                Game boas = new Game();
                System.out.println("--------");
                System.out.println("START!");
                System.out.println("--------");
                boas.printBoard();
                while(true){
                 System.out.println("It's " + boas.changePlay() + " turn!");
                 int play = sc.nextInt();
                 boas.MakeMove(play); 
                 boas.printBoard();  
                }
                
        }

        }
    }
