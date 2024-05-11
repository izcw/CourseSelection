import request from '@/utils/request.js';
export const getList = (params) => {
    return request({
      url: '/CourseServlet?action=GetList',
      method: 'get',
      params:params,
    });
  };
  export const AddCourse = (data) => {
    return request({
      url: '/CourseServlet?action=add',
      method: 'post',
      data
    });
  };
  export const UpdateCourse = (data) => {
    return request({
      url: '/CourseServlet?action=UpdateCourse',
      method: 'post',
      data
    });
  };
  export const DeleteCourse = (data) => {
    return request({
      url: '/CourseServlet?action=DeleteCourse',
      method: 'post',
      data
    });
  };
  export const GetCourseById = (params) => {
    return request({
      url: '/CourseServlet?action=GetCourseById',
      method: 'get',
      params:params,
  
    });
  };