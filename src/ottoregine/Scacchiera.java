package ottoregine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;;
import javax.imageio.ImageIO;

public class Scacchiera extends JFrame {
    private static final int DIM = 8;
    private JLabel[][] caselle = new JLabel[DIM][DIM];
    private BufferedImage queen;
    int[] pos = {-1,-1,-1,-1,-1,-1,-1,-1};
    public Scacchiera() throws InterruptedException {
        
        setLayout(new GridLayout(8,8));
        try {
            queen = ImageIO.read(new File("queen.png"));
        } catch (IOException ex) {
            System.out.println(ex);
        }
        boolean isBlack = true;
        for(int r = 0; r < DIM; r++){
            if (r%2 != 0) {
                isBlack = false;
            }else{
                isBlack = true;
            }
            
            for(int c = 0; c<DIM; c++){
                caselle[r][c] = new JLabel(new ImageIcon(queen));
                caselle[r][c].setOpaque(true);
                if(isBlack) {
                    caselle[r][c].setBackground(Color.darkGray);
                    isBlack = false;
                }else{
                    caselle[r][c].setBackground(Color.WHITE);
                    isBlack = true;
                }

                add(caselle[r][c]);
            }
        }
        
        setSize(600,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        this.algoritmoStart();
    }
    
    public void rappresenta(int[] posizioni) {
        for(int r = 0; r<DIM; r++) {
            int reginaSuColonna = posizioni[r];
            for(int c = 0; c < DIM; c++) {
                if(c == reginaSuColonna) {
                    caselle[r][c].setIcon(new ImageIcon(queen)); 
                }else {
                    caselle[r][c].setIcon(null);
                }
            }
        }
    }
    
    private boolean checkDiag(int riga) {
        for(int i = 0; i < riga; i++){
            if(this.pos[i]+i == this.pos[riga] + riga || this.pos[i] - i == this.pos[riga] - riga){
                return false;
            }
        }
        return true;
    }
    
    private boolean checkY(int riga){
        for(int i = 0; i < riga; i++) {
            for(int j = i +1; j <= riga; j++) {
                if(this.pos[i] == this.pos[j]) {
                    return false;
                }
            }
        }
        return true;
    }
    
    private void algoritmoStart() throws InterruptedException {
        int r = 0;
        while(r<DIM) {
            this.pos[r]++;
            this.rappresenta(this.pos);
            if(DIM == this.pos[r]) {
                this.pos[r] = -1;
                r--;
                
            } else if(checkY(r) && checkDiag(r)) {
                r++;
            }
            
            new Thread().sleep(50);
        }
        JOptionPane.showMessageDialog(null,"Enigma risolto");
    }
}
