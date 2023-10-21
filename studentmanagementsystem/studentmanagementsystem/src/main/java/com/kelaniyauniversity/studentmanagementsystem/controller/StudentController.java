package com.kelaniyauniversity.studentmanagementsystem.controller;

import com.kelaniyauniversity.studentmanagementsystem.DTO.ResponseDTO;
import com.kelaniyauniversity.studentmanagementsystem.DTO.StudentDTO;
import com.kelaniyauniversity.studentmanagementsystem.Service.StudentService;
import com.kelaniyauniversity.studentmanagementsystem.utill.VarList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/v1/student")
public class StudentController {
    @Autowired
    private StudentService studentService;
    @Autowired
    private ResponseDTO responseDTO;
    @PostMapping("/savestudent")
    public ResponseEntity saveStudent(@RequestBody StudentDTO studentDTO){
        try{
            String res=studentService.saveStudent(studentDTO);
            if(res.equals("00")){
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("success");
                responseDTO.setContent(studentDTO);
                return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);
            } else if (res.equals("06")) {
                responseDTO.setCode(VarList.RSP_DUPLICATED);
                responseDTO.setMessage("duplicated");
                responseDTO.setContent(studentDTO);
                return new ResponseEntity(responseDTO,HttpStatus.BAD_REQUEST);
            }else {
                responseDTO.setCode(VarList.RSP_ERROR);
                responseDTO.setMessage("Error");
                responseDTO.setContent(null);
                return new ResponseEntity(responseDTO,HttpStatus.BAD_REQUEST);
            }
        } catch (Exception ex){
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage("Error");
            responseDTO.setContent(null);
            return new ResponseEntity(responseDTO,HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping("/updatestudent")
    public ResponseEntity updatestudent(@RequestBody StudentDTO studentDTO){
        String res= studentService.updateStudent(studentDTO);
        try{
            if(res.equals("00")){
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("update");
                responseDTO.setContent(studentDTO);
                return new ResponseEntity(responseDTO,HttpStatus.ACCEPTED);
            }
         else if(res.equals("10")){
                responseDTO.setCode(VarList.RSP_DUPLICATED);
                responseDTO.setMessage("not found student");
                responseDTO.setContent(studentDTO);
                return new ResponseEntity(responseDTO,HttpStatus.BAD_REQUEST);
            }else {
                responseDTO.setCode(VarList.RSP_ERROR);
                responseDTO.setMessage("Error");
                responseDTO.setContent(null);
                return new ResponseEntity(responseDTO,HttpStatus.BAD_REQUEST);
            }

        }catch (Exception ex){
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage("Error");
            responseDTO.setContent(null);
            return new ResponseEntity(responseDTO,HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping("/deletestudent/{stuId}")
    public ResponseEntity deleteStudent(@PathVariable int stuId){
        String res= studentService.deleteStudent(stuId);
        try {
            if(res.equals("00")){
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("deleted");
                responseDTO.setContent(null);
                return new ResponseEntity(responseDTO,HttpStatus.ACCEPTED);
            } else if (res.equals("01")) {
                responseDTO.setCode(VarList.RSP_FAIL);
                responseDTO.setMessage("no student");
                responseDTO.setContent(null);
                return new ResponseEntity(responseDTO,HttpStatus.BAD_REQUEST);
            }else {
                responseDTO.setCode(VarList.RSP_ERROR);
                responseDTO.setMessage("Error");
                responseDTO.setContent(null);
                return new ResponseEntity(responseDTO,HttpStatus.BAD_REQUEST);
            }
        }catch (Exception ex){
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage("Error");
            responseDTO.setContent(null);
            return new ResponseEntity(responseDTO,HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/getallstudent")
    public ResponseEntity getStudent(){
        try {
            List<StudentDTO>studentDTOList=studentService.getstudent();
            responseDTO.setCode(VarList.RSP_SUCCESS);
            responseDTO.setMessage("get all student");
            responseDTO.setContent(studentDTOList);
            return new ResponseEntity(responseDTO,HttpStatus.ACCEPTED);

            }catch (Exception ex){
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage(ex.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity(responseDTO,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/searchbyid/{stuId}")
    public ResponseEntity searchbyId(@PathVariable int stuId){
        try {
            StudentDTO studentDTO = studentService.getStudentbyId(stuId);
            if(studentDTO != null){
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("success to get student");
                responseDTO.setContent(studentDTO);
                return new ResponseEntity(responseDTO,HttpStatus.ACCEPTED);
            }else {
                responseDTO.setCode(VarList.RSP_ERROR);
                responseDTO.setMessage("Error");
                responseDTO.setContent(null);
                return new ResponseEntity(responseDTO,HttpStatus.BAD_REQUEST);
            }
            }catch (Exception ex){
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage(ex.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity(responseDTO,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/searchbyidandaddress/{stuId}/{stuAddress}")
    public ResponseEntity searchbyIdAndAddress(@PathVariable int stuId,@PathVariable String stuAddress){
        try {
            StudentDTO studentDTO = studentService.getStudentbyIdAndAddress(stuId,stuAddress);
            if(studentDTO != null){
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("success in getting student");
                responseDTO.setContent(studentDTO);
                return new ResponseEntity(responseDTO,HttpStatus.ACCEPTED);
            }else{
                responseDTO.setCode(VarList.RSP_ERROR);
                responseDTO.setMessage("Error");
                responseDTO.setContent(null);
                return new ResponseEntity(responseDTO,HttpStatus.BAD_REQUEST);
            }
        }catch (Exception ex){
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage(ex.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity(responseDTO,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
