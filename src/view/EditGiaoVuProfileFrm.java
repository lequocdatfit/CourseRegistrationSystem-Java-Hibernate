package view;

import model.Giaovu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.beans.SimpleBeanInfo;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EditGiaoVuProfileFrm extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField txtMGV;
    private JTextField txtHoVaTen;
    private JTextField txtDienThoai;
    private JTextField txtNgaySinh;
    private HomeGVFrm home;
    private Giaovu giaovu;

    public EditGiaoVuProfileFrm(Frame parent, boolean modal) {
        super(parent, modal);
        setContentPane(contentPane);
        setModal(true);
        setTitle("Cập nhật thông tin");
        getRootPane().setDefaultButton(buttonOK);
        pack();
        setLocationRelativeTo(parent);
        home = (HomeGVFrm) parent;

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String maGiaoVu = txtMGV.getText();
                String hoVaTen = txtHoVaTen.getText();
                String dienThoai = txtDienThoai.getText();
                Date ngaySinh = null;
                boolean isOk = true;
                try {
                    ngaySinh = new SimpleDateFormat("dd/MM/yyyy").parse(txtNgaySinh.getText());
                } catch (ParseException parseException) {
                    isOk = false;
                    JOptionPane.showMessageDialog(contentPane, "Ngày sinh phải có định dạng dd/mm/yyyy !");
                    parseException.printStackTrace();
                }
                if(isOk) {
                    Giaovu gv = new Giaovu(maGiaoVu, hoVaTen, giaovu.getMatKhau(), ngaySinh, dienThoai);
                    if(home.capNhatThongTinGiaoVu(gv)) {
                        JOptionPane.showMessageDialog(contentPane, "Thông tin đã được cập nhật!");
                    } else {
                        JOptionPane.showMessageDialog(contentPane, "Cập nhật thất bại!");
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

    public void setEditData(Giaovu gv) {
        giaovu = gv;
        txtMGV.setText(gv.getMaGiaoVu());
        txtHoVaTen.setText(gv.getHoVaTen());
        txtDienThoai.setText(gv.getDienThoai());
        txtNgaySinh.setText(new SimpleDateFormat("dd/MM/yyyy").format(gv.getNgaySinh()));
    }
}
