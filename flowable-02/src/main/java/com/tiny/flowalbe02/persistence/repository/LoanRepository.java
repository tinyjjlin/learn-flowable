package com.tiny.flowalbe02.persistence.repository;

import com.tiny.flowalbe02.persistence.entity.LoanRequest;
import org.springframework.data.jpa.repository.JpaRepository;



/**
 * @author tiny lin
 * @date 2019/8/5
 */

public interface LoanRepository  extends JpaRepository<LoanRequest, Long> {
    LoanRequest findByUsername(String username);
}
