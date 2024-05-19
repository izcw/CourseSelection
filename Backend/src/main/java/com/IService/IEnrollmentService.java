package com.IService;

import com.Pojo.Course;
import com.Pojo.Enrollment;

import java.util.List;

public interface IEnrollmentService extends IBaseService<Enrollment> {
    List<Enrollment> GetEnrollmentList(Enrollment e);
    String AddEnrollment(Enrollment e);
    String UpdateEnrollment(Enrollment e);
    boolean DeleteEnrollment(int id);
    Enrollment GetEnrollmentInfo(int id);
    String Release(int id);

    List<Enrollment> GetstudentEnrollmentList(String Id);
}
