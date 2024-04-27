import request from '@/utils/request.js';

export const getList = (params) => {
  return request({
    url: '/ClassServlet?action=GetList',
    method: 'get',
    params:params,

  });
};
