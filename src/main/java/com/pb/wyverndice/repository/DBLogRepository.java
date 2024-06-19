package com.pb.wyverndice.repository;

import com.pb.wyverndice.model.DBLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DBLogRepository extends JpaRepository<DBLog, Long> {
}
