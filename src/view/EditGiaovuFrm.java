package view;

import model.Giaovu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EditGiaovuFrm extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField txtMaGv;
    private JTextField txtHoVaTen;
    private JTextField txtNgaySinh;
    private JTextField txtDienThoai;

    private HomeGVFrm home;
    private String termPassword;

    public EditGiaovuFrm(Frame parent, boolean modal) {
        super(parent, modal);
        setTitle("Chỉnh sửa giáo vụ");
        setContentPane(contentPane);
        setModal(true);
        pack();
        setLocationRelativeTo(null);
        getRootPane().setDefaultButton(buttonOK);
        home = (HomeGVFrm) parent;
        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String maGv = "";
                String hoVaTen = "";
                String dienThoai = "";
                Date ngaySinh = null;
                maGv = txtMaGv.getText();
                hoVaTen = txtHoVaTen.getText();
                dienThoai = txtDienThoai.getText();
                boolean isOk = true;
                try {
                    ngaySinh = new SimpleDateFormat("dd/MM/yyyy").parse(txtNgaySinh.getText());
                } catch (ParseException parseException) {
                    JOptionPane.showMessageDialog(contentPane, "Vui lòng nhập ngày sinh định dạng dd/mm/yyyy !");
                    isOk = false;
                    parseException.printStackTrace();
                }
                if(isOk) {
                    Giaovu gv = new Giaovu(maGv, hoVaTen, termPassword, ngaySinh, dienThoai);
                    if(home.SuaGiaovu(gv)) {
                        JOptionPane.showMessageDialog(contentPane, "Cập nhật thành công!");
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


    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public void setEditData(Giaovu g) {
        txtMaGv.setText(g.getMaGiaoVu());
        txtHoVaTen.setText(g.getHoVaTen());
        txtNgaySinh.setText(new SimpleDateFormat("dd/MM/yyyy").format(g.getNgaySinh()));
        txtDienThoai.setText(g.getDienThoai());
        this.termPassword = g.getMaGiaoVu();
    }
}
