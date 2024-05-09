import request from '@/utils/request.js';
import { setting } from '@/config/setting';
const { tokenName } = setting;
export const login = async (data) => {
  return request({
    url: '/login',
    method: 'post',
    data,
  });
};

// export const getUserInfo = (accessToken) => {
//   return request({
//     url: '/userInfo',
//     method: 'get',
//     data: {
//       [tokenName]: accessToken,
//     },
//   });
// };
export const getUserInfo = (accessToken) => {
  return request({
    url: '/UserServlet?action=GetUserInfo',
    method: 'post',
    data: {
      token: accessToken,
    },
  });
};

// 获取用户数据
export const getUserInfoData = (params) => {
  return request({
    url: '/UserServlet?action=GetUserinfodata',
    method: 'post',
    params
  });
};

export const logout = () => {
  return request({
    url: '/logout',
    method: 'post',
  });
};

export const register = async () => {
  return request({
    url: '/register',
    method: 'post',
  });
};


// 修改密码
export const apiChangepassword = (data) => {
  return request({
    url: '/UserServlet?action=changepassword',
    method: 'post',
    data
  });
};