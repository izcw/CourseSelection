import { getUserInfo, login } from '@/api/user';
import { getAccessToken, removeAccessToken, setAccessToken } from '@/utils/accessToken';
import { getCookie } from '@/utils/index';
import { setting } from '@/config/setting';
const { title, tokenName } = setting;
import { resetRouter } from '@/router';


import i18n from '@/locales';
const { global } = i18n;

import { ElMessage, ElNotification } from 'element-plus';

const state = {
  accessToken: getCookie("token"),
  username: '',
  avatar: '',
  permissions: [],
  userType: getCookie("userType"),
  info: {}
};

const getters = {
  accessToken: (state) => state.accessToken,
  username: (state) => state.username,
  avatar: (state) => state.avatar,
  permissions: (state) => state.permissions,
  userType: (state) => state.userType,
  info: (state) => state.info
};

const mutations = {
  setAccessToken(state, accessToken) {
    state.accessToken = accessToken;
    setAccessToken(accessToken);
  },
  setUsername(state, username) {
    state.username = username;
  },
  setAvatar(state, avatar) {
    state.avatar = avatar;
  },
  setUserType(state, userType) {
    state.userType = userType;
  },
  setPermissions(state, permissions) {
    state.permissions = permissions;
  },
  setUserInfo(state, userInfo) {
    state.info = userInfo;
  }
};

const actions = {
  setPermissions({ commit }, permissions) {
    commit('setPermissions', permissions);
  },
  async login({ commit }, userInfo) {
    const { data } = await login(userInfo);
    const accessToken = data[tokenName];
    if (accessToken) {
      commit('setAccessToken', accessToken);
      const hour = new Date().getHours();
      const thisTime =
        hour < 8
          ? global.t('sayHi.early')
          : hour <= 11
          ? global.t('sayHi.morning')
          : hour <= 13
          ? global.t('sayHi.noon')
          : hour < 18
          ? global.t('sayHi.afternoon')
          : global.t('sayHi.evening');
      ElNotification({
        title: `${thisTime}！`,
        message: `${global.t('notice.msg')}${title}!`,
        type: 'success',
      });
    } else {
      ElMessage.error(`登录接口异常，未正确返回${tokenName}...`);
    }
  },
  async getUserInfo({ commit, state }) {
    const { data } = await getUserInfo(state.accessToken);
    if (!data) {
      ElMessage.error('验证失败，请重新登录...');
      return false;
    }
    let { permissions, username, avatar } = data;
    // console.log(data);
    if (permissions && username && Array.isArray(permissions)) {
      commit('setPermissions', permissions);
      commit('setUsername', username);
      commit('setAvatar', avatar);
      return ['admin'];
    } else {
      ElMessage.error('用户信息接口异常');
      return false;
    }
  },
  async logout({ dispatch }) {
    // await logout(state.accessToken);
    await dispatch('resetAccessToken');
    await resetRouter();
  },
  resetAccessToken({ commit }) {
    commit('setPermissions', []);
    commit('setAccessToken', '');
    commit('setUserInfo', {}); // 重置用户信息
    removeAccessToken();
  },
};

export default { state, getters, mutations, actions };