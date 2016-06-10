package me.jimmyshaw.luxuryfanapp.app;


import android.content.SharedPreferences;

import java.util.HashMap;
import java.util.Map;

import me.jimmyshaw.luxuryfanapp.R;

/*
    ModelLab is a Bill Pugh singleton pattern implementation. Before Java 5, certain singleton
    implementations failed in some situations where too many threads try to get the singleton class
    instance simultaneously. Bill's approach involves a private inner static class. When the singleton
    class is loaded, the helper class is not loaded into memory. Only when getInstance is called does
    the helper class get loaded and creates the singleton instance. This implementation is widely
    used as it doesn't require synchronization.
*/
public class ModelLab {

    private final String API_KEY = "k5whpdvu4rf2h2gj3wuzaysg";

    private ModelLab() {

    }

    public static ModelLab getInstance() {
        return ModelLabHelper.INSTANCE;
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

    public static class ModelLabHelper {
        private static final ModelLab INSTANCE = new ModelLab();
    }
}
