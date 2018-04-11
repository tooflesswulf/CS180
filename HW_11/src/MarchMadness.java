import javax.swing.*;
import java.util.Random;

/**
 * MarchMadness
 *
 * description goes here.
 *
 * @author Albert Xu
 *
 * @version 4/5/18
 *
 */

public class MarchMadness extends JFrame {
    public static int getWinrate(String msg) {
        while(true) {
            String in = JOptionPane.showInputDialog(msg);
            if(in==null) return -1;
            
            try {
                int ret = Integer.valueOf(in);
                if(0 <= ret && ret <= 100) {
                    return ret;
                }
            } catch (NumberFormatException e) { }
            JOptionPane.showMessageDialog(null,"Please enter a number between 0 and 100.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    static String getName(String msg) {
        while(true) {
            String in = JOptionPane.showInputDialog(msg);
            if(in==null) return null;
            
            if(in.equals("")) {
                JOptionPane.showMessageDialog(null,"Please enter a name.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                return in;
            }
        }
    }
    
    public static void main(String[] args) {
        Random r = new Random();
        String title = "March Madness Simulator";

        while(true) {
            String t1=getName("Enter the name of team one.");
            if(t1==null) break;
            String t2=getName("Enter the name of team two.");
            if(t2==null) break;
    
            int t1w=getWinrate("Enter team one's win percentage.");
            if(t1w==-1) break;
            int t2w=getWinrate("Enter team two's win percentage.");
            if(t2w==-1) break;
    
            while (t1w + t2w != 100) {
                JOptionPane.showMessageDialog(null, "The two teams' winrate must add to 100.", "Error", JOptionPane.ERROR_MESSAGE);
                t1w=getWinrate("Enter team one's win percentage.");
                if(t1w==-1) break;
                t2w=getWinrate("Enter team two's win percentage.");
                if(t2w==-1) break;
            }
            if(t1w==-1) break;
            if(t2w==-1) break;
    
    
            String[] options={"20", "15", "10", "5"};
            int resp=JOptionPane.showOptionDialog(null,
                    "Choose the number of simulations you want to run.", title,
                    JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                    null, options, options[0]);
    
            if(resp==-1) break;
            
            int nreps=Integer.valueOf(options[resp]);
            int[] wins = {0, 0};
            
            for (int i=0; i < nreps; ++i) {
                if(r.nextInt(100) < t1w) {
                    wins[0]++;
                } else {
                    wins[1]++;
                }
            }
    
            JOptionPane.showMessageDialog(null,
                    String.format("%s wins: %d %s wins: %d", t1, wins[0], t2, wins[1]));
    
            int n = JOptionPane.showConfirmDialog(
                    null,
                    "Would you like to run another simulation?",
                    title,
                    JOptionPane.YES_NO_OPTION);
            
            if(n != JOptionPane.YES_OPTION) break;
        }
    
        JOptionPane.showMessageDialog(null,
                "Honestly this last dialog is really annoying.\nhurdur goodbye.");
    }
}