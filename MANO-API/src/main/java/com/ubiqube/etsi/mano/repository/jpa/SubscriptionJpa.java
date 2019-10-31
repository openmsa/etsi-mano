package com.ubiqube.etsi.mano.repository.jpa;

import java.io.InputStream;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.model.vnf.sol005.SubscriptionObject;
import com.ubiqube.etsi.mano.repository.SubscriptionRepository;

@Profile("RDBMS")
@Service
public class SubscriptionJpa implements SubscriptionRepository {

	@Override
	public SubscriptionObject get(final String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(final String id) {
		// TODO Auto-generated method stub

	}

	@Override
	public SubscriptionObject save(final SubscriptionObject entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SubscriptionObject> query(final String filter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void storeObject(@NotNull final String _id, @NotNull final String _filename, final Object _object) {
		// TODO Auto-generated method stub

	}

	@Override
	public <T, U extends Class> T loadObject(@NotNull final String _id, @NotNull final String _filename, final U t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void storeBinary(@NotNull final String _id, @NotNull final String _filename, final InputStream _stream) {
		// TODO Auto-generated method stub

	}

	@Override
	public byte[] getBinary(@NotNull final String _id, @NotNull final String _filename) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public byte[] getBinary(@NotNull final String _id, @NotNull final String _filename, final int min, final Integer max) {
		// TODO Auto-generated method stub
		return null;
	}

}
