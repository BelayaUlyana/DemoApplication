package com.mycompany.testtask.usersList;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.mycompany.testtask.POJO.User;
import com.mycompany.testtask.R;

import java.util.List;

public class ListUsersAdapter extends RecyclerView.Adapter<ListUsersAdapter.UserViewHolder> {

    private List<User> userList;
    private OnUserClickListener onUserClickListener;

    public interface OnUserClickListener {
        void onUserClick(User user);
    }

    ListUsersAdapter(OnUserClickListener onUserClickListener) {
        this.onUserClickListener = onUserClickListener;
    }


    @NonNull
    @Override
    public ListUsersAdapter.UserViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
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
            String imageUrl = "https://avatars.io/twitter/";
            textViewName.setText(user.getName());
            textViewDescription.setText(user.getEmail());
            textViewInfo.setText(user.getCompany().getCatchPhrase());

            Glide.with(imgView.getContext())
                    .load(imageUrl.concat(user.getId().toString()))
                    .apply(RequestOptions.bitmapTransform(new RoundedCorners(250)))
                    .placeholder(R.drawable.progress_animation)
                    .error(R.drawable.user)
                    .into(imgView);
        }
    }
}
