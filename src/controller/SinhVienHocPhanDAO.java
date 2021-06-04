package controller;

import model.Hocphan;
import model.Sinhvien;
import model.SinhvienHocphan;
import model.SinhvienHocphanPK;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import utils.HibernateUtil;

import java.util.List;


public class SinhVienHocPhanDAO {
    public static List<SinhvienHocphan> layDanhSachSinhVienDangKyHP(String idHocPhan) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<SinhvienHocphan> ls = null;
        try {
            String hql = "from SinhvienHocphan s where s.idHocPhan = :idHocPhan";
            Query query = session.createQuery(hql);
            query.setParameter("idHocPhan", idHocPhan);
            ls = query.list();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return ls;
    }

    public static List<SinhvienHocphan> layDanhSachHocPhanSVDangKy(String idSinhVien) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<SinhvienHocphan> ls = null;
        try {
            String hql = "from SinhvienHocphan s where s.idSinhVien = :idSinhVien";
            Query query = session.createQuery(hql);
            query.setParameter("idSinhVien", idSinhVien);
            ls = query.list();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return ls;
    }
}
