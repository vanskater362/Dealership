package com.cardealership;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.List;
import java.util.Objects;

public class HintAdapter extends ArrayAdapter<String> {

    HintAdapter(Context theContext, List<String> objects, int theLayoutResId) {
        super(theContext, theLayoutResId, objects);
    }

    @Override
    public int getCount() {
        // don't display last item. It is used as hint.
        int count = super.getCount();
        return count > 0 ? count - 1 : count;
    }
}
