package com.kelaniyauniversity.studentmanagementsystem.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Component
public class StudentDTO {
    private int stuId;
    private String stuName;
    private String stuAddress;
}