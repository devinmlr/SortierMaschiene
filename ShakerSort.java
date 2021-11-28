import java.awt.Color;

public class ShakerSort extends Sortierer
{
    
    ShakerSort(int[] data, Zahlenfeld zf){
        super(data, zf);
    }
    
    public String toString(){
        return "ShakerSort";
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
        boolean sorted = false;
        int x=1;
        int i=0;
        int n=0;
        zf.resetColor();
        do{
            sorted=true;
            for (i=0+n;i<data.length-x;i++){
                zf.resetColor();
                zf.setColor(i,Color.RED);
                zf.setColor(i+1,Color.GREEN);
                zf.repaint();
                if (data[i]>data[i+1]){
                    int a=data[i+1];
                    data[i+1]=data[i];
                    data[i]=a;
                    sorted=false;
                    tauschungen++;
                }
                abfragen += 1;
                pause();
                if (!isRunning()) {
                    break;   
                }
            }
            x++;
            for (i=data.length-x;i>0+n;i--){
                zf.resetColor();
                zf.setColor(i,Color.RED);
                zf.setColor(i-1,Color.GREEN);
                zf.repaint();
                if (data[i]<data[i-1]){
                    int a=data[i-1];
                    data[i-1]=data[i];
                    data[i]=a;
                    sorted=false;
                    tauschungen++;
                }
                abfragen++;
                pause();
                if (!isRunning()) {
                    break;   
                }
            }
            n++;
        }while (sorted==false);
        return getTime();
    }
}

