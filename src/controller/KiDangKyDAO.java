package controller;

import model.Hocki;
import model.Kidangkihocphan;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateUtil;

import java.util.List;

public class KiDangKyDAO {
    public static List<Kidangkihocphan> layDanhSachKiDangKyHP() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Kidangkihocphan> ls = null;
        try {
            String hql = "from Kidangkihocphan k";
            Query query = session.createQuery(hql);
            ls = query.list();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return ls;
    }

    public static Kidangkihocphan layThongTinKiDangKy(String id) {
        Kidangkihocphan k = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            k = (Kidangkihocphan) session.get(Kidangkihocphan.class, id);
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return k;
    }

    public static List<Kidangkihocphan> layDanhSachKiDangKyCuaHocKi(Hocki h) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Kidangkihocphan> ls =null;
        try {
            String hql = "from Kidangkihocphan k where k.tenHocKi = :tenHK and k.namHoc = :namHoc";
            Query query = session.createQuery(hql);
            query.setParameter("tenHK", h.getTenHk());
            query.setParameter("namHoc", h.getNamHoc());
            ls = query.list();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return ls;
    }

    public static boolean themKiDangKy(Kidangkihocphan k) {
        if(KiDangKyDAO.layThongTinKiDangKy(k.getId()) != null) {
            return false;
        }
        Transaction transaction = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            session.save(k);
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            session.close();
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
        return true;
    }
}
