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

  /**
   * 查看列表接口，发送当前用户名，返回列表报告
   * @param {Object} p [发送的参数：当前用户名]
   * @returns {Promise<unknown>}
   */
  static p_getList=p=>post('/file/getList',p);

  static p_testUpload=p=>post('/file/upload',p);

  /**
   * 邀请码接口，发送专家邀请码到后端
   * @param {Object} p [发送的参数]
   * @returns {Promise<unknown>}
   */
  static p_Invitation=p=>post('/user/invitation',p);

  /**
   * 指标列表接口，获取所有指标
   * @returns {Promise<unknown>}
   */
  static g_getJClassList=p=>get('judgement/getJClassList',p);

  /**
   * 添加指标接口，将前端的新指标数据发送到后端
   * @param {Object} p [发送的参数]
   * @returns {Promise<unknown>}
   */
  static p_getOneJudgement=p=>post('judgement/getOneJudgement',p);


  /**
   * 指标列表接口，获取所有指标
   * @returns {Promise<unknown>}
   */
  static g_getJudgementList=p=>get('file/getJudgementList',p);

  /**
   * 添加指标接口，将前端的新指标数据发送到后端
   * @param {Object} p [发送的参数]
   * @returns {Promise<unknown>}
   */
  static p_newJudgement=p=>post('file/newJudgement',p);

  /**
   * 分配指标接口，发送报告id和指标id，返回刷新后的报告列表
   * @param {Object} p [发送的参数]
   * @returns {Promise<unknown>}
   */
  static p_allocateJudgement=p=>post('file/p_allocateJudgement',p);
}
