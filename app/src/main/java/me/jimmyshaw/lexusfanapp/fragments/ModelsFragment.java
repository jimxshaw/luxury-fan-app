package me.jimmyshaw.lexusfanapp.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

        EdmundsService service = EdmundsServiceGenerator.createService(EdmundsService.class);

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

        Call<Models> call = service.getModels(options);
        call.enqueue(new Callback<Models>() {
            @Override
            public void onResponse(Call<Models> call, Response<Models> response) {
                if (response.isSuccessful()) {
                    mModels = response.body().getModels();
                    Log.i("GET Status", "Successfully retrieved data");
                    Log.i("GET Status", response.body().getModelsCount().toString());

                }
                else {
                    Log.i("GET Status", "Failed to retrieve data");
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


        mModelRecyclerView = (RecyclerView) inflater.inflate(R.layout.fragment_models, container, false);
        updateUI();
        mModelRecyclerView.setHasFixedSize(true);
        mModelRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return mModelRecyclerView;
    }

    private void updateUI() {
        mModelAdapter = new ModelAdapter(mModels);
        mModelRecyclerView.setAdapter(mModelAdapter);
    }

    public class ModelHolder extends RecyclerView.ViewHolder {

        public ModelHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.fragment_models_card_view_item, parent, false));

        }
    }

    public class ModelAdapter extends RecyclerView.Adapter<ModelHolder> {
        // Set number of Cards in the recycler view.
        private final int LENGTH = 18;

        private List<Model> models;

        public ModelAdapter(List<Model> models) {
            this.models = models;
        }


        @Override
        public ModelHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ModelHolder(LayoutInflater.from(parent.getContext()), parent);
        }

        @Override
        public void onBindViewHolder(ModelHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return LENGTH;
        }
    }

}
