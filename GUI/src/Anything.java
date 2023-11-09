import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Anything extends JFrame{
    private JButton button1;
    private JTextField textField1;
    private JPanel jpanel;
    private JRadioButton rbMale;
    private JRadioButton rbFemale;
    private JRadioButton rbPNTS;
    private JTextArea textArea1;
    private JComboBox cbProgram;
    private JCheckBox cbC;
    private JCheckBox cbJava;
    private JCheckBox cbCplus;

    public Anything() {

        button1.addActionListener(e -> {

            String name = textField1.getText();

            if (name.equals("") || cbProgram.getSelectedIndex() == 0){
                if(cbProgram.getSelectedIndex() == 0){
                    int opt = JOptionPane.showConfirmDialog(this, "No program? I will set it to CS");
                    if(opt == JOptionPane.YES_OPTION){
                        cbProgram.setSelectedIndex(1);
                    } else {
                        return;
                    }
                }
                if (name.equals("")){
                    name = JOptionPane.showInputDialog("What is your name");
                    textField1.setText(name);
                }
               // JOptionPane.showMessageDialog(this, "Invalid");
            }
            else{
                textArea1.append("Hello " + name + "! \n");
                textArea1.append("Your gender is ");

                if (rbMale.isSelected()){
                    textArea1.append("Male\n");
                } else if(rbFemale.isSelected()){
                    textArea1.append("Female\n");
                } else if(rbPNTS.isSelected()){
                    textArea1.append("(unknowm) \n");
                }
                String prog = (String) cbProgram.getSelectedItem();
                textArea1.append("Program is " + prog + "\n");

                textArea1.append("You know");
                if (cbC.isSelected()){
                    textArea1.append(" how to code using C\n");
                } else if(cbJava.isSelected()){
                    textArea1.append(" how to code using Java\n");
                } else if(cbCplus.isSelected()){
                    textArea1.append(" how to code usingC++\n");
                } else {
                    textArea1.append(" nothing!\n");
                }
            }
        });
    }

    public static void main(String[] args) {
        Anything app = new Anything();
        app.setContentPane(app.jpanel);
        app.setSize(1000, 500);
        app.setDefaultCloseOperation(EXIT_ON_CLOSE);
        app.setVisible(true);
        app.setTitle("Hello World");

    }
}
