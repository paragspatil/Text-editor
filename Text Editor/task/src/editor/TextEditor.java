package editor;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextEditor extends JFrame {

    List<Integer> initIndex = new ArrayList<>();
    List<Integer> finalIndex = new ArrayList<>();
    ListIterator<Integer> initIndexIter;
    ListIterator<Integer> finalIndexIter;


    private JTextArea TextArea;
    private JTextArea SearchField;
    private JButton SaveButton;
    private JButton OpenButton;
    private JScrollPane ScrollPane;
    private JPanel bottomPanel;
    private JPanel topPanel;
    private JMenu MenuFile;
    private JMenuItem MenuLoad;
    private JMenuItem MenuSave;
    private JMenuItem MenuExit;


    private Icon saveIcon = new ImageIcon("E:\\trash\\Text Editor\\Text Editor\\task\\src\\editor\\saveicon.png");
    private Icon fileIcon = new ImageIcon("E:\\trash\\Text Editor\\Text Editor\\task\\src\\editor\\fileIcon.png");
    private Icon previousIcon = new ImageIcon("E:\\trash\\Text Editor\\Text Editor\\task\\src\\editor\\previousIcon.png");
    private Icon nextIcon = new ImageIcon("E:\\trash\\Text Editor\\Text Editor\\task\\src\\editor\\nextIcon.png");
    private Icon searchIcon = new ImageIcon("E:\\trash\\Text Editor\\Text Editor\\task\\src\\editor\\searchIcon.png");

    private  JFileChooser FileChooser;


    private JMenu MenuSearch;
    private JMenuItem MenuStartSearch;
    private JMenuItem MenuPreviousMatch;
    private JMenuItem MenuNextMatch;
    private JMenuItem MenuUseRegExp;


    private JButton StartSearchButton;
    private JButton PreviousMatchButton;
    private JButton NextMatchButton;
    private JCheckBox UseRegExCheckbox;


    public TextEditor() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 500);
        setVisible(true);
        //setLayout(null);
        setTitle("The first Stage");

        TextArea = getTextArea();
        SearchField = SearchField();
        SaveButton = saveButton();
        OpenButton = OpenButton();
        ScrollPane = scrollableTextArea();
        topPanel = topBar();
        bottomPanel = bottomBar();
        MenuSave = MenuSave();
        MenuLoad = MenuLoad();
        MenuExit = MenuExit();
        MenuFile = MenuFile();
        FileChooser = FileChooser();
        StartSearchButton = StartSearchButton();
        PreviousMatchButton = PreviousMatchButton();
        NextMatchButton = NextMatchButton();
        UseRegExCheckbox = UseRegExCheckbox();

        MenuStartSearch = MenuStartSearch();
        MenuPreviousMatch = MenuPreviousMatch();
        MenuNextMatch = MenuNextMatch();
        MenuUseRegExp = MenuUseRegExp();
        MenuSearch = MenuSearch();

        topPanel.add(SaveButton);
        topPanel.add(OpenButton);
        topPanel.add(SearchField,BorderLayout.EAST);
        topPanel.add(StartSearchButton);
        topPanel.add(PreviousMatchButton);
        topPanel.add(NextMatchButton);
        topPanel.add(UseRegExCheckbox);
        topPanel.add(new JLabel("Use regex"));


        bottomPanel.add(ScrollPane);
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(MenuFile);
        menuBar.add(MenuSearch);
        setJMenuBar(menuBar);
        this.add(FileChooser);
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
    private  JTextArea SearchField(){
        JTextArea SearchField = new JTextArea();
        SearchField.setRows(2);
        SearchField.getDocument().putProperty("filterNewlines", Boolean.TRUE);
        SearchField.setColumns(30);
        SearchField.setVisible(true);
        SearchField.setName("SearchField");
        return SearchField;
    }
    private JButton saveButton(){

        JButton saveButton = new JButton(saveIcon);
        saveButton.setVisible(true);
        //saveButton.setText("Save");
        saveButton.setSize(50,50);
        saveButton.setName("SaveButton");
        saveButton.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.CYAN, 0),
                BorderFactory.createLineBorder(Color.BLACK, 0)));
        saveButton.addActionListener(e->{
               saveFile();
        });
        return saveButton;
    }
    private JButton OpenButton(){
        JButton OpenButton = new JButton(fileIcon);
        OpenButton.setVisible(true);
        OpenButton.setSize(10,10);
        //OpenButton.setText("Load");
        OpenButton.setName("OpenButton");
        System.out.println(OpenButton.getSize());
        OpenButton.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.CYAN, 0),
                BorderFactory.createLineBorder(Color.BLACK, 0)));
        OpenButton.addActionListener(e->{
         loadFile();
        });
        return OpenButton;
    }

    private JPanel topBar(){
        JPanel topbar = new JPanel();
        topbar.setVisible(true);
        topbar.setSize(500,50);
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
        JMenuItem MenuLoad = new JMenuItem("Open");
        MenuLoad.setName("MenuOpen");
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


    private JMenu MenuSearch(){
        JMenu MenuSearch = new JMenu("search");
        MenuSearch.setName("MenuSearch");
        MenuSearch.add(MenuStartSearch);
        MenuSearch.add(MenuPreviousMatch);
        MenuSearch.add(MenuNextMatch);
        MenuSearch.add(MenuUseRegExp);
        return MenuSearch;
    }

    private JMenuItem MenuStartSearch(){
        JMenuItem MenuStartSearch = new JMenuItem("Start search");
        MenuStartSearch.setName("MenuStartSearch");
        return MenuStartSearch;
    }
    private  JMenuItem MenuPreviousMatch(){
        JMenuItem MenuPreviousMatch = new JMenuItem("Previous search");
        MenuPreviousMatch.setName("MenuPreviousMatch");
        return MenuPreviousMatch;
    }
    private JMenuItem MenuNextMatch(){
        JMenuItem MenuNextMatch = new JMenuItem("Next match");
        MenuNextMatch.setName("MenuNextMatch");
        return MenuNextMatch;
    }

    private JMenuItem MenuUseRegExp(){
        JMenuItem MenuUseRegExp = new JMenuItem("use regular expressions");
        MenuUseRegExp.setName("MenuUseRegExp");
        return MenuUseRegExp;
    }


    private JButton StartSearchButton(){
        JButton StartSearchButton = new JButton(searchIcon);
        StartSearchButton.setName("StartSearchButton");
        StartSearchButton.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.CYAN, 0),
                BorderFactory.createLineBorder(Color.BLACK, 0)));
        StartSearchButton.addActionListener(e ->{
            new SwingWorker(){

                @Override
                protected Object doInBackground() throws Exception {
                    normalSearch();
                    return null;
                }
            }.execute();
        });
        return StartSearchButton;
    }

    private JButton PreviousMatchButton(){
        JButton PreviousMatchButton = new JButton(previousIcon);
        PreviousMatchButton.setName("PreviousMatchButton");
        PreviousMatchButton.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.CYAN, 0),
                BorderFactory.createLineBorder(Color.BLACK, 0)));
        PreviousMatchButton.addActionListener(e -> {
            if(finalIndexIter.hasPrevious()) {
                int i = finalIndexIter.previous();
                int j = initIndexIter.previous();
                TextArea.setCaretPosition(j);
                TextArea.select(i, j);
                TextArea.grabFocus();

            }
        });
        return PreviousMatchButton;
    }

    private JButton NextMatchButton(){
        JButton NextMatchButton = new JButton(nextIcon);
        NextMatchButton.setName("NextMatchButton");
        NextMatchButton.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.CYAN, 0),
                BorderFactory.createLineBorder(Color.BLACK, 0)));
        NextMatchButton.addActionListener(e -> {
            if(finalIndexIter.hasNext()) {
                int i = finalIndexIter.next();
                int j = initIndexIter.next();
                TextArea.setCaretPosition(j);
                TextArea.select(i, j);
                TextArea.grabFocus();

            }
        });
        return NextMatchButton;
    }

    private JCheckBox UseRegExCheckbox(){
        JCheckBox UseRegExCheckbox = new JCheckBox();
        UseRegExCheckbox.setName("UseRegExCheckbox");

        return UseRegExCheckbox;
    }

    private JFileChooser FileChooser(){
        JFileChooser FileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        FileChooser.setName("FileChooser");
        return FileChooser;
    }



    private void saveFile(){
        int saveResult = FileChooser.showSaveDialog(null);
        if (saveResult == JFileChooser.APPROVE_OPTION) {
            // user selects a file
            File newFile = FileChooser.getSelectedFile();
            try(FileWriter writer = new FileWriter(newFile)){
                writer.write(TextArea.getText());
                //System.out.println("and the save content is " +TextArea.getText());
                // System.out.println("filename is " + FilenameField.getText());
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }


    }
    public static String readFileAsString(String fileName) throws IOException {
        return new String(Files.readAllBytes(Paths.get(fileName)));
    }
    private void loadFile(){

        int result = FileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            // user selects a file
            File loadFile = FileChooser.getSelectedFile();
            String content = null;
            try {
                content = readFileAsString(loadFile.getAbsolutePath());
            } catch (IOException e) {
                e.printStackTrace();
            }
            TextArea.setText(content);
        }

    }

    private void normalSearch(){
        String target = SearchField.getText();
        initIndex.clear();
        finalIndex.clear();
        int tempEnd = 0;
        Pattern pattern = Pattern.compile(target);

        String content = TextArea.getText();
        Matcher matcher = pattern.matcher(content);
        while (matcher.find()){
            int firstIndex = content.indexOf(target,tempEnd);
            int lastIndex = firstIndex + target.length();


            tempEnd = lastIndex;
            initIndex.add(firstIndex);
            finalIndex.add(lastIndex);

            System.out.println(firstIndex+ " and " + lastIndex + "and tempend" + tempEnd);
        }
        initIndexIter = initIndex.listIterator();
        finalIndexIter  = finalIndex.listIterator();
        System.out.println(initIndexIter.hasNext());
        if(finalIndexIter.hasNext()) {
            int i = finalIndexIter.next();
            int j = initIndexIter.next();
            TextArea.setCaretPosition(j);

            TextArea.select(i, j);
            TextArea.grabFocus();
        }
        System.out.println(initIndex.toString());
        System.out.println(finalIndex.toString());

    }

}

