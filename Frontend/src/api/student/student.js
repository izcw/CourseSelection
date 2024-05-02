import request from '@/utils/request.js';

export const getStudentList = (params) => {
  return request({
    url: '/StudentServlet?action=Query',
    method: 'get',
    params:params,
  });
};


export const getAddStudentList = (params) => {
    return request({
      url: '/StudentServlet?action=Add',
      method: 'get',
      params:params,
    });
  };
  
  export const getDeleteStudentList = (params) => {
    return request({
      url: '/StudentServlet?action=Delete',
      method: 'get',
      params:params,
    });
  };
  

  export const getEditorStudentList = (params) => {
    return request({
      url: '/StudentServlet?action=Editor',
      method: 'get',
      params:params,
    });
  };
  