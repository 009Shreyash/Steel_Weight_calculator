package com.example.admin.steelweightcalculator.Activity;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.admin.steelweightcalculator.R;
import com.thekhaeng.pushdownanim.PushDownAnim;


public class SteelFragment extends Fragment {

    Button btn_calculate;
    EditText edt_steel_length, edt_steel_diameter, edt_steel_weight, edt_no_steel, edt_no_beam;
    TextView tv_steel_weight;
    double weigth;
    Spinner spinner_steel;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_steel, container, false);
        Button_init(view);

        init(view);


        return view;


    }


    private void init(View view) {

        tv_steel_weight = view.findViewById(R.id.tv_steel_weight);
        edt_steel_weight = view.findViewById(R.id.edt_steel_diametre_wise_weight);
        edt_steel_diameter = view.findViewById(R.id.edt_steel_diametre);
        edt_no_steel = view.findViewById(R.id.edt_no_of_steel);
        edt_no_beam = view.findViewById(R.id.edt_no_of_Beam);
        edt_steel_length = view.findViewById(R.id.edt_steel_length);


        edt_steel_length.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                tv_steel_weight.setText("0");
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        edt_steel_diameter.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                edt_steel_weight.setText("0");
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                try {
                    String steel_diameter = edt_steel_diameter.getText().toString().trim();
                    double diameter = Integer.parseInt(steel_diameter);
                    double final_diameter = diameter * diameter;
                    weigth = final_diameter / 162;

                    String diameter_wise_weight = String.format("%.3f", weigth);
                    edt_steel_weight.setText(diameter_wise_weight);

                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }

            }
        });


    }

    private void Button_init(View view) {
        btn_calculate = view.findViewById(R.id.btn_calculate_weight);

        PushDownAnim.setPushDownAnimTo(btn_calculate)
                .setScale(PushDownAnim.MODE_STATIC_DP, 2)
                .setDurationPush(PushDownAnim.DEFAULT_PUSH_DURATION)
                .setDurationRelease(PushDownAnim.DEFAULT_RELEASE_DURATION)
                .setInterpolatorPush(PushDownAnim.DEFAULT_INTERPOLATOR)
                .setInterpolatorRelease(PushDownAnim.DEFAULT_INTERPOLATOR)
                .setOnClickListener(getClickListener());
    }

    @NonNull
    private View.OnClickListener getClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view == btn_calculate) {

                    validate();

                }
            }

        };
    }


    public void validate() {

        if (edt_steel_diameter.getText().toString().length() == 0) {
            edt_steel_diameter.setError("Fill Diameter First");
            edt_steel_diameter.requestFocus();
        } else if (edt_no_steel.getText().toString().length() == 0) {
            edt_no_steel.setError("Please Enter No of Steel");
            edt_no_steel.requestFocus();
        } else if (edt_no_beam.getText().toString().length() == 0) {
            edt_no_beam.setError("Please Enter No of Beam");
            edt_no_beam.requestFocus();
        } else if (edt_steel_length.getText().toString().length() == 0) {
            edt_steel_length.setError("Fill Length of Steel");
            edt_steel_length.requestFocus();
        } else {
            getAns();
        }
    }

    private void getAns() {
        String steel_length = edt_steel_length.getText().toString();
        String no_steel = edt_no_steel.getText().toString();
        String no_beam = edt_no_beam.getText().toString();

        double total_steel_length = Double.parseDouble(steel_length);
        double total_no_steel = Double.parseDouble(no_steel);
        double total_no_beam = Double.parseDouble(no_beam);


        double Total_steel_weigth = weigth * total_no_steel * total_no_beam * total_steel_length;

        String final_total_weight = String.format("%.3f", Total_steel_weigth);


        tv_steel_weight.setText(final_total_weight);
    }


}
