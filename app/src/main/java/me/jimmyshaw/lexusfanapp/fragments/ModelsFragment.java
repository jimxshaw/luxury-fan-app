package me.jimmyshaw.lexusfanapp.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

        // Add a progress dialog spinner that will display during interactions with the server.
        final ProgressDialog progressDialog = new ProgressDialog(getActivity(), R.style.ProgressDialogTheme);
        progressDialog.setCancelable(false);
        progressDialog.setProgressStyle(android.R.style.Widget_ProgressBar);
        progressDialog.show();

        // Use our service generator to create the backend API interface.
        EdmundsService service = EdmundsServiceGenerator.createService(EdmundsService.class);

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

        // Use the hash map to actually query the backend API server with a GET request.
        Call<Models> call = service.getModels(options);
        call.enqueue(new Callback<Models>() {
            @Override
            public void onResponse(Call<Models> call, Response<Models> response) {
                if (response.isSuccessful()) {
                    mModels = response.body().getModels();
                    progressDialog.dismiss();

                    updateUI();

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
        View view = inflater.inflate(R.layout.fragment_models, container, false);

        mModelRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        mModelRecyclerView.setHasFixedSize(true);
        mModelRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        return mModelRecyclerView;
    }

    private void updateUI() {
        mModelAdapter = new ModelAdapter(mModels);
        mModelRecyclerView.setAdapter(mModelAdapter);
    }

    public class ModelHolder extends RecyclerView.ViewHolder {

        private Model model;
        private TextView mName;
        private TextView mTotalCostOfOwnership;

        public ModelHolder(View itemView) {
            super(itemView);

            mName = (TextView) itemView.findViewById(R.id.card_view_name);
            mTotalCostOfOwnership = (TextView) itemView.findViewById(R.id.card_view_tco);
        }

        public void bindModel(Model model) {
            this.model = model;

            // This if statement added to prevent null object reference errors.
            if (this.model != null) {
                mName.setText(this.model.getName());
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
