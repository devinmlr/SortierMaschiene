import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;

public class Zahlenfeld extends JPanel{

    private int[] z;
    private Color[] c;
    
    Zahlenfeld(int[] data){
        z=data;
        c=new Color[z.length];
    }
    
    public void setData(int[] data){
        z=data;
        c=new Color[z.length];
    }
    
    public void init(){
        for(int i=0;i<z.length;i++){
            z[i]=(int)(Math.random()*getHeight());
            c[i]=Color.BLACK;
        }
    }
    
    public int[] getData(){
        return z;
    }
    
    public Color[] getColor(){
        return c;
    }
    
    public void setColor(int i,Color color){
        c[i]=color;
    }
    
    public void resetColor(){
        for(int i=0;i<z.length;i++){
            c[i]=Color.lightGray;
        }
    }
        
    public void paint(Graphics g){
        int x,y,w,h;
        int gap=1;
        g.setColor(Color.gray);
        g.fillRect(0,0,getWidth()-1,getHeight()-1);
        for(int i=0;i<z.length;i++){
            g.setColor(c[i]);    
            x=(int)(i*((getWidth()-gap*z.length)/(1.0*z.length)+gap));
            y=getHeight()-1-z[i];
            w=(getWidth()-gap*z.length)/z.length;
            h=z[i];
            //System.out.println("x:"+x+" y:"+y+"w: "+w+"h: "+h);
            g.fillRect(x,y,w,h);
        }
        g.setColor(Color.BLACK);
        g.drawRect(0,0,getWidth()-1,getHeight()-1);
    }
    
}
