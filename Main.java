import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.util.Vector;
import java.text.NumberFormat;

public class Main extends JFrame
{
    int[] data=new int[50];
    Zahlenfeld zf=new Zahlenfeld(data);
    
    // Hier Sortierer einbinden!
    //Sortierer sortierer;
       
    DefaultComboBoxModel<Sortierer> sortiererListe=new DefaultComboBoxModel<Sortierer>();
    boolean run=false;
    boolean stepToDo=true;
    //boolean tSelected = true;
    JFormattedTextField field;
    JTextArea text;
    JButton st;
    JButton stop;
    JButton bDoStep;
    JButton bClear;
    JCheckBox cbSteps;
    JCheckBox cbTone;
    JTextArea messages;
    JLabel lAnzahl;
    JTextField tfAnzahl;
    JComboBox list;
    JSlider sl;
    JSlider slVol;
    
    public void resetList(){
        sortiererListe.removeAllElements();
        sortiererListe.addElement(new BubbleSort(data,zf));
        sortiererListe.addElement(new MySort(data,zf));
        sortiererListe.addElement(new QuickSort(data,zf));
        sortiererListe.addElement(new BogoSort(data,zf));
        sortiererListe.addElement(new Wirdnichtklappen(data,zf));
        sortiererListe.addElement(new ShakerSort(data,zf));
        //TO DO: Hier Sortierer an die Liste anfügen
    }
    
