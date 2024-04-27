/**
 * @author hujiangjun 1217437592@qq.com
 * @description 路由控制
 */
import router from '@/router';
import store from '@/store';
import NProgress from 'nprogress';
import 'nprogress/nprogress.css';
import { getPageTitle,getCookie } from '@/utils/index';
import { setting } from '@/config/setting';
const { authentication, loginInterception, progressBar, routesWhiteList, recordRoute } = setting;

NProgress.configure({
  easing: 'ease',
  speed: 500,
  trickleSpeed: 200,
  showSpinner: false,
});
router.beforeResolve(async (to, from, next) => {
  let hasToken = getCookie("token");
  next()
  // if(to.path==='/'){
    //       next({ path: '/' });
    //     }
    // console.log(to)
    // if (to.path === '/login') {
    //   next({ path: '/' });
    //   if (progressBar) NProgress.done();
    // }
    // if (to.meta.role!=store.getters['user/userType']&&JSON.stringify(to.meta.role) != "{}" &&to.path != '/') {
     
    //   next({ path: '/' });
    // }else{
    //   next()
    // } 
  
})
// router.beforeResolve(async (to, from, next) => {
  
//   if (progressBar) NProgress.start();
  
//   let hasToken = getCookie("token");
//   if (!loginInterception) hasToken = true;
//   if (hasToken) {
//     console.log(111)
//     if(to.path==='/'){
//       next({ path: '/' });
//     }
//     if (to.path === '/login') {
//       next({ path: '/' });
//       if (progressBar) NProgress.done();
//     } else {
//       console.log(to,'to')
//       const hasPermissions =
//         store.getters['user/permissions'] && store.getters['user/permissions'].length > 0;
        
//         // to.meta.role===store.getters['user/userType']
//       if (to.meta.role===store.getters['user/userType']&&JSON.stringify(to.meta.role) != "{}") {
//         next();
//       } else {
//         return {name:"Root"}
        
//         // try {
//         //   let permissions;
//         //   if (!loginInterception) {
//         //     //settings.js loginInterception为false时，创建虚拟权限
//         //     await store.dispatch('user/setPermissions', ['admin']);
//         //     permissions = ['admin'];
//         //   } else {
//         //     permissions = await store.dispatch('user/getUserInfo');
//         //   }

//         //   let accessRoutes = [];
//         //   if (authentication === 'intelligence') {
//         //     accessRoutes = await store.dispatch('routes/setRoutes', permissions);
//         //   } else if (authentication === 'all') {
//         //     accessRoutes = await store.dispatch('routes/setAllRoutes');
//         //   }
//         //   accessRoutes.forEach((item) => {
//         //     router.addRoute(item);
//         //   });
//         //   next({ ...to, replace: true });
//         // } catch {
//         //   await store.dispatch('user/resetAccessToken');
//         //   if (progressBar) NProgress.done();
//         // }
//       }
//     }
//   } 
//   // else {
//   //   // 免登录路由
//   //   if (routesWhiteList.indexOf(to.path) !== -1) {
//   //     next();
//   //   } else {
//   //     if (recordRoute) {
//   //       next(`/login?redirect=${to.path}`);
//   //     } else {
//   //       next('/login');
//   //     }
//   //     if (progressBar) NProgress.done();
//   //   }
//   // }
//   document.title = getPageTitle(to.meta.title);
// });
router.afterEach(() => {
  if (progressBar) NProgress.done();
});



