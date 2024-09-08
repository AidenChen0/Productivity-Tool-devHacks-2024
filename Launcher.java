import javax.swing.*;

public class Launcher {
    public static void main(String[] args){
        // Create a new Runnable object, mainRun, and override the run function
        Runnable mainRun = new Runnable() {
            @Override
            public void run(){
                // Run functionality goes here

                // Run the Main window, defined in class file MainWindow.java
                // Show the main window, again defined in the main window
                MainWindow main = new MainWindow("Main Window");
                
            }
        };

        // Run the runnable function mainRun "later" using SwingUtilities.invokeLater
        SwingUtilities.invokeLater(mainRun);
    }
}
