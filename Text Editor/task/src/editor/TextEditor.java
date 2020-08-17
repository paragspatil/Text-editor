package editor;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class TextEditor extends JFrame {

    private JTextArea TextArea;
    private JTextArea FilenameField;
    private JButton SaveButton;
    private JButton LoadButton;
    private JScrollPane ScrollPane;
    private JPanel bottomPanel;
    private JPanel topPanel;
    private JMenu MenuFile;
    private JMenuItem MenuLoad;
    private JMenuItem MenuSave;
    private JMenuItem MenuExit;

    public TextEditor() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
        setVisible(true);
        //setLayout(null);
        setTitle("The first Stage");

        TextArea = getTextArea();
        FilenameField = fileNameTextArea();
        SaveButton = saveButton();
        LoadButton = loadButton();
        ScrollPane = scrollableTextArea();
        topPanel = topBar();
        bottomPanel = bottomBar();
        MenuSave = MenuSave();
        MenuLoad = MenuLoad();
        MenuExit = MenuExit();
        MenuFile = MenuFile();

        topPanel.add(FilenameField,BorderLayout.EAST);
        topPanel.add(SaveButton);
        topPanel.add(LoadButton);

        bottomPanel.add(ScrollPane);
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(MenuFile);
        setJMenuBar(menuBar);
        add(topPanel,BorderLayout.PAGE_START);
        add(ScrollPane,BorderLayout.CENTER);

    }
    private JTextArea getTextArea(){
        JTextArea textArea = new JTextArea();
        textArea.setVisible(true);
        textArea.setName("TextArea");
        //textArea.setEditable(true);

        textArea.setRows(10);
       textArea.setColumns(10);
        textArea.setName("TextArea");
        return textArea;
    }
    private JScrollPane scrollableTextArea(){
        JScrollPane scrollableTextArea = new JScrollPane(TextArea);
        scrollableTextArea.setSize(200,200);
        scrollableTextArea.setVisible(true);
        scrollableTextArea.setName("ScrollPane");
        return scrollableTextArea;
    }
    private  JTextArea fileNameTextArea(){
        JTextArea fileNameTextArea = new JTextArea();
        fileNameTextArea.setRows(1);
        fileNameTextArea.getDocument().putProperty("filterNewlines", Boolean.TRUE);
        fileNameTextArea.setColumns(30);
        fileNameTextArea.setVisible(true);
        fileNameTextArea.setName("FilenameField");
        return fileNameTextArea;
    }
    private JButton saveButton(){
        JButton saveButton = new JButton("SaveButton");
        saveButton.setVisible(true);
        saveButton.setText("Save");
        saveButton.setSize(100,50);
        saveButton.setName("SaveButton");
        saveButton.addActionListener(e->{
               saveFile();
        });
        return saveButton;
    }
    private JButton loadButton(){
        JButton loadButton = new JButton("LoadButton");
        loadButton.setVisible(true);
        loadButton.setSize(100,50);
        loadButton.setText("Load");
        loadButton.setName("LoadButton");
        loadButton.addActionListener(e->{
         loadFile();
        });
        return loadButton;
    }

    private JPanel topBar(){
        JPanel topbar = new JPanel();
        topbar.setVisible(true);
        topbar.setSize(450,100);
        topbar.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

        return topbar;
    }
    private JPanel bottomBar(){
        JPanel bottomBar = new JPanel();
        bottomBar.setVisible(true);
        bottomBar.setSize(300,300);

        return bottomBar;
    }


    private JMenu MenuFile(){
        JMenu MenuFile = new JMenu("File");
        MenuFile.setName("MenuFile");
        MenuFile.add(MenuSave);
        MenuFile.add(MenuLoad);
        MenuFile.add(MenuExit);

        return MenuFile;
    }

    private JMenuItem MenuSave(){
        JMenuItem MenuSave = new JMenuItem("Save");
        MenuSave.setName("MenuSave");
        MenuSave.addActionListener(e -> {
            saveFile();
        });
        return MenuSave;
    }

    private JMenuItem MenuLoad(){
        JMenuItem MenuLoad = new JMenuItem("Load");
        MenuLoad.setName("MenuLoad");
        MenuLoad.addActionListener(e -> {
            loadFile();
        });
        return MenuLoad;
    }

    private JMenuItem MenuExit(){
        JMenuItem MenuExit = new JMenuItem("Exit");
        MenuExit.setName("MenuExit");
        dispose();
        return MenuExit;
    }

    private void saveFile(){
        File newFile = new File("E:\\trash\\Text Editor\\Text Editor\\task\\src\\" + FilenameField.getText());
        try(FileWriter writer = new FileWriter(newFile)){
            writer.write(TextArea.getText());
            //System.out.println("and the save content is " +TextArea.getText());
           // System.out.println("filename is " + FilenameField.getText());
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public static String readFileAsString(String fileName) throws IOException {
        return new String(Files.readAllBytes(Paths.get(fileName)));
    }
    private void loadFile(){

        File loadFile = new File("E:\\trash\\Text Editor\\Text Editor\\task\\src\\" + FilenameField.getText());
        String content = null;
        try {
            content = readFileAsString("E:\\trash\\Text Editor\\Text Editor\\task\\src\\" + FilenameField.getText());
        } catch (IOException e) {
            e.printStackTrace();
        }
        TextArea.setText(content);
        /*
        try {
            Scanner scanner = new Scanner(loadFile);
            while (scanner.hasNext()){
                content += scanner.nextLine();
            }
            TextArea.setText(content);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        */




    }

}

