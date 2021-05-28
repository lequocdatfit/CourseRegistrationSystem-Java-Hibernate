package controller;

import model.Giaovu;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateUtil;

import java.util.List;

public class GiaoVuDAO {
    public static Giaovu LayThongTinGiaoVu(String maGiaoVu) {
        Giaovu gv = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            gv = (Giaovu) session.get(Giaovu.class, maGiaoVu);
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return gv;
    }

    public static List<Giaovu> LayDanhSachGiaoVu() {
        List<Giaovu> ls = null;
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            final String hql = "from Giaovu gv";
            Query query = session.createQuery(hql);
            ls =  query.list();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return ls;
    }

    public static boolean themGiaoVu(Giaovu gv) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        if(GiaoVuDAO.LayThongTinGiaoVu(gv.getMaGiaoVu())!= null) {
            return false;
        }
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(gv);
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return true;
    }

    public static boolean suaThongTinGiaoVu(Giaovu g) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        if(GiaoVuDAO.LayThongTinGiaoVu(g.getMaGiaoVu()) == null) {
            return false;
        }
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(g);
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

    public static boolean xoaGiaoVu(Giaovu gv) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        if(GiaoVuDAO.LayThongTinGiaoVu(gv.getMaGiaoVu()) == null) {
            return false;
        }
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.delete(gv);
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
