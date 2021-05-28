package view;

import controller.MonhocDAO;
import model.Monhoc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class EditMonhocFrm extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField txtMaMh;
    private JTextField txtTenMh;
    private JTextField txtTinChi;

    private HomeGVFrm home;

    public EditMonhocFrm(Frame parent, boolean modal) {
        super(parent, modal);
        setContentPane(contentPane);
        pack();
        setLocationRelativeTo(rootPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        home = (HomeGVFrm) parent;

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String maMh = "";
                String tenMh = "";
                int soTinChi = 0;
                maMh = txtMaMh.getText();
                tenMh = txtTenMh.getText();
                boolean isOk = true;
                try {
                    soTinChi = Integer.parseInt(txtTinChi.getText());
                } catch (Exception ex) {
                    isOk = false;
                    JOptionPane.showMessageDialog(contentPane, "Số tín chỉ là một số tự nhiên!");
                    ex.printStackTrace();
                }
                if(isOk) {
                    Monhoc m = new Monhoc(maMh, tenMh, soTinChi);
                    if(home.suaMonHoc(m)) {
                        JOptionPane.showMessageDialog(contentPane,"Cập nhật thành công!");
                    } else {
                        JOptionPane.showMessageDialog(contentPane, "Cập nhật thất bại!");
                    }
                }
                dispose();
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

    public void setEditData(Monhoc m) {
        txtMaMh.setText(m.getMaMh());
        txtTenMh.setText(m.getTenMh());
        txtTinChi.setText(String.valueOf(m.getSoTinChi()));
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }
}
