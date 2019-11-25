package com.mycompany.testtask.usersList;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mycompany.testtask.POJO.User;
import com.mycompany.testtask.R;
import com.mycompany.testtask.usersDetails.DetailUserActivity;

import java.util.List;

public class ListUserFragment extends Fragment implements ListUserContract {

    ListUserAdapter adapter;
    private OnFragmentInteractionListener mListener;

    interface OnFragmentInteractionListener {
        void onFragmentInteraction(User user);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_list, container, false);

        Toolbar toolbar = view.findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.toolbarTitle);

        ListUserPresenter presenter = new ListUserPresenter(this, this.getContext());
        initRecyclerView(view);

        if (isConnectingToInternet()) {
            presenter.getUserList();
        } else {
            presenter.getUserListDB();
        }
        return view;
    }

    private void initRecyclerView(View view) {
        RecyclerView recyclerView = view.findViewById(R.id.customRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        ListUserAdapter.OnUserClickListener onUserClickListener = new ListUserAdapter.OnUserClickListener() {

            @Override
            public void onUserClick(User user) {
                updateDetail(user);
            }
        };

        adapter = new ListUserAdapter(onUserClickListener);
        recyclerView.setAdapter(adapter);
    }

    private void updateDetail(User user) {
        Intent intent = new Intent(getContext(), DetailUserActivity.class);
        intent.putExtra("user", user);
//        startActivity(intent);
        mListener.onFragmentInteraction(user);
    }


    @Override
    public void showInfo(List<User> userList) {
        adapter.setItems(userList);
    }

    @Override
    public void showError(String error) {
        Log.e("LOG ERROR ", error);
        Toast.makeText(getContext(), "Error:" + error, Toast.LENGTH_SHORT).show();
    }

    private boolean isConnectingToInternet() {
        ConnectivityManager cm = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        assert cm != null;
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mListener = (OnFragmentInteractionListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " должен реализовывать интерфейс OnFragmentInteractionListener");
        }
    }
}
