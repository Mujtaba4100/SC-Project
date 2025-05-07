import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MainScreen extends JFrame {

    private Model model; // Model for storing courses
    private ArrayList<String> selectedCourses;
    private JTextArea selectedCoursesArea;

    public MainScreen(Model model) {
        this.model = model;
        initComponents();
    }

    private void initComponents() {
        // Labels for each section
        JLabel passedLabel = new JLabel("Passed Courses");
        JLabel failedLabel = new JLabel("Failed Courses");
        JLabel improveLabel = new JLabel("Courses to Improve");

        // Create DefaultListModel to dynamically update the JList
        DefaultListModel<String> passedModel = new DefaultListModel<>();
        DefaultListModel<String> failedModel = new DefaultListModel<>();
        DefaultListModel<String> improveModel = new DefaultListModel<>();

        // Add courses to the DefaultListModel
        for (String course : model.getPassedCourses()) {
            passedModel.addElement(course);
        }

        for (String course : model.getFailedCourses()) {
            failedModel.addElement(course);
        }

        for (String course : model.getCoursesToImprove()) {
            improveModel.addElement(course);
        }

        // JList for the courses
        JList<String> passedCoursesList = new JList<>(passedModel);
        JList<String> failedCoursesList = new JList<>(failedModel);
        JList<String> improveCoursesList = new JList<>(improveModel);

        // Scroll panes for the lists
        JScrollPane passedScroll = new JScrollPane(passedCoursesList);
        JScrollPane failedScroll = new JScrollPane(failedCoursesList);
        JScrollPane improveScroll = new JScrollPane(improveCoursesList);

        // Button to add courses to the bucket
        JButton addCourseButton = new JButton("Add Selected Course");

        selectedCourses = new ArrayList<>();
        selectedCoursesArea = new JTextArea(5, 20);
        selectedCoursesArea.setEditable(false);
        JScrollPane selectedScroll = new JScrollPane(selectedCoursesArea);

        // Action listener for the add course button
        addCourseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedCourse = passedCoursesList.getSelectedValue();
                if (selectedCourse != null && !selectedCourses.contains(selectedCourse)) {
                    int totalCredits = calculateTotalCredits();
                    if (totalCredits + 3 > 18) {
                        JOptionPane.showMessageDialog(MainScreen.this, "You cannot select more than 18 credit hours!", "Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        selectedCourses.add(selectedCourse);
                        updateSelectedCoursesArea();
                    }
                }
            }
        });

        // Layout setup using GridBagLayout
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Adding components to the layout
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(passedLabel, gbc);
        gbc.gridx = 1;
        add(passedScroll, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(failedLabel, gbc);
        gbc.gridx = 1;
        add(failedScroll, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(improveLabel, gbc);
        gbc.gridx = 1;
        add(improveScroll, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        add(addCourseButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        add(selectedScroll, gbc);

        // Window setup
        setTitle("Course Selection");
        setSize(800, 600);
        setLocationRelativeTo(null); // Center on screen
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    // Method to calculate total credit hours
    private int calculateTotalCredits() {
        return selectedCourses.size() * 3; // Each course is 3 credit hours (for simplicity)
    }

    // Update the selected courses area
    private void updateSelectedCoursesArea() {
        StringBuilder sb = new StringBuilder();
        for (String course : selectedCourses) {
            sb.append(course).append("\n");
        }
        selectedCoursesArea.setText(sb.toString());
    }

    public static void main(String[] args) {
        Model model = new Model();
        MainScreen viewMainScreen = new MainScreen(model);
        viewMainScreen.setVisible(true);
    }
}
