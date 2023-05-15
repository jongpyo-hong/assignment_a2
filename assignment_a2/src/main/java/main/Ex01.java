package main;

import configs.AppCtx;
import models.emp.Emp;
import models.emp.EmpDao;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Ex01 {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppCtx.class);

        EmpDao empDao = ctx.getBean(EmpDao.class);

        Emp emp = new Emp();

        /** 입력 */
        /**emp.setENAME("직원");
        emp.setJOB("STAFF");
        long EMPNO = empDao.insert(emp);
        System.out.println(EMPNO);*/

        /** 수정 */
        /**emp.setENAME("직원");
        emp.setJOB("STAFF");
        emp.setEMPNO(6);*/

        /** 삭제 */
        /**emp.setEMPNO(10);
        Emp EMPNO = empDao.delete(emp);
        System.out.println(EMPNO);*/

        /** 조회 */
        emp = empDao.get(11);
        System.out.println(emp);


        ctx.close();
    }
}
