package org.hibernate.envers.bugs;

import java.time.Duration;
import java.time.Instant;

import org.hibernate.Transaction;
import org.hibernate.envers.bugs.hhh13760.Address;
import org.hibernate.envers.bugs.hhh13760.AddressVersion;
import org.hibernate.envers.bugs.hhh13760.BaseDomainEntityVersion;
import org.hibernate.envers.bugs.hhh13760.ChildAddressVersion;
import org.hibernate.envers.bugs.hhh13760.Shipment;
import org.junit.Test;

public class HHH13760TestCase extends AbstractEnversTestCase {

	@Override
	protected Class[] getAnnotatedClasses() {
		return new Class[] {Shipment.class, Address.class, AddressVersion.class, ChildAddressVersion.class };
	}

	@Test
	public void hhh13760Test() {

		openSession();
		long id, idChild, idChildG, idA, idB, idC, idOrigin;
		Transaction tx = null;
		AddressVersion originV0, destinationV0;
		Shipment shipment;

		try {
			shipment = new Shipment(Instant.now(), "system", Instant.now().plus(Duration.ofDays(3)), "abcd123",
				null, null);
			tx = session.beginTransaction();
			Address origin = new Address(Instant.now(), "system", "Valencia#1");
			Address destination = new Address(Instant.now(), "system", "Madrid#3");
			originV0 = origin.addInitialVersion("Poligono Manises");
			destinationV0 = destination.addInitialVersion("Poligono Alcobendas");
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

			Shipment shipment1 = session.get(Shipment.class, id);
			// Cast the addressversion to the childAddressVersion, and delete the child one, so the cache for
			// the addressVersion will not be there, and entityNotFound exception will be thrown while envers audit
			ChildAddressVersion childAddressVersion = session.get(ChildAddressVersion.class, originV0);

			session.remove(shipment1);
			session.remove(childAddressVersion);

			session.flush();
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
