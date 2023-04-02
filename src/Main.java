import javax.swing.*;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Name Input");

        // create a text field for user input
        JTextField nameField = new JTextField();
        nameField.setBounds(50, 50, 200, 30);
        frame.add(nameField);

        // create a button to submit the name
        JButton submitButton = new JButton("Submit");
        submitButton.setBounds(100, 100, 100, 30);
        frame.add(submitButton);

        // create a label to display the output
        JLabel outputLabel = new JLabel();
        outputLabel.setBounds(50, 150, 300, 30);
        frame.add(outputLabel);

        // possible responses
        String[] responses = {"is a dickhead", "is ugly", "is stupid", "is gay", "is homophobic", "is cute", "is fucking his/her mother", "is awesome"};

        // add action listener to the submit button
        submitButton.addActionListener(e -> {
            String name = nameField.getText();
            if (!name.isBlank()) {
                String response = name + " " + responses[new Random().nextInt(responses.length)];
                outputLabel.setText(response);
            }
        });

        // set window size and show the frame
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(400, 250);
        frame.setLayout(null);
        frame.setVisible(true);
    }
}