package view;

import model.Monhoc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class InputMonHocFrm extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField txtMaMh;
    private JTextField txtTenMh;
    private JTextField txtTinChi;

    private HomeGVFrm home;

    public InputMonHocFrm(Frame parent, boolean modal) {
        super(parent, modal);
        setContentPane(contentPane);
        setModal(true);
        pack();
        setLocationRelativeTo(parent);

        home = (HomeGVFrm) parent;
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String MaMh = "";
                String TenMh = "";
                int SoTinChi = 0;
                MaMh = txtMaMh.getText();
                TenMh = txtTenMh.getText();
                boolean isOk = true;
                try {
                    SoTinChi = Integer.parseInt(txtTinChi.getText());
                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(contentPane, "Số tín chỉ phải là một số tự nhiên!");
                    isOk =false;
                    exception.printStackTrace();
                }
                if(isOk) {
                     Monhoc m = new Monhoc(MaMh, TenMh, SoTinChi);
                     if(home.themMonHoc(m)) {
                         JOptionPane.showMessageDialog(contentPane, "Thêm môn học thành công!");
                     } else {
                         JOptionPane.showMessageDialog(contentPane, "Thêm môn học thất bại! " +
                                 "(Mã môn học không được trùng)");
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


    private void onCancel() {
        // add your code here if necessary
        dispose();
    }


}
