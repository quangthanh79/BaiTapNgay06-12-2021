package com.pqt.phamquangthanh.navigationdrawer.ui.unit;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.pqt.phamquangthanh.navigationdrawer.R;
import com.pqt.phamquangthanh.navigationdrawer.databinding.FragmentUnitBinding;

public class UnitFragment extends Fragment {

    private UnitViewModel unitViewModel;
    private FragmentUnitBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        unitViewModel =
                new ViewModelProvider(this).get(UnitViewModel.class);

        binding = FragmentUnitBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textHome;
        unitViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}