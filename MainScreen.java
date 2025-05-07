import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainScreen extends javax.swing.JFrame {

    private Model model; // Model for storing courses
    private JTextField passedCourseField, failedCourseField, improveCourseField;

    public MainScreen(Model model) {
        this.model = model;
        initComponents();
    }

    private void initComponents() {
        // Labels for each section
        JLabel passedLabel = new JLabel("Passed Courses");
        JLabel failedLabel = new JLabel("Failed Courses");
        JLabel improveLabel = new JLabel("Courses to Improve");

        // Text Fields to add courses
        passedCourseField = new JTextField(20);
        failedCourseField = new JTextField(20);
        improveCourseField = new JTextField(20);

        // Buttons to add courses
        JButton addPassedCourseButton = new JButton("Add Passed Course");
        JButton addFailedCourseButton = new JButton("Add Failed Course");
        JButton addImproveCourseButton = new JButton("Add Course to Improve");

        // Course Lists (JList)
        JList<String> passedCoursesList = new JList<>(model.getPassedCourses().toArray(new String[0]));
        JList<String> failedCoursesList = new JList<>(model.getFailedCourses().toArray(new String[0]));
        JList<String> improveCoursesList = new JList<>(model.getCoursesToImprove().toArray(new String[0]));

        JScrollPane passedScroll = new JScrollPane(passedCoursesList);
        JScrollPane failedScroll = new JScrollPane(failedCoursesList);
        JScrollPane improveScroll = new JScrollPane(improveCoursesList);

        // Setting up fonts and colors
        Font font = new Font("Arial", Font.PLAIN, 16);
        passedCourseField.setFont(font);
        failedCourseField.setFont(font);
        improveCourseField.setFont(font);

        // Action Listeners for Buttons
        addPassedCourseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String course = passedCourseField.getText().trim();
                if (!course.isEmpty()) {
                    model.addPassedCourse(course);
                    passedCoursesList.setListData(model.getPassedCourses().toArray(new String[0]));
                    passedCourseField.setText("");
                } else {
                    JOptionPane.showMessageDialog(MainScreen.this, "Course name cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        addFailedCourseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String course = failedCourseField.getText().trim();
                if (!course.isEmpty()) {
                    model.addFailedCourse(course);
                    failedCoursesList.setListData(model.getFailedCourses().toArray(new String[0]));
                    failedCourseField.setText("");
                } else {
                    JOptionPane.showMessageDialog(MainScreen.this, "Course name cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        addImproveCourseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String course = improveCourseField.getText().trim();
                if (!course.isEmpty()) {
                    model.addCourseToImprove(course);
                    improveCoursesList.setListData(model.getCoursesToImprove().toArray(new String[0]));
                    improveCourseField.setText("");
                } else {
                    JOptionPane.showMessageDialog(MainScreen.this, "Course name cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Layout setup using GridBagLayout for better flexibility
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Passed Courses Section
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(passedLabel, gbc);

        gbc.gridx = 1;
        add(passedScroll, gbc);

        gbc.gridx = 2;
        add(passedCourseField, gbc);

        gbc.gridx = 3;
        add(addPassedCourseButton, gbc);

        // Failed Courses Section
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(failedLabel, gbc);

        gbc.gridx = 1;
        add(failedScroll, gbc);

        gbc.gridx = 2;
        add(failedCourseField, gbc);

        gbc.gridx = 3;
        add(addFailedCourseButton, gbc);

        // Courses to Improve Section
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(improveLabel, gbc);

        gbc.gridx = 1;
        add(improveScroll, gbc);

        gbc.gridx = 2;
        add(improveCourseField, gbc);

        gbc.gridx = 3;
        add(addImproveCourseButton, gbc);

        // Window setup
        setTitle("Manage Your Courses");
        setSize(800, 600);
        setLocationRelativeTo(null); // Center on screen
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        // Initialize model
        Model model = new Model();

        // Instantiate MainScreen and pass the model
        MainScreen viewMainScreen = new MainScreen(model);
        viewMainScreen.setVisible(true);
    }
}
