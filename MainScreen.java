import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MainScreen extends javax.swing.JFrame {

    // Lists to hold courses
    private ArrayList<String> passedCourses;
    private ArrayList<String> failedCourses;
    private ArrayList<String> coursesToImprove;

    public MainScreen() {
        initComponents();
    }

    // Initialize components
    @SuppressWarnings("unchecked")
    private void initComponents() {
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        passedCoursesList = new javax.swing.JList<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        failedCoursesList = new javax.swing.JList<>();
        jScrollPane3 = new javax.swing.JScrollPane();
        coursesToImproveList = new javax.swing.JList<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Course Information");

        // Initialize course lists
        passedCourses = new ArrayList<>();
        failedCourses = new ArrayList<>();
        coursesToImprove = new ArrayList<>();

        // Sample courses (you can replace these with dynamic data)
        passedCourses.add("Math 101");
        passedCourses.add("Physics 102");
        passedCourses.add("Computer Science 101");

        failedCourses.add("Biology 101");
        failedCourses.add("History 102");

        coursesToImprove.add("Chemistry 101");
        coursesToImprove.add("English 102");

        // Set up JList for passed courses
        passedCoursesList.setListData(passedCourses.toArray(new String[0]));
        jScrollPane1.setViewportView(passedCoursesList);

        // Set up JList for failed courses
        failedCoursesList.setListData(failedCourses.toArray(new String[0]));
        jScrollPane2.setViewportView(failedCoursesList);

        // Set up JList for courses to improve
        coursesToImproveList.setListData(coursesToImprove.toArray(new String[0]));
        jScrollPane3.setViewportView(coursesToImproveList);

        // Use GridLayout to arrange the lists side by side
        jPanel1.setLayout(new GridLayout(1, 3, 10, 10));  // 1 row, 3 columns, with 10px horizontal and vertical gaps
        jPanel1.add(jScrollPane1); // Passed Courses
        jPanel1.add(jScrollPane2); // Failed Courses
        jPanel1.add(jScrollPane3); // Courses to Improve

        // Add panel to the frame
        getContentPane().add(jPanel1, BorderLayout.CENTER);

        // Set a larger window size to approximate 14 inches
        setSize(1400, 800); // Adjusting window size (approx 14.0 inches on screen)
        setVisible(true);  // Make the window visible
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainScreen().setVisible(true);
            }
        });
    }

    // Variables declaration
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JList<String> passedCoursesList;
    private javax.swing.JList<String> failedCoursesList;
    private javax.swing.JList<String> coursesToImproveList;
}
