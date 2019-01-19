
package HMS;


/**
 * Otelde bulunan bir oda icin gerekli data fieldler.
 * @author Yunus CEVIK
 */
public class Room {
    private static final int MAXROOM = 100;
    private int roomID;
    private char roomStatus;
    private int roomCapacity;
    private int guestID;
    private String guestName;
    private String guestSurname;

    public static int getMAXROOM() { return MAXROOM; }
    public int getRoomID() { return roomID; }
    public char getRoomStatus() { return roomStatus; }  
    public int getRoomCapacity() { return roomCapacity; } 
    public int getGuestID() { return guestID; }
    public String getGuestName() { return guestName; }
    public String getGuestSurname() { return guestSurname; } 
    
    public void setRoomID(int roomID) { this.roomID = roomID; }
    public void setRoomStatus(char roomStatus) { this.roomStatus = roomStatus; }
    public void setRoomCapacity(int roomCapacity) { this.roomCapacity = roomCapacity; }  
    public void setGuestID(int guestID) { this.guestID = guestID; }
    public void setGuestName(String guestName) { this.guestName = guestName; }
    public void setGuestSurname(String guestSurname) { this.guestSurname = guestSurname; }
}
