package com.bjtu.questionPlatform.utils.verifyCodeUtils;

import com.bjtu.questionPlatform.utils.exceptionHandler.exception.DefinitionException;
import com.bjtu.questionPlatform.utils.exceptionHandler.exception.ErrorEnum;
import com.bjtu.questionPlatform.utils.redis.JedisInstance;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

import java.util.Random;

/**
 * @program: questionPlatform_back_end
 * @description: verify code tools
 * @author: CodingLiOOT
 * @create: 2021-03-28 16:28
 * @version: 1.0
 **/
@Slf4j
@Component
public class VerifyCodeUtils {
    private Jedis jedis = JedisInstance.getInstance().getResource();

    public String generateCode(String key) {
        StringBuilder str = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            str.append(random.nextInt(10));
        }
        String code = str.toString();
        jedis.setex(key, 300, code);
        return code;
    }

    public boolean verifyCode(String key, String value) {
        if (!value.equals(jedis.get(key))) {
            throw new DefinitionException(ErrorEnum.ERROR_VERIFY_CODE);
        }
        jedis.del(key);
        return true;
    }

}
