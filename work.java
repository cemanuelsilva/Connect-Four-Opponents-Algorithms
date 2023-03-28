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
                            lastmove = configInicial[i][move];
                            break;

                        }
                    }
                }
            }
               
            //evaluation();
            this.depth++;

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
            for(int i = 0; i<6; i++){
                for(int j = 0; j<7; i++){
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
        
        
        
        char winner(){

            if(verifyColumn() != '-'){
                if(verifyColumn() == 'x'){
                    System.out.println("Ganhou: x");
                    return 'x';
                }
                else{
                    System.out.println("Ganhou: o");
                    return 'o';
                }
            }

            if(verifyLine() != '-'){
                if(verifyLine() == 'x'){
                    System.out.println("Ganhou: x");
                    return 'x';
                }
                else{
                    System.out.println("Ganhou: o");
                    return 'o';
                }
            }

            if(verifyDiagonalLeft() != '-'){
                if(verifyDiagonalLeft() == 'x'){
                    System.out.println("Ganhou: x");
                    return 'x';
                }
                else{
                    System.out.println("Ganhou: o");
                    return 'o';
                }
            }

            if(verifyDiagonalRight() != '-'){
                if(verifyDiagonalRight() == 'x'){
                    System.out.println("Ganhou: x");
                    return 'x';
                }
                else{
                    System.out.println("Ganhou: o");
                    return 'o';
                }
            }

            return '-';
        }
        
        char verifyLine(){
            
            for(int i = 0; i < 6; i++){
                for(int j = 0; j <4; j++){
                    if(configInicial[i][j] != '-'){
                        if((configInicial[i][j] == configInicial[i][j+1] && configInicial[i][j] == configInicial[i][j+2] && configInicial[i][j] == configInicial[i][j+3])){
                            //System.out.println("Ganhou: " + configInicial[i][j]);
                            return configInicial[i][j];
                        }
                    }
                }
            }

            return '-';
        }

        char verifyColumn(){

            //colunas
            for(int i = 0; i < 3; i++){
                for(int j = 0; j <7; j++){
                    if(configInicial[i][j] != '-'){
                        if((configInicial[i][j] == configInicial[i+1][j] && configInicial[i][j] == configInicial[i+2][j] && configInicial[i][j] == configInicial[i+3][j])){
                            //System.out.println("Ganhou: " + configInicial[i][j]);
                            return configInicial[i][j];
                        }
                    }
                }
            }

            return '-';
        }


            //diagonais
            char verifyDiagonalRight(){

                for(int i = 3; i < 6; i++){
                    for(int j = 0; j < 4; j++){
                        if(configInicial[i][j] != '-'){
                            if((configInicial[i][j] == configInicial[i-1][j+1] && configInicial[i][j] == configInicial[i-2][j+2] && configInicial[i][j] == configInicial[i-3][j+3])){
                                //System.out.println("Ganhou: " + configInicial[i][j]);
                                return configInicial[i][j];
                            }
                        }
                    }
                }
    
                return '-';
            }
     

        char verifyDiagonalLeft(){
            for(int i = 0; i < 3; i++){
                for(int j = 0; j < 4; j++){
                    if(configInicial[i][j] != '-'){
                        if((configInicial[i][j] == configInicial[i+1][j+1] && configInicial[i][j] == configInicial[i+2][j+2] && configInicial[i][j] == configInicial[i+3][j+3])){
                            //System.out.println("Ganhou: " + configInicial[i][j]);
                            return configInicial[i][j];
                        }
                    }
                }
            }
            
            return '-';
        }
    

        int evaluation(){

            int count = 0;
            
            count += evalColumn();
            count += evalDiagonalLeft();
            count += evalDiagonalRight();
            count += evalLines();
            
            System.out.println(count);
            return count;
        }
        
        int eval_sup(int countX, int countY){

                int count = 0;

                if(countY == 4){
                    count= 512;
                    }
                if(countX == 4){
                    count= 512;
                }
                
                if(countY == 3 && countX == 0){
                    count-= 50;
                }
                if(countY == 2 && countX == 0){
                    count-=10;
                }
                if(countY == 1 && countX == 0){
                    count-= 1;
                }

                if(countX == 3 && countY == 0){
                    count+= 50;
                }
                if(countX == 2 && countY == 0){
                    count+=10;
                }
                if(countX == 1 && countY == 0){
                    count+= 1;
                }

            return count;
        }

        int evalLines(){

            int count = 0;
            int countX = 0;
            int countY = 0;
        //nº de colunas
            for(int k = 0; k<4; k++){
                for(int i = 5; i > 0; i--){
                    for(int j = 0; j <4; j++){
                        if(configInicial[i][k+j] != '-'){
                            if((configInicial[i][k+j] == 'x')){
                                
                                countX++;
                            }else{
                                countY++;
                            }      
                        }
                    }
                    count += eval_sup(countX,countY);
                    countX =0;
                    countY = 0;
                }
            }

            return count;
        }


         int evalColumn(){

            int count = 0;
            int countX = 0;
            int countY = 0;
        //nº de colunas
        for(int k = 0; k<7; k++){
            for(int i = 0; i < 3; i++){
                for(int j = 0; j <4; j++){
                    if(configInicial[i+j][k] != '-'){
                        if((configInicial[i+j][k] == 'x')){
                            countX++;
                        }else{
                            countY++;
                        }      
                    }
                }
                count += eval_sup(countX,countY);
                countX =0;
                countY = 0;
            }
        }

        return count;
        }
       

        int evalDiagonalRight(){

            int count = 0;
            int countX = 0;
            int countY = 0;
        for(int k = 3; k<6; k++){
            for(int i = 0; i < 4; i++){
                for(int j = 0; j <4; j++){
                 //   System.out.println("config: " + configInicial[start-k][j+k] + " i: " + (start-k) + " j: " + (start+k));
                    if(configInicial[k-j][i+j] != '-'){
                        if((configInicial[k-j][i+j] == 'x')){
                            countX++;
                        }else{
                            countY++;
                        }      
                    }
                }
                count += eval_sup(countX,countY);
                countX =0;
                countY = 0;
            
            }
        }
        return count;
        }

        int evalDiagonalLeft(){
            
            int count = 0;
            int countX = 0;
            int countY = 0;
         for(int k = 0; k<3; k++){
            for(int i = 0; i < 4; i++){
                for(int j = 0; j <4; j++){
                    if(configInicial[k+j][i+j] != '-'){
                        if((configInicial[k+j][i+j] == 'x')){
                            countX++;
                        }
                        else{
                            countY++;
                        }
                    }
                }
                count += eval_sup(countX,countY);
                countX =0;
                countY = 0;

            }
        }
            return count;
        }

        /*
        int minimax(Game board, depth, isMaximizing){
            char result = winner();
            if(result != '-'){
                
            }
        }

        int bestmove(){

            int bestScore = -1000;
            int bestMove;
            int score;

            LinkedList<Game> tabuleiros = MakeDescendents();

            for(Game tab : tabuleiros){
                score = minimax(tab);
            }


            return 0;

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

                while(boas.winner() == '-'){
                    

                    System.out.println("It's " + boas.changePlay() + " turn!");
                    System.out.println("Make a move by choosing your coordinates to play (1 to 7).\n");
                    System.out.println("Possible moves: " + boas.PossibleMoves());  
                    System.out.println("\n");
                    

                    boas.printBoard();
                    boas.evaluation();
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
