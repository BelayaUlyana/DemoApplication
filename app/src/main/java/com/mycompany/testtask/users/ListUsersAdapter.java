package com.mycompany.testtask.users;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mycompany.testtask.POJO.User;
import com.mycompany.testtask.R;

import java.util.List;

public class ListUsersAdapter extends RecyclerView.Adapter<ListUsersAdapter.UserViewHolder> {

    Context context;
    private List<User> userList;

    ListUsersAdapter(Context context, List<User> usersList) {
        this.context = context;
        this.userList = usersList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ListUsersAdapter.UserViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater mInflater = LayoutInflater.from(viewGroup.getContext());
        View view = mInflater.inflate(R.layout.custom_row, viewGroup, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder userViewHolder, int position) {
        String imageUrl = "https://avatars.io/twitter/";
        User user = userList.get(position);
        userViewHolder.textViewName.setText(user.getName());
        userViewHolder.textViewDescription.setText(user.getEmail());
        userViewHolder.textViewInfo.setText(user.getCompany().getCatchPhrase());

        Glide.with(userViewHolder.imgView.getContext())
                .load(imageUrl.concat(userList.get(position).getId().toString()))
                .placeholder(R.drawable.progress_animation)
                .error(R.drawable.user)
                .into(userViewHolder.imgView);
    }

    @Override
    public int getItemCount() {
        if (userList != null) {
            return userList.size();
        }
        return 0;
    }

    class UserViewHolder extends RecyclerView.ViewHolder {

        final View mView;
        private final ImageView imgView;
        private final TextView textViewName, textViewDescription, textViewInfo;

        UserViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;

            imgView = mView.findViewById(R.id.image);
            textViewName = mView.findViewById(R.id.name);
            textViewDescription = mView.findViewById(R.id.description);
            textViewInfo = mView.findViewById(R.id.info);

        }
    }
}
