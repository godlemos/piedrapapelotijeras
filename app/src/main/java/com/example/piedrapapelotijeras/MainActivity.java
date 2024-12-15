package com.example.piedrapapelotijeras;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView tvResultado, tvEleccionComputadora, tvPuntajeJugador, tvPuntajeComputadora;
    private Button btnPiedra, btnPapel, btnTijera;

    private int puntajeJugador = 0;
    private int puntajeComputadora = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Cargar el layout dinámicamente (sin usar R directamente)
        int layoutId = getResources().getIdentifier("activity_main", "layout", getPackageName());
        setContentView(layoutId);

        // Vincular vistas dinámicamente
        tvResultado = findView("tv_resultado", TextView.class);
        tvEleccionComputadora = findView("tv_eleccion_computadora", TextView.class);
        tvPuntajeJugador = findView("tv_puntaje_jugador", TextView.class);
        tvPuntajeComputadora = findView("tv_puntaje_computadora", TextView.class);
        btnPiedra = findView("btn_piedra", Button.class);
        btnPapel = findView("btn_papel", Button.class);
        btnTijera = findView("btn_tijera", Button.class);

        // Configurar los botones
        btnPiedra.setOnClickListener(v -> playGame("Piedra"));
        btnPapel.setOnClickListener(v -> playGame("Papel"));
        btnTijera.setOnClickListener(v -> playGame("Tijera"));
    }

    // Método genérico para encontrar vistas dinámicamente
    private <T extends View> T findView(String viewIdName, Class<T> viewClass) {
        int viewId = getResources().getIdentifier(viewIdName, "id", getPackageName());
        return viewClass.cast(findViewById(viewId));
    }

    private void playGame(String playerChoice) {
        String[] choices = {"Piedra", "Papel", "Tijera"};
        String computerChoice = choices[new Random().nextInt(choices.length)];

        // Mostrar elección de la computadora
        tvEleccionComputadora.setText("La computadora eligió: " + computerChoice);

        // Determinar resultado del juego
        String result;
        if (playerChoice.equals(computerChoice)) {
            result = "Empate";
        } else if ((playerChoice.equals("Piedra") && computerChoice.equals("Tijera")) ||
                (playerChoice.equals("Papel") && computerChoice.equals("Piedra")) ||
                (playerChoice.equals("Tijera") && computerChoice.equals("Papel"))) {
            result = "Ganaste";
            puntajeJugador++;
        } else {
            result = "Perdiste";
            puntajeComputadora++;
        }

        // Mostrar resultado
        tvResultado.setText(result);

        // Actualizar puntuaciones
        tvPuntajeJugador.setText("Jugador: " + puntajeJugador);
        tvPuntajeComputadora.setText("Computadora: " + puntajeComputadora);
    }
}
