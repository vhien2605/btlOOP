package app.service.mainService;

import app.domain.Student;
import app.repository.StudentRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;
import java.util.Optional;

public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public ObservableList<Student> getAllStudents() {
        List<Student> students = this.studentRepository.findAll();
        return FXCollections.observableList(students);
    }

    public Student findById(int studentID) {
        Optional<Student> wrapperResult = this.studentRepository.findById(studentID);
        return wrapperResult.orElse(null);
    }
}
