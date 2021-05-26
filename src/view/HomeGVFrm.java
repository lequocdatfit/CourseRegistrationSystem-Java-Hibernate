package view;

import javax.swing.*;

public class HomeGVFrm extends JFrame{
    private JPanel mainPanel;
    private JTabbedPane tabbedPane1;
    private JTable table1;

    public HomeGVFrm(String title) {
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.setSize(600, 650);
        this.setLocationRelativeTo(null);
    }
}
