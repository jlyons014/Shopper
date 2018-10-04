package com.example.joe.shopper;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

public class ShoppingLists extends CursorAdapter {
    /**
     * Initializes ShoppingLists CursorAdapter
     * @param context reference to activity that initialized the CursorAdapter
     * @param cursor reference to the actual cursor that contains the data from
     *               the database
     * @param flags determines special behavior of the CursorAdapter. Will
     *              always be 0 which means the Cursor Adapter shouldn't have
     *              any special behavior
     */
    public ShoppingLists (Context context, Cursor cursor, int flags)
    {
        super(context, cursor, flags);
    }

    /**
     * Makes a new View to hold the data in the Cursor.
     * @param context reference to activity that initialized the CursorAdapter
     * @param cursor reference to the actual cursor that contains the data from the database
     * @param viewGroup reference to the shopperListView in the main activity
     * @return reference to the new View that will be displayed in the Main Activity,
     *          e.g. the one that contains the data
     */
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {

        return LayoutInflater.from(context).inflate(R.layout.li_shopping_list, viewGroup, false);
    }

    /**
     * Binds the new View to the data in the cursor
     * @param view new View just created
     * @param context references to activity that intitialized the CursorAdapter
     * @param cursor reference to the actual cursor that contains the data from the database
     */
    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        ((TextView) view.findViewById(R.id.nameTextView)).setText
                (cursor.getString(cursor.getColumnIndex("name")));
        ((TextView) view.findViewById(R.id.storeTextView)).setText
                (cursor.getString(cursor.getColumnIndex("store")));
        ((TextView) view.findViewById(R.id.dateTextView)).setText
                (cursor.getString(cursor.getColumnIndex("date")));

    }
}
