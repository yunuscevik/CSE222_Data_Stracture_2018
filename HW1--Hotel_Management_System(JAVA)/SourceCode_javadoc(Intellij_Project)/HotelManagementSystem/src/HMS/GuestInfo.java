
package HMS;

/**
 * Sisteme giris yapmis ve bir oda rezerve yapmak icin gerekli misafir bilgileri.
 * @author Yunus CEVIK
 */
public class GuestInfo{
    public int guestID;
    public String guestName;
    public String guestSurname;
    
    public int getGuestID() { return guestID; }
    public String getGuestName() { return guestName; }
    public String getGuestSurname() { return guestSurname; }
    
    public void setGuestID(int guestID) { this.guestID = guestID; }
    public void setGuestName(String guestName) { this.guestName = guestName; }
    public void setGuestSurname(String guestSurname) { this.guestSurname = guestSurname; }
}
