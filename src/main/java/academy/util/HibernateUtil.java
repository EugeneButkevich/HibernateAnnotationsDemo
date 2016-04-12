package academy.util;

import java.io.Serializable;
import java.util.Collection;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.ConfigurationArtefactType;

import academy.pojos.Person;
import academy.pojos.Role;

public class HibernateUtil {

	private static Logger log = Logger.getLogger(HibernateUtil.class);
	/*
	 * private final SessionFactory sessionFactory = new
	 * Configuration().configure().buildSessionFactory();
	 */
	private SessionFactory sessionFactory;

	public HibernateUtil() {

		try {
			sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
		} catch (Throwable e) {
			log.error("Initial SessionFactory creation failed." + e);
		}

	}

	public Serializable addPerson(Person person) {
		final Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Serializable id = 0;
		System.out.println("зашли в адд");
		try {
			System.out.println("будем считать id");
			id = session.save(person);
			System.out.println("id=" + id);
		} catch (HibernateException e) {
			log.error("Error was thrown in DAO: " + e);
			transaction.rollback();
		} finally {
			session.flush();
			transaction.commit();
			session.close();
		}
		System.out.println("возвращаем id=" + id);
		return id;
	}

	public Collection<Person> getAllPersons() {
		final Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Collection<Person> personList = null;
		try {
			personList = session.createCriteria(Person.class).list();
		} catch (HibernateException e) {
			log.error("Error was thrown in DAO: " + e);
			transaction.rollback();
		} finally {
			transaction.commit();
			session.close();
		}
		return personList;
	}

	public void deletePerson(long idPerson) {
		final Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		try {
			System.out.println("РЈРґР°Р»СЏРµРј РїРѕС†С‹РєР° СЃ id=" + idPerson);
			Person person = (Person) session.get(Person.class, idPerson);
			session.delete(person);
			System.out.println("СѓРґР°Р»РёР»Рё РІСЂРѕРґСЊ РєР°Рє");
		} catch (HibernateException e) {
			log.error("Error was thrown in DAO: " + e);
			transaction.rollback();
		} finally {
			transaction.commit();
			session.close();
		}
	}

	public void updatePerson(Person person) {
		final Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		try {
			/* Person person = session.load(Person.class, idPerson); */
			session.update(person);
		} catch (HibernateException e) {
			log.error("Error was thrown in DAO: " + e);
			transaction.rollback();
		} finally {
			transaction.commit();
			session.close();
		}
	}

	public void saveOrUpdatePerson(Person person) {
		final Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		try {
			/* session.merge(person); */
			session.saveOrUpdate(person);
		} catch (HibernateException e) {
			log.error("Error was thrown in DAO: " + e);
			transaction.rollback();
		} finally {
			session.flush();
			transaction.commit();
			session.close();
		}
	}

	public Person getPerson(long id) {
		Person person = null;
		final Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		try {
			person = (Person) session.get(Person.class, id);
		} catch (HibernateException e) {
			log.error("Error was thrown in DAO: " + e);
			transaction.rollback();
		} finally {
			session.flush();
			transaction.commit();
			session.close();
		}
		return person;
	}

	public Role getRole(long id) {
		Role role = null;
		final Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		try {
			/* role = (Role) session.get(Role.class, id); */
			role = (Role) session.load(Role.class, id);
		} catch (HibernateException e) {
			log.error("Error was thrown in DAO: " + e);
			transaction.rollback();
		} finally {
			session.flush();
			transaction.commit();
			session.close();
		}
		return role;
	}

	public void close() {
		this.sessionFactory.close();
	}
}
