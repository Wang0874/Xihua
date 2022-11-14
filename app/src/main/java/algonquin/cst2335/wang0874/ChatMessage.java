package algonquin.cst2335.wang0874;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class ChatMessage {

    @ColumnInfo(name="Message")
    public String message;

    @ColumnInfo(name="TimeSent")
    public String timeSent;

    @ColumnInfo(name="SendOrReceive")
    public int sendOrReceive;

    @PrimaryKey(autoGenerate=true)
    public int id;

    public ChatMessage(){ }

    public ChatMessage(String message, String timeSent, int sentOrReceive) {
        this.message = message;
        this.timeSent = timeSent;
        this.sendOrReceive = sentOrReceive;
    }

    public String getMessage() {
        return message;
    }

    public String getTimeSent() {
        return timeSent;
    }

    public int getSentOrReceive() {
        return sendOrReceive;
    }

    public void setId(int id) { this.id = id; }
    public int getId() { return id; }
}
