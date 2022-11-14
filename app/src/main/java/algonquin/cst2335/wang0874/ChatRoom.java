package algonquin.cst2335.wang0874;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import algonquin.cst2335.wang0874.databinding.ActivityChatRoomBinding;

public class ChatRoom extends AppCompatActivity {

    ActivityChatRoomBinding binding;
    ArrayList<ChatMessage> messages;
    ChatRoomViewModel chatModel;
    RecyclerView.Adapter<MyRowHolder> myAdapter;
    ChatMessageDAO mDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityChatRoomBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        chatModel = new ViewModelProvider(this).get(ChatRoomViewModel.class);

        messages = chatModel.messages.getValue();

        MessageDatabase db = Room.databaseBuilder(getApplicationContext(), MessageDatabase.class, "MessageDatabase").build();
        mDAO = db.cmDAO();

        if(messages == null)
        {
            chatModel.messages.postValue(messages = new ArrayList<>());

            Executor thread = Executors.newSingleThreadExecutor();
            thread.execute( () -> {

                messages.addAll( mDAO.getAllMessages() );

                runOnUiThread( () ->{
                    binding.recycleView.setAdapter( myAdapter );
                });
            } );
        }

        binding.sendButton.setOnClickListener( click -> {
            SimpleDateFormat sdf = new SimpleDateFormat("EEEE, dd-MMM-yyyy hh-mm-ss a", Locale.getDefault());
            String currentDateAndTime = sdf.format(new Date());
            ChatMessage thisMessage = new ChatMessage(binding.inputText.getText().toString(), currentDateAndTime, 1);
            messages.add(thisMessage);

            Executor thread = Executors.newSingleThreadExecutor();
            thread.execute( () -> {
                mDAO.insertMessage(thisMessage);
            });

            myAdapter.notifyItemInserted(messages.size() - 1);
            binding.inputText.setText("");
        });

        binding.receiveButton.setOnClickListener( click -> {
            SimpleDateFormat sdf = new SimpleDateFormat("EEEE, dd-MMM-yyyy hh-mm-ss a", Locale.getDefault());
            String currentDateAndTime = sdf.format(new Date());
            ChatMessage thisMessage = new ChatMessage(binding.inputText.getText().toString(), currentDateAndTime, 2);
            messages.add(thisMessage);

            Executor thread = Executors.newSingleThreadExecutor();
            thread.execute( () -> {
                mDAO.insertMessage(thisMessage);
            });

            myAdapter.notifyItemInserted(messages.size() - 1);
            binding.inputText.setText("");
        });

        binding.recycleView.setLayoutManager(new LinearLayoutManager(this));

        binding.recycleView.setAdapter(myAdapter = new RecyclerView.Adapter<MyRowHolder>() {
            @NonNull
            @Override
            public MyRowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                LayoutInflater inflater = getLayoutInflater();
                int layoutID;
                if (viewType == 1) {
                    layoutID = R.layout.send_message;
                } else {
                    layoutID = R.layout.receive_message;
                }

                View loadedRow = inflater.inflate(layoutID, parent, false);
                return new MyRowHolder(loadedRow);
            }

            @Override
            public void onBindViewHolder(@NonNull MyRowHolder holder, int position) {
                //String obj = messages.get(position);

                holder.messageText.setText(messages.get(position).getMessage());
                holder.timeText.setText(messages.get(position).getTimeSent());
//                holder.setPosition(position);
            }

            @Override
            public int getItemCount() {
                return messages.size();
            }

            @Override
            public int getItemViewType(int position) {
                //return super.getItemViewType(position);
                ChatMessage thisRow = messages.get(position);

                return thisRow.getSentOrReceive();
            }
        });
    }

    class MyRowHolder extends RecyclerView.ViewHolder {
        TextView messageText;
        TextView timeText;
//        int position = -1;

        public MyRowHolder(@NonNull View itemView) {
            super(itemView);

            itemView.setOnClickListener(click -> {

                int position = getAbsoluteAdapterPosition();

                ChatMessage thisMessage = messages.get(position);

                AlertDialog.Builder builder = new AlertDialog.Builder(ChatRoom.this);

                builder.setMessage("Do you want to delete the message: " + messageText.getText())
                        .setTitle("Question:")
                        .setNegativeButton("No", (dialog, cl) -> {
                        })
                        .setPositiveButton("Yes", (dialog, cl) -> {

                            messages.remove(position);
                            myAdapter.notifyItemRemoved(position);

                            Snackbar.make(messageText, "You deleted message #" + position, Snackbar.LENGTH_LONG)
                                    .setAction("Undo", clicked -> {
                                        messages.add(position, thisMessage);
                                        myAdapter.notifyItemInserted(position);
                                        Executor thread = Executors.newSingleThreadExecutor();
                                        thread.execute( () -> {
                                            mDAO.insertMessage(thisMessage);
                                        });

                                    }).show();

                            Executor thread = Executors.newSingleThreadExecutor();
                            thread.execute( () -> {
                                mDAO.deleteMessage(thisMessage);
                            });
                            myAdapter.notifyItemRemoved(position);
                        }).create().show();
            });

            messageText = itemView.findViewById(R.id.messageText);
            timeText = itemView.findViewById(R.id.timeText);
        }

    }

}
