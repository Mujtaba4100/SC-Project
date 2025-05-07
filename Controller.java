public class Controller {

    private Model model;
    private Login viewLogin;
    private MainScreen viewMainScreen;  // Main screen view
    private Signup viewSignup;  // Signup screen view

    public Controller(Model model, Login viewLogin) {
        this.model = model;
        this.viewLogin = viewLogin;
    }

    // Handle user login
    public void handleLogin(String username, String password) {
        if (model.checkLoginCredentials(username, password)) {
            viewLogin.dispose();  // Close Login window after successful login

            // Instantiate MainScreen (MainScreen) only after successful login
            viewMainScreen = new MainScreen(model);
            viewMainScreen.setVisible(true);  // Show MainScreen screen after login
        } else {
            viewLogin.showError("Invalid Username or Password");
        }
    }

    // Handle user signup
    public void handleSignup(String username, String password) {
        model.setUserCredentials(username, password); // Store user credentials
        viewSignup.dispose(); // Close the signup window
        viewLogin.showSuccess("Signup Successful! Please Login.");
        viewLogin.setVisible(true); // Show login screen after signup
    }

    // Open signup screen
    public void openSignupScreen() {
        // Open the Signup screen
        viewSignup = new Signup();
        viewSignup.setController(this);
        viewSignup.setVisible(true); // Show Signup screen
        viewLogin.setVisible(false); // Hide Login screen
    }
}
