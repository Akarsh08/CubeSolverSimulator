/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cubesolversimulator;
import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
/**
 *
 * @author AKARSH
 */
public class VisualInput {
    
    public VisualInput()
    {
        wc= Webcam.getDefault();
        wc.setViewSize(WebcamResolution.VGA.getSize());
        wp= new WebcamPanel(wc);
        wc.open();
        wp.setMirrored(true);
        wp.setBounds(725,430, 200, 150);        
    }
    
    public void capture()
    {        
        try {
            ImageIO.write(wc.getImage(), "PNG", new File("first.png"));
        } catch (IOException ex) {
           Logger.getLogger(VisualInput.class.getName()).log(Level.SEVERE, null, ex);
        }
    }   
    Webcam wc;
    WebcamPanel wp;
}
