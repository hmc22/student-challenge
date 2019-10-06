package com.netas.student.service;

import com.google.gson.Gson;
import com.netas.student.entity.Student;
import com.netas.student.repository.StudentRepository;
import lombok.Data;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.faces.model.SelectItem;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Data
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    /**
     * @return list of all student
     */
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    /**
     * Create or Update student at Student table in database
     *
     * @param student
     * @return saved student
     */
    public Student save(Student student) {
        return studentRepository.save(student);
    }

    /**
     * Delete student from Student table in database
     *
     * @param student
     */
    public void delete(Student student) {
        studentRepository.delete(student);
    }


    /**
     * Reads cities.json file under resources folder. Convert it List
     *
     * @return List of cities
     * @throws IOException
     * @throws ParseException
     */
    public List<SelectItem> getCities() throws IOException, ParseException {

        Gson gson = new Gson();
        JSONObject object = (JSONObject) new JSONParser().parse(new FileReader(getClass().getClassLoader().getResource("cities.json").getFile()));
        Map cityMap = gson.fromJson(String.valueOf(object), Map.class);
        List<SelectItem> cities = new ArrayList<>();
        for (Long i = 0L; i < 82L; i++) {
            cities.add(new SelectItem(cityMap.get(i.toString()).toString(), cityMap.get(i.toString()).toString()));
        }
        return cities;
    }

    /**
     * Reads district.json file under resources folder. Convert it Map
     * Find districts that belongs that city
     *
     * @param city
     * @return List of Districts
     * @throws IOException
     * @throws ParseException
     */
    public List<SelectItem> getDistricts(String city) throws IOException, ParseException {
        Gson gson = new Gson();
        JSONObject object = (JSONObject) new JSONParser().parse(new FileReader(getClass().getClassLoader().getResource("district.json").getFile()));
        Map<String, ArrayList> map = gson.fromJson(String.valueOf(object), Map.class);
        List<SelectItem> districts = new ArrayList<>();
        for (int i = 0; i < map.get(city).size(); i++) {
            districts.add(new SelectItem(map.get(city).get(i).toString(), map.get(city).get(i).toString()));
        }
        return districts;
    }
}
