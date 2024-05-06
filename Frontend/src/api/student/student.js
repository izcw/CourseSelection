import request from '@/utils/request.js';

export const getStudentList = (params) => {
  return request({
    url: '/StudentServlet?action=query',
    method: 'get',
    params:params,
  });
};

export const getAddStudentList = (params) => {
    return request({
      url: '/StudentServlet?action=add',
      method: 'get',
      params:params,
    });
  };
  
  export const getDeleteStudentList = (params) => {
    return request({
      url: '/StudentServlet?action=delete',
      method: 'get',
      params:params,
    });
  };
  

  export const getEditorStudentList = (params) => {
    return request({
      url: '/StudentServlet?action=editor',
      method: 'get',
      params:params,
    });
  };
  