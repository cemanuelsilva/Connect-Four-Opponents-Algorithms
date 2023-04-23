import java.util.*;
import java.lang.Math;

public class work{

    static int countPruning = 0;
    static int countAlphaBeta = 0;
    static int countMinimax = 0;

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
            this.heuristic = evaluation();

        }

        //============CLASS SUPPORT ======================================//

        public void printBoard() {
      
        for (int x = 0; x < 6; x++) {
            for (int y = 0; y < 7; y++) {
                
                System.out.print(configInicial[x][y] + " ");
            }
            
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
                if(configInicial[0][j] == '-'){
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
                //novo.printBoard();
                //System.out.println("---Verify--");
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
                if(configInicial[0][move] != '-'){
                    System.out.println("Not possible!");
                    return;
                    
                }

                else{
                for(int i = 5; i > -1; i--){
                        if(configInicial[i][move] != '-'){
                            continue;
                        }
                        else{
                            configInicial[i][move] = lastmove;
                            lastmove = configInicial[i][move];

                            changePlay();
                            this.depth++;
                            break;

                        }
                    }
                }
            }


        }

        void changePlay(){
            if (lastmove == 'x'){
                 lastmove = 'o';
            }
            else{
                lastmove= 'x';
            }
            
        }


        //==========================================================//

        //============== VERIFY =======================================//
    
        boolean VerifyDraw(){
            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < 7; j++) {
                    if (configInicial[i][j] == '-') {
                        return false;
                    }
                }
            }
            return true;
        }
        
        
        
        char winner(){

            if(verifyColumn() != '-'){
                if(verifyColumn() == 'x'){
                    //System.out.println("Ganhou: x");
                    return 'x';
                }
                else{
                    //System.out.println("Ganhou: o");
                    return 'o';
                }
            }

            if(verifyLine() != '-'){
                if(verifyLine() == 'x'){
                    //System.out.println("Ganhou: x");
                    return 'x';
                }
                else{
                    //System.out.println("Ganhou: o");
                    return 'o';
                }
            }

            if(verifyDiagonalLeft() != '-'){
                if(verifyDiagonalLeft() == 'x'){
                    //System.out.println("Ganhou: x");
                    return 'x';
                }
                else{
                    //System.out.println("Ganhou: o");
                    return 'o';
                }
            }

            if(verifyDiagonalRight() != '-'){
                if(verifyDiagonalRight() == 'x'){
                    //System.out.println("Ganhou: x");
                    return 'x';
                }
                else{
                    //System.out.println("Ganhou: o");
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

                for(int i = 5; i > 3; i--){
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
        //==========================================================//
    
        //===================EVALUATION =============================//

        int evaluation(){

            int count = 0;
            
            count += evalColumn();
            count += evalDiagonalLeft();
            count += evalDiagonalRight();
            count += evalLines();
            
            //System.out.println(count);
            return count;
        }
        
        int eval_sup(int countX, int countY){

                int count = 0;

                if(countY == 4){
                    count= -512;
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

        //==========================================================//


        public int FirstMove(Game bestOutcome) {
            while(bestOutcome.pai != this) {
                bestOutcome = bestOutcome.pai;
            }
            return bestOutcome.lastMovement;
        }


    }

    //=========== ALGORITHMS ===============================================//

    public static int MiniMaxComputerMovePlayer1(Game board, char player, int maxDepth) {
        Game bestOutcome = MiniMax_decision(board, player, maxDepth);
        // bestOutcome.PrintPath();
        return board.FirstMove(bestOutcome);
    }
    
    
    public static Game MiniMax_decision(Game board, char player, int maxDepth) {
        
        maxDepth = maxDepth + board.depth;
        board.pai = null;
        LinkedList<Game> descendents = board.MakeDescendents();
        //n.pai = null
        Game best = null;
        for(Game child : descendents) {
            Game g = MiniMax_value(child, maxDepth, player);
            
            if(best == null) {
                best = g;
            }
             else if (player == 'x' && best.heuristic > g.heuristic) {
            best = g;
            } else if (player == 'o' && best.heuristic < g.heuristic) {
            best = g;
        }
    
        }
    
        return best;
    }
    
    static Game MiniMax_value(Game board, int maxDepth, char player) {
        
        countMinimax++;

        if(board.depth > maxDepth || board.winner() != '-') {
            return board;
        }
    
        LinkedList<Game> descendents = board.MakeDescendents();
    
        if(descendents.size() == 0) {
            return board;
        }
    
        Game best = null;
    
        for(Game child : descendents) {
            
            if (board.lastmove == player) { // min node
                
                Game bestSubtreeChild = MiniMax_value(child, maxDepth,player);
                if (best == null || (player == 'x' && best.heuristic > bestSubtreeChild.heuristic) || (player == 'o' && best.heuristic < bestSubtreeChild.heuristic)) {
                    best = bestSubtreeChild;
                }

            } else { // max node
                Game bestSubtreeChild = MiniMax_value(child, maxDepth, player);
                if (best == null || (player == 'x' && best.heuristic > bestSubtreeChild.heuristic) || (player == 'o' && best.heuristic < bestSubtreeChild.heuristic)) {
                    best = bestSubtreeChild;
                }

            }
    
        
        }
        return best;
    }

    public static int AlphaBetaComputerMove(Game board, char player, int maxDepth) {
        Game bestOutcome = AlphaBeta_decision(board, player, maxDepth);
        return board.FirstMove(bestOutcome);
    }
    
    static Game AlphaBeta_decision(Game board, char playerChar, int maxDepth) {
        maxDepth = maxDepth + board.depth;
        LinkedList<Game> descendents = board.MakeDescendents();
        Game best = null;
        for (Game child : descendents) {
            Game g = AlphaBeta_value(child, maxDepth, Integer.MIN_VALUE, Integer.MAX_VALUE, playerChar);
            if (best == null) {
                best = g;
            } else if (playerChar == 'x' && best.heuristic > g.heuristic) {
                best = g;
            } else if (playerChar == 'o' && best.heuristic < g.heuristic) {
                best = g;
            }
        }
        return best;
    }
    
    static Game AlphaBeta_value(Game board, int maxDepth, int alpha, int beta, char playerChar) {
        
        countAlphaBeta++;

        if (board.depth >= maxDepth || board.winner() != '-') {
            return board;
        }
        LinkedList<Game> descendents = board.MakeDescendents();
        if (descendents.size() == 0) {
            return board;
        }
        Game best = null;

        for (Game child : descendents) {

            
            if (board.lastmove == playerChar) { // min node
                
                Game bestSubtreeChild = AlphaBeta_value(child, maxDepth, alpha, beta, playerChar);
                if (best == null || (playerChar == 'x' && best.heuristic > bestSubtreeChild.heuristic) || (playerChar == 'o' && best.heuristic < bestSubtreeChild.heuristic)) {
                    best = bestSubtreeChild;
                }

                beta = Math.min(beta, bestSubtreeChild.heuristic);
                if (beta >= alpha) {
                    countPruning++;
                    return best;
                }
            } else { // max node
                Game bestSubtreeChild = AlphaBeta_value(child, maxDepth, alpha, beta, playerChar);
                if (best == null || (playerChar == 'x' && best.heuristic > bestSubtreeChild.heuristic) || (playerChar == 'o' && best.heuristic < bestSubtreeChild.heuristic)) {
                    best = bestSubtreeChild;
                }

                alpha = Math.max(alpha, bestSubtreeChild.heuristic);
                if (alpha <= beta) {
                    countPruning++;
                    return best;
                }
            }
        }
        return best;
    }

    

    //==========================================================//

    static void initiateGame(int player1, int starts) {
        Scanner sc = new Scanner(System.in);
        Game board = new Game();
        boolean playerTurn = true;
        int move;
        int answer;
        char boardInfo, boardInfo2, boardBot;
        int maxDepth;
    
        board.printBoard();
        

        if (starts == 1) {
            playerTurn = true;
            boardInfo = 'o';
            boardInfo2 = 'x';
            
        } else {
            playerTurn = false;
            boardInfo = 'x';
            boardInfo2 = 'o';
        
        }
        
       if(player1 == 2){
        maxDepth = 13;
       }
       else if(player1 == 3){
        maxDepth = 20;
       }
       else{
        maxDepth = 17;
       }

            

        while (board.winner() == '-') {
            
            if(board.VerifyDraw() == true){
                System.out.println("\n\nDRAW!");
                return;
            }

             switch (player1) {
                case 1:
                System.out.println("----------------------------------");
                System.out.println("It's " + board.lastmove + " turn!");
                board.printBoard();
                    if(playerTurn){
                    answer = sc.nextInt();
                    while(answer < 1 || answer > 7){
                        System.out.println("Please select a number between 1-7");
                        board.printBoard();
                        answer = sc.nextInt();
                        }
                        
                    }
                    else{
                        answer = sc.nextInt();
                        while(answer < 1 || answer > 7){
                        System.out.println("Please select a number between 1-7");
                        board.printBoard();
                        answer = sc.nextInt();
                        }
                    }

                    board.MakeMove(answer);
                    break;

                    case 2:
                    System.out.println("----------------------------------");
                    System.out.println("It's " + board.lastmove + " turn!");
                    board.printBoard();
                    if(playerTurn){
                        answer = sc.nextInt();
                        while(answer < 1 || answer > 7){
                            System.out.println("Please select a number between 1-7");
                            board.printBoard();
                            answer = sc.nextInt();
                            }
                            move = answer;
                            playerTurn = false;
                        }
                    else{
                         move = MiniMaxComputerMovePlayer1(board, boardInfo2, maxDepth);
                         playerTurn = true;
                        
                        }
                    
                    board.MakeMove(move);
                    break;

                    case 3:
                    System.out.println("----------------------------------");
                    System.out.println("It's " + board.lastmove + " turn!");
                    board.printBoard();
                    if(playerTurn){
                        answer = sc.nextInt();
                        while(answer < 1 || answer > 7){
                            System.out.println("Please select a number between 1-7");
                            board.printBoard();
                            answer = sc.nextInt();
                            }
                            move = answer;
                            playerTurn = false;
                        }
                    else{
                         move = AlphaBetaComputerMove(board, boardInfo2, maxDepth);
                         playerTurn = true;
                         
                        }
                    
                    board.MakeMove(move);
                    break;

                    case 4:
                    System.out.println("----------------------------------");
                    System.out.println("It's " + board.lastmove + " turn!");
                    board.printBoard();
                    if(playerTurn){
                        move = MiniMaxComputerMovePlayer1(board, boardInfo, maxDepth);
                        playerTurn = false;
                        }
                    else{
                         move = MiniMaxComputerMovePlayer1(board, boardInfo2, maxDepth);
                         playerTurn = true;
                         
                        }
                    
                    board.MakeMove(move);
                    break;

                    case 5:
                    System.out.println("----------------------------------");
                    System.out.println("It's " + board.lastmove + " turn!");
                    board.printBoard();
                    if(playerTurn){
                        move = AlphaBetaComputerMove(board, boardInfo, maxDepth);
                        playerTurn = false;
                        }
                    else{
                         move = AlphaBetaComputerMove(board, boardInfo2, maxDepth);
                         playerTurn = true;
                         
                        }
                    
                    board.MakeMove(move);
                    break;

                    case 6:
                    System.out.println("----------------------------------");
                    System.out.println("It's " + board.lastmove + " turn!");
                    board.printBoard();
                    if(playerTurn){
                        move = MiniMaxComputerMovePlayer1(board, boardInfo, maxDepth);
                        playerTurn = false;
                        }
                    else{
                         move = AlphaBetaComputerMove(board, boardInfo2, maxDepth);
                         playerTurn = true;
                         
                        }
                    
                    board.MakeMove(move);
                    break;
                }   
                
            }
                      
            //int heuristica = board.heuristic();
            //System.out.println("Heuristic: " + heuristica);
            //System.out.println("Nodes Pruned: " + board.prunedNodes);
            if(board.lastmove == 'x'){
            System.out.println("\n\n Player 'O' WON!!");
            }
            else{
                System.out.println("\n\n Player 'X' WON!!");
            }
    
        }
        
    

    


    public static void main(String Argrs[]){
        
        //==== VARIAVEIS ====//
        
        boolean humanPlay = true;
        boolean pcPlay = true;
        Scanner sc = new Scanner(System.in);
        int player1;
        int player2;
        int answer = 0;
        int Starts;

        //====================//
        
        //==== MENU ====//

        while(answer < 1 || answer > 7){
            System.out.println("----------");
            System.out.println("Welcome!");
            System.out.println("Game Selection ");
            System.out.println("1º - P1: Human vs P2: Human");
            System.out.println("2º - P1: Human Vs P2: Minimax");
            System.out.println("3º - P1: Human Vs P2: AlphaBeta");
            System.out.println("4º - P1: Minimax Vs P2: Minimax");
            System.out.println("5º - P1: AlphaBeta vs P2: AlphaBeta");
            System.out.println("6º - P1: Minimax vs P2: AlphaBeta");
            System.out.println("----------");
            answer = sc.nextInt();
        }

        player1 = answer;
        answer = 0;
        

        while(answer < 1 || answer > 3){
            System.out.println("----------");
            System.out.println("Who Starts? ");
            System.out.println("1º - Player 1");
            System.out.println("2º - Player 2");
            answer = sc.nextInt();
        }

        Starts = answer;
        System.out.println("Have fun!");
        System.out.println("----------\n");

        //==================//

        long startTime = System.currentTimeMillis();
        
        initiateGame(player1, Starts);
        
        long endTime = System.currentTimeMillis();
        long elapsedTime = endTime - startTime;
        double elapsedSeconds = (double)elapsedTime / 1000.0;
        System.out.println("Elapsed time: " + elapsedSeconds + " seconds");
        System.out.println("Pruning: " + countPruning);
        System.out.println("Minimax nodes: " + countMinimax);
        System.out.println("AlphaBeta nodes: " + countAlphaBeta);
        
        }
    }