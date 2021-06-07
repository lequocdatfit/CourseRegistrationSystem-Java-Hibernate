package view;

import controller.GiaoVuDAO;
import controller.SinhVienDAO;
import model.Sinhvien;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ChangeSVPasswordFrm extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField txtCurrentPassword;
    private JTextField txtNewPassword;
    private JTextField txtCheckNewPassword;
    private Sinhvien sinhvien;

    public ChangeSVPasswordFrm(Frame parent, boolean modal, Sinhvien s) {
        super(parent, modal);
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        pack();
        setLocationRelativeTo(parent);
        sinhvien = s;

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String currentPassword = txtCurrentPassword.getText();
                String newPassword = txtNewPassword.getText();
                String check = txtCheckNewPassword.getText();
                boolean isOK = true;
                if(!currentPassword.equals(sinhvien.getMatKhau())) {
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
                    sinhvien.setMatKhau(newPassword);
                    if(SinhVienDAO.capNhatSinhVien(sinhvien)) {
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
