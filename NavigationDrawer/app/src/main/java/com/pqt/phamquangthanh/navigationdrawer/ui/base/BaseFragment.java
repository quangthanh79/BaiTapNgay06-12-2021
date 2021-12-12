package com.pqt.phamquangthanh.navigationdrawer.ui.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.pqt.phamquangthanh.navigationdrawer.MainActivity;
import com.pqt.phamquangthanh.navigationdrawer.R;
import com.pqt.phamquangthanh.navigationdrawer.databinding.FragmentBaseBinding;
import com.pqt.phamquangthanh.navigationdrawer.ui.base.BaseViewModel;

import java.util.ArrayList;
import java.util.List;

public class BaseFragment extends Fragment {

    private BaseViewModel baseViewModel;
    private FragmentBaseBinding binding;

    private List<Integer> array_base;
    private Spinner spinner1,spinner2;

    private EditText number;
    private TextView result;
    private Button btnConvert;
    public static int fromBase=0,toBase=0;
    public static final char CHAR_55 = 55;
    public View root;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        baseViewModel =
                new ViewModelProvider(this).get(BaseViewModel.class);

        binding = FragmentBaseBinding.inflate(inflater, container, false);
        root = binding.getRoot();
        mapView();
        array_base = new ArrayList<>();
        array_base.add(2);
        array_base.add(8);
        array_base.add(10);
        array_base.add(16);
        ArrayAdapter spinnerAdapter = new ArrayAdapter<>(getActivity(), R.layout.support_simple_spinner_dropdown_item, array_base);

        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(spinnerAdapter);
        spinner2.setAdapter(spinnerAdapter);

        this.spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                onItemSelectedHandler(parent, view, position, id);
                fromBase = array_base.get(position);
                result.setText("");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                fromBase = 10;
                result.setText("");
            }
        });

        this.spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                toBase = array_base.get(position);
                result.setText("");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                toBase = 10;
                result.setText("");
            }
        });
        btnConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(number.getText().toString().trim().length() == 0){
                    Toast.makeText(getActivity(), "Vui lòng nhập đủ thông tin!", Toast.LENGTH_SHORT).show();
                }
                else {
                    if (fromBase == 10) {
                        result.setText(convertFrom10(Integer.parseInt(number.getText().toString().trim())));
                    }
                    if(toBase == 10){
                        result.setText(ConvertTo10Front(number.getText().toString().trim()));
                    }
                    if(fromBase != 10 && toBase != 10){
                        String middle = ConvertTo10Front(number.getText().toString().trim());
                        result.setText(convertFrom10(Integer.parseInt(middle)));
                    }
                }
            }
        });

//        final TextView textView = binding.result;
        baseViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                result.setText(s);
            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
    private void mapView(){
        spinner1   = (Spinner) root.findViewById(R.id.spinner_base_from);
        spinner2   = (Spinner) root.findViewById(R.id.spinner_base_to);
        number     = (EditText) root.findViewById(R.id.number);
        btnConvert = (Button) root.findViewById(R.id.btnConvert);
        result     = (TextView) root.findViewById(R.id.result);
    }
    public static String convertFrom10(int nunber) {
        if (nunber < 0 || toBase < 2 || toBase > 16 ) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        int m;
        int remainder = nunber;

        while (remainder > 0) {
            if (nunber > 10) {
                m = remainder % toBase;
                if (m >= 10) {
                    sb.append((char) (CHAR_55 + m));
                } else {
                    sb.append(m);
                }
            } else {
                sb.append(remainder % toBase);
            }
            remainder = remainder / toBase;
        }
        return sb.reverse().toString();
    }
    public static String ConvertTo10Front(String FromNumber)
    {
        String result = "";

        int numresult = 0;

        int n = FromNumber.length();

        for (int i = 0; i < n;i++ )
        {   String m = Character.toString(FromNumber.charAt(i));

            int x=0;
            if (fromBase == 16)
            {
                if (m.equals("A")) x = 10;
                if (m.equals("B")) x = 11;
                if (m.equals("C")) x = 12;
                if (m.equals("D")) x = 13;
                if (m.equals("E")) x = 14;
                if (m.equals("F")) x = 15;
            }
            if (FromNumber.charAt(i) >= '0' && FromNumber.charAt(i) <='9') x = Integer.parseInt(m);

            numresult = numresult +  x * LuyThua(fromBase, n - i - 1);

        }

        result = numresult+"";
        return result;

    }
    public static int LuyThua(int num, int somu)
    {
        int result = num;

        for (int i = 1; i < somu; i++)
        {
            result = result * num;
        }

        if (somu == 0) result = 1;

        return result;
    }
}