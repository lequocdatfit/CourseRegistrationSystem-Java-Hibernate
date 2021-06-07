package view;

import model.Hocki;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class InputHocKiFrm extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField txtTenHK;
    private JTextField txtNamHoc;
    private JTextField txtNgayBatDau;
    private JTextField txtNgayKetThuc;

    private HomeGVFrm home;

    public InputHocKiFrm(Frame parent, boolean modal) {
        super(parent, modal);
        setContentPane(contentPane);
        pack();
        setTitle("Thêm học kì");
        setLocationRelativeTo(null);
        setModal(true);
        home = (HomeGVFrm) parent;
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String tenHk = txtTenHK.getText();
                String namHoc = txtNamHoc.getText();
                Date ngayBatDau = null;
                Date ngayKetThuc = null;
                boolean isOk = true;
                try {
                    ngayBatDau = new SimpleDateFormat("dd/MM/yyyy").parse(txtNgayBatDau.getText());
                    ngayKetThuc = new SimpleDateFormat("dd/MM/yyyy").parse(txtNgayKetThuc.getText());
                } catch (ParseException parseException) {
                    isOk = false;
                    parseException.printStackTrace();
                }
                if(isOk) {
                    Hocki h = new Hocki(tenHk, namHoc, ngayBatDau, ngayKetThuc, false);
                    if(home.themHocKi(h)) {
                        JOptionPane.showMessageDialog(contentPane, "Thêm thành công!");
                    } else {
                        JOptionPane.showMessageDialog(contentPane, "Thêm thất bại! (Trùng tên học kì và năm học)");
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
