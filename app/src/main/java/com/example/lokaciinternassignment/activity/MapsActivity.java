package com.example.lokaciinternassignment.activity;

import android.os.Bundle;
import android.widget.OverScroller;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.lokaciinternassignment.R;
import com.example.lokaciinternassignment.adapter.ShopListAdapter;
import com.example.lokaciinternassignment.model.ShopListItem;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import me.everything.android.ui.overscroll.HorizontalOverScrollBounceEffectDecorator;
import me.everything.android.ui.overscroll.OverScrollDecoratorHelper;
import me.everything.android.ui.overscroll.adapters.RecyclerViewOverScrollDecorAdapter;

import java.util.ArrayList;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }

        ArrayList<ShopListItem> shopListItems = new ArrayList<>();
        shopListItems.add(new ShopListItem(R.drawable.shop_one,"First Shop",5,2.0,"Address One"));
        shopListItems.add(new ShopListItem(R.drawable.shop_two,"Second Shop",4,5.0,"Address Two"));
        shopListItems.add(new ShopListItem(R.drawable.shop_three,"Third Shop",3,7.0,"Address Three"));
        shopListItems.add(new ShopListItem(R.drawable.shop_four,"Forth Shop",2,1.0,"Address Four"));

        RecyclerView recyclerView = findViewById(R.id.recycle_view);
        
        ShopListAdapter shopListAdapter = new ShopListAdapter(shopListItems);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        recyclerView.setAdapter(shopListAdapter);

    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        // Add a marker in Sydney and move the camera
        LatLng coordinate = new LatLng(-34, 151);
        googleMap.addMarker(new MarkerOptions().position(coordinate).title("Marker in Sydney"));

        googleMap.moveCamera(CameraUpdateFactory.newLatLng(coordinate));
        googleMap.animateCamera(CameraUpdateFactory.zoomIn());
        googleMap.animateCamera(CameraUpdateFactory.zoomTo(15F), 2000, null);
    }
}
