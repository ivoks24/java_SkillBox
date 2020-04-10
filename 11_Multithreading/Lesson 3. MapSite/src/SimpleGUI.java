import java.awt.*;
import java.awt.event.*;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class SimpleGUI extends JFrame {

    private JPanel panel = new JPanel();
    private JButton button = new JButton("Click");
    private JTextField output = new JTextField("", 5); // Document doc = Jsoup.connect(paths).get();
    private JLabel label = new JLabel();

    public SimpleGUI() {

        super("Simple Example");
        this.setBounds(100, 100, 250, 100);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        output.setSize(20, 50);

        Container container = this.getContentPane();
        container.setLayout(new GridLayout(1, 2, 2, 2));

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(output);
        panel.add(label);

        label.setText("RTYUIIIIIII");

        container.add(panel);

        button.addActionListener(e -> {

            Process process = new Process(output.getText());
            label.setText(process.getFinalSites().get(5));
            JFileChooser fc = new JFileChooser();
//            FileNameExtensionFilter filter = new FileNameExtensionFilter("*.TXT","*.*");
//            fc.setFileFilter(filter);
//            if ( fc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION ) {
//                try ( FileWriter fw = new FileWriter(fc.getSelectedFile()) ) {
//                    fw.write(process.getFinalSites().get(5));
//                }
//                catch (IOException ex ) {
//                    System.out.println("Всё погибло!");
//                }
//            }

        });
        container.add(button);

    }
}
