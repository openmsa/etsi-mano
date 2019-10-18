package com.ubiqube.etsi.mano.jpa.wf;

import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.ubiqube.etsi.mano.dao.wf.Task;
import com.ubiqube.etsi.mano.repository.CrudRepository;

@Repository
public interface TaskRepository extends CrudRepository<Task> {

	Task findById(UUID uuid);
}
