package view;

import controller.GiaoVuDAO;
import model.Giaovu;
import model.Sinhvien;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
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
    private int selectedIndex;

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

        tabbedPane1.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                Thread updateData = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("State change!");
                        int index = tabbedPane1.getSelectedIndex();
                        if(index == 0) {
                            ls_giaovu.clear();
                            ls_giaovu = GiaoVuDAO.LayDanhSachGiaoVu();
                            giaovuModel.setRowCount(0);
                            updateGiaovuTable();
                        }
                    }
                });
                updateData.start();
            }
        });

        thêmGiáoVụButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InputGiaovuFrm input = new InputGiaovuFrm(HomeGVFrm.this, rootPaneCheckingEnabled);
                input.setVisible(true);
            }
        });


        sửaGiáoVụButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedIndex = giaovutbl.getSelectedRow();
                if(ls_giaovu.size() == 0) {
                    JOptionPane.showMessageDialog(mainPanel, "Không có giáo vụ nào để sửa");
                } else if (selectedIndex == -1) {
                    JOptionPane.showMessageDialog(mainPanel, "Hãy chọn một giáo vụ trên bảng rồi chọn Sửa");
                } else {
                    EditGiaovuFrm edit = new EditGiaovuFrm(HomeGVFrm.this, rootPaneCheckingEnabled);
                    edit.setEditData(ls_giaovu.get(selectedIndex));
                    edit.setVisible(true);
                }
            }
        });
        xóaGiáoVụButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedIndex = giaovutbl.getSelectedRow();
                if(ls_giaovu.size() == 0) {
                    JOptionPane.showMessageDialog(rootPane, "Không có giáo vụ nào để xóa !");
                } else if(selectedIndex == -1) {
                    JOptionPane.showMessageDialog(rootPane, "Hãy chọn giáo vụ trên bảng rồi chọn xóa !");
                } else {
                    int output = JOptionPane.showConfirmDialog(rootPane, "Bạn có muốn xóa giáo vụ này không ?",
                            "Cảnh báo", JOptionPane.YES_OPTION, JOptionPane.NO_OPTION);
                    if(output == JOptionPane.YES_OPTION) {
                        if(HomeGVFrm.this.xoaGiaoVu(ls_giaovu.get(selectedIndex))) {
                            JOptionPane.showMessageDialog(rootPane, "Xóa thành công!");
                        } else {
                            JOptionPane.showMessageDialog(rootPane, "Xóa thất bại!");
                        }
                    } else if(output == JOptionPane.NO_OPTION) {
                        return;
                    }
                }
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

    public boolean SuaGiaovu(Giaovu gv) {
        if(GiaoVuDAO.suaThongTinGiaoVu(gv)) {
            ls_giaovu.clear();
            ls_giaovu = GiaoVuDAO.LayDanhSachGiaoVu();
            giaovuModel.setRowCount(0);
            updateGiaovuTable();
            return true;
        }
        return false;
    }

    public boolean xoaGiaoVu(Giaovu gv) {
        if(GiaoVuDAO.xoaGiaoVu(gv)) {
            ls_giaovu.clear();
            ls_giaovu = GiaoVuDAO.LayDanhSachGiaoVu();
            giaovuModel.setRowCount(0);
            updateGiaovuTable();
            return true;
        }
        return false;
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
