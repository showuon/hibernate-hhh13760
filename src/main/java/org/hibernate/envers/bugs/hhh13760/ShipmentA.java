//package org.hibernate.envers.bugs.hhh13760;
//
//import org.hibernate.envers.AuditTable;
//import org.hibernate.envers.Audited;
//import org.hibernate.envers.RelationTargetAuditMode;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.FetchType;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.JoinColumns;
//import javax.persistence.ManyToOne;
//import javax.persistence.Table;
//import javax.validation.constraints.NotEmpty;
//import javax.validation.constraints.NotNull;
//import javax.validation.constraints.Pattern;
//import javax.validation.constraints.Size;
//import java.time.Instant;
//import java.util.Objects;
//
//@Entity
//@Table(name = "shipmentA")
//@Audited
//@AuditTable(value = "shipmentA_audit")
//public class ShipmentA {
//
//	private static final long serialVersionUID = 1l;
//
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@Column(name = "id")
//	private Long id;
//
//	@ManyToOne(fetch = FetchType.LAZY)
//	private ShipmentB shipmentB;
//
//	ShipmentA() {
//	}
//
//	public ShipmentA(ShipmentB b) {
//		this.shipmentB = b;
//	}
//
//	public Long getId() {return this.id;}
//	public ShipmentB getShipmentB() {return this.shipmentB;}
//	public void setShipmentB(ShipmentB b) {this.shipmentB = b;}
//
////	public ShipmentA(Instant when, String who, Instant dueDate, String identifier, AddressVersion origin,
////                     AddressVersion destination) {
////		super(when, who);
////		this.dueDate = Objects.requireNonNull(dueDate);
////		this.identifier = Objects.requireNonNull(identifier);
////		this.origin = origin;
////		this.destination = destination;
//////		this.shipmentChild2 = new ShipmentChildG(Instant.now(), "system",
//////			Instant.now().plus(Duration.ofDays(3)), "abcd789",
//////			null, null);
////	}
////
////	public Instant getDueDate() {
////		return dueDate;
////	}
////
////	public AddressVersion getOrigin() {
////		return origin;
////	}
////
////	public AddressVersion getDestination() {
////		return destination;
////	}
////
////	public String getIdentifier() {
////		return identifier;
////	}
////
////	public void setOrigin(AddressVersion origin) {
////		this.origin = origin;
////	}
////
////	public void setDestination(AddressVersion destination) {
////		this.destination = destination;
////	}
////
////	public void setClosed(Boolean closed) {
////		this.closed = closed;
////	}
////
////	public Boolean getClosed() {
////		return closed;
////	}
////
////	public ShipmentChildG getShipmentChildG(){return this.shipmentChildG;}
////	public void setShipmentChildG(ShipmentChildG shipmentChildG){this.shipmentChildG = shipmentChildG;}
//}
