package DominoeProject;
import java.util.*;
import java.lang.Thread;

public class GameLogic {
    public static void main(String[] args) {
        Scanner myObj = new Scanner(System.in);
        
        ArrayList<DominoeTile> dominoList = new ArrayList<DominoeTile>(){
            {
                add(new DominoeTile(0, 0));
                add(new DominoeTile(1, 1));
                add(new DominoeTile(1, 0));
                add(new DominoeTile(2, 0));
                add(new DominoeTile(2, 1));
                add(new DominoeTile(2, 2));
                add(new DominoeTile(3, 0));
                add(new DominoeTile(3, 1));
                add(new DominoeTile(3, 2));
                add(new DominoeTile(3, 3));
                add(new DominoeTile(4, 0));
                add(new DominoeTile(4, 1));
                add(new DominoeTile(4, 2));
                add(new DominoeTile(4, 3));
                add(new DominoeTile(4, 4));
                add(new DominoeTile(5, 0));
                add(new DominoeTile(5, 1));
                add(new DominoeTile(5, 2));
                add(new DominoeTile(5, 3));
                add(new DominoeTile(5, 4));
                add(new DominoeTile(5, 5));
                add(new DominoeTile(6, 0));
                add(new DominoeTile(6, 1));
                add(new DominoeTile(6, 2));
                add(new DominoeTile(6, 3));
                add(new DominoeTile(6, 4));
                add(new DominoeTile(6, 5));
                add(new DominoeTile(6, 6));
            }
        };

        CDominoes dObj = new CDominoes(dominoList);
        
        CPlayer curPlayer = null;

        CPlayer player1 = new CPlayer(1, CRandom.randomizedDraw(dObj.getDominoes(), 10));

        CPlayer player2 = new CPlayer(2, CRandom.randomizedDraw(dObj.getDominoes(), 10));

        ArrayList<DominoeTile> domSequence = new ArrayList<>();
        System.out.println("Welcome to the 2 player Dominoes Game");
        try{
        	Thread.sleep(1000);
        } catch (InterruptedException e){
            System.out.println(e);
        }   

        System.out.println("Randomly selecting a player to go first");
        Random rand = new Random();
        if(rand.nextInt(2) == 1){
            curPlayer = player1;
        } else {
            curPlayer = player2;
        }
        try{
        Thread.sleep(2000);
        } catch (InterruptedException m){
            System.out.println(m);
        }   

        System.out.println(String.format("Player %s will play the first",curPlayer.getId()));
        try{
        Thread.sleep(1000);
        } catch (InterruptedException e){
            System.out.println(e);
        }   

        boolean winner = false;
        boolean isGameDraw = false;
        Integer head = -1;
        Integer tail = -1;

        boolean isFirstMove = true;
        
        while(!winner){
            boolean canPlay = false;
            boolean switchPlayer = false;

            while(!canPlay){

                
                if(dObj.getDominoes().isEmpty()){
                    ArrayList<DominoeTile> bothPlayerTiles = new ArrayList<>();
                    bothPlayerTiles.addAll(player1.getTiles());
                    bothPlayerTiles.addAll(player2.getTiles());
                    boolean conditionMet = false;
                    for(DominoeTile dce: bothPlayerTiles){
                        if(dce.getLeftTile() == head || dce.getLeftTile() == tail || dce.getRightTile() == head || dce.getRightTile() == tail ){
                            conditionMet = true;
                        }
                        if(!conditionMet) isGameDraw = true; 
                }
            }
                


                for(DominoeTile dc: curPlayer.getTiles()){
                    if(dc.getLeftTile() == head || dc.getLeftTile() == tail || dc.getRightTile() == head || dc.getRightTile() == tail ){
                        canPlay = true;
                    }
                }

                if(!canPlay){
                    if(dObj.getDominoes().isEmpty()){
                        CTable.print(curPlayer, domSequence);
                        System.out.println(String.format("Head: %s | Tail: %s", head, tail));
                        System.out.println("Out of dominoes to draw from pile. ");
                        switchPlayer = true;
                        canPlay = true;
                    } else{
                        
                        if(isFirstMove) {
                            canPlay= true;
                        } 
                        else{

                            System.out.println("please select a new playable domino from below");
                            
                            for(DominoeTile cp: dObj.getDominoes()){
                                System.out.println(String.format("%s: [?,?]", dObj.getDominoes().indexOf(cp)));
                            }

                            boolean isPlayValid = false;
                            Integer inputVal = null;

                            System.out.println("Draw a domino you like");

                            while(!isPlayValid){
                                try{
                                inputVal = myObj.nextInt();
                                if (inputVal >= 0 && inputVal < dObj.getDominoes().size()){
                                    isPlayValid = true;
                                } else {
                                    System.out.println("InputVal is "+inputVal+" dom: "+dObj.getDominoes().size());
                                    System.out.println("Invalid option. Please choose a number from the above displayed.");
                                }
                                } catch (InputMismatchException m) {
                                    System.out.println("Invalid number provided as input please re-enter");
                                }
                                myObj.nextLine();
                            }

                            DominoeTile chosenChip = dObj.getDominoes().get(inputVal);
                            System.out.println(String.format("Chosen Domino: [%s, %s]", chosenChip.getLeftTile(), chosenChip.getRightTile()));

                            curPlayer.getTiles().add(chosenChip);
                            dObj.getDominoes().remove(chosenChip);
                        }
                        
                    }
                }
            }

                if(canPlay && !switchPlayer){
                    boolean isValidChoice = false;

                    while (!isValidChoice)
                    {

                        try{
                            Thread.sleep(1000);
                            } catch (InterruptedException m){
                                System.out.println(m);
                            }   
                        System.out.println(String.format("---Player %s Chance Now---", curPlayer.getId()));

                        CTable.print(curPlayer, domSequence);
                        
                        boolean isValidPlay = false;
                        Integer playInp = null;

                        while(!isValidPlay){
                        System.out.println("Choose a domino to play from your dominoes");
                        try{
                        playInp = myObj.nextInt();
                        if(playInp >= 0 && playInp < curPlayer.getTiles().size()){
                            isValidPlay = true;
                        } else {
                            System.out.println("Choose a valid option from above");
                        }
                        } catch(InputMismatchException m){
                            System.out.println("Please provide a valid number as input");
                        } 
                        myObj.nextLine();
                        }  
                        DominoeTile chosenChip2 = curPlayer.getTiles().get(playInp);
                        System.out.println(String.format("Chosen Domino: [%s, %s]", chosenChip2.getLeftTile(), chosenChip2.getRightTile()));

                        if( (chosenChip2.getLeftTile() == tail && chosenChip2.getLeftTile() == head)
                         || (chosenChip2.getRightTile() == tail && chosenChip2.getRightTile() == head)
                         || (chosenChip2.getLeftTile() == head && chosenChip2.getRightTile() == tail)
                         || (chosenChip2.getLeftTile() == tail && chosenChip2.getRightTile() == head)
                         ){
                            boolean validEnd = false;
                            Integer endSelected = null;
                            while (!validEnd){
                                System.out.println("This domino can be played on either end of the Sequence. Choose Head or Tail");
                                System.out.println("1: Head");
                                System.out.println("2: Tail");
                                try{
                                endSelected = myObj.nextInt();
                                if(endSelected == 1 || endSelected == 2 ){
                                    validEnd = true;
                                } else {
                                    System.out.println("You choosed an invalid option");
                                }
                                } catch (InputMismatchException m){
                                    System.out.println("Choose 1  or 2");
                                }
                                myObj.nextLine();
                            }
                            if(endSelected == 1){
                                if (chosenChip2.getLeftTile() == head){
                                    domSequence.add(0,new DominoeTile(chosenChip2.getRightTile(), chosenChip2.getLeftTile()));
                                    head = chosenChip2.getRightTile();
                                    isValidChoice=true;
                                    curPlayer.getTiles().remove(chosenChip2);
                                } else{
                                    domSequence.add(0,new DominoeTile(chosenChip2.getLeftTile(), chosenChip2.getRightTile()));
                                    head = chosenChip2.getLeftTile();
                                    isValidChoice=true;
                                    curPlayer.getTiles().remove(chosenChip2);
                                }
                            } else {
                                if(chosenChip2.getLeftTile() == tail){
                                    domSequence.add(new DominoeTile(chosenChip2.getLeftTile(), chosenChip2.getRightTile()));
                                    tail = chosenChip2.getRightTile();
                                    isValidChoice=true;
                                    curPlayer.getTiles().remove(chosenChip2);

                                } else {
                                    domSequence.add(new DominoeTile(chosenChip2.getRightTile(), chosenChip2.getLeftTile()));
                                    tail = chosenChip2.getLeftTile();
                                    isValidChoice=true;
                                    curPlayer.getTiles().remove(chosenChip2);

                                }
                            }

                        } else if (isFirstMove){
                            domSequence.add(chosenChip2);
                            head = chosenChip2.getLeftTile();
                            tail = chosenChip2.getRightTile();
                            curPlayer.getTiles().remove(chosenChip2);

                            isFirstMove = false;
                            if(curPlayer == player1){
                                curPlayer = player2;
                            } else {
                                curPlayer = player1;
                            }
                        } else if (chosenChip2.getLeftTile() == head){
                            domSequence.add(0, new DominoeTile(chosenChip2.getRightTile(), chosenChip2.getLeftTile()));
                            head = chosenChip2.getRightTile();
                            isValidChoice = true;
                            curPlayer.getTiles().remove(chosenChip2);
                        } else if (chosenChip2.getRightTile() == head){
                            domSequence.add(0, chosenChip2);
                            head = chosenChip2.getLeftTile();
                            isValidChoice = true;
                            curPlayer.getTiles().remove(chosenChip2);
                        } else if (chosenChip2.getLeftTile() == tail){
                            domSequence.add(chosenChip2);
                            tail = chosenChip2.getRightTile();
                            isValidChoice = true;
                            curPlayer.getTiles().remove(chosenChip2);
                        } else if (chosenChip2.getRightTile() == tail){
                            domSequence.add( new DominoeTile(chosenChip2.getRightTile(), chosenChip2.getLeftTile()));
                            tail = chosenChip2.getLeftTile();
                            isValidChoice = true;
                            curPlayer.getTiles().remove(chosenChip2);
                        } else {
                            System.out.println("Invalid domino chose, please rechoose the domino.");
                        }
                        
                     }    
                }

                if(curPlayer.getTiles().size() == 0){
                    System.out.println(String.format("Player %s has won.", curPlayer.getId()));
                    if(curPlayer == player1) {
                        System.out.println("Remaining dominoes for player 2 are: ");
                        player2.getTiles().forEach(x -> {
                            System.out.println(String.format("[%s ,%s]", x.getLeftTile(), x.getRightTile()));
                        });
                    } else {
                        System.out.println("Remaining dominoes for player 1 are: ");
                        player1.getTiles().forEach(x -> {
                            System.out.println(String.format("[%s ,%s]", x.getLeftTile(), x.getRightTile()));
                        });
                    }
                    winner = true;
                } else if (isGameDraw) { System.out.println("Game is draw"); winner = true;} {
                    if (curPlayer == player1){
                        curPlayer = player2;
                    } else{
                        curPlayer = player1;
                    }
                }
                   
        }

    }
}