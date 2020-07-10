package com.bladez.pokemonlist.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bladez.pokemonlist.R;
import com.bladez.pokemonlist.model.PokeDetails;
import com.bladez.pokemonlist.model.PokeSpecies;
import com.bladez.pokemonlist.service.PokeAsyncTask;
import com.bladez.pokemonlist.service.PokeCallback;
import com.bladez.pokemonlist.ui.component.adapter.PokeItemAdapter;
import com.bladez.pokemonlist.model.PokeItem;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ListFragment extends Fragment {

    private NavController navController;
    private Fragment fragment;
    private Button exit;
    private TextInputEditText searchbar;
    private Button search;
    private RecyclerView list;
    private PokeItemAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    private PokeCallback detailsCallback = res -> {
        if (res != null) {
            Gson gson = new Gson();
            PokeSpecies species = gson.fromJson(res, PokeSpecies.class);

            Bundle bundle = new Bundle();
            bundle.putParcelable("species", species);

            navController.navigate(R.id.detailsFragment, bundle);
        } else {
            Toast.makeText(getActivity(), "Pokemon not found!", Toast.LENGTH_SHORT).show();
        }
    };

    public ListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        fragment = this;
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list, container, false);

        navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);

        exit = view.findViewById(R.id.btn_exit);
        searchbar = view.findViewById(R.id.editText);
        search = view.findViewById(R.id.btn_search);
        list = view.findViewById(R.id.recycler_view_list);
        list.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        list.setLayoutManager(layoutManager);

        exit.setOnClickListener(v -> getActivity().finishAndRemoveTask());

//        searchbar.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                if (s.length() == 0) {
//                    //TODO richiamare onCreateView
//                    getActivity().getSupportFragmentManager().beginTransaction().detach(fragment).attach(fragment).commit();
//                }
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//            }
//        });

        search.setOnClickListener(v -> {
//            Log.d("LOG", "onCreateView: clicked on search button!");
            //TODO recuperare stringa da ricercare
            String pokemonName = searchbar.getText().toString().toLowerCase();

            //TODO fare chiamata con il nome pokemon
            PokeAsyncTask task = new PokeAsyncTask("pokemon-species/" + pokemonName, detailsCallback);
            task.execute();
            //TODO ricaricare la lista con l'elemento trovato o con TextView not found!
        });

        String next = "";
        ArrayList<PokeItem> pokemons = null;

//        Log.d("LOG", "checking arguments...");
        if (getArguments() != null) {
//            Log.d("LOG", "arguments found! Loading...");
            next = getArguments().getString("next");
            pokemons = getArguments().getParcelableArrayList("pokemons");
//            Log.d("LOG", "Loaded!");
        }

        if (!"".equalsIgnoreCase(next) && pokemons != null) {
            adapter = new PokeItemAdapter(getActivity(), pokemons, next, detailsCallback);
            list.setAdapter(adapter);

        } else {
            //TODO error
            Toast.makeText(getActivity(), "Error: no arguments received!", Toast.LENGTH_SHORT).show();
//            Log.d("LOG", "Error: no arguments received!");
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

}
