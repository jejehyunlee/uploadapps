package com.apollo.uploadapps.credit.repository;

import com.apollo.uploadapps.credit.entity.Credit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditRepository extends JpaRepository<Credit, Long> {
}
