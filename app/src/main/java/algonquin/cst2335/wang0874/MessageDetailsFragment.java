package algonquin.cst2335.wang0874;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import algonquin.cst2335.wang0874.databinding.DetailsLayoutBinding;

public class MessageDetailsFragment extends Fragment {

    private ChatMessage selected;
    DetailsLayoutBinding binding;

    public MessageDetailsFragment(){

    }
    public MessageDetailsFragment(ChatMessage m){
        selected = m;

    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        binding = DetailsLayoutBinding.inflate(inflater);
        binding.textView.setText(selected.getMessage());
        binding.timeView.setText(selected.getTimeSent());
        if (selected.isSentButton){
            binding.sendView.setText("This is a send message");
        }else{
            binding.sendView.setText("This is a receive message");
        }
        binding.idView.setText("DatabaseID is" + selected.id);
        binding.getRoot().setClickable(true);
        return binding.getRoot();

    }
}
