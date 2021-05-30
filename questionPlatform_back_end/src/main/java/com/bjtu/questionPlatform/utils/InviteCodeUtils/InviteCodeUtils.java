package com.bjtu.questionPlatform.utils.InviteCodeUtils;

import com.bjtu.questionPlatform.utils.exceptionHandler.exception.DefinitionException;
import com.bjtu.questionPlatform.utils.exceptionHandler.exception.ErrorEnum;
import com.bjtu.questionPlatform.utils.redis.JedisInstance;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

import java.util.Random;

@Slf4j
@Component
public class InviteCodeUtils {
    private final Jedis jedis = JedisInstance.getInstance().getResource();

    public String setCode(String expertName) { // 根据专家姓名生成随机数
        StringBuilder str = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            str.append(random.nextInt(10));
        }
        String code = str.toString();
        jedis.setex(expertName, 21600, code); // 存储6个小时
        return code;
    }

    public boolean verifyCode(String expertName, String value) { // 将用户名和验证码进行对比
        if (!value.equals(jedis.get(expertName))) {
            throw new DefinitionException(ErrorEnum.ERROR_VERIFY_CODE);
        }
        jedis.del(expertName);
        return true;
    }

}
