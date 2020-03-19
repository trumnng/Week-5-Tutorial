package com.example.week5tutorial;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.week5tutorial.R;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "au.edu.unsw.infs3634.beers.message";
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.rvList);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mTwoPane = findViewById(R.id.detailContainer) != null;
        CoinAdapter.RecyclerViewClickListener listener = new CoinAdapter.RecyclerViewClickListener() {
            @Override
            public void onClick (View view, int position) {
                if (mTwoPane) {
                    final FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction transaction = fragmentManager.beginTransaction();
                    Bundle arguments = new Bundle();
                    arguments.putInt("position", position);
                    Activity2Fragment fragment = new Activity2Fragment();
                    fragment.setArguments(arguments);
                    transaction.replace(R.id.detailContainer, fragment);
                    transaction.commit();
                } else {
                    launchActivity2(position);
                }
            }
        };
        mAdapter = new CoinAdapter(Coin.getCoins(), listener);
        mRecyclerView.setAdapter(mAdapter);

    }

    private void launchActivity2(int position) {
        Intent intent = new Intent(this, Activity2.class);
        intent.putExtra(EXTRA_MESSAGE, position);
        startActivity(intent);
    }
}
