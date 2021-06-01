package view;

import model.Lophoc;
import model.Sinhvien;

import javax.swing.*;
import java.awt.event.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EditSinhVien extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField txtId;
    private JTextField txtMaSV;
    private JTextField txtHoVaTen;
    private JTextField txtGioiTinh;
    private JTextField txtNamNhapHoc;
    private SinhvienLophocFrm home;
    private String password;
    private Lophoc l;

    public EditSinhVien(JDialog parent, boolean modal) {
        super(parent, modal);
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        setTitle("Cập nhật thông tin");
        home = (SinhvienLophocFrm) parent;
        pack();
        setLocationRelativeTo(parent);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String Id = txtId.getText();
                String maSV = txtMaSV.getText();
                String hoVaTen = txtHoVaTen.getText();
                boolean isOK = true;
                Boolean gioiTinh = Boolean.valueOf(txtGioiTinh.getText());
                Date namNhapHoc = null;
                try {
                     namNhapHoc = new SimpleDateFormat("dd/MM/yyyy").parse(txtNamNhapHoc.getText());

                } catch (ParseException parseException) {
                    isOK = false;
                    JOptionPane.showMessageDialog(contentPane, "Vui lòng nhập ngày sinh định dạng dd/mm/yyyy, " +
                            "giới tính (0: nữ, 1: nam)!");
                    parseException.printStackTrace();
                }
                if(isOK) {
                    Sinhvien s = new Sinhvien(Id, maSV, hoVaTen, gioiTinh, namNhapHoc, password, l.getMaLop());
                    if(home.capNhatSinhVien(s)) {
                        JOptionPane.showMessageDialog(contentPane, "Cập nhật thành công!");
                        dispose();
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

    public void setEditData(Sinhvien s, Lophoc l) {
        txtId.setText(s.getId());
        txtMaSV.setText(s.getMaSv());
        txtHoVaTen.setText(s.getHoVaTen());
        txtNamNhapHoc.setText(new SimpleDateFormat("dd/MM/yyyy").format(s.getNamNhapHoc()));
        txtGioiTinh.setText(s.getGioiTinh() ? "1": "0");
        password = s.getMatKhau();
        this.l = l;
    }
}
