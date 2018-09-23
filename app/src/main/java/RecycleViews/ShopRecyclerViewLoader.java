package RecycleViews;

public class ShopRecyclerViewLoader {

    public void load(OnComplete onComplete){


        //TODO:: check the shop version

        //TODO:: if version is not equal => download the data from server

        //TODO:: get the urls and save the Images by the Items Id name and save the path in Const.java

        //TODO:: if version is equal => load the data from database and storage device

        //TODO:: check if the image exists in storage device

        // TODO:: if not download the missing image

        onComplete.run();
    }



    public interface OnComplete{

        public void run();
    }
}
