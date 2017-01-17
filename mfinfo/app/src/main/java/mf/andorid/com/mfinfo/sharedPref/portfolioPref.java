package mf.andorid.com.mfinfo.sharedPref;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Created by 8398 on 07/01/17.
 */
public class portfolioPref {


    public static final String PREFS_NAME = "myPortfolio";
    public static final String FAVORITES = "Product_Favorite";

    public portfolioPref() {
        super();
    }

    // This four methods are used for maintaining favorites.
    public void saveFavorites(Context context, List<portfolio> favorites) {
        SharedPreferences settings;
        SharedPreferences.Editor editor;

        settings = context.getSharedPreferences(PREFS_NAME,
                Context.MODE_PRIVATE);
        editor = settings.edit();


        Gson gson = new Gson();
        String jsonFavorites = gson.toJson(favorites);

        editor.putString(FAVORITES, jsonFavorites);

        editor.commit();
    }
    public void clearsavedpref(Context context){
        SharedPreferences settings;
        SharedPreferences.Editor editor;

        settings = context.getSharedPreferences(PREFS_NAME,
                Context.MODE_PRIVATE);
        editor = settings.edit();
        editor.clear();
        editor.commit();
    }

    public void addFavorite(Context context, portfolio product) {
        List<portfolio> favorites = getFavorites(context);
        if (favorites == null)
            favorites = new ArrayList<portfolio>();
        favorites.add(product);
        saveFavorites(context, favorites);
    }

    public void removeFavorite(Context context, portfolio product) {
        ArrayList<portfolio> favorites = getFavorites(context);
        for(int i=0;i<favorites.size();i++){
            System.out.println("inside removed"+favorites.get(i));
        }
        System.out.println("Actual removed"+product);
        if (favorites != null) {
            favorites.remove(product);
            System.out.println("removed");
            saveFavorites(context, favorites);
        }
    }

    public ArrayList<portfolio> getFavorites(Context context) {
        SharedPreferences settings;
        List<portfolio> favorites;

        settings = context.getSharedPreferences(PREFS_NAME,
                Context.MODE_PRIVATE);

        if (settings.contains(FAVORITES)) {
            String jsonFavorites = settings.getString(FAVORITES, null);
            Gson gson = new Gson();
            portfolio[] favoriteItems = gson.fromJson(jsonFavorites,
                    portfolio[].class);

            favorites = Arrays.asList(favoriteItems);
            favorites = new ArrayList<portfolio>(favorites);
        } else
            return null;

        return (ArrayList<portfolio>) favorites;
    }

    }
