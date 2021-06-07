package controller;

import model.Monhoc;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateUtil;

import java.util.List;

public class MonhocDAO {
    public static List<Monhoc> LayDanhSachMonHoc() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Monhoc> ls = null;
        try {
            final String hql = "from Monhoc m";
            Query query = session.createQuery(hql);
            ls = query.list();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return ls;
    }

    public static Monhoc layThongTinMonHoc(String maMonHoc) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Monhoc m = null;
        try {
            m = (Monhoc) session.get(Monhoc.class, maMonHoc);
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return m;
    }

    public static boolean themMonHoc(Monhoc m) {
        if(MonhocDAO.layThongTinMonHoc(m.getMaMh()) != null) {
            return false;
        }
        Transaction transaction = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            session.save(m);
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

    public static boolean capNhatMonHoc(Monhoc m) {
        if(MonhocDAO.layThongTinMonHoc(m.getMaMh()) == null) {
            return false;
        }
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(m);
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

    public static boolean xoaThongTinMonHoc(Monhoc m) {
        if(MonhocDAO.layThongTinMonHoc(m.getMaMh()) == null) {
            return false;
        }
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.delete(m);
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
        return true;
    }
}
