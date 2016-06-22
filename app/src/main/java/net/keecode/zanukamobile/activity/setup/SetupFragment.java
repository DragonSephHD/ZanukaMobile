package net.keecode.zanukamobile.activity.setup;

import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.View;

import net.keecode.zanukamobile.element.NoSwipeViewPager;

/**
 * Created by bteusm on 16.06.2016.
 */
public abstract class SetupFragment extends Fragment {

    protected Fragment fragment;

    protected int fragmentId;

    public static String phoneNumber;

    protected static String email;

    protected static String username;

    public abstract void start(Fragment c, NoSwipeViewPager viewPager);

    public abstract void resume(Fragment c);

    public abstract void pause(Fragment c);

    public abstract void stop(Fragment c);

    public int getFragmentId(){
        return fragmentId;
    }

    public void showSnackbar(String message, View view){
        Snackbar snackbar = Snackbar
                .make(view, message, Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    protected View findViewById(int id){
        return fragment.getActivity().findViewById(id);
    }

    public Fragment getFragment() {
        return fragment;
    }

    public void setFragment(Fragment fragment) {
        this.fragment = fragment;
    }

    public static String getPhoneNumber() {
        return phoneNumber;
    }
}
