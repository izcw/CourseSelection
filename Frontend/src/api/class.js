import request from '@/utils/request.js';

export const getList = (params) => {
  return request({
    url: '/ClassServlet?action=GetList',
    method: 'get',
    params:params,

  });
};
export const AddClass = (data) => {
  return request({
    url: '/ClassServlet?action=AddClass',
    method: 'post',
    data: data
  });
};
export const getClassInfo = (params) => {
  return request({
    url: '/ClassServlet?action=GetClassById',
    method: 'get',
    params:params,

  });
};
