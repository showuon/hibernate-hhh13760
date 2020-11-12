//package org.hibernate.envers.bugs.hhh13760;
//
//import org.hibernate.envers.AuditTable;
//import org.hibernate.envers.Audited;
//
//import javax.persistence.CascadeType;
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.FetchType;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
//import javax.persistence.OneToOne;
//import javax.persistence.Table;
//import java.time.Instant;
//import java.util.Objects;
//
//@Entity
//@Table(name = "shipmentB")
//@Audited
//@AuditTable(value = "shipmentB_audit")
//public class ShipmentB {
//
//	private static final long serialVersionUID = 1l;
//
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@Column(name = "id")
//	private Long id;
//
//	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//	@JoinColumn(name = "shipmentC_id")
//	private ShipmentC shipmentC= new ShipmentC();
//
//	ShipmentB() {
//	}
//
//	public ShipmentB(ShipmentC c) {
//		this.shipmentC = c;
//	}
//	public Long getId() {return this.id;}
//
////	public ShipmentB(Instant when, String who, Instant dueDate, String identifier, AddressVersion origin,
////                     AddressVersion destination) {
////
//////		this.shipmentChild2 = new ShipmentChildG(Instant.now(), "system",
//////			Instant.now().plus(Duration.ofDays(3)), "abcd789",
//////			null, null);
////	}
//
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
