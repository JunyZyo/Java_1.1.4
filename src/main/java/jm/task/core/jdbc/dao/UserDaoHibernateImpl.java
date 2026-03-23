package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

import static jm.task.core.jdbc.util.Util.getSessionFactory;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        String sql = "CREATE TABLE IF NOT EXISTS users " + "(id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY, " + "name VARCHAR(50) NOT NULL, lastName VARCHAR(50) NOT NULL, " + "age TINYINT NOT NULL)";

        session.createSQLQuery(sql).executeUpdate();

        transaction.commit();
        session.close();

        System.out.println("Таблица users создана");
    }

    @Override
    public void dropUsersTable() {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        String sql = "DROP TABLE IF EXISTS users";

        session.createSQLQuery(sql).executeUpdate();

        transaction.commit();
        session.close();
        System.out.println("Таблица удалена");
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            User user = new User();
            user.setName(name);
            user.setLastName(lastName);
            user.setAge(age);

            session.save(user);

            transaction.commit();

            System.out.println("User с именем — " + name + " добавлен в базу данных");

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public void removeUserById(long id) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            User user = session.get(User.class, id);

            if (user != null) {
                session.remove(user);
                System.out.println("Данные по id " + id + " удалены");
            } else {
                System.out.println("Пользователь с id " + id + " не найден");
            }

            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();

        try (Session session = getSessionFactory().openSession()) {
            users = session.createQuery("FROM User", User.class).getResultList();

            System.out.println("\nСписок всех пользователей в таблице:");
            users.forEach(user -> System.out.println(user.getId() + ": " + user.getName() + " " + user.getLastName() + ", " + user.getAge() + " лет"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public void cleanUsersTable() {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        session.createQuery("DELETE FROM User").executeUpdate();

        transaction.commit();
        System.out.println("Таблица очищена");
    }
}
