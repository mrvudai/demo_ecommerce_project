package daipv.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Slf4j
public class TestSchedule {

    @Scheduled(fixedRate = 30000) // Chạy mỗi 30 giây
    public void runHourlyTask() {
        Date date = new Date();
        log.info("test schedule " + date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds());
    }
}
