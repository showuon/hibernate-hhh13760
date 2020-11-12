package org.hibernate.envers.bugs;

import java.time.Duration;
import java.time.Instant;

import org.hibernate.Hibernate;
import org.hibernate.Transaction;
import org.hibernate.envers.bugs.hhh13760.Address;
import org.hibernate.envers.bugs.hhh13760.AddressVersion;
import org.hibernate.envers.bugs.hhh13760.Shipment;
//import org.hibernate.envers.bugs.hhh13760.ShipmentA;
//import org.hibernate.envers.bugs.hhh13760.ShipmentB;
//import org.hibernate.envers.bugs.hhh13760.ShipmentC;
//import org.hibernate.envers.bugs.hhh13760.ShipmentChild;
//import org.hibernate.envers.bugs.hhh13760.ShipmentChildG;
import org.junit.Test;

public class HHH13760TestCase extends AbstractEnversTestCase {

	@Override
	protected Class[] getAnnotatedClasses() {
//		return new Class[] {ShipmentA.class,
//			ShipmentB.class,
//			ShipmentC.class,
//			Shipment.class, ShipmentChild.class, ShipmentChildG.class, Address.class, AddressVersion.class };
		return new Class[] {Shipment.class, Address.class, AddressVersion.class };
//			ShipmentB.class,
//			ShipmentC.class,
//			Shipment.class, ShipmentChild.class, ShipmentChildG.class, Address.class, AddressVersion.class };
	}

