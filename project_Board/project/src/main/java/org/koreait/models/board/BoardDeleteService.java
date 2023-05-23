package org.koreait.models.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardDeleteService {
    @Autowired
    private BoardDao boardDao;

    public Long delete(Long id) {

        return (long) boardDao.delete(id);
    }
}
