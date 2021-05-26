package view;

import controller.GiaoVuDAO;
import controller.SinhVienDAO;
import model.Giaovu;
import model.Sinhvien;

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
    private JLabel usernameLb;
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
                Thread loginThread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String userText = txtUsername.getText();
                        String pwdText = txtPassword.getText();
                        if(isStudent) {
                            Sinhvien sv = SinhVienDAO.LayThongTinSinhVienBangMa(userText);
                            if(sv == null) {
                                JOptionPane.showMessageDialog(mainPanel, "Tên đăng nhập hoặc mật khẩu không đúng!");
                            } else {
                                if(sv.getMatKhau().equals(pwdText)) {
                                    JOptionPane.showMessageDialog(mainPanel, "Đăng nhập thành công!");
                                    HomeFrm homeFrm = new HomeFrm("Trang chủ");
                                    homeFrm.setVisible(true);
                                    setVisible(false);
                                } else {
                                    JOptionPane.showMessageDialog(mainPanel, "Tên đăng nhập hoặc mật khẩu không đúng!");
                                }
                            }
                        } else {
                            Giaovu gv = GiaoVuDAO.LayThongTinGiaoVu(userText);
                            if(gv == null) {
                                JOptionPane.showMessageDialog(mainPanel, "Tên đăng nhập hoặc mật khẩu không đúng!");
                            } else {
                                if(gv.getMatKhau().equals(pwdText)) {
                                    JOptionPane.showMessageDialog(mainPanel, "Đăng nhập thành công!");
                                    HomeGVFrm homeGVFrm = new HomeGVFrm("Trang chủ");
                                    homeGVFrm.setVisible(true);
                                    setVisible(false);
                                }
                            }
                        }
                    }
                });
                loginThread.start();
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
                    usernameLb.setText("Mã sinh viên");
                } else {
                    btnChange.setText("Bạn là sinh viên ?");
                    setTitle("Đăng nhập giáo vụ");
                    usernameLb.setText("Mã giáo vụ");
                }
            }
        });
    }
}