	@Test
	public void hhh13760Test() {

		openSession();
		long id, idChild, idChildG, idA, idB, idC, idOrigin;
		Transaction tx = null;
		AddressVersion originV0;
		Shipment shipment;

//		try {
//			ShipmentC shipmentC = new ShipmentC("test");
//			ShipmentB shipmentB = new ShipmentB(shipmentC);
//			ShipmentA shipmentA = new ShipmentA(shipmentB);
//			tx = session.beginTransaction();
//			session.persist(shipmentC);
//			session.persist(shipmentB);
//			session.persist(shipmentA);
//			session.flush();
//
//
//			ShipmentA shipmentA1 = new ShipmentA(shipmentB);
//			session.persist(shipmentA1);
//			session.persist(shipmentB);
//			session.flush();
//
//
//			shipmentC = new ShipmentC("test");
//			shipmentB = new ShipmentB(shipmentC);
//			session.persist(shipmentC);
//			session.persist(shipmentB);
//			session.flush();
//
//
//			idA = shipmentA.getId();
//			idB = shipmentB.getId();
//			idC = shipmentC.getId();
//		} finally {
//			if (tx != null) {
//				tx.commit();
//				tx = null;
//			}
//		}
//		session.close();


		try {
//			shipment = new Shipment(Instant.now(), "system", Instant.now().plus(Duration.ofDays(3)), "abcd123",
//					null, null);
////			ShipmentChildG shipmentChildG = new ShipmentChildG(Instant.now(), "system", Instant.now().plus(Duration.ofDays(3)), "abcd789",
////				null, null);
//			tx = session.beginTransaction();
//			session.persist(shipment);
////			session.persist(shipmentChildG);
////			session.flush();
////			id = shipment.getId();
////			idChildG = shipmentChildG.getId();
//
//			Address origin = new Address(Instant.now(), "system", "Valencia#1");
//			Address destination = new Address(Instant.now(), "system", "Madrid#3");
//			originV0 = origin.addInitialVersion("Poligono Manises");
//			AddressVersion destinationV0 = destination.addInitialVersion("Poligono Alcobendas");
//			session.persist(origin);
//			session.persist(destination);
//			session.flush();

//			session.persist(shipment);
//			session.persist(shipmentChildG);
//			session.flush();

			shipment = new Shipment(Instant.now(), "system", Instant.now().plus(Duration.ofDays(3)), "abcd123",
				null, null);
//			ShipmentChildG shipmentChildG = new ShipmentChildG(Instant.now(), "system", Instant.now().plus(Duration.ofDays(3)), "abcd789",
//				null, null);
			tx = session.beginTransaction();

//			session.persist(shipmentChildG);
//			session.flush();
//			id = shipment.getId();
//			idChildG = shipmentChildG.getId();

			Address origin = new Address(Instant.now(), "system", "Valencia#1");
			Address destination = new Address(Instant.now(), "system", "Madrid#3");
			originV0 = origin.addInitialVersion("Poligono Manises");
			AddressVersion destinationV0 = destination.addInitialVersion("Poligono Alcobendas");
			session.persist(origin);
			session.persist(destination);
			shipment.setOrigin(originV0);
			shipment.setDestination(destinationV0);
			session.persist(shipment);
			session.flush();


//			shipment.setShipmentChildG(shipmentChildG);
//			session.persist(shipment);
//			session.flush();
			id = shipment.getId();
		} finally {
			if (tx != null) {
				tx.commit();
				tx = null;
			}
		}
		session.close();
//
//
//		// child
//		openSession();
//		try {
//			// child
//			ShipmentChild shipmentChild = new ShipmentChild(Instant.now(), "system",
//				Instant.now().plus(Duration.ofDays(3)), "abcd456",
//				null, null);
//			tx = session.beginTransaction();
//			session.persist(shipmentChild);
//			session.flush();
//			idChild = shipmentChild.getId();
//
//			Address originChild = new Address(Instant.now(), "system", "Valencia#1");
//			Address destinationChild = new Address(Instant.now(), "system", "Madrid#3");
//			AddressVersion originV0Child = originChild.addInitialVersion("Poligono Manises");
//			AddressVersion destinationV0Child = destinationChild.addInitialVersion("Poligono Alcobendas");
//			session.persist(originChild);
//			session.persist(destinationChild);
//			session.flush();
//
//			shipmentChild.setOrigin(originV0Child);
//			shipmentChild.setDestination(destinationV0Child);
//			session.merge(shipmentChild);
//			session.persist(shipmentChild);
//			session.flush();
////			shipmentChild.setParentObj(shipment);
//			session.merge(shipmentChild);
//			session.persist(shipmentChild);
//			session.flush();
//		} finally {
//			if (tx != null) {
//				tx.commit();
//				tx = null;
//			}
//		}
//		session.close();

		Shipment shipmentResult;
		//--- delete ---
		openSession();
		try {
			tx = session.beginTransaction();

//			ShipmentA a = session.load(ShipmentA.class, idA);
//			ShipmentB b = session.load(ShipmentB.class, idB);
//
//
//
//			a.setShipmentB(b);
//			session.update(a);
//			session.flush();
			shipmentResult = session.get(Shipment.class, id);
			AddressVersion addressVersion = session.get(AddressVersion.class, originV0);

//
//			ShipmentChild shipmentChildResult = session.get(ShipmentChild.class, idChild);
//			shipmentChildResult.setParentObj(shipmentResult);
//			session.merge(shipmentChildResult);
//			session.persist(shipmentChildResult);
//			ShipmentChildG shipmentChildG = session.load(ShipmentChildG.class, idChildG);
//			session.delete(shipmentChildG);



//			AddressVersion addressVersion = session.get(AddressVersion.class);
//			AddressVersion addressVersion = session.load(AddressVersion.class, originV0);

//			shipment.setOrigin(addressVersion);

			// #### Behavior observed ####
			// - If origin/destination are not lazy properties, it seems to work.
			// - If origin/destination are lazy properties and they're initialized with
			// `Hibernate.initialize`, cast-class exception happens:
			// class org.hibernate.envers.bugs.hhh13760.AddressVersion cannot be cast to
			// class java.lang.Long
			// - If origin/destination are lazy properties and they're not initialized when
			// the update
			// happens, PropertyAccessException happens:
			// java.lang.IllegalArgumentException: Can not set long field
			// org.hibernate.envers.bugs.hhh13760.BaseDomainEntity.id to
			// org.hibernate.envers.bugs.hhh13760.AddressVersion

			// (all failures only happen with envers enabled, of course)

//			Hibernate.initialize(shipmentResult.getOrigin());
//			Hibernate.initialize(shipmentResult.getDestination());
//			shipmentResult.setClosed(true);
//			session.delete(shipmentResult);
//			session.flush();


			session.remove(addressVersion);
//			Address origin = new Address(Instant.now(), "system", "Valencia#12");
//			AddressVersion addressVersion1 = origin.addInitialVersion("Poligono Manises2");
//			shipmentResult.setOrigin(addressVersion1);
//			session.persist(origin);
//			session.persist(shipmentResult);


			session.flush();
			session.clear();

//			session.flush();

//			session.delete(origin);


//			session.update(shipmentResult);
//			session.flush();
		} finally {
			if (tx != null) {
				tx.commit();
				tx = null;
			}
		}
		session.close();
		// -- end deletion --

		openSession();
		try {
			tx = session.beginTransaction();

//			ShipmentA a = session.load(ShipmentA.class, idA);
//			ShipmentB b = session.load(ShipmentB.class, idB);
//
//
//
//			a.setShipmentB(b);
//			session.update(a);
//			session.flush();
//			Shipment shipmentResult = session.get(Shipment.class, id);
//			session.remove(shipmentResult);
//			session.flush();
//			AddressVersion addressVersion = session.get(AddressVersion.class, originV0.getDescription());
//
//			ShipmentChild shipmentChildResult = session.get(ShipmentChild.class, idChild);
//			shipmentChildResult.setParentObj(shipmentResult);
//			session.merge(shipmentChildResult);
//			session.persist(shipmentChildResult);
//			ShipmentChildG shipmentChildG = session.load(ShipmentChildG.class, idChildG);
//			session.delete(shipmentChildG);



//			AddressVersion addressVersion = session.get(AddressVersion.class);
//			AddressVersion addressVersion = session.load(AddressVersion.class, originV0);

//			shipment.setOrigin(addressVersion);

			// #### Behavior observed ####
			// - If origin/destination are not lazy properties, it seems to work.
			// - If origin/destination are lazy properties and they're initialized with
			// `Hibernate.initialize`, cast-class exception happens:
			// class org.hibernate.envers.bugs.hhh13760.AddressVersion cannot be cast to
			// class java.lang.Long
			// - If origin/destination are lazy properties and they're not initialized when
			// the update
			// happens, PropertyAccessException happens:
			// java.lang.IllegalArgumentException: Can not set long field
			// org.hibernate.envers.bugs.hhh13760.BaseDomainEntity.id to
			// org.hibernate.envers.bugs.hhh13760.AddressVersion
			
			// (all failures only happen with envers enabled, of course)
			
//			Hibernate.initialize(shipmentResult.getOrigin());
//			Hibernate.initialize(shipmentResult.getDestination());
//			shipmentResult.setClosed(true);
//			session.merge(shipmentResult);
//			session.flush();

		} finally {
			if (tx != null) {
				tx.commit();
				tx = null;
			}
		}
		session.close();
	}
}
