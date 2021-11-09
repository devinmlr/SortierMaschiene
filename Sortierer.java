
/**
 * Beschreiben Sie hier die Klasse Sortierer.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public abstract class Sortierer
{
    private int delay=100;
    private boolean running=true;
    public Zahlenfeld zf;
    public int[] data;
    private long starttime;
    private boolean stepMode=false;
    private boolean waitForStep=true;
    
    Sortierer(int[] data, Zahlenfeld zf){
        this.zf=zf;
        this.data=data;
    }
    
    public void setStepMode(boolean stepMode){
        this.stepMode=stepMode;
    }
    
    public abstract String toString();
    
    public void resetTimer(){
        starttime=System.nanoTime();
    }
    
    public long getTime(){
        return System.nanoTime()-starttime;
    }
    
    public void pause(){
        try{
           if (!stepMode){
                Thread.sleep(delay);   
           } else {
               while (waitForStep){
                   Thread.sleep(1);
               }
               waitForStep=true;
           }
        } catch (Exception e){
        }
    }
    
    public void doStep(){
        waitForStep=false;
    }
    
    public boolean isRunning(){
        return running;
    }
    
    public void setDelay(int delay){
        this.delay=delay;
    }
    
    public int getDelay(){
        return delay;
    }
    
    public void start(){
        running=true;
    }
    
    public void stop(){
        running=false;
    }
    
    public abstract long sortiere();
}
