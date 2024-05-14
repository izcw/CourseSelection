import request from '@/utils/request.js';
export const getList = (params) => {
    return request({
      url: '/EnrollmentServlet?action=GetList',
      method: 'get',
      params:params,
    });
  };
  export const AddEnrollment = (data) => {
    return request({
      url: '/EnrollmentServlet?action=AddEnrollment',
      method: 'post',
      data
    });
  };

  export const UpdateEnrollment = (data) => {
    return request({
      url: '/EnrollmentServlet?action=UpdateEnrollment',
      method: 'post',
      data
    });
  };
  export const DeleteEnrollment = (data) => {
    return request({
      url: '/EnrollmentServlet?action=DeleteEnrollment',
      method: 'post',
      data
    });
  };
  export const Release = (data) => {
    return request({
      url: '/EnrollmentServlet?action=Release',
      method: 'post',
      data
    });
  };
  export const GetEnrollmentById = (params) => {
    return request({
      url: '/EnrollmentServlet?action=GetEnrollmentById',
      method: 'get',
      params:params,
  
    });
  };
  export const BindingCourse = (data) => {
    return request({
      url: '/EnrollmentServlet?action=BindingCourse',
      method: 'post',
      data
    });
  };
  export const BindingClass = (data) => {
    return request({
      url: '/EnrollmentServlet?action=BindingClass',
      method: 'post',
      data
    });
  };
  export const GetBoundCourse = (params) => {
    return request({
      url: '/EnrollmentServlet?action=GetBoundCourse',
      method: 'get',
      params:params,
  
    });
  };
  export const GetBoundClass = (params) => {
    return request({
      url: '/EnrollmentServlet?action=GetBoundClass',
      method: 'get',
      params:params,
  
    });
  };