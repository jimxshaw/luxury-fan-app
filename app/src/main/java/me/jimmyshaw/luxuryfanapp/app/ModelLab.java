package me.jimmyshaw.luxuryfanapp.app;


import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;

import me.jimmyshaw.luxuryfanapp.R;


public class ModelLab {

    private static ModelLab sModelLab;

    private final String API_KEY = "k5whpdvu4rf2h2gj3wuzaysg";

    private String zipCode;

    private ModelLab(Context context) {

    }

    public static ModelLab get(Context context) {
        if (sModelLab == null) {
            sModelLab = new ModelLab(context);
        }
        return sModelLab;
    }

    public String getApiKey() {
        return API_KEY;
    }

    public Map<String, String> getModelsOptions(String category) {
        // Create the hash map of GET request query parameters.
        Map<String, String> options = new HashMap<>();
        options.put("state", "new");
        options.put("year", "2016");
        options.put("view", "basic");
        options.put("api_key", API_KEY);
        // A null category means there's no filter and the user will see all car models. So we
        // don't have to put the category in the HashMap.
        if (category != null) {
            options.put("category", category);
        }
        return options;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String newZipCode) {
        zipCode = newZipCode;
    }

    public Map<String, String> getModelsAndPricesMap() {
        Map<String, String> modelsAndPrices = new HashMap<>();
        modelsAndPrices.put("CT 200h", "$31,250");
        modelsAndPrices.put("ES 300h", "$41,020");
        modelsAndPrices.put("ES 350", "$38,100");
        modelsAndPrices.put("GS 200t", "$45,615");
        modelsAndPrices.put("GS 350", "$50,000");
        modelsAndPrices.put("GS 450h", "$63,080");
        modelsAndPrices.put("GS F", "$84,440");
        modelsAndPrices.put("GX 460", "$50,780");
        modelsAndPrices.put("IS 200t", "$37,325");
        modelsAndPrices.put("IS 300", "$39,700");
        modelsAndPrices.put("IS 350", "$40,870");
        modelsAndPrices.put("LS 460", "$72,520");
        modelsAndPrices.put("LS 600h L", "$120,440");
        modelsAndPrices.put("LX 570", "$88,880");
        modelsAndPrices.put("NX 200t", "$34,965");
        modelsAndPrices.put("NX 300h", "$39,720");
        modelsAndPrices.put("RC 200t", "$39,995");
        modelsAndPrices.put("RC 300", "$42,610");
        modelsAndPrices.put("RC 350", "$42,780");
        modelsAndPrices.put("RC F", "$62,805");
        modelsAndPrices.put("RX 350", "$41,900");
        modelsAndPrices.put("RX 450h", "$52,235");

        return modelsAndPrices;
    }

    public int bindImage(String modelName) {
        switch (modelName) {
            case "CT 200h":
                return R.drawable.model_ct_200h;
            case "ES 300h":
                return R.drawable.model_es_300h;
            case "ES 350":
                return R.drawable.model_es_350;
            case "GS 200t":
                return R.drawable.model_gs_200t;
            case "GS 350":
                return R.drawable.model_gs_350;
            case "GS 450h":
                return R.drawable.model_gs_450h;
            case "GS F":
                return R.drawable.model_gs_f;
            case "GX 460":
                return R.drawable.model_gx_460;
            case "IS 200t":
                return R.drawable.model_is_200t;
            case "IS 300":
                return R.drawable.model_is_300;
            case "IS 350":
                return R.drawable.model_is_350;
            case "LS 460":
                return R.drawable.model_ls_460;
            case "LS 600h L":
                return R.drawable.model_ls_600h_l;
            case "LX 570":
                return R.drawable.model_lx_570;
            case "NX 200t":
                return R.drawable.model_nx_200t;
            case "NX 300h":
                return R.drawable.model_nx_300h;
            case "RC 200t":
                return R.drawable.model_rc_200t;
            case "RC 300":
                return R.drawable.model_rc_300;
            case "RC 350":
                return R.drawable.model_rc_350;
            case "RC F":
                return R.drawable.model_rc_f;
            case "RX 350":
                return R.drawable.model_rx_350;
            case "RX 450h":
                return R.drawable.model_rx_450h;
            default:
                return R.drawable.model_logo;
        }
    }

    // This method could used to scale down resources.
    private Bitmap decodeResource(Resources res, int id) {
        Bitmap bitmap = null;
        BitmapFactory.Options options = new BitmapFactory.Options();
        /*
            Regarding inSampleSize:
            If set to a value > 1, requests the decoder to subsample the original image, returning
            a smaller image to save memory. The sample size is the number of pixels in either
            dimension that correspond to a single pixel in the decoded bitmap. For example,
            inSampleSize == 4 returns an image that is 1/4 the width/height of the original,
            and 1/16 the number of pixels.
        */
        for (options.inSampleSize = 1; options.inSampleSize <= 32; options.inSampleSize++) {
            try {
                bitmap = BitmapFactory.decodeResource(res, id, options);
                Log.d("Inside decodeResource", "Decoded successfully for sampleSize " + options.inSampleSize);
                break;
            }
            catch (OutOfMemoryError e) {
                // If an OutOfMemoryError occurs, we continue with the for loop to the next
                // inSampleSize value.
                Log.e("Inside decodeResources", "OutOfMemoryError while reading file for sampleSize " + options.inSampleSize + " re-trying with higher value");
            }
        }
        return bitmap;
    }
}
