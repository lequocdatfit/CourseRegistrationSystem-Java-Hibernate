package view;

import controller.SinhVienDAO;
import model.Sinhvien;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EditSinhVienInfoFrm extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField txtId;
    private JTextField txtMaSV;
    private JTextField txtHoVaTen;
    private JTextField txtGioiTinh;
    private JTextField txtNamNhapHoc;
    private JTextField txtLopHoc;
    private JComboBox comboBoxGioiTInh;
    private final HomeFrm home;
    private Sinhvien currentSv;

    public EditSinhVienInfoFrm(Frame parent, boolean modal) {
        super(parent, modal);
        setContentPane(contentPane);
        setModal(true);
        setTitle("Cập nhật thông tin");
        getRootPane().setDefaultButton(buttonOK);
        pack();
        setLocationRelativeTo(parent);

        home = (HomeFrm) parent;

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String id = txtId.getText();
                String maSv = txtMaSV.getText();
                String hoVaTen = txtHoVaTen.getText();
                Boolean gioiTinh = comboBoxGioiTInh.getSelectedIndex() == 0;
                Date ngayNhapHoc = null;
                boolean isOK = true;
                try {
                    ngayNhapHoc = new SimpleDateFormat("dd/MM/yyyy").parse(txtNamNhapHoc.getText());
                } catch (ParseException parseException) {
                    isOK = false;
                    JOptionPane.showMessageDialog(contentPane, "Ngày nhập học phải là định dạng dd/mm/yyyy !");
                    parseException.printStackTrace();
                }
                if(isOK) {
                    Sinhvien sv = new Sinhvien(id, maSv, hoVaTen, gioiTinh, ngayNhapHoc, currentSv.getMatKhau(), currentSv.getMaLop());
                    if(home.capNhatThongTinSV(sv)) {
                        JOptionPane.showMessageDialog(contentPane, "Cập nhật tài khoản thành công!");
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(contentPane, "Cập nhật tài khoản thất bại!");
                    }
                }
            }
        });

        String[] gioiTinh = {"Nam", "Nữ"};
        comboBoxGioiTInh.setModel(new DefaultComboBoxModel(gioiTinh));

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

    public void setEditData(Sinhvien sv) {
        currentSv = sv;
        txtId.setText(currentSv.getId());
        txtMaSV.setText(currentSv.getMaSv());
        txtHoVaTen.setText(currentSv.getHoVaTen());
        comboBoxGioiTInh.setSelectedIndex(currentSv.getGioiTinh() ? 0: 1);
        txtNamNhapHoc.setText(new SimpleDateFormat("dd/MM/yyyy").format(currentSv.getNamNhapHoc()));
        txtLopHoc.setText(currentSv.getMaLop());
    }
}
