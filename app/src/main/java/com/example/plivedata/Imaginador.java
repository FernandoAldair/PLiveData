package com.example.plivedata;

import androidx.lifecycle.LiveData;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import static java.util.concurrent.TimeUnit.SECONDS;

public class Imaginador {


    interface ImaginadorListener{
        void cuandoDeLaOrden(String orden);
    }

    Random random = new Random();
    ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    ScheduledFuture<?> imaginar;

    LiveData<String> ordenLiveData = new LiveData<String>() {

    };

    void iniciar(final ImaginadorListener imaginadorListener) {
        if (imaginar == null || imaginar.isCancelled()) {
            imaginar = scheduler.scheduleAtFixedRate(new Runnable() {
                int imagen;
                int repeticiones = -1;

                @Override
                public void run() {
                    if (repeticiones < 0) {
                        imagen = random.nextInt(5) + 1;
                    }
                    imaginadorListener.cuandoDeLaOrden("Imaginar" + imagen);
                }
            }, 0, 1, SECONDS);
        }
    }

    void  PararImaginar() {
        if (imaginar != null){
            imaginar.cancel(true);
        }
    }
}
