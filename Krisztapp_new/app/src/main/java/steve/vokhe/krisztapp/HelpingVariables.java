package steve.vokhe.krisztapp;

public class HelpingVariables {
    private boolean mIsActionMode=false;

    public HelpingVariables(boolean isActionMode){
        mIsActionMode = isActionMode;
    }

    public boolean getIsActionMode(){
        return mIsActionMode;
    }

    public boolean setIsActionMode(boolean t){
        mIsActionMode=t;
        return mIsActionMode;
    }
}
