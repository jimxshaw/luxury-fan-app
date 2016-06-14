package me.jimmyshaw.luxuryfanapp.fragments;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

import me.jimmyshaw.luxuryfanapp.R;
import me.jimmyshaw.luxuryfanapp.app.ModelDetailActivity;
import me.jimmyshaw.luxuryfanapp.app.ModelLab;
import me.jimmyshaw.luxuryfanapp.edmunds.Model;
import me.jimmyshaw.luxuryfanapp.edmunds.Models;
import me.jimmyshaw.luxuryfanapp.edmunds.Style;
import me.jimmyshaw.luxuryfanapp.edmunds.Year;
import me.jimmyshaw.luxuryfanapp.edmunds.util.EdmundsService;
import me.jimmyshaw.luxuryfanapp.edmunds.util.EdmundsServiceGenerator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ModelFragment extends Fragment {

    private static final String ARG_CATEGORY = "model_category";


    private String mCategory;

    private RecyclerView mModelRecyclerView;
    private ModelAdapter mModelAdapter;

    private List<Model> mModels;

    // New instances of ModelFragment can only be created through this static newInstance
    // method, which takes a model category argument such as null, sedan, coupe etc. This way, our
    // list of models can be filtered by that particular category.
    public static Fragment newInstance(String category) {
        Bundle args = new Bundle();
        args.putString(ARG_CATEGORY, category);
        ModelFragment modelFragment = new ModelFragment();
        modelFragment.setArguments(args);

        return modelFragment;
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

        // Use a map of options to actually query the backend API server with a GET request.
        Call<Models> call = service.getModels(ModelLab.get(getActivity()).getModelsOptions(mCategory));
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
        View view = inflater.inflate(R.layout.fragment_model, container, false);

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

    // Use this method to show an info dialog, such as showing how a particular make determines
    // the naming convention for its models.
    private void showInfoDialog(int resTitleId, int resBodyId, String message) {
        String title = getResources().getString(resTitleId);
        String body = getResources().getString(resBodyId) + " " + message;
        AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
        alertDialog.setTitle(title);
        alertDialog.setMessage(body);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alertDialog.show();
    }

    public class ModelHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private Model mModel;
        private TextView mName;
        private ImageView mImage;
        private ImageView mFavoriteButton;

        private String mStyleId;

        public ModelHolder(View itemView) {
            super(itemView);
            // Capture our widgets and make the text views be underlined.
            mName = (TextView) itemView.findViewById(R.id.card_view_name);
            mName.setPaintFlags(mName.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
            mName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showInfoDialog(R.string.car_name_info_title, R.string.lexus_car_name_info, "");
                }
            });

            mFavoriteButton = (ImageView) itemView.findViewById(R.id.card_view_favorite_button);
            mFavoriteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getActivity(), "Added to favorites list!", Toast.LENGTH_LONG).show();
                    Log.i("Zip Code: ", ModelLab.get(getActivity()).getZipCode());
                }
            });

            mImage = (ImageView) itemView.findViewById(R.id.card_view_image);
            mImage.setOnClickListener(this);
        }

        public void bindModel(Model model) {
            mModel = model;
            mName.setText(mModel.getName());

            // Use Picasso to resize the model image and load it into our image view.
            Picasso.with(getActivity())
                    .load(ModelLab.get(getActivity()).bindImage(mModel.getName()))
                    .placeholder(android.R.color.white)
                    .into(mImage);

        }

        private String getStyleId() {
            // Every model has a list of years, every year has a list of styles and every style has
            // a styleId. The styleId is a crucial field that determines information such as MSRP,
            // base price, incentives etc. 
            Year year = mModel.getYears().get(0);
            Style style = year.getStyles().get(0);
            mStyleId = style.getId().toString();
            Log.i("StyleId: ", mStyleId);

            return mStyleId;
        }

        @Override
        public void onClick(View v) {
            // This onClick method is bound to our model image.
            Intent intent = ModelDetailActivity.newIntent(getActivity(), "Lexus", mModel.getName(), getStyleId());
            startActivity(intent);
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
            View view = layoutInflate.inflate(R.layout.fragment_model_card_view_item, parent, false);
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
