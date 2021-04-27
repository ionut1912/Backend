package licenta.backend.helpers;

import java.util.Date;

public interface ReservationHelper {
    String getname();

    String getemail();

    String getroomtype();

    Date getcheckin();

    Date getcheckout();

    boolean getdeleted();

    long getuserid();
}
