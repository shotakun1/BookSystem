import { createRouter, createWebHistory } from 'vue-router'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/home',
      name: 'home',
      component: () => import('../views/Home.vue'),
      meta:{
        title:'home page'
      }
    },
    {
      path: '/login',
      name: 'login',
      // route level code-splitting
      // this generates a separate chunk (About.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      component: () => import('../views/Login.vue'),
      meta:{
        title:'login page'
      }
    },
    {
      path: '/register',
      name: 'register',
      component: () => import('../views/Register.vue'),
      meta:{
        title:'register page'
      }
    }
  ]
})

router.beforeEach((to, from, next) => {
  /* 路由发生变化修改页面title */
  if (to.meta.title) {
    document.title = to.meta.title
  }
  if (to.path === '/login'||to.path === '/register'){
    document.querySelector('body')
        .setAttribute('style',
            'background-image: linear-gradient(to right , #7A88FF, #7AFFAF);' +
            'margin: 0')
  } else if (to.path === '/home'){
    document.querySelector('body')
        .setAttribute('style',
            'background-image: linear-gradient(270deg, #56ab2f, #a8e063);' +
            'background-size: cover;' +
            'background-attachment: fixed;' +
            'margin: 0')
  } else{
    document.querySelector('body')
        .removeAttribute('style')
  }
  next()
})


export default router
