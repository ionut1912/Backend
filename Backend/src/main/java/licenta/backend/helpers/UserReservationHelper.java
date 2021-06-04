package licenta.backend.helpers;

import java.util.Date;

public interface UserReservationHelper {
    int getreservationid();
    String getname();
    String getemail();
    String getroomtype();
    Date getcheckin();
    Date getcheckout();
    boolean getdeleted();
    int getuserid();
    int getroomid();
}