    public Main(){
        setBounds(100,100,500,700);
        setLayout(null);
        getContentPane().setBackground(Color.darkGray);

        zf.setBounds(10,10,350,100);
        zf.init();
        zf.repaint();
        add(zf);
        
        lAnzahl=new JLabel("Anzahl");
        lAnzahl.setBounds(10,120,100,25);
        lAnzahl.setForeground(Color.white);
        add(lAnzahl);
        
        tfAnzahl=new JTextField("50");
        tfAnzahl.setBounds(120,120,100,25);
        tfAnzahl.setForeground(Color.black);
        add(tfAnzahl);
                
        resetList();
        
        list=new JComboBox(sortiererListe);
        list.setBounds(120,160,200,25);
        list.setOpaque(true);
        list.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if (list.getSelectedItem()!=null){
                    String sortName=list.getSelectedItem().toString();
                    //System.out.println(sortName);
                }
            }
        });
        add(list);
           
        JButton reset=new JButton("Reset");
        reset.setForeground(Color.white);
        reset.setBackground(Color.gray);
        reset.setBounds(10,160,100,25);
        reset.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e){
               ((Sortierer)list.getSelectedItem()).stop();
               st.setEnabled(true);
               run=false;
               data=new int[Integer.parseInt(tfAnzahl.getText())];
               zf.setData(data);
               zf.init();
               zf.repaint();
               resetList();
               
               
           }
        });
        add(reset);
        
        st=new JButton("Start");
        st.setForeground(Color.white);
        st.setBackground(Color.gray);
        st.setBounds(10,190,100,25);
        st.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e){
               st.setEnabled(false);
               list.setEnabled(false);
               stop.setEnabled(true);
               if (cbSteps.isSelected()) bDoStep.setEnabled(true);
               ((Sortierer)list.getSelectedItem()).setStepMode(cbSteps.isSelected());
               ((Sortierer)list.getSelectedItem()).setDelay(sl.getValue());
               new Thread(new Runnable() {
                    @Override
                    public void run() {
                       //System.out.println("Thread startet..."+this);
                       messages.append(list.getSelectedItem().toString() + ": \n");
                       ((Sortierer)list.getSelectedItem()).start();
                       long time=((Sortierer)list.getSelectedItem()).sortiere();
                       messages.append("Laufzeit: "+time+" Nanosekunden\n");
                       int abfragen=((Sortierer)list.getSelectedItem()).getAbfragen();
                       messages.append("Aufrufe: "+abfragen+" \n");
                       int tauschungen=((Sortierer)list.getSelectedItem()).getTauschungen();
                       messages.append("Tauschungen: "+tauschungen+" \n");
                       //System.out.println("Thread stopped..."+this);
                       stop.setEnabled(false);
                       st.setEnabled(true);
                       list.setEnabled(true);
                    }
               }).start();
               
           }
        });
        add(st);
        
        bClear = new JButton("Clear");
        bClear.setForeground(Color.white);
        bClear.setBackground(Color.gray);
        bClear.setBounds(145,630,100,25);
        
        bClear.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e){
               messages.setText("");
           }
        });
        add(bClear);
        
        stop=new JButton("Stop");
        stop.setForeground(Color.white);
        stop.setBackground(Color.gray);
        stop.setEnabled(false);
        stop.setBounds(120,190,100,25);
        stop.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e){
               st.setEnabled(true);
               list.setEnabled(true);
               stop.setEnabled(false);
               bDoStep.setEnabled(false);
               ((Sortierer)list.getSelectedItem()).setStepMode(false);
               ((Sortierer)list.getSelectedItem()).doStep();
               ((Sortierer)list.getSelectedItem()).stop();
           }
        });
        add(stop);
        
        cbSteps=new JCheckBox("Step by Step");
        cbSteps.setOpaque(false);
        cbSteps.setForeground(Color.white);
        cbSteps.setBounds(10,220,100,25);
        
        cbSteps.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                ((Sortierer)list.getSelectedItem()).setStepMode(cbSteps.isSelected());
                ((Sortierer)list.getSelectedItem()).doStep();
                if (!st.isEnabled()) bDoStep.setEnabled(cbSteps.isSelected());
            }
        });
        add(cbSteps);
        
        cbTone=new JCheckBox("Ton?");
        cbTone.setBounds(10,250,100,25);
        
        cbTone.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                ((Sortierer)list.getSelectedItem()).toggleTon();
            }
        });
        
        //add(cbTone);
        
        bDoStep=new JButton("Do Step");
        bDoStep.setForeground(Color.white);
        bDoStep.setBackground(Color.gray);
        bDoStep.setEnabled(false);
        bDoStep.setBounds(120,220,100,25);
        bDoStep.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e){
               ((Sortierer)list.getSelectedItem()).doStep();
               //stepToDo=true;
               //run=false;
               //System.out.println("stepToDo="+stepToDo);
           }
        });
        add(bDoStep);
        /*JButton r=new JButton("Run");
        r.setBounds(10,250,100,25);
        r.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e){
               run=true;
           }
        });
        add(r);*/
        
        
               
        /*NumberFormat format = NumberFormat.getInstance();
        NumberFormatter formatter = new NumberFormatter(format);
        formatter.setValueClass(Integer.class);
        formatter.setMinimum(0);
        formatter.setMaximum(Integer.MAX_VALUE);
        formatter.setAllowsInvalid(false);
        // If you want the value to be committed on each keystroke instead of focus lost
        formatter.setCommitsOnValidEdit(true);
        field = new JFormattedTextField(formatter);
        field.setText("100");
        field.setBounds(230,250,100,25);
        add(field);*/
        JLabel l=new JLabel();
        l.setBounds(120,250,100,25);
        add(l);
        
        sl=new JSlider();
        sl.setBounds(230,250,100,25);
        sl.setMinimum(0);
        sl.setMaximum(100);
        sl.setValue(100);
        sl.setOpaque(false);
        l.setText("Pause "+sl.getValue()+" ms");
        l.setForeground(Color.white);
        sl.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent event) {
                l.setText("Pause "+sl.getValue()+" ms");
                ((Sortierer)list.getSelectedItem()).setDelay(sl.getValue());
            }
        });
        add(sl);
        
        
        JLabel lVol=new JLabel();
        lVol.setBounds(120,275,100,25);
        add(lVol);
        
        slVol=new JSlider();
        slVol.setBounds(230,275,100,25);
        slVol.setMinimum(0);
        slVol.setMaximum(100);
        slVol.setValue(100);
        slVol.setOpaque(false);
        lVol.setText("Volume "+slVol.getValue()+" %");
        lVol.setForeground(Color.white);
        slVol.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent event) {
                lVol.setText("Volume "+slVol.getValue()+" %");
                ((Sortierer)list.getSelectedItem()).setVolume(slVol.getValue());
            }
        });
        add(slVol);
        
        messages=new JTextArea();
        messages.setFont(new Font("Arial", Font.BOLD, 15));
        messages.setForeground(Color.white);
        messages.setBackground(Color.gray);
        messages.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void removeUpdate(DocumentEvent e) {}
            @Override
            public void insertUpdate(DocumentEvent e) {
                messages.setCaretPosition( messages.getText().length() );
            }
            @Override
            public void changedUpdate(DocumentEvent arg0) {}
        });
        JScrollPane sp=new JScrollPane(messages);
        sp.setBounds(10,325,400,300);
        add(sp);
        
        addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {
               ((Sortierer)list.getSelectedItem()).setStepMode(false);
               ((Sortierer)list.getSelectedItem()).doStep();
               ((Sortierer)list.getSelectedItem()).stop();
                //System.out.println("Closed");
                e.getWindow().dispose();
            }
        });
        setVisible(true);
    }
        
    public static void main(String[] a)
    {
        new Main();
    }
}
