package steve.vokhe.krisztapp;

import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import java.util.ArrayList;


public class ExampleAdapter extends RecyclerView.Adapter<ExampleAdapter.ExampleViewHolder> {
    private ArrayList<Example> mExampleList;
    private OnItemClickListener mListener;
    private OnItemLongClickListener mLongListener;
    static int x=0;

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public interface OnItemLongClickListener{

        void onItemLongClick(int position);


    }

    public void setOnItemLongClickListener(OnItemLongClickListener listener){
        mLongListener = listener;
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }

    public static class ExampleViewHolder extends RecyclerView.ViewHolder implements OnItemLongClickListener{
        public ImageView mIconImageView;
        public TextView mTextView;
        public ImageView mDifficultyImageView;
        public ImageView mSpiceImageView;
        public ImageView mOwnerImageView;

        public ExampleViewHolder(@NonNull final View itemView, final OnItemClickListener listener, final OnItemLongClickListener longListener) {
            super(itemView);

            mIconImageView = itemView.findViewById(R.id.icon_card);
            mTextView = itemView.findViewById(R.id.title_card);
            mDifficultyImageView = itemView.findViewById(R.id.difficulty_card);
            mSpiceImageView = itemView.findViewById(R.id.spice_card);
            mOwnerImageView = itemView.findViewById(R.id.owner_card);

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    MainActivity.isam=true;
                    MainActivity.counter.setVisibility(View.VISIBLE);
                    MainActivity.counter.setText(x + " elem kijelölve törlésre");
                    return true;
                }
            });




            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener !=null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            listener.onItemClick(position);
                            MainActivity.counter.setText(x + " elem kijelölve törlésre");
                        }
                    }
                }
            });
        }

        @Override
        public void onItemLongClick(int position) {
            MainActivity.isam=true;
            MainActivity.counter.setVisibility(View.VISIBLE);
            MainActivity.counter.setText(x + " elem kijelölve törlésre");
        }
    }

    public ExampleAdapter(ArrayList<Example> exampleList){
        mExampleList = exampleList;
    }

    @NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_item,parent,false);
        ExampleViewHolder evh = new ExampleViewHolder(v, mListener, mLongListener);
        return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull ExampleViewHolder holder, int position) {
        Example currentItem = mExampleList.get(position);

        holder.mIconImageView.setImageResource(currentItem.getIconResource());
        holder.mTextView.setText(currentItem.getTitle());
        holder.mDifficultyImageView.setImageResource(currentItem.getDifficultyResource());
        holder.mSpiceImageView.setImageResource(currentItem.getSpiceResource());
        holder.mOwnerImageView.setImageResource(currentItem.getOwnerResource());
    }

    @Override
    public int getItemCount() {
        return mExampleList.size();
    }
}
