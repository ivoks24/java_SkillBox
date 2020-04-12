import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.*;

public class SimpleGUI extends JFrame {

    private JPanel panel = new JPanel();
    private JButton button = new JButton("Click");
    private JTextField output = new JTextField(15); // Document doc = Jsoup.connect(paths).get();
    private JLabel label = new JLabel("Error!");

    public SimpleGUI() {

        super("Simple Example");
        this.setBounds(10, 10, 300, 120);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        output.setSize(20, 50);

        Container container = this.getContentPane();
        container.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 15));
        container.setBackground(Color.DARK_GRAY);

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.DARK_GRAY);

        output.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(0, 5, 0, 5),
                BorderFactory.createMatteBorder(2, 0, 2, 0, Color.LIGHT_GRAY)));

        label.setVisible(false);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        label.setBorder(BorderFactory.createEmptyBorder(14, 0, 0, 0));
        label.setForeground(Color.RED);

        panel.add(output);
        panel.add(label);

        container.add(panel);
        container.add(button);

        button.addActionListener(e -> {

            label.setVisible(false);

            try {
                Process process = new Process(output.getText());
                output.setText("");

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
                label.setVisible(true);
            }
        });

    }
}
