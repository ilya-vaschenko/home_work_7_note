package com.example.home_work_7_note;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NotesViewHolder> {

    private NoteSource dataSource;
    public static final String TAG = "NoteAdapter";
    private OnItemClickListener clickListener;
    private final Fragment fragment;
    private int menuPosition;

    public int getMenuPosition() {
        return menuPosition;
    }

    public NotesAdapter(Fragment fragment) {
        this.fragment = fragment;
    }

    public void setDataSource(NoteSource dataSource){
        this.dataSource = dataSource;
        notifyDataSetChanged();
    }

    public void setList(NoteSource dataSource) {
        this.dataSource = dataSource;
        notifyDataSetChanged();
    }

    public void setListener(@NonNull OnItemClickListener clickListener) {
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public NotesAdapter.NotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new NotesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotesAdapter.NotesViewHolder holder, int position) {
        holder.bind(dataSource.getNote(position));

        Log.d(TAG, String.valueOf(dataSource.getNote(position).getName()));
    }

    @Override
    public int getItemCount() {
        return dataSource.size();
    }

    public class NotesViewHolder extends RecyclerView.ViewHolder {
        private final TextView name;
        private final TextView date;
        private final CheckBox checkBox;

        public NotesViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.item_name);
            date = itemView.findViewById(R.id.item_date);
            checkBox = itemView.findViewById(R.id.checkBox);

            registerContextMenu(itemView);

            itemView.setOnClickListener(v -> {
                clickListener.onItemClick(getAdapterPosition());
                menuPosition = getLayoutPosition();
            });
        }

        private void registerContextMenu(@NonNull View itemView) {
            if (fragment != null) {
                itemView.setOnLongClickListener(v -> {
                    menuPosition = getLayoutPosition();
                    return false;
                });

                fragment.registerForContextMenu(itemView);
            }
        }

        public void bind(@NonNull Note note) {
            name.setText(note.getName());
            date.setText(note.getDate().toString());
            date.setText(new SimpleDateFormat("dd-MM-yy").format(note.getDate()));
        }
    }

    interface OnItemClickListener {
        void onItemClick(int position);
    }
}
