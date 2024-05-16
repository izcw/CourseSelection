import request from '@/utils/request.js';
export const GetListByEnrollmentId = (params) => {
    return request({
      url: '/EnrollmentStudentServlet?action=GetListByEnrollmentId',
      method: 'get',
      params:params,
    });
  };