package com.tiny.flowalbe02.service;

import com.tiny.flowalbe02.persistence.entity.LoanRequest;
import com.tiny.flowalbe02.persistence.repository.LoanRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author tiny lin
 * @date 2019/8/5
 */
@Slf4j
@Service
public class LoanFormService {

    @Autowired
    private LoanRepository loanRepository;

    public LoanRequest getLoanRequest(String username ){
        return loanRepository.findByUsername(username);
    }

    public void createDemoUsers() {
        if (loanRepository.findAll().size() == 0) {
            loanRepository.save(new LoanRequest("jjlin", "jj", "lin", new Date()));
            loanRepository.save(new LoanRequest("tinyjjlin", "tiny", "lin", new Date()));
        }
    }

}
