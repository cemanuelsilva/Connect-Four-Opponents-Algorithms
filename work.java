import java.util.*;

public class work{

    final static int rows = 6; 
    final static int columns = 7;

    static class Game{

        Game pai;

        char configInicial[][];
        char lastmove;
        int lastMovement;
        int depth;
        int heuristic;


        Game(){

            configInicial = new char[6][7];

            for(int i = 0; i < 6; i++){
                for(int j = 0; j < 7; j++){
                    configInicial[i][j] = '-';
                }
            }

            lastmove = 'x';
            heuristic = 0;
            depth = 0;
            
        }

        public Game(char configInicial[][], char lastmove, int depth){

            this.configInicial = new char[6][7];
            
            for(int i=0; i<6; i++) {
                for(int j=0; j<7;j++) {
                    this.configInicial[i][j] = configInicial[i][j];
                }
            }
            this.lastmove = lastmove;
            this.depth = depth;

        }



        public void printBoard() {
      
        for (int x = 0; x < 6; x++) {
            for (int y = 0; y < 7; y++) {
                // just a print so it does not make new lines for every char
                System.out.print(configInicial[x][y] + " ");
            }
            // new line once one column (board[x][0] - board[x][8]) is printed
            // note: you proably want to turn around the x and y above since
            // I guess you want to print rows instead of columns
            System.out.println();
        }

        for(int i = 1; i < 8; i++){
            System.out.print(i + " ");
            }
            System.out.println();
        }
    

        // dar fix
        
        LinkedList<Integer> PossibleMoves(){

            LinkedList<Integer> possible = new LinkedList<>();

            for(int j = 0; j < 7; j++){
                if(configInicial[1][j] == '-'){
                    possible.add(j+1);
                }
            }

            /*
            for(int i = 0; i < possible.size(); i++){
                System.out.print(possible.get(i));
            }
            */

            return possible;

        }

        LinkedList<Game> MakeDescendents(){

            LinkedList<Integer> possible = PossibleMoves();
            LinkedList<Game> tabuleiros = new LinkedList<>();

            for(int move : possible){

                Game novo = new Game(this.configInicial, this.lastmove, this.depth);
                novo.pai = this;
                novo.MakeMove(move);
                novo.depth++;
                novo.printBoard();
                System.out.println("---Verify--");
                tabuleiros.add(novo);
                
            }

            return tabuleiros;

        }
              



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

        //human_play()

        //pc_play()

        void whoStarts(int escolha){

            if(escolha==1){
                lastmove='x';
                //human
            }
            else{
                lastmove='o';
                //pc
            }
        }


        boolean VerifyDraw(){
            int counter =0;
            for(int i = 0; i<rows; i++){
                for(int j = 0; j<columns; i++){
                    if(configInicial[i][j] != '-'){
                        counter++;
                    }
                }
            }
            if(counter == 42){
                System.out.println("It's a draw match!");
                System.out.println("Please, play again.");
                return true;
            }else{
                return false;
            }
        }


        boolean winner(){
            //linhas
            String rows = "";
            for(int i = 0; i<work.rows; i++){
                for(int j = 0; j<work.columns; j++){
                    rows += configInicial[i][j];
                }
                if(rows.contains("xxxx") || rows.contains("oooo")){
                    // ?: é um operador ternário
                    //(condition) ? (return if true) : (return if false);
                    System.out.println(rows.contains("xxxx") ? "Ganhou o jogador 1!" : "Ganhou o jogador 2!");
                    return true;
                }
                
                rows = "";
                
            }

            //colunas
            String columns = "";
            for(int j = 0; j<work.columns; j++){
                for(int i = 0; i<work.rows; i++){
                    columns +=configInicial[i][j];
                }
                if(columns.contains("xxxx") ||columns.contains("oooo")){
                    System.out.println(columns.contains("xxxx") ? "Ganhou o jogador 1!" : "Ganhou o jogador 2!");
                    return true;
                }
                columns = "";
            }


            //diagonais
            String diagonal1 = "";
            String diagonal2 = "";
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 4; j++) {
                    diagonal1 += configInicial[i][j] + configInicial[i+1][j+1] + configInicial[i+2][j+2] + configInicial[i+3][j+3];
                    diagonal2 += configInicial[i][j+3] + configInicial[i+1][j+2] + configInicial[i+2][j+1] + configInicial[i+3][j];
                    if (diagonal1.contains("xxxx") || diagonal1.contains("oooo")){
                        System.out.println(diagonal1.contains("xxxx") ? "Ganhou o jogador 1!!" : "Ganhou o jogador 2!!");
                         return true;
                    }
                    if (diagonal2.contains("xxxx") || diagonal2.contains("oooo")) {
                        System.out.println(diagonal2.contains("xxxx") ? "Ganhou o jogador 1!!" : "Ganhou o jogador 2!!");
                        return true;
                    }
                }  
                diagonal1 = "";
                diagonal2 = "";
            }
            return false;
        }

    }


        /* 
        void verify(){

            //Linha
            for (int row = 0; row < 5; row++) {
                for (int col = 0; col < 4; col++) {
                    if (configInicial[row][col] != '-' && configInicial[row][col] == configInicial[row][col+1] && configInicial[row][col] == configInicial[row][col+2] && configInicial[row][col] == configInicial[row][col+3]){
                        System.out.println("Acabou");
                    }
                }

        }
        
        
    }
    */

        /*
        boolean winner(){
            for(int i = 5; i < 0; i++){
                for(int j = 7; j < 0; j++){
                    if(configInicial[i][j] == )
                }
            }
        }
        */

    


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

                while(!boas.winner()){
                    System.out.println("It's " + boas.changePlay() + " turn!");
                    System.out.println("Make a move by choosing your coordinates to play (1 to 7).\n");
                    System.out.println("Possible moves: " + boas.PossibleMoves());  
                    System.out.println("\n");
                    

                    boas.printBoard();
                    int play = sc.nextInt();
                    boas.MakeMove(play);
                 //boas.MakeDescendents();
                 System.out.println();
                 System.out.println("-----------------------");
                }
                boas.printBoard();
                break;

            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            
                
        }

        }
    }
