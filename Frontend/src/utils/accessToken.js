import { setting } from '@/config/setting';
const { tokenTableName } = setting;
import Cookies from 'js-cookie';
export function getAccessToken() {
  return Cookies.get(tokenTableName);
}

export function setAccessToken(accessToken) {
  return Cookies.set(tokenTableName, accessToken);
}

export function removeAccessToken() {

  document.cookie = "token=; expires=Thu, 01 Jan 1970 00:00:00 GMT";

  return Cookies.remove(tokenTableName);
}
