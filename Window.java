import javax.swing.JButton;
import javax.swing.JFrame;

public abstract class Window extends JFrame{
    private int windowDimensions = 600; // In pixels
    private JFrame window; // The main window, the "frame" for the program
    
    public Window(){
        initialize();
    }
    public Window(String label){
        initialize();
        window.setTitle(label);
    }

    public void initialize(){
        window = new JFrame();
        window.setTitle("Window");
        window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        window.setSize(windowDimensions, windowDimensions);
        window.setResizable(false);
        window.setLocationRelativeTo(null);
    }

    public void show(){window.setVisible(true);}
    public void hide(){window.setVisible(false);}

    public JFrame getWindow(){return window;}

    public int getDimensions(){return windowDimensions;}
    public void setDimensions(int dim){this.windowDimensions = dim;}
}
