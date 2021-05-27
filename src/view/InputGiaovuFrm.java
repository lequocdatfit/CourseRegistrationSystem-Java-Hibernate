package view;

import model.Giaovu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class InputGiaovuFrm extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField txtMaGV;
    private JTextField txtHoVaTen;
    private JTextField txtNgaySinh;
    private JTextField txtDienThoai;

    private HomeGVFrm home;

    public InputGiaovuFrm(Frame parent, boolean modal) {
        super(parent, modal);
        setContentPane(contentPane);
        setModal(true);
        pack();
        getRootPane().setDefaultButton(buttonOK);
        setLocationRelativeTo(null);
        setTitle("Thêm giáo vụ");

        home = (HomeGVFrm) parent;

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String MaGV = "";
                String HoVaTen = "";
                String MatKhau = "";
                String DienThoai = "";
                Date NgaySinh = null;
                MaGV = txtMaGV.getText();
                HoVaTen = txtHoVaTen.getText();
                MatKhau = "demo@123";
                DienThoai = txtDienThoai.getText();
                boolean isOk = true;
                try {
                    NgaySinh = new SimpleDateFormat("dd/MM/yyyy").parse(txtNgaySinh.getText());

                } catch (ParseException parseException) {
                    JOptionPane.showMessageDialog(contentPane,"Vui lòng nhập đúng định dạng dd/mm/yyyy");
                    parseException.printStackTrace();
                    isOk = false;
                }
                if(isOk) {
                    Giaovu gv = new Giaovu(MaGV, HoVaTen, MatKhau, NgaySinh, DienThoai);
                    if(home.themGiaoVu(gv)) {
                        JOptionPane.showMessageDialog(contentPane, "Thêm thành công!");
                        txtMaGV.setText("");
                        txtHoVaTen.setText("");
                        txtDienThoai.setText("");
                        txtNgaySinh.setText("");
                    } else {
                        JOptionPane.showMessageDialog(contentPane, "Thêm thất bại! (lỗi connection)");
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
