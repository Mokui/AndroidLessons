package com.example.reclyclerviewexo.notes.helpers;

import com.example.reclyclerviewexo.notes.DAO.NotesDatabaseHelper;
import com.example.reclyclerviewexo.notes.adapter.NoteAdapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

public class ItemTouchHelperCallback extends ItemTouchHelper.Callback {
    private NoteAdapter adapter;

    public ItemTouchHelperCallback(NoteAdapter adapter) {
        this.adapter = adapter;
    }

    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        int dragFlagsUpDown = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
        int dragFlagsLeftRight = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
        return makeMovementFlags(dragFlagsUpDown,dragFlagsLeftRight);
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        adapter.onItemDismiss(viewHolder);
    }
}
