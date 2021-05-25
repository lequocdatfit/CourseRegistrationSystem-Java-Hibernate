package view;

import javax.swing.*;

public class HomeFrm extends JFrame{
    private JPanel mainPanel;
    private JTable table1;

    public HomeFrm(String title) {
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.setSize(600, 650);
        this.setLocationRelativeTo(null);
    }



}
