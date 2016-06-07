package me.jimmyshaw.lexusfanapp.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.jimmyshaw.lexusfanapp.R;
import me.jimmyshaw.lexusfanapp.edmunds.Model;
import me.jimmyshaw.lexusfanapp.edmunds.Models;
import me.jimmyshaw.lexusfanapp.edmunds.util.EdmundsService;
import me.jimmyshaw.lexusfanapp.edmunds.util.EdmundsServiceGenerator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ModelsFragment extends Fragment {

    private final String API_KEY = "k5whpdvu4rf2h2gj3wuzaysg";

    private static final String ARG_CATEGORY = "model_category";
    private String mCategory;

    private RecyclerView mModelRecyclerView;
    private ModelAdapter mModelAdapter;

    private List<Model> mModels;

    private Map<String, String> mModelsAndPrices;

    // New instances of ModelsFragment can only be created through this static newInstance
    // method, which takes a model category argument such as null, sedan, coupe etc. This way, our
    // list of models can be filtered by that particular category.
    public static Fragment newInstance(String category) {
        Bundle args = new Bundle();
        args.putString(ARG_CATEGORY, category);
        ModelsFragment modelsFragment = new ModelsFragment();
        modelsFragment.setArguments(args);

        return modelsFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Retrieve the category argument that's been set through the newInstance creation of
        // this fragment and assign it to a variable.
        mCategory = getArguments().getString(ARG_CATEGORY);

        // Instantiate our map of known models and prices to be used later in the ViewHolder.
        mModelsAndPrices = getModelsAndPricesMap();

        // Add a progress dialog spinner that will display during interactions with the server.
        final ProgressDialog progressDialog = new ProgressDialog(getActivity(), R.style.ProgressDialogTheme);
        progressDialog.setCancelable(false);
        progressDialog.setProgressStyle(android.R.style.Widget_ProgressBar);
        progressDialog.show();

        // Use our service generator to create the backend API interface.
        EdmundsService service = EdmundsServiceGenerator.createService(EdmundsService.class);

        // Use a map of options to actually query the backend API server with a GET request.
        Call<Models> call = service.getModels(getModelsOptions());
        call.enqueue(new Callback<Models>() {
            @Override
            public void onResponse(Call<Models> call, Response<Models> response) {
                if (response.isSuccessful()) {
                    mModels = response.body().getModels();
                    progressDialog.dismiss();
                    updateUI();
                }
            }

            @Override
            public void onFailure(Call<Models> call, Throwable t) {
                Log.e("Error retrieving data", t.getMessage());
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_models, container, false);

        mModelRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        mModelRecyclerView.setHasFixedSize(true);
        mModelRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        return mModelRecyclerView;
    }

    private Map<String, String> getModelsOptions() {
        // Create the hash map of GET request query parameters.
        Map<String, String> options = new HashMap<>();
        options.put("state", "new");
        options.put("year", "2016");
        options.put("view", "basic");
        options.put("api_key", API_KEY);
        // A null category means there's no filter and the user will see all car models. So we
        // don't have to put the category in the HashMap.
        if (mCategory != null) {
            options.put("category", mCategory);
        }
        return options;
    }

    private Map<String, String> getModelsAndPricesMap() {
        Map<String, String> modelsAndPrices = new HashMap<>();
        modelsAndPrices.put("CT 200H", "");
        modelsAndPrices.put("ES 300H", "");
        modelsAndPrices.put("ES 350", "");
        modelsAndPrices.put("GS 200T", "");
        modelsAndPrices.put("GS 350", "");
        modelsAndPrices.put("GS 450H", "");
        modelsAndPrices.put("GS F", "");
        modelsAndPrices.put("GX 460", "");
        modelsAndPrices.put("IS 200T", "");
        modelsAndPrices.put("IS 300", "");
        modelsAndPrices.put("IS 350", "");
        modelsAndPrices.put("LS 460", "");
        modelsAndPrices.put("LS 600H L", "");
        modelsAndPrices.put("LX 570", "");
        modelsAndPrices.put("NX 200T", "");
        modelsAndPrices.put("NX 300H", "");
        modelsAndPrices.put("RC 200T", "");
        modelsAndPrices.put("RC 300", "");
        modelsAndPrices.put("RC 350", "");
        modelsAndPrices.put("RC F", "");
        modelsAndPrices.put("RX 350", "");
        modelsAndPrices.put("RX 450H", "");

        return modelsAndPrices;
    }

    private void updateUI() {
        mModelAdapter = new ModelAdapter(mModels);
        mModelRecyclerView.setAdapter(mModelAdapter);
    }

    public class ModelHolder extends RecyclerView.ViewHolder {

        private Model mModel;
        private TextView mName;
        private TextView mPrice;
        private ImageView mImage;

        public ModelHolder(View itemView) {
            super(itemView);
            mName = (TextView) itemView.findViewById(R.id.card_view_name);
            mPrice = (TextView) itemView.findViewById(R.id.card_view_price);
            mImage = (ImageView) itemView.findViewById(R.id.card_view_image);
        }

        public void bindModel(Model model) {
            mModel = model;
            mName.setText(mModel.getName());

        }

        private int bindImage(String modelName) {
            switch (modelName) {
                case "CT 200H":
                    return 0;
                case "ES 300H":
                    return 0;
                case "ES 350":
                    return 0;
                case "GS 200T":
                    return 0;
                case "GS 350":
                    return 0;
                case "GS 450H":
                    return 0;
                case "GS F":
                    return 0;
                case "GX 460":
                    return 0;
                case "IS 200T":
                    return 0;
                case "IS 300":
                    return 0;
                case "IS 350":
                    return 0;
                case "LS 460":
                    return 0;
                case "LS 600H L":
                    return 0;
                case "LX 570":
                    return 0;
                case "NX 200T":
                    return 0;
                case "NX 300H":
                    return 0;
                case "RC 200T":
                    return 0;
                case "RC 300":
                    return 0;
                case "RC 350":
                    return 0;
                case "RC F":
                    return 0;
                case "RX 350":
                    return 0;
                case "RX 450H":
                    return 0;
                default:
                    return 0;
            }
        }

    }

    public class ModelAdapter extends RecyclerView.Adapter<ModelHolder> {
        // Set number of Cards in the recycler view.
        private List<Model> models;

        public ModelAdapter(List<Model> models) {
            this.models = models;
        }

        @Override
        public ModelHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflate = LayoutInflater.from(getActivity());
            View view = layoutInflate.inflate(R.layout.fragment_models_card_view_item, parent, false);
            return new ModelHolder(view);
        }

        @Override
        public void onBindViewHolder(ModelHolder holder, int position) {
            // For our list of models, get one model by position and bind it to our view holder class.
            Model model = models.get(position);
            holder.bindModel(model);
        }

        @Override
        public int getItemCount() {
            // Check for the numbers of models in our list. If it's null, meaning our async GET
            // request from the server hasn't completed yet, return 0. When the GET requests completes
            // our list of models will be filled, our UI will update and the cars will appear.
            return models == null ? 0 : models.size();
        }
    }
}
