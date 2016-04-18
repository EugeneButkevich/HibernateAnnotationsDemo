package academy.util;

import java.io.Serializable;
import java.util.Collection;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;

import academy.pojos.Document;
import academy.pojos.Person;
import academy.pojos.Role;

public class HibernateUtil {

	private static Logger log = Logger.getLogger(HibernateUtil.class);

	private final SessionFactory sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();;

	public HibernateUtil() {
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public Long addPerson(Person person) {
		final Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Long id = null;
		try {
			id = (Long) session.save(person);
		} catch (HibernateException e) {
			log.error("Error was thrown in DAO: " + e);
			transaction.rollback();
		} finally {
			session.flush();
			transaction.commit();
			session.close();
		}
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
			Person person = (Person) session.get(Person.class, idPerson);
			session.delete(person);
		} catch (HibernateException e) {
			log.error("Error was thrown in DAO: " + e);
			transaction.rollback();
		} finally {
			session.flush();
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

	public Long addRole(Role role) {
		final Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Long id = null;
		try {
			id = (Long) session.save(role);
		} catch (HibernateException e) {
			log.error("Error was thrown in DAO: " + e);
			transaction.rollback();
		} finally {
			session.flush();
			transaction.commit();
			session.close();
		}
		return id;
	}

	public Role getRole(long id) {
		Role role = null;
		final Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		try {
			role = (Role) session.get(Role.class, id);
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

	public void updateRole(Role role) {
		final Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		try {
			/* Person person = session.load(Person.class, idPerson); */
			session.update(role);
		} catch (HibernateException e) {
			log.error("Error was thrown in DAO: " + e);
			transaction.rollback();
		} finally {
			transaction.commit();
			session.close();
		}
	}

	public void deleteRole(long idRole) {
		final Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		try {
			Role role = (Role) session.get(Role.class, idRole);
			session.delete(role);
		} catch (HibernateException e) {
			log.error("Error was thrown in DAO: " + e);
			transaction.rollback();
		} finally {
			session.flush();
			transaction.commit();
			session.close();
		}
	}

	public Long addDocument(Document document) {
		final Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Long id = null;
		try {
			id = (Long) session.save(document);
		} catch (HibernateException e) {
			log.error("Error was thrown in DAO: " + e);
			transaction.rollback();
		} finally {
			session.flush();
			transaction.commit();
			session.close();
		}
		return id;
	}

	public Document getDocument(long id) {
		Document document = null;
		final Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		try {
			document = (Document) session.get(Document.class, id);
		} catch (HibernateException e) {
			log.error("Error was thrown in DAO: " + e);
			transaction.rollback();
		} finally {
			session.flush();
			transaction.commit();
			session.close();
		}
		return document;
	}
	
	public void updateDocument(Document document) {
		final Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		try {
			/* Person person = session.load(Person.class, idPerson); */
			session.update(document);
		} catch (HibernateException e) {
			log.error("Error was thrown in DAO: " + e);
			transaction.rollback();
		} finally {
			transaction.commit();
			session.close();
		}
	}
	
	public void deleteDocument(long idDocument) {
		final Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		try {
			Document document = (Document) session.get(Document.class, idDocument);
			session.delete(document);
		} catch (HibernateException e) {
			log.error("Error was thrown in DAO: " + e);
			transaction.rollback();
		} finally {
			session.flush();
			transaction.commit();
			session.close();
		}
	}

	public void close() {
		this.sessionFactory.close();
	}
}
