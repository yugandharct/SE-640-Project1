package DominoeProject;
import java.util.*;

public class CTable {
    
    public static void print(CPlayer player, List<DominoeTile> Sequence){
        
        System.out.println(String.format("Sequence: %s", ( Sequence == null || Sequence.isEmpty() ? "[]":printArrayList(Sequence))));
        System.out.println(String.format("Player %s Dominoes:", player.getId()));
        
        player.getTiles().forEach(x -> {
            System.out.println(String.format("%s: [%s, %s]", player.getTiles().indexOf(x), x.getLeftTile(), x.getRightTile()));
        });
    
    }

    private static String printArrayList(List<DominoeTile> tileArray){
        String Str = "";
        for(DominoeTile tile: tileArray){
            Str+= String.format("[%s,%s]",tile.getLeftTile(),tile.getRightTile());
        }
        return Str;
    }


    @Override
    public String toString() {
        return "CTable []";
    }
    
}