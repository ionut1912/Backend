package licenta.backend.helpers;

import java.util.Date;

public interface RoomDetails {
    String getname();

    String getroomtype();

    String getroomdetails();

    int getroomid();

    Date getcheckin();

    Date getcheckout();
}
