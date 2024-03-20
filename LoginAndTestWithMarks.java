import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginAndTestWithMarks extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JPanel loginPanel;
    private JPanel testPanel;
    private JLabel questionLabel;
    private JRadioButton optionA;
    private JRadioButton optionB;
    private JRadioButton optionC;
    private JButton nextButton;
    private JButton finishButton;
    private int currentQuestionIndex = 0;
    private int marks = 0;

    // Dummy questions
    private String[] questions = {
            "What is the capital of France?",
            "What is the largest planet in our solar system?",
            "What is the chemical symbol for water?"
    };

    private String[][] options = {
            {"Paris", "London", "Berlin"},
            {"Earth", "Jupiter", "Saturn"},
            {"H2O", "CO2", "O2"}
    };

    private String[] correctAnswers = {"Paris", "Jupiter", "H2O"};

    public LoginAndTestWithMarks() {
        setTitle("Login and Test");
        setSize(300, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Login Panel
        loginPanel = new JPanel(new GridLayout(3, 2));
        usernameField = new JTextField();
        passwordField = new JPasswordField();
        loginButton = new JButton("Login");

        loginPanel.add(new JLabel("Username:"));
        loginPanel.add(usernameField);
        loginPanel.add(new JLabel("Password:"));
        loginPanel.add(passwordField);
        loginPanel.add(new JLabel()); // empty cell for spacing
        loginPanel.add(loginButton);

        // Test Panel
        testPanel = new JPanel(new GridLayout(5, 1));
        questionLabel = new JLabel();
        optionA = new JRadioButton();
        optionB = new JRadioButton();
        optionC = new JRadioButton();
        ButtonGroup optionGroup = new ButtonGroup();
        optionGroup.add(optionA);
        optionGroup.add(optionB);
        optionGroup.add(optionC);
        nextButton = new JButton("Next");
        finishButton = new JButton("Finish");

        testPanel.add(questionLabel);
        testPanel.add(optionA);
        testPanel.add(optionB);
        testPanel.add(optionC);
        testPanel.add(nextButton);

        // Add action listeners
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Perform login authentication
                // If successful, show test panel
                showTestPanel();
            }
        });

        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Process answer
                processAnswer();
                // Show next question or finish test
                if (currentQuestionIndex < questions.length - 1) {
                    currentQuestionIndex++;
                    displayQuestion();
                } else {
                    finishTest();
                }
            }
        });

        finishButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                finishTest();
            }
        });

        // Initially, show login panel
        add(loginPanel);
    }

    private void showTestPanel() {
        displayQuestion();
        setContentPane(testPanel);
        revalidate();
        repaint();
    }

    private void displayQuestion() {
        questionLabel.setText(questions[currentQuestionIndex]);
        optionA.setText(options[currentQuestionIndex][0]);
        optionB.setText(options[currentQuestionIndex][1]);
        optionC.setText(options[currentQuestionIndex][2]);
    }

    private void processAnswer() {
        String selectedAnswer = "";
        if (optionA.isSelected()) {
            selectedAnswer = optionA.getText();
        } else if (optionB.isSelected()) {
            selectedAnswer = optionB.getText();
        } else if (optionC.isSelected()) {
            selectedAnswer = optionC.getText();
        }

        // Compare selected answer with correct answer
        if (selectedAnswer.equals(correctAnswers[currentQuestionIndex])) {
            marks++;
        }
    }

    private void finishTest() {
        JOptionPane.showMessageDialog(this, "Test completed!\nMarks obtained: " + marks + "/" + questions.length);
        // Add logic to end test
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LoginAndTestWithMarks().setVisible(true);
            }
        });
    }
}


