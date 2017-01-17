package mf.andorid.com.mfinfo.sharedPref;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import mf.andorid.com.mfinfo.Adapter.Product;


public class wishlistsharedPref {

    public static final String PREFS_NAME = "PRODUCT_APP";
    public static final String FAVORITES = "Product_Favorite";

    public wishlistsharedPref() {
        super();
    }

    // This four methods are used for maintaining favorites.
    public void saveFavorites(Context context, List<Product> favorites) {
        SharedPreferences settings;
        Editor editor;

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
        Editor editor;

        settings = context.getSharedPreferences(PREFS_NAME,
                Context.MODE_PRIVATE);
        editor = settings.edit();
        editor.clear();
        editor.commit();
    }

    public void addFavorite(Context context, Product product) {
        List<Product> favorites = getFavorites(context);
        if (favorites == null)
            favorites = new ArrayList<Product>();
        favorites.add(product);
        saveFavorites(context, favorites);
    }

    public void removeFavorite(Context context, Product product) {
        ArrayList<Product> favorites = getFavorites(context);
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

    public ArrayList<Product> getFavorites(Context context) {
        SharedPreferences settings;
        List<Product> favorites;

        settings = context.getSharedPreferences(PREFS_NAME,
                Context.MODE_PRIVATE);

        if (settings.contains(FAVORITES)) {
            String jsonFavorites = settings.getString(FAVORITES, null);
            Gson gson = new Gson();
            Product[] favoriteItems = gson.fromJson(jsonFavorites,
                    Product[].class);

            favorites = Arrays.asList(favoriteItems);
            favorites = new ArrayList<Product>(favorites);
        } else
            return null;

        return (ArrayList<Product>) favorites;
    }
    public void updateshare(Context context,String code,String value){
        System.out.println("inside update share");
        ArrayList<Product> p=getFavorites(context);
        if(p!=null){
            for(int i=0;i<p.size();i++){
                if(p.get(i).getCode().equals(code)){
                    Double d1=Double.parseDouble(p.get(i).getNav());
                    System.out.println(d1);
                    Double d2=Double.parseDouble(value);
                    System.out.println(d2);
                    Double change=(d2-d1)/d1;
                    System.out.println(change);

                    Product p1=new Product(p.get(i).getName(),value,p.get(i).getCode(),Double.toString(change),p.get(i).getDate());
                    p.remove(p.get(i));
                    p.add(p1);
                    clearsavedpref(context);
                    saveFavorites(context, p);
                    //addFavorite(context,p1);
                    break;
                }
            }
        }

    }
}