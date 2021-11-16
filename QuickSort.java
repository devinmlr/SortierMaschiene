import java.awt.Color;

public class QuickSort extends Sortierer
{
    private Tone sound = new Tone();
    
    
    QuickSort(int[] data, Zahlenfeld zf){
        super(data, zf);
    }
    
    public String toString(){
        return "Quicksort";
    }
    
       
    public long sortiere(){
        // HINWEISE ===================================================
        // Zugriff auf Zahlenfeld per data[i]
        // Länge des Array per data.length
        // Farben der Balken alle auf Schwarz setzen per zf.resetColor()
        // Balken i auf Farbe Rot setzen per zf.setColor(i,Color.RED)
        // Zahlenfelddiagramm neu zeichnen per zf.repaint()
        // pause einfügen per pause()
        // ============================================================
               
        resetTimer();
        
        int iPivot;
        int itemLeft;
        int itemRight;
        int swap;
        
        for (int i=0; i < data.length; i++) {
            iPivot = data.length-1;
            
            if (data[i] > data[iPivot]) {
                for (int f=data.length-1-i; f >= 0; f--) {
                    if (data[f] < data[iPivot]) {
                        swap = data[f];
                        data[f] = data[i];
                        data[i] = swap;
                        break;
                    }
                }
            }
            
            
            
            zf.repaint();
            pause(); 
        }
           
        return getTime();
    }
    
}

