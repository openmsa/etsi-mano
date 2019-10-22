package com.ubiqube.etsi.mano.repository.jpa;

import java.io.ByteArrayInputStream;
import java.io.IOException;
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
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.validation.constraints.NotNull;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ubiqube.etsi.mano.dao.mano.VnfPackage;
import com.ubiqube.etsi.mano.exception.GenericException;
import com.ubiqube.etsi.mano.exception.NotFoundException;
import com.ubiqube.etsi.mano.grammar.AstBuilder;
import com.ubiqube.etsi.mano.jpa.VnfPackageJpa;
import com.ubiqube.etsi.mano.model.nslcm.LcmOperationStateType;
import com.ubiqube.etsi.mano.model.nslcm.sol003.LcmOperationType;
import com.ubiqube.etsi.mano.model.nslcm.sol003.VnfLcmOpOcc;
import com.ubiqube.etsi.mano.model.vnf.sol005.VnfPkgInfo;
import com.ubiqube.etsi.mano.repository.ContentManager;
import com.ubiqube.etsi.mano.repository.VnfPackageRepository;

import ma.glasnost.orika.MapperFacade;

@Service
public class VnfPackageFacade implements VnfPackageRepository {
	private final EntityManager em;
	private final VnfPackageJpa repository;
	private final MapperFacade mapper;
	private final ContentManager contentManager;
	private final ObjectMapper jsonMapper;
	private final JpaQueryer queryer;

	public VnfPackageFacade(final VnfPackageJpa _repository, final MapperFacade _orikaMapperFacade, final ContentManager _contentManager, final ObjectMapper _jsonMapper, final EntityManager _em) {
		super();
		this.repository = _repository;
		mapper = _orikaMapperFacade;
		jsonMapper = _jsonMapper;
		contentManager = _contentManager;
		em = _em;
		queryer = new JpaQueryer(_em);
	}

	@Override
	public VnfPkgInfo get(final String id) {
		final Optional<VnfPackage> vnfPackage = repository.findById(UUID.fromString(id));
		return mapper.map(vnfPackage.orElseThrow(() -> new NotFoundException("VNF Package " + id + " not found.")), VnfPkgInfo.class);
	}

	@Override
	public void delete(final String id) {
		repository.deleteById(UUID.fromString(id));
	}

	@Override
	public VnfPkgInfo save(final VnfPkgInfo entity) {
		final VnfPackage vnf = mapper.map(entity, VnfPackage.class);
		repository.save(vnf);
		final VnfPkgInfo tmp = mapper.map(vnf, VnfPkgInfo.class);
		mapper.map(tmp, entity);
		return entity;
	}

	@Override
	public List<VnfPkgInfo> query(final String filter) {
		final AstBuilder astBuilder = new AstBuilder(filter);
		final CriteriaBuilder builder = em.getCriteriaBuilder();
		final CriteriaQuery<VnfPackage> q = builder.createQuery(VnfPackage.class);
		final Root<VnfPackage> root = q.from(VnfPackage.class);
		final Map<String, From<?, ?>> joins = new HashMap<>();
		joins.put("ROOT", root);
		Join<Object, Object> jTmp = root.join("softwareImages", JoinType.LEFT);
		joins.put("softwareImages", jTmp);
		jTmp = jTmp.join("checksum");
		joins.put("checksum", jTmp);
		jTmp = root.join("additionalArtifacts", JoinType.LEFT);
		joins.put("additionalArtifacts", jTmp);
		final Predicate p = queryer.getCriteria(astBuilder.getNodes(), VnfPackage.class, joins);
		if (null == p) {
			q.select(root);
		} else {
			q.select(root).where(p);
		}
		final List<VnfPackage> res = em.createQuery(q).getResultList();
		return res.stream().map(x -> mapper.map(x, VnfPkgInfo.class)).collect(Collectors.toList());
	}

	@Override
	public void storeObject(final String _id, final String _filename, final Object _object) {
		try {
			final String str = jsonMapper.writeValueAsString(_object);
			storeBinary(_id, _filename, new ByteArrayInputStream(str.getBytes()));
		} catch (final JsonProcessingException e) {
			throw new GenericException(e);
		}
	}

	@Override
	public void storeBinary(final String _id, final String _filename, final InputStream _stream) {
		contentManager.store(_id, _filename, _stream);
	}

	@Override
	public byte[] getBinary(final String _id, final String _filename) {
		return getBinary(_id, _filename, 0, null);
	}

	@Override
	public byte[] getBinary(final String _id, final String _filename, final int min, final Integer max) {
		final InputStream os = contentManager.load(_id, _filename, 0, null);
		try {
			return IOUtils.toByteArray(os);
		} catch (final IOException e) {
			throw new GenericException(e);
		}
	}

	@Override
	public <T, U extends Class> T loadObject(@NotNull final String _id, final String _filename, final U t) {
		final byte[] content = getBinary(_id, _filename, 0, null);
		try {
			return (T) jsonMapper.readValue(content, t);
		} catch (final IOException e) {
			throw new GenericException(e);
		}
	}

	@Override
	public VnfLcmOpOcc createLcmOpOccs(final String vnfInstanceId, final LcmOperationType terminate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateState(final VnfLcmOpOcc lcmOpOccs, final LcmOperationStateType processing) {
		// TODO Auto-generated method stub

	}

	@Override
	public void attachProcessIdToLcmOpOccs(@NotNull final String id, final String processId) {
		// TODO Auto-generated method stub

	}

	protected Map<String, From<?, ?>> getJoins(final Root<VnfPackage> root) {
		final Map<String, From<?, ?>> joins = new HashMap<>();
		joins.put("ROOT", root);
		Join<Object, Object> jTmp = root.join("softwareImages", JoinType.LEFT);
		joins.put("softwareImages", jTmp);
		jTmp = jTmp.join("checksum");
		joins.put("checksum", jTmp);
		jTmp = root.join("additionalArtifacts", JoinType.LEFT);
		joins.put("additionalArtifacts", jTmp);
		return joins;
	}
}
