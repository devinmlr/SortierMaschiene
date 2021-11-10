import java.awt.Color;
/**
 * Beschreiben Sie hier die Klasse BubbleSort.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class BubbleSort extends Sortierer
{
    private Sound2 sound = new Sound2();
    
    
    BubbleSort(int[] data, Zahlenfeld zf){
        super(data, zf);    
    }
    
    @Override
    public String toString(){
        return "BubbleSort";
    }
    
    @Override
    public long sortiere(){
        resetTimer();
        boolean vertauscht;
        int h;
        do{
            vertauscht=false;
            for(int i=0;i<data.length-1;i++){
                zf.resetColor(); 
                zf.setColor(i,Color.RED);
                zf.setColor(i+1,Color.GREEN);
                zf.repaint();
                if (data[i]>data[i+1]){
                  h=data[i];
                  data[i]=data[i+1];
                  data[i+1]=h;
                  vertauscht=true;
                  try {
                      sound.tone(data[i]*50, 10);
                    }
                    catch (Exception e) {}
                }
                pause();
                if (!isRunning()) {
                    break;   
                }
            }
        } while (vertauscht);
        return getTime();
        //System.out.println("Zeitbedarf: "+getTime()+" Nanosekunden");
    }
    

}
