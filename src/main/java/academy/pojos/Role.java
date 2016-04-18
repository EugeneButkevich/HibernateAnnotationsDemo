package academy.pojos;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.*;

@Entity
/* @SequenceGenerator(name = "PK", sequenceName = "role") */
@Table(name = "role")
public class Role implements Serializable {

	private static final long serialVersionUID = 4L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, insertable = true, updatable = true)
	private long id;

	@Column(name = "name")
	private String name;

	@OneToMany(mappedBy = "role", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<Person> persons;

	public Role() {
	}

	public Role(long id, String name) {
		this.id = id;
		this.name = name;
	}

	public Role(String name) {
		this.name = name;
	}

	public Role(long id, String name, Set<Person> persons) {
		this.id = id;
		this.name = name;
		this.persons = persons;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Person> getPersons() {
		return persons;
	}

	public void setPersons(Set<Person> persons) {
		this.persons = persons;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Role other = (Role) obj;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
/*		if (persons == null) {
			if (!other.persons.isEmpty())
				return false;
		}  else if (!(persons.containsAll(other.persons)&&(other.persons.containsAll(persons))))
			return false;*/
		return true;
	}

}
