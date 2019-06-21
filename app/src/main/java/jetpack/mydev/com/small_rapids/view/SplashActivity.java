package jetpack.mydev.com.small_rapids.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import jetpack.mydev.com.small_rapids.R;

import static jetpack.mydev.com.small_rapids.utils.ConstVars.TIME_SPLASH;

/**
 * Description:
 * Created by EX383473 on 07/06/2019.
 */
public class SplashActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Quitamos el título de la ventana
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //Seleccionamos el activity a mostrar
        setContentView(R.layout.activity_splash);
        //Arrancamos un hilo que en conteo regresivo mostrará la vista
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //Inicializamos FireBase
                Intent intent = new Intent(SplashActivity.this, MapsActivity.class);
                startActivity(intent);
                //Terminamos la actividad actual
                finish();
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        }, TIME_SPLASH);
    }
}
