package controller;

import model.Sinhvien;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateUtil;

import java.util.List;

public class SinhVienDAO {
    public static List<Sinhvien> layDanhSachSinhVien() {
        List<Sinhvien> ls = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            final String hql = "from Sinhvien v";
            Query query = session.createQuery(hql);
            ls = query.list();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return ls;
    }
    public static Sinhvien LayThongTinSinhVienBangMa(String maSinhVien) {
        Sinhvien sv = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            final String hql = "SELECT sv from Sinhvien sv WHERE sv.maSv = :maSinhVien";
            Query query = session.createQuery(hql);
            query.setParameter("maSinhVien", maSinhVien);
            List ls= query.list();
            if(!ls.isEmpty()) {
                sv = (Sinhvien) ls.get(0);
            }

        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return sv;
    }

    public static Sinhvien layThongTinSinhVienBangID(String id) {
        Sinhvien sv = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            sv = (Sinhvien) session.get(Sinhvien.class, id);
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return sv;
    }

    public static boolean themSinhVien(Sinhvien s) {
        if(SinhVienDAO.layThongTinSinhVienBangID(s.getId()) != null) {
            return false;
        }
        Transaction transaction = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            session.save(s);
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            //session.close();
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
        return true;
    }
    public static boolean capNhatSinhVien(Sinhvien s) {
        if(SinhVienDAO.layThongTinSinhVienBangID(s.getId()) == null) {
            return false;
        }
        Transaction transaction = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            session.update(s);
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            //session.close();
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
        return true;
    }

    public static boolean resetMatKhauSV(Sinhvien s) {
        if (SinhVienDAO.layThongTinSinhVienBangID(s.getId()) == null) {
            return false;
        }
        Transaction transaction = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            String hql = "update Sinhvien set matKhau = :matKhau where id = :idSV";
            Query query = session.createQuery(hql);
            query.setParameter("matKhau", s.getMatKhau());
            query.setParameter("idSV", s.getId());
            query.executeUpdate();
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
            //session.close();
            return false;
        } finally {
            session.close();
        }
        return true;
    }
}
