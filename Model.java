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
    }

    public boolean checkLoginCredentials(String username, String password) {
        return username.equals(savedUsername) && password.equals(savedPassword);
    }
}
