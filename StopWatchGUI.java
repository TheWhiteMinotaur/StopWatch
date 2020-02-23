package project1;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;




public class StopWatchGUI {
    /*****************************************************************
     * Main Method
     ****************************************************************/ 
    public static void main(String args[]){
       JFrame frame = new JFrame ("StopWatch");
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       mainPanel panel = new mainPanel();
       frame.getContentPane().add(panel);
       frame.pack();
       frame.setVisible(true);

    }
}
