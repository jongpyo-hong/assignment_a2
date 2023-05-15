package models.emp;

import oracle.jdbc.proxy.annotation.Pre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmpDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public long insert(Emp emp) { // prepareStatement
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String sql = "INSERT INTO EMP2 (EMPNO, ENAME, JOB) VALUES (EMP2_SEQ.nextval, ?, ?)";
        jdbcTemplate.update(con -> {
            PreparedStatement pstmt = con.prepareStatement(sql, new String[]{"EMPNO"});
            pstmt.setString(1, emp.getENAME());
            pstmt.setString(2, emp.getJOB());

            return pstmt;
        }, keyHolder);

        Number key = keyHolder.getKey();
        long EMPNO = key.longValue();

        return EMPNO;
    }

    public long update(Emp emp) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String sql = "UPDATE EMP2 SET ENAME=?, JOB=? WHERE EMPNO=?";
        jdbcTemplate.update(con -> {
            PreparedStatement pstmt = con.prepareStatement(sql, new String[]{"EMPNO"});

            pstmt.setString(1, emp.getENAME());
            pstmt.setString(2, emp.getJOB());
            pstmt.setLong(3, emp.getEMPNO());

            return pstmt;
        }, keyHolder);

        Number key = keyHolder.getKey();
        long EMPNO = key.longValue();

        return EMPNO;
    }

        public Emp delete(Emp emp) {
        String sql = "DELETE FROM EMP2 WHERE EMPNO=?";
        jdbcTemplate.update(con -> {
            PreparedStatement pstmt = con.prepareStatement(sql);

            pstmt.setLong(1, emp.getEMPNO());
            return pstmt;
        });

        return emp;
    }

    public Emp get(int EMPNO) {
            String sql = "SELECT * FROM EMP2 WHERE EMPNO=?";
            Emp emp = jdbcTemplate.queryForObject(sql,this::mapper, EMPNO);

            return emp;
    }

    private Emp mapper(ResultSet rs, int i) throws SQLException {
        Emp emp = new Emp();
        emp.setEMPNO(rs.getLong("EMPNO"));
        emp.setENAME(rs.getString("ENAME"));
        emp.setJOB(rs.getString("JOB"));
        return emp;
    }

    /** 강사님 솔루션 delete()
     public boolean delete(long EMPNO) {
     String sql = "DELETE FROM EMP2 WHERE EMPNO=?";
     int affectedRows = jdbcTemplate.update(sql, EMPNO);
     return affectedRows > 0;*/

    /** 강사님 솔루션 get()
     public Emp get(long EMPNO) {
     try {
     String sql = "SELECT * FROM EMP2 WHERE EMPNO=?";
     Emp emp = jdbcTemplate.queryForObject(sql, this::mapper, EMPNO);

     return emp;
     } catch (Exception e) {
     return null;
     }
     }*/
}
