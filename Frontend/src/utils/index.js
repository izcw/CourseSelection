/**
 * @author hu-snail 1217437592@qq.com
 * @description 常用公共工具函数
 */

import { setting } from '@/config/setting';
const { title } = setting;

export const getPageTitle = (pageTitle) => {
  if (pageTitle) {
    return `${pageTitle}-${title}`;
  }
  return `${title}`;
};
export const getCookie=(cookieName) =>{
  
  const strCookie = document.cookie
  console.log(strCookie)
  const cookieList = strCookie.split(';')
  
  for(let i = 0; i < cookieList.length; i++) {
    const arr = cookieList[i].split('=')
    if (cookieName === arr[0].trim()) {
      return arr[1]
    }
  }
  
  return ''
}