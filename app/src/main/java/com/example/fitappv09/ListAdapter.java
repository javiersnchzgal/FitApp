package com.example.fitappv09;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class ListAdapter extends ArrayAdapter implements View.OnClickListener {

    private int layout;
    public Intent intent;

    public ListAdapter(@NonNull Context context, int resource, @NonNull List objects) {
        super(context, resource, objects);
        layout = resource;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ListViewHolder  mainViewHolder = null;
        if(convertView == null) {
            LayoutInflater inflater =LayoutInflater.from(getContext());
            convertView = inflater.inflate(layout, parent, false);
            ListViewHolder viewHolder = new ListViewHolder();
            viewHolder.text = convertView.findViewById(R.id.tvDatosEjercicio);
            viewHolder.btnEdit = convertView.findViewById(R.id.btnExerciseEdit);
            viewHolder.btnDelete = convertView.findViewById(R.id.btnExerciseEdit);
            convertView.setTag(viewHolder);
        }

        mainViewHolder = (ListViewHolder) convertView.getTag();
        mainViewHolder.btnDelete.setOnClickListener(this);
        mainViewHolder.btnEdit.setOnClickListener(this);
        mainViewHolder.text.setText((CharSequence) getItem(position));


        return convertView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnExerciseEdit:
                Toast.makeText(getContext(),"Editar ejercicio",Toast.LENGTH_SHORT).show();
            case R.id.btnExerciseDelete:
                Toast.makeText(getContext(),"Eliminar ejercicio",Toast.LENGTH_SHORT).show();
        }
    }
}
