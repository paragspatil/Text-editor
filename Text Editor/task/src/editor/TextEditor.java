package editor;

import javax.swing.*;
import java.awt.*;

public class TextEditor extends JFrame {
    public TextEditor() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 300);
        setVisible(true);
        setLayout(null);
        setTitle("The first Stage");


        JTextArea textArea = getTextArea();
        add(textArea);
    }
    private JTextArea getTextArea(){
        JTextArea textArea = new JTextArea();
        textArea.setVisible(true);
        textArea.setName("TextArea");
        textArea.setEditable(true);
        textArea.setBounds(25,25,200,200);



        return textArea;
    }

}

