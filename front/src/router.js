import { createWebHistory, createRouter } from 'vue-router';
import BigFiveSurvey from '@/view/BigFiveSurvey.vue';
import CareerTest from '@/view/CareerTest.vue';
import Login from '@/view/Login.vue';
import Registration from '@/view/Registration.vue';
import { store } from './store.js';

const ifNotAuthenticated = (to, from, next) => {
  if (!store.getters.isAuthenticated) {
    next()
    return
  }
  next('/')
}

const ifAuthenticated = (to, from, next) => {
  if (store.getters.isAuthenticated) {
    next()
    return
  }
  next('/login')
}

const routes = [
  {
    path: '/',
    name: 'Big five survey',
    component: <BigFiveSurvey />,
    beforeEnter: ifAuthenticated
  },
  {
    path: '/career-test',
    name: 'Career test',
    component: <CareerTest />,
    beforeEnter: ifAuthenticated
  },
  {
    path: '/login',
    name: 'Login',
    component: <Login />,
    beforeEnter: ifNotAuthenticated
  },
  {
    path: '/registration',
    name: 'Registration',
    component: <Registration />,
    beforeEnter: ifNotAuthenticated
  }
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;