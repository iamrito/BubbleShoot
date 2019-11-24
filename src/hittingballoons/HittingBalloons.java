package hittingballoons;
import javax.swing.*;
public class HittingBalloons extends JFrame{
    private final Canvas canvas = new Canvas();
    public HittingBalloons(){
        add(canvas);
        canvas.setFocusable(true);
    }
    public static void main(String[] args) {
        JOptionPane.showMessageDialog(null,"CONTROLS:\n\t\tPress right and left arrow key to move the Gun.\n\t\tUp arrow key to shoot.\nYou have 60 seconds.\nTry to hit as many bubbles as you can."
                + "\nClick OK to Start.","Start The Game",JOptionPane.INFORMATION_MESSAGE);
        JFrame frame = new HittingBalloons();
        frame.setTitle("Hitting Balloons");
        frame.setSize(500,300);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setResizable(false);
    }
}
