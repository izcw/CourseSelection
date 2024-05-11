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
    url: '/ClassServlet?action=add',
    method: 'post',
    data
  });
};
export const getClassInfo = (params) => {
  return request({
    url: '/ClassServlet?action=GetClassById',
    method: 'get',
    params:params,

  });
};
export const UpdateClass = (data) => {
  return request({
    url: '/ClassServlet?action=UpdateClass',
    method: 'post',
    data
  });
};
export const DeleteClass = (data) => {
  return request({
    url: '/ClassServlet?action=DeleteClass',
    method: 'post',
    data
  });
};
export const BindingStudent = (data) => {
  return request({
    url: '/ClassServlet?action=BindingStudent',
    method: 'post',
    data
  });
};