import request from '@/utils/request.js';

// 获取数据
export const getTeacherList = (params) => {
  return request({
    url: '/TeacherServlet?action=query',
    method: 'get',
    params:params,
  });
};

// 添加
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
  
// 编辑
  export const getEditorTeacherList = (data) => {
    return request({
      url: '/TeacherServlet?action=editor',
      method: 'post',
      data
    });
  };