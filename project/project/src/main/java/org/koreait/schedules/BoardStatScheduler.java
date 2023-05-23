package org.koreait.schedules;

import lombok.extern.java.Log;
import org.koreait.models.board.BoardDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Log
@Component
public class BoardStatScheduler {
    @Autowired
    private BoardDao boardDao;
    @Scheduled(cron="0 0 1 * * *")
    public void process() {
        boardDao.processStat();
    }
}