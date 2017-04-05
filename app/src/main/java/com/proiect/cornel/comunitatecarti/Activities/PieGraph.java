package com.proiect.cornel.comunitatecarti.Activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;

import com.proiect.cornel.comunitatecarti.Classes.Carte;
import com.proiect.cornel.comunitatecarti.Classes.Statics;

import org.achartengine.ChartFactory;
import org.achartengine.model.CategorySeries;
import org.achartengine.renderer.DefaultRenderer;
import org.achartengine.renderer.SimpleSeriesRenderer;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by i332191 on 10/01/2017.
 */

public class PieGraph {
    Map<String, Integer> map = new HashMap<>();

    public Intent getIntent(Context context) {
        for (Carte carte : Statics.listaTotalaCarti) {
            if (map.containsKey(carte.getCategorie())) {
                int n = map.get(carte.getCategorie());
                map.put(carte.getCategorie(), ++n);
            } else {
                map.put(carte.getCategorie(), 1);
            }
        }

        CategorySeries series = new CategorySeries("Pie Graph");

/*        System.out.println("===================");
        System.out.println(map.size());
        System.out.println("===================");*/


        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            series.add(entry.getKey() + " - " + entry.getValue(), entry.getValue());
        }

        int nr = map.size();
        int[] colors = new int[map.size()];
        for (int i = 0; i < map.size(); i++) {
            Random rand = new Random();

            int r = rand.nextInt();
            int g = rand.nextInt();
            int b = rand.nextInt();

            colors[i] = (int) (Color.rgb(r, g, b));
        }

        DefaultRenderer renderer = new DefaultRenderer();
        renderer.setLabelsTextSize(25);
        renderer.setApplyBackgroundColor(true);
        renderer.setBackgroundColor(Color.rgb(153, 102, 0));
        renderer.setLegendTextSize(35);
        for (int color : colors) {
            SimpleSeriesRenderer r = new SimpleSeriesRenderer();
            r.setColor(color);
            renderer.addSeriesRenderer(r);

        }
        Intent intent = ChartFactory.getPieChartIntent(context, series, renderer, "Pie");


        return intent;

    }
}
