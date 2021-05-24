import { createWebHistory, createRouter } from 'vue-router';
import BigFiveSurvey from '@/view/BigFiveSurvey.vue';
import CareerTest from '@/view/CareerTest.vue';

const routes = [
  {
    path: '/',
    name: 'Big five survey',
    component: <BigFiveSurvey />,
  },
  {
    path: '/career-test',
    name: 'Career test',
    component: <CareerTest />,
  },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;