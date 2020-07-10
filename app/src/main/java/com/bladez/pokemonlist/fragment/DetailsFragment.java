package com.bladez.pokemonlist.fragment;

import android.animation.ValueAnimator;
import android.content.Context;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bladez.pokemonlist.R;
import com.bladez.pokemonlist.model.PokeDetails;
import com.bladez.pokemonlist.model.PokeSpecies;
import com.bladez.pokemonlist.service.PokeAsyncTask;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.squareup.picasso.Picasso;

import static android.view.View.GONE;

public class DetailsFragment extends Fragment {

    private NavController navController;
    private PokeSpecies species;
    private PokeDetails details;
    private ConstraintLayout mainLayout;
    private ConstraintLayout lowerLayout;
    private Button back;
    private TextView header;
    private ImageView image;
    private ImageView type1;
    private ImageView type2;
    private ImageView singleType;
    private TextView hp;
    private TextView atk;
    private TextView def;
    private TextView spAtk;
    private TextView spDef;
    private TextView speed;
    private ProgressBar progHp;
    private ProgressBar progAtk;
    private ProgressBar progDef;
    private ProgressBar progSpAtk;
    private ProgressBar progSpDef;
    private ProgressBar progSpeed;

    public DetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_details, container, false);

        navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);

        mainLayout = view.findViewById(R.id.fragment_details_layout);
        lowerLayout = view.findViewById(R.id.lower_square);
        back = view.findViewById(R.id.btn_back);
        header = view.findViewById(R.id.txt_header);
        image = view.findViewById(R.id.img_pokemon);
        type1 = view.findViewById(R.id.img_type1);
        type2 = view.findViewById(R.id.img_type2);
        singleType = view.findViewById(R.id.img_single_type);
        hp = view.findViewById(R.id.txt_hp_value);
        atk = view.findViewById(R.id.txt_atk_value);
        def = view.findViewById(R.id.txt_def_value);
        spAtk = view.findViewById(R.id.txt_sp_atk_value);
        spDef = view.findViewById(R.id.txt_sp_def_value);
        speed = view.findViewById(R.id.txt_sp_speed_value);
        progHp = view.findViewById(R.id.prog_hp);
        progAtk = view.findViewById(R.id.prog_atk);
        progDef = view.findViewById(R.id.prog_def);
        progSpAtk = view.findViewById(R.id.prog_sp_atk);
        progSpDef = view.findViewById(R.id.prog_sp_def);
        progSpeed = view.findViewById(R.id.prog_speed);

