package algonquin.cst2335.wang0874;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import algonquin.cst2335.wang0874.databinding.DetailsLayoutBinding;

public class MessageDetailsFragment extends Fragment {
    ChatMessage selected;

    public MessageDetailsFragment(ChatMessage m) {
        selected = m;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        DetailsLayoutBinding binding = DetailsLayoutBinding.inflate(inflater);

        binding.messageText.setText("Message is: " + selected.getMessage());
        binding.timeText.setText("Time is: " + selected.getTimeSent());
        binding.isSentOrReceive.setText("Sent(1) or Receive(2) is: " + selected.getSentOrReceive());
        binding.databaseText.setText("Id is: " + selected.getId());

        return binding.getRoot();
    }
}
