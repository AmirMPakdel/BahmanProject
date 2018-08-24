package Models.Chat;

import com.stfalcon.chatkit.commons.models.IMessage;
import com.stfalcon.chatkit.commons.models.IUser;

import java.util.Date;

public class ChatMessage implements IMessage {
    private String messageID;
    private String messageText;
    private ChatAuthor messageAuthor;
    private Date messageDate;

    public ChatMessage(String messageID, String messageText, ChatAuthor messageAuthor) {
        this.messageID = messageID;
        this.messageText = messageText;
        this.messageAuthor = messageAuthor;
        messageDate = new Date();
    }

    @Override
    public String getId() {
        return messageID;
    }

    @Override
    public String getText() {
        return messageText;
    }

    @Override
    public IUser getUser() {
        return messageAuthor;
    }

    @Override
    public Date getCreatedAt() {
        return messageDate;
    }
}