//        Log.d("LOG", "checking arguments...");
        if (getArguments() != null) {
//            Log.d("LOG", "arguments found! Loading...");
            species = getArguments().getParcelable("species");
//            Log.d("LOG", "Loaded " + species.toString());
        }

        if (species != null) {
            back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    navController.navigateUp();
                    getActivity().getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
                }
            });
            //TODO comporre header con id e nome pokemon
            String headline = "#" + padPokemonId(species.getId()) + " " + species.getName().substring(0, 1).toUpperCase() + species.getName().substring(1);
            header.setText(headline);
            //TODO scaricare immagine e settare in imageView
            Picasso.get().load("https://pokeres.bastionbot.org/images/pokemon/" + species.getId() + ".png").fit().into(image);
            //TODO settare i tipi del pokemon (scaricare pokeDetails)
            PokeAsyncTask typesTask = new PokeAsyncTask("pokemon/" + species.getId(), res -> {
                if (res != null) {
                    Gson gson = new Gson();
                    details = gson.fromJson(res, PokeDetails.class);
                    if (details.getTypes().size() > 1) {
                        String stringType1 = ((JsonObject) ((JsonObject)details.getTypes().get(0)).get("type")).get("name").getAsString();
                        type1.setImageDrawable(getResources().getDrawable(getResources().getIdentifier(stringType1, "drawable", getContext().getPackageName())));

                        String stringType2 = ((JsonObject) ((JsonObject)details.getTypes().get(1)).get("type")).get("name").getAsString();
                        type2.setImageDrawable(getResources().getDrawable(getResources().getIdentifier(stringType2, "drawable", getContext().getPackageName())));

                        singleType.setVisibility(GONE);
                    } else {
                        String type = ((JsonObject) ((JsonObject)details.getTypes().get(0)).get("type")).get("name").getAsString();
                        singleType.setImageDrawable(getResources().getDrawable(getResources().getIdentifier(type, "drawable", getContext().getPackageName())));

                        type1.setVisibility(GONE);
                        type2.setVisibility(GONE);
                    }

                    setPokemonStats(details);
                } else {
                    singleType.setVisibility(GONE);
                    type1.setVisibility(GONE);
                    type2.setVisibility(GONE);
                }
            });
            typesTask.execute();
            //TODO settare le statistiche del pokemon
            //TODO chiamare api per ricevere colore e settare sfondo
            PokeAsyncTask colorTask = new PokeAsyncTask("pokemon-species/" + species.getId(), res -> {
                Gson gson = new Gson();
                PokeSpecies species = gson.fromJson(res, PokeSpecies.class);
                String color = ((JsonObject)species.getColor()).get("name").getAsString();
//                Log.d("LOG", "color " + color);
                if ("white".equalsIgnoreCase(color)) {
                    lowerLayout.setBackground(getResources().getDrawable(R.drawable.black_background_square));
                }

                getActivity().getWindow().setStatusBarColor(getResources().getColor(getResources().getIdentifier(color, "color", getContext().getPackageName())));

                mainLayout.setBackground(getResources().getDrawable(getResources().getIdentifier(color, "color", getContext().getPackageName())));
                progHp.getProgressDrawable().setColorFilter(getResources().getColor(getResources().getIdentifier(color, "color", getContext().getPackageName())), android.graphics.PorterDuff.Mode.SRC_IN);
                progAtk.getProgressDrawable().setColorFilter(getResources().getColor(getResources().getIdentifier(color, "color", getContext().getPackageName())), android.graphics.PorterDuff.Mode.SRC_IN);
                progDef.getProgressDrawable().setColorFilter(getResources().getColor(getResources().getIdentifier(color, "color", getContext().getPackageName())), android.graphics.PorterDuff.Mode.SRC_IN);
                progSpAtk.getProgressDrawable().setColorFilter(getResources().getColor(getResources().getIdentifier(color, "color", getContext().getPackageName())), android.graphics.PorterDuff.Mode.SRC_IN);
                progSpDef.getProgressDrawable().setColorFilter(getResources().getColor(getResources().getIdentifier(color, "color", getContext().getPackageName())), android.graphics.PorterDuff.Mode.SRC_IN);
                progSpeed.getProgressDrawable().setColorFilter(getResources().getColor(getResources().getIdentifier(color, "color", getContext().getPackageName())), android.graphics.PorterDuff.Mode.SRC_IN);

//                ValueAnimator animator = ValueAnimator.ofInt(0, progHp.getMax());
//                animator.setDuration(3000);
//                animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//                    @Override
//                    public void onAnimationUpdate(ValueAnimator animation){
//                        progHp.setProgress((Integer)animation.getAnimatedValue());
//                        progAtk.setProgress((Integer)animation.getAnimatedValue());
//                        progDef.setProgress((Integer)animation.getAnimatedValue());
//                        progSpAtk.setProgress((Integer)animation.getAnimatedValue());
//                        progSpDef.setProgress((Integer)animation.getAnimatedValue());
//                        progSpeed.setProgress((Integer)animation.getAnimatedValue());
//                    }
//                });
            });
            colorTask.execute();
        }

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    private String padPokemonId(int id) {
        String stringId = String.valueOf(id);
        while (stringId.length() < 3) {
            stringId = "0" + stringId;
        }
        return stringId;
    }

    private void setPokemonStats(PokeDetails details) {
//        Log.d("LOG", "setPokemonStats: " + details.getStats().get(0));

        JsonElement hpVal = ((JsonObject) details.getStats().get(0)).get("base_stat");
        JsonElement atkVal = ((JsonObject) details.getStats().get(1)).get("base_stat");
        JsonElement defVal = ((JsonObject) details.getStats().get(2)).get("base_stat");
        JsonElement spAtkVal = ((JsonObject) details.getStats().get(3)).get("base_stat");
        JsonElement spDefVal = ((JsonObject) details.getStats().get(4)).get("base_stat");
        JsonElement speedVal = ((JsonObject) details.getStats().get(5)).get("base_stat");

        hp.setText(hpVal.getAsString());
        atk.setText(atkVal.getAsString());
        def.setText(defVal.getAsString());
        spAtk.setText(spAtkVal.getAsString());
        spDef.setText(spDefVal.getAsString());
        speed.setText(speedVal.getAsString());
        progHp.setProgress(hpVal.getAsInt());
        progAtk.setProgress(atkVal.getAsInt());
        progDef.setProgress(defVal.getAsInt());
        progSpAtk.setProgress(spAtkVal.getAsInt());
        progSpDef.setProgress(spDefVal.getAsInt());
        progSpeed.setProgress(speedVal.getAsInt());
    }

}
