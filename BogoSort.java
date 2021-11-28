import java.awt.Color;
import java.util.Random;
/**
 * Beschreiben Sie hier die Klasse BubbleSort.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class BogoSort extends Sortierer
{
    
    BogoSort(int[] data, Zahlenfeld zf){
        super(data, zf);    
    }
    
    @Override
    public String toString(){
        return "BogoSort";
    }
    
    @Override
    public long sortiere(){
        resetTimer();
        Random r = new Random();
        v=0;
        s=0;
        while (isSorted()==false) { 
        int a = r.nextInt(data.length);
        int b = r.nextInt(data.length);
                
        zf.resetColor();
        zf.setColor(a,Color.RED);
        zf.setColor(b,Color.GREEN);
        int temp = data[a];
        data[a] = data[b];
        data[b] = temp;
        v++;
        zf.repaint();
        pause();
        if (!isRunning()) {
                    break;   
                }
    }
    return getTime();
    }
    private  boolean isSorted() {
        for(int i = 0; i < data.length - 1; i++) {
            if (data[i] > data[i + 1]) {
                return false;
            }
        }
        return true;
    
        
        //System.out.println("Zeitbedarf: "+getTime()+" Nanosekunden");
        }
    

}
