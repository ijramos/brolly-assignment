package com.brolly.assignment.domain;

import com.brolly.assignment.domain.model.Export;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExportRepository extends CrudRepository<Export, Long> {

}
