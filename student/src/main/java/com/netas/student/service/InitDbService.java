package com.netas.student.service;

import com.netas.student.entity.Student;
import com.netas.student.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class InitDbService {

    @Autowired
    private StudentRepository studentRepository;

    /**
     * When program run insert 3 records to database
     */
    @PostConstruct
    private void init() {

        {
            Student student = new Student();
            student.setName("Ali");
            student.setCity("Ankara");
            student.setDescription("description");
            student.setDistrict("Çankaya");
            student.setSurname("Kaya");
            student.setMobilePhoneNumber("(535) 165-5522");
            studentRepository.save(student);
        }
        {
            Student student = new Student();
            student.setName("Veli");
            student.setCity("Ankara");
            student.setDescription("description");
            student.setDistrict("Çankaya");
            student.setSurname("Celik");
            student.setMobilePhoneNumber("(535) 165-5522");
            studentRepository.save(student);
        }
        {
            Student student = new Student();
            student.setName("Fatma");
            student.setCity("Ankara");
            student.setDescription("description");
            student.setDistrict("Çankaya");
            student.setSurname("Kara");
            student.setMobilePhoneNumber("(535) 165-5522");
            studentRepository.save(student);
        }

    }

}
	

