package view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrm extends JFrame {
    private JPanel mainPanel;
    private JTextField txtUsername;
    private JButton btnSignIn;
    private JButton btnExit;
    private JPasswordField txtPassword;
    private JButton btnChange;
    private boolean isStudent;

    public LoginFrm(String title) {
        super(title);
        isStudent = true;
        this.setContentPane(mainPanel);
        this.setSize(400, 300);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        btnSignIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userText = txtUsername.getText();
                String pwdText = txtPassword.getText();
                if(userText.equals("lequocdat") && pwdText.equals("12345")) {
                    JOptionPane.showMessageDialog(mainPanel,"Đăng nhập thành công!");
                    HomeFrm homeFrm = new HomeFrm("Trang chủ");
                    homeFrm.setVisible(true);
                    setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(mainPanel,"Tên đăng nhập hoặc mật khẩu không đúng!");
                }
            }
        });
        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        btnChange.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isStudent = !isStudent;
                if(isStudent) {
                    btnChange.setText("Bạn là giáo vụ ?");
                    setTitle("Đăng nhập sinh viên");
                } else {
                    btnChange.setText("Bạn là sinh viên ?");
                    setTitle("Đăng nhập giáo vụ");
                }
            }
        });
    }
}
