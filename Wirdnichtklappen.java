import java.awt.Color;

public class Wirdnichtklappen extends Sortierer
{
    
    Wirdnichtklappen(int[] data, Zahlenfeld zf){
        super(data, zf);
    }
    
    public String toString(){
        return "Wirdnichtklappen";
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
        boolean vertauscht;
        int h;
        int c=0;
        do{
            vertauscht=false;
            for(int i=0+c;i<data.length-1-c;i++){
                zf.resetColor();
                zf.setColor(i,Color.RED);
                zf.setColor(i+1,Color.GREEN);
                zf.setColor((data.length-i-1),Color.RED);
                zf.setColor((data.length-i-2),Color.GREEN);
                zf.repaint();
                if (data[i]>data[i+1]){
                  h=data[i];
                  data[i]=data[i+1];
                  data[i+1]=h;
                  tauschungen++;
                  vertauscht=true;
                }
                abfragen++;
                if (data[data.length-i-1]<data[data.length-i-2]){
                  h=data[data.length-i-1];
                  data[data.length-i-1]=data[data.length-i-2];
                  data[data.length-i-2]=h;
                  tauschungen++;
                  vertauscht=true;
                }
                abfragen++;
                pause();
                if (!isRunning()) {
                    break;   
                }
            }
            c++;//ik: Ha ha ha!
        }while(vertauscht);
        return getTime();
    }
}

