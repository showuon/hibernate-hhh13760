//package org.hibernate.envers.bugs.hhh13760;
//
//import org.hibernate.envers.AuditTable;
//import org.hibernate.envers.Audited;
//import org.hibernate.envers.RelationTargetAuditMode;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.FetchType;
//import javax.persistence.JoinColumn;
//import javax.persistence.JoinColumns;
//import javax.persistence.ManyToOne;
//import javax.persistence.Table;
//import javax.persistence.UniqueConstraint;
//import javax.validation.constraints.NotEmpty;
//import javax.validation.constraints.NotNull;
//import javax.validation.constraints.Pattern;
//import javax.validation.constraints.Size;
//import java.time.Duration;
//import java.time.Instant;
//import java.util.Objects;
//
//@Entity
//@Table(name = "shipmentChildG")
//@Audited
//@AuditTable(value = "shipmentChildG_audit")
//public class ShipmentChildG extends BaseDomainEntity {
//
//	private static final long serialVersionUID = 1l;
//
//	@Column(name = "due_date", nullable = false, updatable = false)
//	private Instant dueDate;
//
//	@Column(name = "identifier", nullable = false, updatable = false)
//	@Size(min = 5, max = 100)
//	@NotNull
//	@NotEmpty
//	@Pattern(regexp = "^[A-Za-z0-9]+$")
//	private String identifier;
//
//	@javax.persistence.Version
//	@Column(name = "mvc_version", nullable = false)
//	private long mvcVersion;
//
//	@Column(name = "closed")
//	private Boolean closed;
//
//
////	@ManyToOne(fetch = FetchType.LAZY)
////	@JoinColumn(name = "ORGANIZATION_ID", referencedColumnName = "identifier")
////	@NotNull
////	private Shipment shipment = new Shipment(Instant.now(), "system",
////		Instant.now().plus(Duration.ofDays(3)), "abcd789",
////		null, null);
//
//
//	ShipmentChildG() {
//	}
//
//	public ShipmentChildG(Instant when, String who, Instant dueDate, String identifier, AddressVersion origin,
//						  AddressVersion destination) {
//		super(when, who);
//		this.dueDate = Objects.requireNonNull(dueDate);
//		this.identifier = Objects.requireNonNull(identifier);
//
//	}
//
//	public Instant getDueDate() {
//		return dueDate;
//	}
//
////	public AddressVersion getOrigin() {
////		return origin;
////	}
////
////	public AddressVersion getDestination() {
////		return destination;
////	}
//
//	public String getIdentifier() {
//		return identifier;
//	}
//
////	public void setOrigin(AddressVersion origin) {
////		this.origin = origin;
////	}
////
////	public void setDestination(AddressVersion destination) {
////		this.destination = destination;
////	}
//
//	public void setClosed(Boolean closed) {
//		this.closed = closed;
//	}
//
//	public Boolean getClosed() {
//		return closed;
//	}
//}
