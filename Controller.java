public class Controller {

    private Model model;
    private Login viewLogin;
    private MainScreen viewMainScreen;

    public Controller(Model model, Login viewLogin, MainScreen viewMainScreen) {
        this.model = model;
        this.viewLogin = viewLogin;
        this.viewMainScreen = viewMainScreen;
    }

    // Handle user login
    public void handleLogin(String username, String password) {
        if (model.checkLoginCredentials(username, password)) {
            viewLogin.dispose();  // Close login window
            viewMainScreen.setVisible(true);  // Show MainScreen screen
        } else {
            viewLogin.showError("Invalid Username or Password");
        }
    }

    // Handle user signup
    public void handleSignup(String username, String password) {
        model.setUserCredentials(username, password);
        viewLogin.showSuccess("Signup Successful! Please Login.");
    }

    // Handle adding courses
    public void addPassedCourse(String course) {
        model.addPassedCourse(course);
        viewMainScreen.updateCourseLists();
    }

    public void addFailedCourse(String course) {
        model.addFailedCourse(course);
        viewMainScreen.updateCourseLists();
    }

    public void addCourseToImprove(String course) {
        model.addCourseToImprove(course);
        viewMainScreen.updateCourseLists();
    }
}
