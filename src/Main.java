import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

public class SnowApp extends JPanel implements ActionListener, MouseListener {

    static class Snowflake {
        int x, y, size, speed;
    }

    ArrayList<Snowflake> flakes = new ArrayList<>();
    Random rand = new Random();

    int clicked = 0;
    final int TARGET = 67;
    boolean completed = false;

    Timer timer = new Timer(30, this);

    public SnowApp() {
        setBackground(Color.BLACK);
        addMouseListener(this);

        for (int i = 0; i < 80; i++) {
            flakes.add(createFlake());
        }

        timer.start();
    }

    Snowflake createFlake() {
        Snowflake f = new Snowflake();
        f.x = rand.nextInt(1920);
        f.y = rand.nextInt(1080);
        f.size = 8 + rand.nextInt(10);
        f.speed = 1 + rand.nextInt(3);
        return f;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.WHITE);

        if (!completed) {
            for (Snowflake f : flakes) {
                g.fillOval(f.x, f.y, f.size, f.size);
            }

            g.setFont(new Font("Arial", Font.BOLD, 32));
            g.drawString("the words best boyfriend",
                    getWidth() / 2 - 260,
                    getHeight() / 2);

            g.setFont(new Font("Arial", Font.BOLD, 24));
            g.drawString(clicked + " / " + TARGET,
                    getWidth() - 150,
                    40);
        } else {
            g.setFont(new Font("Arial", Font.BOLD, 40));
            g.drawString("Successful ATM installation",
                    getWidth() / 2 - 380,
                    getHeight() / 2);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!completed) {
            for (Snowflake f : flakes) {
                f.y += f.speed;
                if (f.y > getHeight()) {
                    f.y = 0;
                    f.x = rand.nextInt(getWidth());
                }
            }
        }
        repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (completed) return;

        for (int i = flakes.size() - 1; i >= 0; i--) {
            Snowflake f = flakes.get(i);
            Rectangle r = new Rectangle(f.x, f.y, f.size, f.size);
            if (r.contains(e.getPoint())) {
                flakes.remove(i);
                clicked++;

                if (clicked >= TARGET) {
                    completed = true;
                }
                break;
            }
        }
    }

    // unused mouse methods
    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}

    public static void main(String[] args) {
        JFrame frame = new JFrame("Snow App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setUndecorated(true);

        frame.add(new SnowApp());
        frame.setVisible(true);
    }
}
