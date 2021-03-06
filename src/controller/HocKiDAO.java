package controller;

import model.Hocki;
import model.HockiPK;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateUtil;

import java.util.List;

public class HocKiDAO {
    public static List<Hocki> layDanhSachHocKi() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Hocki> ls = null;
        try {
            final String hql = "from Hocki h";
            Query query = session.createQuery(hql);
            ls = query.list();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return ls;
    }

    public static Hocki layThongTinHocKi(String tenHK, String namHoc) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Hocki h = null;
        try {
            h = (Hocki) session.get(Hocki.class, new HockiPK(tenHK, namHoc));
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return h;
    }

    public static boolean themHocKi(Hocki h) {
        if(HocKiDAO.layThongTinHocKi(h.getTenHk(), h.getNamHoc()) != null) {
            return false;
        }
        Transaction transaction = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            session.save(h);
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

    public static boolean xoaHocKi(Hocki h) {
        if(HocKiDAO.layThongTinHocKi(h.getTenHk(), h.getNamHoc()) == null) {
            return false;
        }
        Transaction transaction = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            session.delete(h);
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

    public static boolean setCurrentSemester(Hocki h) {
        if(HocKiDAO.layThongTinHocKi(h.getTenHk(), h.getNamHoc()) == null) {
            return false;
        }
        Transaction transaction = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            final String hql = "UPDATE Hocki set hkHienTai = :bool";
            Query query = session.createQuery(hql);
            query.setParameter("bool", false);
            int result = query.executeUpdate();
            String hql2 = "UPDATE Hocki set hkHienTai = :bool WHERE tenHk = :tenHk and namHoc = :namHoc";
            Query query1 = session.createQuery(hql2);
            query1.setParameter("bool", true);
            query1.setParameter("tenHk", h.getTenHk());
            query1.setParameter("namHoc", h.getNamHoc());
            int output = query1.executeUpdate();
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

    public static Hocki layHocKiHienTai() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Hocki h = null;
        try {
            final String hql = "from Hocki h WHERE h.hkHienTai = :bool";
            Query query = session.createQuery(hql);
            query.setParameter("bool", true);
            List result = query.list();
            h = (Hocki) result.get(0);
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return h;
    }
}
