package algonquin.cst2335.wang0874;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ChatMessageDAO {

    @Insert
    public long insertMessage(ChatMessage m);

    @Query("Select * from ChatMessage")
    public List<ChatMessage> getAllMessages();

    //@Query("Select * from ChatMessage where id = :thisId")
    //public ChatMessage getMessagesById(int thisId);

    @Delete
    public void deleteMessage(ChatMessage m);
}


