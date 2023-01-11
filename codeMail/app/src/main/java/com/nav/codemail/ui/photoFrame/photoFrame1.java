package com.nav.codemail.ui.photoFrame;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.nav.codemail.R;


public class photoFrame1 extends Fragment {
    String [] names = {"Bill", "Harry", "Rohit", "SRK", "Virat" };
    ImageView pic;
    ImageButton prev_button, next_button;
    TextView text;
    int currentImage=0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_photo_frame, container, false);
        prev_button = (ImageButton) view.findViewById(R.id.prev_button);
        prev_button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                text = getView().findViewById(R.id.info);
                String idX = "pic" + currentImage;
                int x = getResources().getIdentifier(idX, "id", getActivity().getPackageName());
                Log.d("package name", getActivity().getPackageName());
                pic = getView().findViewById(x);
                pic.setAlpha(0f);

                currentImage = (2 + currentImage - 1) % 2;
                String idY = "pic" + currentImage;
                int y = getResources().getIdentifier(idY, "id", getActivity().getPackageName());
                pic = getView().findViewById(y);
                pic.setAlpha(1f);
                text.setText(names[currentImage]);
            }
        });
        next_button = (ImageButton) view.findViewById(R.id.next_button);
        next_button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                text = getView().findViewById(R.id.info);
                String idX = "pic" + currentImage;
                int x = getResources().getIdentifier(idX, "id", getActivity().getPackageName());
                Log.i("x", idX);
                Log.d("package name", getActivity().getPackageName());
                pic = getView().findViewById(x);
                pic.setAlpha(0f);

                currentImage = (currentImage + 1) % 2;
                String idY = "pic" + currentImage;
                int y = getResources().getIdentifier(idY, "id", getActivity().getPackageName());
                Log.i("y", idY);
                pic = getView().findViewById(y);
                pic.setAlpha(1f);
                text.setText(names[currentImage]);
            }
        });
        return view;
    }


}