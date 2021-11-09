import java.awt.Color;

public class MySort extends Sortierer
{
    
    MySort(int[] data, Zahlenfeld zf){
        super(data, zf);
    }
    
    public String toString(){
        return "MinMaxSort";
    }
    
    public int Minimum () {
        int h=8000;
        for (int i=0; i<data.length; i++) {
            if (data[i]<h) {
                h=data[i];
            }
        }
        
        return h;
    }
    
    public int Maximum () {
        int h=0;
        for (int i=0; i<data.length; i++) {
            if (data[i]>h) {
                h=data[i];
            }
        }
        
        return h;
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
        int help = 0;
        int num;
        
        resetTimer();
        
            for (int h = 0; h < data.length/2; h++) {
                zf.resetColor();
                //Minimum bestimmen
                num=8000;
                for (int f=h; f<data.length-h; f++) {
                    //zf.setColor(f,Color.RED);
                    if (data[f]<num) {
                        num=data[f];
                        help=f;
                    }
                    zf.repaint();
                }
                data[help] = data[h];
                data[h] = num;
                
                //Maximum bestimmen
                num=0;
                for (int f=h; f<data.length-h; f++) {
                    zf.setColor(f,Color.RED);
                    if (data[f]>num) {
                        num=data[f];
                        help=f;
                    }
                    zf.repaint();
                }
                data[help] = data[data.length-h-1];
                data[data.length-h-1] = num;
                
                if (!isRunning()) {
                    break;   
                }
                
                pause();
            }
        
        
        
        return getTime();
    }
}

