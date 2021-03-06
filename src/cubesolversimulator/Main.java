/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cubesolversimulator;

import gnu.io.CommPortIdentifier;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import gnu.io.UnsupportedCommOperationException;
import java.awt.Color;
import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author AKARSH
 */
public class Main extends javax.swing.JFrame {

    /**
     * Creates new form Main
     */
    
    public Main() {
        initComponents();
        jTextArea1.setLineWrap(true);
      // jPanel1.paint(jPanel1.getGraphics());
        grd=new Grid();
        jPanel1.add(grd);        
        grd.setLocation(0,0);
        grd.setSize(500,jPanel1.getHeight()-1);        
        grd.paint(jPanel1.getGraphics());
        Src=new Color[3];
        
        //vi=new VisualInput();
        //add(vi.wp);        
    }

    private void executeArduino() {
        try {
            new SerialTest().arduino();
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    class Grid extends javax.swing.JPanel
    {
        Grid()
        {
            G=new Color[3][3];
            W=new Color[3][3];
            B=new Color[3][3];
            Y=new Color[3][3];
            O=new Color[3][3];
            R=new Color[3][3];   
            //Green
            for(int i=0;i<3;i++)
            {
                for(int j=0;j<3;j++)
                {
                    G[i][j]=Color.GREEN;
                }
            }
            //White
            for(int i=0;i<3;i++)
            {
                for(int j=0;j<3;j++)
                {
                    W[i][j]=Color.WHITE;
                }
            }
            //Blue
            for(int i=0;i<3;i++)
            {
                for(int j=0;j<3;j++)
                {
                    B[i][j]=Color.BLUE;
                }
            }
            //yellow
            for(int i=0;i<3;i++)
            {
                for(int j=0;j<3;j++)
                {
                    Y[i][j]=Color.YELLOW;
                }
            }
            //Orange
            for(int i=0;i<3;i++)
            {
                for(int j=0;j<3;j++)
                {
                    O[i][j]=Color.ORANGE;
                }
            }
            //Red
            for(int i=0;i<3;i++)
            {
                for(int j=0;j<3;j++)
                {
                    R[i][j]=Color.RED;
                }
            }
        }
        @Override
        public void paint(Graphics g)
        {
            int x=10, y=115, d=30;
            //Making Green Face
            for(int i=0;i<3;i++)
            {
                for(int j=0;j<3;j++)
                {
                    g.setColor(G[i][j]);
                    g.fillRect(x, y, d,d);
                    x=x+d+5;                    
                }
                x=10;
                y=y+d+5;
            }
            // Making White Face
            x=115;  y=115;
            for(int i=0;i<3;i++)
            {
                for(int j=0;j<3;j++)
                {
                    g.setColor(W[i][j]);
                    g.fillRect(x, y, d,d);
                    x=x+d+5;                    
                }
                x=115;
                y=y+d+5;
            }
            // Making Blue Face
            x=220;   y=115;
            for(int i=0;i<3;i++)
            {
                for(int j=0;j<3;j++)
                {
                    g.setColor(B[i][j]);
                    g.fillRect(x, y, d,d);
                    x=x+d+5;                    
                }
                x=220;
                y=y+d+5;
            }
            // Making Yellow Face
            x=325;   y=115;
            for(int i=0;i<3;i++)
            {
                for(int j=0;j<3;j++)
                {
                    g.setColor(Y[i][j]);
                    g.fillRect(x, y, d, d);
                    x=x+d+5;                    
                }
                x=325;
                y=y+d+5;
            }
            // Making Orange Face
            x=115;   y=10;
            for(int i=0;i<3;i++)
            {
                for(int j=0;j<3;j++)
                {
                    g.setColor(O[i][j]);
                    g.fillRect(x, y, d, d);
                    x=x+d+5;                    
                }
                x=115;
                y=y+d+5;
            }
            // Making Red Face
            x=115;   y=220;
            for(int i=0;i<3;i++)
            {
                for(int j=0;j<3;j++)
                {
                    g.setColor(R[i][j]);
                    g.fillRect(x, y, d, d);
                    x=x+d+5;                    
                }
                x=115;
                y=y+d+5;
            }
        }
    }
    
    public class SerialTest implements SerialPortEventListener {        
   
    private SerialPort serialPort ;         //defining serial port object
    private CommPortIdentifier portId  = null;       //my COM port
    private static final int TIME_OUT = 2000;    //time in milliseconds
    private static final int BAUD_RATE = 9600; //baud rate to 9600bps
    private BufferedReader input;               //declaring my input buffer
    private OutputStream output;                //declaring output stream
    private String name;        //user input name string
    Scanner inputName;          //user input name    
    //method initialize
    private void initialize(){
        CommPortIdentifier ports = null;      //to browse through each port identified
        Enumeration portEnum = CommPortIdentifier.getPortIdentifiers(); //store all available ports
        while(portEnum.hasMoreElements()){  //browse through available ports
             ports = (CommPortIdentifier)portEnum.nextElement();
             //following line checks whether there is the port i am looking for and whether it is serial
             if(ports.getPortType() == CommPortIdentifier.PORT_SERIAL&&ports.getName().equals("COM3"))
             { 
                System.out.println("COM port found:COM3");
                portId = ports;                  //initialize my port
                break;
             }           
        }
       //if serial port am looking for is not found
        if(portId==null){
            System.out.println("COM port not found");
            //System.exit(1);
        }
        
    }
    
    //end of initialize method
    
    //connect method
   
    private void portConnect(){
        //connect to port
        try{
            serialPort = (SerialPort)portId.open(this.getClass().getName(),TIME_OUT);   //down cast the comm port to serial port
            System.out.println("Port open succesful: COM3");   
            //set serial port parameters
            serialPort.setSerialPortParams(BAUD_RATE,SerialPort.DATABITS_8,SerialPort.STOPBITS_1,SerialPort.PARITY_NONE);
        }
        catch(PortInUseException e){
            System.out.println("Port already in use");
            System.exit(1);
        }
        catch(NullPointerException e2){
            System.out.println("COM port maybe disconnected");
        }
        catch(UnsupportedCommOperationException e3){
            System.out.println(e3.toString());
        }
       
        //input and output channels
        try{
            //defining reader and output stream
            input = new BufferedReader(new InputStreamReader(serialPort.getInputStream()));
            output =  serialPort.getOutputStream();
            //adding listeners to input and output streams
            serialPort.addEventListener(this);
            serialPort.notifyOnDataAvailable(true);
            serialPort.notifyOnOutputEmpty(true);
        }
        catch(Exception e){
            System.out.println(e.toString());
        }
        
    }
    
    public void serialEvent(SerialPortEvent evt) { 
   
       if (evt.getEventType() == SerialPortEvent.DATA_AVAILABLE) { //if data available on serial port
            try {                
                String inputLine=input.readLine();
                System.out.println(inputLine);
                if(inputLine.equals("exit"))
                {                    
                    serialPort.close();
                }
                String str = jTextArea1.getText();                
                ByteArrayInputStream bais = new ByteArrayInputStream(str.getBytes(StandardCharsets.UTF_8));
                System.setIn(bais);
                inputName = new Scanner(System.in); //get user name
                name = inputName.nextLine();
                name = name + 'e';
                System.out.printf("%s",name);               
                output.write(name.getBytes());           //sends the user name                
                //serialPort.close();
            }
            catch (Exception e) {
                System.err.println(e.toString());
            }
        }   
    }
    //end of serialEvent method
    
    //closePort method
    private void close(){
        if(serialPort!=null){
            serialPort.close(); //close serial port
        }
        input = null;        //close input and output streams
        output = null;
    }
    //main method
    public void arduino() {
        SerialTest myTest = new SerialTest();  //creates an object of the class
        myTest.initialize();
        myTest.portConnect();
        System.out.println("Started");
       // while(1>0);       //wait till any activity
    }
//end of main method
// end of  SerialTest class
}
 
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButton15 = new javax.swing.JButton();
        jButton16 = new javax.swing.JButton();
        jButton17 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel1.setText("Cube Solver Simulator");

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(730, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 311, Short.MAX_VALUE)
                .addContainerGap())
        );

