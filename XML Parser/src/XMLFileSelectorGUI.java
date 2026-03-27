import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class XMLFileSelectorGUI extends JFrame {
    private File selectedFile;
    private JLabel selectedFileLabel;
    private JLabel headerLabel;
    private JLabel outputLabel;
    private JButton selectButton;
    private JButton submitButton;
    private JTextField outputField;
    private JCheckBox checkBox1;
    private JCheckBox checkBox2;
    private JCheckBox checkBox3;
    private JCheckBox checkBox4;
    private JCheckBox checkBox5;
    private JCheckBox checkBox6;
    private JCheckBox checkBox7;
    private JCheckBox checkBox8;
    private JCheckBox checkBox9;
    private JCheckBox checkBox10;

    public XMLFileSelectorGUI() {
        setTitle("XML Parser and Extraction Tool");
        setSize(550, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        headerLabel = new JLabel("Select an XML file and choose at least one option:");
        headerLabel.setFont(new Font("Arial", Font.BOLD, 16));
        selectedFileLabel = new JLabel("No file selected.");
        selectButton = new JButton("Select XML File");
        outputLabel = new JLabel("Enter desired text file name:");
        outputField = new JTextField("output");
        submitButton = new JButton("Submit");

        selectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileFilter(new FileNameExtensionFilter("XML Files", "xml"));
                int result = fileChooser.showOpenDialog(XMLFileSelectorGUI.this);
                if (result == JFileChooser.APPROVE_OPTION) {
                    selectedFile = fileChooser.getSelectedFile();
                    selectedFileLabel.setText("Selected file: " + selectedFile.getAbsolutePath());
                } else {
                    selectedFileLabel.setText("No file selected.");
                }
            }
        });

        submitButton.setEnabled(false);

        ActionListener checkBoxListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Enable the submit button if at least one checkbox is selected
                submitButton.setEnabled(checkBox1.isSelected() || checkBox2.isSelected() || checkBox3.isSelected() 
                || checkBox4.isSelected() || checkBox5.isSelected() || checkBox6.isSelected() || checkBox7.isSelected()
                || checkBox8.isSelected() || checkBox9.isSelected()|| checkBox10.isSelected());
            }
        };
        checkBox1 = new JCheckBox("Business Rules");
        checkBox2 = new JCheckBox("Business Rules Plus");
        checkBox3 = new JCheckBox("Business Actions");
        checkBox4 = new JCheckBox("Worker Rules");
        checkBox5 = new JCheckBox("Chat Spec Name");
        checkBox6 = new JCheckBox("Custom Events");
        checkBox7 = new JCheckBox("Content Groups");
        checkBox8 = new JCheckBox("Javascript Functions");
        checkBox9 = new JCheckBox("Variables");
        checkBox10 = new JCheckBox("Rich Media Prechats");

        // add listeners to all checkboxes
        checkBox1.addActionListener(checkBoxListener);
        checkBox2.addActionListener(checkBoxListener);
        checkBox3.addActionListener(checkBoxListener);
        checkBox4.addActionListener(checkBoxListener);
        checkBox5.addActionListener(checkBoxListener);
        checkBox6.addActionListener(checkBoxListener);
        checkBox7.addActionListener(checkBoxListener);
        checkBox8.addActionListener(checkBoxListener);
        checkBox9.addActionListener(checkBoxListener);
        checkBox10.addActionListener(checkBoxListener);
        
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent click) {
                if (selectedFile != null) {
                    try {
                    XMLParsing.generateOutputFile(outputField.getText());
                    FileWriter myWriter = new FileWriter(outputField.getText() + ".txt");
                    // Code to handle the selected XML file.
                    XMLParsing.getFile(selectedFile);
                    if (checkBox1.isSelected()) {
                        XMLParsing.getBuRules(myWriter);
                    }
                    if (checkBox2.isSelected()) {
                        XMLParsing.getBuRulesPlus(myWriter);
                    }
                    if (checkBox3.isSelected()) {
                        XMLParsing.getBuAction(myWriter);
                    }
                    if (checkBox4.isSelected()) {
                        XMLParsing.getWorkerRules(myWriter);
                    }         
                    if (checkBox5.isSelected()) {
                        XMLParsing.getChatSpecName(myWriter);
                    }         
                    if (checkBox6.isSelected()) {
                        XMLParsing.getCustomEvents(myWriter);
                    }         
                    if (checkBox7.isSelected()) {
                        XMLParsing.getCG(myWriter);
                    }         
                    if (checkBox8.isSelected()) {
                        XMLParsing.getJS(myWriter);
                    }         
                    if (checkBox9.isSelected()) {
                        XMLParsing.getVars(myWriter);
                    }         
                    if (checkBox10.isSelected()) {
                        XMLParsing.getPreRichMedia(myWriter);
                    }         
                    myWriter.close(); // Do not remove
                } catch (IOException e) {
                    e.printStackTrace();
                }
                JOptionPane.showMessageDialog(XMLFileSelectorGUI.this,
                    outputField.getText()+".txt successfully generated",
                    "Submited", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(XMLFileSelectorGUI.this,
                    "Please select an XML file first.",
                    "Failed Action", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        // GUI Display
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 20, 5, 20);
        
        gbc.gridwidth = 2;
        add(headerLabel, gbc);
        gbc.anchor = GridBagConstraints.CENTER;
        add(selectButton, gbc);
        add(selectedFileLabel, gbc);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridwidth = 1;
        add(checkBox1, gbc);
        add(checkBox2, gbc);
        add(checkBox3, gbc);
        add(checkBox4, gbc);
        add(checkBox5, gbc);

        // Move to the next column for the new row
        gbc.gridx = 1;
        gbc.gridy = 3;
        add(checkBox6, gbc);
        gbc.gridy = 4;
        add(checkBox7, gbc);
        gbc.gridy = 5;
        add(checkBox8, gbc);
        gbc.gridy = 6;
        add(checkBox9, gbc);
        gbc.gridy = 7;
        add(checkBox10, gbc);

        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 20, 10, 20);
        gbc.gridx = 0; // Set the grid position back to the first column
        gbc.gridy = GridBagConstraints.RELATIVE;
        add(outputLabel, gbc);
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(outputField, gbc);
        gbc.fill = GridBagConstraints.NONE;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.gridx = 0; // Make the submit button span two columns
        gbc.gridwidth = 2;
        add(submitButton, gbc);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
