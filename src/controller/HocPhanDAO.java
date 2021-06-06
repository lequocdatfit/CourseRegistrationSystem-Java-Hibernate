package controller;

import model.Hocki;
import model.Hocphan;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateUtil;

import java.util.List;

public class HocPhanDAO {
    public static List<Hocphan> layDaySachHocPhan() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Hocphan> ls = null;
        try {
            final String hql = "from Hocphan h";
            Query query = session.createQuery(hql);
            ls = query.list();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return ls;
    }

    public static List<Hocphan> layDaySachHocPhanCuaHocKi(Hocki h) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Hocphan> ls = null;
        try {
            final String hql = "from Hocphan h where h.tenHk = :tenHK and h.namHoc = :namHoc";
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



    public static Hocphan layThongTinHocPhan(String maHocPhan) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Hocphan h = null;
        try {
            h = (Hocphan) session.get(Hocphan.class, maHocPhan);
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return h;
    }

    public static boolean themHocPhan(Hocphan h) {
        if(HocPhanDAO.layThongTinHocPhan(h.getId()) != null) {
            return false;
        }
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(h);
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

    public static boolean xoaHocPhan(Hocphan h) {
        if(HocPhanDAO.layThongTinHocPhan(h.getId()) == null) {
            return false;
        }
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.delete(h);
            transaction.commit();
        } catch (Exception e) {
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
