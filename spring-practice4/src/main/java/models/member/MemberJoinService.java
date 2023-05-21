package models.member;

import controllers.JoinForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberJoinService {
    @Autowired
    private MemberDao memberDao;

    public void join(JoinForm joinForm) {

        memberDao.insert(joinForm);
    }
}