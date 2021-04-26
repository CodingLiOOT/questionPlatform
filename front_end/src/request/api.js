import {get,post,uploadFile} from './http'


export default class api {

  /**
   * 登录接口，发送用户信息到后端查验用户信息
   * @param {Object} p [发送的参数]
   * @returns {Promise<unknown>}
   */
  static p_Login=p=>post('/user/login',p);

  /**
   * 注册接口，发送用户信息到后端注册
   * @param {Object} p [发送的参数]
   * @returns {Promise<unknown>}
   */
  static p_Register=p=>post('/user/register',p);

  /**
   * 测试接口，测试token拦截
   * @param p
   * @returns {Promise<unknown>}
   */
  static g_getAllUser=p=>get('/user/getAllUser',p);

  /**
   * 邮箱验证码接口，发送邮箱到后端
   * @param {Object} p [发送的参数：邮箱验证码]
   * @returns {Promise<unknown>}
   */
  static p_SendCode=p=>post('/user/sendVerifyCode',p);

  /**
   * 获取报告详情接口，发送报告关键词，打分情况到前端
   * @param {Object} p [发送的参数：报告编号]
   * @returns {Promise<unknown>}
   */
  static p_getReportDetail=p=>post('/file/getReport',p);

  static p_testUpload=p=>uploadFile('/file/upload',p);
}
