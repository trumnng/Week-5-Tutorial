package com.example.week5tutorial;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Activity2Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Activity2Fragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private TextView name;
    private TextView symbol;
    private TextView value;
    private TextView change1h;
    private TextView change24h;
    private TextView change7d;
    private TextView marketcap;
    private TextView volume;
    private Coin mCoin;

    public Activity2Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Activity2Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Activity2Fragment newInstance(String param1, String param2) {
        Activity2Fragment fragment = new Activity2Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_activity2, container, false);

        int position = 0;
        Boolean mTwoPane = false;


        name = v.findViewById(R.id.txvName);
        symbol = v.findViewById(R.id.txvSymbol);
        value = v.findViewById(R.id.txvValue);
        change1h = v.findViewById(R.id.txvChange1h);
        change24h = v.findViewById(R.id.txvChange24h);
        change7d = v.findViewById(R.id.txvChange7d);
        marketcap = v.findViewById(R.id.txvMarketcap);
        volume = v.findViewById(R.id.txvVolume);

        if(this.getArguments() != null) {
            mTwoPane = getArguments().getBoolean("position", true);
        }
        if (mTwoPane) {
            mCoin = Coin.getCoins().get(getArguments().getInt("position"));
        } else {
            Intent intent = getActivity().getIntent();
            position = intent.getIntExtra("Position", 0);
            mCoin = Coin.getCoins().get(position);
        }

        name.setText(mCoin.getName());
        symbol.setText(mCoin.getSymbol());
        String value1 = Double.toString(mCoin.getValue());
        value.setText(value1);
        String change1h1 = Double.toString(mCoin.getChange1h());
        change1h.setText(change1h1);
        String change24h1 = Double.toString(mCoin.getChange24h());
        change24h.setText(change24h1);
        String change7d1 = Double.toString(mCoin.getChange7d());
        change7d.setText(change7d1);
        String marketcap1 = Double.toString(mCoin.getMarketcap());
        marketcap.setText(marketcap1);
        String volume1 = Double.toString(mCoin.getVolume());
        volume.setText(volume1);

        return v;
    }
}
