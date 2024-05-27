import request from '@/utils/request.js';

// 查询课程类别
export const getEnrollmentList = (params) => {
  return request({
    url: '/StudentEnrollmentServlet?action=query',
    method: 'get',
    params:params,
  });
};

// 查询可选课程
export const getviewcoursetList = (params) => {
    return request({
      url: '/StudentEnrollmentServlet?action=querycourse',
      method: 'get',
      params:params,
    });
  };

//   选择课程
  export const getSelectStudentcourses = (data) => {
    return request({
      url: '/EnrollmentStudentServlet?action=selectstudent',
      method: 'post',
      data,
    });
  };
  
  // 查询学生选的课程(用于判断该学生是否已选这门课)
  export const getSelectcoursesList = (params) => {
    return request({
      url: '/EnrollmentStudentServlet?action=mycourse',
      method: 'get',
      params:params,
    });
  };

  //   查询我的选课
  export const getViewclassscheduleList = (params) => {
    return request({
      url: '/CourseServlet?action=myclassscourse',
      method: 'get',
      params:params,
    });
  };