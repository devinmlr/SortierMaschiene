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
    
    private void quicksort(int links, int rechts) {
        if (links < rechts) {
            int teiler = teile(links, rechts);
            quicksort(links, teiler - 1);
            quicksort(teiler +1, rechts);
        }
    }
    
    public int teile (int links, int rechts) {
        zf.repaint();
        
        int swap;
        int i = links;
        int j = rechts -1;
        int pivot = data[rechts];
        zf.setColor(pivot-1, Color.GREEN);
        while (i<j) {
            while (i<rechts && data[i]<pivot) {
                i += 1;
                zf.setColor(i, Color.RED);
                abfragen += 1;
            }
            while (j>links && data[j] >= pivot) {
                j -= 1;
                zf.setColor(j, Color.RED);
                abfragen += 1;
            } 
            if (i<j) {
                swap = data[i];
                data[i] = data[j];
                data[j] = swap;
                tauschungen += 1;
            }
        }
        
        if (data[i] > pivot) {
            swap = data[i];
            data[i] = data[rechts];
            data[rechts] = swap;
            tauschungen += 1;
        }
        pause();
        return i;
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
        
        quicksort(0,data.length-1);
           
        return getTime();
    }
    
}

