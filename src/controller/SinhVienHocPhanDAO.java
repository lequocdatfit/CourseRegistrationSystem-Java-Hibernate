package controller;

import antlr.preprocessor.Hierarchy;
import model.Hocphan;
import model.Sinhvien;
import model.SinhvienHocphan;
import model.SinhvienHocphanPK;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateUtil;

import java.sql.Date;
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

    synchronized public static boolean dangKyNhieuHocPhan(List<SinhvienHocphan> ls_svhp) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        boolean isOk = true;
        try {
            transaction = session.beginTransaction();
            for (SinhvienHocphan svh : ls_svhp) {
                Hocphan h = HocPhanDAO.layThongTinHocPhan(svh.getIdHocPhan());
                List<SinhvienHocphan> dsdk = SinhVienHocPhanDAO.layDanhSachSinhVienDangKyHP(h.getId());
                if(dsdk.size() >= h.getSlot()) {
                    isOk = false;
                    continue;
                }
                Query query = session.createSQLQuery("INSERT INTO sinhvien_hocphan (IdSinhVien, IdHocPhan, NgayDangKy) " +
                        "VALUES (:idSinhVien, :idHocPhan, :ngayDangKy)");
                query.setParameter("idSinhVien", svh.getSinhVien().getId());
                query.setParameter("idHocPhan", svh.getIdHocPhan());
                query.setParameter("ngayDangKy", new Date(svh.getNgayDangKy().getTime()));
                query.executeUpdate();
            }
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
        return isOk;
    }

    public static boolean huyDangKyHocPhan(SinhvienHocphan h) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.delete(h);
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
            session.close();
            return false;
        } finally {
            session.close();
        }
        return true;
    }
}
