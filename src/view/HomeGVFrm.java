package view;

import controller.GiaoVuDAO;
import model.Giaovu;
import model.Sinhvien;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.List;

public class HomeGVFrm extends JFrame{
    private JPanel mainPanel;
    private JTabbedPane tabbedPane1;
    private JTable giaovutbl;
    private JButton button1;
    private JButton thêmGiáoVụButton;
    private JButton sửaGiáoVụButton;
    private JButton xóaGiáoVụButton;
    private DefaultTableModel giaovuModel;

    private List<Sinhvien> ls_sinhvien;
    private List<Giaovu> ls_giaovu;

    public HomeGVFrm(String title) {
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 650);
        this.setContentPane(mainPanel);
        this.setLocationRelativeTo(null);

        ls_giaovu = GiaoVuDAO.LayDanhSachGiaoVu();

        giaovuModel = (DefaultTableModel) giaovutbl.getModel();
        giaovuModel.setColumnIdentifiers(new Object[] {
                "STT", "Mã giáo vụ", "Họ và tên", "Ngày sinh", "Điện thoại"
        });

        this.updateGiaovuTable();



        thêmGiáoVụButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InputGiaovuFrm input = new InputGiaovuFrm(HomeGVFrm.this, rootPaneCheckingEnabled);
                input.setVisible(true);
            }
        });
    }

    public boolean themGiaoVu(Giaovu gv) {
        if(GiaoVuDAO.themGiaoVu(gv)) {
            ls_giaovu.add(gv);
            giaovuModel.setRowCount(0);
            updateGiaovuTable();
            return true;
        } else {
            return false;
        }
    }

    public void updateGiaovuTable() {
        if(!ls_giaovu.isEmpty()) {
            for (int i=0; i<ls_giaovu.size(); i++) {
                Giaovu g = ls_giaovu.get(i);
                giaovuModel.addRow(new Object[] {
                        i+1, g.getMaGiaoVu(), g.getHoVaTen(),
                        new SimpleDateFormat("dd/MM/yyyy").format(g.getNgaySinh()), g.getDienThoai()
                });
            }
        }
    }
}
