import java.util.*;

public class work{

    static class Game{

        Game pai;

        char configInicial[][];
        char lastmove;
        int lastMovement;
        int depth;
        int heuristic;


        Game(){

            configInicial = new char[6][7];

            for(int i = 1; i < 6; i++){
                for(int j = 0; j < 7; j++){
                    configInicial[i][j] = '-';
                }
            }

            this.lastmove = lastmove;
            this.depth = depth;

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
    

        // dar fix
        /*
        Vector<Integer> PossibleMoves(){

            Vector<Integer> possible = new Vector<>();

            for(int i = 5; i > 0; i++){
                for(int j = 2; j < 8; j++){
                    if(MakeMove(j) == true){
                        possible.add(j);
                    }
                    else{
                        continue;
                    }
                }
            }


            for(int i = 0; i < possible.size(); i++){
                System.out.println(possible.get(i));
            
        }
        return possible;
        }
        */        



        void MakeMove(int move){
            // 1 - 7

            lastMovement = move;
            move--;

            if(move > 6 || move < 0){
                System.out.println("Not possible!");
                return;   
            }
            else{
                if(configInicial[1][move] != '-'){
                    System.out.println("Not possible!");
                    return;
                    
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
               
        }

        char changePlay(){
            if (lastmove == 'x'){
                return 'o';
            }
            else{
                return 'x';
            }
            
        }

        void whoStarts(int escolha){

            if(escolha==1){
                lastmove='x';
            }
            else{
                lastmove='o';
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
        System.out.println("1º - Two Players");
        System.out.println("2º - Minimax");
        System.out.println("3º - Alpha");
        System.out.println("4º - MonteCarlo");
        System.out.println("----------");

        Scanner sc = new Scanner(System.in);
        int answer = sc.nextInt();
        
        System.out.println("Have fun!");
        System.out.println("----------\n");

        switch(answer){
            case 1:
                System.out.println("----------");
                System.out.println("1º- Ir em primeiro");
                System.out.println("2º- Ir em segundo");
                System.out.println("-----------");

                Game boas = new Game();
                int escolha = 0;

                while(escolha != 1 && escolha != 2){
                escolha = sc.nextInt();
                if((escolha != 1 || escolha != 2)){
                    System.out.println("--------------------------");
                    System.out.println("Insira uma opção correta");
                    System.out.println("--------------------------");
                    }
                }

                boas.whoStarts(escolha);

                System.out.println("--------");
                System.out.println("START!");
                System.out.println("--------\n");

                //boas.PossibleMoves();  
                while(true){
                 System.out.println("It's " + boas.changePlay() + " turn!\n");
                 boas.printBoard();
                 int play = sc.nextInt();
                 boas.MakeMove(play);
                 System.out.println();
                 System.out.println("-----------------------");
                }
                //break;

            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            
                
        }

        }
    }
