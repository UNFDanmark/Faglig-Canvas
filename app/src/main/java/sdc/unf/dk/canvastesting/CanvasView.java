package sdc.unf.dk.canvastesting;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by user on 7/6/17.
 */

public class CanvasView extends View {

    //Feltvariabler
    Bitmap sun1, sun2;
    int sunPosX, height, width;
    Paint text, sky;
    Boolean sunSwitch;

    //Lav alle disse 3 constructors for at kunne manipulere med viewet i layout designeren.
    public CanvasView(Context context) {
        super(context);
        setup();
    }

    public CanvasView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setup();
    }

    public CanvasView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setup();
    }

    //Jeg har lavet en metode som kaldes fra alle constructors så jeg ikke skal skrive koden i alle tre.
    //Foreslår at I gør det samme
    public void setup(){

        //Find billeder og konverter dem til BitMap formatet. Skal gøres for alle jeres billeder
        sun1 = BitmapFactory.decodeResource(this.getResources(),
                R.drawable.sun1);
        sun2 = BitmapFactory.decodeResource(this.getResources(),
                R.drawable.sun2);

        //Sæt paint. Altså farver (og tekststørrelse) som bruges til at tegne
        sky = new Paint();
        sky.setColor(Color.BLUE);
        //Kan også bruge variabelNavn.setARGB(). A er gennemsigtigheden, R er rød, G er grøn og B er blå.
        //Brug den til at lave mere præcise farver.
        text = new Paint();
        text.setColor(Color.RED);
        text.setTextSize(75);

        //Sæt position. Dette er bare for mit eksempel.
        sunPosX = 0;
        sunSwitch = true;

        //postInvalidate er hvad der får Android til at opdatere jeres View ved at kalde onDraw og tegne det på ny.
        postInvalidate();
    }

    //Dette er den kode der skal til for at starte jeres timer. Minus at sætte sunPosX, den er for eksemplet.
    public void animation(){
        Timing timer = new Timing();
        sunPosX = 0;
        postInvalidate();
        timer.start();
    }


    //Det er den her der bliver kaldt når Android skal tegne jeres View
    @Override
    protected void onDraw(Canvas canvas) {
        //Få fat i højden og bredde af jeres View. I pixels
        height = canvas.getHeight();
        width = canvas.getWidth();

        //Sæt billederne til den størrelse i vil have dem. Parametrene er det originale BitMap, bredden af det nye bitmap, højden af det nye bitmap, true.
        //Bredden og højden skal være i pixels
        sun1 = Bitmap.createScaledBitmap(sun1, width/10, height/10, true);
        sun2 = Bitmap.createScaledBitmap(sun2, width/10, height/10, true);

        //Tegn ting. Se på de metoder Studio foreslår når i skriver variabelNavn.draw
        //Der skulle også stå nogenlunde gennemskueligt hvad parametrene skal være
        canvas.drawRect(0, 0, width, height, sky);
        canvas.drawText("Her er noget tekst", 40, height - 40, text);
        if(sunSwitch){
            canvas.drawBitmap(sun1, sunPosX, height/4, null);
        } else {
            canvas.drawBitmap(sun2, sunPosX, height/4, null);
        }
    }

    //Jeres timer. Kopier direkte alt andet end det der står i run metoden.
    //Run bliver kaldt en enkelt gang når i skriver threadNavn.start();
    public class Timing extends Thread {
        @Override
        public void run() {
            //Det er helt op til jer selv om I vil have den til at loope eller om det bare skal være et enkelt delay.
            //I kan kode lige så mange forskellige timer threads som I har brug for men pas på med at køre for mange samtidigt.
            while(sunPosX < width){

                //De her 5 linjer skal skrives for at lave et delay. Parameteren i sleep er hvor lang tid delayet er i millisekunder (1000 == 1 sekund);
                try{
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    //Gør ingenting
                }

                //Gør ting efter et delay
                //I kan IKKE direkte ændre i jeres UI herfra (Sætte tekst i knapper, ændre billeder)
                //Det I kan gøre er at ændre nogle felt variabler og så kalde postInvalidate() og så have at onDraw() reagerer på de felt variabler.
                sunPosX += width/100;
                sunSwitch = !sunSwitch;
                postInvalidate();
            }
        }
    }
}
