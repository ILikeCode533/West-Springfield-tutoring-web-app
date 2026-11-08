package org.tutoringclub.service;

import org.springframework.stereotype.Service;
import org.tutoringclub.repository.TutoringRequestRepository;
import org.tutoringclub.repository.UserRepository;
import org.tutoringclub.repository.SubjectRepository;
import java.util.UUID;
import org.tutoringclub.domain.TutoringRequest;
import org.tutoringclub.domain.User;
import org.tutoringclub.domain.Subject;

@Service
public class TutoringRequestService {

    private final TutoringRequestRepository requestRepository;
    private final UserRepository userRepository;
    private final SubjectRepository subjectRepository;

    public TutoringRequestService(
            TutoringRequestRepository requestRepository,
            UserRepository userRepository,
            SubjectRepository subjectRepository) {

        this.requestRepository = requestRepository;
        this.userRepository = userRepository;
        this.subjectRepository = subjectRepository;
    }
    public TutoringRequest submitRequest(UUID studentId, UUID subjectId){
        User student = userRepository.findById(studentId).orElseThrow(() -> new IllegalArgumentException("Student not found"));
        Subject subject = subjectRepository.findById(subjectId).orElseThrow(() -> new IllegalArgumentException("Subject not found"));
        TutoringRequest request = new TutoringRequest();
        request.setStudent(student);
        request.setSubject(subject);
        
        return requestRepository.save(request);
        
    }
}