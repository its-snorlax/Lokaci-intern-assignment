package com.example.lokaciinternassignment.activity;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Picture;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import com.example.lokaciinternassignment.OnSnapPositionChangeListener;
import com.example.lokaciinternassignment.R;
import com.example.lokaciinternassignment.SnapPositionDetector;
import com.example.lokaciinternassignment.adapter.ShopListAdapter;
import com.example.lokaciinternassignment.model.ShopListItem;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.*;

import java.util.ArrayList;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback, OnSnapPositionChangeListener {

    private GoogleMap googleMap;
    private ArrayList<ShopListItem> shopListItems;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);

        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }

        shopListItems = new ArrayList<>();
        shopListItems.add(new ShopListItem(R.drawable.shop_one, "New Delhi", 5.0, 2.0, "Address One", 28.6139, 77.2090));
        shopListItems.add(new ShopListItem(R.drawable.shop_two, "Agra", 4.0, 5.0, "Address Two", 27.1767, 78.0081));
        shopListItems.add(new ShopListItem(R.drawable.shop_three, "Jaipur", 3.0, 7.0, "Address Three", 26.9124, 75.7873));
        shopListItems.add(new ShopListItem(R.drawable.shop_four, "Gurugram", 2.0, 1.0, "Address Four", 28.4595, 77.0266));
        shopListItems.add(new ShopListItem((R.drawable.red_kitten_01), "Apka Bazar", 3.0, 2.5, "Address Five", 28.4598, 77.0312));


        RecyclerView recyclerView = findViewById(R.id.recycle_view);
        ShopListAdapter shopListAdapter = new ShopListAdapter(shopListItems);
        final PagerSnapHelper pagerSnapHelper = new PagerSnapHelper();

        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(shopListAdapter);
        pagerSnapHelper.attachToRecyclerView(recyclerView);

        recyclerView.addOnScrollListener(new SnapPositionDetector(this, pagerSnapHelper));
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        for (ShopListItem shopListItem : shopListItems) {
            double longitude = shopListItem.getLongitude();
            double latitude = shopListItem.getLatitude();
            String shopName = shopListItem.getShopName();
            double rating = shopListItem.getRating();
            LatLng coordinate = new LatLng(latitude, longitude);
            googleMap.addMarker(new MarkerOptions().position(coordinate).title(shopName).icon(BitmapDescriptorFactory.fromBitmap(createBitmap(shopName,rating))));
        }
    }

    @Override
    public void onSnapPositionChange(int position) {
        ShopListItem currentItem = shopListItems.get(position);
        LatLng coordinate = new LatLng(currentItem.getLatitude(), currentItem.getLongitude());
        MarkerOptions markerOptions = new MarkerOptions().position(coordinate).title(currentItem.getShopName()).icon(BitmapDescriptorFactory.fromBitmap(createBitmap(currentItem.getShopName(),currentItem.getRating())));
        googleMap.addMarker(markerOptions).showInfoWindow();
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(coordinate));
        googleMap.animateCamera(CameraUpdateFactory.zoomIn());
        googleMap.animateCamera(CameraUpdateFactory.zoomTo(15F), 2000, null);
    }

    private Bitmap createBitmap(String name,Double rating) {
        View markerLayout = getLayoutInflater().inflate(R.layout.custom_marker, null);

        TextView nameView = markerLayout.findViewById(R.id.name);
        TextView ratingView = markerLayout.findViewById(R.id.rating);

        nameView.setText(name);
        ratingView.setText(String.format("%s", rating));

        markerLayout.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED), View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        markerLayout.layout(0, 0, markerLayout.getMeasuredWidth(), markerLayout.getMeasuredHeight());

        final Bitmap bitmap = Bitmap.createBitmap(markerLayout.getMeasuredWidth(), markerLayout.getMeasuredHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        markerLayout.draw(canvas);
        return bitmap;
    }
}
