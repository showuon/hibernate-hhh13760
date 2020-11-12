package org.hibernate.envers.bugs;

import java.time.Duration;
import java.time.Instant;

import org.hibernate.Transaction;
import org.hibernate.envers.bugs.hhh13760.Address;
import org.hibernate.envers.bugs.hhh13760.AddressVersion;
import org.hibernate.envers.bugs.hhh13760.Shipment;
import org.junit.Test;

public class HHH13760TestCase extends AbstractEnversTestCase {

	@Override
	protected Class[] getAnnotatedClasses() {
		return new Class[] {Shipment.class, Address.class, AddressVersion.class };
	}

	@Test
	public void hhh13760Test() {

		openSession();
		long id, idChild, idChildG, idA, idB, idC, idOrigin;
		Transaction tx = null;
		AddressVersion originV0;
		Shipment shipment;

		try {
			shipment = new Shipment(Instant.now(), "system", Instant.now().plus(Duration.ofDays(3)), "abcd123",
				null, null);
			tx = session.beginTransaction();
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

			id = shipment.getId();
		} finally {
			if (tx != null) {
				tx.commit();
				tx = null;
			}
		}
		session.close();

		//--- delete ---
		openSession();
		try {
			tx = session.beginTransaction();
			AddressVersion addressVersion = session.get(AddressVersion.class, originV0);

			session.remove(addressVersion);
			session.flush();
			session.clear();
		} finally {
			if (tx != null) {
				tx.commit();
				tx = null;
			}
		}
		session.close();
		// -- end deletion --

	}
}
