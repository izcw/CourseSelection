import request from '@/utils/request.js';

// 获取数据
export const getStudentList = (params) => {
  return request({
    url: '/StudentServlet?action=query',
    method: 'get',
    params:params,
  });
};

export const GetStudentCountByClassId = (params) => {
  return request({
    url: '/StudentServlet?action=GetStudentCountByClassId',
    method: 'get',
    params:params,
  });
};

// 添加
export const getAddStudentList = (data) => {
    return request({
      url: '/StudentServlet?action=add',
      method: 'post',
      data
    });
  };

  // 删除多项
  export const getDeleteMultipleStudentList = (data) => {
    return request({
      url: '/StudentServlet?action=deletes',
      method: 'post',
      data
    });
  };
  
// 编辑
  export const getEditorStudentList = (data) => {
    return request({
      url: '/StudentServlet?action=editor',
      method: 'post',
      data
    });
  };
  