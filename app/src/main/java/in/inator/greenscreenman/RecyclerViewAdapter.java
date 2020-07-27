package in.inator.greenscreenman;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

    private static final String TAG = "RecyclerViewAdapter";
    private Context mContext;
    private ArrayList<String>mUsername;
    private ArrayList<String>mComments;
    private ArrayList<Integer>mImages;

    public RecyclerViewAdapter(Context mContext, ArrayList<String> mUsername, ArrayList<String> mComments, ArrayList<Integer> mImages) {
        this.mContext = mContext;
        this.mUsername = mUsername;
        this.mComments = mComments;
        this.mImages = mImages;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tv_chat,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.image.setImageResource(mImages.get(position));
        holder.username.setText(mUsername.get(position));
        holder.comment.setText(mComments.get(position));
        String k = String.valueOf(mUsername.size());
    }

    @Override
    public int getItemCount() {
        return mUsername.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView username,comment;
        RelativeLayout commentLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.iv_user);
            username = itemView.findViewById(R.id.tv_username);
            comment = itemView.findViewById(R.id.tv_comment);
            commentLayout = itemView.findViewById(R.id.comment_layout);
        }
    }
}
