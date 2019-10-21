/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package typeGame;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PushbackReader;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author hp
 */
public class Controller {
    private mainGUI1 view;
    private int point = 50;

    public Controller(mainGUI1 view) {
        this.view = view;
    }
    
    public void proses(){
        this.view.getTypeText().setText("");
        this.view.getLabelPoint().setText(String.valueOf(this.point));
        try{
            File file = new File("lirik.txt");
            PushbackReader reader = new PushbackReader(new InputStreamReader(new FileInputStream(file)));
            
            char[] words = new char[(int) file.length()];
            reader.read(words);
            
            String data = null;
            if((data = new String (words))!=null){
                String[] list = data.split("\\s+");
                
                String mess = "";
                for (int i = 0; i < 4; i++) {
                    int rand = new Random().nextInt(list.length);
                    mess += list[rand] + " ";
                    
                }
                view.getLabelText().setText(mess);
            }
            
            if(this.point == 0){
                int dialogButton = JOptionPane.YES_NO_OPTION;
                JOptionPane.showConfirmDialog(null, "Do you want to continue?", "Warning", dialogButton);
                if(dialogButton == JOptionPane.YES_OPTION){
                    this.point = 50;
                    proses();
                }
                if(dialogButton == JOptionPane.NO_OPTION){
                    System.exit(0);
                }
            }
            
        }catch(FileNotFoundException ex){
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }catch(IOException ex){
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
