import javax.swing.*;

public class Main {

    public static void main(String[] args) {

//        SimpleGUI app = new SimpleGUI();
//        app.setVisible(true);

        JFrame frame = new JFrame();

        Form form = new Form();
        frame.setContentPane(form.getRootPanel());

        frame.setSize(300, 140);
        frame.setLocationRelativeTo(null);
//         frame.dispose();
//         frame.setUndecorated(true);
        frame.setResizable(false);

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}