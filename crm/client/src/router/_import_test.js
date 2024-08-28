module.exports = file => () => import('@/views/' + file + '.vue')
/*module.exports = file => {
  return resolve => require(['@/views/' + file + '.vue'], resolve).default // vue-loaded
}*/
