package com.example.mustofa.shipinspector;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Hull.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Hull#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Hull extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Spinner sp_hull,sp_subhull,sp_hull_inspek;
    LinearLayout subHull,hullInspek;
    ArrayAdapter<String> subAdapter;
    private String[] jenis = new String[]{"Bottom","Side Shell","Bulkhead","Main Deck","Second Deck","Superstructure and Deckhouse","Piping","Sub Assembly","Assembly","Grand Assembly","Erection"};
    private String[] bottom = new String[]{"Pelat Alas","Profil Alas","Center Girder","Side Girder","Strut Pelat Wrang","Pelat Alas Dalam","Profil Alas Dalam"};
    private String[] sideshell = new String[]{"Pelat Sisi Atas","Profil Gading Besar","Profil Gading Biasa","Bracket"};
    private String[] maindeck = new String[]{"Pelat Geladak","Profil Deck Beam","Profil Strong Beam","Profil Deck Girder","Profil Deck Side Girder","Bracket"};
    private String[] bulkhead = new String[]{"Pelat Sekat","Profil Penegar"};
    private String[] superStructure = new String[]{"Pelat Geladak","Profil Deck Beam","Profil Strong Beam","Profil Deck Girder"};
    private String[] jenisInspek = new String[]{"Material Identification","Preparation","Fabrication"};
    private Button button;
    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Hull.
     */
    // TODO: Rename and change types and number of parameters
    public static Hull newInstance(String param1, String param2) {
        Hull fragment = new Hull();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public Hull() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_hull, container, false);
        subHull = (LinearLayout)view.findViewById(R.id.view_subhull);
        hullInspek = (LinearLayout)view.findViewById(R.id.view_hull_inspek);
        sp_hull = (Spinner) view.findViewById(R.id.sp_hull);
        sp_hull_inspek = (Spinner) view.findViewById(R.id.sp_hull_inspek);
        sp_subhull = (Spinner) view.findViewById(R.id.sp_subhull);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item,jenis);
        ArrayAdapter<String> inspekAdapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item,jenisInspek);
        sp_hull.setAdapter(arrayAdapter);
        sp_hull_inspek.setAdapter(inspekAdapter);
        sp_hull.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(sp_hull.getSelectedItemPosition() < 7)
                {
                    if(sp_hull.getSelectedItemPosition() < 6) {
                        subHull.setVisibility(View.VISIBLE);
                        switch (position) {
                            case 0:
                                subAdapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item,bottom);
                                break;
                            case 1:
                                subAdapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item,sideshell);
                                break;
                            case 2:
                                subAdapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item,bulkhead);
                                break;
                            case 3:
                                subAdapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item,maindeck);
                                break;
                            case 4:
                                subAdapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item,maindeck);
                                break;
                            case 5:
                                subAdapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item,superStructure);
                                break;
                        }
                    }
                    sp_subhull.setAdapter(subAdapter);
                    hullInspek.setVisibility(View.VISIBLE);
                }
                else
                {
                    hullInspek.setVisibility(View.GONE);
                    subHull.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        button = (Button) view.findViewById(R.id.hull_next);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = null;
                if(sp_hull.getSelectedItemPosition() < 7)
                {
                    switch (sp_hull_inspek.getSelectedItemPosition()){
                        case 0:
                            intent = new Intent(getContext(),MaterialForm.class);
                            break;
                        case 1:
                            intent = new Intent(getContext(),PrepFabForm.class);
                            intent.putExtra("judul","Preparation");
                            break;
                        case 2:
                            intent = new Intent(getContext(),PrepFabForm.class);
                            intent.putExtra("judul","Fabrication");
                            break;
                    }
                }
                else
                {
                    switch (sp_hull.getSelectedItemPosition()){
                        case 7:
                            intent = new Intent(getContext(),SubAssemblyForm.class);
                            break;
                        case 8:
                            intent = new Intent(getContext(),AssemblyForm.class);
                            break;
                        case 9:
                            intent = new Intent(getContext(),GrandAssemblyForm.class);
                            break;
                        case 10:
                            intent = new Intent(getContext(),ErectionForm.class);
                            break;
                    }
                }
                intent.putExtra("state","hull");
                startActivity(intent);
            }
        });
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }
/*

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
*/

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}
