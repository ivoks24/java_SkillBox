import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import javax.swing.*;

public class SimpleGUI extends JFrame {

    private final JTextField output = new JTextField(15); // Document doc = Jsoup.connect(paths).get();

    public SimpleGUI() {

        super("Simple Example");
        this.setBounds(10, 10, 300, 120);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        output.setSize(20, 50);

        Container container = this.getContentPane();
        container.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 15));
        container.setBackground(Color.DARK_GRAY);

        output.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(0, 5, 0, 5),
                BorderFactory.createMatteBorder(2, 0, 2, 0, Color.LIGHT_GRAY)));

        JButton button = new JButton("Click");
        container.add(output);
        container.add(button);

        button.addActionListener(e -> {
            try {

                new URL(output.getText());

                Process process = new Process(output.getText());
                output.setText("");

                do {
                    Thread.sleep(200);
                } while (process.getCountThread().get() != 0);

                JFileChooser fc = new JFileChooser();
                if (fc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                    FileWriter fw = new FileWriter(fc.getSelectedFile());
                    process.getTreeSite().forEach(line -> {
                        try {
                            fw.write(line);
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }
                    });
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null,
                        ex.getLocalizedMessage(), "Error dialog!", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}
