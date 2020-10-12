package jm.task.core.jdbc.dao;

import com.sun.javafx.binding.StringFormatter;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        Session session = null;
        try {
            session = Util.getSessionFactory().openSession();
            session.beginTransaction();
            session.createSQLQuery("CREATE TABLE IF NOT EXISTS user (id INT AUTO_INCREMENT NOT NULL, name VARCHAR(45) NOT NULL, lastname VARCHAR(45) NOT NULL, age INT NOT NULL, PRIMARY KEY (id))").executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (session != null) {
                session.getTransaction().rollback();
            }
        }
    }

    @Override
    public void dropUsersTable() {
        Session session = null;
        try {
            session = Util.getSessionFactory().openSession();
            session.beginTransaction();
            session.createSQLQuery("DROP TABLE IF EXISTS user").executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (session != null) {
                session.getTransaction().rollback();
            }
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = null;
        try {
            session = Util.getSessionFactory().openSession();
            session.beginTransaction();
            User user = new User(name, lastName, age);
            session.save(user);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (session != null) {
                session.getTransaction().rollback();
            }
        }
    }

    @Override
    public void removeUserById(long id) {
        Session session = null;
        try {
            session = Util.getSessionFactory().openSession();
            session.beginTransaction();
            User user = new User();
            user.setId(id);
            session.delete(user);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (session != null) {
                session.getTransaction().rollback();
            }
        }
    }

    @Override
    public List<User> getAllUsers() {
        Session session = null;
        List<User> users = null;
        try {
            session = Util.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createQuery("FROM user");
            users = query.list();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (session != null) {
                session.getTransaction().rollback();
            }
        }
        return users;
    }

    @Override
    public void cleanUsersTable() {
        Session session = null;
        try {
            session = Util.getSessionFactory().openSession();
            session.beginTransaction();
            String hql = "DELETE user";
            Query query = session.createQuery(hql);
            query.executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (session != null) {
                session.getTransaction().rollback();
            }
        }
    }
}
