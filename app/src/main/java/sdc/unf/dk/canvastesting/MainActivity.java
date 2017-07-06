package sdc.unf.dk.canvastesting;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends Activity {

    CanvasView cv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Der sker ikke noget specielt her. Det der skal til for at I kan bruge jeres egen View i layoutet sker i layoutet. Få Andreas til at gøre dette
        setContentView(R.layout.activity_main);

        cv = (CanvasView) findViewById(R.id.canvasView);
    }

    //Knappen kalder denne metode, som kalder metoden i CanvasView. I kan ikke få knapper til direkte at kalde metoder i jeres View
    public void animation(View view) {
        cv.animation();
    }
}
