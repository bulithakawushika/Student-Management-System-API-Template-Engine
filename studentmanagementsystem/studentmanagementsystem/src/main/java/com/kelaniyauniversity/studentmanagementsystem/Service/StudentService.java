package com.kelaniyauniversity.studentmanagementsystem.Service;

import com.kelaniyauniversity.studentmanagementsystem.DTO.StudentDTO;
import com.kelaniyauniversity.studentmanagementsystem.Repository.StudentRepo;
import com.kelaniyauniversity.studentmanagementsystem.entity.Student;
import com.kelaniyauniversity.studentmanagementsystem.utill.VarList;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class StudentService {
    @Autowired
    private StudentRepo studentRepo;
    @Autowired
    private ModelMapper modelMapper;
    public String saveStudent(StudentDTO studentDTO){
        if(studentRepo.existsById(studentDTO.getStuId())){
            return VarList.RSP_DUPLICATED;
        }else {
            studentRepo.save(modelMapper.map(studentDTO,Student.class));
            return VarList.RSP_SUCCESS;
        }
    }
    public String updateStudent(StudentDTO studentDTO){
        if(studentRepo.existsById(studentDTO.getStuId())){
            studentRepo.save(modelMapper.map(studentDTO,Student.class));
            return VarList.RSP_SUCCESS;
        }else {
            return VarList.RSP_FAIL;
        }
    }
    public String deleteStudent(int stuId){
        if(studentRepo.existsById(stuId)){
            studentRepo.deleteById(stuId);
            return VarList.RSP_SUCCESS;
        }else{
            return VarList.RSP_NO_DATA_FOUND;
        }

    }
    public List<StudentDTO> getstudent(){
        List<Student>allstudentlist= studentRepo.findAll();
        return modelMapper.map(allstudentlist,new TypeToken<ArrayList<StudentDTO>>(){}.getType());
    }
    public StudentDTO getStudentbyId(int stuId){
        if(studentRepo.existsById(stuId)){
            Student student = studentRepo.findById(stuId).orElse(null);
            return modelMapper.map(student,StudentDTO.class);
        }else{
            return null;
        }
    }
    public StudentDTO getStudentbyIdAndAddress(int stuId,String stuAddress){
        if(studentRepo.existsById(stuId)){
            Student student = studentRepo.getStudentbyIdAndAddress(stuId,stuAddress);
            return modelMapper.map(student,StudentDTO.class);
        }else {
            return null;
        }
    }
}
