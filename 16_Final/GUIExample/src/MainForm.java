import javax.swing.*;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;

public class MainForm {

    private JPanel rootPanel;
    private JButton button;
    private JTextField surname;
    private JTextField givenName;
    private JTextField patronymic;
    private JPanel firstPanel;
    private boolean onOff = true;

    public MainForm(JFrame frame) {

        firstPanel.setLayout(new BoxLayout(firstPanel, BoxLayout.Y_AXIS));

        button.addActionListener(new Action() {
            @Override
            public Object getValue(String key) {
                return null;
            }

            @Override
            public void putValue(String key, Object value) {

            }

            @Override
            public void setEnabled(boolean b) {

            }

            @Override
            public boolean isEnabled() {
                return false;
            }

            @Override
            public void addPropertyChangeListener(PropertyChangeListener listener) {

            }

            @Override
            public void removePropertyChangeListener(PropertyChangeListener listener) {

            }

            @Override
            public void actionPerformed(ActionEvent e) {

                if (onOff) {

                    String sur = surname.getText().trim();
                    String name = givenName.getText().trim();

                    if (!sur.isEmpty() && !name.isEmpty()) {

                        button.setText("Expand");

                        frame.setSize(250, 110);
                        surname.setSize(200, 30);

                        button.setName("Expand");
                        surname.setText(sur + " " + name + " " + patronymic.getText().trim());

                        givenName.setVisible(false);
                        patronymic.setText("");
                        patronymic.setVisible(false);

                        onOff = false;

                    } else {
                        JOptionPane.showMessageDialog(
                                rootPanel,
                                "Имя и Фамилия обязательные для ввода!",
                                "Error!",
                                JOptionPane.WARNING_MESSAGE);
                    }
                } else {

                    String[] fullName = surname.getText().trim()
                            .replace("\\+", " ")
                            .split(" ");

                    if (fullName.length > 1) {

                        button.setText("Collapse");

                        frame.setSize(200, 170);
                        surname.setSize(110, 30);

                        givenName.setVisible(true);
                        patronymic.setVisible(true);

                        surname.setText(fullName[0]);
                        givenName.setText(fullName[1]);

                        if (fullName.length > 2) {
                            patronymic.setText(fullName[2]);
                        }

                        onOff = true;
                    } else {
                        JOptionPane.showMessageDialog(
                                rootPanel,
                                "Имя и Фамилия обязательные для ввода!",
                                "Error!",
                                JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
        });

    }

    public JPanel getRootPanel() {
        return rootPanel;
    }
}
