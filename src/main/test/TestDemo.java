import com.hairgroup.choose.until.JWTUtil;
import org.junit.Test;

/**
 * Created on 2020/9/24
 *
 * @author Yue Wu
 */
public class TestDemo {

    @Test
    public void test() {
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1X2lkIjo5LCJyb2xlIjowLCJpZGVudGl0eV9pZCI6NywiZXhwIjoxNjAxMDMxODE2fQ.zglu-7J6oxwKnB3B8dkpo8451HUd6r0kRbXax4yZNpo";
        Integer userID = JWTUtil.getUserID(token);
        Integer userRole = JWTUtil.getUserRole(token);
        Integer userIdentity = JWTUtil.getUserIdentity(token);

        System.out.println(userID);
        System.out.println(userRole);
        System.out.println(userIdentity);
    }
}
