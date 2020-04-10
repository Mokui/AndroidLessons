package com.example.reclyclerviewexo.notes.adapter;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.reclyclerviewexo.R;
import com.example.reclyclerviewexo.activities.DetailActivity;
import com.example.reclyclerviewexo.fragments.DetailFragment;
import com.example.reclyclerviewexo.notes.DAO.NotesDatabaseHelper;
import com.example.reclyclerviewexo.notes.POJOS.NoteDTO;
import com.example.reclyclerviewexo.notes.POJOS.RetourWS;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.RecyclerView;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {
    private List<NoteDTO> listeNotes;
    private AppCompatActivity main;

    public NoteAdapter(List<NoteDTO> listeNotes, AppCompatActivity main) {
        this.listeNotes = listeNotes;
        this.main = main;
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View viewNote = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note, parent, false);
        return new NoteViewHolder(viewNote);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        holder.textViewHolder.setText(listeNotes.get(position).intitule);
    }

    @Override
    public int getItemCount() {
        return listeNotes.size();
    }

    public void onItemDismiss(RecyclerView.ViewHolder view) {
        if(view.getAdapterPosition() > -1){
            NotesDatabaseHelper.getDatabase(view.itemView.getContext()).noteDAO().delete(listeNotes.get(view.getAdapterPosition()));
            listeNotes.remove(view.getAdapterPosition());
            notifyItemRemoved(view.getAdapterPosition());
        }
    }

    public class NoteViewHolder extends RecyclerView.ViewHolder {

        public TextView textViewHolder;
        public Button btnViewHolder;
        public RetourWS retourWS;
        private static final String TAG = "Im the tag guy: ";

        public NoteViewHolder(@NonNull final View itemView) {
            super(itemView);

            textViewHolder = itemView.findViewById(R.id.titlenote);
            btnViewHolder = itemView.findViewById(R.id.btn);

            itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(final View view) {
                    NoteDTO note = listeNotes.get(getAdapterPosition());
                    //Toast.makeText(view.getContext(), "Note numéro "+getAdapterPosition(), Toast.LENGTH_SHORT).show();

                    SharedPreferences preferences =
                            PreferenceManager.getDefaultSharedPreferences(view.getContext());
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putInt("last", getAdapterPosition());
                    editor.apply();


                    // client HTTP :
                    AsyncHttpClient client = new AsyncHttpClient();

                    // paramètres :
                    RequestParams requestParams = new RequestParams();
                    requestParams.put("memo", note.intitule);

                    // appel :
                    client.post("http://httpbin.org/post", requestParams, new AsyncHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, byte[] responseBody) {
                            String retour = new String(responseBody);

                            Gson gson = new Gson();
                            retourWS = gson.fromJson(retour, RetourWS.class);
                            //Toast.makeText(view.getContext(), retourWS.form.memo, Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailure(int statusCode, cz.msebera.android.httpclient.Header[] headers, byte[] responseBody, Throwable error) {
                            Log.e(TAG, error.toString());
                        }
                    });

                    if(main.findViewById(R.id.large_detail) == null) {
                        Intent intent = new Intent(view.getContext(), DetailActivity.class);
                        intent.putExtra("detail", note.intitule);
                        view.getContext().startActivity(intent);
                    } else {
                        // fragment :
                        DetailFragment fragment = new DetailFragment();
                        Bundle bundle = new Bundle();
                        bundle.putString(DetailFragment.EXTRA_PARAM, note.intitule);
                        fragment.setArguments(bundle);

                        // fragment manager :
                        FragmentManager fragmentManager = main.getSupportFragmentManager();
                        // transaction :
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.large_detail, fragment, "exemple2");
                        fragmentTransaction.commit();
                    }
                }
            });
        }
    }
}
