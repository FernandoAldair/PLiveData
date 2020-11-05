package com.example.plivedata;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.example.plivedata.databinding.FragmentPantallaBinding;

public class PantallaFragment extends Fragment {
    private FragmentPantallaBinding binding;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return (binding = FragmentPantallaBinding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ImaginadorViewModel imaginadorViewModel = new ViewModelProvider(this).get(ImaginadorViewModel.class);

        imaginadorViewModel.obtenerImagen().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer imagen) {
                Glide.with(PantallaFragment.this).load(imagen).into(binding.imagen);
            }
        });

    }
}