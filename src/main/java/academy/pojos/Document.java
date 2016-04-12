package academy.pojos;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
/* @SequenceGenerator(name = "PK", sequenceName = "role") */
@Table(name = "document")
public class Document implements Serializable {

	private static final long serialVersionUID = 4L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, insertable = true, updatable = true)
	private long id;
	
	@Column(name = "title")
	private String title;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "person_document", joinColumns = @JoinColumn(name = "document_id"), inverseJoinColumns = @JoinColumn(name = "person_id"))
	private Set<Person> persons = new HashSet<Person>();

	public Document() {
	}

	public Document(long id, String title) {
		this.id = id;
		this.title = title;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Set<Person> getPersons() {
		return persons;
	}

	public void setPersons(Set<Person> persons) {
		this.persons = persons;
	}

}
