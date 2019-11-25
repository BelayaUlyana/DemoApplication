package com.mycompany.testtask.usersList;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.mycompany.testtask.POJO.User;
import com.mycompany.testtask.R;

import java.util.List;

public class ListUserAdapter extends RecyclerView.Adapter<ListUserAdapter.UserViewHolder> {

    private List<User> userList;
    private OnUserClickListener onUserClickListener;
    private static final String IMG_URL = "https://avatars.io/twitter/";

    public interface OnUserClickListener {
        void onUserClick(User user);
    }

    ListUserAdapter(OnUserClickListener onUserClickListener) {
        this.onUserClickListener = onUserClickListener;
    }


    @NonNull
    @Override
    public ListUserAdapter.UserViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.custom_row, viewGroup, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder userViewHolder, int position) {
        User user = userList.get(position);
        userViewHolder.bind(user);
    }

    void setItems(List<User> userList) {
        this.userList = userList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (userList != null) {
            return userList.size();
        }
        return 0;
    }

    class UserViewHolder extends RecyclerView.ViewHolder {

        private final ImageView imgView;
        private final TextView textViewName, textViewDescription, textViewInfo;

        UserViewHolder(@NonNull View itemView) {
            super(itemView);

            imgView = itemView.findViewById(R.id.image);
            textViewName = itemView.findViewById(R.id.name);
            textViewDescription = itemView.findViewById(R.id.description);
            textViewInfo = itemView.findViewById(R.id.info);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    User user = userList.get(getLayoutPosition());
                    onUserClickListener.onUserClick(user);
                }
            });

        }

        void bind(User user) {
            textViewName.setText(user.getName());
            textViewDescription.setText(user.getEmail());
            textViewInfo.setText(user.getCompany().getCatchPhrase());

            Glide.with(imgView.getContext())
                    .load(IMG_URL.concat(user.getId().toString()))
                    .apply(RequestOptions.bitmapTransform(new RoundedCorners(250)))
                    .placeholder(R.drawable.progress_animation)
                    .error(R.drawable.user)
                    .into(imgView);
        }
    }
}
