package steve.vokhe.krisztapp;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

public class Example {
    private int mIconResource;
    private String mTitle;
    private final int mDifficultyResource;
    private final int mSpiceResource;
    private final int mOwnerResource;
    private boolean mIsactive;
    private final int mOriginalImage;

    public Example(int iconResource, String title, int difficultyResource, int spiceResource, int ownerResource, boolean isactive){
        mIconResource = iconResource;
        mTitle = title;
        mDifficultyResource = difficultyResource;
        mSpiceResource = spiceResource;
        mOwnerResource = ownerResource;
        mIsactive = isactive;
        mOriginalImage = mIconResource;
    }

    public void changeTitle(String text){
        mTitle = text;
    }

    public void changeIcon(int resource){
        mIconResource=resource;
    }

    public void changeBack(){
        mIconResource=mOriginalImage;
    }

    public int getIconResource(){
        return mIconResource;
    }

    public String getTitle (){
        return mTitle;
    }

    public int getDifficultyResource(){
        return mDifficultyResource;
    }

    public int getSpiceResource(){
        return mSpiceResource;
    }

    public int getOwnerResource(){
        return mOwnerResource;
    }

    public boolean getActive(){
        return mIsactive;
    }

    public boolean setActive(boolean status){
        mIsactive=status;
        return mIsactive;
    }


}
