package org.hibernate.envers.bugs.hhh13760;

import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

import java.time.Instant;
import java.util.Collection;
import java.util.LinkedList;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "address")
@Audited
public class Address extends BaseDomainEntity {
	private static final long serialVersionUID = 1l;

	@Column(name = "name", insertable = true, updatable = true, nullable = true)
	private String name;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "id", cascade = CascadeType.ALL)
	Collection<AddressVersion> versions = new LinkedList<AddressVersion>();

	Address() {
	}

	public Address(Instant when, String who, String name) {
		setCreatedAt(when);
		setCreatedBy(who);
		this.name = name;
	}

	public AddressVersion addInitialVersion(String description) {
		AddressVersion v = new AddressVersion(getCreatedAt(), getCreatedBy(), this, 0, description);
		versions.add(v);
		return v;
	}

	public String getName() {
		return name;
	}

	public Collection<AddressVersion> getVersions() {
		return versions;
	}
}
