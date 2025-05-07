public class Controller {

    private Model model;
    private Login viewLogin;
    private MainScreen viewMainScreen;  // Move this to controller

    public Controller(Model model, Login viewLogin) {
        this.model = model;
        this.viewLogin = viewLogin;
    }

    // Handle user login
    public void handleLogin(String username, String password) {
        if (model.checkLoginCredentials(username, password)) {
            viewLogin.dispose();  // Close Login window after successful login

            // Instantiate MainScreen (MainScreen) only after successful login
            viewMainScreen = new MainScreen();
            viewMainScreen.setVisible(true);  // Show MainScreen screen after login
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
        viewMainScreen.updateCourseLists();  // Update the view with new course lists
    }

    public void addFailedCourse(String course) {
        model.addFailedCourse(course);
        viewMainScreen.updateCourseLists();  // Update the view with new course lists
    }

    public void addCourseToImprove(String course) {
        model.addCourseToImprove(course);
        viewMainScreen.updateCourseLists();  // Update the view with new course lists
    }
}
