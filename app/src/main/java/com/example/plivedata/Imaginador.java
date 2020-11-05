package com.example.plivedata;

import androidx.lifecycle.LiveData;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import static java.util.concurrent.TimeUnit.SECONDS;

public class Imaginador {


    interface ImaginadorListener{
        void cuandoImagine(String orden);
    }

    Random random = new Random();
    ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    ScheduledFuture<?> imaginar;

    LiveData<String> imaginacionLiveData = new LiveData<String>() {
        @Override
        protected void onActive() {
            super.onActive();

            iniciarImaginar(new ImaginadorListener() {
                @Override
                public void cuandoImagine(String imaginacion) {
                    postValue(imaginacion);
                }
            });
        }

        @Override
        protected void onInactive() {
            super.onInactive();

            pararImaginar();
        }
    };

    void iniciarImaginar(final ImaginadorListener imaginadorListener) {
        if (imaginar == null || imaginar.isCancelled()) {
            imaginar = scheduler.scheduleAtFixedRate(new Runnable() {
                @Override
                public void run() {
                    int imagen = random.nextInt(5) + 1;
                    imaginadorListener.cuandoImagine("IMAGINAR" + imagen);
                }
            }, 0, 3, SECONDS);
        }
    }

    void  pararImaginar() {
        if (imaginar != null){
            imaginar.cancel(true);
        }
    }
}
