import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;

public class Form extends JFrame {
    private JPanel rootPanel;
    private JButton clickButton;
    private JTextField output;  // https://lenta.ru
    private JLabel label;
    private JPanel panel2;

    public Form() {

        panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));
        panel2.setBackground(rootPanel.getBackground());

        label.setVisible(false);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        label.setBorder(BorderFactory.createEmptyBorder(14, 10, 0, 0));
        output.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(0, 5, 0, 5),
                BorderFactory.createMatteBorder(2, 0, 2, 0, Color.LIGHT_GRAY)));

        clickButton.addActionListener(e -> {

            label.setVisible(false);

            try {
                Process process = new Process(output.getText());
                output.setText("");

                JFileChooser fc = new JFileChooser();
                if (fc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                    FileWriter fw = new FileWriter(fc.getSelectedFile());
                    process.getFinalSites().forEach(line -> {
                        try {
                            fw.write(line);
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }
                    });
                }
            } catch (IllegalArgumentException | IOException ex) {
                label.setVisible(true);
            }
        });
    }

    public JPanel getRootPanel() {
        return rootPanel;
    }
}
