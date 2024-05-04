import request from '@/utils/request.js';

export const getList = (params) => {
  return request({
    url: '/TeacherServlet?action=GetList',
    method: 'get',
    params:params,

  });
};
