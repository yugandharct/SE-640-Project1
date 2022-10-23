package DominoeProject;
import java.util.*;

public class CRandom {

    public static List<DominoeTile> randomizedDraw(List<DominoeTile> dominoList, int drawingCount){
        List<DominoeTile> returnList = new ArrayList<>();
        Random rand = new Random();
        
        int c = 0; //Counter
        while(c < drawingCount){
            DominoeTile randomElement = dominoList.get(rand.nextInt(dominoList.size()));
            returnList.add(randomElement);
            dominoList.remove(randomElement);
            c++;
        }
        return returnList;
    }   
}