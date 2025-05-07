import java.io.*;
import java.util.ArrayList;

public class Model {

    // Store user credentials
    private String savedUsername;
    private String savedPassword;

    // Store course data
    private ArrayList<String> passedCourses;
    private ArrayList<String> failedCourses;
    private ArrayList<String> coursesToImprove;

    public Model() {
        passedCourses = new ArrayList<>();
        failedCourses = new ArrayList<>();
        coursesToImprove = new ArrayList<>();
        loadUserCredentials(); // Load saved credentials if any
        initializeCourses(); // Initialize the courses in each category
    }

    // Initialize courses with 5 each in each category
    private void initializeCourses() {
        // Passed Courses
        passedCourses.add("Math 101");
        passedCourses.add("Physics 101");
        passedCourses.add("Computer Science 101");
        passedCourses.add("History 101");
        passedCourses.add("Chemistry 101");

        // Failed Courses (Courses user needs to retake)
        failedCourses.add("Biology 101");
        failedCourses.add("Statistics 101");
        failedCourses.add("Economics 101");
        failedCourses.add("Engineering 101");
        failedCourses.add("Psychology 101");

        // Courses to Improve (Courses user is allowed to improve)
        coursesToImprove.add("English 101");
        coursesToImprove.add("Sociology 101");
        coursesToImprove.add("Philosophy 101");
        coursesToImprove.add("Music 101");
        coursesToImprove.add("Art 101");
    }

    // Getters for courses
    public ArrayList<String> getPassedCourses() {
        return passedCourses;
    }

    public ArrayList<String> getFailedCourses() {
        return failedCourses;
    }

    public ArrayList<String> getCoursesToImprove() {
        return coursesToImprove;
    }

    // Setters for courses
    public void addPassedCourse(String course) {
        passedCourses.add(course);
    }

    public void addFailedCourse(String course) {
        failedCourses.add(course);
    }

    public void addCourseToImprove(String course) {
        coursesToImprove.add(course);
    }

    // Methods to handle user data
    public void setUserCredentials(String username, String password) {
        this.savedUsername = username;
        this.savedPassword = password;
        saveUserCredentials(); // Save credentials to a file
    }

    public boolean checkLoginCredentials(String username, String password) {
        return username.equals(savedUsername) && password.equals(savedPassword);
    }

    // Save credentials to a file
    private void saveUserCredentials() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("userCredentials.txt"))) {
            writer.write(savedUsername);
            writer.newLine();
            writer.write(savedPassword);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Load credentials from a file
    private void loadUserCredentials() {
        try (BufferedReader reader = new BufferedReader(new FileReader("userCredentials.txt"))) {
            savedUsername = reader.readLine();
            savedPassword = reader.readLine();
        } catch (IOException e) {
            // No credentials file found or file is empty, so just leave the fields empty.
        }
    }
}
