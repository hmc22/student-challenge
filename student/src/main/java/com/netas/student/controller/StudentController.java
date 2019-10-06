package com.netas.student.controller;

import com.netas.student.entity.Student;
import com.netas.student.service.StudentService;
import lombok.Data;
import org.json.simple.parser.ParseException;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.model.SelectItem;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

@Data
@ManagedBean(name = "studentController")
@ViewScoped
public class StudentController implements Serializable {

    @ManagedProperty("#{studentService}")
    private StudentService studentService;

    private List<Student> studentList;

    private Student student = new Student();

    @PostConstruct
    public List<Student> loadStudents() throws IOException, ParseException {
        studentList = studentService.findAll();
        return studentList;
    }

    public void save() {
        studentService.save(student);
        student = new Student();
        studentList = studentService.findAll();
        FacesContext.getCurrentInstance().addMessage
                (null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Student saved!", null));
    }

    public void remove(Student student) {
        studentService.delete(student);
        studentList = studentService.findAll();
        FacesContext.getCurrentInstance().addMessage
                (null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Student removed!", null));
    }

    public void clear() {
        student = new Student();
    }

    public List<SelectItem> getCities() throws IOException, ParseException {
        List<SelectItem> cities = studentService.getCities();
        return cities;
    }

    public List<SelectItem> getDistricts() throws IOException, ParseException {
        if (student.getCity() == null || student.getCity().equals("Select")) {
            return null;
        } else {
            List<SelectItem> districts = studentService.getDistricts(student.getCity());
            return districts;
        }
    }

    public void cityChanged(AjaxBehaviorEvent event) throws IOException, ParseException {
        System.out.println(event);
        getDistricts();
        student.setDistrict(null);
    }


}
