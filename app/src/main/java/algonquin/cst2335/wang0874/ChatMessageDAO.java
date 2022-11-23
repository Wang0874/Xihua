package algonquin.cst2335.wang0874;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;



//object that performs CRUD operation
@Dao
public interface ChatMessageDAO {

    @Insert
    long insertMessage(ChatMessage m);
    //this matches the @Entity class name
    @Query("Select * from ChatMessage")

    List<ChatMessage> getAllMessages();

    @Delete
    void deleteMessage(ChatMessage m);
}



