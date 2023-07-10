package Adapter;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.prm392_finalecommerce.Fragment_Admin_Category;
import com.example.prm392_finalecommerce.Fragment_Admin_Product;

public class AdminTabAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public AdminTabAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                Fragment_Admin_Category tab1 = new Fragment_Admin_Category();
                return tab1;
            case 1:
                Fragment_Admin_Product tab2 = new Fragment_Admin_Product();
                return tab2;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
