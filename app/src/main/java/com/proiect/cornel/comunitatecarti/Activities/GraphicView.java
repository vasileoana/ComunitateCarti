package com.proiect.cornel.comunitatecarti.Activities;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.proiect.cornel.comunitatecarti.Classes.Carte;
import com.proiect.cornel.comunitatecarti.Classes.Statics;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by i332191 on 11/01/2017.
 */

public class GraphicView extends View {

    private Map<String, Integer> map = new HashMap<>();

    public GraphicView(Context context) {
        super(context);
    }

    public GraphicView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void initValues() {
        for (Carte carte : Statics.listaTotalaCarti) {
            if (map.containsKey(carte.getCategorie())) {
                int n = map.get(carte.getCategorie());
                map.put(carte.getCategorie(), ++n);
            } else {
                map.put(carte.getCategorie(), 1);
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.rgb(153, 102, 0));
        initValues();
        int nr = map.size();
        float suma = 0;
        for (Map.Entry<String, Integer> entry : map.entrySet()){
            suma += entry.getValue();
        }
        int[] colors = new int[map.size()];
        for (int i = 0; i < map.size(); i++) {
            Random rand = new Random();

            int r = rand.nextInt();
            int g = rand.nextInt();
            int b = rand.nextInt();

            colors[i] = (int) (Color.rgb(r, g, b));
        }

        int startAngle = 0;
        int i = 0;
        int pozXText = 50;
        int pozYText = 100;
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            float angle = 360 * entry.getValue()/suma;
            Paint paint = new Paint();
            paint.setStrokeWidth(2);
            paint.setColor(colors[i++]);
            int w = canvas.getWidth() / 3 - 80;
            int h = canvas.getHeight() / 3;
            int dim = 500;
            canvas.drawArc(w, h, w + dim, h + dim, startAngle, angle, true, paint);
            startAngle += angle;
            paint.setTextSize(50);
            canvas.drawText(entry.getKey() + " " + String.format("%.02f",entry.getValue()/suma * 100) + "%", pozXText, pozYText, paint);
            pozYText += 60;
        }

    }
}
