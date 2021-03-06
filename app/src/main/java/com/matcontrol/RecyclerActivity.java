package com.matcontrol;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.matcontrol.control.BottomSheetBehaviorRecyclerManager;
import com.matcontrol.control.BottomSheetBehavior_v27;

import java.util.ArrayList;
import java.util.List;

public class RecyclerActivity extends AppCompatActivity {

    private BottomSheetBehavior_v27<View> mBottomSheetBehavior;
    private View mBottomSheetView;

    private CoordinatorLayout mParent;


    private RecyclerView mBottomSheetRecyclerRight;
    private LinearLayoutManager mLayoutManagerRight;
    private RecyclerAdapter mAdapterRight;

    private RecyclerView mBottomSheetRecyclerLeft;
    private LinearLayoutManager mLayoutManagerLeft;
    private RecyclerAdapter mAdapterLeft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);

        mParent = (CoordinatorLayout) findViewById(R.id.parent_container);
        mParent.setDescendantFocusability(ViewGroup.FOCUS_BLOCK_DESCENDANTS);

        mBottomSheetView = findViewById(R.id.main_bottomsheet);


        mBottomSheetRecyclerLeft = (RecyclerView) findViewById(R.id.btm_recyclerview_left);
        mLayoutManagerLeft = new LinearLayoutManager(this);
        mBottomSheetRecyclerLeft.setLayoutManager(mLayoutManagerLeft);

        mBottomSheetRecyclerRight = (RecyclerView) findViewById(R.id.btm_recyclerview_right);
        mLayoutManagerRight = new LinearLayoutManager(this);
        mBottomSheetRecyclerRight.setLayoutManager(mLayoutManagerRight);


        mBottomSheetBehavior = BottomSheetBehavior_v27.from(mBottomSheetView);

        mBottomSheetBehavior.setPeekHeight(150);
        mBottomSheetBehavior.setState(BottomSheetBehavior_v27.STATE_COLLAPSED);
        mBottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior_v27.BottomSheetCallback() {
            @Override
            public void onStateChanged(View bottomSheet, int newState) {
                if (newState == BottomSheetBehavior_v27.STATE_COLLAPSED) {
                    //  mBottomSheetBehavior.setPeekHeight(0);
                }
            }

            @Override
            public void onSlide(View bottomSheet, float slideOffset) {
            }
        });

        mAdapterLeft = new RecyclerAdapter();
        mBottomSheetRecyclerLeft.setAdapter(mAdapterLeft);
        mAdapterLeft.setOnClickInterface(new RecyclerAdapter.OnClickInterface() {
            @Override
            public void onClick(TempModel tempModel) {
                Toast.makeText(RecyclerActivity.this, "Clicked ".concat(tempModel.getName()), Toast.LENGTH_SHORT).show();
            }
        });

        mAdapterRight = new RecyclerAdapter();
        mBottomSheetRecyclerRight.setAdapter(mAdapterRight);
        mAdapterRight.setOnClickInterface(new RecyclerAdapter.OnClickInterface() {
            @Override
            public void onClick(TempModel tempModel) {
                Toast.makeText(RecyclerActivity.this, "Clicked ".concat(tempModel.getName()), Toast.LENGTH_SHORT).show();
            }
        });

        List<TempModel> modelsLeft = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            modelsLeft.add(new TempModel(i + " left"));
        }
        List<TempModel> modelsRight = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            modelsRight.add(new TempModel(i + " right"));
        }


        mAdapterLeft.update(modelsLeft);
        mAdapterRight.update(modelsRight);

        //helper to rule scrolls
        BottomSheetBehaviorRecyclerManager manager = new BottomSheetBehaviorRecyclerManager(mParent, mBottomSheetBehavior, mBottomSheetView);
        manager.addControl(mBottomSheetRecyclerLeft);
        manager.addControl(mBottomSheetRecyclerRight);
        manager.create();

    }
}
