import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CourseSelectionPage extends JFrame {
    private ArrayList<Course> courses;
    private DefaultListModel<String> model;
    private JList<String> courseList;
    private JTextArea selectedCoursesArea;
    private int totalCreditHours = 0;

    public CourseSelectionPage() {
        // Setting up the course selection page frame
        setTitle("Course Selection");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Sample courses (course name and credit hours)
        courses = new ArrayList<>();
        courses.add(new Course("Math 101", 3, false)); // Not failed
        courses.add(new Course("Physics 101", 4, true)); // Failed course
        courses.add(new Course("Computer Science 101", 3, false)); // Not failed
        courses.add(new Course("Chemistry 101", 3, true)); // Failed course

        // List model for the courses
        model = new DefaultListModel<>();
        for (Course course : courses) {
            model.addElement(course.getName());
        }

        courseList = new JList<>(model);
        courseList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        JScrollPane listScroll = new JScrollPane(courseList);

        selectedCoursesArea = new JTextArea(5, 20);
        selectedCoursesArea.setEditable(false);
        JScrollPane selectedScroll = new JScrollPane(selectedCoursesArea);

        JButton submitButton = new JButton("Submit Courses");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedCreditHours = 0;
                StringBuilder selectedCourses = new StringBuilder("Selected Courses:\n");

                for (int i : courseList.getSelectedIndices()) {
                    Course course = courses.get(i);
                    selectedCreditHours += course.getCreditHours();
                    selectedCourses.append(course.getName()).append(" - ").append(course.getCreditHours()).append(" credit hours\n");
                }

                if (selectedCreditHours > 18) {
                    JOptionPane.showMessageDialog(CourseSelectionPage.this, "You cannot select more than 18 credit hours!", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    totalCreditHours = selectedCreditHours;
                    selectedCoursesArea.setText(selectedCourses.toString());
                }
            }
        });

        // Layout setup
        setLayout(new BorderLayout());
        JPanel panel = new JPanel(new GridLayout(1, 2));
        panel.add(listScroll);
        panel.add(selectedScroll);
        add(panel, BorderLayout.CENTER);
        add(submitButton, BorderLayout.SOUTH);

        // Highlight failed courses in red
        highlightFailedCourses();
    }

    private void highlightFailedCourses() {
        for (int i = 0; i < courses.size(); i++) {
            Course course = courses.get(i);
            if (course.isFailed()) {
                courseList.setSelectionBackground(Color.RED);
            } else {
                courseList.setSelectionBackground(Color.WHITE);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new CourseSelectionPage().setVisible(true);
        });
    }

    // Course class to hold course data
    private class Course {
        private String name;
        private int creditHours;
        private boolean failed;

        public Course(String name, int creditHours, boolean failed) {
            this.name = name;
            this.creditHours = creditHours;
            this.failed = failed;
        }

        public String getName() {
            return name;
        }

        public int getCreditHours() {
            return creditHours;
        }

        public boolean isFailed() {
            return failed;
        }
    }
}
