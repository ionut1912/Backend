package licenta.backend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
@Table(name = "roomsviewed")
public class RoomViewed {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  long roomviewedid;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "roomid")
    @JsonBackReference("roomviewed")
    private  Room roomviewed;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userid")
    @JsonBackReference("userroomviewed")
    private  User userroomviewed;

    public RoomViewed() {
    }

    public RoomViewed(Room roomviewed, User userroomviewed) {
        this.roomviewed = roomviewed;
        this.userroomviewed = userroomviewed;
    }

    public long getRoomviewedid() {
        return roomviewedid;
    }

    public void setRoomviewedid(long roomviewedid) {
        this.roomviewedid = roomviewedid;
    }

    public Room getRoomviewed() {
        return roomviewed;
    }

    public void setRoomviewed(Room roomviewed) {
        this.roomviewed = roomviewed;
    }

    public User getUserroomviewed() {
        return userroomviewed;
    }

    public void setUserroomviewed(User userroomviewed) {
        this.userroomviewed = userroomviewed;
    }
}
