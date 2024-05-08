import request from '@/utils/request.js';

// export const getList = (params) => {
//   return request({
//     url: '/TeacherServlet?action=GetList',
//     method: 'get',
//     params:params,

//   });
// };

export const getTeacherList = (params) => {
  return request({
    url: '/TeacherServlet?action=query',
    method: 'get',
    params:params,
  });
};

export const getAddTeacherList = (data) => {
    return request({
      url: '/TeacherServlet?action=add',
      method: 'post',
      data
    });
  };

  // 删除多项
  export const getDeleteMultipleTeacherList = (data) => {
    return request({
      url: '/TeacherServlet?action=deletes',
      method: 'post',
      data
    });
  };
  

  export const getEditorTeacherList = (data) => {
    return request({
      url: '/TeacherServlet?action=editor',
      method: 'post',
      data
    });
  };