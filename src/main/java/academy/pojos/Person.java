package academy.pojos;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
/* @SequenceGenerator(name = "PK", sequenceName = "person") */
@Table(name = "person")
public class Person implements Serializable {

	private static final long serialVersionUID = 4L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, insertable = true, updatable = true)
	private long id;

	@Column(name = "age")
	private int age;

	@Column(name = "name")
	private String name;

	@Column(name = "surname")
	private String surname;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "role_id")
	private Role role;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "person_document", joinColumns = @JoinColumn(name = "person_id"), inverseJoinColumns = @JoinColumn(name = "document_id"))
	private Set<Document> documents = new HashSet<Document>();

	public Person() {
	}

	public Person(final int age, final String name, final String surname) {
		this.age = age;
		this.name = name;
		this.surname = surname;
	}

	public Person(int age, String name, String surname, Role role, Set<Document> documents) {
		super();
		this.age = age;
		this.name = name;
		this.surname = surname;
		this.role = role;
		this.documents = documents;
	}

	public Person(final long id, final int age, final String name, final String surname, Role role,
			Set<Document> documents) {
		this.id = id;
		this.age = age;
		this.name = name;
		this.surname = surname;
		this.role = role;
		this.documents = documents;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Set<Document> getDocuments() {
		return documents;
	}

	public void setDocuments(Set<Document> documents) {
		this.documents = documents;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (age != other.age)
			return false;
		if (documents == null) {
			if (!other.documents.isEmpty())
				return false;
		} else if (!documents.containsAll(other.documents)&&(other.documents.containsAll(documents))){
			System.err.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
			return false; }
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (role == null) {
			if (other.role != null)
				return false;
		} else if (!role.equals(other.role))
			return false;
		if (surname == null) {
			if (other.surname != null)
				return false;
		} else if (!surname.equals(other.surname))
			return false;
		return true;
	}

}
