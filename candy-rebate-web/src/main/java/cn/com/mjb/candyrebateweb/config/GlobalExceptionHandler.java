
package cn.com.mjb.candyrebateweb.config;


import cn.com.mjb.candyrebatecore.exception.CoreBusinessException;
import cn.com.mjb.candyrebatecore.module.dto.GlobalExceptionLogDto;
import cn.com.mjb.candyrebatecore.module.dto.MessageBox;
import cn.com.mjb.candyrebatecore.module.enums.ErrorCodeEnum;
import cn.com.mjb.candyrebateweb.dao.CrExceptionLogDao;
import cn.com.mjb.candyrebateweb.module.domain.CrExceptionLog;
import com.xiaoleilu.hutool.util.BeanUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.task.TaskExecutor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.annotation.Resource;

/**
 * 全局的的异常拦截器
 *
 * @author paascloud.net @gmail.com
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @Resource
    private TaskExecutor taskExecutor;

    @Value("${spring.profiles.active}")
    String profile;

    @Value("${spring.application.name}")
    String applicationName;

    @Resource
    private CrExceptionLogDao crExceptionLogDao;

    /**
     * 参数非法异常.
     *
     * @param e the e
     * @return the wrapper
     */
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public MessageBox illegalArgumentException(IllegalArgumentException e) {
        log.error("参数非法异常={}", e.getMessage(), e);
        return MessageBox.failed(ErrorCodeEnum.GL99990500);
    }

    /**
     * 业务异常.
     *
     * @param e the e
     * @return the wrapper
     */
    @ExceptionHandler(CoreBusinessException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public MessageBox businessException(CoreBusinessException e) {
        log.error("业务异常={}", e.getMessage(), e);
        return MessageBox.failed(e.getCode(), e.getMessage());
    }


    /**
     * 全局异常.
     *
     * @param e the e
     * @return the wrapper
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public MessageBox exception(Exception e) {
        log.info("保存全局异常信息 ex={}", e.getMessage(), e);
        taskExecutor.execute(() -> {
            GlobalExceptionLogDto globalExceptionLogDto = new GlobalExceptionLogDto().getGlobalExceptionLogDto(e, profile, applicationName);
            CrExceptionLog crExceptionLog = new CrExceptionLog();
            BeanUtil.copyProperties(globalExceptionLogDto, crExceptionLog);
            crExceptionLogDao.insert(crExceptionLog);
        });
        return MessageBox.failed(ErrorCodeEnum.GL99990500);
    }
}
