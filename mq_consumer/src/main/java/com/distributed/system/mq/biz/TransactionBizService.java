package com.distributed.system.mq.biz;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


/**
 * 事务业务逻辑
 *
 * @author cyc
 */
@Service
public class TransactionBizService {
    private static final Logger log = LoggerFactory.getLogger(TransactionBizService.class);
//    @Autowired
//    private UserService userService;

    public void addScoreBySyn(int score) {
        log.info("==>addScoreBySyn:" + score);
//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        userService.addScoreBySyn(score);
    }
}
