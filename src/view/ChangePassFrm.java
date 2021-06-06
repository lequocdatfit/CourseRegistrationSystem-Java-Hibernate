package view;

import controller.GiaoVuDAO;
import model.Giaovu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ChangePassFrm extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JPasswordField txtCurrentPassword;
    private JPasswordField txtNewPassword;
    private JPasswordField txtCheckNewPassword;
    private Giaovu giaovu;

    public ChangePassFrm(Frame parent, boolean modal, Giaovu gv) {
        super(parent, modal);
        setContentPane(contentPane);
        setModal(true);
        setTitle("Đổi mật khẩu");
        getRootPane().setDefaultButton(buttonOK);
        pack();
        setLocationRelativeTo(parent);

        giaovu = gv;
        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String currentPassword = txtCurrentPassword.getText();
                String newPassword = txtNewPassword.getText();
                String check = txtCheckNewPassword.getText();
                boolean isOK = true;
                if(!currentPassword.equals(giaovu.getMatKhau())) {
                    isOK = false;
                    JOptionPane.showMessageDialog(contentPane, "Mật khẩu hiện tại không đúng!");
                } else if(newPassword.length() < 8 || newPassword.contains(" ")) {
                    isOK = false;
                    JOptionPane.showMessageDialog(contentPane, "Mật khẩu phải từ 8 kí tự, không chứa kí tự trống!");
                } else if(!newPassword.equals(check)) {
                    isOK = false;
                    JOptionPane.showMessageDialog(contentPane, "Mật khẩu mới không khớp!");
                }
                if(isOK) {
                    giaovu.setMatKhau(newPassword);
                    if(GiaoVuDAO.capNhatThongTinGiaoVu(giaovu)) {
                        JOptionPane.showMessageDialog(contentPane, "Đổi mật khẩu thành công!");
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(contentPane, "Đổi mật khẩu thất bại!");
                    }
                }
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        // add your code here
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

}
