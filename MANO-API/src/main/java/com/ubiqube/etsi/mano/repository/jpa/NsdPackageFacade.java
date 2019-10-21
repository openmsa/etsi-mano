package com.ubiqube.etsi.mano.repository.jpa;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ubiqube.etsi.mano.dao.mano.NsdPackage;
import com.ubiqube.etsi.mano.exception.NotFoundException;
import com.ubiqube.etsi.mano.grammar.AstBuilder;
import com.ubiqube.etsi.mano.model.nsd.sol005.NsDescriptorsNsdInfo;
import com.ubiqube.etsi.mano.model.nsd.sol005.NsDescriptorsNsdInfo.NsdUsageStateEnum;
import com.ubiqube.etsi.mano.model.nslcm.sol005.NsLcmOpOccsNsLcmOpOcc;
import com.ubiqube.etsi.mano.model.nslcm.sol005.NsLcmOpOccsNsLcmOpOcc.LcmOperationTypeEnum;
import com.ubiqube.etsi.mano.repository.ContentManager;
import com.ubiqube.etsi.mano.repository.NsdRepository;

import ma.glasnost.orika.MapperFacade;

public class NsdPackageFacade implements NsdRepository {
	private final EntityManager em;
	private final NsdPackageJpa repository;
	private final MapperFacade mapper;
	private final ContentManager contentManager;
	private final ObjectMapper jsonMapper;
	private final JpaQueryer queryer = new JpaQueryer();

	public NsdPackageFacade(final NsdPackageJpa repository, final MapperFacade mapper, final ContentManager contentManager, final ObjectMapper jsonMapper, final EntityManager _em) {
		super();
		this.repository = repository;
		this.mapper = mapper;
		this.contentManager = contentManager;
		this.jsonMapper = jsonMapper;
		em = _em;
	}

	@Override
	public NsDescriptorsNsdInfo get(final String id) {
		final Optional<NsdPackage> nsdPackage = repository.findById(UUID.fromString(id));
		return mapper.map(nsdPackage.orElseThrow(() -> new NotFoundException("NSD Package " + id + " not found.")), NsDescriptorsNsdInfo.class);
	}

	@Override
	public void delete(final String id) {
		repository.deleteById(UUID.fromString(id));
	}

	@Override
	public NsDescriptorsNsdInfo save(final NsDescriptorsNsdInfo entity) {
		final NsdPackage nsd = mapper.map(entity, NsdPackage.class);
		repository.save(nsd);
		return mapper.map(nsd, NsDescriptorsNsdInfo.class);
	}

	@Override
	public List<NsDescriptorsNsdInfo> query(final String filter) {
		final AstBuilder astBuilder = new AstBuilder(filter);
		final CriteriaBuilder builder = em.getCriteriaBuilder();
		final CriteriaQuery<NsdPackage> q = builder.createQuery(NsdPackage.class);
		final Root<NsdPackage> root = q.from(NsdPackage.class);
		final Map<String, From<?, ?>> joins = new HashMap<>();
		joins.put("ROOT", root);
		final Join<Object, Object> jTmp = root.join("softwareImages");
		final Predicate p = queryer.getCriteria(astBuilder.getNodes(), NsdPackage.class, joins);
		q.select(root).where(p);
		final List<NsdPackage> res = em.createQuery(q).getResultList();
		return res.stream().map(x -> mapper.map(x, NsDescriptorsNsdInfo.class)).collect(Collectors.toList());
	}

	@Override
	public void storeObject(final String _id, final Object _object, final String _filename) {
		// TODO Auto-generated method stub

	}

	@Override
	public void storeBinary(final String _id, final InputStream _stream, final String _filename) {
		// TODO Auto-generated method stub

	}

	@Override
	public byte[] getBinary(final String _id, final String _filename) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public byte[] getBinary(final String _id, final String _filename, final int min, final Integer max) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void changeNsdUpdateState(final NsDescriptorsNsdInfo nsdInfo, final NsdUsageStateEnum inUse) {
		// TODO Auto-generated method stub

	}

	@Override
	public <T, U extends Class> T loadObject(@NotNull final String _id, final U t, final String _filename) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NsLcmOpOccsNsLcmOpOcc createLcmOpOccs(final String nsInstanceId, final LcmOperationTypeEnum instantiate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void attachProcessIdToLcmOpOccs(@NotNull final String lcmOpOccsId, final String processId) {
		// TODO Auto-generated method stub

	}

}
