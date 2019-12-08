package org.hibernate.envers.bugs.hhh13760;

import java.time.Instant;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity(name = "Address.Version")
@Table(name = "address_version")
public class AddressVersion extends BaseDomainEntityVersion {

	private static final long serialVersionUID = 1l;

	@Id
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "id", referencedColumnName = "id", insertable = true, updatable = false, nullable = false)
	private Address id;

	@Column(name = "description", insertable = true, updatable = false, nullable = true)
	private String description;

	@Override
	public Object getId() {
		return id;
	}

	AddressVersion() {
	}

	AddressVersion(Instant when, String who, Address id, long version, String description) {
		setCreatedAt(when);
		setCreatedBy(who);
		setVersion(version);
		this.id = Objects.requireNonNull(id);
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public AddressVersion update(Instant when, String who, String description) {
		var newVersion = new AddressVersion(when, who, id, getVersion() + 1, description);
		id.versions.add(newVersion);
		return newVersion;
	}
}