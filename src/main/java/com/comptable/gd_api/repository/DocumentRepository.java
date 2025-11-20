package com.comptable.gd_api.repository;

import com.comptable.gd_api.entity.Document;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentRepository extends JpaRepository<Document,Long> {

}
