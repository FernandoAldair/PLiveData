package com.example.plivedata;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.arch.core.util.Function;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

public class ImaginadorViewModel extends AndroidViewModel {
    Imaginador imaginador;

    LiveData<Integer> imaginarLiveData;

    public ImaginadorViewModel(@NonNull Application application) {
        super(application);

        imaginador = new Imaginador();

        imaginarLiveData = Transformations.switchMap(imaginador.imaginacionLiveData, new Function<String, LiveData<Integer>>(){

            @Override
            public LiveData<Integer> apply(String imaginacion) {

                int imagen;
                switch (imaginacion) {

                    case "IMAGINAR1":
                    default:
                        imagen = R.drawable.lol1;
                        break;

                    case "IMAGINAR2":
                        imagen = R.drawable.lol2;
                        break;

                    case "IMAGINAR3":
                        imagen = R.drawable.lol3;
                        break;

                    case "IMAGINAR4":
                        imagen = R.drawable.lol4;
                        break;
                }
                return new MutableLiveData<>(imagen);
            }
        });
    }

    LiveData<Integer> obtenerImagen(){
        return imaginarLiveData;
    }
}
