# Connect Four - Opponent´s Algorithms 

### Game Modes

* P1: Human vs P2: Human
* P1: Human Vs P2: Minimax
* P1: Human Vs P2: AlphaBeta
* P1: Minimax Vs P2: Minimax
* P1: AlphaBeta vs P2: AlphaBeta
* P1: Minimax vs P2: AlphaBeta

### Possible Changes

Starting on line **617**, you can change the **maxDepth**, affecting the performance of Minimax and AlphaBeta algorithms depending how do you want them to predict and play. 

```java
//Player Plays 
if(player1 == 2){
   maxDepth = 2;
 }
//Player Plays
else if(player1 == 3){
    maxDepth = 2;
  }
//Computer vs Computer
 else{
    maxDepth = 10;
  }
```
