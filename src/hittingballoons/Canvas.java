package hittingballoons;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class Canvas extends JPanel{
    private double gunTail_x = 250;
    private double gunTail_y = 273;
    private final double gunLength = 40;
    private double gunHead_x = gunTail_x;
    private double gunHead_y = (gunTail_y - gunLength);
    private int bubbleDiameter = 20;
    private int bubble_y = getHeight();
    private int bubble_x = 250;
    private double bullet_x = gunHead_x;
    private double bullet_y = gunTail_y;
    private final int bulletDiameter = 7;
    private final int speedOfBullet = 5;
    private final int speedOfBalloon = 1;
    private final long startTime = System.currentTimeMillis();
    private long closeTime = 0;
    private int k = 1;
    private int score = 0;
    private final double fiveDegrees = Math.PI / 36;
    private double angle = 0;
    private int count = 1;                                                      //count is to stop repainting the bullet each time when
    public Canvas(){                                                            //the left or right arrow key is pressed
        this.setBackground(Color.lightGray);
        addKeyListener(new KeyAdapter(){
           public void keyPressed(KeyEvent e){
               switch(e.getKeyCode()){
                   case KeyEvent.VK_UP:
                        if(bullet_x < 0 || bullet_x > 500 || bullet_y < 0)      //reassigning position of the bullet once it crosses the border
                        {
                            
                        if(angle >= -((Math.PI / 2) + (Math.PI / 36)) && angle < Math.PI / 2){
                            bullet_x = gunHead_x - 3;           
                            bullet_y = gunHead_y - 3;
                        }
                        else if(angle > -Math.PI / 2 && angle <= ((Math.PI / 2) + (Math.PI / 36))){
                            bullet_x = gunHead_x + 3;           
                            bullet_y = gunHead_y - 3;
                        }}
                        count = 0;
                        break;
                   case KeyEvent.VK_RIGHT:
                        if(angle >= -((Math.PI / 2) + (Math.PI / 36)) && angle < Math.PI / 2)
                        {                               
                            angle = angle + fiveDegrees;
                            gunHead_x = gunTail_x + gunLength * Math.sin(angle);
                            gunHead_y = gunTail_y - gunLength * Math.cos(angle);
                            bullet_x = gunHead_x - 3;
                            bullet_y = gunHead_y - 3;
                            count = 1;
                        }
                        break;
                   case KeyEvent.VK_LEFT:
                       if(angle > -Math.PI / 2 && angle <= ((Math.PI / 2) + (Math.PI / 36)))
                       {
                           angle = angle - fiveDegrees;
                           gunHead_x = gunTail_x + gunLength * Math.sin(angle);
                           gunHead_y = gunTail_y - gunLength * Math.cos(angle);
                           bullet_x = gunHead_x - 3;
                           bullet_y = gunHead_y - 3;
                           count = 1;
                       }
                       break;
                   default:
                       break;
               }
           }
        });
        Timer timer = new Timer(15, new TimerListener());
        timer.start();
    }
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        if(k==1)
        {
            closeTime = System.currentTimeMillis();
            g.setColor(Color.red);
            g.drawLine((int)gunHead_x, (int)gunHead_y, (int)gunTail_x, (int)gunTail_y);
            if(bubble_y < 0){
                bubble_y = getHeight() - bubbleDiameter;
                bubble_x =(int)(Math.random()*400);
                bubbleDiameter = 20;
            }
            bubble_y = bubble_y - speedOfBalloon;
            g.setColor(Color.blue);
            g.fillOval(bubble_x, bubble_y, bubbleDiameter, bubbleDiameter);
            g.setColor(Color.red);
            g.fillOval((int)bullet_x,(int)bullet_y, bulletDiameter, bulletDiameter);
            if(closeTime - startTime >= 60000)
            {
                bubbleDiameter = 0;
                k = 0;
                JOptionPane.showMessageDialog(null,"Time's Up!\nYour score is " + score,"Your Score",JOptionPane.INFORMATION_MESSAGE);
                System.exit(0);
            }
            
            //bullet will move only when count is equal to zero
            if((bullet_y > -7 && bullet_x < 507 && bullet_x > -7) && count == 0){
                if(angle > 0){
                    bullet_x = bullet_x + speedOfBullet * Math.cos(Math.atan((gunHead_y - gunTail_y) / (gunHead_x - gunTail_x)));
                    bullet_y = bullet_y + speedOfBullet * Math.sin(Math.atan((gunHead_y - gunTail_y) / (gunHead_x - gunTail_x)));
                }
                else if(angle < 0){
                    bullet_x = bullet_x - speedOfBullet * Math.cos(Math.atan((gunHead_y - gunTail_y) / (gunHead_x - gunTail_x)));
                    bullet_y = bullet_y - speedOfBullet * Math.abs(Math.sin(Math.atan((gunHead_y - gunTail_y) / (gunHead_x - gunTail_x))));
                }
                else{
                    bullet_y = bullet_y - speedOfBullet;
                }
            }
            
            //bullet_x,bullet_y,bubble_x & bubble_y are the coordinates of the extreme left of the ovals
            //+3 and +10 is to get the coordionates of the centers
            
            if(Math.sqrt(Math.pow((bullet_x+3)-(bubble_x+10),2)+Math.pow((bullet_y+3)-(bubble_y+10),2)) < 14){
                g.setColor(Color.blue);
                g.fillOval(bubble_x + 25, bubble_y + 25, 10, 10);
                g.fillOval(bubble_x + 25, bubble_y - 15, 10, 10);
                g.fillOval(bubble_x - 15, bubble_y + 25, 10, 10);
                g.fillOval(bubble_x - 15, bubble_y - 15, 10, 10);
                bubble_y = getHeight() - (bubbleDiameter / 2);
                bubble_x =(int)(Math.random()*400) - (bubbleDiameter / 2);
                bubbleDiameter = 20;
                score++;
            }
        }
    }
    class TimerListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            repaint();
        }
    }
}