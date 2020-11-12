package org.hibernate.envers.bugs.hhh13760;

import java.time.Duration;
import java.time.Instant;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;

@Entity
@Table(name = "shipment")
@Audited
@AuditTable(value = "shipment_audit")
public class Shipment extends BaseDomainEntity {

	private static final long serialVersionUID = 1l;

//	@Id
//	@ManyToOne(optional = false, fetch = FetchType.LAZY)
//	private Address id;


//	@Id
//	@Column(name = "version", nullable = false, updatable = false)
//	private long version;

	@Column(name = "due_date", nullable = false, updatable = false)
	private Instant dueDate;

	@Column(name = "identifier", nullable = false, updatable = false)
	@Size(min = 5, max = 100)
	@NotNull
	@NotEmpty
	@Pattern(regexp = "^[A-Za-z0-9]+$")
	private String identifier;

	@javax.persistence.Version
	@Column(name = "mvc_version", nullable = false)
	private long mvcVersion;
	
	@Column(name = "closed")
	private Boolean closed;

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumns(value = { @JoinColumn(name = "origin_address_id", referencedColumnName = "id"),
			@JoinColumn(name = "origin_address_version", referencedColumnName = "version") })
	@NotNull
//	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	private AddressVersion origin;

	@ManyToOne(optional = false, fetch = FetchType.LAZY, targetEntity = AddressVersion.class)
	@JoinColumns(value = { @JoinColumn(name = "destination_address_id", referencedColumnName = "id", nullable = true),
			@JoinColumn(name = "destination_address_version", referencedColumnName = "version", nullable = true) })
//	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	private AddressVersion destination;

//	@ManyToOne(optional = true, fetch = FetchType.LAZY)
////	@JoinColumn(name = "due_date")
//	private ShipmentChildG shipmentChildG;

	Shipment() {
	}

	public Shipment(Instant when, String who, Instant dueDate, String identifier, AddressVersion origin,
			AddressVersion destination) {
		super(when, who);
		this.dueDate = Objects.requireNonNull(dueDate);
		this.identifier = Objects.requireNonNull(identifier);
		this.origin = origin;
		this.destination = destination;
//		this.shipmentChild2 = new ShipmentChildG(Instant.now(), "system",
//			Instant.now().plus(Duration.ofDays(3)), "abcd789",
//			null, null);
	}

	public Instant getDueDate() {
		return dueDate;
	}

	public AddressVersion getOrigin() {
		return origin;
	}

	public AddressVersion getDestination() {
		return destination;
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setOrigin(AddressVersion origin) {
		this.origin = origin;
	}

	public void setDestination(AddressVersion destination) {
		this.destination = destination;
	}
	
	public void setClosed(Boolean closed) {
		this.closed = closed;
	}
	
	public Boolean getClosed() {
		return closed;
	}

//	public ShipmentChildG getShipmentChildG(){return this.shipmentChildG;}
//	public void setShipmentChildG(ShipmentChildG shipmentChildG){this.shipmentChildG = shipmentChildG;}
}
