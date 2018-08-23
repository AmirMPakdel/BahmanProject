package Gameplay;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.blackcoin.packdel.bahmanproject.R;
import com.stfalcon.chatkit.commons.ImageLoader;
import com.stfalcon.chatkit.messages.MessagesList;
import com.stfalcon.chatkit.messages.MessagesListAdapter;

import Models.Chat.ChatAuthor;
import Models.Chat.ChatMessage;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentChat extends Fragment {


    private boolean isChatInitialized = false;
    private MessagesList chatList; // chat view
    private ImageButton btn_send;   // button to send messages
    private EditText et_message; // edit text for chat input
    private ChatAuthor self;        // the current user chat info
    private ChatAuthor opponent;    // opponent chat info

    private MessagesListAdapter<ChatMessage> chatAdapter;
    private ImageLoader imageLoader = (imageView, url) -> {
        //todo implement this
        /*
         * url : this is the opponent image url or what ever the server sends and we store it on
         *       ChatAuthor.authorAvatarUrl.
         *       Note that this is all automatic. each ChatMessage has a ChatAuthor. the opponent profile
         *       image url must be set in there and its automatically gets passed down here. now we should get the image
         *       from url and store it in imageView. ( the url can be the image in base64 String or whatever format that
         *       server sends for us)
         *
         *
         *       for now we just simply put android icon as opponent profile
         */

        imageView.setImageResource(R.drawable.android_logo);
    };


    public FragmentChat() {
        // Required empty public constructor
    }


    public void initChatView(ChatAuthor self, ChatAuthor opponent) {
        isChatInitialized = true;
        this.opponent = opponent;
        this.self = self;


        chatAdapter = new MessagesListAdapter<>(this.self.getId(), imageLoader);

        //todo implement these listeners if you want
        chatAdapter.setOnMessageClickListener(message -> {
        });
        chatAdapter.setOnMessageLongClickListener(message -> {
        });

        chatList.setAdapter(chatAdapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_chat, container, false);

        initViews(rootView);


        return rootView;
    }

    private void initViews(View rootView) {
        chatList = rootView.findViewById(R.id.messagesList);
        btn_send = rootView.findViewById(R.id.chat_button_send);
        et_message = rootView.findViewById(R.id.et_chat_input);
    }




}
