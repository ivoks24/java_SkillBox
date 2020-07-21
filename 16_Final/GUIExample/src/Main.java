import javax.swing.*;

public class Main {

    public static void main(String[] args) {

        JFrame frame = new JFrame("MyFrame");
        frame.setSize(200, 170);
        frame.setLocationRelativeTo(null);

        frame.add(new MainForm(frame).getRootPanel());

        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }
}
