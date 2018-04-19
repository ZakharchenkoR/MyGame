import javax.swing.*;

public class MainWindow extends JFrame {

    public MainWindow(){
        setTitle("Game");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(320,345);
        setLocation(500,500);
        add(new EngineGame());
        setVisible(true);
    }

    public static void main(String[]args){
        MainWindow window = new MainWindow();
    }
}
