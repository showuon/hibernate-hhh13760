package org.hibernate.envers.bugs.hhh13760;

import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.Instant;
import java.util.List;
import java.util.Objects;

@Entity(name = "Child.Address.Version")
@Table(name = "child_address_version")
@Audited
public class ChildAddressVersion extends AddressVersion {

	private static final long serialVersionUID = 1l;

//	@Id
//	@ManyToOne(optional = false, fetch = FetchType.LAZY)
//	@JoinColumn(name = "id", referencedColumnName = "id", insertable = true, updatable = false, nullable = false)
//	private Address id;

	@Column(name = "child_description", insertable = true, updatable = false, nullable = true)
	private String description;

	//	@OneToMany(fetch = FetchType.LAZY)
//	@JoinColumns(value = { @JoinColumn(name = "origin_address_id", referencedColumnName = "id", nullable = true),
//		@JoinColumn(name = "origin_address_version", referencedColumnName = "version", nullable = true) })
//	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "origin", cascade = CascadeType.REMOVE)
	@NotAudited
	private List<Shipment> shipmentList;

//	@Override
//	public Object getId() {
//		return id;
//	}

	ChildAddressVersion() {
	}

	ChildAddressVersion(Instant when, String who, Address id, long version, String description) {
		super(when, who, id, version, description);
//		setCreatedAt(when);
//		setCreatedBy(who);
//		setVersion(version);
////		this.id = Objects.requireNonNull(id);

//		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public ChildAddressVersion update(Instant when, String who, String description) {
//		super.update(when, who, description);

		ChildAddressVersion newVersion = new ChildAddressVersion(when, who, (Address)this.getId(), getVersion() + 1, description);
		((Address)this.getId()).versions.add(newVersion);
		return newVersion;
	}
}