        jButton1.setBackground(new java.awt.Color(255, 255, 255));
        jButton1.setText("F");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(255, 153, 0));
        jButton2.setText("U");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(255, 255, 0));
        jButton3.setText("B");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(255, 51, 51));
        jButton4.setText("D");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setBackground(new java.awt.Color(0, 102, 255));
        jButton5.setText("R");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setBackground(new java.awt.Color(51, 204, 0));
        jButton6.setText("L");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setBackground(new java.awt.Color(255, 255, 255));
        jButton7.setText("F '");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton8.setBackground(new java.awt.Color(255, 153, 0));
        jButton8.setText("U '");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton9.setBackground(new java.awt.Color(255, 255, 0));
        jButton9.setText("B '");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jButton10.setBackground(new java.awt.Color(255, 51, 51));
        jButton10.setText("D '");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jButton11.setBackground(new java.awt.Color(0, 102, 255));
        jButton11.setText("R '");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jButton12.setBackground(new java.awt.Color(51, 204, 0));
        jButton12.setText("L '");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        jButton13.setBackground(new java.awt.Color(153, 255, 51));
        jButton13.setFont(new java.awt.Font("Courier New", 1, 24)); // NOI18N
        jButton13.setText("Solve");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        jButton14.setBackground(new java.awt.Color(255, 204, 51));
        jButton14.setFont(new java.awt.Font("Courier New", 1, 16)); // NOI18N
        jButton14.setText("Clear Log");
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Courier New", 1, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Moves:");
        jLabel2.setAutoscrolls(true);
        jLabel2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jLabel2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel3.setFont(new java.awt.Font("Courier New", 1, 18)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText(" ");
        jLabel3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jButton15.setBackground(new java.awt.Color(255, 102, 102));
        jButton15.setFont(new java.awt.Font("Courier New", 1, 14)); // NOI18N
        jButton15.setText("Execute from Log");
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });

        jButton16.setBackground(new java.awt.Color(153, 153, 255));
        jButton16.setFont(new java.awt.Font("Courier New", 1, 12)); // NOI18N
        jButton16.setText("Capture form Camera");
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });

        jButton17.setBackground(new java.awt.Color(153, 204, 255));
        jButton17.setFont(new java.awt.Font("Courier New", 1, 16)); // NOI18N
        jButton17.setText("Setup Cube");
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(375, 375, 375)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jButton7)
                                                .addGap(18, 18, 18)
                                                .addComponent(jButton8))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jButton1)
                                                .addGap(18, 18, 18)
                                                .addComponent(jButton2)
                                                .addGap(10, 10, 10)))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jButton9)
                                                .addGap(18, 18, 18)
                                                .addComponent(jButton10))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jButton3)
                                                .addGap(18, 18, 18)
                                                .addComponent(jButton4)
                                                .addGap(10, 10, 10)))
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(8, 8, 8)
                                                .addComponent(jButton5)
                                                .addGap(18, 18, 18)
                                                .addComponent(jButton6))
                                            .addGroup(layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jButton11)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jButton12))))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jButton15, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jButton16))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jButton17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jButton14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                                .addGap(18, 18, 18)
                                .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(35, 35, 35)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jButton1)
                                    .addComponent(jButton2)
                                    .addComponent(jButton3)
                                    .addComponent(jButton4)
                                    .addComponent(jButton5)
                                    .addComponent(jButton6))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jButton7)
                                    .addComponent(jButton8)
                                    .addComponent(jButton9)
                                    .addComponent(jButton10)
                                    .addComponent(jButton11)
                                    .addComponent(jButton12)))
                            .addComponent(jButton13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton17, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton14, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                            .addComponent(jButton15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        F();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        U();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        B();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        D();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        R();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        L();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        Fa();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        Ua();
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        Ba();
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        Da();
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        Ra();
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        La();
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        
        jTextArea1.setText("");
        // Solving cube
        
        topCross();
        topCorners();
        midLayer();
        bottomCross();
        bottomCorner();
        sideCorner();
        sideEdge();
        executeArduino();
        jLabel3.setText(""+jTextArea1.getText().length());
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        // Clear Text Area
        jTextArea1.setText("");
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        buf=jTextArea1.getText();
        jTextArea1.setText("");
        execSequence();
        executeArduino();
    }//GEN-LAST:event_jButton15ActionPerformed

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        vi=new VisualInputForm();
        vi.setVisible(true);           
    }//GEN-LAST:event_jButton16ActionPerformed

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
        grd.repaint();
        this.repaint();
        faceCount=0;
    }//GEN-LAST:event_jButton17ActionPerformed

    void F()
    {
        // Rotating Face Clockwise [F]
        Color[][] tmp = new Color[3][3];
        for (int i = 0; i < 3; ++i) 
        {
            for (int j = 0; j < 3; ++j) 
            {
                tmp[i][j] = W[3 - j - 1][i];
            }
        }
        for (int i = 0; i < 3; ++i) 
        {
            for (int j = 0; j < 3; ++j) 
            {
                W[i][j]=tmp[i][j];
            }
        }
        //Ressigning Side faces
        Src[0]=B[0][0];     Src[1]=B[1][0];     Src[2]=B[2][0];
        B[0][0]=O[2][0];    B[1][0]=O[2][1];    B[2][0]=O[2][2];
        O[2][0]=G[2][2];    O[2][1]=G[1][2];    O[2][2]=G[0][2];
        G[0][2]=R[0][0];    G[1][2]=R[0][1];    G[2][2]=R[0][2];
        R[0][0]=Src[2];     R[0][1]=Src[1];     R[0][2]=Src[0];
        //Repainting Grid       
        grd.paint(jPanel1.getGraphics());
        jTextArea1.setText(jTextArea1.getText()+"F");
    }
    
    void U()
    {
        // Rotating Face Clockwise [U]
        Color[][] tmp = new Color[3][3];
        for (int i = 0; i < 3; ++i) 
        {
            for (int j = 0; j < 3; ++j) 
            {
                tmp[i][j] = O[3 - j - 1][i];
            }
        }
        for (int i = 0; i < 3; ++i) 
        {
            for (int j = 0; j < 3; ++j) 
            {
                O[i][j]=tmp[i][j];
            }
        }
        //Reassigning Side Faces
        Src[0]=W[0][0];     Src[1]=W[0][1];     Src[2]=W[0][2];
        W[0][0]=B[0][0];    W[0][1]=B[0][1];    W[0][2]=B[0][2];
        B[0][0]=Y[0][0];    B[0][1]=Y[0][1];    B[0][2]=Y[0][2];
        Y[0][0]=G[0][0];    Y[0][1]=G[0][1];    Y[0][2]=G[0][2];
        G[0][0]=Src[0];     G[0][1]=Src[1];     G[0][2]=Src[2];
        //Repainting Grid
        grd.paint(jPanel1.getGraphics());
        jTextArea1.setText(jTextArea1.getText()+"U");
    }
    
    void B()
    {
        //Rotating Face Clockwise [B]
        Color[][] tmp = new Color[3][3];
        for (int i = 0; i < 3; ++i) 
        {
            for (int j = 0; j < 3; ++j) 
            {
                tmp[i][j] = Y[3 - j - 1][i];
            }
        }
        for (int i = 0; i < 3; ++i) 
        {
            for (int j = 0; j < 3; ++j) 
            {
                Y[i][j]=tmp[i][j];
            }
        }
        //Reassingning Side Faces
        Src[0]=G[0][0];     Src[1]=G[1][0];     Src[2]=G[2][0];
        G[0][0]=O[0][2];    G[1][0]=O[0][1];    G[2][0]=O[0][0];
        O[0][0]=B[0][2];    O[0][1]=B[1][2];    O[0][2]=B[2][2];
        B[0][2]=R[2][2];    B[1][2]=R[2][1];    B[2][2]=R[2][0];
        R[2][0]=Src[0];     R[2][1]=Src[1];     R[2][2]=Src[2];
        //Repainting Grid
        grd.paint(jPanel1.getGraphics());
        jTextArea1.setText(jTextArea1.getText()+"B");
    }
    
    void D()
    {
        //Rotating Face Clockwise [D]
        Color[][] tmp = new Color[3][3];
        for (int i = 0; i < 3; ++i) 
        {
            for (int j = 0; j < 3; ++j) 
            {
                tmp[i][j] = R[3 - j - 1][i];
            }
        }
        for (int i = 0; i < 3; ++i) 
        {
            for (int j = 0; j < 3; ++j) 
            {
                R[i][j]=tmp[i][j];
            }
        }
        // Reassigning side Faces
        Src[0]=G[2][0];     Src[1]=G[2][1];     Src[2]=G[2][2];
        G[2][0]=Y[2][0];    G[2][1]=Y[2][1];    G[2][2]=Y[2][2];
        Y[2][0]=B[2][0];    Y[2][1]=B[2][1];    Y[2][2]=B[2][2];
        B[2][0]=W[2][0];    B[2][1]=W[2][1];    B[2][2]=W[2][2];
        W[2][0]=Src[0];     W[2][1]=Src[1];     W[2][2]=Src[2];      
        // Repainting Grid
        grd.paint(jPanel1.getGraphics());
        jTextArea1.setText(jTextArea1.getText()+"D");
    }
    
    void R()
    {
        // Rotating Face Clockwise [R]
        Color[][] tmp = new Color[3][3];
        for (int i = 0; i < 3; ++i) 
        {
            for (int j = 0; j < 3; ++j) 
            {
                tmp[i][j] = B[3 - j - 1][i];
            }
        }
        for (int i = 0; i < 3; ++i) 
        {
            for (int j = 0; j < 3; ++j) 
            {
                B[i][j]=tmp[i][j];
            }
        }
        // Reassingning Side Faces
        Src[0]=O[0][2];     Src[1]=O[1][2];     Src[2]=O[2][2];
        O[0][2]=W[0][2];    O[1][2]=W[1][2];    O[2][2]=W[2][2];
        W[0][2]=R[0][2];    W[1][2]=R[1][2];    W[2][2]=R[2][2];
        R[0][2]=Y[2][0];    R[1][2]=Y[1][0];    R[2][2]=Y[0][0];
        Y[0][0]=Src[2];     Y[1][0]=Src[1];     Y[2][0]=Src[0];
        // Repainting Grid
        grd.paint(jPanel1.getGraphics());
        jTextArea1.setText(jTextArea1.getText()+"R");
    }
    
    void L()
    {
        // Rotating Face Clockwise [L]
        Color[][] tmp = new Color[3][3];
        for (int i = 0; i < 3; ++i) 
        {
            for (int j = 0; j < 3; ++j) 
            {
                tmp[i][j] =G[3 - j - 1][i];
            }
        }
        for (int i = 0; i < 3; ++i) 
        {
            for (int j = 0; j < 3; ++j) 
            {
                G[i][j]=tmp[i][j];
            }
        }
        // Reassingning Side Face
        Src[0]=O[0][0];     Src[1]=O[1][0];     Src[2]=O[2][0];
        O[0][0]=Y[2][2];    O[1][0]=Y[1][2];    O[2][0]=Y[0][2];
        Y[0][2]=R[2][0];    Y[1][2]=R[1][0];    Y[2][2]=R[0][0];
        R[0][0]=W[0][0];    R[1][0]=W[1][0];    R[2][0]=W[2][0];
        W[0][0]=Src[0];     W[1][0]=Src[1];     W[2][0]=Src[2];
        // Repainting Grid
        grd.paint(jPanel1.getGraphics());
        jTextArea1.setText(jTextArea1.getText()+"L");
    }
    
    void Fa()
    {
        // Rotating Face Anti Clockwise [F']
        Color[][] tmp = new Color[3][3];
        for (int i = 0; i < 3; ++i) 
        {
            for (int j = 0; j < 3; ++j) 
            {
                tmp[i][j] =W[j][3 - i - 1];
            }
        }
        for (int i = 0; i < 3; ++i) 
        {
            for (int j = 0; j < 3; ++j) 
            {
                W[i][j]=tmp[i][j];
            }
        }
        //Reassigning Side Faces
        Src[0]=B[0][0];     Src[1]=B[1][0];     Src[2]=B[2][0];
        B[0][0]=R[0][2];    B[1][0]=R[0][1];    B[2][0]=R[0][0];
        R[0][0]=G[0][2];    R[0][1]=G[1][2];    R[0][2]=G[2][2];
        G[0][2]=O[2][2];    G[1][2]=O[2][1];    G[2][2]=O[2][0];
        O[2][0]=Src[0];     O[2][1]=Src[1];     O[2][2]=Src[2];        
        //Repainting Grid
        grd.paint(jPanel1.getGraphics());
        jTextArea1.setText(jTextArea1.getText()+"f");
    }
    
    void Ua()
    {
        // Rotating Face Anti Clockwise [U']        
        Color[][] tmp = new Color[3][3];
        for (int i = 0; i < 3; ++i) 
        {
            for (int j = 0; j < 3; ++j) 
            {
                tmp[i][j] =O[j][3 - i - 1];
            }
        }
        for (int i = 0; i < 3; ++i) 
        {
            for (int j = 0; j < 3; ++j) 
            {
                O[i][j]=tmp[i][j];
            }
        }
        // Reassigning Side Faces
        Src[0]=G[0][0];     Src[1]=G[0][1];     Src[2]=G[0][2];
        G[0][0]=Y[0][0];    G[0][1]=Y[0][1];    G[0][2]=Y[0][2];
        Y[0][0]=B[0][0];    Y[0][1]=B[0][1];    Y[0][2]=B[0][2];
        B[0][0]=W[0][0];    B[0][1]=W[0][1];    B[0][2]=W[0][2];
        W[0][0]=Src[0];     W[0][1]=Src[1];     W[0][2]=Src[2];
        // Repainting Grid
        grd.paint(jPanel1.getGraphics());
        jTextArea1.setText(jTextArea1.getText()+"u");        
    }
    
    void Ba()
    {
        // Rotating Face Anti Clockwise [B']        
        Color[][] tmp = new Color[3][3];
        for (int i = 0; i < 3; ++i) 
        {
            for (int j = 0; j < 3; ++j) 
            {
                tmp[i][j] =Y[j][3 - i - 1];
            }
        }
        for (int i = 0; i < 3; ++i) 
        {
            for (int j = 0; j < 3; ++j) 
            {
                Y[i][j]=tmp[i][j];
            }
        }
        // Reassingning Side Face 
        Src[0]=G[0][0];     Src[1]=G[1][0];     Src[2]=G[2][0];
        G[0][0]=R[2][0];    G[1][0]=R[2][1];    G[2][0]=R[2][2];
        R[2][0]=B[2][2];    R[2][1]=B[1][2];    R[2][2]=B[0][2];
        B[0][2]=O[0][0];    B[1][2]=O[0][1];    B[2][2]=O[0][2];
        O[0][0]=Src[2];     O[0][1]=Src[1];     O[0][2]=Src[0];
        //Repainting Grid
        grd.paint(jPanel1.getGraphics());
        jTextArea1.setText(jTextArea1.getText()+"b");
    }
    
    void Da()
    {
        // Rotating Face Anti Clockwise [D']        
        Color[][] tmp = new Color[3][3];
        for (int i = 0; i < 3; ++i) 
        {
            for (int j = 0; j < 3; ++j) 
            {
                tmp[i][j] =R[j][3 - i - 1];
            }
        }
        for (int i = 0; i < 3; ++i) 
        {
            for (int j = 0; j < 3; ++j) 
            {
                R[i][j]=tmp[i][j];
            }
        }
        // Reassigning Side Faces
        Src[0]=G[2][0];     Src[1]=G[2][1];     Src[2]=G[2][2];
        G[2][0]= W[2][0];    G[2][1]= W[2][1];   G[2][2]= W[2][2];
        W[2][0]= B[2][0];    W[2][1]= B[2][1];   W[2][2]= B[2][2];
        B[2][0]= Y[2][0];    B[2][1]= Y[2][1];   B[2][2]= Y[2][2];
        Y[2][0]= Src[0];    Y[2][1]= Src[1];   Y[2][2]= Src[2];
        //Repainting Grid        
        grd.paint(jPanel1.getGraphics());
        jTextArea1.setText(jTextArea1.getText()+"d");
    }
    
    void Ra()
    {
        // Rotating Face Anti Clockwise [R']        
        Color[][] tmp = new Color[3][3];
        for (int i = 0; i < 3; ++i) 
        {
            for (int j = 0; j < 3; ++j) 
            {
                tmp[i][j] =B[j][3 - i - 1];
            }
        }
        for (int i = 0; i < 3; ++i) 
        {
            for (int j = 0; j < 3; ++j) 
            {
                B[i][j]=tmp[i][j];
            }
        }
        // Reassigning Side Faces
        Src[0]=O[0][2];     Src[1]=O[1][2];     Src[2]=O[2][2];
        O[0][2]=Y[2][0];    O[1][2]=Y[1][0];    O[2][2]=Y[0][0];
        Y[0][0]=R[2][2];    Y[1][0]=R[1][2];    Y[2][0]=R[0][2];
        R[0][2]=W[0][2];    R[1][2]=W[1][2];    R[2][2]=W[2][2];
        W[0][2]=Src[0];     W[1][2]=Src[1];     W[2][2]=Src[2];
        // Repainting Grid 
        grd.paint(jPanel1.getGraphics());
        jTextArea1.setText(jTextArea1.getText()+"r");
    }
    
    void La()
    {
        // Rotating Face Anti Clockwise [L']        
        Color[][] tmp = new Color[3][3];
        for (int i = 0; i < 3; ++i) 
        {
            for (int j = 0; j < 3; ++j) 
            {
                tmp[i][j]= G[j][3 - i - 1];
            }
        }
        for (int i = 0; i < 3; ++i) 
        {
            for (int j = 0; j < 3; ++j) 
            {
                G[i][j]=tmp[i][j];
            }
        }
        // Reassigning Side Faces
        Src[0]=O[0][0];     Src[1]=O[1][0];     Src[2]=O[2][0];
        O[0][0]=W[0][0];    O[1][0]=W[1][0];    O[2][0]=W[2][0];
        W[0][0]=R[0][0];    W[1][0]=R[1][0];    W[2][0]=R[2][0];
        R[0][0]=Y[2][2];    R[1][0]=Y[1][2];    R[2][0]=Y[0][2];
        Y[0][2]=Src[2];     Y[1][2]=Src[1];     Y[2][2]=Src[0];
        // Repainting Grid
        grd.paint(jPanel1.getGraphics());
        jTextArea1.setText(jTextArea1.getText()+"l");
    }
    
    void execSequence()
    {
        for(int i=0; i<buf.length();i++)
        {
            switch(buf.charAt(i))
            {
                case 'F': F();
                    break;
                case 'f': Fa();
                    break;
                case 'U': U();
                    break;
                case 'u': Ua();
                    break;
                case 'B': B();
                    break;
                case 'b': Ba();
                    break;
                case 'D': D();
                    break;
                case 'd': Da();
                    break;
                case 'R': R();
                    break;
                case 'r': Ra();
                    break;
                case 'L': L();
                    break;
                case 'l': La();
                    break;                    
            }
        }
    }
    
    void topCross()
    {
        scanTop();
        if(!crossComplete)            
        {
            setEdge1();
            scanTop();
            if(crossComplete)
                return;
            setEdge2();
            scanTop();
            if(crossComplete)
                return;
            setEdge3();
            scanTop();            
            if(crossComplete)
                return;
            setEdge4();            
        }        
        
    }
    
    void scanTop()
    {
        crossComplete=false;
        if(O[0][1]==Color.ORANGE && Y[0][1]==Color.YELLOW && O[1][0]==Color.ORANGE && G[0][1]==Color.GREEN)
            if(O[1][2]==Color.ORANGE && B[1][0]==Color.BLUE && O[2][1]==Color.ORANGE && R[0][1]==Color.RED)
                crossComplete=true;
    }
    
    void setEdge1()
    {
        // Edge 1=> Orange & Yellow edge
        // Position 1
        if((O[0][1]==Color.ORANGE && Y[0][1]==Color.YELLOW)|| (O[0][1])==Color.YELLOW && Y[0][1]==Color.ORANGE)
        {
            if(Y[0][1]==Color.ORANGE)                
            {
                buf="URB";
                execSequence();
            }
        }            
        // Position 2
        else if((O[1][0]==Color.ORANGE && G[0][1]==Color.YELLOW)||(O[1][0]==Color.YELLOW && G[0][1]==Color.ORANGE))
        {
            if(O[1][0]==Color.ORANGE)
            {
                buf="U";
                execSequence();
            }
            else
            {
                buf="lb";
                execSequence();
            } 
        }
        // Position 3
        else if((O[1][2]==Color.ORANGE && B[0][1]==Color.YELLOW) || (O[1][2]==Color.YELLOW && B[0][1]==Color.ORANGE))
        {
            if(O[1][2]==Color.ORANGE)
            {
                buf="u";
                execSequence();
            }
            else
            {
                buf="RB";
                execSequence();
            }
        }
        // Position 4
        else if((O[2][1]==Color.ORANGE && W[0][1]==Color.YELLOW) || (O[2][1]==Color.YELLOW && W[0][1]==Color.ORANGE))
        {
            if(O[2][1]==Color.ORANGE)
            {
                buf="UU";
                execSequence();
            }
            else
            {
                buf="uRB";
                execSequence();
            }
        }
        // POsition 5
        else if((G[1][2]==Color.ORANGE && W[1][0]==Color.YELLOW) || (G[1][2]==Color.YELLOW && W[1][0]==Color.ORANGE))
        {
            if(G[1][2]==Color.ORANGE)
            {
                buf="llb";
                execSequence();
            }
            else
            {
                buf="Ldbb";
                execSequence();
            }
        }
        // POsition 6
        else if((B[1][0]==Color.ORANGE && W[1][2]==Color.YELLOW) || (B[1][0]==Color.YELLOW && W[1][2]==Color.ORANGE))
        {
            if(B[1][0]==Color.ORANGE)
            {
                buf="RRB";
                execSequence();
            }
            else
            {
                buf="rDBB";
                execSequence();
            }
        }
        // POsition 7
        else if((W[2][1]==Color.ORANGE && R[0][1]==Color.YELLOW) || (W[2][1]==Color.YELLOW && R[0][1]==Color.ORANGE))
        {
            if(W[2][1]==Color.ORANGE)
            {
                buf="DrB";
                execSequence();
            }
            else
            {
                buf="DDBB";
                execSequence();
            }
        }
        // POsition 8
        else if((B[1][2]==Color.ORANGE && Y[1][0]==Color.YELLOW) || (B[1][2]==Color.YELLOW && Y[1][0]==Color.ORANGE))
        {
            if(B[1][2]==Color.ORANGE)
            {
                buf="B";
                execSequence();
            }
            else
            {
                buf="RDBB";
                execSequence();
            }
        }
        // Position 9
        else if((G[1][0]==Color.ORANGE && Y[1][2]==Color.YELLOW) || (G[1][0]==Color.YELLOW && Y[1][2]==Color.ORANGE))
        {
            if(G[1][0]==Color.ORANGE)
            {
                buf="b";
                execSequence();
            }
            else
            {
                buf="ldbb";
                execSequence();
            }
        }
        // Position 10
        else if((R[2][1]==Color.ORANGE && Y[2][1]==Color.YELLOW) || (R[2][1]==Color.YELLOW && Y[2][1]==Color.ORANGE))
        {
            if(R[2][1]==Color.ORANGE)
            {
                buf="bb";
                execSequence();
            }
            else
            {
                buf="DLb";
                execSequence();
            }
        }
        // Position 11
        else if((R[1][2]==Color.ORANGE && B[2][1]==Color.YELLOW) || (R[1][2]==Color.YELLOW && B[2][1]==Color.ORANGE))
        {
            if(R[1][2]==Color.ORANGE)
            {
                buf="DBB";
                execSequence();
            }
            else
            {
                buf="rB";
                execSequence();
            }
        }
        // Position 12
        else if((R[1][0]==Color.ORANGE && G[2][1]==Color.YELLOW) || (R[1][0]==Color.YELLOW && G[2][1]==Color.ORANGE))
        {
            if(R[1][0]==Color.ORANGE)
            {
                buf="dbb";
                execSequence();
            }
            else
            {
                buf="Lb";
                execSequence();
            }
        }
    }
    
    void setEdge2() 
    {
        // Edge 2 => Orange & Green Edge
        // Position 2 // Verified
        if((O[1][0]==Color.ORANGE && G[0][1]==Color.GREEN)|| (O[1][0]==Color.GREEN && G[0][1]==Color.ORANGE))
        {
            if(G[0][1]==Color.ORANGE)
            {
                buf="UBuL";
                execSequence();
            }
        }
        // Position 3 // Verified
        else if((O[1][2]==Color.ORANGE && B[0][1]==Color.GREEN)|| (O[1][2]==Color.GREEN && B[0][1]==Color.ORANGE))
        {
            if(O[1][2]==Color.ORANGE)
            {
                buf="rrddll";
                execSequence();
            }
            else
            {
                buf="Uful";
                execSequence();
            }
        }
        // Position 4 // Verified
        else if((O[2][1]==Color.ORANGE && W[0][1]==Color.GREEN)|| (O[2][1]==Color.GREEN && W[0][1]==Color.ORANGE))
        {
            if(O[2][1]==Color.ORANGE)
            {
                buf="ULul";
                execSequence();
            }
            else
            {
                buf="fl";
                execSequence();
            }
        }
        // Position 5 // Verified
        else if((W[1][0]==Color.ORANGE && G[1][2]==Color.GREEN)|| (W[1][0]==Color.GREEN && G[1][2]==Color.ORANGE))
        {
            if(W[1][0]==Color.ORANGE)
            {
                buf="l";
                execSequence();
            }
            else
            {
                buf="FULul";
                execSequence();
            }
        }
        // Position 6 // Verified
        else if((W[1][2]==Color.ORANGE && B[1][0]==Color.GREEN)|| (W[1][2]==Color.GREEN && B[1][0]==Color.ORANGE))
        {
            if(W[1][2]==Color.ORANGE)
            {
                buf="ffl";
                execSequence();
            }
            else
            {
                buf="fUluL";
                execSequence();
            }
        }
        // Position 7 // Verified
        else if((W[2][1]==Color.ORANGE && R[0][1]==Color.GREEN)|| (W[2][1]==Color.GREEN && R[0][1]==Color.ORANGE))
        {
            if(W[2][1]==Color.ORANGE)
            {
                buf="Fl";
                execSequence();
            }
            else
            {
                buf="dll";
                execSequence();
            }
        }
        // Position 8 // Verified
        else if((Y[1][0]==Color.ORANGE && B[1][2]==Color.GREEN)|| (Y[1][0]==Color.GREEN && B[1][2]==Color.ORANGE))
        {
            if(Y[1][0]==Color.ORANGE)
            {
                buf="RDDll";
                execSequence();
            }
            else
            {
                buf="RDbLB";
                execSequence();
            }
        }
        // Position 9
        else if((Y[1][2]==Color.ORANGE && G[1][0]==Color.GREEN)|| (Y[1][2]==Color.GREEN && G[1][0]==Color.ORANGE))
        {
            if(Y[1][2]==Color.ORANGE)
            {
                buf="L";
                execSequence();
            }
            else
            {
                buf="BDbLL";
                execSequence();
            }
        }
        // Position 10
        else if((Y[2][1]==Color.ORANGE && R[2][1]==Color.GREEN)|| (Y[2][1]==Color.GREEN && R[2][1]==Color.ORANGE))
        {
            if(Y[2][1]==Color.ORANGE)
            {
                buf="bLB";
                execSequence();
            }
            else
            {
                buf="DLL";
                execSequence();
            }
        }
        // Position 11
        else if((B[2][1]==Color.ORANGE && R[1][2]==Color.GREEN)|| (B[2][1]==Color.GREEN && R[1][2]==Color.ORANGE))
        {
            if(B[2][1]==Color.ORANGE)
            {
                buf="DbLB";
                execSequence();
            }
            else
            { 
                buf="DDLL";
                execSequence();
            }
        }
        // Position 12 // Verified
        else if((G[2][1]==Color.ORANGE && R[1][0]==Color.GREEN)|| (G[2][1]==Color.GREEN && R[1][0]==Color.ORANGE))
        {
            if(R[1][0]==Color.ORANGE)
            {
                buf="LL";
                execSequence();
            }
            else
            {
                buf="DFl";
                execSequence();
            }
        }
    }
    
    void setEdge3()
    {
        // Edge 3 => Orange & Blue Edge
        // Position 3
        if((B[0][1]==Color.ORANGE && O[1][2]==Color.BLUE)|| (B[0][1]==Color.BLUE  && O[1][2]==Color.ORANGE))
        {
            if(B[0][1]==Color.ORANGE)
            {
                buf="ubUr";
                execSequence();
            }            
        }
        // Position 4 
        else if((O[2][1]==Color.ORANGE && W[0][1]==Color.BLUE )|| (O[2][1]==Color.BLUE  && W[0][1]==Color.ORANGE))
        {
            if(O[2][1]==Color.ORANGE)
            {
                buf="urUR";
                execSequence();
            }
            else
            {
                buf="FR";
                execSequence();
            }
        }
        // Position 5 
        else if((W[1][0]==Color.ORANGE && G[1][2]==Color.BLUE )|| (W[1][0]==Color.BLUE  && G[1][2]==Color.ORANGE))
        {
            if(W[1][0]==Color.ORANGE)
            {
                buf="FFR";
                execSequence();
            }
            else
            {
                buf="FuRUr";
                execSequence();
            }            
        }
        // Position 6 
        else if((W[1][2]==Color.ORANGE && B[1][0]==Color.BLUE )|| (W[1][2]==Color.BLUE  && B[1][0]==Color.ORANGE))
        {
            if(W[1][2]==Color.ORANGE)
            {
                buf="R";
                execSequence();
            }
            else
            {
                buf="FDRR";
                execSequence();
            }
        }
        // Position 7
        else if((W[2][1]==Color.ORANGE && R[0][1]==Color.BLUE )|| (W[2][1]==Color.BLUE  && R[0][1]==Color.ORANGE))
        {
            if(W[2][1]==Color.ORANGE)
            {
                buf="fR";
                execSequence();
            }
            else
            {
                buf="DRR";
                execSequence();
            }
        }
        // Position 8
        else if((Y[1][0]==Color.ORANGE && B[1][2]==Color.BLUE )|| (Y[1][0]==Color.BLUE  && B[1][2]==Color.ORANGE))
        {
            if(Y[1][0]==Color.ORANGE)
            {
                buf="r";
                execSequence();
            }
            else
            {
                buf="bdBrr"; 
                execSequence();
            }
        }
        // Position 9 //verified
        else if((Y[1][2]==Color.ORANGE && G[1][0]==Color.BLUE )|| (Y[1][2]==Color.BLUE  && G[1][0]==Color.ORANGE))
        {
            if(Y[1][2]==Color.ORANGE)
            {
                buf="lddLrr";
                execSequence();
            }
            else
            {
                buf="Bdbrr"; 
                execSequence();
            }
        }
        // Position 10
        else if((Y[2][1]==Color.ORANGE && R[2][1]==Color.BLUE )|| (Y[2][1]==Color.BLUE  && R[2][1]==Color.ORANGE))
        {
            if(Y[2][1]==Color.ORANGE)
            {
                buf="Brb";
                execSequence();
            }
            else
            {
                buf="dRR"; 
                execSequence();
            }
        }
        // Position 11
        else if((B[2][1]==Color.ORANGE && R[1][2]==Color.BLUE )|| (B[2][1]==Color.BLUE  && R[1][2]==Color.ORANGE))
        {
            if(B[2][1]==Color.ORANGE)
            {
                buf="dfR";
                execSequence();
            }
            else
            {
                buf="RR"; 
                execSequence();
            }
        }
        // Position 12
        else if((G[2][1]==Color.ORANGE && R[1][0]==Color.BLUE )|| (G[2][1]==Color.BLUE  && R[1][0]==Color.ORANGE))
        {
            if(G[2][1]==Color.ORANGE)
            {
                buf="DfR";
                execSequence();
            }
            else
            {
                buf="DDRR";
                execSequence();
            }
        }
    }
    
    void setEdge4()
    {
        // Edge 4 => Orange & White Edge
        // Position 4
        if((W[0][1]==Color.ORANGE && O[2][1]==Color.WHITE)|| (W[0][1]==Color.WHITE && O[2][1]==Color.ORANGE))
        {
            if(W[0][1]==Color.ORANGE)
            {
                buf="urUf";
                execSequence();
            }            
        }
        // Position 5
        else if((G[1][2]==Color.ORANGE && W[1][0]==Color.WHITE )|| (G[1][2]==Color.WHITE  && W[1][0]==Color.ORANGE))
        {
            if(G[1][2]==Color.ORANGE)
            {
                buf="F";
                execSequence();
            }
            else
            {
                buf="luFULf";
                execSequence();
            }
        }
        // Position 6
        else if((B[1][0]==Color.ORANGE && W[1][2]==Color.WHITE )|| (B[1][0]==Color.WHITE && W[1][2]==Color.ORANGE))
        {
            if(B[1][0]==Color.ORANGE)
            {
                buf="f";
                execSequence();
            }
            else
            {
                buf="RUfurF";
                execSequence();
            }
        }
        // Position 7
        else if((R[0][1]==Color.ORANGE && W[2][1]==Color.WHITE )|| (R[0][1]==Color.WHITE  && W[2][1]==Color.ORANGE))
        {
            if(R[0][1]==Color.ORANGE)
            {
                buf="FF";
                execSequence();
            }
            else
            {
                buf="dlFL";
                execSequence();
            }
        }
        // Position 8
        else if((B[1][2]==Color.ORANGE && Y[1][0]==Color.WHITE )|| (B[1][2]==Color.WHITE  && Y[1][0]==Color.ORANGE))
        {
            if(B[1][2]==Color.ORANGE)
            {
                buf="bdBdFF";
                execSequence();
            }
            else
            {
                buf="RdrFF";
                execSequence();
            }
        }
        // Position 9
        else if((Y[1][2]==Color.ORANGE && G[1][0]==Color.WHITE )|| (Y[1][2]==Color.WHITE  && G[1][0]==Color.ORANGE))
        {
            if(Y[1][2]==Color.ORANGE)
            {
                buf="lDLFF";
                execSequence();
            }
            else
            {
                buf="BDbDFF";
                execSequence();
            }
        }
        // Position 10
        else if((R[2][1]==Color.ORANGE && Y[2][1]==Color.WHITE )|| (R[2][1]==Color.WHITE  && Y[2][1]==Color.ORANGE))
        {
            if(R[2][1]==Color.ORANGE)
            {
                buf="DDFF";
                execSequence();
            }
            else
            {
                buf="DlFL";
                execSequence();
            }
        }
        // Position 11
        else if((R[1][2]==Color.ORANGE && B[2][1]==Color.WHITE )|| (R[1][2]==Color.WHITE  && B[2][1]==Color.ORANGE))
        {
            if(R[1][2]==Color.ORANGE)
            {
                buf="dff";
                execSequence();
            }
            else
            {
                buf="Rfr";
                execSequence();
            }
        }
        // Position 12
        else if((R[1][0]==Color.ORANGE && G[2][1]==Color.WHITE )|| (R[1][0]==Color.WHITE  && G[2][1]==Color.ORANGE))
        {
            if(R[1][0]==Color.ORANGE)
            {
                buf="Dff";
                execSequence();
            }
            else
            {
                buf="lFL";
                execSequence();
            }
        }
    }              
    
    void topCorners()
    {
        scanCorner();
        if(!cornerComplete)
        {
            setCorner1();
            scanCorner();
            if(cornerComplete)
            {
                return;
            }
            setCorner2();
            scanCorner();
            if(cornerComplete)
            {
                return;
            }
            setCorner3();
            scanCorner();
            if(cornerComplete)
            {
                return;
            }
            setCorner4();
        }
    }
    
    void scanCorner()
    {
        cornerComplete=false;
        if(O[0][0]==Color.ORANGE && G[0][0]==Color.GREEN && Y[0][2]==Color.YELLOW)
        {
            if(O[0][2]==Color.ORANGE && B[0][2]==Color.BLUE && Y[0][0]==Color.YELLOW)
            {
                if(O[2][0]==Color.ORANGE && G[0][2]==Color.GREEN && W[0][0]==Color.WHITE)
                {
                    if(O[2][2]==Color.ORANGE && B[0][0]==Color.BLUE && W[0][2]==Color.WHITE)
                    {
                        cornerComplete=true;
                    }
                }
            }
        }
    }
            
    void setCorner1()
    {
        //corner 1 => orange - green - yellow
        //position 1
        if((O[0][0]==Color.ORANGE && G[0][0]==Color.GREEN && Y[0][2]==Color.YELLOW)|| (O[0][0]==Color.YELLOW && G[0][0]==Color.ORANGE && Y[0][2]==Color.GREEN) || (O[0][0]==Color.GREEN && G[0][0]==Color.YELLOW && Y[0][2]==Color.ORANGE))
        {
            if(G[0][0]==Color.ORANGE)
            {
                buf="ldLDldL";
                execSequence();
            }
            else if(Y[0][2]==Color.ORANGE)
            {
                buf="BDbdBDb";
                execSequence();
            }
        }
        // position 2
        else if((O[0][2]==Color.ORANGE || O[0][2]==Color.GREEN || O[0][2]==Color.YELLOW) && (B[0][2]==Color.ORANGE || B[0][2]==Color.GREEN || B[0][2]==Color.YELLOW) && (Y[0][0]==Color.ORANGE || Y[0][0]==Color.GREEN || Y[0][0]==Color.YELLOW))
        {
            if(O[0][2]==Color.ORANGE)
            {
                buf="RDrldL";
                execSequence();
            }
            else if(B[0][2]==Color.ORANGE)
            {
                buf="RDrBDb";
                execSequence();                
            }
            else
            {
                buf="bdBDDldL";
                execSequence();                
            }
        }
        // position 3
        else if((O[2][0]==Color.ORANGE || O[2][0]==Color.GREEN || O[2][0]==Color.YELLOW) && (G[0][2]==Color.ORANGE ||  G[0][2]==Color.GREEN || G[0][2]==Color.YELLOW) && (W[0][0]==Color.ORANGE || W[0][0]==Color.GREEN || W[0][0]==Color.YELLOW))
        {
            if(O[2][0]==Color.ORANGE)
            {
                buf="fdFBDb";
                execSequence();
            }
            else if(W[0][0]==Color.ORANGE)
            {
                buf="fdFldL";
                execSequence();                
            }
            else
            {
                buf="LDlddBDb";//|fdFldLBDb";
                execSequence();                
            }
        }
        // Position 4
        else if((O[2][2]==Color.ORANGE || O[2][2]==Color.GREEN || O[2][2]==Color.YELLOW) && (W[0][2]==Color.ORANGE || W[0][2]==Color.GREEN || W[0][2]==Color.YELLOW) && (B[0][0]==Color.ORANGE || B[0][0]==Color.GREEN || B[0][0]==Color.YELLOW))
        {
            if(O[2][2]==Color.ORANGE)
            {
                buf="rDDRBDb";
                execSequence();
            }
            else if(W[0][2]==Color.ORANGE)
            {
                buf="FDDfBDb";
                execSequence();                
            }
            else
            {
                buf="rDDRldL";
                execSequence();                
            }
        }
        // Position 5
        else if((G[2][0]==Color.ORANGE || G[2][0]==Color.GREEN || G[2][0]==Color.YELLOW) && (Y[2][2]==Color.ORANGE || Y[2][2]==Color.GREEN || Y[2][2]==Color.YELLOW) && (R[2][0]==Color.ORANGE || R[2][0]==Color.GREEN || R[2][0]==Color.YELLOW))
        {
            if(G[2][0]==Color.ORANGE)
            {
                buf="ldL";
                execSequence();
            }
            else if(Y[2][2]==Color.ORANGE)
            {
                buf="BDb";
                execSequence();                
            }
            else
            {
                buf="lDLDDldL";
                execSequence();                
            }
        }
        // Position 6
        else if((Y[2][0]==Color.ORANGE || Y[2][0]==Color.GREEN || Y[2][0]==Color.YELLOW) && (B[2][2]==Color.ORANGE || B[2][2]==Color.GREEN || B[2][2]==Color.YELLOW) && (R[2][2]==Color.ORANGE || R[2][2]==Color.GREEN || R[2][2]==Color.YELLOW))
        {
            if(B[2][2]==Color.ORANGE)
            {
                buf="DBDb";
                execSequence();
            }
            else if(R[2][2]==Color.ORANGE)
            {
                buf="bDBdldL";
                execSequence();                
            }
            else
            {
                buf="DldL";
                execSequence();                
            }
        }
        // Position 7
        else if((G[2][2]==Color.ORANGE || G[2][2]==Color.GREEN || G[2][2]==Color.YELLOW) && (W[2][0]==Color.ORANGE || W[2][0]==Color.GREEN || W[2][0]==Color.YELLOW) && (R[0][0]==Color.ORANGE || R[0][0]==Color.GREEN || R[0][0]==Color.YELLOW))
        {
            if(G[2][2]==Color.ORANGE)
            {
                buf="dBDB";
                execSequence();
            }
            else if(W[2][0]==Color.ORANGE)
            {
                buf="dldL";
                execSequence();                
            }
            else
            {
                buf="LdlDBDb";
                execSequence();                
            }
        }
        // Position 8
        else if((W[2][2]==Color.ORANGE || W[2][2]==Color.GREEN || W[2][2]==Color.YELLOW) && (B[2][0]==Color.ORANGE || B[2][0]==Color.GREEN || B[2][0]==Color.YELLOW) && (R[0][2]==Color.ORANGE || R[0][2]==Color.GREEN || R[0][2]==Color.YELLOW))
        {
            if(W[2][2]==Color.ORANGE)
            {
                buf="DDBDb";
                execSequence();
            }
            else if(B[2][0]==Color.ORANGE)
            {
                buf="DDldL";
                execSequence();                
            }
            else
            {
                buf="rDRldL";
                execSequence();                
            }
        }
    }
    
    void setCorner2()
    {
        //Corner Piece 2 Orange - Blue - Yellow
        // Position 2
        if((O[0][2]==Color.ORANGE || O[0][2]==Color.BLUE || O[0][2]==Color.YELLOW) && (B[0][2]==Color.ORANGE || B[0][2]==Color.BLUE || B[0][2]==Color.YELLOW) && (Y[0][0]==Color.ORANGE || Y[0][0]==Color.BLUE || Y[0][0]==Color.YELLOW))
        {
            if(B[0][2]==Color.ORANGE)
            {
                buf="RDrdRDr";
                execSequence();                                
            }
            else if(Y[0][0]==Color.ORANGE)
            {
                buf="bdBDbdB";
                execSequence();
            }
        }
        // Position 3
        else if((O[2][0]==Color.ORANGE ||O[2][0]==Color.BLUE || O[2][0]==Color.YELLOW) && (G[0][2]==Color.ORANGE || G[0][2]==Color.BLUE || G[0][2]==Color.YELLOW) && (W[0][0]==Color.ORANGE || W[0][0]==Color.BLUE || W[0][0]==Color.YELLOW))
        {
            if(O[2][0]==Color.ORANGE)
            {
                buf="fdFdRDr";
                execSequence();   
            }
            else if(G[0][2]==Color.ORANGE)
            {
                buf="LDlDRDr";
                execSequence();   
            }
            else if(Y[0][0]==Color.ORANGE)
            {
                buf="fdFdbdB";
                execSequence();   
            }
        }
        // Position 4
        else if((O[2][2]==Color.ORANGE || O[2][2]==Color.BLUE || O[2][2]==Color.YELLOW) && (W[0][2]==Color.ORANGE || W[0][2]==Color.BLUE || W[0][2]==Color.YELLOW) && (B[0][0]==Color.ORANGE || B[0][0]==Color.BLUE || B[0][0]==Color.YELLOW))
        {
            if(O[2][2]==Color.ORANGE)
            {
                buf="rDRDbdB";
                execSequence();
            }
            else if(W[0][2]==Color.ORANGE)
            {
                buf="FDfRDr";
                execSequence();
            }
            else
            {
                buf="rDDRdbdB";
                execSequence();
            }
        }
        // Position 5
        else if((G[2][0]==Color.ORANGE || G[2][0]==Color.BLUE || G[2][0]==Color.YELLOW) && (Y[2][2]==Color.ORANGE || Y[2][2]==Color.BLUE || Y[2][2]==Color.YELLOW) && (R[2][0]==Color.ORANGE || R[2][0]==Color.BLUE || R[2][0]==Color.YELLOW))
        {
            if(Y[2][2]==Color.ORANGE)
            {
                buf="dRDr";
                execSequence();
            }
            else if(G[2][0]==Color.ORANGE)
            {
                buf="dbdB";
                execSequence();
            }
            else
            {
                buf="dRDDrdRDr";
                execSequence();
            }
        }
        // Position 6
        else if((B[2][2]==Color.ORANGE || B[2][2]==Color.BLUE || B[2][2]==Color.YELLOW) && (R[2][2]==Color.ORANGE || R[2][2]==Color.BLUE || R[2][2]==Color.YELLOW) && (Y[2][0]==Color.ORANGE || Y[2][0]==Color.BLUE || Y[2][0]==Color.YELLOW))
        {
            if(B[2][2]==Color.ORANGE)
            {
                buf="RDr";
                execSequence();
            }
            else if(Y[2][0]==Color.ORANGE)
            {
                buf="bdB";
                execSequence();
            }
            else
            {
                buf="RDDrdRDr";
                execSequence();
            }
        }
        // Position 7
        else if((G[2][2]==Color.ORANGE || G[2][2]==Color.BLUE || G[2][2]==Color.YELLOW) && (W[2][0]==Color.ORANGE || W[2][0]==Color.BLUE || W[2][0]==Color.YELLOW) && (R[0][0]==Color.ORANGE || R[0][0]==Color.BLUE || R[0][0]==Color.YELLOW))
        {
            if(G[2][2]==Color.ORANGE)
            {
                buf="DDRDr";
                execSequence();
            }
            else if(W[2][0]==Color.ORANGE)
            {
                buf="DDbdB";
                execSequence();
            }
            else
            {
                buf="DDRdrDDRDr";
                execSequence();
            }
        }
        // Position 8
        else if((W[2][2]==Color.ORANGE || W[2][2]==Color.BLUE || W[2][2]==Color.YELLOW) && (R[0][2]==Color.ORANGE || R[0][2]==Color.BLUE || R[0][2]==Color.YELLOW) && (B[2][0]==Color.ORANGE || B[2][0]==Color.BLUE || B[2][0]==Color.YELLOW))
        {
            if(W[2][2]==Color.ORANGE)
            {
                buf="DRDr";
                execSequence();
            }
            else if(R[0][2]==Color.ORANGE)
            {
                buf="DbddBDbdB";
                execSequence();
            }
            else
            {
                buf="DbdB";
                execSequence();
            }
        }        
    }
    
    void setCorner3()
    {
        //Corner Piece 3 Orange - Green - White
        // Position 3
        if((O[2][0]==Color.ORANGE || O[2][0]==Color.GREEN || O[2][0]==Color.WHITE) && (G[0][2]==Color.ORANGE || G[0][2]==Color.GREEN || G[0][2]==Color.WHITE) && (W[0][0]==Color.ORANGE || W[0][0]==Color.GREEN || W[0][0]==Color.WHITE))
        {
            if(G[0][2]==Color.ORANGE)
            {
                buf="LDldLDl";
                execSequence();                                
            }
            else if(W[0][0]==Color.ORANGE)
            {
                buf="fdFDfdF";
                execSequence();
            }
        }
        // Position 4
        else if((O[2][2]==Color.ORANGE || O[2][2]==Color.GREEN || O[2][2]==Color.WHITE) && (W[0][2]==Color.ORANGE || W[0][2]==Color.GREEN || W[0][2]==Color.WHITE) && (B[0][0]==Color.ORANGE || B[0][0]==Color.GREEN || B[0][0]==Color.WHITE))
        {
            if(O[2][2]==Color.ORANGE)
            {
                buf="rdRLDl";
                execSequence();
            }
            else if(W[0][2]==Color.ORANGE)
            {
                buf="FDfddLDl";
                execSequence();
            }
            else
            {
                buf="rdRfdF";
                execSequence();
            }
        }
        // Position 5
        else if((G[2][0]==Color.ORANGE || G[2][0]==Color.GREEN || G[2][0]==Color.WHITE) && (Y[2][2]==Color.ORANGE || Y[2][2]==Color.GREEN || Y[2][2]==Color.WHITE) && (R[2][0]==Color.ORANGE || R[2][0]==Color.GREEN || R[2][0]==Color.WHITE))
        {
            if(Y[2][2]==Color.ORANGE)
            {
                buf="DLDl";
                execSequence();
            }
            else if(G[2][0]==Color.ORANGE)
            {
                buf="DfdF";
                execSequence();
            }
            else
            {
                buf="DfDFddfdF";
                execSequence();
            }
        }
        // Position 6       
        else if((B[2][2]==Color.ORANGE || B[2][2]==Color.GREEN || B[2][2]==Color.WHITE) && (R[2][2]==Color.ORANGE || R[2][2]==Color.GREEN || R[2][2]==Color.WHITE) && (Y[2][0]==Color.ORANGE || Y[2][0]==Color.GREEN || Y[2][0]==Color.WHITE))
        {
            if(B[2][2]==Color.ORANGE)
            {
                buf="ddLDl";
                execSequence();
            }
            else if(Y[2][0]==Color.ORANGE)
            {
                buf="ddfdF";
                execSequence();
            }
            else
            {
                buf="ddLdlddLDl";
                execSequence();
            }
        }
        // Position 7
        else if((G[2][2]==Color.ORANGE || G[2][2]==Color.GREEN || G[2][2]==Color.WHITE) && (W[2][0]==Color.ORANGE || W[2][0]==Color.GREEN || W[2][0]==Color.WHITE) && (R[0][0]==Color.ORANGE || R[0][0]==Color.GREEN || R[0][0]==Color.WHITE))
        {
            if(G[2][2]==Color.ORANGE)
            {
                buf="LDl";
                execSequence();
            }
            else if(W[2][0]==Color.ORANGE)
            {
                buf="fdF";
                execSequence();
            }
            else
            {
                buf="fDFDDfdF";
                execSequence();
            }
        }
        // Position 8
        else if((W[2][2]==Color.ORANGE || W[2][2]==Color.GREEN || W[2][2]==Color.WHITE) && (R[0][2]==Color.ORANGE || R[0][2]==Color.GREEN || R[0][2]==Color.WHITE) && (B[2][0]==Color.ORANGE || B[2][0]==Color.GREEN || B[2][0]==Color.WHITE))
        {
            if(W[2][2]==Color.ORANGE)
            {
                buf="dLDl";
                execSequence();
            }
            else if(R[0][2]==Color.ORANGE)
            {
                buf="rddRfdF";
                execSequence();
            }
            else
            {
                buf="dfdF";
                execSequence();
            }
        }
    }
    
    void setCorner4()
    {
        //Corner Piece 4 Orange - Blue - White
        // Position 4
        if((O[2][2]==Color.ORANGE || O[2][2]==Color.BLUE || O[2][2]==Color.WHITE) && (W[0][2]==Color.ORANGE || W[0][2]==Color.BLUE || W[0][2]==Color.WHITE) && (B[0][0]==Color.ORANGE || B[0][0]==Color.BLUE || B[0][0]==Color.WHITE))
        {
            if(W[0][2]==Color.ORANGE)
            {
                buf="FDfdFDf";
                execSequence();
            }
            else if(B[0][0]==Color.ORANGE)
            {
                buf="rdRDrdR";
                execSequence();
            }
        }
        // Position 5
        else if((G[2][0]==Color.ORANGE || G[2][0]==Color.BLUE || G[2][0]==Color.WHITE) && (Y[2][2]==Color.ORANGE || Y[2][2]==Color.BLUE || Y[2][2]==Color.WHITE) && (R[2][0]==Color.ORANGE || R[2][0]==Color.BLUE || R[2][0]==Color.WHITE))
        {
            if(Y[2][2]==Color.ORANGE)
            {
                buf="ddFDf";
                execSequence();
            }
            else if(G[2][0]==Color.ORANGE)
            {
                buf="DDrdR";
                execSequence();
            }
            else
            {
                buf="DDrDRDDrdR";
                execSequence();
            }
        }
        // Position 6       
        else if((B[2][2]==Color.ORANGE || B[2][2]==Color.BLUE || B[2][2]==Color.WHITE) && (R[2][2]==Color.ORANGE || R[2][2]==Color.BLUE || R[2][2]==Color.WHITE) && (Y[2][0]==Color.ORANGE || Y[2][0]==Color.BLUE || Y[2][0]==Color.WHITE))
        {
            if(B[2][2]==Color.ORANGE)
            {
                buf="dFDf";
                execSequence();
            }
            else if(Y[2][0]==Color.ORANGE)
            {
                buf="drdR";
                execSequence();
            }
            else
            {
                buf="drDRDDrdR";
                execSequence();
            }
        }
        // Position 7
        else if((G[2][2]==Color.ORANGE || G[2][2]==Color.BLUE || G[2][2]==Color.WHITE) && (W[2][0]==Color.ORANGE || W[2][0]==Color.BLUE || W[2][0]==Color.WHITE) && (R[0][0]==Color.ORANGE || R[0][0]==Color.BLUE || R[0][0]==Color.WHITE))
        {
            if(G[2][2]==Color.ORANGE)
            {
                buf="DFDf";
                execSequence();
            }
            else if(W[2][0]==Color.ORANGE)
            {
                buf="DrdR";
                execSequence();
            }
            else
            {
                buf="DrDRddrdR";
                execSequence();
            }
        }
        // Position 8
        else if((W[2][2]==Color.ORANGE || W[2][2]==Color.BLUE || W[2][2]==Color.WHITE) && (R[0][2]==Color.ORANGE || R[0][2]==Color.BLUE || R[0][2]==Color.WHITE) && (B[2][0]==Color.ORANGE || B[2][0]==Color.BLUE || B[2][0]==Color.WHITE))
        {
            if(W[2][2]==Color.ORANGE)
            {
                buf="FDf";
                execSequence();
            }
            else if(R[0][2]==Color.ORANGE)
            {
                buf="rDRddrdR";
                execSequence();
            }
            else
            {
                buf="rdR";
                execSequence();
            }
        }
    }
    
    void midLayer()
    {
        scanMid();
        while(!midComplete)
        {
            setMid();
            scanMid();
        }
    }
    
    void scanMid()
    {
        midComplete=false;
        if(W[1][0]==Color.WHITE && W[1][2]== Color.WHITE && G[1][2]==Color.GREEN && G[1][0]==Color.GREEN)
        {
            if(B[1][0]==Color.BLUE && B[1][2]==Color.BLUE && Y[1][0]==Color.YELLOW && Y[1][2]==Color.YELLOW)
            {
                midComplete=true;
            }
        }
    }
    
    int getColorCode(Color clr)
    {
        if(clr==Color.WHITE)
            return 1;
        else if(clr==Color.GREEN)
            return 2;
        else if(clr==Color.BLUE)
            return 3;
        else if(clr==Color.YELLOW)
            return 4;
        return 0;
    }
    
    void setMid()
    {
        //Position 1
        if(W[2][1]!=Color.RED && R[0][1]!=Color.RED)
        {
            switch(getColorCode(W[2][1]))
            {
                case 1:if(R[0][1]==Color.GREEN)
                        {
                            buf="DLDldfdF";
                            execSequence();
                        }
                        else if(R[0][1]==Color.BLUE)
                        {
                            buf="drdRDFDf";
                            execSequence();
                        }
                        break;
                case 2:if (R[0][1]==Color.YELLOW)
                        {
                            buf="BDbdldL";
                            execSequence();
                        }
                        else if(R[0][1]==Color.WHITE)
                        {
                            buf="ddfdFDLDl";
                            execSequence();
                        }
                        break;
                case 3: if(R[0][1]==Color.WHITE)
                        {
                            buf="DDFDfdrdR";
                            execSequence();
                        }
                        else if(R[0][1]==Color.YELLOW)
                        {
                            buf="bdBDRDr";
                            execSequence();
                        }
                        break;
                case 4: if(R[0][1]==Color.BLUE)
                        {
                            buf="dRDrdbdB";
                            execSequence();
                        }
                        else if(R[0][1]==Color.GREEN)
                        {
                            buf="DldLDBDb";
                            execSequence();
                        }
                        break;
            }
        }
        //Position 2 else if and switch case to follow
        else if(B[2][1]!=Color.RED && R[1][2]!=Color.RED)
        {
            switch(getColorCode(B[2][1]))
            {
                case 1:if(R[1][2]==Color.GREEN)
                    {
                        buf="LDldfdF";
                        execSequence();
                    }
                    else if(R[1][2]==Color.BLUE)
                    {
                        buf="ddrdRDFDf";
                        execSequence();
                    }
                    break;
                case 2:if(R[1][2]==Color.YELLOW)
                    {
                        buf="dBDbdldL";
                        execSequence();
                    }
                    else if(R[1][2]==Color.WHITE)
                    {
                        buf="DfdFDLDl";
                        execSequence();
                    }
                    break;
                case 3:if(R[1][2]==Color.WHITE)
                    {
                        buf="DFDfdrdR";
                        execSequence();
                    }                
                    else if(R[1][2]==Color.YELLOW)
                    {
                        buf="dfdFDLDl";
                        execSequence();
                    }
                    break;
                case 4:if(R[1][2]==Color.BLUE)
                    {
                        buf="DDRDrdbdB";
                        execSequence();                        
                    }
                    else if(R[1][2]==Color.GREEN)
                    {
                        buf="ldLDBDb";
                        execSequence();
                    }
                    break;
            }
        }
        // Position 3 Yellow side
        else if(Y[2][1]!=Color.RED && R[2][1]!=Color.RED)
        {
            switch(getColorCode(Y[2][1]))
            {
                case 1: if(R[2][1]==Color.GREEN)
                    {
                        buf="dLDldfdF0";
                        execSequence();
                    }
                    else if(R[2][1]==Color.BLUE)
                    {
                        buf="DrdRDFDf";
                        execSequence();
                    }
                case 2: {
                    if(R[2][1]==Color.YELLOW)
                    {
                        buf="DDBDbdldL";
                        execSequence();
                    }
                    else if(R[2][1]==Color.WHITE)
                    {
                        buf="fdFDLDl";
                        execSequence();
                    }
                    break;
                }
                case 3:{
                    if(R[2][1]==Color.WHITE)
                    {
                        buf="FDfdrdR";
                        execSequence();
                    }
                    else if(R[2][1]==Color.YELLOW)
                    {
                        buf="ddbdBDRDr";
                        execSequence();
                    }
                    break;
                }
                case 4:{
                    if(R[2][1]==Color.BLUE)
                    {
                        buf="DRDrdbdB";
                        execSequence();
                    }
                    else if(R[2][1]==Color.GREEN)
                    {
                        buf="dldLDBDb";
                        execSequence();
                    }
                    break;
                }
            }
        }
        //Position 4
        else if(G[2][1]!=Color.RED && R[1][0]!=Color.RED)
        {
            switch(getColorCode(G[2][1]))
            {
                case 1:{
                    if(R[1][0]==Color.GREEN)
                    {
                        buf="DDLDldfdF";
                        execSequence();                        
                    }
                    else if(R[1][0]==Color.BLUE)
                    {
                        buf="rdRDFDf";
                        execSequence();
                    }
                    break;
                }
                case 2: {
                    if(R[1][0]==Color.YELLOW)
                    {
                        buf="DBDbdldL";
                        execSequence();
                    }
                    else if(R[1][0]==Color.WHITE)
                    {
                        buf="dfdFDLDl";
                        execSequence();
                    }
                    break;
                }
                case 3:{
                    if(R[1][0]==Color.WHITE)
                    {
                        buf="dFDfdrdR";
                        execSequence();
                    }
                    else if(R[1][0]==Color.YELLOW)
                    {
                        buf="DbdBDRDr";
                        execSequence();
                    }
                    break;
                }
                case 4:{
                    if(R[1][0]==Color.BLUE)
                    {
                        buf="RDrdbdB";
                        execSequence();
                    }
                    else if(R[1][0]==Color.GREEN)
                    {
                        buf="ddldLDBDb";
                        execSequence();
                    }
                }
            }
        }
        else
        {
            if(G[1][2]!=Color.GREEN || W[1][0]!=Color.WHITE)
            {
                buf="DLDldfdF";
                execSequence();
            }
            else if(W[1][2]!=Color.WHITE || B[1][0]!=Color.BLUE)
            {
                buf="drdRDFDf";
                execSequence();
            }
            else if(B[1][2]!=Color.BLUE || Y[1][0]!=Color.YELLOW)
            {
                buf="DRDrdbdB";
                execSequence();
            }
            else if(Y[1][2]!=Color.YELLOW || G[1][0]!=Color.GREEN)
            {
                buf="dldLDBDb";
                execSequence();
            }
        }
    }
    
    void bottomCross()
    {
        scanBottomCross();
        while(!bottomCrossComplete)
        {
            setBottomCross();
            scanBottomCross();
        }
    }
    
    void setBottomCross()
    {
            if(R[1][2]==Color.RED &&(R[1][0]==Color.RED || R[2][1]==Color.RED))
            {
                buf="FDLdlf";
                execSequence();
            }
            else if(R[0][1]==Color.RED && (R[1][2]==Color.RED || R[2][1]==Color.RED))
            {
                buf="LDBdbl";
                execSequence();
            }
            else if(R[1][0]==Color.RED && R[0][1]==Color.RED)
            {
                buf="BDRdrb";
                execSequence();
            }
            else if(R[2][1]==Color.RED && R[1][0]==Color.RED)
            {
                buf="RDFdfr";
                execSequence();
            }
            else
            {
                buf="FDLdlf";
                execSequence();
            }
    }
    
    void scanBottomCross()
    {
        bottomCrossComplete=false;
        if(R[0][1]==Color.RED && R[1][2]==Color.RED && R[2][1]==Color.RED && R[1][0]==Color.RED)
            bottomCrossComplete=true;
    }
    
    void bottomCorner()
    {
        scanBottomCorner();
        while(!bottomCornerComplete)
        {
            setBottomCorner();
            scanBottomCorner();
        }
    }
    
    void scanBottomCorner()
    {
        bottomCornerComplete=false;
        if(R[0][0]==Color.RED && R[0][2]==Color.RED && R[2][0]==Color.RED && R[2][2]==Color.RED)
        {
            bottomCornerComplete=true;
        }
    }
    
    char bottomCornerOrientation()
    {
        //Scanning bottom for no red corners
        if(R[0][0]!=Color.RED && R[2][0]!=Color.RED && R[2][2]!=Color.RED && R[0][2]!=Color.RED)
        {
            if(W[2][2]==Color.RED && W[2][0]!=Color.RED)
                return 'W';
            else if(G[2][2]==Color.RED && G[2][0]!=Color.RED)
                return 'G';
            else if(Y[2][2]==Color.RED && Y[2][0]!=Color.RED)
                return 'Y';
            else if(B[2][2]==Color.RED && B[2][0]!=Color.RED)
                return 'B';
            else if(W[2][2]==Color.RED && W[2][0]==Color.RED)
                return 'W';
            else if(G[2][2]==Color.RED && G[2][0]==Color.RED)
                return 'G';
        }
        //Scanning bottom for just one red corner
        else if(R[0][2]==Color.RED && R[0][0]!=Color.RED && R[2][2]!=Color.RED && R[2][0]!=Color.RED)
            return 'W';
        else if(R[0][0]==Color.RED && R[2][0]!=Color.RED && R[2][2]!=Color.RED && R[0][2]!=Color.RED)
            return 'G';
        else if(R[2][0]==Color.RED && R[0][0]!=Color.RED && R[2][2]!=Color.RED && R[0][2]!=Color.RED)
            return 'Y';
        else if(R[2][2]==Color.RED && R[2][0]!=Color.RED && R[0][0]!=Color.RED && R[0][2]!=Color.RED)
            return 'B';
        //Scanning for two reds corner
        else if(R[0][2]==Color.RED && R[0][0]==Color.RED && R[2][2]!=Color.RED && R[2][0]!= Color.RED)
        {
            if(B[2][2]==Color.RED)
                return 'B';
            else if(Y[2][2]==Color.RED)
                return 'Y';            
        }
        else if(R[0][2]==Color.RED && R[0][0]!=Color.RED && R[2][2]!=Color.RED && R[2][0]== Color.RED)
        {
            if(B[2][2]==Color.RED)
                return 'B';
            else if(G[2][2]==Color.RED)
                return 'G';
        }
        else if(R[0][0]==Color.RED && R[2][0]==Color.RED && R[2][2]!=Color.RED && R[0][2]!=Color.RED)
        {
            if(W[2][2]==Color.RED)
                return 'W';
            else if(B[2][2]==Color.RED)
                return 'B';
        }
        else if(R[0][0]==Color.RED && R[2][0]!=Color.RED && R[2][2]==Color.RED && R[0][2]!=Color.RED)
        {
            if(W[2][2]==Color.RED)
                return 'W';
            else if(Y[2][2]==Color.RED)
                return 'Y';
        }
        else if(R[2][0]==Color.RED && R[0][0]!=Color.RED && R[2][2]==Color.RED && R[0][2]!=Color.RED)
        {
            if(G[2][2]==Color.RED)
                return 'G';
            else if(W[2][2]==Color.RED)
                return 'W';
        }
        else if(R[2][2]==Color.RED && R[2][0]!=Color.RED && R[0][0]!=Color.RED && R[0][2]==Color.RED)
        {
            if(Y[2][2]==Color.RED)
                return 'Y';
            else if(G[2][2]==Color.RED)
                return 'G';
        }
        return 'X';
    }
    
    void setBottomCorner()
    {
        char orient=bottomCornerOrientation();
        switch(orient)
        {
            case 'W': buf="LDlDLDDl";
                      execSequence();
                      break;
            
            case 'G': buf="BDbDBDDb";
                      execSequence();
                      break;
                
            case 'Y': buf="RDrDRDDr";
                      execSequence();
                      break;
               
            case 'B': buf="FDfDFDDf";
                      execSequence();
                      break;
            
        }        
    }
    
    void sideCorner()
    {
        scanSideCorner();
        while(!sideCornerComplete)
        {
            setSideCorner();
            scanSideCorner();
        }
        matchSideCorner();
    }
    
    void scanSideCorner()
    {
        sideCornerComplete=false;
        if(W[2][2]==W[2][0] && G[2][2]==G[2][0] && Y[2][2]==Y[2][0] && B[2][2]==B[2][0])
            sideCornerComplete=true;
    }
    
    void setSideCorner()
    {
        if(Y[2][2]==Y[2][0])
        {
            buf="lFlBBLflBBLL";
            execSequence();
        }
        else if(B[2][2]==B[2][0])
        {
            buf="bLbRRBlbRRBB";
            execSequence();
        }
        else if(W[2][2]==W[2][0])
        {
            buf="rBrFFRbrFFRR";
            execSequence();
        }
        else
        {
            buf="fRfLLFrfLLFF";
            execSequence();
        }
    }
    
    void matchSideCorner()
    {
        if(W[2][2]==Color.BLUE)
        {
            buf="D";
            execSequence();
        }
        else if(W[2][2]==Color.GREEN)
        {
            buf="d";
            execSequence();
        }
        else if(W[2][2]==Color.YELLOW)
        {
            buf="dd";
            execSequence();
        }
    }
    
    void sideEdge()
    {
        scanSideEdge();
        while(!sideEdgeComplete)
        {
            setSideEdge();
            scanSideEdge();            
        }
    }
    
    void scanSideEdge()
    {
        sideEdgeComplete=false;
        if(W[2][1]==Color.WHITE && G[2][1]==Color.GREEN && Y[2][1]==Color.YELLOW && B[2][1]==Color.BLUE)
            sideEdgeComplete=true;
    }
    
    void setSideEdge()
    {
        //yellow front
        if(W[2][1]==Color.WHITE)
        {
            if(Y[2][1]==Color.GREEN)
            {
                buf="BBDLrBBlRDBB";
                execSequence();
            }
            else
            {
                buf="BBdLrBBlRdBB";
                execSequence();
            }
        }
        //blue front
        else if(G[2][1]==Color.GREEN)
        {
            if(B[2][1]==Color.YELLOW)
            {
                buf="RRDBfRRbFDRR";
                execSequence();
            }
            else
            {
                buf="RRdBfRRbFdRR";
                execSequence();
            }
        }
        //White front
        else if(Y[2][1]==Color.YELLOW)
        {
            if(W[2][1]==Color.BLUE)
            {
                buf="FFDRlFFrLDFF";
                execSequence();                
            }
            else
            {
                buf="FFdRlFFrLdFF";
                execSequence();
            }
        }
        //Green Front
        else if(B[2][1]==Color.BLUE)
        {
            if(G[2][1]==Color.WHITE)
            {
                buf="LLDFbLLfBDLL";
                execSequence();
            }
            else
            {
                buf="LLdFbLLfBdLL";
                execSequence();
            }
        }
        else
        {
            buf="FFDRlFFrLDFF";
            execSequence();
        }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Main().setVisible(true);
            }
        });
        
    }    
   
    // Buffers
    String buf= new String();
    // Markers
    boolean crossComplete=false, cornerComplete=false, midComplete=false, bottomCrossComplete=false;
    boolean bottomCornerComplete=false, sideCornerComplete=false, sideEdgeComplete=false;
    // Array Declarations
    public static Color[][] G, W, B, Y, O, R;
    // Temporary variable
    Color[] Src;    // Temporary variable
    //Color[] Src;
    public static int faceCount=0;
    // Grid Declaration
    Grid grd;
    //Visual Input -- Webcam Unit
    VisualInputForm vi;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables
}