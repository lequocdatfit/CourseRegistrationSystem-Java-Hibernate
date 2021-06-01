package view;

import model.Lophoc;
import model.Sinhvien;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class InputSinhVien extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField txtId;
    private JTextField txtMSV;
    private JTextField txtHoVaTen;
    private JTextField txtGioiTinh;
    private JTextField txtNamHoc;
    private SinhvienLophocFrm home;
    private Lophoc l;
    public InputSinhVien(JDialog parent) {
        super(parent);
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        pack();
        setLocationRelativeTo(parent);
        home = (SinhvienLophocFrm) parent;

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String Id = txtId.getText();
                String MaSV = txtMSV.getText();
                String HoVaTen = txtHoVaTen.getText();
                Date NamNhapHoc = null;
                Boolean GioiTinh = null;
                boolean isOk = true;
                try {
                    GioiTinh = Boolean.valueOf(txtGioiTinh.getText());
                    NamNhapHoc = new SimpleDateFormat("dd/MM/yyyy").parse(txtNamHoc.getText());
                } catch (ParseException parseException) {
                    isOk = false;
                    JOptionPane.showMessageDialog(contentPane, "Vui lòng nhập đúng định dạng!");
                    parseException.printStackTrace();
                }
                if(isOk) {
                    Sinhvien sv = new Sinhvien(Id, MaSV, HoVaTen, GioiTinh, NamNhapHoc, "demo@123", l.getMaLop());
                    if(home.themSinhVien(sv)) {
                        JOptionPane.showMessageDialog(contentPane, "Thêm sinh viên thành công!");
                    } else {
                        JOptionPane.showMessageDialog(contentPane, "Thêm sinh viên thất bại!");
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

    public void setLopHoc(Lophoc x) {
        l = x;
    }

}
