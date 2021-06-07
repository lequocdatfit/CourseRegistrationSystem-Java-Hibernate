package view;

import model.Hocki;
import model.Kidangkihocphan;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class InputKiDangKyFrm extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField txtID;
    private JTextField txtTenHK;
    private JTextField txtNamHoc;
    private JTextField txtNgayBatDau;
    private JTextField txtNgayKetThuc;
    private HomeGVFrm home;

    public InputKiDangKyFrm(Frame parent, boolean modal) {
        super(parent, modal);
        setContentPane(contentPane);
        setModal(true);
        setTitle("Thêm kì đăng ký");
        getRootPane().setDefaultButton(buttonOK);
        pack();
        setLocationRelativeTo(parent);
        home = (HomeGVFrm) parent;


        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String id = txtID.getText();
                String tenHocKi = txtTenHK.getText();
                String namHoc = txtNamHoc.getText();
                Date ngayBatDau = null;
                Date ngayKetThuc = null;
                boolean isOk = true;
                try {
                    ngayBatDau = new SimpleDateFormat("dd/MM/yyyy").parse(txtNgayBatDau.getText());
                    ngayKetThuc = new SimpleDateFormat("dd/MM/yyyy").parse(txtNgayKetThuc.getText());
                } catch (ParseException parseException) {
                    isOk = false;
                    JOptionPane.showMessageDialog(contentPane, "Vui lòng nhập định dạng dd/mm/yyyy !");
                    parseException.printStackTrace();
                }
                if(isOk) {
                    Kidangkihocphan k = new Kidangkihocphan(id, tenHocKi, namHoc, ngayBatDau, ngayKetThuc);
                    if(home.themKiDangKyHocPhan(k)) {
                        JOptionPane.showMessageDialog(contentPane, "Thêm kì đăng ký học phần thành công!");
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(contentPane, "Thêm kì đăng ký học phần thất bại! " +
                                "(Id không được trùng)");
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

    public void setData(Hocki h) {
        txtTenHK.setText(h.getTenHk());
        txtNamHoc.setText(h.getNamHoc());
    }
}
