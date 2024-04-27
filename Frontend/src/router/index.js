import { createRouter, createWebHashHistory } from 'vue-router';
import Layout from '@/layouts/index.vue';
import i18n from '@/locales';
const { global } = i18n;
export const constantRoutes = [
  // {
  //   path: '/login',
  //   name: 'Login',
  //   component: () => import('@/views/login/index.vue'),
  //   meta: {
  //     title: '登录',
  //   },
  //   hidden: true,
  // },
  {
    path: '/401',
    name: '401',
    component: () => import('@/views/errorPage/401.vue'),
    hidden: true,
  },
  {
    path: '/404',
    name: '404',
    component: () => import('@/views/errorPage/404.vue'),
    hidden: true,
  },
];

export const asyncRoutes = [
  {
    path: '/',
    component: Layout,
    redirect: '/index',
    name: 'Root',
    children: [
      {
        path: '/index',
        name: 'Index',
        component: () => import('../views/index/index.vue'),
        meta: {
          title: global.t('route.home'),
          icon: 'icon-home',
          affix: true,
          noKeepAlive: true,
        },
      },
    ],
  },
  {
    path: '/student',
    component: Layout,
    name: 'student',
    role:'student',
    meta: { title: global.t('route.student'), icon: 'icon-people'},
    children: [
      {
        path: '/personalStudent',
        name: 'PersonalStudent',
        component: () => import('@/views/student/index.vue'),
        meta: {
          title: global.t('route.personalStudent'),
          icon: 'icon-info',
          role:'student',
        },
      },
      {
        path: '/querySchedule',
        name: 'querySchedule',
        component: () => import('@/views/student/querySchedule.vue'),
        meta: {
          title: global.t('route.queryScheduleTxt'),
          icon: 'icon-table-file',
          role:'student',
        },
      },
      {
        path: '/queryCourse',
        name: 'queryCourse',
        component: () => import('@/views/student/queryCourse.vue'),
        meta: {
          title: global.t('route.querycourseTxt'),
          icon: 'icon-check-correct',
          role:'student',
        },
      }
    ],
  },
  {
    //选课
    path: '/choose',
    component: Layout,
    name: 'Choose',
    role:'student',
    meta: { title: global.t('route.choose'), icon: 'icon-full-selection' },
    children: [
      {
        path: '/courseinfo',
        name: 'courseinfo',
        component: () => import('@/views/choose/index.vue'),
        meta: {
          title: global.t('route.courseinfoTxt'),
          icon: 'icon-eyes',
          role:'student'
        },
      },
      {
        path: '/sinceChoose',
        name: 'sinceChoose',
        component: () => import('@/views/choose/sinceChoose.vue'),
        meta: {
          title: global.t('route.sinceChooseTxt'),
          icon: 'icon-like',
          role:'student'
        },
      }
      
    ],
  },
  {
    //课程管理
    path: '/lessonadmin',
    component: Layout,
    name: 'lessonadmin',
    role:'admin',
    children: [
      {
        path: '/lessonadmin',
        component: () => import('@/views/admin/lessonadmin/index.vue'),
        name: 'lessonadmin',
        meta: { title: global.t('route.lessonadminTxt'), icon: 'icon-app-switch',role:'admin' },
      },
    ],
  },
  {
    //选课管理
    path: '/courseadmin',
    component: Layout,
    name: 'courseadmin',
    role:'admin', 
    children: [
      {
        path: '/courseadmin',
        component: () => import('@/views/admin/courseadmin/index.vue'),
        name: 'courseadmin',
        meta: { title: global.t('route.courseadminTxt'), icon: 'icon-full-selection' ,role:'admin'},
      },
    ],
  },
  {
    //班级管理
    path: '/classadmin',
    component: Layout,
    name: 'classadmin',
    role:'admin',
    children: [
      {
        path: '/classadmin',
        component: () => import('@/views/admin/classadmin/index.vue'),
        name: 'classadmin',
        meta: { title: global.t('route.classadminTxt'), icon: 'icon-school',role:'admin'},
      },
    ],
  },
  {
    //学生管理
    path: '/studentadmin',
    component: Layout,
    name: 'studentadmin',
    role:'admin',
    children: [
      {
        path: '/studentadmin',
        component: () => import('@/views/admin/studentadmin/index.vue'),
        name: 'studentadmin',
        meta: { title: global.t('route.studentadminTxt'), icon: 'icon-peoples' ,role:'admin'},
      },
    ],
  },
  {
    //教师管理
    path: '/teacheradmin',
    component: Layout,
    name: 'teacheradmin',
    role:'admin',
    children: [
      {
        path: '/teacheradmin',
        component: () => import('@/views/admin/teacheradmin/index.vue'),
        name: 'teacheradmin',
        meta: { title: global.t('route.teacheradminTxt'), icon: 'icon-people-bottom-card',role:'admin' },
      },
    ],
  },
  {
    path: '/comp',
    component: Layout,
    name: 'Comp',
    meta: { title: global.t('route.components'), icon: 'icon-code' },
    children: [
      {
        path: '/element',
        name: 'ElementComp',
        component: () => import('@/views/element/index.vue'),
        meta: {
          title: global.t('route.eleComponents'),
          icon: 'icon-code',
        },
      },
      {
        path: '/iconPark',
        name: 'IconPark',
        component: () => import('@/views/icon/index.vue'),
        meta: {
          title: global.t('route.icons'),
          icon: 'icon-like',
        },
      },
      {
        path: '/chart',
        name: 'Chart',
        component: () => import('@/views/echarts/index.vue'),
        meta: {
          title: global.t('route.charts'),
          icon: 'icon-chart-line',
        },
        children: [
          {
            path: '/line',
            name: 'Line',
            component: () => import('@/views/echarts/line.vue'),
            meta: {
              title: global.t('route.lineChart'),
            },
          },
          {
            path: '/bar',
            name: 'Bar',
            component: () => import('@/views/echarts/bar.vue'),
            meta: {
              title: global.t('route.barChart'),
            },
          },
          {
            path: '/otherChart',
            name: 'OtherChart',
            component: () => import('@/views/echarts/other.vue'),
            meta: {
              title: global.t('route.mixedChart'),
            },
          },
        ],
      },
    ],
  },
  {
    path: '/errorPage',
    name: 'ErrorPage',
    component: Layout,
    meta: {
      title: global.t('route.errorPages'),
      icon: 'icon-link-cloud-faild',
    },
    children: [
      {
        path: '/404Page',
        name: '404Page',
        component: () => import('@/views/errorPage/404.vue'),
        meta: {
          title: global.t('route.page404'),
          icon: 'icon-link-cloud-faild',
        },
      },
      {
        path: '/401Page',
        name: '401Page',
        component: () => import('@/views/errorPage/401.vue'),
        meta: {
          title: global.t('route.page401'),
          icon: 'icon-link-interrupt',
        },
      },
    ],
  },
  {
    
      path: '/404Page',
      name: '404Page',
      component: () => import('@/views/errorPage/404.vue'),
      meta: {
        title: global.t('route.page404'),
        icon: 'icon-link-cloud-faild',
      },
    
  },
  { path: "/:pathMatch(.*)", redirect: "/404Page", name: "notMatch", hidden: true },
  // {
  //   path: '*',
  //   redirect: '/404',
  //   hidden: true,
  // },
];

const router = createRouter({
  history: createWebHashHistory(),
  routes: constantRoutes,
});

// reset router
export function resetRouter() {
  router.getRoutes().forEach((route) => {
    const { name } = route;
    if (name) {
      router.hasRoute(name) && router.removeRoute(name);
    }
  });
}

export default router;
