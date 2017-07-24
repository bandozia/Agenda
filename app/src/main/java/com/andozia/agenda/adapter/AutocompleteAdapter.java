package com.andozia.agenda.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import com.andozia.agenda.R;
import com.andozia.contatolib.ContatoPF;

import java.util.ArrayList;
import java.util.List;
//Adapter especifico para o autocomplete text view
public class AutocompleteAdapter extends ArrayAdapter<ContatoPF> {

    private Context contex;
    private List<ContatoPF> contatos;
    private int resourceId;

    private List<ContatoPF> sugestions;

    public AutocompleteAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<ContatoPF> objects) {
        super(context, resource, objects);

        this.contex = context;
        this.contatos = objects;
        this.resourceId = resource;

        this.sugestions = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return this.contatos.size();
    }

    @Nullable
    @Override
    public ContatoPF getItem(int position) {
        return this.contatos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @NonNull
    @Override
    public Filter getFilter() {
        return filter;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        ContatoPF contato = this.contatos.get(position);
        View linha = LayoutInflater.from(this.contex).inflate(this.resourceId, parent, false);

        ((TextView) linha.findViewById(R.id.auto_nomeTx)).setText(contato.getNome());
        ((TextView) linha.findViewById(R.id.auto_sobrenomeTx)).setText(contato.getSobrenome());
        ((TextView) linha.findViewById(R.id.auto_emailTx)).setText(contato.getEmail());

        return linha;

    }

    Filter filter = new Filter() {

        @Override
        public CharSequence convertResultToString(Object resultValue) {
            return ((ContatoPF) resultValue).getNome();
        }

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            if (constraint != null){
                sugestions.clear();
                FilterResults results = new FilterResults();
                for (ContatoPF contato : contatos){
                    if (contato.getNome().toLowerCase().startsWith(constraint.toString().toLowerCase())){
                        sugestions.add(contato);
                    }
                }
                results.count = sugestions.size();
                results.values = sugestions;
                return results;

            }else{
                return null;
            }
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {

            if (results != null && results.count > 0){
                List<ContatoPF> contatos = (ArrayList<ContatoPF>) results.values;
                clear();
                for (ContatoPF contato : contatos){
                    add(contato);
                }
                notifyDataSetChanged();
            }
        }
    };

}
