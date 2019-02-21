package com.vironit.android.weather.ui.maps;

import android.content.Context;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.clustering.ClusterManager;
import com.google.maps.android.clustering.view.DefaultClusterRenderer;
import com.google.maps.android.ui.IconGenerator;
import com.vironit.android.weather.R;


public class ClusterIconRendered extends DefaultClusterRenderer<OfficeClusterItem> {

    private TextView textView;
    private IconGenerator iconGenerator;

    public ClusterIconRendered(Context context, GoogleMap map, ClusterManager<OfficeClusterItem> clusterManager) {
        super(context, map, clusterManager);
        int size = context.getResources().getDimensionPixelSize(R.dimen.map_icon_size);
        textView = new TextView(context);
        textView.setBackgroundResource(R.drawable.bg_marker);
        textView.setGravity(Gravity.CENTER);
        textView.setLayoutParams(new ViewGroup.LayoutParams(size, size));
        iconGenerator = new IconGenerator(context);
        iconGenerator.setContentView(textView);
        iconGenerator.setBackground(null);
    }

    @Override
    protected void onBeforeClusterItemRendered(OfficeClusterItem item, MarkerOptions markerOptions) {
        super.onBeforeClusterItemRendered(item, markerOptions);
        textView.setText(item.getTitle());
        markerOptions.icon(BitmapDescriptorFactory.fromBitmap(iconGenerator.makeIcon()));
        markerOptions.snippet(item.getSnippet());
        markerOptions.title(item.getTitle());
        super.onBeforeClusterItemRendered(item, markerOptions);
    }
}
