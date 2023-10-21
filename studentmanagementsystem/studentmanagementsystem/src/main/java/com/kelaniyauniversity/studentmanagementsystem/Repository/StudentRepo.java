package com.kelaniyauniversity.studentmanagementsystem.Repository;

import com.kelaniyauniversity.studentmanagementsystem.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StudentRepo extends JpaRepository<Student,Integer> {
    @Query(value = "SELECT * FROM STUDENT WHERE STU_ID =?1 AND STU_ADDRESS=?2",nativeQuery = true)
    Student getStudentbyIdAndAddress(int stuId,String stuAddress);
}