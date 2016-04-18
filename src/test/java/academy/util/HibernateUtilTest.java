package academy.util;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import academy.pojos.Document;
import academy.pojos.Person;
import academy.pojos.Role;

public class HibernateUtilTest {

	private static HibernateUtil util;
	long idPerson;

	@BeforeClass
	public static void start() {
		util = new HibernateUtil();
	}

	public Long addToDatabase(Object object) {
		final Session session = util.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		Long id = null;
		try {
			id = (Long) session.save(object);
		} catch (HibernateException e) {
			transaction.rollback();
		} finally {
			session.flush();
			transaction.commit();
			session.close();
		}
		return id;
	}

	@Test
	public void getRoleTest() throws Exception {
		long idRole;
		Role createdRole = new Role("getRoleTest");
		idRole = addToDatabase(createdRole);
		Role obtainedRole = util.getRole(idRole);
		assertEquals(createdRole, obtainedRole);
	}

	@Test
	public void addRoleTest() throws Exception {
		long idRole;
		Role role = new Role("addRoleTest");
		idRole = util.addRole(role);
		assertEquals(role, util.getRole(idRole));
	}

	@Test
	public void getDocumentTest() throws Exception {
		long idDocument;
		Document createdDocument = new Document("getDocumentTest");
		idDocument = addToDatabase(createdDocument);
		Document obtainedDocument = util.getDocument(idDocument);
		assertEquals(createdDocument, obtainedDocument);
	}

	@Test
	public void addDocumentTest() throws Exception {
		long idDocument;
		Document document = new Document("addDocumentTest");
		System.err.println("Before id= " + document.getId());
		idDocument = util.addDocument(document);
		System.err.println("After id= " + document.getId());
		assertEquals(document, util.getDocument(idDocument));
	}

	@Test
	public void getPersonTest() throws Exception {
		Role role = new Role();
		Document document1 = new Document();
		Document document2 = new Document();
		Set<Document> documents = new HashSet<Document>();

		role.setName("getPersonTest");
		document1.setTitle("Birth certificate");
		document2.setTitle("Passport");
		documents.add(document1);
		documents.add(document2);
		Person createdPerson = new Person(26, "Petr", "Petrovich", role, documents);
		idPerson = addToDatabase(createdPerson);
		Person obtainedPerson = util.getPerson(idPerson);
		assertEquals(createdPerson, obtainedPerson);
	}

	@Test
	public void addPersonTest() throws Exception {
		Role role = new Role();
		Document document1 = new Document();
		Document document2 = new Document();
		Set<Document> documents = new HashSet<Document>();

		role.setName("getPersonTest");
		document1.setTitle("Birth certificate");
		document2.setTitle("Passport");
		documents.add(document1);
		documents.add(document2);
		Person createdPerson = new Person(26, "Petr", "Petrovich", role, documents);
		idPerson = util.addPerson(createdPerson);
		Person obtainedPerson = util.getPerson(idPerson);
		assertEquals(createdPerson, obtainedPerson);
	}

	@Test
	public void updateRoleTest() throws Exception {
		Role createdRole = new Role("New role in updateRoleTest");
		long idRole = addToDatabase(createdRole);
		Role obtainedRole = util.getRole(idRole);
		obtainedRole.setName("Update role in updateRoleTest");
		util.updateRole(obtainedRole);
		Role updatedRole = util.getRole(idRole);
		assertTrue(obtainedRole.equals(updatedRole));
	}

	@Test
	public void updateDocumentTest() throws Exception {
		Document createdDocument = new Document("New document in updateDocumentTest");
		long idDocument = addToDatabase(createdDocument);
		Document obtainedDocument = util.getDocument(idDocument);
		obtainedDocument.setTitle("Update document in updateDocumentTest");
		util.updateDocument(obtainedDocument);
		Document updatedDocument = util.getDocument(idDocument);
		assertTrue(obtainedDocument.equals(updatedDocument));
	}

	@Test
	public void updatePersonTest() throws Exception {
		Role role1 = new Role();
		Document document1 = new Document();
		Document document2 = new Document();
		Set<Document> documents1 = new HashSet<Document>();
		role1.setName("updatePersonTest1");
		document1.setTitle("Birth certificate");
		document2.setTitle("Passport");
		documents1.add(document1);
		documents1.add(document2);
		Person createdPerson = new Person(26, "Petr", "Petrovich", role1, documents1);
		long idPerson = addToDatabase(createdPerson);

		Person obtainedPerson = util.getPerson(idPerson);
		Document document3 = new Document();
		Document document4 = new Document();
		Set<Document> documents2 = new HashSet<Document>();
		Role role2 = new Role("updatePersonTest");
		document3.setTitle("Driver license");
		document4.setTitle("Marriage certificate");
		documents2.addAll(obtainedPerson.getDocuments());
		documents2.add(document3);
		documents2.add(document4);
		obtainedPerson.setDocuments(documents2);
		obtainedPerson.setAge(obtainedPerson.getAge() + 1);
		obtainedPerson.setName("Vasya");
		obtainedPerson.setSurname("Vasil'evich");
		obtainedPerson.setRole(role2);
		util.updatePerson(obtainedPerson);
		Person updatedPerson = util.getPerson(idPerson);
		assertEquals(obtainedPerson, updatedPerson);
	}

	@Test
	public void deleteRoleTest() throws Exception {
		Role createdRole = new Role("New role in deleteRoleTest");
		long idRole = addToDatabase(createdRole);
		util.deleteRole(idRole);
		assertNull(util.getRole(idRole));
	}

	@Test
	public void deleteDocumentTest() throws Exception {
		Document createdDocument = new Document("New document in deleteDocumentTest");
		long idDocument = addToDatabase(createdDocument);
		util.deleteDocument(idDocument);
		assertNull(util.getDocument(idDocument));
	}

	@Test
	public void deletePersonTest() throws Exception {
		Role role1 = new Role();
		Document document1 = new Document();
		Document document2 = new Document();
		Set<Document> documents = new HashSet<Document>();
		role1.setName("deletePersonTest");
		document1.setTitle("Birth certificate");
		document2.setTitle("Passport");
		documents.add(document1);
		documents.add(document2);
		Person createdPerson = new Person(26, "Petr", "Petrovich", role1, documents);
		long idPerson = addToDatabase(createdPerson);
		util.deletePerson(idPerson);
		assertNull(util.getPerson(idPerson));
	}

	@Test
	public void getAllPersonsTest() throws Exception {
		for (Person p : util.getAllPersons()) {
			System.err.println(p.getId() + " " + p.getName() + " " + p.getSurname());
		}
		assertNotNull(util.getAllPersons());
	}

	@AfterClass
	public static void finish() {
		util.close();
	}
}
