package Models.Chat;

import com.stfalcon.chatkit.commons.models.IUser;

public class ChatAuthor implements IUser {

    private String authorID;
    private String authorName;
    private String authorAvatarUrl;


    public ChatAuthor(String authorID, String authorName, String authorAvatarUrl) {
        this.authorID = authorID;
        this.authorName = authorName;
        this.authorAvatarUrl = authorAvatarUrl;
    }

    @Override
    public String getId() {
        return authorID;
    }

    @Override
    public String getName() {
        return authorName;
    }

    @Override
    public String getAvatar() {
        return authorAvatarUrl;
    }
}

