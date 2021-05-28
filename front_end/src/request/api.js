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
   * 指标类列表接口，获取所有指标类
   * @returns {Promise<unknown>}
   */
  static g_getJClassList=p=>post('judgement/getJClassList',p);

  /**
   * 查看某一指标类接口，查看一个指标类
   * @param {Object} p [发送的参数]
   * @returns {Promise<unknown>}
   */
  static p_getOneJudgement=p=>post('judgement/getOneJudgement',p);

  /**
   * 添加指标类接口，将前端的新指标类数据发送到后端
   * @param {Object} p [发送的参数]
   * @returns {Promise<unknown>}
   */
  static p_newJudgement=p=>post('judgement/newJudgement',p);

  /**
   * 获取所有报告列表接口，返回报告列表
   * @returns {Promise<unknown>}
   */
  static p_getAllReportList= ()=>post('judgement/getAllReportList',null);

  /**
   * 分配指标接口，发送报告id和指标id，返回刷新后的报告列表
   * @param {Object} p [发送的参数]
   * @returns {Promise<unknown>}
   */

  static p_allocateJudgement=p=>post('judgement/allocateJudgement',p);

  /**
   * 专家列表接口，获取专家列表
   * @returns {Promise<unknown>}
   */
  static g_getExpertList=()=>post('judgement/getExpertList',null);

  /**

   * 邀请专家接口，发送被邀请专家姓名、报告编号、关键词、名称
   * @param {Object} p []
   * @returns {Promise | Promise<unknown>}
   */
  static p_inviteExpert=p=>post('Company/inviteExpert',p);

  /**

   * 专家分配接口，发送报告关键词，获取分配专家
   * @param {Object} p [发送的参数：报告编号、关键词、名称、创建日期]
   * @returns {Promise<unknown>}
   */
  static p_createExpert=p=>post('Company/createExpert',p);


  /**
   * 待打分列表接口
   * @returns {Promise | Promise<unknown>}
   */
  static g_getReportList=p=>post('Expert/getReportList',p);

}
