package org.tutoringclub.service;

import org.springframework.stereotype.Service;
import org.tutoringclub.repository.TutoringRequestRepository;
import org.tutoringclub.repository.UserRepository;
import org.tutoringclub.repository.SubjectRepository;

@Service
public class TutoringRequestService {

    private final TutoringRequestRepository requestRepository;

    public TutoringRequestService(
            TutoringRequestRepository requestRepository,
            UserRepository userRepository,
            SubjectRepository subjectRepository) {

        this.requestRepository = requestRepository;
        this.userRepository = userRepository;
        this.subjectRepository = subjectRepository;
    }
}