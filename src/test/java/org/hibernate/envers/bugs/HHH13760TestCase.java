package org.hibernate.envers.bugs;

import java.time.Duration;
import java.time.Instant;

import org.hibernate.Hibernate;
import org.hibernate.Transaction;
import org.hibernate.envers.bugs.hhh13760.Address;
import org.hibernate.envers.bugs.hhh13760.AddressVersion;
import org.hibernate.envers.bugs.hhh13760.Shipment;
import org.junit.Test;

public class HHH13760TestCase extends AbstractEnversTestCase {

	@Override
	protected Class[] getAnnotatedClasses() {
		return new Class[] { Shipment.class, Address.class, AddressVersion.class };
	}

	@Test
	public void hhh13760Test() {
		openSession();
		long id;
		Transaction tx = null;
		try {
			var shipment = new Shipment(Instant.now(), "system", Instant.now().plus(Duration.ofDays(3)), "abcd123",
					null, null);
			tx = session.beginTransaction();
			session.persist(shipment);
			session.flush();
			id = shipment.getId();

			var origin = new Address(Instant.now(), "system", "Valencia#1");
			var destination = new Address(Instant.now(), "system", "Madrid#3");
			var originV0 = origin.addInitialVersion("Poligono Manises");
			var destinationV0 = destination.addInitialVersion("Poligono Alcobendas");
			session.persist(origin);
			session.persist(destination);
			session.flush();

			shipment.setOrigin(originV0);
			shipment.setDestination(destinationV0);
			session.merge(shipment);
			session.flush();
		} finally {
			if (tx != null) {
				tx.commit();
				tx = null;
			}
		}
		session.close();

		openSession();
		try {
			tx = session.beginTransaction();
			var shipment = session.get(Shipment.class, id);
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
			
			Hibernate.initialize(shipment.getOrigin());
			Hibernate.initialize(shipment.getDestination());
			shipment.setClosed(true);
			session.merge(shipment);
			session.flush();

		} finally {
			if (tx != null) {
				tx.commit();
				tx = null;
			}
		}
		session.close();
	}
}
